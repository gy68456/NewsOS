package DAO;


import java.sql.ResultSet;

import Bean.UploadBean;
import DB.sqlDB;

public class uploadimg {

	
	/**
	 * ��NewsInfo����в���
	 * @param objNewsInfoBean
	 * @return ����1��ʾ����ɹ�,0��ʾ���ɹ�
	 */
	public int Addimg(UploadBean objUploadBean) {

		sqlDB objsqlDB = new sqlDB();
		// Security objSecurity = new Security();
		String _sql = "insert into imgUpload(imgurl, upimgdepict, upimgtime) Values (?,?,?)";
		String path = objUploadBean.getImgurl();
		String type = objUploadBean.getUpimgdepict();
		String time = objUploadBean.getImgtime();
		

		String[] a = { path, type, time };

		if (objsqlDB.insert(_sql, a) == true) {
			System.out.println("baocun");
			return 1;
		} else {
			return 0;
		}
	}
	
	
	/**
	 * ��ѯǰ4��ͼƬ����ҳ��ʾ
	 * @param NewsID
	 * @return 
	 */
	public ResultSet selectimg() {
		
			
			sqlDB objsqlDB = new sqlDB();
			String _sql = "select top 4 * from imgUpload Order By upimgtime Desc";
		
			//System.out.println(_Sql);
			return objsqlDB.select_2(_sql);
	}
}
