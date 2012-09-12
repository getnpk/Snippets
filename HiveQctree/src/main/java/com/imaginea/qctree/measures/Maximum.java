package com.imaginea.qctree.measures;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Maximum implements Aggregable {

  private static final Log LOG = LogFactory.getLog(Maximum.class);

  private double max = Double.MIN_VALUE;

  @Override
  public void aggregate(List<Double> measures) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Computing Maximum Aggregate");
    }
    for (double value : measures) {
      max = Math.max(max, value);
    }
  }

  @Override
  public double getAggregateValue() {
    return max;
  }

  @Override
  public void accumalate(Aggregable other) {
    Maximum otherMax = (Maximum) other;
    this.max = Math.max(this.max, otherMax.max);
  }

  @Override
  public String toString() {
    return String.valueOf(Aggregates.FORMAT.format(max));
  }
}
