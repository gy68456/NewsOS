package DAO;

import java.sql.ResultSet;

import DB.sqlDB;

public class LoginDAO {

	public LoginDAO(){
		
	}
	
	/**
	 * �� pwd != null ʱ��֤��½,�� pwd = null ʱ��Ӧ���ڲ�ѯ�˻��Ƿ����
	 * @param LoginID
	 * @param pwd 
	 * @return ����1�����м�¼������0�����޼�¼������2�������
	 */
	public int selectAdminUser(String  LoginID,String pwd) {
		try {
			
			sqlDB objsqlDB = new sqlDB();
			String _sql = "select * from Adminuser where LoginID= '" + LoginID + "' ";
			if(pwd != null){
			_sql = _sql + "and pwd='" + pwd + "'";
			}
			
			
			ResultSet rs = objsqlDB.select_2(_sql);
			if(rs.next()){
				return 1;
			}else{
			return 0;
			}
			

		} catch (Exception ex) {
			return 2;
		}
	}
}
