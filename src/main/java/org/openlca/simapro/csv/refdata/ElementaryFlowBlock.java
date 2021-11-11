package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

public class ElementaryFlowBlock implements CsvBlock {

  private final ElementaryFlowType type;
  private final List<ElementaryFlowRow> flows = new ArrayList<>();

  private ElementaryFlowBlock(ElementaryFlowType type) {
    this.type = Objects.requireNonNull(type);
  }

  public static ElementaryFlowBlock of(ElementaryFlowType type) {
    return new ElementaryFlowBlock(type);
  }

  public ElementaryFlowType type() {
    return type;
  }

  public List<ElementaryFlowRow> flows() {
    return flows;
  }

  public static ElementaryFlowBlock read(
    ElementaryFlowType type, Iterable<CsvLine> lines) {
    var block = new ElementaryFlowBlock(type);
    for (var line : lines) {
      if (line.first().equals(type.blockHeader()))
        continue;
      if (line.isEmpty())
        break;
      var row = ElementaryFlowRow.read(line);
      block.flows.add(row);
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(type.blockHeader()).writeln()
      .putRecords(flows)
      .writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
