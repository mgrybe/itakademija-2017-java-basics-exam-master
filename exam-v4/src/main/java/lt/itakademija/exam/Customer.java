package lt.itakademija.exam;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a customer in a banking system.
 */
public final class Customer {

    private final Long id;

    private final PersonCode personCode;

    private final PersonName personName;

    private final Set<Account> accounts;

    public Customer(final Long id, final PersonCode personCode, final PersonName fullName) {
        this.id = id;
        this.personCode = personCode;
        this.personName = fullName;
        this.accounts = new HashSet<>();
    }

    /**
     * Returns a unique customer identifier.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns a person code.
     *
     * @return person code
     */
    public PersonCode getPersonCode() {
        return personCode;
    }

    /**
     * Returns a person name.
     *
     * @return person name
     */
    public PersonName getPersonName() {
        return personName;
    }

    /**
     * Registers an account with this particular customer.
     *
     * @param account account
     */
    public void addAccount(Account account) {
        accounts.add(account);
    }

    /**
     * Returns an unmodifiable list of all customers' accounts.
     *
     * @return accounts
     */
    public Set<Account> getAccounts() {
        return Collections.unmodifiableSet(accounts);
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
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", personCode=" + personCode + ", personName=" + personName + ", accounts=" + accounts
                + "]";
    }

}
