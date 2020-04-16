package pl.ratecheck.model.nbp.exchangerates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.nbp.NoDataExeption;

@Component
public class NbpExchangeratesProvider {
	private final String NBP_EXCHANGERATES_URL_FORMAT = "http://api.nbp.pl/api/exchangerates/rates/C/{code}/{startDate}/{endDate}/?format=json";
	private final Period MAX_DATA_TIME_PERIOD = Period.ofDays(92);
	
	public List <NbpExchangerate> provideRatesList(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode) throws NoDataExeption{
		List<URL> urlsList = provideUrl(startDate, stopDate, currencyCode);
		List<NbpExchangerate> ratesList = new ArrayList<>();
		try {
			urlsList.stream().map(this::mapToRatesList).forEach(ratesList::addAll);
		} catch (RuntimeException e) {
			throw new NoDataExeption();
		}
		if(currencyCode == CurrencyCode.JPY) {
			ratesList.stream().forEach(rate -> {rate.setAsk(rate.getAsk()*100);
												rate.setBid(rate.getBid()*100);});
												
		}
		return ratesList;
	}

	private List<URL> provideUrl(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode) {
		Period period = Period.ZERO;
		period = period.plusDays(startDate.until(stopDate, ChronoUnit.DAYS));
		List<URL> Urls = new LinkedList<URL>();
		LocalDate validStopDate;
		try {
			for (; !period.isNegative(); period = period.minus(MAX_DATA_TIME_PERIOD)) {
				if (!period.minus(MAX_DATA_TIME_PERIOD).isNegative()) {
					validStopDate = startDate.plus(MAX_DATA_TIME_PERIOD);
				} else {
					validStopDate = startDate.plus(period);
				}
				String preparedUrl = NBP_EXCHANGERATES_URL_FORMAT.replace("{code}", currencyCode.getCode());
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
	
	private List<NbpExchangerate> mapToRatesList(URL url){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NbpExchangeratesToMap rates;
		try {
			rates = mapper.readValue(url, NbpExchangeratesToMap.class);
			return rates.getRates();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);			
		}
	}
	
	

}

class NbpExchangeratesToMap{
	public NbpExchangerate[] rates;
	
	public NbpExchangeratesToMap() {
		super();
	}

	public NbpExchangeratesToMap(String currency, String code, NbpExchangerate[] rates) {
		this.rates = rates;
	}

	public List<NbpExchangerate> getRates() {
		return Arrays.asList(rates);
	}

	public void setRates(NbpExchangerate[] rates) {
		this.rates = rates;
	}
}
