package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HSet
 */
public class SetAdapter implements HSet {

    private Hashtable hashtable = new Hashtable();

    /**
     * Check if the object is null
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

    /**
     * Adds the specified element to this set if it is not already present (optional operation).
     */
    public boolean add(Object o) {
        isNull(o);
        if(contains(o))
            return false;
        hashtable.put(o, o);
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
     */
    public boolean addAll(HCollection c) {
        isNull(c);
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            add(value);
        }
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
        isNull(o);
        return hashtable.containsKey(o);
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     */
    public boolean containsAll(HCollection c){
        isNull(c);
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            if(!contains(value))
                return false;
        }
        return true;
    }

    /**
     * Compares the specified object with this set for equality.
     */
    public boolean equals(Object o){ //
        if (o == this)
            return true;
        if (!(o instanceof HSet))
            return false;
        HSet s = (HSet) o;
        if (s.size() != size())
            return false;
        try {
            return containsAll(s);
        }
        catch (ClassCastException cce)   {
            return false;
        }
        catch (NullPointerException npe) {
            return false;
        }
    }

    /**
     * Returns the hash code value for this set.
     */
    public int hashCode(){
        int hashCode = 0;
        HIterator it = iterator();
        while (it.hasNext()) {
            hashCode += it.next().hashCode();
        }
        return hashCode;
    }

    /**
     * Returns true if this set contains no elements.
     */
    public boolean isEmpty(){
        return hashtable.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this set.
     */
    public HIterator iterator(){
        return new SetIterator();
    }

    /**
     * Removes the specified element from this set if it is present (optional operation).
     */
    public boolean remove(Object o) {
        isNull(o);
        if(!contains(o))
            return false;
        hashtable.remove(o);
        return true;
    }

    /**
     * Removes from this set all of its elements that are contained in the specified collection (optional operation).
     */
    public boolean removeAll(HCollection c){
        isNull(c);
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            remove(value);
        }
        return true;
    }

    /**
     * Retains only the elements in this set that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(HCollection c){
        isNull(c);
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            if(!contains(value))
                remove(value);
        }
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
        Object[] v = new Object[size()];
        Enumeration keys = hashtable.elements();
        for(int i = 0; keys.hasMoreElements(); i++) {
            v[i] = keys.nextElement();
        }
        return v;
    }

    /**
     * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     */
    public Object[] toArray(Object[] a) {
        return toArray();
    }

    private class SetIterator implements HIterator {
        Enumeration keys = hashtable.keys();
        Object last = null;

        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        public Object next() {
            last = keys.nextElement();
            return last;
        }

        public void remove() {
            if(last == null)
                throw new IllegalStateException();
            SetAdapter.this.remove(last);
            last = null;
        }
    }
}