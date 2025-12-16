package training.enrollment;

import lombok.extern.slf4j.Slf4j;
import org.springaicommunity.mcp.annotation.McpTool;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
public class EnrollmentTool {

    @McpTool(name = "enroll", description = "Jelentkezés az adott kurzusra az adott dátummal")
    public void enroll(String courseCode, LocalDate enrollmentDate) {
        log.info("Enrolled on course {} at {}", courseCode, enrollmentDate);
    }
}
