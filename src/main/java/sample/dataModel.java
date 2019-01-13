package sample;

import java.sql.*;

public class dataModel {

    Connection connection = null;
    PreparedStatement prepStatment = null;
    ResultSet results = null;

    public void connectDataBase(){

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/peraems","root","94omalka");
            connection= conn;
            System.out.println(connection);
        }
        catch(Exception e)
        {
            connection= null;
        }
    }

    public ResultSet searchData(String dataBaseName, String key, String value){

        String sql = "SELECT * FROM "+ dataBaseName+ " WHERE "+key+ " = ?";
        try {
            prepStatment = connection.prepareStatement(sql);
            prepStatment.setString(1,value);
            return prepStatment.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public void closeConnection(){

        try {
            this.prepStatment.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
