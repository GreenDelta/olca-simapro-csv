package org.openlca.simapro.csv.enums;

/**
 * Allowed types of product flows in SimaPro. In a SimaPro CSV file, product
 * exchanges are divided into sections of these types where each section starts
 * with the CSV header indicating the type.
 */
public enum ProductType {

	AVOIDED_PRODUCTS("Avoided products"),

	ELECTRICITY_HEAT("Electricity/heat"),

	MATERIAL_FUELS("Materials/fuels"),

	WASTE_TO_TREATMENT("Waste to treatment");

	private final String header;

	ProductType(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public static ProductType forHeader(String header) {
		for (var type : values()) {
      if (type.getHeader().equalsIgnoreCase(header))
        return type;
    }
		return null;
	}

}
