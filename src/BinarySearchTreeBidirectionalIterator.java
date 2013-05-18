import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BinarySearchTreeBidirectionalIterator<T extends Comparable<T>> extends BinarySearchTreeIterator<T> implements BidirectionalIterator<T> {

	public BinarySearchTreeBidirectionalIterator(BinarySearchTree<T> bst, LinkedList<T> list, int start) {
		super(bst, list);
		if (start == BidirectionalIterator.START_AT_END)
			index = list.size();
	}

	public boolean hasPrev() {
		return (index > 0);
	}

	public T prev() throws NoSuchElementException {
		if (index > 0)
			return list.get(--index);
		else
			throw new NoSuchElementException();
	}

	public void prevIndex() throws IndexOutOfBoundsException {
		if (index > 0)
			index--;
		else
			throw new IndexOutOfBoundsException();
	}

}
