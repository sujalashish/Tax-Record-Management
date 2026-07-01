package dao;

import config.DBConnection;
import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    // REGISTER ADMIN

    public boolean registerAdmin(Admin admin) {

        boolean success = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO admins "
                    + "(first_name,last_name,email,password,"
                    + "dob,gender,marital_status,"
                    + "mobile,alternate_mobile,"
                    + "address1,address2,"
                    + "city,state,pin_code,"
                    + "aadhar,pan,"
                    + "account_status,role_name,"
                    + "photo_path)"
                    + " VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";



            PreparedStatement pst =
                    con.prepareStatement(sql);



            pst.setString(1, admin.getFirstName());
            pst.setString(2, admin.getLastName());

            pst.setString(3, admin.getEmail());
            pst.setString(4, admin.getPassword());

            pst.setString(5, admin.getDob());
            pst.setString(6, admin.getGender());
            pst.setString(7, admin.getMaritalStatus());

            pst.setString(8, admin.getMobile());
            pst.setString(9, admin.getAlternateMobile());

            pst.setString(10, admin.getAddress1());
            pst.setString(11, admin.getAddress2());

            pst.setString(12, admin.getCity());
            pst.setString(13, admin.getState());
            pst.setString(14, admin.getPinCode());

            pst.setString(15, admin.getAadhar());
            pst.setString(16, admin.getPan());

            pst.setString(17, admin.getAccountStatus());
            pst.setString(18, admin.getRole());

            pst.setString(19, admin.getPhotoPath());



            int rows = pst.executeUpdate();

            success = rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return success;
    }



    // LOGIN ADMIN

    public boolean loginAdmin(String email,
                              String password) {

        boolean valid = false;

        try {

            Connection con = DBConnection.getConnection();

            String query =
                    "SELECT * FROM admins "
                    + "WHERE email=? AND password=?";

            PreparedStatement pst =
                    con.prepareStatement(query);

            pst.setString(1, email);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            valid = rs.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return valid;
    }
}