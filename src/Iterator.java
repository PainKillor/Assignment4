import java.util.NoSuchElementException;

public interface Iterator<T> {
	boolean hasNext();
	T next() throws NoSuchElementException;
	void nextIndex() throws IndexOutOfBoundsException;
	void remove() throws IndexOutOfBoundsException;
}
