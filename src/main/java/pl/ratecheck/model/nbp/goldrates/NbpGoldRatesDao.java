package pl.ratecheck.model.nbp.goldrates;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.ratecheck.model.GoldMassUnits;
import pl.ratecheck.model.Rate;
import pl.ratecheck.model.GoldRatesDao;
import pl.ratecheck.model.nbp.NoDataExeption;

@Repository
public class NbpGoldRatesDao implements GoldRatesDao {
	@Autowired
	private NbpGoldRatesProvider ratesProvider;
	public NbpGoldRatesDao() {
	}

	@Override
	public List<? extends Rate> getGoldRatesInDatesRange(LocalDate startDate, LocalDate stopDate, GoldMassUnits unit){

		List<NbpGoldRate> goldRates;
		try {
			goldRates = ratesProvider.provideRatesList(startDate, stopDate);
		} catch (NoDataExeption e) {
			e.printStackTrace();

			return null;
		}
		if(unit.equals(GoldMassUnits.OUNCE))
			goldRates.stream().forEach(rate -> rate.setValue(rate.getValue()*GoldMassUnits.OUNCE.getConverterToGram()));
		return goldRates;
	}

}
