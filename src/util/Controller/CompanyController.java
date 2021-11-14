package util.Controller;

import db.DbConnection;
import model.Agent;
import model.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyController {
    public boolean saveCompany(Company c) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Company VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getCid());
        stm.setObject(2,c.getName());
        stm.setObject(3,c.getAddress());
        stm.setObject(4,c.getContact());
        return stm.executeUpdate()>0;
    }

    public boolean updateCompany(Company c) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Company SET name=?,address=?,contact=? WHERE Cid=?");
        stm.setObject(1,c.getName());
        stm.setObject(2,c.getAddress());
        stm.setObject(3,c.getContact());
        stm.setObject(4,c.getCid());
        return stm.executeUpdate()>0;
    }

    public boolean deleteCompany(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Company WHERE Aid='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Company> getAllCompany() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Company");
        ResultSet rst = stm.executeQuery();
        ArrayList<Company> companies = new ArrayList<>();
        while (rst.next()) {
            companies.add(new Company(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return companies;
    }

    public Company getCompany(String companyId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Company WHERE Cid=?");
        stm.setObject(1, companyId);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            return new Company(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );

        } else {
            return null;
        }
    }

}
