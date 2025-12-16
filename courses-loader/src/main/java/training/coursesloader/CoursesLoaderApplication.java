package training.coursesloader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class CoursesLoaderApplication implements CommandLineRunner {

    private final VectorStore vectorStore;

    public static void main(String[] args) {
        SpringApplication.run(CoursesLoaderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Document> documents = Files.list(Path.of("input_full"))
                .peek(path -> log.info("Loading {} file", path))
                .flatMap(path -> {
                    DocumentReader reader = new TextReader(new FileSystemResource(path));
                    return reader.read().stream();
                }).toList();

        vectorStore.write(documents);
        log.info("Documents written to vector store: {}", documents.size());
    }
}
