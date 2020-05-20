package pl.ratecheck.model.nbp.exchangerates;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import pl.ratecheck.model.Exchangerate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NbpExchangerate implements Exchangerate {
	@JsonProperty("effectiveDate")
	private LocalDate date;
	private double bid;
	private double ask;

	public NbpExchangerate() {
	}

	public NbpExchangerate(LocalDate date, double bid, double ask) {
		this.date = date;
		this.bid = bid;
		this.ask = ask;
	}

	@Override
	public LocalDate getDate() {
		return date;
	}

	@Override
	public void setDate(LocalDate date) {
		this.date = date;
	}

	@JsonSetter("effectiveDate")
	public void setDate(String date) {
		this.date = LocalDate.parse(date);
	}

	@Override
	public double getBid() {
		return bid;
	}

	@Override
	public void setBid(double bid) {
		this.bid = bid;
	}

	@Override
	public double getAsk() {
		return ask;
	}

	@Override
	public void setAsk(double ask) {
		this.ask = ask;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ask);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(bid);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		NbpExchangerate other = (NbpExchangerate) obj;
		if (Double.doubleToLongBits(ask) != Double.doubleToLongBits(other.ask))
			return false;
		if (Double.doubleToLongBits(bid) != Double.doubleToLongBits(other.bid))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	
	
}
