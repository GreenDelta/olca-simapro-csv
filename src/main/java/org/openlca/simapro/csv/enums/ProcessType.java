package org.openlca.simapro.csv.enums;

public enum ProcessType {

	SYSTEM("System"),

	UNIT_PROCESS("Unit process");

	private final String value;

	ProcessType(String value) {
		this.value = value;
	}

	public static ProcessType of(String value) {
		for (ProcessType type : values())
			if (type.value.equals(value))
				return type;
		return UNIT_PROCESS;
	}

  @Override
  public String toString() {
    return value;
  }
}
