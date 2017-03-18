package lt.itakademija.exam;

import java.util.Objects;

/**
 * Represents money transfer operation.
 */
public final class Operation {

    private final Long id;

    private final Account debitAccount;

    private final Account creditAccount;

    private final Money debitAmount;

    public Operation(final Long id, final Account debitAccount, final Account creditAccount, final Money debitAmount) {
        this.id = id;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.debitAmount = debitAmount;
    }

    /**
     * Returns a unique id.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns a debit account.
     *
     * @return account
     */
    public Account getDebitAccount() {
        return debitAccount;
    }

    /**
     * Returns a credit account.
     *
     * @return account
     */
    public Account getCreditAccount() {
        return creditAccount;
    }

    /**
     * Returns a debit amount.
     *
     * @return amount
     */
    public Money getDebitAmount() {
        return debitAmount;
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
        Operation other = (Operation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Operation [id=" + id + ", debitAccount=" + debitAccount + ", creditAccount=" + creditAccount + "]";
    }

}
