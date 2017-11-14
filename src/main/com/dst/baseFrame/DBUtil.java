package main.com.dst.baseFrame;

import java.sql.*;


public class DBUtil {

    static {
        try {
            Class.forName("org.h2.Driver");
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

    public static Connection getConH2_ES_STORAGE() throws Exception {
        Connection con = null;
        con = DriverManager.getConnection(DBConfig.DBURL, DBConfig.DBNAME, DBConfig.DBPASS);
        return con;
    }

    public static Connection getConPostger_ES_STORAGE() throws Exception {
        Connection con = null;
        con = DriverManager.getConnection(DBConfig.DBURL1, DBConfig.DBNAME1, DBConfig.DBPASS1);
        return con;
    }


    public static void release(Connection con) throws Exception {
        if (!con.isClosed()) {
            con.close();
        }
    }

    public static void release(PreparedStatement pstmt, Connection con) {
        try {
            pstmt.close();
            release(con);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void release(ResultSet rs, PreparedStatement pstmt, Connection con) {

        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        release(pstmt, con);
    }

    public static void release(ResultSet rs, PreparedStatement pstmt) {

        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
