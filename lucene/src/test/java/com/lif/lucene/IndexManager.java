package com.lif.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

/**
 * 索引库管理
 *
 * @Author: lifan
 * @Date: 2020/2/5 14:05
 */
//@SpringBootTest
public class IndexManager {

    private IndexWriter indexWriter;

    @Before
    public void init() throws IOException {
        indexWriter = indexWriter =
                new IndexWriter(FSDirectory.open(
                        new File("D:\\javadev\\LucenePath").toPath()),
                        new IndexWriterConfig(new IKAnalyzer()));
    }

    /**
     * 1.创建一个IndexWriter对象，需要使用ikanalyzer作为分析器。
     * <p>
     * 2.创建一个Document对象。
     * <p>
     * 3.向Doucment对象中添加域。
     * <p>
     * 4.把文档写入索引库。
     * <p>
     * 5.关闭索引库。
     *
     * @throws IOException
     */
    @Test
    public void addDocument() throws IOException {
//        IndexWriter indexWriter =
//                new IndexWriter(FSDirectory.open(
//                        new File("D:\\javadev\\LucenePath").toPath()),
//                        new IndexWriterConfig(new IKAnalyzer()));
        Document document = new Document();
        document.add(new TextField("name", "新添加的文件", Field.Store.YES));
        document.add(new TextField("content", "新添加的文件的内容", Field.Store.NO));
        document.add(new StoredField("path", "D:/hello"));
        indexWriter.addDocument(document);
        indexWriter.close();
    }

    @Test
    public void deleteAllDocument() throws IOException {
//        indexWriter = indexWriter =
//                new IndexWriter(FSDirectory.open(
//                        new File("D:\\javadev\\LucenePath").toPath()),
//                        new IndexWriterConfig(new IKAnalyzer()));
        // 删除所有文档
        indexWriter.deleteAll();
        // 关闭索引库
        indexWriter.close();
    }

    @Test
    public void deleteDecoumentByQuery() throws IOException {
        indexWriter.deleteDocuments(new Term("content", "方法"));
        indexWriter.close();
    }

    @Test
    public void updateDocumnet() throws IOException {
        Document document = new Document();
        document.add(new TextField("name1", "更新后的文档1", Field.Store.YES));
        document.add(new TextField("name2", "更新后的文档2", Field.Store.YES));
        document.add(new TextField("name3", "更新后的文档3", Field.Store.YES));
        indexWriter.updateDocument(new Term("content", "js"), document);
        indexWriter.close();
    }
}
