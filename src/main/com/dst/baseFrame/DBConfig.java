package main.com.dst.baseFrame;

public interface DBConfig {



	 String DBURL ="jdbc:h2:/Users/admin/Downloads/database/ES_STORAGE";

	 String DBNAME = "wso2carbon";
	 String DBPASS = "wso2carbon";

	String DBURL1 ="jdbc:postgresql://localhost:5432/dst";

	String DBNAME1 = "postgres";
	String DBPASS1 = "123456";



	//数据存放的临时目录
	String BasePath="/home/H2Data";

//	ES_STORAGE数据库表文件存放的临时目录
	String ES_STORAGE_Path=BasePath+"/ES_STORAGE/";




}
