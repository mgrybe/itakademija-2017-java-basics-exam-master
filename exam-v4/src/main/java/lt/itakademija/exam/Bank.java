package lt.itakademija.exam;

public interface Bank {

    /**
     * Creates a customer and stores it internally. After this methods completes successfully, a customer
     * does belong to this bank.
     * <p></p>
     * Every customer is given a unique identifier (E.g. 1, 2, 3...).
     *
     * @param personCode person code
     * @param personName person full name
     * @return a customer
     * @throws CustomerCreateException if attempting to create a customer with a
     *                                 person code for which such customer already exist
     * @throws NullPointerException    if method invoked with null values
     * @see Customer#getId()
     */
    Customer createCustomer(PersonCode personCode, PersonName personName);

    /**
     * Creates a customer account.
     * <p></p>
     * Every account is given a unique identifier (E.g. 1, 2, 3...).
     *
     * @param customer customer for which account will be created
     * @param currency account currency
     * @return an account
     * @throws AccountCreateException if attempting to create an account for a customer which does not
     *                                belong to this bank.
     * @throws NullPointerException   if method invoked with null values
     */
    Account createAccount(Customer customer, Currency currency);

    /**
     * Creates and executes money transfer operation.
     * <p></p>
     * After invoking this method, money from debitAccount will be
     * transferred to creditAccount. If debit and credit account currencies
     * differ, then currency conversion will be performed from debit currency to
     * credit currency.
     * <p></p>
     * The returned operation object contains debit and credit accounts, debit amount and
     * a unique identifier (E.g. 1, 2, 3...).
     * <p></p>
     * Example:
     * Transferring 1 EUR to friends USD account, your friend will receive 1 * EUR/USD rate.
     *
     * @param debitAccount  debit account (money goes from this account)
     * @param creditAccount credit account (money goes to this account)
     * @param debitAmount   transfer amount
     * @return operation object which encapsulates transfer details
     */
    Operation transferMoney(Account debitAccount, Account creditAccount, Money debitAmount);

    /**
     * Returns a total balance (money owned by this bank) in a provided currency.
     *
     * @param currency balance currency
     * @return balance
     */
    Money getBalance(Currency currency);

}
