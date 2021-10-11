package org.openlca.simapro.csv.process;

import org.openlca.simapro.csv.Numeric;

public interface ExchangeRow {

  String name();

  String unit();

  Numeric amount();

}
