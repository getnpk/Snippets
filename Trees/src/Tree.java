
public class Tree< T extends Comparable< T > >{
	
	TreeNode<T> root;
	
	public Tree(){
		root = null;
	}

	public void insertNode ( T value ){
	
		if ( root == null)
			root = new TreeNode< T > (value);
		else
			root.insert(value);
	} 
	
	
	public void insertLeft (T value){
		root.insertleft(value);
	}
	
	
	public void insertRight (T value){
		root.insertright(value);
	}
	
	public void postorder(){
		postorderhelper(root);
	}
	
	public void postorderhelper( TreeNode< T > root ){
		
		if (root == null)
			return;
		postorderhelper(root.left);
		postorderhelper(root.right);
		System.out.printf("%s ", root.data);
		
	}
}
