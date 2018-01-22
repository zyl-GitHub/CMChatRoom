package com.CM.rest.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.CircularRedirectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CM.common.pojo.EasyUIDataGridResult;
import com.CM.mapper.FileMapper;
import com.CM.pojo.File;
import com.CM.pojo.FileExample;
import com.CM.rest.service.FileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResult;
@Service
public class FileServiceImpl implements FileService
{
	@Autowired
	private FileMapper fileMapper;
	@Override
	public TaotaoResult uploadFile(String originalFileName, String link)
	{
		File file = new File();
		Date date = new Date();
		try
		{
			file.setName(new String(originalFileName.getBytes("ISO-8859-1")));
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		file.setLink(link);
		file.setCount(0);
		file.setCreated(date);
		file.setFiletypeId(0);
		fileMapper.insert(file);
		return TaotaoResult.ok();
	}
	@Override
	public EasyUIDataGridResult showFileList(String name, int page, int row)
	{
		PageHelper.startPage(page, row);
		//执行查询
		FileExample example = new FileExample();
		FileExample.Criteria criteria = example.createCriteria();
		//criteria.andNameEqualTo(name);
		List<File> list = fileMapper.selectByExample(example);
		//取分页信息
		PageInfo<File> pageInfo = new PageInfo<>(list);
		//返回处理结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	@Override
	public void addDownloadFileCount(Integer id)
	{
		FileExample example = new FileExample();
		FileExample.Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<File> list = fileMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			File file = list.get(0);
			file.setCount(file.getCount()+1);
			fileMapper.updateByExample(file, example);
		}
	}


}
