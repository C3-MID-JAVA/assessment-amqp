package ec.com.sofka;

import java.time.LocalDateTime;

public class Log {
    private String id;
    private String message;
    private LocalDateTime timestamp;

    // Constructor
    public Log(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


}
