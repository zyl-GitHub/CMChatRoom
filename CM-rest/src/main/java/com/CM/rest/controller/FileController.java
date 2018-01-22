package com.CM.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.CM.common.pojo.EasyUIDataGridResult;
import com.CM.rest.service.FileService;


import com.taotao.common.pojo.TaotaoResult;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController
{
	@Autowired
	private FileService fileService;
	@RequestMapping("/rest/uploadfile")
	public TaotaoResult uploadFile(String originalFileName, String link)
	{
		return fileService.uploadFile(originalFileName, link);
	}
	@RequestMapping("/rest/showFileList")
	@ResponseBody
	public EasyUIDataGridResult showFileList(String name,int page,int rows)
	{
		return fileService.showFileList(name, page, rows);
	}
	@RequestMapping("/rest/addDownloadFileCount")
	public void addDownloadFileCount(Integer id)
	{
		fileService.addDownloadFileCount(id);
	}
}
