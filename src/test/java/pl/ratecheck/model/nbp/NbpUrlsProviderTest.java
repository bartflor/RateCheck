package pl.ratecheck.model.nbp;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.ratecheck.model.CurrencyCode;

class NbpUrlsProviderTest {
	NbpUrlsProvider nbpUrlsProvider = new NbpUrlsProvider();

	@Test
	final void provideUrlShouldRetunOneProperlyFormatedExchangeratesUrl() {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate stopDate = LocalDate.of(2020, 2, 1);
		CurrencyCode currencyCode = CurrencyCode.GBP;
		//when
		List<URL> resultList = nbpUrlsProvider.provideUrl(startDate, stopDate, currencyCode);
		//then
		String expected = "http://api.nbp.pl/api/exchangerates/rates/C/GBP/2020-01-01/2020-02-01/?format=json";
		Assertions.assertEquals(1, resultList.size(), "Should return List of 1 URL");
		Assertions.assertEquals(expected, resultList.get(0).toString(), "Should return properly formated URL");
	}

	@Test
	final void provideShouldRetunOneProperlyFormatedGoldRatesUrl() {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate stopDate = LocalDate.of(2020, 2, 1);
		//when
		List<URL> resultList = nbpUrlsProvider.provideUrl(startDate, stopDate);
		//then
		String expected = "http://api.nbp.pl/api/cenyzlota/2020-01-01/2020-02-01?format=json";
		Assertions.assertEquals(1, resultList.size(), "Should return List of 1 URL");
		Assertions.assertEquals(expected, resultList.get(0).toString(), "Should return properly formated URL");
	}

}
