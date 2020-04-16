package pl.ratecheck.model;

import java.time.LocalDate;

public interface Rate {

	LocalDate getDate();

	void setDate(String date);

	Double getValue();

	void setValue(Double value);

}