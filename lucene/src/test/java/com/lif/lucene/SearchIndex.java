package com.lif.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * @Author: lifan
 * @Date: 2020/2/6 11:03
 */
public class SearchIndex {

    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    @Before
    public void init() throws IOException {
        indexReader = DirectoryReader.open(FSDirectory.open(new File("D:\\javadev\\LucenePath").toPath()));
        indexSearcher = new IndexSearcher(indexReader);
    }

    @Test
    public void testRangeQuery() throws IOException {
        // 创建一个Query对象
        Query query = LongPoint.newRangeQuery("size", 0l, 1000l);
        // 执行查询
        printResult(query);
    }

    @Test
    public void testQueryPaser() throws Exception {
        // 创建一个QueryPaser对象
        //参数1：默认搜索域，参数2：分析器对象
        QueryParser queryParser = new QueryParser("content", new IKAnalyzer());
        Query query = queryParser.parse("js是什么");
        printResult(query);
    }

    private void printResult(Query query) throws IOException {
        TopDocs search = indexSearcher.search(query, 10);
        System.out.println("查询总记录数： " + search.totalHits);
        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc doc : scoreDocs) {
            // 取文档id
            int id = doc.doc;
            // 根据文档id取文档对象
            Document doc1 = indexSearcher.doc(id);
            System.out.println(doc1.get("name"));
            System.out.println(doc1.get("path"));
//            System.out.println(doc1.get("content"));
            System.out.println(doc1.get("size"));
            System.out.println("--------------");
        }
        indexReader.close();
    }
}
