package lt.itakademija.exam;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.MathContext.DECIMAL64;

public final class Money {

    private static final int GREATER = 1;

    private static final int LESS = -1;

    private static final int EQUAL = 0;

    private final BigDecimal amount;

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(double amount) {
        this(BigDecimal.valueOf(amount));
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount, DECIMAL64));
    }

    public Money substract(Money money) {
        return new Money(amount.subtract(money.amount, DECIMAL64));
    }

    public Money multiply(Money value) {
        return new Money(amount.multiply(value.amount, DECIMAL64));
    }

    public Money multiply(double value) {
        return multiply(new Money(value));
    }

    public Money divide(double value) {
        return divide(new Money(value));
    }

    public Money divide(Money value) {
        return new Money(amount.divide(value.amount, DECIMAL64));
    }

    public boolean isGreaterThan(Money money) {
        return amount.compareTo(money.amount) == GREATER;
    }

    public boolean isGreaterThanOrEqual(Money money) {
        return isGreaterThan(money) || equalTo(money);
    }

    public boolean isLessThan(Money money) {
        return amount.compareTo(money.amount) == LESS;
    }

    public boolean isLessThanOrEqual(Money money) {
        return isLessThan(money) || equalTo(money);
    }

    public boolean equalTo(Money money) {
        return amount.compareTo(money.amount) == EQUAL;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (amount.compareTo(other.amount) != 0)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Money [amount=" + amount + "]";
    }

}
