package org.openlca.simapro.csv.enums;


public enum ProcessCategory {

	MATERIAL("material"),

	ENERGY("energy"),

	TRANSPORT("transport"),

	PROCESSING("processing"),

	USE("use"),

	WASTE_SCENARIO("waste scenario"),

	WASTE_TREATMENT("waste treatment");

	private final String value;

	ProcessCategory(String value) {
		this.value = value;
	}

	public static ProcessCategory of(String value) {
		for (var category : values()) {
      if (category.value.equalsIgnoreCase((value)))
        return category;
    }
		return MATERIAL;
	}

  @Override
  public String toString() {
    return value;
  }
}
