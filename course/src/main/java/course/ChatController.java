package course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@Slf4j
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
        ChatResponse response = chatClient
                .prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system("""
                        Te egy Java oktató vagy, próbálj röviden, egy-két mondatban válaszolni.
                        """)
                .user(question)
                .call()
                .chatResponse();

        Usage usage = response.getMetadata().getUsage();
        log.info("Usage: {}", usage);
        return response.getResult().getOutput().getText();
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

    @PostMapping("/images")
    public String images(@RequestParam String question, @RequestParam MultipartFile image) {
        return chatClient
                .prompt()
                .user(spec -> {
                    spec
                            .text(question)
                            .media(MimeTypeUtils.IMAGE_PNG, new InputStreamResource(image));
                })
                .call()
                .content();
    }

    @PostMapping("/pronounce")
    public ResponseEntity<Resource> pronounce(@RequestBody String question) {

        ChatOptions options = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O_MINI_AUDIO_PREVIEW)
                .outputModalities(List.of("text", "audio"))
                .outputAudio(new OpenAiApi.ChatCompletionRequest.AudioParameters(OpenAiApi.ChatCompletionRequest.AudioParameters.Voice.ALLOY, OpenAiApi.ChatCompletionRequest.AudioParameters.AudioResponseFormat.WAV))
                .build();

        byte[] data = chatClient
                .prompt()
                .user(question)
                .options(options)
                .call()
                .chatClientResponse().chatResponse().getResult()
                .getOutput().getMedia().getFirst().getDataAsByteArray();

        Resource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"speech.wav\"")
                .contentType(new MediaType("audio", "mpeg"))
                .contentLength(data.length)
                .body(resource);

    }
}
