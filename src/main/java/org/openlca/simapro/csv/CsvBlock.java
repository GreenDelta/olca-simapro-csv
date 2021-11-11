package org.openlca.simapro.csv;

import org.openlca.simapro.csv.method.ImpactMethodBlock;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.CalculatedParameterBlock;
import org.openlca.simapro.csv.refdata.ElementaryFlowBlock;
import org.openlca.simapro.csv.refdata.InputParameterBlock;
import org.openlca.simapro.csv.refdata.QuantityBlock;
import org.openlca.simapro.csv.refdata.SystemDescriptionBlock;
import org.openlca.simapro.csv.refdata.UnitBlock;

/**
 * A block of contiguous rows in a CSV file. It extends the {@link CsvRecord}
 * interface with some additional type checks and conversions:
 *
 * {@code if (block.isSomeBlock() {var someBlock = block.asSomeBlock();})}
 */
public interface CsvBlock extends CsvRecord {


  default boolean isCalculatedParameterBlock() {
    return this instanceof CalculatedParameterBlock;
  }

  default CalculatedParameterBlock asCalculatedParameterBlock() {
    return (CalculatedParameterBlock) this;
  }


  default boolean isElementaryFlowBlock() {
    return this instanceof ElementaryFlowBlock;
  }

  default ElementaryFlowBlock asElementaryFlowBlock() {
    return (ElementaryFlowBlock) this;
  }


  default boolean isImpactMethodBlock() {
    return this instanceof ImpactMethodBlock;
  }

  default ImpactMethodBlock asImpactMethodBlock() {
    return (ImpactMethodBlock) this;
  }


  default boolean isInputParameterBlock() {
    return this instanceof InputParameterBlock;
  }

  default InputParameterBlock asInputParameterBlock() {
    return (InputParameterBlock) this;
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


  default boolean isQuantityBlock() {
    return this instanceof QuantityBlock;
  }

  default QuantityBlock asQuantityBlock() {
    return (QuantityBlock) this;
  }


  default boolean isSystemDescriptionBlock() {
    return this instanceof SystemDescriptionBlock;
  }

  default SystemDescriptionBlock asSystemDescriptionBlock() {
    return (SystemDescriptionBlock) this;
  }


  default boolean isUnitBlock() {
    return this instanceof UnitBlock;
  }

  default UnitBlock asUnitBlock() {
    return (UnitBlock) this;
  }


}
