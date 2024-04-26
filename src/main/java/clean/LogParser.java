package clean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class LogParser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
//        File file = new File("resources/stream.log");
        String logPath = "/Users/himanshu.prasad/IdeaProjects/safe-parser/src/main/resources/stream.log";
        try (BufferedReader reader = new BufferedReader(new FileReader(logPath))) {
            ObjectMapper mapper = new ObjectMapper();

            String logEntry;
            while(!Objects.isNull((logEntry =  reader.readLine()))) {
                LogData logData = parseLogEntry(logEntry, mapper);
                if(!Objects.isNull(logData)) {
                    System.out.println("Parsed log entry");
                    System.out.println(logData);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LogData parseLogEntry(String logEntry, ObjectMapper mapper) throws IOException {
        try {
            JsonNode node = mapper.readTree(logEntry);
            LocalDateTime timestamp = LocalDateTime.parse(node.get("timestamp").asText(), DATE_TIME_FORMATTER);
            LogLevel level = LogLevel.valueOf(node.get("level").asText().toUpperCase());
            String thread = node.get("thread").asText();
            String correlationId = node.get("mdc").get("correlationId").asText();
            String logger  = node.get("logger").asText();
            String message = node.get("message").asText();
            String context = node.get("context").asText();
            return new LogData(timestamp, level, thread, correlationId, logger, message, context);
        } catch (IOException ex) {
            throw new IOException("Error parsing log entry: " + ex.getMessage());
        }
    }
}
