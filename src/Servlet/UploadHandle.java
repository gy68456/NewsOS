package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Bean.UploadBean;
import DAO.uploadfile;
import DAO.uploadimg;
import net.sf.json.JSONObject;
import zjxTool.nowtime;

/**
 * Servlet implementation class UploadHandle
 */
@WebServlet("/UploadHandle")
public class UploadHandle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadHandle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		fileload(request,response);
	}

	/**
	 * @Method: makeFileName
	 * @Description: �����ϴ��ļ����ļ������ļ����ԣ�uuid+"_"+�ļ���ԭʼ����
	 * @Anthor:�°�����
	 * @param filename
	 *            �ļ���ԭʼ����
	 * @return uuid+"_"+�ļ���ԭʼ����
	 */
	private String makeFileName(String filename) { // 2.jpg
		// Ϊ��ֹ�ļ����ǵ���������ҪΪ�ϴ��ļ�����һ��Ψһ���ļ���
		return UUID.randomUUID().toString() + "_" + filename;
	}

	/**
	 * Ϊ��ֹһ��Ŀ¼�������̫���ļ���Ҫʹ��hash�㷨��ɢ�洢
	 * 
	 * @Method: makePath
	 * @Description:
	 * @Anthor:�°�����
	 *
	 * @param filename
	 *            �ļ�����Ҫ�����ļ������ɴ洢Ŀ¼
	 * @param savePath
	 *            �ļ��洢·��
	 * @return �µĴ洢Ŀ¼
	 */
	private String makePath(String filename, String savePath) {
		// �õ��ļ�����hashCode��ֵ���õ��ľ���filename����ַ����������ڴ��еĵ�ַ
		Date now = new Date(); //��ȡ��ǰʱ��
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//��ʽ����ǰ��
		
		String dates  = dateFormat.format(now);
		
		// �����µı���Ŀ¼
		 
		          //�����µı���Ŀ¼
		 String dir = savePath + "\\" + dates;  //upload\2\3  upload\3\5
		// File�ȿ��Դ����ļ�Ҳ���Դ���Ŀ¼
		 
		File file = new File(dir);
		
		// ���Ŀ¼������
		if (!file.exists()) {
			// ����Ŀ¼
			
			file.mkdirs();
			
		}
		return dir;

	}
	
	private void fileload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// �õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
		String savePath = getServletContext().getInitParameter("xunirul");//this.getServletContext().getRealPath("/upload");
		PrintWriter outp = response.getWriter();
		JSONObject objJsonObject = new JSONObject();
		// �ϴ�ʱ���ɵ���ʱ�ļ�����Ŀ¼
		//System.out.println(savePath);
		String tempPath = getServletContext().getInitParameter("xunilinshirul");//this.getServletContext().getRealPath("/temp");
		//System.out.println(tempPath);
		File tmpFile = new File(tempPath);
		if (!tmpFile.exists()) {
			// ������ʱĿ¼
			tmpFile.mkdir();
		}

		// ��Ϣ��ʾ
		String message = "";
		try {
			// ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
			// 1������һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼���С�
			factory.setSizeThreshold(1024 * 100);// ���û������Ĵ�СΪ100KB�������ָ������ô�������Ĵ�СĬ����10KB
			// �����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
			factory.setRepository(tmpFile);
			// 2������һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �����ļ��ϴ�����
			upload.setProgressListener(new ProgressListener() {
				public void update(long pBytesRead, long pContentLength, int arg2) {
					//System.out.println("�ļ���СΪ��" + pContentLength + ",��ǰ�Ѵ�����" + pBytesRead);
					/**
					 * �ļ���СΪ��14608,��ǰ�Ѵ�����4096 �ļ���СΪ��14608,��ǰ�Ѵ�����7367
					 * �ļ���СΪ��14608,��ǰ�Ѵ�����11419 �ļ���СΪ��14608,��ǰ�Ѵ�����14608
					 */
				}
			});
			// ����ϴ��ļ�������������
			upload.setHeaderEncoding("UTF-8");
			// 3���ж��ύ�����������Ƿ����ϴ�����������
			if (!ServletFileUpload.isMultipartContent(request)) {
				// ���մ�ͳ��ʽ��ȡ����
				return;
			}

			// �����ϴ������ļ��Ĵ�С�����ֵ��Ŀǰ������Ϊ1024*1024�ֽڣ�Ҳ����1MB
			upload.setFileSizeMax(1024 * 1024);
			// �����ϴ��ļ����������ֵ�����ֵ=ͬʱ�ϴ��Ķ���ļ��Ĵ�С�����ֵ�ĺͣ�Ŀǰ����Ϊ10MB
			upload.setSizeMax(1024 * 1024 * 10);
			// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form������������
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem item : list) {
				// ���fileitem�з�װ������ͨ�����������
				if (item.isFormField()) {
					String name = item.getFieldName();
					// �����ͨ����������ݵ�������������
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					//System.out.println(name + "=" + value);
				} else {// ���fileitem�з�װ�����ϴ��ļ�
						// �õ��ϴ����ļ����ƣ�
					String filename = item.getName();
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·���ģ��磺
					// c:\a\b\1.txt������Щֻ�ǵ������ļ������磺1.txt
					// ������ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// �õ��ϴ��ļ�����չ��
					String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
					// �����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
					//System.out.println("�ϴ����ļ�����չ���ǣ�" + fileExtName);
					// ��ȡitem�е��ϴ��ļ���������
					InputStream in = item.getInputStream();
					// �õ��ļ����������
					String saveFilename = makeFileName(filename);
					
					// �õ��ļ��ı���Ŀ¼
					String realSavePath = makePath(saveFilename, savePath);
					
					// ����һ���ļ������
					FileOutputStream out = new FileOutputStream(realSavePath + "\\" + saveFilename);
					
					// ����һ��������
					byte buffer[] = new byte[1024];
					// �ж��������е������Ƿ��Ѿ�����ı�ʶ
					int len = 0;
					// ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾin���滹������
					while ((len = in.read(buffer)) > 0) {
						// ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\"
						// + filename)����
						out.write(buffer, 0, len);
					}
					// �ر�������
					in.close();
					// �ر������
					out.close();
					// ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					// item.delete();
					UploadBean objUploadBean =new UploadBean();
					uploadfile objuploadfile = new uploadfile();
					Date now = new Date(); //��ȡ��ǰʱ��
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");//��ʽ����ǰ��
					
					String dates  = dateFormat.format(now);
					objUploadBean.setFilePath(dates+"/"+saveFilename);
					objUploadBean.setFileType(fileExtName);
					objUploadBean.setName(filename);
					
					objUploadBean.setUpfileDepict(request.getParameter("depict"));
					nowtime objnowtime =new nowtime();
					objUploadBean.setUpfiletime(objnowtime.nowtime());
					objuploadfile.Addfile(objUploadBean);
					objJsonObject.put("message", 1);
					
				}
			}
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			//request.setAttribute("message", "�����ļ��������ֵ������");
			objJsonObject.put("message", 2);
			//request.getRequestDispatcher("../Admin/message.jsp").forward(request, response);
			return;
		} catch (FileUploadBase.SizeLimitExceededException e) {
			objJsonObject.put("message", 3);
			e.printStackTrace();
			//request.setAttribute("message", "�ϴ��ļ����ܵĴ�С�������Ƶ����ֵ������");
			//request.getRequestDispatcher("../Admin/message.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			objJsonObject.put("message", 4);
			//message = "�ļ��ϴ�ʧ�ܣ�";
			e.printStackTrace();
		}
		//request.setAttribute("message", message);
		//request.getRequestDispatcher("../Admin/message.jsp").forward(request, response);
		outp.println(objJsonObject.toString());
		//System.out.println(objJsonObject.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}