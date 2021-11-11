package org.openlca.simapro.csv;

import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.UnitBlock;

/**
 * A block of contiguous rows in a CSV file. This is just a marker interface
 * for easy type checks and conversions, use it like this:
 * {@code if (block.isSomeBlock() {var someBlock = block.asSomeBlock();})}
 */
public interface CsvBlock {

  default boolean isUnitBlock() {
    return this instanceof UnitBlock;
  }

  default UnitBlock asUnitBlock() {
    return (UnitBlock) this;
  }

  default boolean isProcessBlock() {
    return this instanceof ProcessBlock;
  }

  default ProcessBlock asProcessBlock() {
    return (ProcessBlock) this;
  }

  default boolean isProductStageBlock() {
    return this instanceof ProductStageBlock;
  }

  default ProductStageBlock asProductStageBlock() {
    return (ProductStageBlock) this;
  }

}
