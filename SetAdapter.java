import java.util.*;

public class SetAdapter implements HSet {

    private Hashtable hashtable = new Hashtable();

    private HList list = new ListAdapter();

    /**
     * Adds the specified element to this set if it is not already present (optional operation).
     */
    public boolean add(Object o) {
        if(contains(o))
           return false;
        list.add(o);
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
     */
    public boolean addAll(HCollection c) {
        return true;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * Returns true if this set contains the specified element.
     */
    public boolean contains(Object o) {
        return list.equals(o);
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     */
    public boolean containsAll(HCollection c){
        return true;
    }

    /**
     * Compares the specified object with this set for equality.
     */
    public boolean equals(Object o){
        return true;
    }

    /**
     * Returns the hash code value for this set.
     */
    public int hashCode(){
        return 0;
    }

    /**
     * Returns true if this set contains no elements.
     */
    public boolean isEmpty(){
        return true;
    }

    /**
     * Returns an iterator over the elements in this set.
     */
    public HIterator iterator(){
        return null;
    }

    /**
     * Removes the specified element from this set if it is present (optional operation).
     */
    public boolean remove(Object o) {
        return true;
    }

    /**
     * Removes from this set all of its elements that are contained in the specified collection (optional operation).
     */
    public boolean removeAll(HCollection c){
        return true;
    }

    /**
     * Retains only the elements in this set that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(HCollection c){
        return true;
    }

    /**
     * Returns the number of elements in this set (its cardinality).
     */
    public int size(){
        return hashtable.size();
    }

    /**
     * Returns an array containing all of the elements in this set.
     */
    public Object[] toArray(){
        return null;
    }

    /**
     * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     */
    public Object[] toArray(Object[] a){
        return null;
    }
}