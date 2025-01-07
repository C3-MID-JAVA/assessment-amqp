package ec.com.sofka.card;

import ec.com.sofka.card.value.CardId;
import ec.com.sofka.card.value.object.*;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.Entity;

public class Card extends Entity<CardId> {
    private CardNumber cardNumber;
    private CardType cardType;
    private ExpirationDate expirationDate;
    private CVV cvv;
    private Status status;

    public Card(CardId id, CardNumber cardNumber, CardType cardType, CVV cvv, ExpirationDate expirationDate, Status status) {
        super(id);
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.status = status;
    }

    protected Card(CardId id) {
        super(id);
    }

    public CardNumber getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(CardNumber cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public CVV getCvv() {
        return cvv;
    }

    public void setCvv(CVV cvv) {
        this.cvv = cvv;
    }

    public ExpirationDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(ExpirationDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
