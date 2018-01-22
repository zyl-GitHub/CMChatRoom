package com.CM.protal.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CM.common.pojo.EasyUIDataGridResult;
import com.CM.common.pojo.PictureResult;
import com.CM.common.utils.FastDFSClient;
import com.CM.protal.service.FileService;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
@Service
public class FileServiceImpl implements FileService
{
	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	@Value("${REST_UPLOADFILE_URL}")
	private String REST_UPLOADFILE_URL;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_SHOWFILELIST_URL}")
	private String REST_SHOWFILELIST_URL;
	@Value("${REST_DOWNLOAD_COUNT}")
	private String REST_DOWNLOAD_COUNT;
	@Override
	public PictureResult uploadFile(MultipartFile uploadFile)
	{
		PictureResult result = new PictureResult();
		//判断图片是否为空
		if(uploadFile.isEmpty())
		{
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		try
		{
			//取图片扩展名
			//获取图片的全路径
			String originalFileName = uploadFile.getOriginalFilename();
			//取得图片的扩展名
			String extName = originalFileName.substring(originalFileName.lastIndexOf('.')+1);
			//实例化FastDFS的客户端对象，构造函数加载了配置文件：内容是图片服务器的ip地址
			FastDFSClient client = new FastDFSClient("classpath:properties/client.conf");
			//得到图片在图片服务器上的ip地址
			String url = client.uploadFile(uploadFile.getBytes(),extName);
			result.setError(0);
			result.setUrl(IMAGE_SERVER_BASE_URL+url);
			String link = result.getUrl();
			Map<String, String> param = new HashMap<String, String>();
			param.put("originalFileName", originalFileName);
			param.put("link", link);
			HttpClientUtil.doGet(REST_BASE_URL+REST_UPLOADFILE_URL, param);
		} catch (Exception e)
		{
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}
	@Override
	public EasyUIDataGridResult showFileList(String name, int page, int rows)
	{
		Map<String, String> param = new HashMap<>();
		param.put("page", page+"");
		param.put("name", name+"");
		param.put("rows", rows+"");
		String json = HttpClientUtil.doGet(REST_BASE_URL+REST_SHOWFILELIST_URL,param);
		return JsonUtils.jsonToPojo(json, EasyUIDataGridResult.class);
	}
	@Override
	public void download(Integer id,String link, String filename,
			HttpServletResponse response)
	{
		try
		{
			filename = new String(filename.getBytes("ISO-8859-1"));
		} catch (UnsupportedEncodingException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		response.setHeader("Content-Type", "application/octet-stream");  
		response.setHeader("content-disposition", "attachment;filename=" + filename);
		URL url;
		try
		{
			url = new URL(link);
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String directoryPath = "d:/downloads";
			String path = "/"+filename;
			File directory = new File("d:/downloads");
			if(!directory.exists())
			{
				directory.mkdirs();
			}
			OutputStream out = response.getOutputStream();
			byte[] b = new byte[1024];
			int length = -1;
			while((length = in.read(b))!= -1)
			{
				out.write(b, 0, length);
			}
			out.close();
			in.close();
			Map<String, String> param = new HashMap<>();
			param.put("id", id+"");
			HttpClientUtil.doGet(REST_BASE_URL+REST_DOWNLOAD_COUNT,param);
		} catch (IOException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}

}
