package org.openlca.simapro.csv.enums;

public enum SubCompartment {

  // emissions to air
  AIR_HIGH_POP(
    ElementaryFlowType.EMISSIONS_TO_AIR, "high. pop."),
  AIR_INDOOR(
    ElementaryFlowType.EMISSIONS_TO_AIR, "indoor"),
  AIR_LOW_POP(
    ElementaryFlowType.EMISSIONS_TO_AIR, "low. pop."),
  AIR_LOW_POP_LONG_TERM(
    ElementaryFlowType.EMISSIONS_TO_AIR, "low. pop., long-term"),
  AIR_STRATOSPHERE(
    ElementaryFlowType.EMISSIONS_TO_AIR, "stratosphere"),
  AIR_STRATOSPHERE_TROPOSPHERE(
    ElementaryFlowType.EMISSIONS_TO_AIR, "stratosphere + troposphere"),

  // resources
  RESOURCES_BIOTIC(
    ElementaryFlowType.RESOURCES, "biotic"),
  RESOURCES_IN_AIR(
    ElementaryFlowType.RESOURCES, "in air"),
  RESOURCES_IN_GROUND(
    ElementaryFlowType.RESOURCES, "in ground"),
  RESOURCES_IN_WATER(
    ElementaryFlowType.RESOURCES, "in water"),
  RESOURCES_LAND(
    ElementaryFlowType.RESOURCES, "land"),

  // emissions to soil
  SOIL_AGRICULTURAL(
    ElementaryFlowType.EMISSIONS_TO_SOIL, "agricultural"),
  SOIL_FORESTRY(
    ElementaryFlowType.EMISSIONS_TO_SOIL, "forestry"),
  SOIL_INDUSTRIAL(
    ElementaryFlowType.EMISSIONS_TO_SOIL, "industrial"),
  SOIL_URBAN(
    ElementaryFlowType.EMISSIONS_TO_SOIL, "urban, non industrial"),

  // emissions to water
  WATER_FOSSIL(
    ElementaryFlowType.EMISSIONS_TO_WATER, "fossilwater"),
  WATER_GROUND(
    ElementaryFlowType.EMISSIONS_TO_WATER, "groundwater"),
  WATER_GROUND_LONG_TERM(
    ElementaryFlowType.EMISSIONS_TO_WATER, "groundwater, long-term"),
  WATER_LAKE(
    ElementaryFlowType.EMISSIONS_TO_WATER, "lake"),
  WATER_OCEAN(
    ElementaryFlowType.EMISSIONS_TO_WATER, "ocean"),
  WATER_RIVER(
    ElementaryFlowType.EMISSIONS_TO_WATER, "river"),
  WATER_RIVER_LONG_TERM(
    ElementaryFlowType.EMISSIONS_TO_WATER, "river, long-term"),

  UNSPECIFIED(null, "");

  private final ElementaryFlowType flowType;
  private final String value;

  SubCompartment(ElementaryFlowType type, String value) {
    this.value = value;
    this.flowType = type;
  }

  public ElementaryFlowType flowType() {
    return flowType;
  }

  @Override
  public String toString() {
    return value;
  }

  public static SubCompartment of(String value) {
    for (var sub : values()) {
      if (sub.value.equals(value))
        return sub;
    }
    return UNSPECIFIED;
  }

}
