package course;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.filter.Filter;
import org.springframework.ai.vectorstore.filter.FilterExpressionBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ask-courses")
@Slf4j
public class RagController {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    public RagController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public String ask(@RequestBody String question) {
//        Filter.Expression filter = new FilterExpressionBuilder().eq("day", "3 nap").build();

        ChatClientResponse response = chatClient
                .prompt()
                .advisors(QuestionAnswerAdvisor.builder(vectorStore)
//                        .searchRequest(
//                        SearchRequest.builder().topK(5).filterExpression("days == '3 nap'").build()
//                        SearchRequest.builder().topK(5).filterExpression(filter).build()
//                )
                .build())
                .system("""
                        Te a Training360 chatbotja vagy, aki képzésekkel kapcsolatos
                        kérdésekre válaszol.
                        
                        Csatolom a kérdéssel kapcsolatos képzéseket, csak ezek közül válassz.
                        Ha nem találsz a kérdéssel kapcsolatos képzést, akkor ne találgass!
                        
                        A képzésről a címét írd le és egy mondatot a tematikáról!
                        """)
                .user(question)
                .call()
                .chatClientResponse();

        List<Document> document = response.chatResponse().getMetadata().get(QuestionAnswerAdvisor.RETRIEVED_DOCUMENTS);
        log.info("Retrieved documents: {}", document);
        return response.chatResponse().getResult().getOutput().getText();
    }
}
