package lt.itakademija.exam.test;

import lt.itakademija.exam.*;
import lt.itakademija.exam.CurrencyRatesProvider.CurrencyPair;
import lt.itakademija.exam.grader.ExamTask;
import lt.itakademija.exam.grader.GradeEvaluatingRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static lt.itakademija.exam.test.Methods.hasMethod;
import static lt.itakademija.exam.test.Methods.permuteWithNull;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(GradeEvaluatingRunner.class)
public abstract class BaseTest {

    private static final Currency EUR = new Currency("EUR");
    private static final Currency USD = new Currency("USD");

    private static final String PERSON_NAME = "Testas";
    private static final String PERSON_SURNAME = "Testaitis";

    private static Map<CurrencyPair, Money> currencyRatesMap = new HashMap<>();

    private Bank bank;

    private CurrencyConverter currencyConverter;

    protected abstract Bank createBank(CurrencyConverter currencyConverter);

    protected abstract CurrencyConverter createCurrencyConverter(CurrencyRatesProvider ratesProvider);

    @BeforeClass
    public final static void setUpClass() {
        // For 1.00 USD you will get 0.93020 EUR
        currencyRatesMap.put(new CurrencyPair(USD, EUR), new Money("0.93020"));
        currencyRatesMap.put(new CurrencyPair(EUR, USD), new Money("1.07504"));
    }

    @Before
    public final void setUpTest() {
        this.currencyConverter = createCurrencyConverter(new CurrencyRatesProvider(currencyRatesMap));
        this.bank = createBank(currencyConverter);
    }

    @Test
    @ExamTask(grade = 0.5)
    public final void createsBankCorrectly() {
        assertThat(bank, notNullValue());
    }

    @Test
    @ExamTask(grade = 0.25)
    public final void throwsNullPointerExceptionIfCreatingCustomerWithNullArgs() {
        // @formatter:off
        assertThat(bank, hasMethod("createCustomer", PersonCode.class, PersonName.class)
                         .throwing(NullPointerException.class)
                         .invokedWithArgs(permuteWithNull(new PersonCode("1"), new PersonName(PERSON_NAME, PERSON_SURNAME))));
        // @formatter:on
    }

    @Test
    @ExamTask(grade = 1.0)
    public final void createsCustomersCorrectly() {
        for (int i = 0; i < random(100); i++) {
            final Customer customer = createCustomer(Integer.toString(i));

            assertThat(customer, notNullValue());
            assertThat("Customer id is invalid", customer.getId(), is(i + 1L));
            assertThat("Person code is invalid", customer.getPersonCode(), is(new PersonCode(Integer.toString(i))));
            assertThat("Person name is invalid", customer.getPersonName(), is(new PersonName(PERSON_NAME + i, PERSON_SURNAME + i)));
        }
    }

    @Test(expected = CustomerCreateException.class)
    @ExamTask(grade = 0.25)
    public final void throwsCustomerCreateExceptionIfExistingPersonCodeProvided() {
        bank.createCustomer(new PersonCode("1"), new PersonName(PERSON_NAME, PERSON_SURNAME));
        bank.createCustomer(new PersonCode("1"), new PersonName(PERSON_NAME, PERSON_SURNAME));
    }

    @Test
    @ExamTask(grade = 1.0)
    public final void createsAccount() {
        final Customer customer = createCustomer("1");
        final Account account = bank.createAccount(customer, EUR);

        assertThat(account, notNullValue());
        assertThat("Account id is invalid", account.getId(), is(1L));
        assertThat("Account balance is invalid", account.getBalance(), is(new Money(0.0d)));
        assertThat("Account customer is missing", account.getCustomer(), notNullValue());
        assertThat("Account customer is invalid", account.getCustomer(), is(customer));
        assertThat("Account is not registered with a customer", customer.getAccounts(), hasItem(account));
    }

    @Test(expected = AccountCreateException.class)
    @ExamTask(grade = 0.25)
    public final void throwsAccountCreateExceptionIfNonExistentCustomerPassed() {
        final Customer customer = new Customer(1L, new PersonCode("1"), new PersonName(PERSON_NAME, PERSON_SURNAME));
        bank.createAccount(customer, EUR);
    }

    @Test
    @ExamTask(grade = 0.25)
    public final void throwsNullPointerExceptionIfCreatingAccountWithNullArgs() {
        // @formatter:off
        assertThat(bank, hasMethod("createAccount", Customer.class, Currency.class)
                         .throwing(NullPointerException.class)
                         .invokedWithArgs(permuteWithNull(createCustomer("1", PERSON_NAME, PERSON_SURNAME), EUR)));
        // @formatter:on
    }

    @Test
    @ExamTask(grade = 2.0)
    public final void countsTotalBalanceInEurosCorrectly() {
        double accountMoney = 10.0d;

        int customersWithAccountInEurosCount = random(100);
        for (int i = 0; i < customersWithAccountInEurosCount; i++) {
            final Customer customer = createCustomer(Integer.toString(i));
            final Account account = bank.createAccount(customer, EUR);
            account.setBalance(new Money(accountMoney));
        }

        int customersWithAccountInUsDollarsCount = random(100);
        for (int i = customersWithAccountInEurosCount; i < customersWithAccountInEurosCount + customersWithAccountInUsDollarsCount; i++) {
            final Customer customer = createCustomer(Integer.toString(i));
            final Account account = bank.createAccount(customer, USD);
            account.setBalance(new Money(accountMoney));
        }

        Money balanceInEuros = new Money(customersWithAccountInEurosCount * accountMoney);
        Money balanceInDollars = new Money(customersWithAccountInUsDollarsCount * accountMoney);

        assertThat("Incorrect total bank balance", bank.getBalance(EUR), is(balanceInEuros.add(convert(balanceInDollars, USD, EUR))));
    }

    @Test(expected = InsufficientFundsException.class)
    @ExamTask(grade = 0.25)
    public final void throwsInsufficientFundsExceptionIfDebitAccountLacksMoney() {
        final Customer customer1 = createCustomer("1");
        final Account account1 = bank.createAccount(customer1, EUR);

        final Customer customer2 = createCustomer("2");
        final Account account2 = bank.createAccount(customer2, EUR);

        bank.transferMoney(account1, account2, new Money(100.0));
    }

    @Test
    @ExamTask(grade = 2.0)
    public final void createsPaymentCorrectly() {
        final Customer customer1 = createCustomer("1");
        final Account debitAccount = bank.createAccount(customer1, EUR);
        final Money debitBalance = new Money(random(100, 1000));
        debitAccount.setBalance(debitBalance);

        final Customer customer2 = createCustomer("2");
        final Account creditAccount = bank.createAccount(customer2, USD);
        final Money creditBalance = new Money(0.0d);
        creditAccount.setBalance(creditBalance);

        final Money paymentAmount = new Money(10.0d);

        Operation operation = null;
        final int numberOfPayments = random(1, 9);
        for (int i = 0; i < numberOfPayments; i++) {
            operation = bank.transferMoney(debitAccount, creditAccount, paymentAmount);
        }

        // Verify
        final Money expectedDebitAccoutBalance = debitBalance.substract(paymentAmount.multiply(numberOfPayments));
        assertThat("Debit account balance is incorrect", debitAccount.getBalance(), is(expectedDebitAccoutBalance));

        final Money expectedCreditAccountBalance = convert(paymentAmount.multiply(numberOfPayments), EUR, USD);
        assertThat("Credit account balance is incorrect", creditAccount.getBalance(), is(expectedCreditAccountBalance));

        assertThat("Wrong debit account", operation.getDebitAccount(), is(debitAccount));
        assertThat("Wrong credit account", operation.getCreditAccount(), is(creditAccount));

        assertThat("Wrong payment id", operation.getId(), is(Long.valueOf(numberOfPayments)));
        assertThat("Wrong operation amount", operation.getDebitAmount(), is(paymentAmount));
    }

    @Test
    @ExamTask(grade = 0.25)
    public final void createCurrencyConverter() {
        assertThat(this.currencyConverter, notNullValue());
    }

    @Test(expected = CurrencyConversionException.class)
    @ExamTask(grade = 0.25)
    public final void throwsCurrencyConversionExceptionIfUnableToConvertCurrencyPair() {
        this.currencyConverter.convert(USD, new Currency("DUMMY"), new Money("1"));
    }

    @Test
    @ExamTask(grade = 0.75)
    public final void convertsCurrencyCorrectly() {
        // Converting USD to EUR
        final Money amount = new Money("100");
        Money convertedAmount = this.currencyConverter.convert(USD, EUR, amount);

        final Money usdEurRate = currencyRatesMap.get(new CurrencyPair(USD, EUR));
        assertThat("Currency conversion error", convertedAmount, is(amount.multiply(usdEurRate)));

        // Converting EUR to USD
        convertedAmount = this.currencyConverter.convert(EUR, USD, amount);

        final Money eurUsdRate = currencyRatesMap.get(new CurrencyPair(EUR, USD));
        assertThat("Currency conversion error", convertedAmount, is(amount.multiply(eurUsdRate)));
    }

    private Money convert(Money amount, Currency from, Currency to) {
        final Money rate = currencyRatesMap.get(new CurrencyPair(from, to));
        return amount.multiply(rate);
    }

    private Customer createCustomer(final String context) {
        return createCustomer(context, PERSON_NAME + context, PERSON_SURNAME + context);
    }

    private Customer createCustomer(String personCode, String name, String surname) {
        return bank.createCustomer(new PersonCode(personCode), new PersonName(name, surname));
    }

    private static int random(int max) {
        return random(1, max);
    }

    private static int random(int min, int max) {
        return (int) (Math.random() * max) + min;
    }

}
