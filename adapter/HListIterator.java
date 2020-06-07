package adapter;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, 
 * modify the list during iteration, and obtain the iterator's current position in the list.
 */
public interface HListIterator extends HIterator{

    /**
     * Inserts the specified element into the list (optional operation).
     * @param o  the element to insert. 
     */
    public void add(Object o);

    /**
     * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
     * @return
     */
    public boolean hasPrevious();

    /**
     * Returns the index of the element that would be returned by a subsequent call to next.
     * @return true if the list iterator has more elements when traversing the list in the reverse direction.
     */
    public int nextIndex();

    /**
     * Returns the previous element in the list.
     * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
     */
    public Object previous();

    /**
     * Returns the index of the element that would be returned by a subsequent call to previous.
     * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
     */
    public int previousIndex();

    /**
     * Replaces the last element returned by next or previous with the specified element (optional operation).
     * @param o  the element with which to replace the last element returned by next or previous. 
     */
    public void set(Object o);

}