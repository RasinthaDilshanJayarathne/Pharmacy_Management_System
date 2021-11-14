package util.Controller;

import db.DbConnection;
import model.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentController {

    public boolean saveAgent(Agent b) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Agent VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,b.getAid());
        stm.setObject(2,b.getName());
        stm.setObject(3,b.getAge());
        stm.setObject(4,b.getContact());
        stm.setObject(5,b.getGender());
        return stm.executeUpdate()>0;
    }

    public boolean updateAgent(Agent a) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Agent SET name=?,age=?,contact=?,gender=? WHERE Aid=?");
        stm.setObject(1,a.getName());
        stm.setObject(2,a.getAge());
        stm.setObject(3,a.getContact());
        stm.setObject(4,a.getGender());
        stm.setObject(5,a.getAid());
        return stm.executeUpdate()>0;
    }

    public boolean deleteAgent(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Agent WHERE Aid='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public static List<Agent> searchAgent(String value) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM Agent WHERE Aid LIKE '%"+value+"%' or bloodGroup LIKE '%"+value+"%'");
        ResultSet rst = pstm.executeQuery();

        List<Agent> agent=new ArrayList<>();
        while (rst.next()) {
            agent.add(new Agent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return agent;
    }

    public ArrayList<Agent> getAllAgent() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Agent");
        ResultSet rst = stm.executeQuery();
        ArrayList<Agent> agent = new ArrayList<>();
        while (rst.next()) {
            agent.add(new Agent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return agent;
    }

    public Agent getAgent(String agentId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Agent WHERE Aid=?");
        stm.setObject(1, agentId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Agent(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getString(5)
            );

        } else {
            return null;
        }
    }
}
