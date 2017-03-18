package lt.itakademija.exam;

import java.util.ArrayList;
import java.util.List;

public final class BankImpl implements Bank {

    private final CurrencyConverter currencyConverter;

    private long customerSequence = 0L;

    private long accountSequence = 0L;

    private long operationSequence = 0L;

    private final List<Customer> customers;

    public BankImpl(CurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
        this.customers = new ArrayList<>();
    }

    @Override
    public Customer createCustomer(PersonCode personCode, PersonName fullName) {
        if (personCode == null || fullName == null) {
            throw new NullPointerException();
        }

        for (Customer customer : customers) {
            if (customer.getPersonCode().equals(personCode)) {
                throw new CustomerCreateException("Customer already exists");
            }
        }

        final Customer customer = new Customer(++customerSequence, personCode, fullName);
        customers.add(customer);
        return customer;
    }

    @Override
    public Account createAccount(Customer customer, Currency currency) {
        if (customer == null || currency == null) {
            throw new NullPointerException();
        }
        if (!customers.contains(customer)) {
            throw new AccountCreateException("Customer does not exist");
        }

        final Account account = new Account(++accountSequence, customer, currency, new Money(0.0d));
        customer.addAccount(account);
        return account;
    }

    @Override
    public Money getBalance(Currency currency) {
        Money balance = new Money(0.0d);

        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getCurrency().equals(currency)) {
                    balance = balance.add(account.getBalance());
                } else {
                    balance = balance.add(currencyConverter.convert(account.getCurrency(), currency, account.getBalance()));
                }
            }
        }

        return balance;
    }

    @Override
    public Operation transferMoney(Account debitAccount, Account creditAccount, Money debitAmount) {
        if (debitAccount.getBalance().isLessThan(debitAmount)) {
            throw new InsufficientFundsException("Insufficient funds");
        }

        debitAccount.setBalance(debitAccount.getBalance().substract(debitAmount));
        final Money creditAmount = currencyConverter.convert(debitAccount.getCurrency(), creditAccount.getCurrency(), debitAmount);
        creditAccount.setBalance(creditAccount.getBalance().add(creditAmount));

        return new Operation(++operationSequence, debitAccount, creditAccount, debitAmount);
    }

}