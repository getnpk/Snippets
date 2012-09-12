package com.imaginea.qctree.hadoop;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;

import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.imaginea.qctree.measures.Aggregable;
import com.imaginea.qctree.measures.Aggregates;
import com.imaginea.qctree.Cell;
import com.imaginea.qctree.Class;
import com.imaginea.qctree.QCCube;
import com.imaginea.qctree.Table;

/**
 * A QC-tree is a compact representation of the QC-cube. It is a prefix tree,
 * having (dimension value, aggregate) as node.
 * 
 */
public class QCTree implements Serializable{

  private static final Log LOG = LogFactory.getLog(QCTree.class);
  private static final String NONE = "NONE";
  private final QCNode root;

  public QCTree(Class clazz) {
    if (clazz.getUpperBound().equals(Cell.ROOT)) {
      root = new QCNode(-1, Cell.DIMENSION_VALUE_ANY);
    } else {
      String[] dimensions = clazz.getUpperBound().getDimensions();
      int idx;
      for (idx = 0; idx < dimensions.length; ++idx) {
        if (!dimensions[idx].equals(Cell.DIMENSION_VALUE_ANY)) {
          break;
        }
      }
      root = new QCNode(idx, dimensions[idx]);
      QCNode parent = root;
      QCNode temp;

      for (int i = idx + 1; i < dimensions.length; ++i) {
        if (!dimensions[i].equals(Cell.DIMENSION_VALUE_ANY)) {
          temp = new QCNode(i, dimensions[i]);
          if (parent.isLeaf()) {
            parent.children = new TreeSet<QCTree.QCNode>();
          }
          parent.children.add(temp);
          temp.parent = parent;
          parent = temp;
        }
      }
      // Leaf Node
      parent.setAggregates(clazz.getAggregates());
    }
  }

  public QCTree() {
    root = new QCNode();
  }

  public static QCTree build(final QCCube qCube) {
    QCTree tree = null;
    Cell last = null;

    for (Class clazz : qCube.getClasses()) {
      if (tree == null) {
        tree = new QCTree(clazz);
        last = clazz.getUpperBound();
      } else if (!last.equals(clazz.getUpperBound())) {
        tree.add(clazz);
        last = clazz.getUpperBound();
      } else {
        tree.addDrillDownLink(clazz);
      }
    }
    return tree;
  }

  /*
   * To add the drill down links, find the two nodes in the tree and add a link
   * between them. FIXME crap code, Kamesh think for any better approach?
   */
  public void addDrillDownLink(Class clazz) {
    Cell chdUB = clazz.getChild().getUpperBound();
    Cell curLB = clazz.getLowerBound();
    if (LOG.isDebugEnabled()) {
      LOG.debug("Adding drill down link between : " + chdUB + " and " + curLB);
    }
    String[] dimensions = clazz.getUpperBound().getDimensions();
    String[] dim = new String[dimensions.length];
    System.arraycopy(Cell.ROOT.getDimensions(), 0, dim, 0, dim.length);

    int idx = 0;
    while (!(chdUB.getDimensionAt(idx) == Cell.DIMENSION_VALUE_ANY && curLB
        .getDimensionAt(idx) != Cell.DIMENSION_VALUE_ANY)) {
      dim[idx] = dimensions[idx];
      idx++;
    }
    dim[idx] = dimensions[idx];

    QCNode from = getLongestMatchingNode(root, chdUB.getDimensions());
    QCNode to = getLongestMatchingNode(root, dim);
    if (from.ddLink == null) {
      from.ddLink = new LinkedList<QCNode>();
    }
    from.ddLink.add(to);
  }

  private QCNode getLongestMatchingNode(QCNode node, String[] dimensions) {
    for (int idx = 0; idx < dimensions.length; ++idx) {
      if (!dimensions[idx].equals(Cell.DIMENSION_VALUE_ANY)) {
        node = getNode(node, idx, dimensions[idx]);
        if (!node.getDimValue().equals(dimensions[idx])) {
          break;
        }
      }
    }
    return node;
  }

  /*
   * If the QCTree contains a QCNode with given input dimensions, it returns the
   * same QCNode, otherwise its parent will be returned.
   */
  private QCNode getNode(QCNode temp, int dimIdx, String dimValue) {
    if (temp != null && !temp.isLeaf()) {
      for (QCNode child : temp.children) {
        if (child.dimIdx == dimIdx && child.dimValue.equals(dimValue)) {
          temp = child;
          break;
        }
      }
    }
    return temp;
  }

  public boolean add(Class clazz) {
    if (LOG.isDebugEnabled()) {
      LOG.debug("Adding tree edge : " + clazz);
    }
    String[] dimensions = clazz.getUpperBound().getDimensions();
    QCNode parent = root;
    int idx;

    for (idx = 0; idx < dimensions.length; ++idx) {
      if (!dimensions[idx].equals(Cell.DIMENSION_VALUE_ANY)) {
        parent = getNode(parent, idx, dimensions[idx]);
        if (!parent.getDimValue().equals(dimensions[idx])) {
          break;
        }
      }
    }

    QCNode temp;
    for (int i = idx; i < dimensions.length; ++i) {
      if (!dimensions[i].equals(Cell.DIMENSION_VALUE_ANY)) {
        temp = new QCNode(i, dimensions[i]);
        if (parent.isLeaf()) {
          parent.children = new TreeSet<QCTree.QCNode>();
        }
        parent.children.add(temp);
        temp.parent = parent;
        parent = temp;
      }
    }
    // Leaf Node
    parent.setAggregates(clazz.getAggregates());
    return true;
  }

  public QCNode getRoot() {
    return root;
  }

  public void printTree(QCNode node, StringBuilder sb) {
    if (node.isLeaf()) {
      sb.append(node.toString()).append(' ');
      sb.append(false).append('\n');
      return;
    }

    sb.append(node.toString()).append(' ');
    sb.append(true).append('\n');
    for (QCNode child : node.children) {
      printTree(child, sb);
    }
    sb.append(NONE).append('\n');
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    printTree(root, sb);
    return sb.toString();
  }


  /*
   * Comparing the graphs using level order traversing
   */
  private boolean areEqual(QCNode n1, QCNode n2) {
    Queue<QCNode> queue1 = new LinkedList<QCTree.QCNode>();
    Queue<QCNode> queue2 = new LinkedList<QCTree.QCNode>();

    queue1.offer(n1);
    queue2.offer(n2);

    while (!queue1.isEmpty() && !queue2.isEmpty()) {
      QCNode node1 = queue1.poll();
      if (!node1.isLeaf()) {
        queue1.addAll(node1.children);
      }
      if (node1.hasDDLinks()) {
        queue1.addAll(node1.ddLink);
      }

      QCNode node2 = queue2.poll();
      if (!node2.isLeaf()) {
        queue2.addAll(node2.children);
      }
      if (node2.hasDDLinks()) {
        queue2.addAll(node2.ddLink);
      }

      if (!node1.equals(node2)) {
        return false;
      }
    }
    // If either of the queue is non-empty, return false.
    return queue1.isEmpty() && queue2.isEmpty();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (obj.getClass() != this.getClass()) {
      return false;
    }
    QCTree that = (QCTree) this;
    return areEqual(that.root, this.root);
  }

  /*
   * Returns the absolute path from the current node till parent.
   */
  public Cell getAbsolutePath(QCNode node) {
    String[] dims = new String[Cell.ROOT.getDimensions().length];
    System.arraycopy(Cell.ROOT.getDimensions(), 0, dims, 0, dims.length);
    while (!root.equals(node)) {
      dims[node.getDimIdx()] = node.getDimValue();
      node = node.parent;
    }
    return new Cell(dims);
  }

  /*
   * While adding nodes to the trees, we add them based on their dimension
   * index. Suppose say dimensions are a b and c (order is important). Suppose
   * say Node n has a child with label b. When another child with label a comes,
   * child a precedes child b, in the child list. This has been designed to
   * achieve best query latency times.
   */
  class QCNode implements Comparable<QCNode>, Serializable {
	  
    private int dimIdx;
    private String dimValue;
    private Aggregates aggregates;

    private QCNode parent;
    private SortedSet<QCNode> children;
    private List<QCNode> ddLink;

    QCNode() {
      children = new TreeSet<QCNode>();
    }

    QCNode(int dimIdx, String dimValue) {
      this.dimIdx = dimIdx;
      this.dimValue = dimValue;
    }

    public void setAggregates(Aggregates aggregates) {
      this.aggregates = aggregates;
    }

    public Aggregates getAggregates() {
      return this.aggregates;
    }

    String getDimValue() {
      return this.dimValue;
    }

    int getDimIdx() {
      return this.dimIdx;
    }

    boolean isLeaf() {
      return this.children == null || this.children.size() == 0;
    }

    boolean hasDDLinks() {
      return this.ddLink != null && this.ddLink.size() != 0;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj == this) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (obj.getClass() != this.getClass()) {
        return false;
      }
      QCNode that = (QCNode) obj;
      return that.dimIdx == this.dimIdx && that.dimValue.equals(this.dimValue);
    }

    // If dimension indices are same, do stable sort
    @Override
    public int compareTo(QCNode o) {
      int diff = dimIdx - o.dimIdx;
      return diff == 0 ? dimValue.compareTo(o.dimValue) : diff;
    }

    @SuppressWarnings("unchecked")
    public Set<QCNode> getChildren() {
      return isLeaf() ? Collections.EMPTY_SET : children;
    }

    public QCNode getLastChild() {
      return children.last();
    }

    @SuppressWarnings("unchecked")
    public List<QCNode> getDDLinks() {
      return hasDDLinks() ? ddLink : Collections.EMPTY_LIST;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      String header;
      if (dimIdx == -1) {
        header = "ALL";
      } else {
        header = Table.getTable().getDimensionHeaderAt(dimIdx);
      }
      sb.append(header);
      sb.append(" = ").append(dimValue).append('\n');
      if (aggregates != null) {
        for (Entry<String, Aggregable> aggr : aggregates.get().entrySet()) {
          sb.append(aggr.getKey()).append(':');
          sb.append(aggr.getValue()).append('\n');
        }
      }
      return sb.substring(0, sb.length() - 1);
    }
  }


}
