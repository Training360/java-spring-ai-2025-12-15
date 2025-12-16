package course;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.moderation.*;
import org.springframework.ai.openai.OpenAiModerationOptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/moderation")
public class ModerationController {

    private final ModerationModel moderationModel;

    @PostMapping
    public List<ModerationResult> moderate(@RequestBody String question) {
        ModerationPrompt prompt = new ModerationPrompt(question);
        ModerationResponse response = moderationModel.call(prompt);
        return response.getResult().getOutput().getResults();
    }
}
