package org.openlca.simapro.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

public class MethodEndTest {

  @Test
  public void testBlockCount() {
    var ds = Tests.testDataSet("method_end.csv");
    assertEquals(1, ds.methods().size());
    assertEquals(3, ds.units().size());
    assertEquals(2, ds.quantities().size());
    var noiseFlows = ds.getElementaryFlows(
      ElementaryFlowType.NON_MATERIAL_EMISSIONS);
    assertEquals(6, noiseFlows.size());
  }

  @Test
  public void testNwSets() {
    var ds = Tests.testDataSet("method_end.csv");
    var method = ds.methods().get(0);
    var nwSets = method.nwSets();
    assertEquals(2, nwSets.size());
  }

}
