package com.CM.protal.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;








import com.CM.common.pojo.EasyUIDataGridResult;
import com.CM.common.pojo.PictureResult;
import com.CM.protal.service.FileService;
import com.taotao.common.pojo.TaotaoResult;

@Controller
public class FileController
{
	@Autowired
	private FileService fileService;
	@RequestMapping("/chat/upload")
	public PictureResult uploadFile(MultipartFile uploadFile)
	{

		PictureResult result = fileService.uploadFile(uploadFile);
		return result;
	}
	@RequestMapping("/file/showlistPage")
	public String showFileListPage()
	{
		return "upload";
	}
	@RequestMapping("/file/queryfile")
	@ResponseBody
	public EasyUIDataGridResult showFileList(String name,Integer page,Integer rows)
	{
		return fileService.showFileList(name, page, rows);
	}
	@RequestMapping("/file/download")
	public void download(Integer id,String link,String filename,HttpServletResponse response)
	{
		fileService.download(id,link, filename, response);
	}
}
