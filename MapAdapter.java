import java.util.*;

public class MapAdapter extends Hashtable implements HMap {

    private Hashtable hashtable = new Hashtable();

    /**
     * Removes all of the mappings from this map (optional operation). 
     * The map will be empty after this call returns.
     * @throws UnsupportedOperationException
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * Returns true if this map contains a mapping for the specified key. 
     * More formally, returns true if and only if this map contains a mapping for a key k such that Objects.equals(key, k). 
     * (There can be at most one such mapping.)
     * @param key - key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     * @throws ClassCastException
     * @throws NullPointerException
     */
    public boolean containsKey(Object key) {
        return hashtable.containsKey(key);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value. 
     * More formally, returns true if and only if this map contains at least one mapping to a value v such that Objects.equals(value, v). 
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     * @param value - value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     * @throws ClassCastException
     * @throws NullPointerException
     */
    public boolean containsValue(Object value) {
        Enumeration keys = hashtable.keys();
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
        Enumeration keys = hashtable.keys();
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
     * @return true if the given object is also a map and the two maps represent the same mappings.
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
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key. 
     * @throws ClassCastException 
     * @throws NullPointerException
     */
    public Object get(Object key) {
        if(!containsKey(key))
            return null;
        return hashtable.get(key);
    }

    /**
     * he hash code of a map is defined to be the sum of the hash codes of each entry in the map's entrySet() view.
     * @return the hash code value for this map
     */
    public int hashCode() {
        Enumeration keys = hashtable.keys();
        int sum = 0;
        while(keys.hasMoreElements()) {
            sum = Integer.parseInt(keys.nextElement().toString());
        }
        return sum;
    }
    
    /**
     * @return true if this map contains no key-value mappings.
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * Returns a set view of the keys contained in this map.
     */
    public HSet keySet() {
        HSet set = new SetAdapter();
        Enumeration keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            set.add(keys.nextElement());
        }
        return set;
    }

    /**
     * Associates the specified value with the specified key in this map
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key
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
     * Copies all of the mappings from the specified map to this map
     * @param m - mappings to be stored in this map
     */
    public void putAll(HMap t) {

    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key - key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     */

    public Object remove(Object key) {
        if(!hashtable.containsKey(key))
            return null;
        Object value = hashtable.get(key);
        hashtable.remove(key);
        return value;
    }

    /**
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return hashtable.size();
    }

    /*
    public Collection values() {
        
    }
    */

    class Entry {

        public Object key;
        public Object value;

        /**
         * Compares the specified object with this entry for equality.
         */
        public boolean equals(Object o) {
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