package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;

public class UnitBlock implements CsvBlock {

  private final List<UnitRow> units = new ArrayList<>();

  public List<UnitRow> units() {
    return units;
  }

  public static UnitBlock read(Iterable<CsvLine> lines) {
    var block = new UnitBlock();
    for (var line : lines) {
      if (line.first().equals("Units"))
        continue;
      if (line.isEmpty())
        break;
      var row = UnitRow.read(line);
      block.units.add(row);
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("Units").writeln()
      .putRecords(units)
      .writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
