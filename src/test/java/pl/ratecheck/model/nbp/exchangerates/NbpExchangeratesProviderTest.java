package pl.ratecheck.model.nbp.exchangerates;

import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.nbp.NbpUrlsProvider;

class NbpExchangeratesProviderTest {
	NbpExchangeratesProvider nbpExchangeratesProvider = new NbpExchangeratesProvider();
	
	@Test
	final void provideRatesListShouldReturnProperRateList() throws MalformedURLException {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 2);
		LocalDate stopDate = LocalDate.of(2020, 1, 3);
		CurrencyCode currencyCode = CurrencyCode.EUR;
		//when
		List<URL> urlsList = new ArrayList<URL>();
		urlsList.add(new URL("http://api.nbp.pl/api/exchangerates/rates/C/EUR/2020-01-02/2020-01-03/?format=json"));
		NbpUrlsProvider mockUrlsProvider = Mockito.mock(NbpUrlsProvider.class);
		ReflectionTestUtils.setField(nbpExchangeratesProvider, "urlsProvider", mockUrlsProvider);
		when(mockUrlsProvider.provideUrl(startDate, stopDate, currencyCode)).thenReturn(urlsList);
		
		List<NbpExchangerate> expected = new ArrayList<NbpExchangerate>();
		expected.add(new NbpExchangerate(startDate, 4.2159, 4.3011));
		expected.add(new NbpExchangerate(stopDate, 4.2087, 4.2937));
		
		List<NbpExchangerate> result = nbpExchangeratesProvider.provideRatesList(startDate, stopDate, currencyCode);
		//then
		Assertions.assertEquals(2, result.size(), "Should return  list of 2 rates from specified data range");
		Assertions.assertIterableEquals(expected, result);
	}

}
