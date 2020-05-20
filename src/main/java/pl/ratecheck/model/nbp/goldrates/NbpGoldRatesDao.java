package pl.ratecheck.model.nbp.goldrates;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pl.ratecheck.model.GoldMassUnits;
import pl.ratecheck.model.GoldRatesDao;
import pl.ratecheck.model.Rate;

@Repository
public class NbpGoldRatesDao implements GoldRatesDao {
	@Autowired
	private NbpGoldRatesProvider ratesProvider;
	public NbpGoldRatesDao() {
	}

	@Override
	public List<? extends Rate> getGoldRatesInDatesRange(LocalDate startDate, LocalDate stopDate, GoldMassUnits unit){
		List<NbpGoldRate> goldRates = ratesProvider.provideRatesList(startDate, stopDate);
		if(unit.equals(GoldMassUnits.OUNCE))
			return goldRates.stream()
					 .map(rate -> new NbpGoldRate(rate.getDate(), rate.getValue()*GoldMassUnits.OUNCE.getConverterToGram()))
					 .collect(Collectors.toList());
		return goldRates;
	}

}
