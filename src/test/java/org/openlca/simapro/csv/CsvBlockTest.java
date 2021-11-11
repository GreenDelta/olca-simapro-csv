package org.openlca.simapro.csv;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;
import org.openlca.simapro.csv.method.ImpactMethodBlock;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.CalculatedParameterBlock;
import org.openlca.simapro.csv.refdata.ElementaryFlowBlock;
import org.openlca.simapro.csv.refdata.InputParameterBlock;
import org.openlca.simapro.csv.refdata.QuantityBlock;
import org.openlca.simapro.csv.refdata.SystemDescriptionBlock;
import org.openlca.simapro.csv.refdata.UnitBlock;

public class CsvBlockTest {

  @Test
  public void testBlockConversions() {

    var a = CalculatedParameterBlock.forProject();
    assertTrue(a.isCalculatedParameterBlock());
    assertEquals(a, a.asCalculatedParameterBlock());

    var b = ElementaryFlowBlock.of(
      ElementaryFlowType.EMISSIONS_TO_AIR);
    assertTrue(b.isElementaryFlowBlock());
    assertEquals(b, b.asElementaryFlowBlock());

    var c = new ImpactMethodBlock();
    assertTrue(c.isImpactMethodBlock());
    assertEquals(c, c.asImpactMethodBlock());

    var d = InputParameterBlock.forDatabase();
    assertTrue(d.isInputParameterBlock());
    assertEquals(d, d.asInputParameterBlock());

    var e = new ProcessBlock();
    assertTrue(e.isProcessBlock());
    assertEquals(e, e.asProcessBlock());

    var f = new ProductStageBlock();
    assertTrue(f.isProductStageBlock());
    assertEquals(f, f.asProductStageBlock());

    var g = new QuantityBlock();
    assertTrue(g.isQuantityBlock());
    assertEquals(g, g.asQuantityBlock());

    var h = new SystemDescriptionBlock();
    assertTrue(h.isSystemDescriptionBlock());
    assertEquals(h, h.asSystemDescriptionBlock());

    var i = new UnitBlock();
    assertTrue(i.isUnitBlock());
    assertEquals(i, i.asUnitBlock());
  }


}
