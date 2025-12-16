package course;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/audio")
public class AudioController {

    private final TextToSpeechModel textToSpeechModel;

    @PostMapping
    public ResponseEntity<Resource> audio(@RequestBody String question) {
        byte[] data = textToSpeechModel.call(question);
        Resource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=speech-%s.mp3"
                        .formatted(LocalDate.now().toString()))
                .contentType(new MediaType("audio", "mpeg"))
                .contentLength(data.length)
                .body(resource);
    }
}
