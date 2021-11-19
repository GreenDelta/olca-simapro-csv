package org.openlca.simapro.csv.enums;

public enum Status {

  NONE(""),

  TEMPORARY("Temporary"),

  DRAFT("Draft"),

  TO_BE_REVISED("To be revised"),

  TO_BE_REVIEWED("To be reviewed"),

  FINISHED("Finished");


  private final String value;

  Status(String value) { this.value = value; }

  public static Status of(String value) {
    for (var type : values()) {
      if (type.value.equalsIgnoreCase(value))
        return type;
    }
    return NONE;
  }

  @Override
  public String toString() {
    return value;
  }
}
