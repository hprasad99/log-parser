package clean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class LogData implements Serializable {

    private LocalDateTime localDateTime;
    private LogLevel logLevel;
    private String thread;
    private String correlationId;
    private String logger;
    private String message;
    private String context;

    private String LOG_DATA = "logData";


    public LogData (
            LocalDateTime localDateTime,
            LogLevel logLevel,
            String thread,
            String correlationId,
            String logger,
            String message,
            String context
    ) {
        this.localDateTime = localDateTime;
        this.logLevel = logLevel;
        this.thread = thread;
        this.correlationId = correlationId;
        this.logger = logger;
        this.message = message;
        this.context = context;
    }


    @Override
    public String toString() {
        return "LogData{" +
                "localDateTime=" + localDateTime +
                ", logLevel=" + logLevel +
                ", thread='" + thread + '\'' +
                ", correlationId='" + correlationId + '\'' +
                ", logger='" + logger + '\'' +
                ", message='" + message + '\'' +
                ", context='" + context + '\'' +
                '}';
    }


    @Override
    public int hashCode() {
        return LOG_DATA.hashCode();
    }
}
