package main.com.dst.baseFrame;



import java.sql.Connection;
import java.sql.PreparedStatement;


public class BaseDAO {

	public Connection getConH21() throws Exception {
		return DBUtil.getConH21();
	}
	
	
	public int executeSQL(String sql,Object[] objs) throws Exception{
		Connection con = getConH21();
		PreparedStatement pstmt = con.prepareStatement(sql);
		if(objs!=null && objs.length!=0){
			for (int i = 0; i < objs.length; i++) {
				pstmt.setObject(i + 1, objs[i]);
				//void setObject(int parameterIndex,Object x)
			

			}
		}
		int result = pstmt.executeUpdate();
		
		 
		DBUtil.release(pstmt,con);
		return result;
	}
	
	public boolean update(String sql,Object[] objs) throws Exception{
		int result = executeSQL(sql, objs);
		if(result > 0){
			return true;
		}else{
			return false;
		}
	}
}
