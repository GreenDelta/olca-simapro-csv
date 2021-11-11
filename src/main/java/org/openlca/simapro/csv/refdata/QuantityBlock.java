package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;

public class QuantityBlock implements CsvBlock {

  private final List<QuantityRow> quantities = new ArrayList<>();

  public List<QuantityRow> quantities() {
    return quantities;
  }

  public static QuantityBlock read(Iterable<CsvLine> lines) {
    var block = new QuantityBlock();
    for (var line : lines) {
      if (line.first().equals("Quantities"))
        continue;
      if (line.isEmpty())
        break;
      var row = QuantityRow.read(line);
      block.quantities.add(row);
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Quantities").writeln()
      .putRecords(quantities)
      .writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
