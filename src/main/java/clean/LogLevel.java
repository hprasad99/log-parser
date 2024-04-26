package clean;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public enum LogLevel implements Serializable {
    DEBUG("debug"),
    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private final String value;
    LogLevel(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return String.valueOf(value);
    }

}
