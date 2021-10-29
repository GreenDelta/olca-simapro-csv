package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

import static org.junit.Assert.*;

public class ElementaryFlowBlockTest {

  @Test
  public void testElementaryFlows() {
    var ds = Tests.testFile("process.csv");
    for (var type : ElementaryFlowType.values()) {
      var flows = ds.getElementaryFlows(type);
      assertEquals(1, flows.size());
      // TODO: @mg test flow values
    }
  }

}
