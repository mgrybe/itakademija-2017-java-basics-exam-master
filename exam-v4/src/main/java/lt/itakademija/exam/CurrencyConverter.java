package lt.itakademija.exam;

public interface CurrencyConverter {

    /**
     * Converts money from one currency to another.
     *
     * @param from   currency owned
     * @param to     currency wanted
     * @param amount amount
     * @return converted amount
     */
    Money convert(Currency from, Currency to, Money amount);

}
