package adapter;

/**
 * An iterator over a collection.
 */
public interface HIterator {

    /**
     * Returns true if the iteration has more elements.
     */
    public boolean hasNext();

    /**
     * Returns the next element in the iteration.
     */
    public Object next();

    /**
     * Removes from the underlying collection the last element returned by the iterator (optional operation).
     */
    public void remove();
}