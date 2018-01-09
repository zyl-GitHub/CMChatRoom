package com.CM.common.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.omg.CORBA.NamedValue;

public class FastDFSClient
{
	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageClient storageClient = null;
	private StorageServer storageServer = null;
	public FastDFSClient(String conf) throws Exception
	{
		if(conf.contains("classpath:"))
		{
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		storageClient = new StorageClient(trackerServer, storageServer);
	}
	/**
	 * 上传文件的方法
	 * @param fileName 文件的全路径
	 * @param extName 文件的扩展名
	 * @param metas 文件的扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String fileName,String extName,NameValuePair[] metas)throws Exception
	{
		String[] strings = storageClient.upload_file(fileName, extName, metas);
		StringBuilder result = new StringBuilder("");
		for(String string:strings)
		{
			result.append("/"+string);
		}
		return result.toString().substring(result.indexOf("/")+1);
	}
	public String uploadFile(String fileName) throws Exception
	{
		return uploadFile(fileName,null,null);
	}
	public String uploadFile(String fileName,String extName) throws Exception
	{
		return uploadFile(fileName,extName,null);
	}
	/**
	 * 上传文件的方法
	 * @param fileContent 文件的内容，字节数组
	 * @param extName 文件的扩展名
	 * @param metas 文件扩展信息
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(byte[] fileContent,String extName,NameValuePair[] metas)throws Exception
	{
		String[] strings = storageClient.upload_file(fileContent, extName, metas);
		StringBuilder result = new StringBuilder("");
		for(String string:strings)
		{
			result.append("/"+string);
		}
		return result.toString().substring(result.indexOf("/")+1);
	}
	public String uploadFile(byte[] fileContent) throws Exception
	{
		return uploadFile(fileContent,null,null);
	}
	public String uploadFile(byte[] fileContent,String extName) throws Exception
	{
		return uploadFile(fileContent,extName,null);
	}
}
