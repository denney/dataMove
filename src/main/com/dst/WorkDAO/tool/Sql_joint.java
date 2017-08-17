package main.com.dst.WorkDAO.tool;

import java.util.List;

/**
 * Created by admin on 2017/8/18.
 */
public class Sql_joint {




    public static  String getSqldataToCsv(List<String> list_cvs_tableName, String DB_cvs_path) throws Exception {

        String s="";
        for (int i = 0; i <list_cvs_tableName.size() ; i++) {
//            CALL CSVWRITE('/Users/00323702/Desktop/WSO2DM_DB/DM_APPLICATION.csv', 'SELECT * FROM DM_APPLICATION');
            String sql="CALL CSVWRITE('"+DB_cvs_path+list_cvs_tableName.get(i)+".csv', 'SELECT * FROM "+list_cvs_tableName.get(i)+"');";
            s=sql+s;
        }

        return s;
    }

    public static  String getSqlCsvToData(List<String> list_cvs_tableName,String DB_cvs_path) throws Exception {

        String s="";
        for (int i = 0; i <list_cvs_tableName.size() ; i++) {
//            copy dm_application from '/Users/00323702/Desktop/h2/dm_application.csv' with (format csv, header true, quote '"', DELIMITER ',', encoding 'UTF8');
            String sql="copy"+list_cvs_tableName.get(i)+" from '"+DB_cvs_path+list_cvs_tableName.get(i)+".csv' with (format csv, header true, quote '\"', DELIMITER ',', encoding 'UTF8');";
            s=sql+s;
        }

        return s;
    }
}
