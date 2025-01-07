package ec.com.sofka.transaction.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

import java.time.LocalDateTime;

public class Date implements ValueObject<LocalDateTime> {
    private final LocalDateTime value;

    private Date(final LocalDateTime value) {
        this.value = validate(value);
    }

    public static Date of(final LocalDateTime value) {
        return new Date(value);
    }

    @Override
    public LocalDateTime getValue() {
        return this.value;
    }

    private LocalDateTime validate(final LocalDateTime value){
        //TODO Validaciones
        return value;
    }
}
