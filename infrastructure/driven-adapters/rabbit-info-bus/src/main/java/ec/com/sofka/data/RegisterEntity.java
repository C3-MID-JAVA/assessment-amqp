package ec.com.sofka.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="register")
public class RegisterEntity {
    @Id
    private String id;
    private String message;

    public RegisterEntity() {
    }

    public RegisterEntity(String message) {
        this.message = message;
    }

    public RegisterEntity(String id, String message) {
        this.id = id;
        this.message = message;
    }

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
}
