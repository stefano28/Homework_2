package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HSet
 */
public class SetAdapter implements HSet {

    private Hashtable hashtable = new Hashtable();

    /**
     * Check if the Object is null.
     * @param o object to be analyzed.
     * @throws NullPointerException if the specified object is null
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

    /**
     * Adds the specified element to this set if it is not already present (optional operation).
     * @param o element to be added to this set.
     * @return true if this set did not already contain the specified element.
     * @throws NullPointerException if the specified element is null and this set does not support null elements. 
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
     * @param c  collection whose elements are to be added to this set.
     * @return true if this set changed as a result of the call.
     * @throws NullPointerException if the specified collection contains one or more null elements and this set does not support null elements, or if the specified collection is null. 
     */
    public boolean addAll(HCollection c) {
        isNull(c);
        boolean result = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            add(value);
            result = true;
        }
        return result;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * Returns true if this set contains the specified element.
     * @param o element whose presence in this set is to be tested.
     * @return true if this set contains the specified element.
     * @throws NullPointerException if the specified element is null and this set does not support null elements (optional).
     */
    public boolean contains(Object o) {
        isNull(o);
        return hashtable.containsKey(o);
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     * @param c collection to be checked for containment in this set.
     * @return true if this set contains all of the elements of the specified collection.
     * @throws NullPointerException if the specified collection is null.
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
     * @param o Object to be compared for equality with this set.
     * @returns true if the specified Object is equal to this set.
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
     * @return the hash code value for this set.
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
     * @return true if this set contains no elements.
     */
    public boolean isEmpty(){
        return hashtable.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this set.
     * @return an iterator over the elements in this set.
     */
    public HIterator iterator(){
        return new SetIterator();
    }

    /**
     * Removes the specified element from this set if it is present (optional operation).
     * @param o object to be removed from this set, if present.
     * @return true if the set contained the specified element.
     * @throws NullPointerException if the specified element is null and this set does not support null elements (optional).
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
     * @param c collection that defines which elements will be removed from this set.
     * @return true if this set changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean removeAll(HCollection c){
        isNull(c);
        boolean result = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            if(remove(value))
                result = true;
        }
        return result;
    }

    /**
     * Retains only the elements in this set that are contained in the specified collection (optional operation).
     * @param c collection that defines which elements this set will retain.
     * @return true if this collection changed as a result of the call.
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean retainAll(HCollection c){
        isNull(c);
        boolean result = false;
        HIterator iter = iterator();
        while(iter.hasNext()) {
            Object value = iter.next();
            if(!c.contains(value)) {
                remove(value);
                result = true;
            }
        }
        return result;
    }

    /**
     * Returns the number of elements in this set (its cardinality).
     * @return true if this set contains the specified element.
     */
    public int size(){
        return hashtable.size();
    }

    /**
     * Returns an array containing all of the elements in this set.
     * @return an array containing all of the elements in this set.
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray(){
        Object[] v = new Object[size()];
        HIterator iter = iterator();
        for(int i = 0; iter.hasNext(); i++) {
            v[i] = iter.next();
        }
        return v;
    }

    /**
     * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this set are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this set.
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray(Object[] a) {
        isNull(a);
        Object[] v = new Object[a.length];
        HIterator iter = iterator();
        if(a.length >= size()) {
            for(int i = 0; i < a.length && iter.hasNext(); i++) {
                v[i] = iter.next();
            }
            return v;
        }
        v = new Object[size()];
        for(int i = 0; i < size(); i++) {
            if(iter.hasNext())
                v[i] = iter.next();
            else
                v[i] = null;
        }
        return v;
    }

    private class SetIterator implements HIterator {
        Enumeration keys = hashtable.keys();
        Object last = null;

        /**
         * @inheritDoc
         */
        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        /**
         * @inheritDoc
         */
        public Object next() {
            last = keys.nextElement();
            return last;
        }

        /**
         * @inheritDoc
         */
        public void remove() {
            if(last == null)
                throw new IllegalStateException();
            SetAdapter.this.remove(last);
            last = null;
        }
    }
}