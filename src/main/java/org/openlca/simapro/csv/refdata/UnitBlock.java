package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.CsvScanner;

public class UnitBlock implements CsvRecord {

  private final List<UnitRow> units = new ArrayList<>();

  public List<UnitRow> units() {
    return units;
  }

  public static UnitBlock read(CsvScanner scanner) {
    var block = new UnitBlock();
    for (var l = scanner.next(); l.isPresent(); l = scanner.next()) {
      var line = l.get();
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
    buffer.putString("Units");
    for (var row : units) {
      row.write(buffer);
    }
    buffer.writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
