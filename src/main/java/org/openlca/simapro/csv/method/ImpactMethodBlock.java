package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvLine;

public class ImpactMethodBlock {

  private String name;
  private VersionRow version;
  private String comment;
  private String category;
  private boolean useDamageAssessment;
  private boolean useNormalization;
  private boolean useWeighting;
  private boolean useAddition;
  private String weightingUnit;

  private final List<ImpactCategoryBlock> impactCategories = new ArrayList<>();
  private final List<DamageCategoryBlock> damageCategories = new ArrayList<>();
  private final List<NwSetBlock> nwSets = new ArrayList<>();

  public String name() {
    return name;
  }

  public ImpactMethodBlock name(String name) {
    this.name = name;
    return this;
  }

  public VersionRow version() {
    return version;
  }

  public ImpactMethodBlock version(VersionRow version) {
    this.version = version;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ImpactMethodBlock comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String category() {
    return category;
  }

  public ImpactMethodBlock category(String category) {
    this.category = category;
    return this;
  }

  public boolean useDamageAssessment() {
    return useDamageAssessment;
  }

  public ImpactMethodBlock useDamageAssessment(boolean useDamageAssessment) {
    this.useDamageAssessment = useDamageAssessment;
    return this;
  }

  public boolean useNormalization() {
    return useNormalization;
  }

  public ImpactMethodBlock useNormalization(boolean useNormalization) {
    this.useNormalization = useNormalization;
    return this;
  }

  public boolean useWeighting() {
    return useWeighting;
  }

  public ImpactMethodBlock useWeighting(boolean useWeighting) {
    this.useWeighting = useWeighting;
    return this;
  }

  public boolean useAddition() {
    return useAddition;
  }

  public ImpactMethodBlock useAddition(boolean useAddition) {
    this.useAddition = useAddition;
    return this;
  }

  public String weightingUnit() {
    return weightingUnit;
  }

  public ImpactMethodBlock weightingUnit(String weightingUnit) {
    this.weightingUnit = weightingUnit;
    return this;
  }

  public List<ImpactCategoryBlock> impactCategories() {
    return impactCategories;
  }

  public List<DamageCategoryBlock> damageCategories() {
    return damageCategories;
  }

  public List<NwSetBlock> nwSets() {
    return nwSets;
  }

  public static ImpactMethodBlock read(Iterable<CsvLine> lines) {
    var iter = lines.iterator();
    var method = new ImpactMethodBlock();

    while (iter.hasNext()) {

      var next = iter.next();
      if (next.isEmpty())
        continue;
      var header = next.first();
      if (header.equalsIgnoreCase("End"))
        break;

      switch (header) {

        case "Name":
          method.name(CsvLine.nextString(iter));
          break;

        case "Version":
          CsvLine.nextOf(iter).ifPresent(
            nextLine -> method.version(VersionRow.read(nextLine)));
          break;

        case "Comment":
          method.comment(CsvLine.nextString(iter));
          break;

        case "Category":
          method.category(CsvLine.nextString(iter));
          break;

        case "Use Damage Assessment":
          method.useDamageAssessment(CsvLine.nextBool(iter));
          break;

        case "Use Normalization":
          method.useNormalization(CsvLine.nextBool(iter));
          break;

        case "Use Weighting":
          method.useWeighting(CsvLine.nextBool(iter));
          break;

        case "Use Addition":
          method.useAddition(CsvLine.nextBool(iter));
          break;

        case "Weighting unit":
          method.weightingUnit(CsvLine.nextString(iter));
          break;

        case "Impact category":
          var impactCategory = new ImpactCategoryBlock();
          impactCategory.name(CsvLine.nextString(iter));
          CsvLine.moveTo(iter, "Substances");
          CsvLine.untilEmpty(iter, nextLine -> {
            var factor = ImpactFactorRow.read(nextLine);
            impactCategory.factors().add(factor);
          });
          method.impactCategories().add(impactCategory);
          break;

        case "Damage category":
          // TODO: read a damage category block
          break;

        case "Normalization-Weighting set":
          // TODO: read a nw-set block
          break;
      }
    }
    return method;
  }

}
