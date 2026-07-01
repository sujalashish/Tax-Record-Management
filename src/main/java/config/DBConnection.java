/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

/**
 *
 * @author Acer
 */

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
     private static final String URL =
            System.getenv("TRM_DB_URL") != null
                ? System.getenv("TRM_DB_URL")
                : "jdbc:mysql://localhost:3306/taxdb";

    private static final String USER =
            System.getenv("TRM_DB_USER") != null
                ? System.getenv("TRM_DB_USER")
                : "root";

    private static final String PASSWORD =
            System.getenv("TRM_DB_PASSWORD") != null
                ? System.getenv("TRM_DB_PASSWORD")
                : "YOUR_PASSWORD_HERE";

    public static Connection getConnection() {

        try {

            Connection con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database Connected!");

            return con;

        } catch (Exception e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();

            return null;
        }
    }
}
