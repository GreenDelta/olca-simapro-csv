package org.openlca.simapro.csv.process;

/**
 * Describes the reference exchange of a process or product stage. Reference
 * exchanges can be linked from other processes.
 */
public interface RefExchangeRow extends ExchangeRow {

  /**
   * The category path of the exchange's flow. This is a string that is
   * separated by back-slash characters ({@code \}).
   */
  String category();

}
