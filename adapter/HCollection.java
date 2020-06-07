package adapter;

/**
 * The root interface in the collection hierarchy.
 */
public interface HCollection {

    /**
     * Ensures that this collection contains the specified element (optional operation).
     * @param o  element whose presence in this collection is to be ensured. 
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException
     */
    public boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     * @param c  elements to be inserted into this collection. 
     * @return elements to be inserted into this collection.
     * @throws NullPointerException
     */
    public boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    public void clear();

    /**
     * Returns true if this collection contains the specified element.
     * @param o  element whose presence in this collection is to be tested. 
     * @return true if this collection contains the specified element 
     * @throws NullPointerException
     */
    public boolean contains(Object o);

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c  collection to be checked for containment in this collection. 
     * @return true if this collection contains all of the elements in the specified collection
     * @throws NullPointerException
     */
    public boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this collection for equality.
     * @param o  Object to be compared for equality with this collection. 
     * @return Object to be compared for equality with this collection.
     * @throws NullPointerException
     */
    public boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     * @return the hash code value for this collection
     */
    public int hashCode();

    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this collection.
     * @return an array containing all of the elements in this collection
     */
    public HIterator iterator();

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     * @param o  element to be removed from this collection, if present. 
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException
     */
    public boolean remove(Object o);

    /**
     * Removes all this collection's elements that are also contained in the specified collection (optional operation).
     * @param c  elements to be removed from this collection. 
     * @return true if this collection changed as a result of the call
     * @throws NullPointerException
     */
    public boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     * @param c  elements to be retained in this collection. 
     * @return true if this collection changed as a result of the call 
     * @throws NullPointerException
     */
    public boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection
     */
    public int size();

    /**
     * Returns an array containing all of the elements in this collection.
     * @return an array containing all of the elements in this collection
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this collection
     * @throws NullPointerException
     */
    public Object[] toArray(Object[] a);
    
}