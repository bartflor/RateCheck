package pl.ratecheck.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ratecheck.model.GoldMassUnits;
import pl.ratecheck.model.Rate;
import pl.ratecheck.model.nbp.goldrates.NbpGoldRatesDao;

@Service
public class GoldRateService {
	@Autowired
	private NbpGoldRatesDao goldRateDao;
	public GoldRateService() {
	}

	public List<? extends Rate> getGoldRateFromPeriod(LocalDate startDate, LocalDate stopDate, GoldMassUnits unit) {
		return goldRateDao.getGoldRatesInDatesRange(startDate, stopDate, unit);
		}
}
