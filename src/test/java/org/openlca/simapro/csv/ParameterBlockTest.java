package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterBlockTest {

  @Test
  public void testDatabaseInputParameters() {
    var ds = Tests.testFile("process.csv");
    assertEquals(1, ds.databaseInputParameters().size());
    // TODO: @mg check parameter values
  }


  @Test
  public void testDatabaseCalculatedParameters() {
    var ds = Tests.testFile("process.csv");
    assertEquals(1, ds.databaseCalculatedParameters().size());
    // TODO: @mg check parameter values
  }


  @Test
  public void testProjectInputParameters() {
    var ds = Tests.testFile("process.csv");
    assertEquals(1, ds.projectInputParameters().size());
    // TODO: @mg check parameter values
  }


  @Test
  public void testProjectCalculatedParameters() {
    var ds = Tests.testFile("process.csv");
    assertEquals(1, ds.projectCalculatedParameters().size());
    // TODO: @mg check parameter values
  }


}
