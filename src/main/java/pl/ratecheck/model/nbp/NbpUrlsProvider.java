package pl.ratecheck.model.nbp;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.ratecheck.model.CurrencyCode;
@Component
public class NbpUrlsProvider {
	private static final String NBP_GOLD_RATES_URL_FORMAT = "http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}?format=json";
	private static final String NBP_EXCHANGERATES_URL_FORMAT = "http://api.nbp.pl/api/exchangerates/rates/C/{code}/{startDate}/{endDate}/?format=json";
	private static final Period MAX_DATA_TIME_PERIOD = Period.ofDays(92);
	
	public NbpUrlsProvider() {}

	public List<URL> provideUrl(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode) {
		String preparedUrl;
		Period period = Period.ZERO;
		period = period.plusDays(startDate.until(stopDate, ChronoUnit.DAYS));
		List<URL> Urls = new LinkedList<URL>();
		LocalDate validStopDate;
		try {
			for (; !period.isNegative(); period = period.minus(MAX_DATA_TIME_PERIOD)) {
				if (!period.minus(MAX_DATA_TIME_PERIOD).isNegative())
					validStopDate = startDate.plus(MAX_DATA_TIME_PERIOD);
				else 
					validStopDate = startDate.plus(period);
				if(currencyCode != null)
					preparedUrl = NBP_EXCHANGERATES_URL_FORMAT.replace("{code}", currencyCode.getCode());
				else 
					preparedUrl = NBP_GOLD_RATES_URL_FORMAT;
				preparedUrl = preparedUrl.replace("{startDate}", startDate.toString());
				preparedUrl = preparedUrl.replace("{endDate}", validStopDate.toString());
				Urls.add(new URL(preparedUrl));
				startDate = validStopDate;
			}
		}

		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return Urls;
	}
	
	public List<URL> provideUrl(LocalDate startDate, LocalDate stopDate) {
		return this.provideUrl(startDate, stopDate, null);
	}

	
}
