package course;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.image.*;
import org.springframework.ai.openai.OpenAiImageOptions;
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
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/generate-image")
public class ImageController {

    private final ImageModel imageModel;

    @PostMapping
    public ResponseEntity<Resource> image(@RequestBody String question) {
        ImageOptions imageOptions = OpenAiImageOptions
                .builder()
                .quality("hd")
                .responseFormat("b64_json")
                .height(1024)
                .width(1024)
                .build();

        ImagePrompt prompt = new ImagePrompt(question, imageOptions);
        ImageResponse response = imageModel
                .call(prompt);

        String base64 = response.getResult().getOutput().getB64Json();

        byte[] data = Base64.getDecoder().decode(base64);

        Resource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=image-%s.png"
                        .formatted(LocalDate.now().toString()))
                .contentType(MediaType.IMAGE_PNG)
                .contentLength(data.length)
                .body(resource);
    }
}
