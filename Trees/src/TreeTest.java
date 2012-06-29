
public class TreeTest {
	
	public static void main(String args[]){
		Tree<Integer> tree = new Tree<Integer>();
		tree.insertNode(49);
		tree.insertNode(64);
		
		tree.postorder();
		
		System.out.println();
		
		Tree<Character> etree = new Tree<Character>();
		
		TreeNode<Character> plus = new TreeNode<Character>('+');
		TreeNode<Character> minus = new TreeNode<Character>('-');
		TreeNode<Character> star = new TreeNode<Character>('*');
		TreeNode<Character> four = new TreeNode<Character>('4');
		TreeNode<Character> three = new TreeNode<Character>('3');
		TreeNode<Character> nine = new TreeNode<Character>('9');
		TreeNode<Character> one = new TreeNode<Character>('1');
	}
}
