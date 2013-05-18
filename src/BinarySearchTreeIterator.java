import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T> {
	
	private BinarySearchTree<T> bst;
	
	protected LinkedList<T> list;
	protected int index;
	
	public BinarySearchTreeIterator(BinarySearchTree<T> bst, LinkedList<T> list) {
		index = 0;
		this.bst = bst;
		this.list = list;
	}
	
	public boolean hasNext() {
		return (index < list.size());
	}

	public T next() throws NoSuchElementException {
		if (index < list.size())
			return list.get(index++);
		else
			throw new NoSuchElementException();
	}

	public void nextIndex() throws IndexOutOfBoundsException {
		if (index < list.size())
			index++;
		else
			throw new IndexOutOfBoundsException();
	}

	public void remove() throws IndexOutOfBoundsException {
		if (index < list.size())
			bst.remove(list.get(index));
	}

}
