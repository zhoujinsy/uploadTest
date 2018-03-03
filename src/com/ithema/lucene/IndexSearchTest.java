package com.ithema.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

public class IndexSearchTest {
	
	@Test
	public void testIndexSearch() throws ParseException, IOException{
		//创建分词器(创建索引和所有时所用的分词器必须一致)
		Analyzer analyzer=new StandardAnalyzer();
		//创建查询对象,第一个参数:默认搜索域, 第二个参数:分词器
		//默认搜索域作用:如果搜索语法中指定域名从指定域中搜索,
		//如果搜索时只写了查询关键字,则从默认搜索域中进行搜索
		QueryParser queryParser=new QueryParser("fileContext", analyzer);
		//查询语法=域名:搜索的关键字
		Query query = queryParser.parse("fileName:web");
		//上面过程完成了查询条件的设置，包括查询语句 对查询语句的分词 和设置默认域
		
		//下面的过程就是利用上面的查询语句进行查询操作
		//指定索引和文档的目录
		Directory directory = FSDirectory.open(new File("H:\\luceneDic"));
		//创建读取对象
		IndexReader indexReader=IndexReader.open(directory);
		//创建索引的搜索对象
		IndexSearcher indexSearcher=new IndexSearcher(indexReader);
		//搜索：第一个参数为查询语句对象, 第二个参数:指定显示多少条,
		TopDocs topdocs = indexSearcher.search(query, 5);
		//一共搜索到多少条记录
		System.out.println("=======count======"+topdocs.totalHits);
		//获取查询结果集
		ScoreDoc[] scoreDocs=topdocs.scoreDocs;
		for(ScoreDoc scoreDoc:scoreDocs){
			//获取docID
			int docId=scoreDoc.doc;
			//通过文档id从硬盘读取对应的文档
			Document document = indexReader.document(docId);
			System.out.println("fileName:"+document.get("fileName"));
			System.out.println("fileSize:"+document.get("fileSize"));
			System.out.println("====================================");
		}
		//输入流 
		//索引库的uri 
		//查询条件fieldname 和 value 
		//分词器 
		//查询工具 
		//查询结果集 
		//结果集解析
	}
}
