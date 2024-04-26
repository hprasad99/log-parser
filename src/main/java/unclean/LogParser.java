package unclean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogParser {
    public static void main(String[] args) {
        String logEntry = "{\"timestamp\":\"2023-06-05 21:10:57.374\",\"level\":\"DEBUG\",\"thread\":\"http-nio-8080-exec-6\",\"mdc\":{\"correlationId\":\"9b1ec2e0-dbe5-4363-befe-95d365fa6732\"},\"logger\":\"com.rrl.vms.starlord.application.config.web.ClientRequestResponseLoggerFilter\",\"message\":\"Total execution time : 148 ms\",\"context\":\"default\"}";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(logEntry);

            String timeStampStr = node.get("timestamp").asText();
            LocalDateTime timeStamp = LocalDateTime.parse(timeStampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));

            String levelStr = node.get("level").asText();
            LogLevel level = LogLevel.valueOf(levelStr.toUpperCase());

            String thread = node.get("thread").asText();
            String correlationId = node.get("mdc").get("correlationId").asText();
            String logger = node.get("logger").asText();
            String message = node.get("message").asText();
            String context = node.get("context").asText();
            System.out.println("Parsed log entry");
            System.out.println("Timestamp: " + timeStamp);
            System.out.println("level: " + level);
            System.out.println("thread: " + thread);
            System.out.println("correlationId: " + correlationId);
            System.out.println("logger: " + logger);
            System.out.println("message: " + message);
            System.out.println("context: " + context);
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing log entry: " + e.getMessage());
        }
    }
}
