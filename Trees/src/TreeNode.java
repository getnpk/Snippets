
public class TreeNode < T extends Comparable < T > >{
	
	TreeNode < T > left;
	TreeNode < T > right;
	T data;
	
	public TreeNode ( T NodeData ){
		data = NodeData;
		left = right = null;
	}
	
	public void insertleft( T value){
		if (left == null)
			left = new TreeNode < T > (value);
		else
			left.insertleft(value);
	}
	
	public void insertright( T value){
		if (right == null)
			right = new TreeNode < T > (value);
		else
			right.insertright(value);
	}
	
	public void insert ( T value ){
		
		if (value.compareTo(data) < 0 ){
			if (left == null)
				left = new TreeNode < T > (value);
			else
				left.insert(value);
		}
		else if (value.compareTo(data) > 0) {
			if (right == null)
				right = new TreeNode < T > (value);
			else
				right.insert(value);
		}
	}
	
}
