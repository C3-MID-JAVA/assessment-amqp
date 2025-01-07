package ec.com.sofka.card.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

import java.time.LocalDateTime;

public class ExpirationDate implements ValueObject<LocalDateTime> {
    private final LocalDateTime value;

    private ExpirationDate(final LocalDateTime value) {
        this.value = validate(value);
    }

    public static ExpirationDate of(final LocalDateTime value) {
        return new ExpirationDate(value);
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
