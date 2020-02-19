package com.lif.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneApplicationTests {

    /**
     * 分析器
     * 1.创建一个Analyzer对象。StandarAnalyzer对象
     * <p>
     * 2.使用分析器对象的tokenStream方法获得一个TokenStream对象
     * <p>
     * 3.向TokenStream对象中设置一个引用，相当于一个指针。
     * <p>
     * 4.调用TokenStream对象的rest方法，如果不调用会抛异常。
     * <p>
     * 5.使用while循环遍历TokenStream对象
     * <p>
     * 6.关闭TokenStream对象
     */
    @Test
    public void testTokenStream() throws IOException {
//        Analyzer ikAnalyzer = new IKAnalyzer();
//        Analyzer analyzer = new SmartChineseAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        TokenStream tokenStream = analyzer.tokenStream("", "这是身上的意见衣服，和什么");
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            System.out.println(charTermAttribute.toString());
        }
        tokenStream.close();
    }

    /**
     * 创建索引库
     * 1. 创建一个Director对象，指定索引保存的位置
     * 2. 基于Director对象创建一个IndexWriter对象
     * 3. 读取磁盘上的文件，对应每个文件创建一个文档对象
     * 4. 向文档对象中添加域
     * 5. 把文档对象写入索引库
     * 6. 关闭IndexWriter对象
     */
    @Test
    public void CreateIndex() {
//        Directory directory = new RAMDirectory();
        // 把索引库保存到磁盘上
        Directory directory = null;
        try {
            directory = FSDirectory.open(new File("D:\\javadev\\LucenePath").toPath());
            // 创建IndexWriter对象
            IndexWriter indexWriter = null;
            try {
//                IndexWriterConfig config = new IndexWriterConfig(new SmartChineseAnalyzer());
                IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
                indexWriter = new IndexWriter(directory, config);
                File dir = new File("D:\\javadev\\lucenefiles");
                File[] files = dir.listFiles();
                for (File f : files) {
                    String fName = f.getName();
                    String fPath = f.getPath();
                    String fContent = FileUtils.readFileToString(f, "utf-8");
                    long fsize = FileUtils.sizeOf(f);
                    // 创建域,参数：域的名称，域的内容，是否存储
                    Field fieldName = new TextField("name", fName, Field.Store.YES);
                    // NNY 只存储
                    Field fieldPath = new StoredField("path", fPath);
                    Field fieldContent = new TextField("content", fContent, Field.Store.YES);
//                    Field fieldSize = new TextField("size", fsize + "", Field.Store.YES);
                    // YYN，只分析、计算，不存储
                    Field fieldSizeValue = new LongPoint("size", fsize);
                    // 只存储
                    Field fieldSizeStore = new StoredField("size", fsize);
                    // 创建文档对象
                    Document document = new Document();
                    // 向文档对象中添加域
                    document.add(fieldName);
                    document.add(fieldPath);
                    document.add(fieldContent);
                    document.add(fieldSizeValue);
                    document.add(fieldSizeStore);
                    // 把文档对象写入索引库
                    indexWriter.addDocument(document);
                }
                // 关闭IndexWriter对象
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                indexWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试搜索
     * 1.创建一个Director对象，指定索引库位置
     * <p>
     * 2.创建一个IndexReader对象
     * <p>
     * 3.创建一个IndexSearch对象，构造方法中的参数indexReader对象
     * <p>
     * 4.创建一个Query对象，TermQuery
     * <p>
     * 5.执行查询，得到一个TopDocs对象
     * <p>
     * 6.取查询结果的总记录数
     * <p>
     * 7.取文档列表
     * <p>
     * 8.打印文档中的内容
     * <p>
     * 9.关闭IndexReader对象
     */
    @Test
    public void SearchIndex() {
        // 把索引库保存到磁盘上
        Directory directory = null;
        try {
            directory = FSDirectory.open(new File("D:\\javadev\\LucenePath").toPath());
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            Query query = new TermQuery(new Term("content", "js"));
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
                System.out.println(doc1.get("content"));
                System.out.println(doc1.get("size"));
                System.out.println("--------------");
            }
            indexReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
