package main.com.dst.WorkDAO;


import main.com.dst.WorkDAO.tool.CreateDir;
import main.com.dst.baseFrame.DBConfig;
import main.com.dst.baseFrame.DBUtil;

public class Main {

    public static void main(String[] args) throws Exception {
        String BasePath= DBConfig.BasePath;
       boolean flag= CreateDir.goCreateDir(BasePath);

        ExcuteSQL.h2DataGoPostgreData(DBUtil.getConH2_ES_STORAGE(),DBUtil.getConPostger_ES_STORAGE(),"ES_STORAGE","ES_STORAGE",BasePath);

    }


}
