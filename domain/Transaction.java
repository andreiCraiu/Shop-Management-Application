package domain;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class Transaction extends Entity<String> {
    private String productId;
    private int quantity;
    private int cardNumber;
    private Date date;
    private Time time;

        public Transaction(String id, String productId, int quantity, Date date, Time time, int cardNumber) {
        super(id);
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
        this.cardNumber = cardNumber;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return getQuantity() == that.getQuantity() &&
                getCardNumber() == that.getCardNumber() &&
                Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getTime(), that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getQuantity(), getCardNumber(), getDate(), getTime());
    }
}
