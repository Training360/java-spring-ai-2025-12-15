package course;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/tools")
public class ToolsController {

    private final ChatClient chatClient;

    private final ActualDateTimeTool actualDateTimeTool;

    private final EnrollCourseTool enrollCourseTool;

    public ToolsController(ChatClient.Builder builder, ActualDateTimeTool actualDateTimeTool, EnrollCourseTool enrollCourseTool) {
        this.chatClient = builder.build();
        this.actualDateTimeTool = actualDateTimeTool;
        this.enrollCourseTool = enrollCourseTool;
    }

    @PostMapping
    public String chat(@RequestBody String question) {
        return chatClient
                .prompt()
                .system("""
                        Ha a kérdező egy tanfolyamra jelentkezik, akkor meg kell adni annak a kódját és a dátumot.
                        Amennyiben az egyik hiányzik, kérdezz rá!
                        """)
                .user(question)
                .tools(actualDateTimeTool, enrollCourseTool)
                .call()
                .content();
    }
}
