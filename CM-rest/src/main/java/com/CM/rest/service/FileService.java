package com.CM.rest.service;

import javax.servlet.http.HttpServletResponse;

import com.CM.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface FileService
{
	public TaotaoResult uploadFile(String originalFileName,String link);
	public EasyUIDataGridResult showFileList(String name, int page, int row);
	public void addDownloadFileCount(Integer id);
}
