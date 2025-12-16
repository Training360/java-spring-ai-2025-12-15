package course;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transcription")
public class TranscriptController {

    private final TranscriptionModel transcriptionModel;

    @PostMapping
    public String transcript(@RequestParam MultipartFile audio) throws IOException {
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(new InputStreamResource(audio.getInputStream())
        {
            @Override
            public String getFilename() {
                return "foo.mp3";
            }
        });
        AudioTranscriptionResponse response = transcriptionModel.call(prompt);
        return response.getResult().getOutput();
    }
}
