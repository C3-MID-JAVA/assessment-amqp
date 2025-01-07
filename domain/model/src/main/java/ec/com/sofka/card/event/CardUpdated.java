package ec.com.sofka.card.event;

import ec.com.sofka.generic.domain.DomainEvent;

import java.time.LocalDateTime;

public class CardUpdated extends DomainEvent {
    private String cardId;
    private String cardNumber;
    private String cardType;
    private LocalDateTime expirationDate;
    private String cvv;
    private String status;

    public CardUpdated(String cardId ,String eventType, String cardNumber, String cardType, String cvv, LocalDateTime expirationDate, String status) {
        super(eventType);
        this.cardId = cardId;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    public CardUpdated(String eventType) {
        super(eventType);
    }

    public String getCardId() {
        return cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCvv() {
        return cvv;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public String getStatus() {
        return status;
    }
}