package pl.ratecheck.model.nbp.exchangerates;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.nbp.NbpUrlsProvider;

@Component
public class NbpExchangeratesProvider {
	@Autowired
	private NbpUrlsProvider urlsProvider;

	public NbpExchangeratesProvider() {	}

	public List <NbpExchangerate> provideRatesList(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode){
		List<URL> urlsList = urlsProvider.provideUrl(startDate, stopDate, currencyCode);
		List<NbpExchangerate> ratesList = urlsList.stream()
												  .flatMap(url -> getRatesListFromUrl(url).stream())
												  .collect(Collectors.toList());
		
		if(currencyCode == CurrencyCode.JPY) {
			return ratesList.stream()
					 		.map(rate -> new NbpExchangerate(rate.getDate(), rate.getBid()*100, rate.getAsk()*100))
					 		.collect(Collectors.toList());
		}
		return ratesList;
	}
	
	private List<NbpExchangerate> getRatesListFromUrl(URL url){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		NbpExchangeratesDeserilazizationAdapter rates;
		try {
			rates = mapper.readValue(url, NbpExchangeratesDeserilazizationAdapter.class);
			return rates.getRates();
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();			
		}
	}
	
	

}

class NbpExchangeratesDeserilazizationAdapter{
	public NbpExchangerate[] rates;
	
	public NbpExchangeratesDeserilazizationAdapter() {}

	public NbpExchangeratesDeserilazizationAdapter(String currency, String code, NbpExchangerate[] rates) {
		this.rates = rates;
	}

	public List<NbpExchangerate> getRates() {
		return Arrays.asList(rates);
	}

	public void setRates(NbpExchangerate[] rates) {
		this.rates = rates;
	}
}
