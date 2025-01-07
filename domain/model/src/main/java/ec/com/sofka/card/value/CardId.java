package ec.com.sofka.card.value;

import ec.com.sofka.generic.util.Identity;

public class CardId extends Identity {
    public CardId() {
        super();
    }

    private CardId(final String id) {
        super(id);
    }

    public static CardId of(final String id) {
        return new CardId(id);
    }
}