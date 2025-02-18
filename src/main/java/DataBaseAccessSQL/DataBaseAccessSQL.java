package DataBaseAccessSQL;


import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAccessSQL {

@Test
    public static Connection connectSQL() throws SQLException, ClassNotFoundException {
      //  Class.forName("com.mysql.jdbc.Driver");
        String jdbcUrl = "jdbc:sqlserver://10.195.105.247;encrypt=false;trustServerCertificate=true;";
        String userName = "Training";
        String password = "Aa123456";
       try {
           Connection connection= DriverManager.getConnection(jdbcUrl,userName,password);
            connection.createStatement();
           System.out.println("successfully connected!");
           return connection;

       }catch (SQLException e){
           throw new RuntimeException(e);
       }



    }

        }


