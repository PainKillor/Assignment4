import java.util.NoSuchElementException;

public interface BidirectionalIterator<T> extends Iterator<T> {
	public static final int START_AT_FRONT = 0;
	public static final int START_AT_END = 1; 
	
	boolean hasPrev();
	T prev() throws NoSuchElementException;
	void prevIndex() throws IndexOutOfBoundsException;
}
