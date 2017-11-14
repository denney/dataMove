package main.com.dst.WorkDAO.tool;


import main.com.dst.baseFrame.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/16.
 */
public class GetListTableByDB_con {

    public static List<String> findTableNameListByDB_con(Connection con) throws Exception {


        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'";
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<String> list = new ArrayList<String>();
        while (rs.next()) {
            list.add(rs.getString("TABLE_NAME"));

        }
     DBUtil.release(rs, pstmt);
        return list;


    }




}
