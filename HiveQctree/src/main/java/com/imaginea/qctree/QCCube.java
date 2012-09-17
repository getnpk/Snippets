package com.imaginea.qctree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

public class QCCube {

  private Set<Class> classes;
  private int classId;
  
  public QCCube() {
    classes = new TreeSet<Class>();
  }

  public Set<Class> getClasses() {
    return Collections.unmodifiableSet(classes);
  }

  public static QCCube construct() {
    Partition base = Partition.inducedBy(Cell.ROOT);
    QCCube cube = new QCCube();
    cube.DFS(Cell.ROOT, base, 0, null);
    return cube;
  }

  private void DFS(Cell cell, Partition partition, int k, Class child) {
    Class clazz = new Class(partition);
    clazz.computeAggregates();
    Cell ub = clazz.upperBoundOf(cell);
    clazz.setLowerBound(new Cell(cell));
    clazz.setClassID(classId);
    clazz.setChild(child);
    ++classId;
    classes.add(clazz);

    for (int j = 0; j < k; ++j) {
      if (cell.getDimensionAt(j) == Cell.DIMENSION_VALUE_ANY
          && ub.getDimensionAt(j) != Cell.DIMENSION_VALUE_ANY) {
        return;
      }
    }

    for (int j = k; j < Table.getTable().getDimensionHeaders().size(); ++j) {
      Cell c = new Cell(ub);
      if (c.getDimensionAt(j) != Cell.DIMENSION_VALUE_ANY) {
        continue;
      }
      for (String column : partition.getUniqueColumnValues(j)) {
        c.setDimensionAt(j, column);
        Partition part = Partition.inducedBy(c, partition.getRows());
        if (!part.isEmpty()) {
          DFS(c, part, j, clazz);
        }
      }
    }
    // We can clear the data, as it is of no use
    partition.clear();
  }
  
  //new method
  public static QCCube initialize(Set<Class> classes){
	  QCCube cube = new QCCube();
	  for (Class c : classes){
		  cube.classes.add(c);
	  }
	  return cube;
  }
  
  //new method
  public void printQCLattice(){
	
	PrintWriter writer = null;
	  
	  try {
		writer = new PrintWriter(new File(Property.latticeFilename));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	  
	  StringBuilder sb;
	  for (Class c : classes){
		  sb = new StringBuilder();
		  sb.append(c.getClassID());
		  sb.append(",");
		  sb.append(c.getUpperBound());
		  sb.append(",");
		  sb.append(c.getLowerBound());
		  sb.append(",");
		  int chdId = c.getChild() == null ? -1 : c.getChild().getClassID();
		  sb.append(chdId);
		  writer.println(sb.toString());
	  }
	  
	  writer.close();
  }
}
