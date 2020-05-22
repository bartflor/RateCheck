package pl.ratecheck.model.todayrates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NbpTodayRatesDao {
	@Autowired
	private NbpTodayRatesProvider ratesProvider;
	public NbpTodayRatesDao() {
	}

	public NbpTodayRates getTodayRates() {
		NbpTodayRates rates = new NbpTodayRates();
		rates.setTodayExchangeRates(ratesProvider.checkTodayExchangeRates());
		rates.setTodayGoldRate(ratesProvider.checkTodayGoldRates());
		return rates;
	}

}
