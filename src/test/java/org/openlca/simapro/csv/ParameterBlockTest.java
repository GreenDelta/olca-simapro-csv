package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParameterBlockTest {

  @Test
  public void testDatabaseInputParameters() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(1, ds.databaseInputParameters().size());
    var inputParameter = ds.databaseInputParameters().get(0);
    assertEquals("db_input_param", inputParameter.name());
    assertEquals("database parameter\n", inputParameter.comment());
    assertFalse(inputParameter.isHidden());
    assertEquals(1, inputParameter.value(), 0.0001);
    assertTrue(inputParameter.uncertainty().isLogNormal());
    assertEquals(1, inputParameter.uncertainty().getAsLogNormal().xsd(), 0.0001);
  }

  @Test
  public void testDatabaseCalculatedParameters() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(1, ds.databaseCalculatedParameters().size());
    var calculatedParameter = ds.databaseCalculatedParameters().get(0);
    assertEquals("db_calc_param", calculatedParameter.name());
    assertEquals("calculated database parameter\n", calculatedParameter.comment());
    assertEquals("db_input_param * 3", calculatedParameter.expression());
  }

  @Test
  public void testProjectInputParameters() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(1, ds.projectInputParameters().size());
    var projectInputParameter = ds.projectInputParameters().get(0);
    assertEquals("proj_input_param", projectInputParameter.name());
    assertEquals("project input parameter\n", projectInputParameter.comment());
    assertFalse(projectInputParameter.isHidden());
    assertEquals(32, projectInputParameter.value(), 0.0001);
    assertTrue(projectInputParameter.uncertainty().isUniform());
    assertEquals(10, projectInputParameter.uncertainty().getAsUniform().min(), 0.0001);
    assertEquals(35, projectInputParameter.uncertainty().getAsUniform().max(), 0.0001);
  }


  @Test
  public void testProjectCalculatedParameters() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(1, ds.projectCalculatedParameters().size());
    var projectCalculatedParameter = ds.projectCalculatedParameters().get(0);
    assertEquals("proj_calc_param", projectCalculatedParameter.name());
    assertEquals("project calculated parameter\n", projectCalculatedParameter.comment());
    assertEquals("db_input_param *4", projectCalculatedParameter.expression());
  }


}
