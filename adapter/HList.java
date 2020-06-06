package adapter;

/**
 * An ordered collection (also known as a sequence).
 */
public interface HList extends HCollection {

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     */
    public void add(int index, Object element);

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     */
    public boolean addAll(int index, HCollection c);

    /**
     * Returns the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int indexOf(Object o);

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     */
    public HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     */
    public HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list (optional operation).
     */
    public Object remove(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     */
    public Object set(int index, Object element);

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     */
    public HList subList(int fromIndex, int toIndex);
}