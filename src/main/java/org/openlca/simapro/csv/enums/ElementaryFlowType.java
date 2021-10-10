package org.openlca.simapro.csv.enums;

/**
 * Allowed types of elementary flows in SimaPro. Elementary flows are written to
 * the elementary exchange sections of processes and reference flow sections at
 * the end of a SimaPro CSV file. These sections start with an header which
 * indicates the elementary flow type. For some of these flow types these
 * headers are different in the elementary exchange sections and reference flow
 * sections. Thus, this enumeration has two header values for each entry: the
 * exchange header and the reference flow header.
 */
public enum ElementaryFlowType {

	RESOURCES("Resources", "Raw materials"),

	EMISSIONS_TO_AIR("Emissions to air", "Airborne emissions"),

	EMISSIONS_TO_WATER("Emissions to water", "Waterborne emissions"),

	EMISSIONS_TO_SOIL("Emissions to soil", "Emissions to soil"),

	FINAL_WASTE_FLOWS("Final waste flows", "Final waste flows"),

	NON_MATERIAL_EMISSIONS("Non material emissions", "Non material emissions"),

	SOCIAL_ISSUES("Social issues", "Social issues"),

	ECONOMIC_ISSUES("Economic issues", "Economic issues");

	private final String exchangeHeader;
	private final String referenceHeader;

	ElementaryFlowType(String exchangeHeader, String referenceHeader) {
		this.exchangeHeader = exchangeHeader;
		this.referenceHeader = referenceHeader;
	}

	public String exchangeHeader() {
		return exchangeHeader;
	}

	public String referenceHeader() {
		return referenceHeader;
	}

	public static ElementaryFlowType forExchangeHeader(String header) {
		for (var type : values()) {
			if (type.exchangeHeader().equalsIgnoreCase(header))
				return type;
		}
		return null;
	}

	public static ElementaryFlowType forReferenceHeader(String header) {
		for (var type : values()) {
			if (type.referenceHeader().equalsIgnoreCase(header))
				return type;
		}
		return null;
	}

}
