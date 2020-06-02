package adapter;

public interface HCollection {

    /**
     * Ensures that this collection contains the specified element (optional operation).
     * @param o
     * @return
     */
    public boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     * @param c
     * @return
     */
    public boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    public void clear();

    /**
     * Returns true if this collection contains the specified element.
     * @param o
     * @return
     */
    public boolean contains(Object o);

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c
     * @return
     */
    public boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this collection for equality.
     * @param o
     * @return
     */
    public boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     * @return
     */
    public int hashCode();

    /**
     * Returns true if this collection contains no elements.
     * @return
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this collection.
     * @return
     */
    public HIterator iterator();

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     * @param o
     * @return
     */
    public boolean remove(Object o);

    /**
     * Removes all this collection's elements that are also contained in the specified collection (optional operation).
     * @param c
     * @return
     */
    public boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     * @param c
     * @return
     */
    public boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this collection.
     */
    public int size();

    /**
     * Returns an array containing all of the elements in this collection.
     * @return
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @param a
     * @return
     */
    public Object[] toArray(Object[] a);
}