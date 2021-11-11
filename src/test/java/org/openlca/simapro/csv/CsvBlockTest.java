package org.openlca.simapro.csv;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;
import org.openlca.simapro.csv.enums.ProductStageCategory;
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

  @Test
  public void testFindProcessBlock() {
    var found = new AtomicInteger(0);
    Tests.withTestFile("process.csv", file ->
      SimaProCsv.read(file, block -> {
        if (block.isProcessBlock()) {
          var b = block.asProcessBlock();
          assertEquals("DefaultX25250700002", b.identifier());
          found.incrementAndGet();
        }
      }));
    assertEquals(1, found.get());
    checkCount("method.csv", CsvBlock::isProcessBlock, 0);
  }

  @Test
  public void testFindCalculatedParameterBlock() {
    checkCount("process.csv", CsvBlock::isCalculatedParameterBlock, 2);
  }

  @Test
  public void testFindElementaryFlowBlock() {
    checkCount(
      "process.csv",
      CsvBlock::isElementaryFlowBlock,
      ElementaryFlowType.values().length);
  }

  @Test
  public void testFindImpactMethodBlock() {
    checkCount("method.csv", CsvBlock::isImpactMethodBlock, 1);
    checkCount("process.csv", CsvBlock::isImpactMethodBlock, 0);
  }

  @Test
  public void testFindInputParameterBlock() {
    checkCount("process.csv", CsvBlock::isInputParameterBlock, 2);
  }

  @Test
  public void testFindProductStageBlock() {
    checkCount("process.csv", CsvBlock::isProductStageBlock, 0);
    checkCount("method.csv", CsvBlock::isProductStageBlock, 0);
    checkCount(
      "stages.csv",
      CsvBlock::isProductStageBlock,
      ProductStageCategory.values().length);
  }

  @Test
  public void testFindQuantityBlock() {
    checkCount("process.csv", CsvBlock::isQuantityBlock, 1);
  }

  @Test
  public void testFindSystemDescriptionBlock() {
    checkCount("process.csv", CsvBlock::isSystemDescriptionBlock, 1);
  }

  @Test
  public void testFindUnitBlock() {
    checkCount("process.csv", CsvBlock::isUnitBlock, 1);
  }

  private void checkCount(String file, Predicate<CsvBlock> p, int expected) {
    var count = new AtomicInteger(0);
    Tests.withTestFile(file, f ->
      SimaProCsv.read(f, block -> {
        if (p.test(block)) {
          count.incrementAndGet();
        }
      }));
    assertEquals(expected, count.get());
  }

}
