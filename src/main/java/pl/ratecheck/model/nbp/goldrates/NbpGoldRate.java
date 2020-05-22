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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NbpGoldRate other = (NbpGoldRate) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
