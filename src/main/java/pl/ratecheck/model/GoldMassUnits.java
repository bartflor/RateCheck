package pl.ratecheck.model;

public enum GoldMassUnits {
	GRAM("Gram","g", 1),
	OUNCE("Ounce", "oz", 28.3495);
	
	private final String name;
	private final String abbreviation;
	private final double converterToGram;
	
	private GoldMassUnits(String name, String abbreviation, double converterToGram) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.converterToGram = converterToGram;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public double getConverterToGram() {
		return converterToGram;
	}
	
	

}
