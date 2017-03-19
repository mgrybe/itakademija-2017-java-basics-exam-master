package lt.itakademija.exam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Provides currency exchange rates.
 */
public final class CurrencyRatesProvider {

    private final Map<CurrencyPair, Money> currencyPairToRatesMap;

    public CurrencyRatesProvider(Map<CurrencyPair, Money> currencyPairToRatesMap) {
        this.currencyPairToRatesMap = new HashMap<>(currencyPairToRatesMap);
    }

    /**
     * Returns currency exchange rate or null if rate for specified currencies does not exist.
     *
     * @param from currency owned
     * @param to   currency wanted
     * @return rate
     */
    public Money getRate(Currency from, Currency to) {
        return currencyPairToRatesMap.get(new CurrencyPair(from, to));
    }

    public static final class CurrencyPair {
        private final Currency from;
        private final Currency to;

        public CurrencyPair(Currency from, Currency to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            CurrencyPair other = (CurrencyPair) obj;
            if (from == null) {
                if (other.from != null)
                    return false;
            } else if (!from.equals(other.from))
                return false;
            if (to == null) {
                if (other.to != null)
                    return false;
            } else if (!to.equals(other.to))
                return false;
            return true;
        }
    }
}
