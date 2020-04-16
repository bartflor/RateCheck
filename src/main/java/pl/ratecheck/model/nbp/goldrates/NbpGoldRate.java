package pl.ratecheck.model.nbp.goldrates;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import pl.ratecheck.model.Rate;

public class NbpGoldRate implements Rate{
	@JsonProperty("data")
	private LocalDate date;
	@JsonProperty("cena")
	private Double value;

	public NbpGoldRate() {
	}

	
	public NbpGoldRate(LocalDate date, Double value) {
		super();
		this.date = date;
		this.value = value;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	@JsonSetter("data")
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}
	
	public Double getValue() {
		return value;
	}


	public void setValue(Double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Rate [data=" + date + ", value=" + value + "]";
	}

}
