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
     * {@inheritDoc}
     */
    public boolean add(Object o) {
        isNull(o);
        if(contains(o))
            return false;
        hashtable.put(o, o);
        return true;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(Object o) {
        isNull(o);
        return hashtable.containsKey(o);
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public boolean isEmpty(){
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public HIterator iterator(){
        return new SetIterator();
    }

    /**
     * {@inheritDoc}
     */
    public boolean remove(Object o) {
        isNull(o);
        if(!contains(o))
            return false;
        hashtable.remove(o);
        return true;
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
     * {@inheritDoc}
     */
    public int size(){
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
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
         * {@inheritDoc}
         */
        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        /**
         * {@inheritDoc}
         */
        public Object next() {
            last = keys.nextElement();
            return last;
        }

        /**
         * {@inheritDoc}
         */
        public void remove() {
            if(last == null)
                throw new IllegalStateException();
            SetAdapter.this.remove(last);
            last = null;
        }
    }
}