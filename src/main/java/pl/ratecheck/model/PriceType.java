package pl.ratecheck.model;

public enum PriceType {
	ASK("Ask"),
	BID("Bid");
	
	private final String type;

	PriceType(String type){
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
