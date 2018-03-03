package com.ithema.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

public class IndexManagerTest {
	@Test
	public void testCreateIndex() throws IOException{
		//创建文档列表，保存多个Document
		List<Document> docList=new ArrayList<>();
		//指定文件所在目录
		//file能够获取文件的路径信息和文件的名称信息，不能获得文件的内容，但是
		//可以作为路径放入输入流中，通过输入流来获取信息。
		File dir=new File("F:\\ssm框架\\lucene_day01\\参考资料\\searchsource");
		//获取文件名称信息，通过输入流获取文件内容和大小信息
		for(File file:dir.listFiles()){
			//文件名称
			String fileName = file.getName();
			//文件内容 common-io里的工具类
			String fileContext = FileUtils.readFileToString(file);
			//获取单个文件大小
			long fileSize = FileUtils.sizeOf(file);
			
			//信息获取完毕后，需要为每一个文件新建一个文档
			Document doc=new Document();
			
			
			//第一个参数:域名
			//第二个参数:域值
			//第三个参数:是否存储,是为yes,不存储为no
			/*TextField nameFiled = new TextField("fileName", fileName, Store.YES);
			TextField contextFiled = new TextField("fileContext", fileContext, Store.YES);
			TextField sizeFiled = new TextField("fileSize", fileSize.toString(), Store.YES);*/
			
			//是否分词:要,因为它要索引,并且它不是一个整体,分词有意义
			//是否索引:要,因为要通过它来进行搜索
			//是否存储:要,因为要直接在页面上显示
			//创建域对象
			TextField nameFiled=new TextField("fileName", fileName, Store.YES);
			//是否分词: 要,因为要根据内容进行搜索,并且它分词有意义
			//是否索引: 要,因为要根据它进行搜索
			//是否存储: 可以要也可以不要,不存储搜索完内容就提取不出来
			TextField contextFiled = new TextField("fileContext", fileContext, Store.YES);
			//是否分词: 要, 因为数字要对比,搜索文档的时候可以搜大小, lunene内部对数字进行了分词算法
			//是否索引: 要, 因为要根据大小进行搜索
			//是否存储: 要, 因为要显示文档大小
			LongField sizeFiled = new LongField("fileSize", fileSize, Store.YES);
			
			//将域对象添加到document中，想当于定义了文档结构
			doc.add(nameFiled);
			doc.add(contextFiled);
			doc.add(sizeFiled);
			
			//将文档添加到文档list中
			docList.add(doc);
		};
		
		
		//分词器（创建标准分词器）
		Analyzer analyzer=new StandardAnalyzer();
		//索引库的uri 指定索引和文档存储的目录,指定的目录最好是磁盘的根目录下的目录，
		//目录名称中不要有空格
		Directory directory = FSDirectory.open(new File("H:\\luceneDic"));
		//输出流
		//创建写对象的初始化对象（指定版本和分析器）
		IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
		//创建写对象（根据路径信息和初始化信息）
		IndexWriter indexWriter=new IndexWriter(directory, config);
		
		//将文档加入写对象中
		for(Document doc:docList){
			indexWriter.addDocument(doc);
		}
		//写，即提交
		indexWriter.commit();
		//关闭流
		indexWriter.close();
		
	}
}
