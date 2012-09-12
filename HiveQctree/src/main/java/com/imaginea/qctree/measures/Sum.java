package com.imaginea.qctree.measures;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Sum implements Aggregable, Serializable  {

  private static final Log LOG = LogFactory.getLog(Sum.class);
  private double sum;

  @Override
  public void aggregate(List<Double> measures) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Computing Sum Aggregate");
    }
    double sum = 0;
    for (double value : measures) {
      sum += value;
    }
    this.sum = sum;
  }

  @Override
  public double getAggregateValue() {
    return sum;
  }

  @Override
  public void accumalate(Aggregable other) {
    Sum otherSum = (Sum) other;
    this.sum += otherSum.sum;
  }

  @Override
  public String toString() {
    return String.valueOf(Aggregates.FORMAT.format(sum));
  }

}
