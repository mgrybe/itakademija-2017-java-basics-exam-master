package lt.itakademija.exam;

import lt.itakademija.exam.Currency;
import lt.itakademija.exam.CurrencyConversionException;
import lt.itakademija.exam.CurrencyConverter;
import lt.itakademija.exam.CurrencyRatesProvider;
import lt.itakademija.exam.Money;

public final class CurrencyConverterImpl implements CurrencyConverter {

	private final CurrencyRatesProvider ratesProvider;
	
	public CurrencyConverterImpl(CurrencyRatesProvider ratesProvider) {
		this.ratesProvider = ratesProvider;
	}

	@Override
	public Money convert(Currency from, Currency to, Money amount) {
		Money rate = ratesProvider.getRate(from, to);
		if (rate == null) {
			throw new CurrencyConversionException("Could not convert currency pair");
		}
		
		return amount.multiply(rate);
	}

}
