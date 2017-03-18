package lt.itakademija.exam;

import java.util.Objects;

/**
 * Represents an account in a banking system.
 * <p></p>
 * Note: this is a very simplified account model (for example it lacks IBAN number).
 */
public final class Account {

    private final Long id;

    private final Customer customer;

    private final Currency currency;

    private Money balance;

    public Account(Long id, Customer customer, Currency currency, Money balance) {
        this.id = id;
        this.customer = customer;
        this.currency = currency;
        this.balance = balance;
    }

    /**
     * Returns a unique account identifier.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns a customer to which this account belongs.
     *
     * @return a customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns account currency.
     *
     * @return currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Returns account balance (money that this account holds).
     *
     * @return balance
     */
    public Money getBalance() {
        return balance;
    }

    /**
     * Sets account balance.
     *
     * @param balance
     */
    public void setBalance(Money balance) {
        this.balance = balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Account other = (Account) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Account [id=" + id + ", currency=" + currency + ", balance=" + balance + "]";
    }

}
