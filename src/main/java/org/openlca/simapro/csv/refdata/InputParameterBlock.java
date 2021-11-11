package org.openlca.simapro.csv.refdata;

import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;

public class InputParameterBlock implements CsvBlock {

  private final boolean forProject;
  private final List<InputParameterRow> parameters = new ArrayList<>();

  private InputParameterBlock(boolean forProject) {
    this.forProject = forProject;
  }

  public static InputParameterBlock forProject() {
    return new InputParameterBlock(true);
  }

  public static InputParameterBlock forDatabase() {
    return new InputParameterBlock(false);
  }

  public boolean isForProject() {
    return forProject;
  }

  public boolean isForDatabase() {
    return !forProject;
  }

  public List<InputParameterRow> parameters() {
    return parameters;
  }

  public static InputParameterBlock readProjectParameters(
    Iterable<CsvLine> lines) {
    return read(true, lines);
  }

  public static InputParameterBlock readDatabaseParameters(
    Iterable<CsvLine> lines) {
    return read(false, lines);
  }

  private static InputParameterBlock read(
    boolean forProject, Iterable<CsvLine> lines) {
    var block = new InputParameterBlock(forProject);
    for (var line : lines) {
      if (line.first().equals("Project Input parameters")
        || line.first().equals("Database Input parameters"))
        continue;
      if (line.isEmpty())
        break;
      var row = InputParameterRow.read(line);
      block.parameters.add(row);
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    var header = forProject
      ? "Project Input parameters"
      : "Database Input parameters";
    buffer.putString(header).writeln()
      .putRecords(parameters)
      .writeln()
      .putString("End")
      .writeln()
      .writeln();
  }

}
