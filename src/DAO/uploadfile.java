package DAO;

import java.sql.ResultSet;

import Bean.NewsInfoBean;
import Bean.UploadBean;
import DB.sqlDB;

public class uploadfile {

	/**
	 * ��NewsInfo����в���
	 * 
	 * @param objNewsInfoBean
	 * @return ����1��ʾ����ɹ�,0��ʾ���ɹ�
	 */
	public int Addfile(UploadBean objUploadBean) {

		sqlDB objsqlDB = new sqlDB();
		// Security objSecurity = new Security();
		String _sql = "insert into FileUpload(FilePath, FileType, upfiletime, upfileDepict,name) Values (?,?,?,?,?)";
		String path = objUploadBean.getFilePath();
		String type = objUploadBean.getFileType();
		String time = objUploadBean.getUpfiletime();
		String depict = objUploadBean.getUpfileDepict();
		String name = objUploadBean.getName();

		String[] a = { path, type, time, depict ,name};

		if (objsqlDB.insert(_sql, a) == true) {
			System.out.println("baocun");
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * ��ѯ�����б�
	 * @return
	 */
	public ResultSet selectfile() {
		
		
		sqlDB objsqlDB = new sqlDB();
		String _sql = "select * from FileUpload Order By upfiletime Desc";
	
		//System.out.println(_Sql);
		return objsqlDB.select_2(_sql);
}
}
