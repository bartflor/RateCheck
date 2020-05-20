package pl.ratecheck.model.nbp.goldrates;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import pl.ratecheck.model.GoldMassUnits;
import pl.ratecheck.model.Rate;

class NbpGoldRatesDaoTest {
	private NbpGoldRatesDao nbpGoldRatesDao = new NbpGoldRatesDao();

	@Test
	final void getGoldRatesInDatesRangeShouldgiveProperRatesList() {
		//given
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate stopDate = LocalDate.of(2020, 2, 1);
		GoldMassUnits unit = GoldMassUnits.GRAM;
		//when
		List<NbpGoldRate> mockGoldRatesList = new ArrayList<>();
		mockGoldRatesList.add(new NbpGoldRate(LocalDate.of(2020, 1, 2), 2.2));
		mockGoldRatesList.add(new NbpGoldRate(LocalDate.of(2020, 1, 7), 2.4));
		mockGoldRatesList.add(new NbpGoldRate(LocalDate.of(2020, 1, 16), 2.6));

		NbpGoldRatesProvider mockRatesProvider = Mockito.mock(NbpGoldRatesProvider.class);
		ReflectionTestUtils.setField(nbpGoldRatesDao, "ratesProvider", mockRatesProvider);
		when(mockRatesProvider.provideRatesList(startDate, stopDate)).thenReturn(mockGoldRatesList);
	
		List<? extends Rate> actual = nbpGoldRatesDao.getGoldRatesInDatesRange(startDate, stopDate, unit);
		//then
		Assertions.assertEquals(3, actual.size());
		Assertions.assertIterableEquals(mockGoldRatesList, actual);
	}

}
