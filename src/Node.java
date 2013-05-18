public class Node<T> {
	
	private T t;
	private Node<T> left;
	private Node<T> right;
	
	public Node() {
		t = null;
		left = null;
		right = null;
	}
	
	public Node(T t) {
		this.t = t;
		left = null;
		right = null;
	}
	
	public Node(Node<T> node) {
		t = node.getData();
		left = node.getLeft();
		right = node.getRight();
	}
	
	public T getData() {
		return t;
	}
	
	public void setData(T t) {
		this.t = t;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	public Node<T> getRight() {
		return right;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}
}
