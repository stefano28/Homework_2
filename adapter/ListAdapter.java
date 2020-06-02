package adapter;
import java.util.*;

public class ListAdapter implements HList {

    private Vector vector = new Vector();

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     */
    public void add(int index, Object element) {
        vector.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list (optional operation).
     */
    public boolean add(Object o) {
        return vector.add(o);
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     */
    public boolean addAll(HCollection c) {
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     */
    public boolean addAll(int index, HCollection c) {
        return true;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     */
    public void clear() {

    }

    /**
     * Returns true if this list contains the specified element.
     */
    public boolean contains(Object o) {
        return true;
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     */
    public boolean containsAll(HCollection c) {
        return true;
    }

    /**
     * Compares the specified object with this list for equality.
     */
    public boolean equals(Object o) {
        return true;
    }

    /**
     * Returns the element at the specified position in this list.
     */
    public Object get(int index) {
        return null;
    }

    /**
     * Returns the hash code value for this list.
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int indexOf(Object o) {
        return 0;
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator() {
        return null;
    }

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     */
    public int lastIndexOf(Object o) {
        return 0;
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     */
    public HListIterator listIterator() {
        return null;
    }

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     */
    public HListIterator listIterator(int index) {
        return null;
    }

    /**
     * Removes the element at the specified position in this list (optional operation).
     */
    public Object remove(int index) {
        return null;
    }

    /**
     * Removes the first occurrence in this list of the specified element (optional operation).
     */
    public boolean remove(Object o) {
        return true;
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection (optional operation).
     */
    public boolean removeAll(HCollection c) {
        return true;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(HCollection c) {
        return true;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     */
    public Object set(int index, Object element) {
        return null;
    }

    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return vector.size();
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     */
    public HList subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray() {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     */
    public Object[] toArray(Object[] a) {
        return null;
    }

    public class ListIteratorAdapter implements HListIterator {
        
        public boolean hasNext() {
            return false;
        }

        public Object next() {
            return null;
        }

        public void remove() {

        }

        public boolean hasPrevious() {
            return false;
        }

        public Object previous() {
            return null;
        }   

        public int nextIndex() {
            return 0;
        }

        public int previousIndex() {
            return 0;
        }

        public void set(Object o) {
        
        }

        public void add(Object o) {

        }
    }

}