package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ProcessCategory;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class WasteScenarioTest {

  private final CsvDataSet ds = Tests.testDataSet("waste_scenario.csv");

  @Test
  public void testWasteScenario() {
    var scenarios = ds.processes()
      .stream()
      .filter(p -> p.category() == ProcessCategory.WASTE_SCENARIO)
      .collect(Collectors.toList());

    assertEquals(1, scenarios.size());

    var scenario = scenarios.get(0);
    assertEquals("Dev99229000009369600014", scenario.identifier());

    var wasteScenario = scenario.wasteScenario();
    assertNotNull(wasteScenario);
    assertEquals("test waste scenario", wasteScenario.name());
    assertEquals("kg", wasteScenario.unit());
    assertEquals(1, wasteScenario.amount().value(), 1e-4);
    assertEquals("All waste types", wasteScenario.wasteType());
    assertEquals("Others", wasteScenario.category());

    var separatedWastes = scenario.separatedWaste();
    assertEquals(1, separatedWastes.size());
    var separatedWaste = separatedWastes.get(0);
    assertEquals("test waste treatment 1", separatedWaste.wasteTreatment());
    assertEquals("All waste types", separatedWaste.wasteType());
    assertEquals(90, separatedWaste.fraction(), 1e-4);

    var remainingWastes = scenario.remainingWaste();
    assertEquals(1, remainingWastes.size());
    var remainingWaste = remainingWastes.get(0);
    assertEquals("test waste treatment 2", remainingWaste.wasteTreatment());
    assertEquals("", remainingWaste.wasteType());
    assertEquals(100, remainingWaste.fraction(), 1e-4);

  }

  @Test
  public void testWasteTreatment() {
    var treatments = ds.processes()
      .stream()
      .filter(p -> p.category() == ProcessCategory.WASTE_TREATMENT)
      .collect(Collectors.toList());

    assertEquals(2, treatments.size());

    var treatment = treatments
      .stream()
      .filter(p -> p.identifier().equals("Dev99229000009369600013"))
      .collect(Collectors.toList())
      .get(0);

    assertNotNull(treatment);

    var wasteTreatment = treatment.wasteTreatment();
    assertNotNull(wasteTreatment);
    assertEquals("test waste treatment 1", wasteTreatment.name());
    assertEquals("kg", wasteTreatment.unit());
    assertEquals(1, wasteTreatment.amount().value(), 1e-4);
    assertEquals("All waste types", wasteTreatment.wasteType());
    assertEquals("Others", wasteTreatment.category());
  }
}
