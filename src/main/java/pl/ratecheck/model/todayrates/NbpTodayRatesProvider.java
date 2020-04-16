package pl.ratecheck.model.todayrates;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.ratecheck.model.nbp.goldrates.NbpGoldRate;

@Component
public class NbpTodayRatesProvider {

	private final String NBP_TODAY_GOLD_RATE_URL = "http://api.nbp.pl/api/cenyzlota?format=json";
	private final String NBP_TODAY_EXCHANGERATES_URL = "http://api.nbp.pl/api/exchangerates/tables/a?format=json";

	public NbpGoldRate checkTodayGoldRates() {
		ObjectMapper mapper = new ObjectMapper();
		NbpGoldRate rate = new NbpGoldRate();
		try {
			NbpGoldRate[] rates = mapper.readValue(new URL(NBP_TODAY_GOLD_RATE_URL), NbpGoldRate[].class);
			rate = rates[0];
		} catch (IOException e) {
			// TODO: handle IO ex
			e.printStackTrace();
		}
		return rate;
	}

	public List<NbpTodayExchangerate> chceckTodayExchangeRates() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NbpRatesToMap[] rates;
		try {
			rates = mapper.readValue(new URL(NBP_TODAY_EXCHANGERATES_URL), NbpRatesToMap[].class);
			return rates[0].getRates();
		} catch (IOException e) {
			// TODO: handle IO ex
			e.printStackTrace();
		}
		return null;
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
