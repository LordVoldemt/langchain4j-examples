import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.*;

/**
 * 这个示例学习如何把本地文件加载成 LangChain4j 的 Document。
 * 加载只是 RAG 的第一步，后续通常还要切分、向量化并放入 EmbeddingStore。
 */
public class DocumentLoaderExamples {

    private static final Logger log = LoggerFactory.getLogger(DocumentLoaderExamples.class);

    public static void main(String[] args) {
        loadSingleDocument();
        loadMultipleDocuments();
        loadMultipleDocumentsWithGlob();
        loadMultipleDocumentsRecursively();
        loadUsingParserFromSPI();
    }

    private static void loadSingleDocument() {
        Path documentPath = toPath("example-files/story-about-happy-carrot.pdf");
        log.info("Loading single document: {}", documentPath);
        // ApacheTikaDocumentParser 能解析多种文档格式，适合 PDF、Office 等非纯文本文件。
        Document document = loadDocument(documentPath, new ApacheTikaDocumentParser());
        log(document);
        log.info("");
    }

    private static void loadMultipleDocuments() {
        Path directoryPath = toPath("example-files/");
        log.info("Loading multiple documents from: {}", directoryPath);
        List<Document> documents = loadDocuments(directoryPath, new ApacheTikaDocumentParser());
        documents.forEach(DocumentLoaderExamples::log);
        log.info("");
    }

    private static void loadMultipleDocumentsWithGlob() {
        Path directoryPath = toPath("example-files/");
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.txt");
        log.info("Loading *.txt documents from: {}", directoryPath);
        List<Document> documents = loadDocuments(directoryPath, pathMatcher, new ApacheTikaDocumentParser());
        documents.forEach(DocumentLoaderExamples::log);
        log.info("");
    }

    private static void loadMultipleDocumentsRecursively() {
        Path directoryPath = toPath("example-files/");
        log.info("Loading multiple documents recursively from: {}", directoryPath);
        List<Document> documents = loadDocumentsRecursively(directoryPath, new ApacheTikaDocumentParser());
        documents.forEach(DocumentLoaderExamples::log);
        log.info("");
    }

    private static void loadUsingParserFromSPI() {
        Path documentPath = toPath("example-files/story-about-happy-carrot.pdf");
        log.info("Loading using parser imported through SPI: {}", documentPath);
        // 不显式传 parser 时，LangChain4j 会通过 SPI 查找可用解析器，运行环境缺依赖时这里可能失败。
        Document document = loadDocument(documentPath); // we are not specifying a parser here, it is imported through SPI
        log(document);
        log.info("");
    }

    private static void log(Document document) {
        log.info("{}: {} ...", document.metadata().getString("file_name"), document.text().trim().substring(0, 50));
    }

    private static Path toPath(String fileName) {
        try {
            URL fileUrl = DocumentLoaderExamples.class.getResource(fileName);
            return Paths.get(fileUrl.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
