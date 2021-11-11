package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class LiteratureReferenceBlockTest {

  @Test
  public void testLiteratureReferenceBlock() {

    var ds = Tests.testDataSet("literature.csv");
    assertEquals(1, ds.literatureReferences().size());
    var literature = ds.literatureReferences().get(0);
    assertEquals("US EPA (1995)", literature.name());
    assertEquals("http://www.epa.gov/ttnchie1/ap42/ch09/bgdocs/b9s13-2.pdf", literature.documentationLink());
    assertEquals("Others", literature.category());
    assertNotNull(literature.description());
  }

}
