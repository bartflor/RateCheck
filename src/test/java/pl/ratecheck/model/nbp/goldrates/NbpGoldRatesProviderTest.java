package pl.ratecheck.model.nbp.goldrates;

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

import pl.ratecheck.model.nbp.NbpUrlsProvider;

class NbpGoldRatesProviderTest {
	private NbpGoldRatesProvider nbpGoldRatesProvider = new NbpGoldRatesProvider();
	@Test
	final void provideRatesListShouldReturnProperRateList() throws MalformedURLException {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 2);
		LocalDate stopDate = LocalDate.of(2020, 1, 3);
		//when
		List<URL> urlsList = new ArrayList<URL>();
		urlsList.add(new URL("http://api.nbp.pl/api/cenyzlota/2020-01-02/2020-01-03/?format=json"));
		NbpUrlsProvider mockUrlsProvider = Mockito.mock(NbpUrlsProvider.class);
		ReflectionTestUtils.setField(nbpGoldRatesProvider, "urlsProvider", mockUrlsProvider);
		when(mockUrlsProvider.provideUrl(startDate, stopDate)).thenReturn(urlsList);
		
		List<NbpGoldRate> expected = new ArrayList<>();
		expected.add(new NbpGoldRate(startDate, 185.97));
		expected.add(new NbpGoldRate(stopDate, 186.58));
		
		List<NbpGoldRate> result = nbpGoldRatesProvider.provideRatesList(startDate, stopDate);
		//then
		Assertions.assertEquals(2, result.size(), "Should return  list of 2 rates from specified data range");
		Assertions.assertIterableEquals(expected, result);
	}

}
