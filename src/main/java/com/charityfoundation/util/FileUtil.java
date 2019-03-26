package com.charityfoundation.util;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import jodd.util.StringUtil;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FileUtil {

	/**
	 * 获取操作系统的文件分隔符
	 */
	private static String separator = System.getProperty("file.separator");

	public static void main(String[] args) {
		String dirName = "d:/FH/topic/";// 创建目录
		FileUtil.createDir(dirName);
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建单个目录
		if (dir.mkdirs()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错");
			e.printStackTrace();

		}

	}

	/**
	 * 读取到字节数组0
	 * 
	 * @param filePath //路径
	 * @throws IOException
	 */
	public static byte[] getContent(String filePath) throws IOException {
		File file = new File(filePath);
		long fileSize = file.length();
		if (fileSize > Integer.MAX_VALUE) {
			System.out.println("file too big...");
			return null;
		}
		FileInputStream fi = new FileInputStream(file);
		byte[] buffer = new byte[(int) fileSize];
		int offset = 0;
		int numRead = 0;
		while (offset < buffer.length
				&& (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
			offset += numRead;
		}
		// 确保所有数据均被读取
		if (offset != buffer.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}
		fi.close();
		return buffer;
	}

	/**
	 * 读取到字节数组1
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}

	/**
	 * 读取到字节数组2
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray2(String filePath) throws IOException {

		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException(filePath);
		}

		FileChannel channel = null;
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(f);
			channel = fs.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			while ((channel.read(byteBuffer)) > 0) {
				// do nothing
				// System.out.println("reading");
			}
			return byteBuffer.array();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				channel.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] toByteArray3(String filePath) throws IOException {

		FileChannel fc = null;
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(filePath, "r");
			fc = rf.getChannel();
			MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,
					fc.size()).load();
			//System.out.println(byteBuffer.isLoaded());
			byte[] result = new byte[(int) fc.size()];
			if (byteBuffer.remaining() > 0) {
				// System.out.println("remain");
				byteBuffer.get(result, 0, byteBuffer.remaining());
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				rf.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
    /**
     * 生成 图片并返回图片名称
     * @param folder 图片目录文件夹
     * @param base64Str 生成图片的编码
     * @param photoName 图片名称
     * @param suffix 图片后缀
     * @return 图片名称+后缀
     * @throws Exception
     */
	public static String filePath(String projPath,String folder,String base64Str,String photoName,String suffix) throws Exception{
  		//获取文件夹物理路径
  		String imageFolder = getParentFolderPath(projPath,folder);
  		
  		String imgFilePath=imageFolder+"/"+photoName+suffix;
  		
  		//BASE64Decoder decoder=new BASE64Decoder();
  		
  		//Base64编码
  		byte[] b=Base64.decode(base64Str.replace(" ", "+"));
  		for(int i=0,l=b.length;i<l;i++){
  			if(b[i]<0){
  				//调整异常数据
  				b[i]+=256;
  			}
  		}
  		OutputStream out=new FileOutputStream(imgFilePath);
  		out.write(b);
  		out.flush();
  		out.close();
  		//生成图片成功，返回图片名称"*.jpg"格式
  		return photoName+suffix;//folder+"/"+photoName+suffix;
  	}
	
/**
 * 
 * @Methods Name filePath2
 * @Create In 2018年7月25日 By Administrator
 * @param path 路径
 * @param folder 文件夹
 * @param base64Str base64编码
 * @param photoName 图片名称
 * @param suffix 后缀
 * @return
 * @throws Exception String
 */
	public static String filePath2(String path,String folder,String base64Str,String photoName,String suffix) throws Exception{
		//获取文件夹物理路径
	    String basePath = path+folder;
	    File dirPath = new File(basePath);
	    if (!dirPath.exists()) {
	      dirPath.mkdirs();
	    }
	    String imgFilePath=basePath +"/"+photoName+suffix;
	    
  		//Base64编码
  		if(!StringUtil.isEmpty(base64Str)){
  		  base64Str = base64Str.replace(" ", "+");
  		}
  		byte[] b=Base64.decode(base64Str);
  		for(int i=0,l=b.length;i<l;i++){
  			if(b[i]<0){
  				//调整异常数据
  				b[i]+=256;
  			}
  		}
  		OutputStream out=new FileOutputStream(imgFilePath);
  		out.write(b);
  		out.flush();
  		out.close();
  		return imgFilePath;
	}
	
	/**
	 * App
	 * @param folder
	 * @param base64Str
	 * @param photoName
	 * @param suffix
	 * @return
	 * @throws Exception
	 */
	public static String fileAppPath2(String projPath,String folder,String base64Str,String photoName,String suffix) throws Exception{
		//获取文件夹物理路径
  		String imageFolder = getParentFolderPath2(projPath,folder);
  		
  		String imgFilePath=imageFolder+"/"+photoName+suffix;
  		
  		
  		//Base64编码
  		byte[] b=Base64.decode(base64Str);
  		for (int i = 0; i < b.length; ++i) {
			 if (b[i] < 0) {
				 b[i] += 256;
			 }
		 }
  		OutputStream out=new FileOutputStream(imgFilePath);
  		out.write(b);
  		out.flush();
  		out.close();
  		//生成图片成功，返回图片名称"*.jpg"格式
  		return photoName+suffix;//folder+"/"+photoName+suffix;
	}
	
  	/**
  	 * 获取文件夹物理路径
  	 * @param folder
  	 * @return
  	 */
	public static String getParentFolderPath(String projPath,String folder) {
//		String result = FileUtil.class.getResource("/").getPath();
//  		Integer index=result.indexOf(SysConstants.getProjectName());//
//  		index+=SysConstants.getProjectName().length()+1;
  		String imageFolder=projPath+folder;
  		//判断文件夹是否存在
  		File uploadDir=new File(imageFolder);
  		if(!uploadDir.isDirectory()){
  			uploadDir.mkdirs();
  		}
		return imageFolder;
	}
	
  	/**
  	 * 获取文件夹的物理路径2
  	 * @param folder
  	 * @return
  	 */
	public static String getParentFolderPath2(String projPath,String folder) {
		String result = FileUtil.class.getResource("/").getPath();
		try {//编码，去除意外情况(如 空格)
			result=URLDecoder.decode(result,"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
  		Integer index=result.lastIndexOf(projPath);//
  		String imageFolder=result.substring(0, index)+folder;//linux环境下
  		//String imageFolder=result.substring(1, index)+folder; Windows环境下
  		//判断文件夹是否存在
  		File uploadDir=new File(imageFolder);
  		if(!uploadDir.isDirectory()){
  			uploadDir.mkdirs();
  		}
		return imageFolder;
	}
	
	/**
	 * 判断目录下文件是否存在
	 * @param path
	 * @return
	 */
	public static Boolean isFileExists(String path)
	{
		File file=new File(path);    
		if(!file.exists())    
		{   
			return false;    
		}  
		return true;
	}


	/**
	 * 设置汽车公告文件的相对路径
	 * @return
	 */
	public static String getCarNoticeImagePath(Integer batch,String carModel) {
		String carNoticeImagePath = "/carnotice/"+ batch +"/"+carModel+"/";
		return carNoticeImagePath;
	}


	/**
	 * 创建目标路径所涉及到的目录
	 * @param imgBasePath  图片存储基本路径
	 * @param relativePath  相对路径
	 */
	public static void makeDirPath(String imgBasePath,String relativePath) {
		String realFileParentPath = imgBasePath + relativePath;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 将链接url图片下载到本地，并返回下载图片的路径
	 * @param url 网络图片的路径
	 * @param relativePath 存储图片的相对路径
	 * @param imgBasePath 存储图片的基本路径
	 * @return
	 */
	public static String downloadPicture(String url, String imgBasePath, String relativePath) {
		URL u;
		String imageName;
		HttpURLConnection urlConnection=null;
		try {
			u = new URL(url);
			urlConnection = (HttpURLConnection) u.openConnection();
			//设置请求头，防止报403错误。
			urlConnection.setRequestProperty("user-agent"," Mozilla/5.0 (iPhone; CPU iPhone OS 10_2_1 like Mac OS X) AppleWebKit/602.4.6 (KHTML, like Gecko) Version/10.0 Mobile/14D27 Safari/602.1");
			//创建目标路径所涉及到的目录
			FileUtil.makeDirPath(imgBasePath, relativePath);
			//获取文件后缀名
			String suffix = url.substring(url.lastIndexOf("."));
			//生成图片的文件名uuid+当前时间避免文件名重复
			imageName = relativePath+ UUID.randomUUID().toString().replaceAll("-","")+suffix;
			File dirPath = new File(imgBasePath+imageName);
			if (urlConnection.getResponseCode()!=200){
				return "";
			}

			// 1K的数据缓冲
			InputStream is = urlConnection.getInputStream();

			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			OutputStream os = new FileOutputStream(dirPath);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return imageName;
	}

	/**
	 * 将图片下载到本地，并返回
	 * @param htmlElements 网页节点
	 * @param imgBasePath 存储图片的
	 * @return
	 */
	public static List<String> getImages(List<HtmlElement> htmlElements, String imgBasePath, String relativePath){
		List<String> picture = new ArrayList<>();
		if (htmlElements.isEmpty()){
			return picture;
		}
		for (HtmlElement d : htmlElements) {
			if (!StringUtils.isEmpty(d)){
				String pic = d.getElementsByTagName("img").get(0).getAttribute("src");
				if (!StringUtils.isEmpty(pic)){
					//下载图片
					String path = FileUtil.downloadPicture("http://data.miit.gov.cn" + pic,imgBasePath,relativePath);
					if (!StringUtils.isEmpty(path)){
						picture.add(path);
					}
				}
			}
		}
		return picture;
	}




}