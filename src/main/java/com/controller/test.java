package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created by Administrator on 2015/12/23.
 */
public class test {
    public static Connection getConnection(){
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/test";
        String name="root";
        String pwd="123456";
        try{
            Class.forName(driver);
            Connection conn=DriverManager.getConnection(url,name,pwd);
            return conn;
        }catch(ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){
        try{
            if(rs!=null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(ps!=null){
                ps.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            if(conn!=null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException
    {

        Connection cc=test.getConnection();

        if(!cc.isClosed())

            System.out.println("Succeeded connecting to the Database!");
        Statement statement = cc.createStatement();
        String sql = "select * from user_test";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getString("userName")+"");
        }


    }
}
