package pl.ratecheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ratecheck.model.todayrates.NbpTodayRates;
import pl.ratecheck.model.todayrates.NbpTodayRatesDao;

@Service
public class TodayRatesService {
	@Autowired
	NbpTodayRatesDao todayRatesDao;
	
	public NbpTodayRates getTodayRates() {
		return todayRatesDao.getTodayRates();
	}
}
