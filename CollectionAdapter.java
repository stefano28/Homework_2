import java.util.*;

public class CollectionAdapter<E> extends Vector<E> {

    Vector<E> vector = new Vector<E>();

    /**
     * Ensures that this collection contains the specified element
     * @param e
     * @return
     */
    public boolean add(E e) {
        vector.addElement(e);
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection
     * @param c
     * @return
     */
    public boolean AddAll(Collection<? extends E> c) {

    }

    /**
     * Removes all of the elements from this collection
     */
    public void clear() {

    }

    /**
     * @return true if this collection contains the specified element.
     */
    public boolean contains(Object o) {
        if(vector.contains(o))
            return true;
        return false;
    }

    /**
     * @return true if this collection contains all of the elements in the specified collection.
     */
    public boolean containsAll(Collection<?> c) {

    }

    /**
     * Compares the specified object with this collection for equality.
     */
    public boolean equals(Object o) {
        Enumeration<E> values = vector.elements();
        while(values.hasMoreElements()) {
            if(vector.contains(o))
                return true;
        }
        return false;
    }

    /**
     * @return the hash code value for this collection.
     */
    public int hashCode() {
        return vector.hashCode();
    }

    /**
     * @return true if this collection contains no elements.
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * @return an iterator over the elements in this collection.
     */
    public Iterator<E> iterator() {

    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     */
    public boolean remove(Object o) {
        if(!contains(o))
            return false;
        vector.removeElement(o);
        return true;
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection.
     * @param c
     * @return
     */
    public boolean removeAll(Collection<?> c) {
        
    }

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(Collection<?> c) {

    }

    /**
     * @return the number of elements in this collection.
     */
    public int size() {
        return vector.size();
    }

    /**
     * @return an array containing all of the elements in this collection.
     */
    public Object[] toArray() {
        Object[] obj = new Object[size()];
        for(int i = 0; i < size(); i++) {
            obj[i] = contains(i);
        }
    }

    /**
     * @return an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     */
    public <T> T[] toArray(T[] a) {

    }
}