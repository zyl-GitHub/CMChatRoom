package com.CM.solr.service.Impl;

import java.util.List;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.CM.mapper.FileMapper;
import com.CM.pojo.File;
import com.CM.pojo.FileExample;
import com.CM.solr.service.ImportService;
@Service
public class ImportServiceImpl implements ImportService
{
	@Autowired
	private SolrServer solrServer;
	@Autowired
	private FileMapper fileMapper;
	@Override
	public void importAllFile() throws Exception
	{
		FileExample example = new FileExample();
		List<File> list = fileMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			for(File file : list)
			{
				SolrInputDocument document = new SolrInputDocument();
				document.addField("id", file.getId());
				document.addField("item_name", file.getName());
				document.addField("item_link", file.getLink());
				document.addField("item_count", file.getCount());
				solrServer.add(document);
			}
			solrServer.commit();
		}
	}

}
