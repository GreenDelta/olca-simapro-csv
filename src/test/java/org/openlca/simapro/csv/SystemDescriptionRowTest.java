package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemDescriptionRowTest {
  @Test
  public void testSystemDescriptionRow() {
    var ds = Tests.testFile("process.csv");
    assertEquals(1, ds.systemDescriptions().size());
    var systemDescription = ds.systemDescriptions().get(0);
    assertEquals("system name", systemDescription.name());
    assertEquals("text for description", systemDescription.description());
    assertEquals("text for sub-systems", systemDescription.subSystems());
    assertEquals("text for cut-off rules", systemDescription.cutOffRules());
    assertEquals("text for energy model", systemDescription.energyModel());
    assertEquals("text for transport model", systemDescription.transportModel());
    assertEquals("text for waste model", systemDescription.wasteModel());
    assertEquals("text for other assumptions", systemDescription.otherAssumptions());
    assertEquals("text for other information", systemDescription.otherInformation());
    assertEquals("text for allocation rules", systemDescription.allocationRules());
  }
}
