package util.Controller;

import db.DbConnection;
import model.Medicine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineController {
    public boolean saveMedicine(Medicine m) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Company VALUES(?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,m.getId());
        stm.setObject(2,m.getName());
        stm.setObject(3,m.getPack());
        stm.setObject(4,m.getQty());
        stm.setObject(5,m.getMadeDate());
        stm.setObject(6,m.getExpDate());
        stm.setObject(7,m.getCompany());
        return stm.executeUpdate()>0;
    }

    public boolean updateMedicine(Medicine m) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Medicine SET name=?,pack=?,qty=?,madeDate=?,expDate=?,company=? WHERE id=?");
        stm.setObject(1,m.getName());
        stm.setObject(2,m.getPack());
        stm.setObject(3,m.getQty());
        stm.setObject(4,m.getMadeDate());
        stm.setObject(5,m.getExpDate());
        stm.setObject(6,m.getCompany());
        stm.setObject(7,m.getId());

        return stm.executeUpdate()>0;
    }

    public boolean deleteMedicine(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Medicine WHERE id='"+id+"'").executeUpdate()>0){
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Medicine> getAllMedicine() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Medicine");
        ResultSet rst = stm.executeQuery();
        ArrayList<Medicine> medicines = new ArrayList<>();
        while (rst.next()) {
            medicines.add(new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            ));
        }
        return medicines;
    }

    public Medicine getMedicine(String medicineId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Company WHERE Cid=?");
        stm.setObject(1, medicineId);
        ResultSet rst = stm.executeQuery();

        if (rst.next()) {
            return new Medicine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }

}
