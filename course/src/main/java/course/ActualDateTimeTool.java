package course;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ActualDateTimeTool {

    @Tool(name = "now", description = "Visszaadja az aktuális dátumot és időt.")
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
