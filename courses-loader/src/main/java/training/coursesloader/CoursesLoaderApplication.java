package training.coursesloader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.model.transformer.KeywordMetadataEnricher;
import org.springframework.ai.model.transformer.SummaryMetadataEnricher;
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

    private final ChatModel chatModel;

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
                })

//                .limit(5)

                .toList();

//        SummaryMetadataEnricher summaryEnricher = new SummaryMetadataEnricher(chatModel,
//                List.of(SummaryMetadataEnricher.SummaryType.CURRENT),
//                """
//                        {context_str}
//
//                        Ez a dokumentum egy képzés tematikáját tartalmazza,
//                        foglald össze két mondatban, miről szól a képzés!
//                        """,
//                MetadataMode.EMBED
//                );
//
//        documents = summaryEnricher.apply(documents);
//
//        KeywordMetadataEnricher keywordEnricher = KeywordMetadataEnricher.builder(chatModel)
//                .keywordsTemplate(new PromptTemplate("""
//                       {context_str}
//
//                       Ez a dokumentum egy képzés tematikáját tartalmazza.
//                       Válaszd ki az alábbi kulcsszavak közül, hogy melyik illik rá
//                       a legjobban, és csak a kulcsszavakat add vissza vesszővel elválasztva!
//                        Kulcsszavak: java-se, spring-boot, testing, jpa
//                        """))
//                .build();
//
//        documents = keywordEnricher.apply(documents);

        DaysMetadataEnricher daysEnricher = new DaysMetadataEnricher();

        documents = daysEnricher.apply(documents);

        vectorStore.write(documents);
        log.info("Documents written to vector store: {}", documents.size());
    }
}
