package pl.ratecheck.model.todayrates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.ratecheck.model.nbp.goldrates.NbpGoldRate;
import pl.ratecheck.model.nbp.goldrates.NbpGoldRatesProvider;

@Component
public class NbpTodayRatesProvider {
	private static final URL NBP_TODAY_GOLD_RATE_URL;
	private static final URL NBP_TODAY_EXCHANGERATES_URL;
	@Autowired
	NbpGoldRatesProvider goldRatesProvider;
	
	static{
		try {
			NBP_TODAY_GOLD_RATE_URL = new URL("http://api.nbp.pl/api/cenyzlota?format=json");
			NBP_TODAY_EXCHANGERATES_URL = new URL("http://api.nbp.pl/api/exchangerates/tables/a?format=json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public NbpGoldRate checkTodayGoldRates() {
		List<NbpGoldRate> rates = goldRatesProvider.getGoldRatesListFromUrl(NBP_TODAY_GOLD_RATE_URL);
		return rates.isEmpty() ? new NbpGoldRate() : rates.get(0);
	}

	public List<NbpTodayExchangerate> checkTodayExchangeRates() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NbpRatesToMap[] rates;
		try {
			rates = mapper.readValue(NBP_TODAY_EXCHANGERATES_URL, NbpRatesToMap[].class);
			return rates.length > 0 ? rates[0].getRates() : Collections.emptyList();
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}

class NbpRatesToMap {
	public NbpTodayExchangerate[] rates;

	public NbpRatesToMap() {
		super();
	}

	public NbpRatesToMap(NbpTodayExchangerate[] rates) {
		this.rates = rates;
	}

	public List<NbpTodayExchangerate> getRates() {
		return Arrays.asList(rates);
	}

	public void setRates(NbpTodayExchangerate[] rates) {
		this.rates = rates;
	}
}
