package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;

public class CalculatedParameterBlock implements CsvBlock {

  private final boolean forProject;
  private final List<CalculatedParameterRow> parameters = new ArrayList<>();

  private CalculatedParameterBlock(boolean forProject) {
    this.forProject = forProject;
  }

  public static CalculatedParameterBlock forProject() {
    return new CalculatedParameterBlock(true);
  }

  public static CalculatedParameterBlock forDatabase() {
    return new CalculatedParameterBlock(false);
  }

  public boolean isForProject() {
    return forProject;
  }

  public boolean isForDatabase() {
    return !forProject;
  }

  public List<CalculatedParameterRow> parameters() {
    return parameters;
  }

  public static CalculatedParameterBlock readProjectParameters(
    Iterable<CsvLine> lines) {
    return read(true, lines);
  }

  public static CalculatedParameterBlock readDatabaseParameters(
    Iterable<CsvLine> lines) {
    return read(false, lines);
  }

  private static CalculatedParameterBlock read(
    boolean forProject, Iterable<CsvLine> lines) {
    var block = new CalculatedParameterBlock(forProject);
    for (var line : lines) {
      if (line.first().equals("Project Calculated parameters")
        || line.first().equals("Database Calculated parameters"))
        continue;
      if (line.isEmpty())
        break;
      var row = CalculatedParameterRow.read(line);
      block.parameters.add(row);
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    var header = forProject
      ? "Project Calculated parameters"
      : "Database Calculated parameters";
    buffer.putString(header).writeln()
      .putRecords(parameters)
      .writeln()
      .putString("End")
      .writeln()
      .writeln();
  }
}
