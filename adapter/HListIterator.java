package adapter;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, 
 * modify the list during iteration, and obtain the iterator's current position in the list.
 */
public interface HListIterator extends HIterator{

    /**
     * Inserts the specified element into the list (optional operation).
     */
    public void add(Object o);

    /**
     * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
     */
    public boolean hasPrevious();

    /**
     * Returns the index of the element that would be returned by a subsequent call to next.
     */
    public int nextIndex();

    /**
     * Returns the previous element in the list.
     */
    public Object previous();

    /**
     * Returns the index of the element that would be returned by a subsequent call to previous.
     */
    public int previousIndex();

    /**
     * Replaces the last element returned by next or previous with the specified element (optional operation).
     */
    public void set(Object o);
}