package course;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    private final Resource javaClassResource = new ClassPathResource("/templates/java-classes.ftl");

    private final SpringTemplateEngine engine;

    private final ChatMemory chatMemory;

    public ChatController(ChatClient.Builder builder, SpringTemplateEngine engine, ChatMemory chatMemory) {
        this.chatClient = builder.build();
        this.engine = engine;
        this.chatMemory = chatMemory;
    }

    @PostMapping("/ask")
    public String ask(@RequestBody String question) {
        return chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system("""
                        Te egy Java oktató vagy, próbálj röviden, egy-két mondatban válaszolni.
                        """)
                .user(question)
                .call()
                .content();
    }

    @PostMapping("/java-versions")
    public List<JavaVersion> javaVersions() {
        return chatClient
                .prompt()
                .user("Milyen Java verziókat ismersz 20 és a felett.")
                .call()
                .entity(new ParameterizedTypeReference<>() {});
    }

    @PostMapping("/java-classes")
    public String javaClasses(@RequestBody JavaClassQuestion question) {
        // https://www.stringtemplate.org/
        return chatClient
                .prompt()
                .user(spec ->
                        spec.text(javaClassResource)
                                .params(Map.of("className", question.className(), "javaVersion", question.javaVersion(),
                                        "user", "John Doe"))
                )
                .call()
                .content();
    }

    @PostMapping("/java-keywords")
    public String keywords(@RequestBody List<String> question) {
        // Thymeleaf
        return chatClient
                .prompt()
                .templateRenderer((template, variables) ->
                        engine.process(template, new Context(Locale.of("hu"),
                                Map.of("keywords", question))))
                .user(spec ->
                        spec.text("java-keywords")
                                .params(Map.of("keywords", question))
                )
                .call()
                .content();
    }

    @PostMapping("memory")
    public MemoryAnswer memory(@RequestBody MemoryQuestion question) {
        final UUID id;
        if (question.id() == null) {
            id = UUID.randomUUID();
        } else {
            id = question.id();
        }

        String answer = chatClient
                .prompt()
                .user(question.question())
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, id))
                .call()
                .content();
        return new MemoryAnswer(id, answer);
    }
}
