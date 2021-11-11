package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.method.*;

import java.util.List;

import static org.junit.Assert.*;

public class ImpactMethodBlockTest {

  private final CsvDataSet ds = Tests.testDataSet("method.csv");

  @Test
  public void testImpactMethod() {
    assertEquals(1, ds.methods().size());
    var method = ds.methods().get(0);
    assertEquals("IMPACTWorld+ (Continental Latin America_Damage 1.431)", method.name());
    assertEquals(1, method.version().major());
    assertEquals(431, method.version().minor());
    assertEquals("Others", method.category());
    assertTrue(method.useDamageAssessment());
    assertTrue(method.useNormalization());
    assertTrue(method.useWeighting());
    assertTrue(method.useAddition());
    assertEquals("EUR2003", method.weightingUnit());
  }

  @Test
  public void testImpactCategories() {
    var method = ds.methods().get(0);
    var impactCategories = method.impactCategories();
    assertEquals(27, impactCategories.size());
    assertContains(impactCategories, new ImpactCategoryRow()
      .name("Climate change, ecosystem quality, short term")
      .unit("PDF.m2.yr"));
    assertContains(impactCategories, new ImpactCategoryRow()
      .name("Marine acidification, long term")
      .unit("PDF.m2.yr"));
    assertContains(impactCategories, new ImpactCategoryRow()
      .name("Water availability, human health")
      .unit("DALY"));
    assertContains(impactCategories, new ImpactCategoryRow()
      .name("Ozone layer depletion")
      .unit("DALY"));
  }

  @Test
  public void testImpactFactors() {
    var climateChangeLT = getImpactCategory(
      "Climate change, ecosystem quality, long term");
    assertContains(climateChangeLT.factors(), new ImpactFactorRow()
      .flow("(E)-HFC-1234ze")
      .casNumber("1645-83-6")
      .compartment("Air")
      .subCompartment("(unspecified)")
      .factor(0.0421)
      .unit("kg"));

    var landTransformationBio = getImpactCategory(
      "Land transformation, biodiversity");
    assertContains(landTransformationBio.factors(), new ImpactFactorRow()
      .flow("Transformation, to  permanent crop, irrigated, GLO")
      .casNumber("")
      .compartment("Raw")
      .subCompartment("land")
      .factor(13)
      .unit("m2"));

    var ionizingRadiation = getImpactCategory(
      "Ionizing radiation, human health");
    assertContains(ionizingRadiation.factors(), new ImpactFactorRow()
      .flow("Antimony-124")
      .casNumber("014683-10-4")
      .compartment("Water")
      .subCompartment("ocean")
      .factor(0)
      .unit("Bq"));
  }

  @Test
  public void testDamageCategories() {
    var method = ds.methods().get(0);
    var damageCategories = method.damageCategories();
    assertEquals(2, damageCategories.size());
    assertContains(damageCategories, new DamageCategoryRow()
      .name("Human health")
      .unit("DALY"));
    assertContains(damageCategories, new DamageCategoryRow()
      .name("Ecosystem quality")
      .unit("PDF.m2.yr"));
  }

  @Test
  public void testDamageCategoryFactors() {
    var humanHealth = getDamageCategory("Human health");
    assertEquals(humanHealth.factors().size(), 11);
    assertContains(humanHealth.factors(), new DamageFactorRow()
      .impactCategory("Human toxicity cancer, short term")
      .factor(1));
    assertContains(humanHealth.factors(), new DamageFactorRow()
      .impactCategory("Ozone layer depletion")
      .factor(1));
    assertContains(humanHealth.factors(), new DamageFactorRow()
      .impactCategory("Photochemical oxidant formation")
      .factor(1));

    var ecosystemQuality = getDamageCategory("Ecosystem quality");
    assertEquals(ecosystemQuality.factors().size(), 16);
    assertContains(ecosystemQuality.factors(), new DamageFactorRow()
      .impactCategory("Freshwater eutrophication")
      .factor(1));
    assertContains(ecosystemQuality.factors(), new DamageFactorRow()
      .impactCategory("Thermally polluted water")
      .factor(1));
    assertContains(ecosystemQuality.factors(), new DamageFactorRow()
      .impactCategory("Water availability, terrestrial ecosystem")
      .factor(1));
  }

  @Test
  public void testNWSets() {
    var method = ds.methods().get(0);
    var nwSets = method.nwSets();
    assertEquals(1, nwSets.size());
    var nwSet = nwSets.get(0);
    assertEquals("IMPACT World+ (Stepwise 2006 values)", nwSet.name());
    assertContains(nwSet.normalizationFactors(), new NwSetFactorRow()
      .impactCategory("Ecosystem quality")
      .factor(1.01E-04));
    assertContains(nwSet.weightingFactors(), new NwSetFactorRow()
      .impactCategory("Human health")
      .factor(5401.459854));
  }

  private ImpactCategoryBlock getImpactCategory(String name) {
    return ds.methods().get(0).impactCategories().stream()
      .filter(cat -> cat.info().name().equals(name))
      .findFirst()
      .orElseThrow();
  }

  private DamageCategoryBlock getDamageCategory(String name) {
    return ds.methods().get(0).damageCategories().stream()
      .filter(cat -> cat.info().name().equals(name))
      .findFirst()
      .orElseThrow();
  }

  private void assertContains(
    List<ImpactCategoryBlock> impactCategories, ImpactCategoryRow expected) {
    boolean found = false;
    for (var impactCategoryBlock : impactCategories) {
      var impactCategory = impactCategoryBlock.info();
      if (impactCategory.name().equals(expected.name())) {
        found = true;
        assertEquals(expected.unit(), impactCategory.unit());
      }
    }
    assertTrue(found);
  }

  private void assertContains(
    List<ImpactFactorRow> impactFactors, ImpactFactorRow expected) {
    boolean found = false;
    for (var impactFactor : impactFactors) {
      if (impactFactor.flow().equals(expected.flow()) &&
          impactFactor.subCompartment().equals(expected.subCompartment())) {
        found = true;
        assertEquals(expected.unit(), impactFactor.unit());
        assertEquals(expected.casNumber(), impactFactor.casNumber());
        assertEquals(expected.compartment(), impactFactor.compartment());
        assertEquals(expected.factor(), impactFactor.factor(), 1e-6);
        assertEquals(expected.unit(), impactFactor.unit());
      }
    }
    assertTrue(found);
  }

  private void assertContains(
    List<DamageCategoryBlock> damageCategories, DamageCategoryRow expected) {
    boolean found = false;
    for (var damageCategoryBlock : damageCategories) {
      var damageCategory = damageCategoryBlock.info();
      if (damageCategory.name().equals(expected.name())) {
        found = true;
        assertEquals(expected.unit(), damageCategory.unit());
      }
    }
    assertTrue(found);
  }

  private void assertContains(
    List<DamageFactorRow> damageFactors, DamageFactorRow expected) {
    boolean found = false;
    for (var damageFactor : damageFactors) {
      if (damageFactor.impactCategory().equals(expected.impactCategory())) {
        found = true;
        assertEquals(expected.factor(), damageFactor.factor(), 1e-6);
      }
    }
    assertTrue(found);
  }

  private void assertContains(
    List<NwSetFactorRow> NwSetFactors, NwSetFactorRow expected) {
    boolean found = false;
    for (var NwSetFactor : NwSetFactors) {
      if (NwSetFactor.impactCategory().equals(expected.impactCategory())) {
        found = true;
        assertEquals(expected.factor(), NwSetFactor.factor(), 1e-6);
      }
    }
    assertTrue(found);
  }

}
