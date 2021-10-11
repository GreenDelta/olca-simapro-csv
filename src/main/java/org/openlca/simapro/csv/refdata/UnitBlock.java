package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class UnitBlock implements CsvRecord {

  private final List<UnitRow> units = new ArrayList<>();

  public List<UnitRow> units() {
    return units;
  }

  public static UnitBlock read(Iterable<CsvLine> lines) {
    var block = new UnitBlock();
    for (var line : lines) {
      if (line.isUnitsStart())
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
    buffer.putString("Units")
      .writeln();
    for (var row : units) {
      row.write(buffer);
    }
    buffer.writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
