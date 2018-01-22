package com.CM.protal.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.CM.common.pojo.EasyUIDataGridResult;
import com.CM.common.pojo.PictureResult;
import com.taotao.common.pojo.TaotaoResult;

public interface FileService
{
	public PictureResult uploadFile(MultipartFile uploadFile);
	public EasyUIDataGridResult showFileList(String name,int page,int rows);
	public void download(Integer id, String link,String filename,HttpServletResponse response);
}	
