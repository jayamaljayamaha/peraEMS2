package sample;

import java.sql.*;

public class dataModel {

    Connection connection = null;
    PreparedStatement prepStatment = null;
    Statement statement = null;
    ResultSet results = null;

    public String connectDataBase(){

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/peraems","root","94omalka");
            connection= conn;
            return "Succesfully Connected";
        }
        catch(Exception e)
        {
            connection= null;
            return "Connection faild";
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

    public String insertData(String dataBaseName,String[] keys, String[] values){


        String sql = "INSERT INTO "+dataBaseName+" (";

        for(int i=0; i<keys.length; i++){

            if(i==keys.length-1){
                sql=sql+keys[i]+")";
            }
            else{
                sql=sql+keys[i]+", ";
            }

        }
        sql=sql+" VALUES (";
        for(int i=0; i<values.length; i++){

            if(i==values.length-1){
                sql=sql+"'"+values[i]+"'"+")";
            }
            else{
                sql=sql+"'"+values[i]+"'"+", ";
            }

        }

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            return "Succesfully inserted";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error Happened";
        }


    }

    public ResultSet getAllData(String dbName){
        String sql = "SELECT * FROM "+ dbName;
        try {
            prepStatment = connection.prepareStatement(sql);
            return prepStatment.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String closeConnection(){

        try {
            //this.prepStatment.close();
            this.connection.close();
            return "connection terminated succesfully";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error happened";
        }

    }
}
