import java.util.LinkedList;

public class BinarySearchTree<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size;
	private int depth;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
		depth = 0;
	}
	
	public BinarySearchTree(T t) {
		this(new Node<T>(t));
	}
	
	public BinarySearchTree(Node<T> node) {
		root = node;
		size = 1;
		depth = 1;
	}
	
	public BinarySearchTree(BinarySearchTree<T> bst) {
		this();
		Iterator<T> it = bst.iterator();
		while (it.hasNext())
			add(it.next());
	}
	
	public Node<T> getRoot() {
		return root;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void add(T t) {
		add(new Node<T>(t));
	}
	
	public void add(Node<T> node) {
		int depth;
		
		if (root == null) {
			root = node;
			depth = 1;
		} else {
			depth = add(root, node, 1);
		}
		
		if (depth > this.depth)
			this.depth = depth;
		
		size++;
	}
	
	private int add(Node<T> current, Node<T> node, int depth) {
		depth++;
		
		if (node.getData().compareTo(current.getData()) < 0) {
			if (current.getLeft() == null) {
				current.setLeft(node);
				return depth;
			} else {
				return add(current.getLeft(), node, depth);
			}
		} else if (node.getData().compareTo(current.getData()) > 0) {
			if (current.getRight() == null) {
				current.setRight(node);
				return depth;
			} else {
				return add(current.getRight(), node, depth);
			}
		} 
		
		return depth;
	}
	
	public void remove(T t) {
		remove(null, root, t);
	}
	
	private void remove(Node<T> parent, Node<T> current, T t) {
		if (t.compareTo(current.getData()) < 0) {
			if (current.getLeft() != null)
				remove(current, current.getLeft(), t);
			else
				return;
		} else if (t.compareTo(current.getData()) > 0) {
			if (current.getRight() != null)
				remove(current, current.getRight(), t);
			else
				return;
		} else {
			if (current.getLeft() == null && current.getRight() == null) {
				if (parent != null) {
					if (parent.getLeft() == current)
						parent.setLeft(null);
					else
						parent.setRight(null);
				} else {
					current = null;
				}
			} else if (current.getLeft() != null && current.getRight() != null) {
				current.setData(fetchPredecessor(current, current.getLeft()));
			} else {
				if (current.getLeft() != null)
					current.setData(fetchPredecessor(current, current.getLeft()));
				else 
					current.setData(fetchSuccessor(current, current.getRight()));
			}
		}
	}
	
	private T fetchPredecessor(Node<T> parent, Node<T> current) {
		if (current.getRight() == null) {
			if (parent.getRight() == current)
				parent.setRight(current.getLeft());
			else
				parent.setLeft(current.getLeft());
			return current.getData();
		} else {
			return fetchPredecessor(current, current.getRight());
		}
	}
	
	private T fetchSuccessor(Node<T> parent, Node<T> current) {
		if (current.getLeft() == null) {
			if (parent.getLeft() == current)
				parent.setLeft(current.getRight());
			else
				parent.setRight(current.getRight());
			return current.getData();
		} else {
			return fetchSuccessor(current, current.getLeft());
		}
	}
	
	public boolean contains(T t) {
		return contains(root, t);
	}
	
	private boolean contains(Node<T> current, T t) {
		if (current != null) {
			if (t.compareTo(current.getData()) < 0)
				return contains(current.getLeft(), t);
			else if (t.compareTo(current.getData()) > 0)
				return contains(current.getRight(), t);
			else
				return true;
		} else {
			return false;
		}
	}
	
	public Iterator<T> iterator() {
		LinkedList<T> list = new LinkedList<T>();
		generateList(root, list);
		
		return new BinarySearchTreeIterator<T>(this, list);
	}
	
	public BidirectionalIterator<T> bidirectionalIterator() {
		return bidirectionalIterator(BidirectionalIterator.START_AT_FRONT);
	}
	
	public BidirectionalIterator<T> bidirectionalIterator(int start) {
		LinkedList<T> list = new LinkedList<T>();
		generateList(root, list);
		
		return new BinarySearchTreeBidirectionalIterator<T>(this, list, start);
	}
	
	private void generateList(Node<T> current, LinkedList<T> list) {
		if (current.getLeft() != null) 
			generateList(current.getLeft(), list);
		
		list.add(current.getData());
		
		if (current.getRight() != null)
			generateList(current.getRight(), list);
	}
}
