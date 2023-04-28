package org.openlca.simapro.csv.method;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;

public class ImpactMethodBlock implements CsvBlock {

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
    if (version == null) {
      version = new VersionRow();
    }
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
    String innerHeader = null;

    while (iter.hasNext()) {

      String header;
      if (innerHeader != null) {
        header = innerHeader;
      } else {
        var next = iter.next();
        if (next.isEmpty())
          continue;
        header = next.first();
      }
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
          CsvLine.nextOf(iter).ifPresent(nextLine ->
            impactCategory.info(ImpactCategoryRow.read(nextLine)));
          CsvLine.moveTo(iter, "Substances");
          CsvLine.untilEmpty(iter, nextLine -> {
            var factor = ImpactFactorRow.read(nextLine);
            impactCategory.factors().add(factor);
          });
          method.impactCategories().add(impactCategory);
          break;

        case "Damage category":
          var damageCategory = new DamageCategoryBlock();
          CsvLine.nextOf(iter).ifPresent(nextLine ->
            damageCategory.info(DamageCategoryRow.read(nextLine)));
          CsvLine.moveTo(iter, "Impact categories");
          CsvLine.untilEmpty(iter, nextLine -> {
            var factor = DamageFactorRow.read(nextLine);
            damageCategory.factors().add(factor);
          });
          method.damageCategories().add(damageCategory);
          break;

        case "Normalization-Weighting set":
          var nwSet = new NwSetBlock();
          nwSet.name(CsvLine.nextString(iter));
          method.nwSets().add(nwSet);

          while (iter.hasNext()) {
            var nwRow = iter.next();
            if (nwRow.isEmpty())
              continue;
            var nwHeader = nwRow.first();
            if ("Normalization".equals(nwHeader)) {
              CsvLine.untilEmpty(iter, nextLine -> {
                var factor = NwSetFactorRow.read(nextLine);
                nwSet.normalizationFactors().add(factor);
              });
            } else if ("Weighting".equals(nwHeader)) {
              CsvLine.untilEmpty(iter, nextLine -> {
                var factor = NwSetFactorRow.read(nextLine);
                nwSet.weightingFactors().add(factor);
              });
            } else {
              innerHeader = nwHeader;
              break; // while
            }
          }
          break; // case
      }
    }
    return method;
  }

  @Override
  public void write(CsvBuffer buffer) {

    buffer.putString("Method").writeln()
      .writeln();

    // Name
    buffer.putString("Name").writeln()
      .putString(name()).writeln()
      .writeln();

    // Version
    buffer.putString("Version").writeln()
      .putRecord(version())
      .writeln();

    // Comment
    buffer.putString("Comment").writeln()
      .putString(comment()).writeln()
      .writeln();

    // Category
    var c = category();
    if (c == null || c.isEmpty()) {
      c = "Others";
    }
    buffer.putString("Category").writeln()
      .putString(c).writeln()
      .writeln();

    // Use Damage Assessment
    buffer.putString("Use Damage Assessment").writeln()
      .putBoolean(useDamageAssessment()).writeln()
      .writeln();

    // Use Normalization
    buffer.putString("Use Normalization").writeln()
      .putBoolean(useNormalization()).writeln()
      .writeln();

    // Use Weighting
    buffer.putString("Use Weighting").writeln()
      .putBoolean(useWeighting()).writeln()
      .writeln();

    // Use Addition
    buffer.putString("Use Addition").writeln()
      .putBoolean(useAddition()).writeln()
      .writeln();

    // Weighting unit
    if (weightingUnit() != null && !weightingUnit().isBlank()) {
      buffer.putString("Weighting unit").writeln()
        .putString(weightingUnit()).writeln()
        .writeln();
    }

    // Impact categories
    buffer.putRecords(impactCategories())
      .writeln();

    // Damage categories
    buffer.putRecords(damageCategories())
      .writeln();

    // Normalization-weighting sets
    buffer.putRecords(nwSets())
      .writeln();

    buffer.putString("End").writeln()
      .writeln();
  }
}
