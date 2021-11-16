package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.Numeric;

/**
 * The comment fields of an exchange row which describes the input or output of
 * a flow in a process.
 */
public interface ExchangeRow {

  /**
   * The name of the flow.
   */
  String name();

  /**
   * The unit of the exchange's amount.
   */
  String unit();

  /**
   * The amount of the exchange.
   */
  Numeric amount();

  /**
   * An optional exchange comment.
   */
  String comment();

  /**
   * An optional platformId as UUID string.
   */
  String platformId();

}
