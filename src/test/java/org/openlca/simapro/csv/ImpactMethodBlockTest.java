package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImpactMethodBlockTest {

  @Test
  public void testImpactMethod() {
    var ds = Tests.testFile("method.csv");
    assertEquals(1, ds.methods().size());
  }

}
