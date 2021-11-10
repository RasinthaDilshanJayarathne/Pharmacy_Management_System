package util.Controller;

import db.DbConnection;
import model.Agent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<Agent> getAllBlood() throws SQLException, ClassNotFoundException {
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
}
