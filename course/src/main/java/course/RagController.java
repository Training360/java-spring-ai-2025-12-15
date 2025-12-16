package course;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ask-courses")
public class RagController {

    private final ChatClient chatClient;

    private final VectorStore vectorStore;

    public RagController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public String ask(@RequestBody String question) {
        return chatClient
                .prompt()
                .advisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .system("""
                        Te a Training360 chatbotja vagy, aki képzésekkel kapcsolatos
                        kérdésekre válaszol.
                        
                        Csatolom a kérdéssel kapcsolatos képzéseket, csak ezek közül válassz.
                        Ha nem találsz a kérdéssel kapcsolatos képzést, akkor ne találgass!
                        
                        A képzésről a címét írd le, azt, hogy hány napos, és egy mondatot a tematikáról!
                        """)
                .user(question)
                .call()
                .content();
    }
}
