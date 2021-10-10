package org.openlca.simapro.csv.enums;


public enum ProcessCategoryType {

	MATERIAL("material"),

	ENERGY("energy"),

	TRANSPORT("transport"),

	PROCESSING("processing"),

	USE("use"),

	WASTE_SCENARIO("waste scenario"),

	WASTE_TREATMENT("waste treatment");

	private final String value;

	ProcessCategoryType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ProcessCategoryType forValue(String value) {
		for (var category : values()) {
      if (category.getValue().equalsIgnoreCase((value)))
        return category;
    }
		return null;
	}
}
