package pl.ratecheck.model.nbp.exchangerates;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.PriceType;
import pl.ratecheck.model.Rate;

class NbpExchangeratesDaoTest {

	private NbpExchangeratesDao nbpExchangeratesDao = new NbpExchangeratesDao();

	@Test
	void getRatesInDatesRangeShouldRetunProperRatesListTest() {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate stopDate = LocalDate.of(2020, 2, 1);
		CurrencyCode currencyCode = CurrencyCode.USD;
		PriceType price = PriceType.ASK;
		//when
		List<NbpExchangerate> exchangeratesList = new ArrayList<NbpExchangerate>();
		exchangeratesList.add(new NbpExchangerate(LocalDate.of(2020, 1, 2), 2.2, 1.1));
		exchangeratesList.add(new NbpExchangerate(LocalDate.of(2020, 1, 7), 2.4, 1.4));
		exchangeratesList.add(new NbpExchangerate(LocalDate.of(2020, 1, 16), 2.6, 1.8));

		NbpExchangeratesProvider mockRatesProvider = Mockito.mock(NbpExchangeratesProvider.class);
		ReflectionTestUtils.setField(nbpExchangeratesDao, "ratesProvider", mockRatesProvider);
		when(mockRatesProvider.provideRatesList(startDate, stopDate, currencyCode)).thenReturn(exchangeratesList);
	
		List<? extends Rate> actual = nbpExchangeratesDao.getRatesInDatesRange(startDate, stopDate, currencyCode, price);
		//then
		Assertions.assertEquals(LocalDate.of(2020, 1, 2), actual.get(0).getDate());
		Assertions.assertEquals(1.1, actual.get(0).getValue());	
		Assertions.assertEquals(3, actual.size());
	}

}
