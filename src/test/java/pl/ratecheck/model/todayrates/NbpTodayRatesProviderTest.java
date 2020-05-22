package pl.ratecheck.model.todayrates;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import pl.ratecheck.model.nbp.goldrates.NbpGoldRate;
import pl.ratecheck.model.nbp.goldrates.NbpGoldRatesProvider;

class NbpTodayRatesProviderTest {
	private NbpTodayRatesProvider nbpTodayRatesProvider = new NbpTodayRatesProvider();

	
	@Test
	final void checkTodayGoldRatesShouldReturnProperValue() throws MalformedURLException {
		NbpTodayRatesProvider nbpTodayRatesProvider = new NbpTodayRatesProvider();

		//when
		List<NbpGoldRate> listOfRate = new ArrayList<>();
		final NbpGoldRate expectedRate = new NbpGoldRate(LocalDate.of(2020, 1, 1), 100.1);
		listOfRate.add(expectedRate);
		NbpGoldRatesProvider mockGoldRatesProvider = Mockito.mock(NbpGoldRatesProvider.class);
		ReflectionTestUtils.setField(nbpTodayRatesProvider, "goldRatesProvider", mockGoldRatesProvider);
		when(mockGoldRatesProvider.getGoldRatesListFromUrl(new URL("http://api.nbp.pl/api/cenyzlota?format=json")))
		.thenReturn(listOfRate);
		
		NbpGoldRate actual = nbpTodayRatesProvider.checkTodayGoldRates();
		//then
		Assertions.assertEquals(expectedRate, actual);
	}
	
	@Test
	final void testChceckTodayExchangeRates() throws JsonParseException, JsonMappingException, IOException {

		//when		
		List<NbpTodayExchangerate> actualList = nbpTodayRatesProvider.checkTodayExchangeRates();
		//then
		Assertions.assertFalse(actualList.isEmpty(), "should return non empty");
	}

}
