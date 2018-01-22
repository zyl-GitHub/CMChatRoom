package com.CM.solr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.CM.solr.service.ImportService;

@Controller
public class importController
{
	@Autowired
	private ImportService importService;
	@RequestMapping("/solr/importAllFile")
	public void importAllFile()
	{
		try
		{
			importService.importAllFile();
		} catch (Exception e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
