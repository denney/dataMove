package main.com.dst.WorkDAO.tool;

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


    public static boolean H2ExportData(Connection con, String DB_Name, String BasePath,List list_cvs_tableName){
        String sql= null;

        PreparedStatement pstmt=null;
        int result;

        String DB_cvs_path= BasePath+"/"+DB_Name+"/";


        try {
            sql = Sql_joint.getSqldataToCsv(list_cvs_tableName, DB_cvs_path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
             pstmt = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
           result=pstmt.executeUpdate();//这个需要看一下debug，这个值现在不确定
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBUtil.release(pstmt,con);
        return false;
    }
    public static boolean Postgresql(Connection con,String postgre_DB_name){


        return false;
    }


       public static void  h2GoPostgre(Connection h2_con,Connection postgre_con, String h2_DB_Name,String postgre_DB_name, String BasePath){
           List list_cvs_tableName=null;

           try {
               list_cvs_tableName=GetListTableByDB_con.findTableNameListByDB_con(h2_con);
           } catch (Exception e) {
               e.printStackTrace();
           }
           boolean h2_flag=H2ExportData( h2_con,h2_DB_Name,  BasePath, list_cvs_tableName);

           if (h2_flag) {
              boolean postgre_flag= Postgresql(postgre_con,postgre_DB_name);
               if (postgre_flag) {
                   System.out.println("数据从h2:"+h2_DB_Name+"数据库,到postgre："+postgre_DB_name+"数据库导入成功");
               }else {
                   System.out.println("数据从h2:"+h2_DB_Name+"数据库,到postgre："+postgre_DB_name+"数据库导入成功");
               }
           }







    }

}
