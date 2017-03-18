package lt.itakademija.exam;

import lt.itakademija.exam.test.BaseTest;

/**
 * Created by mariusg on 2017.03.18.
 */
public class SolutionTest extends BaseTest {
    @Override
    protected Bank createBank(CurrencyConverter currencyConverter) {
        return new BankImpl(currencyConverter);
    }

    @Override
    protected CurrencyConverter createCurrencyConverter(CurrencyRatesProvider ratesProvider) {
        return new CurrencyConverterImpl(ratesProvider);
    }
}
