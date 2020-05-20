package pl.ratecheck.model.nbp.goldrates;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import pl.ratecheck.model.nbp.NbpUrlsProvider;

@Component
public class NbpGoldRatesProvider {
	@Autowired
	private NbpUrlsProvider urlsProvider;
	
	public NbpGoldRatesProvider() {	}
	
	public List <NbpGoldRate> provideRatesList(LocalDate startDate, LocalDate stopDate){
		List<URL> urlsList = urlsProvider.provideUrl(startDate, stopDate);
		return urlsList.stream()
					   .flatMap(url -> this.getGoldRatesListFromUrl(url).stream())
					   .collect(Collectors.toList());
	}

	public List<NbpGoldRate> getGoldRatesListFromUrl(URL url) {
		ObjectMapper mapper = new ObjectMapper();
		List<NbpGoldRate> rates = new LinkedList<>();
		try {
			CollectionType listOfRatesType = mapper.getTypeFactory()
				      							   .constructCollectionType(List.class, NbpGoldRate.class);
			rates = mapper.readValue(url, listOfRatesType);
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		return rates;
	}
	
	
}
