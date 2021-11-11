package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class SystemDescriptionBlockTest {

  @Test
  public void testSystemDescription() {

    var ds = Tests.testDataSet("process.csv");
    assertEquals(1, ds.systemDescriptions().size());
    var block = ds.systemDescriptions().get(0);
    assertEquals("system name", block.name());
    assertEquals("text for description", block.description());
    assertEquals("text for sub-systems", block.subSystems());
    assertEquals("text for cut-off rules", block.cutOffRules());
    assertEquals("text for energy model", block.energyModel());
    assertEquals("text for transport model", block.transportModel());
    assertEquals("text for waste model", block.wasteModel());
    assertEquals("text for other assumptions", block.otherAssumptions());
    assertEquals("text for other information", block.otherInformation());
    assertEquals("text for allocation rules", block.allocationRules());

  }

}
