package course;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String question) {
        return chatClient
                .prompt()
                .system("""
                        Te egy Java oktató vagy, próbálj röviden, egy-két mondatban válaszolni.
                        """)
                .user(question)
                .call()
                .content();
    }
}
