package pl.ratecheck.model.nbp.exchangerates;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import pl.ratecheck.model.Exchangerate;
@JsonIgnoreProperties(ignoreUnknown=true)
public class NbpExchangerate implements Exchangerate {
	@JsonProperty("effectiveDate")
	public LocalDate date;
	public double bid;
	public double ask;


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
}
