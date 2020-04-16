package pl.ratecheck.model.nbp.goldrates;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import pl.ratecheck.model.nbp.NoDataExeption;

@Component
public class NbpGoldRatesProvider {
	private final String NBP_GOLD_RATES_URL = "http://api.nbp.pl/api/cenyzlota/start/stop?format=json";
	private final Period MAX_DATA_TIME_PERIOD = Period.ofDays(92);
	
	public List <NbpGoldRate> provideRatesList(LocalDate startDate, LocalDate stopDate) throws NoDataExeption{
		List<URL> urlsList = provideUrl(startDate, stopDate);
		List<NbpGoldRate> ratesList = new ArrayList<>();
		try {
			urlsList.stream().map(this::mapToRatesList).forEach(ratesList::addAll);
		} catch (RuntimeException e) {
			throw new NoDataExeption();
		}
		return ratesList;
	}

	private List<URL> provideUrl(LocalDate startDate, LocalDate stopDate) {
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
				String preparedUrl = NBP_GOLD_RATES_URL.replace("start", startDate.toString());
				preparedUrl = preparedUrl.replace("stop", validStopDate.toString());
				Urls.add(new URL(preparedUrl));
				startDate = validStopDate;
			}
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return Urls;
	}
	
	private List<NbpGoldRate> mapToRatesList(URL url) {
		ObjectMapper mapper = new ObjectMapper();
		List<NbpGoldRate> rates = new LinkedList<>();
		try {
			CollectionType listOfRatesType = mapper.getTypeFactory()
				      						.constructCollectionType(List.class, NbpGoldRate.class);
			rates = mapper.readValue(url, listOfRatesType);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return rates;
	}
	
	
}
