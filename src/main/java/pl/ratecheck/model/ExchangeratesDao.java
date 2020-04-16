package pl.ratecheck.model;

import java.time.LocalDate;
import java.util.List;

public interface ExchangeratesDao {

	List<? extends Rate> getRatesInDatesRange(LocalDate startDate, LocalDate stopDate, CurrencyCode currencyCode, String price);

}