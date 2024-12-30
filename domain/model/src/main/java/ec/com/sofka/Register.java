package ec.com.sofka;

public class Register {

    private String id;
    private String message;

    public Register(String message) {
        this.message = message;
    }

    public Register(String id, String message) {
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
