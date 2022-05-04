package org.openlca.simapro.csv;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

public class MethodEndTest {

  @Test
  public void testBlocksAfterMethod() {
    var ds = Tests.testDataSet("method_end.csv");
    assertEquals(3, ds.units().size());
    assertEquals(2, ds.quantities().size());
    var noiseFlows = ds.getElementaryFlows(
      ElementaryFlowType.NON_MATERIAL_EMISSIONS);
    assertEquals(6, noiseFlows.size());
  }

}
