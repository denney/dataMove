package main.com.dst.WorkDAO;

import main.com.dst.WorkDAO.tool.CreateDir;
import main.com.dst.WorkDAO.tool.GetListTableByDB_con;
import main.com.dst.WorkDAO.tool.Sql_joint;
import main.com.dst.baseFrame.DBConfig;
import main.com.dst.baseFrame.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 2017/8/18.
 */
public class ExcuteSQL {


    public static boolean H2ExportData(Connection h2_con, String h2_DB_Name, String BasePath, List list_cvs_tableName) {
        String sql = null;

        PreparedStatement pstmt = null;
        int result = -10000000;

        String DB_cvs_path = BasePath + "/" + h2_DB_Name + "/";

        boolean flag=CreateDir.goCreateDir(DB_cvs_path);
        try {
            sql = Sql_joint.getSqldataToCsv(list_cvs_tableName, DB_cvs_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pstmt = h2_con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            result = pstmt.executeUpdate();//这个需要看一下debug，这个值现在不确定
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.release(pstmt, h2_con);
        if (result==0) {
            return true;
        }
        return false;
    }

    public static boolean DataImportPostgresql(Connection postgre_con, String postgre_DB_name, String BasePath, List list_cvs_tableName) {
        String sql = null;

        PreparedStatement pstmt = null;
        int result;

        String DB_cvs_path = BasePath + "/" + postgre_DB_name + "/";
        try {
            sql = Sql_joint.getSqlCsvToData(list_cvs_tableName, DB_cvs_path);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            pstmt = postgre_con.prepareStatement(sql);
            System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            result = pstmt.executeUpdate();//这个需要看一下debug，这个值现在不确定
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.release(pstmt, postgre_con);
        return false;
    }


    public static void h2DataGoPostgreData(Connection h2_con, Connection postgre_con, String h2_DB_Name, String postgre_DB_name, String BasePath) {
        List list_cvs_tableName = null;

        try {
            list_cvs_tableName = GetListTableByDB_con.findTableNameListByDB_con(h2_con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean h2_flag = H2ExportData(h2_con, h2_DB_Name, BasePath, list_cvs_tableName);

        if (h2_flag) {
            boolean postgre_flag = DataImportPostgresql(postgre_con, postgre_DB_name, BasePath, list_cvs_tableName);
            if (postgre_flag) {
                System.out.println("数据从h2:" + h2_DB_Name + "数据库,到postgre：" + postgre_DB_name + "数据库导入成功");
            } else {
                System.out.println("数据从h2:" + h2_DB_Name + "数据库,到postgre：" + postgre_DB_name + "数据库导入成功");
            }
        }


    }








}
