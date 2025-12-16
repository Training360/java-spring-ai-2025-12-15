package training.coursesloader;

import org.springframework.ai.document.Document;
import org.springframework.ai.document.DocumentTransformer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysMetadataEnricher implements DocumentTransformer {

    @Override
    public List<Document> apply(List<Document> documents) {
        for (Document document : documents) {
            String days = getDaysFor(document);
            document.getMetadata().put("days", days);
        }
        return documents;
    }

    private String getDaysFor(Document document) {
        try {
            Pattern pattern = Pattern.compile("(\\d+ nap)");
            return document.getText().lines()
                    .filter(line -> line.startsWith("# "))
                    .peek(System.out::println)
                    .map(line -> {
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            String day = matcher.group(1);
                            return day;
                        }
                        else {
                            return "";
                        }
                    })
                    .findFirst().orElseThrow();
        } catch (Exception e) {
            return "";
        }
    }
}
