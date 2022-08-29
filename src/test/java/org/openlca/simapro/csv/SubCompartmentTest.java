package org.openlca.simapro.csv;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.simapro.csv.enums.SubCompartment;

public class SubCompartmentTest {

  @Test
  public void testFromString() {

    assertEquals(
      SubCompartment.UNSPECIFIED,
      SubCompartment.of("(unspecified)"));
    assertEquals(
      SubCompartment.SOIL_AGRICULTURAL,
      SubCompartment.of("agricultural"));
    assertEquals(
      SubCompartment.WATER_OCEAN,
      SubCompartment.of("ocean"));
    assertEquals(
      SubCompartment.SOIL_INDUSTRIAL,
      SubCompartment.of("industrial"));
    assertEquals(
      SubCompartment.RESOURCES_IN_WATER,
      SubCompartment.of("in water"));
    assertEquals(
      SubCompartment.AIR_STRATOSPHERE_TROPOSPHERE,
      SubCompartment.of("stratosphere + troposphere"));
    assertEquals(
      SubCompartment.AIR_LOW_POP,
      SubCompartment.of("low. pop."));
    assertEquals(
      SubCompartment.AIR_LOW_POP_LONG_TERM,
      SubCompartment.of("low. pop., long-term"));
    assertEquals(
      SubCompartment.AIR_HIGH_POP,
      SubCompartment.of("high. pop."));
    assertEquals(
      SubCompartment.WATER_GROUND,
      SubCompartment.of("groundwater"));
    assertEquals(
      SubCompartment.WATER_GROUND_LONG_TERM,
      SubCompartment.of("groundwater, long-term"));
    assertEquals(
      SubCompartment.RESOURCES_BIOTIC,
      SubCompartment.of("biotic"));

  }

}
