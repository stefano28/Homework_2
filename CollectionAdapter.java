import java.util.*;

public class CollectionAdapter implements HCollection {

    private Vector vector = new Vector();

    /**
     * Ensures that this collection contains the specified element (optional operation).
     */
    public boolean add(Object o) {
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     */
    public boolean addAll(HCollection c) {
        return true;
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     */
    public void clear() {

    }

    /**
     *  Returns true if this collection contains the specified element.
     */
    public boolean contains(Object o) {
        return true;
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     */
    public boolean containsAll(HCollection c) {
        return true;
    }

    /**
     * Compares the specified object with this collection for equality.
     */
    public boolean equals(Object o) {
        return true;
    }

    /**
     * Returns the hash code value for this collection.
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Returns true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return true;
    }

    /**
     * Returns an iterator over the elements in this collection.
     */
    public HIterator Iterator() {
        return null;
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     */
    public boolean remove(Object o) {
        return true;
    }

    /**
     * Removes all this collection's elements that are also contained in the specified collection (optional operation).
     */
    public boolean removeAll(HCollection c) {
        return true;
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(HCollection c) {
        return true;
    }

    /**
     * Returns the number of elements in this collection.
     */
    public int size() {
        return 0;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     */
    public Object[] toArray() {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     */
    public Object[] toArray(Object[] a) {
        return null;
    }
}