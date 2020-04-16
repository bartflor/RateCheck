package pl.ratecheck.model;

import java.time.LocalDate;

public interface Exchangerate {

	LocalDate getDate();

	void setDate(LocalDate date);

	double getBid();

	void setBid(double bid);

	double getAsk();

	void setAsk(double ask);

}