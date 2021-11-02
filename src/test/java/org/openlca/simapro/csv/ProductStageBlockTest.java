package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ProductStageCategory;
import org.openlca.simapro.csv.process.ProductStageOutputRow;
import org.openlca.simapro.csv.process.TechExchangeRow;

import java.util.List;

import static org.junit.Assert.*;

public class ProductStageBlockTest {
  CsvDataSet ds = Tests.testFile("stages.csv");

  @Test
  public void testProductStagBlock() {
    assertEquals(5, ds.productStages().size());
    boolean found = false;
    for (var productStage : ds.productStages()) {
      if (productStage.category() == ProductStageCategory.ASSEMBLY) {
        found = true;
        assertEquals("", productStage.status());

        var products = productStage.products();
        assertEquals(1, products.size());
        assertContains(products, new ProductStageOutputRow()
          .name("assembly")
          .amount(Numeric.of(1))
          .category("Others")
          .unit("p")
          .comment(""));

        var materials = productStage.materialsAndAssemblies();
        assertEquals(2, materials.size());
        assertContains(materials, new TechExchangeRow()
          .name("material 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

        var processes = productStage.processes();
        assertEquals(4, processes.size());
        assertContains(processes, new TechExchangeRow()
          .name("energy 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

        var inputParameters = productStage.inputParameters();
        assertEquals(1, inputParameters.size());
        var inputParameter = inputParameters.get(0);
        assertEquals("param1", inputParameter.name());
        assertEquals(1, inputParameter.value(), 0.0001);
        assertTrue(inputParameter.uncertainty().isUndefined());
        assertFalse(inputParameter.isHidden());
        assertEquals("", inputParameter.comment());

        var calculatedParameters = productStage.calculatedParameters();
        assertEquals(1, calculatedParameters.size());
        var calculatedParameter = calculatedParameters.get(0);
        assertEquals("param2", calculatedParameter.name());
        assertEquals("param1 *2", calculatedParameter.expression());
        assertEquals("", calculatedParameter.comment());

      }
      if (productStage.category() == ProductStageCategory.DISPOSAL_SCENARIO) {
        found = true;
        assertEquals("", productStage.status());

        var products = productStage.products();
        assertEquals(1, products.size());
        assertContains(products, new ProductStageOutputRow()
          .name("displosal scenario")
          .amount(Numeric.of(1))
          .category("Others")
          .unit("p")
          .comment(""));

        var referenceAssembly = productStage.referenceAssembly();
        assertEquals("assembly", referenceAssembly.name());
        assertEquals(1, referenceAssembly.amount().value(), 0.0001);
        assertEquals("p", referenceAssembly.unit());
        assertTrue(referenceAssembly.uncertainty().isUndefined());

        var processes = productStage.processes();
        assertEquals(4, processes.size());
        assertContains(processes, new TechExchangeRow()
          .name("transport 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

        var wasteScenarios = productStage.wasteScenarios();
        assertEquals(1, wasteScenarios.size());
        assertContains(wasteScenarios, new TechExchangeRow()
          .name("waste scenario 1")
          .amount(Numeric.of(100))
          .unit("%")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

      }
      if (productStage.category() == ProductStageCategory.DISASSEMBLY) {
        found = true;
        assertEquals("", productStage.status());

        var products = productStage.products();
        assertEquals(1, products.size());
        assertContains(products, new ProductStageOutputRow()
          .name("dissassembly 1")
          .amount(Numeric.of(1))
          .category("Others")
          .unit("p")
          .comment(""));

        var referenceAssembly = productStage.referenceAssembly();
        assertEquals("assembly", referenceAssembly.name());
        assertEquals(0, referenceAssembly.amount().value(), 0.0001);
        assertEquals("p", referenceAssembly.unit());
        assertTrue(referenceAssembly.uncertainty().isUndefined());

        var processes = productStage.processes();
        assertEquals(4, processes.size());
        assertContains(processes, new TechExchangeRow()
          .name("processing 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

        var wasteScenarios = productStage.wasteScenarios();
        assertEquals(1, wasteScenarios.size());
        assertContains(wasteScenarios, new TechExchangeRow()
          .name("waste scenario 1")
          .amount(Numeric.of(100))
          .unit("%")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

      }
      if (productStage.category() == ProductStageCategory.LIFE_CYCLE) {
        found = true;
        assertEquals("", productStage.status());

        var products = productStage.products();
        assertEquals(1, products.size());
        assertContains(products, new ProductStageOutputRow()
          .name("life cycle 1")
          .amount(Numeric.of(1))
          .category("Others")
          .unit("p")
          .comment(""));

        var referenceAssembly = productStage.assembly();
        assertEquals(1, referenceAssembly.amount().value(), 0.0001);
        assertEquals("p", referenceAssembly.unit());
        assertTrue(referenceAssembly.uncertainty().isUndefined());

        var processes = productStage.processes();
        assertEquals(4, processes.size());
        assertContains(processes, new TechExchangeRow()
          .name("use 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));

        var wasteScenario = productStage.wasteOrDisposalScenario();
        assertEquals("waste scenario 1", wasteScenario.name());
        assertEquals(0, wasteScenario.amount().value(), 0.0001);
        assertEquals("", wasteScenario.unit());
        assertTrue(wasteScenario.uncertainty().isUndefined());

        var additionalLifeCycles = productStage.additionalLifeCycles();
        assertEquals(1, additionalLifeCycles.size());
        assertContains(additionalLifeCycles, new TechExchangeRow()
          .name("life cycle 1")
          .amount(Numeric.of(1))
          .unit("p")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));
      }
      if (productStage.category() == ProductStageCategory.REUSE) {
        found = true;
        assertEquals("", productStage.status());

        var products = productStage.products();
        assertEquals(1, products.size());
        assertContains(products, new ProductStageOutputRow()
          .name("reuse 1")
          .amount(Numeric.of(1))
          .category("Others")
          .unit("p")
          .comment(""));

        var referenceAssembly = productStage.referenceAssembly();
        assertEquals("assembly", referenceAssembly.name());
        assertEquals(1, referenceAssembly.amount().value(), 0.0001);
        assertEquals("p", referenceAssembly.unit());
        assertTrue(referenceAssembly.uncertainty().isUndefined());

        var processes = productStage.processes();
        assertEquals(4, processes.size());
        assertContains(processes, new TechExchangeRow()
          .name("processing 1")
          .amount(Numeric.of(1))
          .unit("kg")
          .uncertainty(UncertaintyRecord.undefined())
          .comment(""));
      }
      assertTrue(found);
      found = false;
    }
  }

  private void assertContains(List<ProductStageOutputRow> products, ProductStageOutputRow expected) {
    boolean found = false;
    for (var product : products) {
      if (product.name().equals(expected.name())) {
        found = true;
        assertEquals(expected.amount().value(), product.amount().value(), 0.0001);
        assertEquals(expected.category(), product.category());
        assertEquals(expected.unit(), product.unit());
        assertEquals(expected.comment(), product.comment());
      }
    }
    assertTrue(found);
  }

  private void assertContains(List<TechExchangeRow> materials, TechExchangeRow expected) {
    boolean found = false;
    for (var material : materials) {
      if (material.name().equals(expected.name())) {
        found = true;
        assertEquals(expected.amount().value(), material.amount().value(), 0.0001);
        assertEquals(expected.unit(), material.unit());
        assertEquals(expected.uncertainty(), material.uncertainty());
        assertEquals(expected.comment(), material.comment());
      }
    }
    assertTrue(found);
  }
}
