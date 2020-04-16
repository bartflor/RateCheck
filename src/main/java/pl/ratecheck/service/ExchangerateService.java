package pl.ratecheck.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.ratecheck.model.CurrencyCode;
import pl.ratecheck.model.ExchangeratesDao;
import pl.ratecheck.model.Rate;
@Service
public class ExchangerateService {
	@Autowired
	private ExchangeratesDao exchangerateDao;

	public ExchangerateService() {
	}

	public List<? extends Rate> getExchangerateFromPeriod(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode, String price) {

		return exchangerateDao.getRatesInDatesRange(startDate, stopDate, currencyCode, price);
	}
}
