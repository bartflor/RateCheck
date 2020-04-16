package pl.ratecheck.model;

import java.time.LocalDate;
import java.util.List;

public interface GoldRatesDao {

	List<? extends Rate> getGoldRatesInDatesRange(LocalDate startDate, LocalDate stopDate, GoldMassUnits unit);
	

}