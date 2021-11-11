package org.openlca.simapro.csv.enums;

/**
 * An enumeration of the possible elementary flow types in SimaPro. Not that the
 * SimaPro CSV format uses different names to identify these types in different
 * contexts (see {@link #exchangeHeader()}, {@link #blockHeader()}, and
 * {@link #compartment()}).
 */
public enum ElementaryFlowType {

  RESOURCES(
    "Resources", "Raw materials", "Raw"),

  EMISSIONS_TO_AIR(
    "Emissions to air", "Airborne emissions", "Air"),

  EMISSIONS_TO_WATER(
    "Emissions to water", "Waterborne emissions", "Water"),

  EMISSIONS_TO_SOIL(
    "Emissions to soil", "Emissions to soil", "Soil"),

  FINAL_WASTE_FLOWS(
    "Final waste flows", "Final waste flows", "Waste"),

  NON_MATERIAL_EMISSIONS(
    "Non material emissions", "Non material emissions", "Non mat."),

  SOCIAL_ISSUES(
    "Social issues", "Social issues", "Social"),

  ECONOMIC_ISSUES(
    "Economic issues", "Economic issues", "Economic");

  private final String exchangeHeader;
  private final String blockHeader;
  private final String compartment;

  ElementaryFlowType(
    String exchangeHeader, String blockHeader, String compartment) {
    this.exchangeHeader = exchangeHeader;
    this.blockHeader = blockHeader;
    this.compartment = compartment;
  }

  /**
   * The section header of the flow type for exchanges in a process.
   */
  public String exchangeHeader() {
    return exchangeHeader;
  }

  /**
   * The header of an elementary flow block of this type in a CSV file.
   */
  public String blockHeader() {
    return blockHeader;
  }

  /**
   * The compartment name of the flow type as used in LCIA characterization
   * factors.
   */
  public String compartment() {
    return compartment;
  }

  /**
   * Returns the elementary flow type for the given string value.
   *
   * @param value an exchange header, ref. data header, or compartment name that
   *              identifies the flow type.
   * @return the matching flow type or {@code null} if no such type could be
   * found
   */
  public static ElementaryFlowType of(String value) {
    if (value == null)
      return null;
    var s = value.trim();
    for (var t : values()) {
      if (s.equalsIgnoreCase(t.exchangeHeader)
        || s.equalsIgnoreCase(t.blockHeader)
        || s.equalsIgnoreCase(t.compartment))
        return t;
    }
    return null;
  }
}
