package course;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import reactor.core.publisher.Flux;

@RestController
@SessionScope
@RequestMapping("/api/stream")
public class ChatStreamController {

    private String question;

    private final ChatClient chatClient;

    public ChatStreamController(ChatClient.Builder builder) {
        chatClient = builder.build();
    }

    @PostMapping
    public void ask(@RequestBody String question) {
        this.question = question;
    }

    @GetMapping(produces = "text/event-stream")
    public Flux<ResponseChunk> ask() {
        return chatClient
                .prompt()
                .system("Te egy Java oktató vagy, válaszolj max. két mondatban!")
                .user(question)
                .stream()
                .content()
                .map(ResponseChunk::intermediate)
                .concatWith(Flux.just(ResponseChunk.close()));
    }

}
