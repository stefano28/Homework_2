package adapter;
import java.util.*;

public class MapAdapter implements HMap {

    private Hashtable<Object, Object> hashtable = new Hashtable<Object, Object>();

    /**
     * Removes all mappings from this map (optional operation).
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean containsKey(Object key) {
        return hashtable.containsKey(key);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     */
    public boolean containsValue(Object value) {
        Enumeration<Object> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if(get(key).equals(value))
                return true;
        }
        return false;
    }

    /**
     * Returns a set view of the mappings contained in this map.
     */
    public HSet entrySet() {
        HSet set = new SetAdapter();
        Enumeration<Object> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            Entry entry = new Entry();
            entry.key = keys.nextElement();
            entry.value = hashtable.get(entry.key);
            set.add(entry);
        }
        return set;
    }


    /**
     * Compares the specified object with this map for equality. 
     */
    public boolean equals(Object o) {
        HMap m = (HMap)o;
        if(m.size() != size())
            return false;
        for(int i = 0; i < size(); i++) {
            if(!m.get(i).equals(get(i)))
                return false;
        }
        return true;
    }

    /**
     * Returns the value to which this map maps the specified key.
     */
    public Object get(Object key) {
        if(!containsKey(key))
            return null;
        return hashtable.get(key);
    }

    /**
     * Returns the hash code value for this map.
     */
    public int hashCode() {
        Enumeration<Object> keys = hashtable.keys();
        int sum = 0;
        while(keys.hasMoreElements()) {
            sum = Integer.parseInt(keys.nextElement().toString());
        }
        return sum;
    }
    
    /**
     * Returns true if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * Returns a set view of the keys contained in this map.
     */
    public HSet keySet() {
        HSet set = new SetAdapter();
        Enumeration<Object> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            set.add(keys.nextElement());
        }
        return set;
    }

    /**
     * Associates the specified value with the specified key in this map (optional operation).
     */
    public Object put(Object key, Object value) {
        Object prev = null;
        try{
            prev = hashtable.put(key, value);
        }catch(NullPointerException e) {

        }
        return prev;
    }

    /**
     * Copies all of the mappings from the specified map to this map (optional operation).
     */
    public void putAll(HMap t) {
        
    }

    /**
     * Removes the mapping for this key from this map if it is present (optional operation).
     */
    public Object remove(Object key) {
        if(!hashtable.containsKey(key))
            return null;
        Object value = hashtable.get(key);
        hashtable.remove(key);
        return value;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * Returns a collection view of the values contained in this map.
     */
    public HCollection values() {
        return null;
    }

    class Entry implements HMap.Entry {

        public Object key;
        public Object value;

        /**
         * Compares the specified object with this entry for equality.
         */
        public boolean equals(Object o) {
            Entry entry = (Entry)o;
            if(entry.getKey().equals(key) && entry.getValue().equals(value))
                return true;
            return false;
        }

        /**
         * Returns the key corresponding to this entry.
         * @return
         */
        public Object getKey() {
            return key;
        }

        /**
         * Returns the value corresponding to this entry.
         * @return
         */
        public Object getValue() {
            return value;
        }

        /**
         * Returns the hash code value for this map entry.
         */
        public int hashCode() {
            return toString().hashCode();
        }

        /**
         * Replaces the value corresponding to this entry with the specified value (optional operation).
         * @param value
         * @return
         */
        public Object setValue(Object value) {
            this.value = value;
            return value;
        }
    }

}