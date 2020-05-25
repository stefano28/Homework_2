import java.util.*;

public class MapAdapter<K, V> extends Hashtable<K, V> {

    private static final long serialVersionUID = 1L;

    private Hashtable<K, V> hashtable = new Hashtable<K, V>();

    /**
     * Removes all of the mappings from this map (optional operation). 
     * The map will be empty after this call returns.
     * @throws UnsupportedOperationException
     */
    public void clear() {
        Enumeration<K> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            hashtable.remove(keys.nextElement());
        }
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
        Enumeration<K> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            Object key = keys.nextElement();
            if(get(key).equals(value))
                return true;
        }
        return false;
    }

    /**
     * Returns an immutable Map.Entry containing the given key and value. 
     * These entries are suitable for populating Map instances using the Map.ofEntries() method
     * @param <K>
     * @param <V>
     * @param k
     * @param v
     * @return an Entry containing the specified key and value
     * @throws NullPointerException - if the key or value is null
     */
    
    /*
    public static <K, V> Entry<K, V> entry(K k, V v) {
        return <K, V> Entry<K, V>;
    }

    public Set<Entry<K, V>> entrySet() {

    }
    */

    /**
     * Compares the specified object with this map for equality. 
     * @return true if the given object is also a map and the two maps represent the same mappings.
     */

    /*
    public boolean equals(Object o) {

    }
    */

    /**
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key. 
     * @throws ClassCastException 
     * @throws NullPointerException
     */
    public V get(Object key) {
        if(!containsKey(key))
            return null;
        return hashtable.get(key);
    }

    public V getOrDefault(Object key, V defaultValue) {
        if(hashtable.containsKey(key))
            return hashtable.get(key);
        return defaultValue;
    }

    public int hashCode() {
        Enumeration<K> keys = hashtable.keys();
        int sum = 0;
        while(keys.hasMoreElements()) {
            sum = Integer.parseInt(keys.nextElement().toString());
        }
        return sum;
    }
    
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /*
    public Set<K> keySet() {

    }
    */

    /**
     * Associates the specified value with the specified key in this map
     * @param key - key with which the specified value is to be associated
     * @param value - value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key
     */
    public V put(K key, V value) {
        V prev = null;
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
    public void putAll(Map<? extends K, ? extends V> m) {
        Enumeration<K> keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            K key = keys.nextElement();
            V value = m.get(key);
            hashtable.put(key, value);
        }
    }

    /**
     * If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null,
     *  else returns the current value.
     * @param key
     * @param value
     * @return
     */
    public V putifAbsent(K key, V value) {
        if(hashtable.contains(key))
            return hashtable.get(key);
        put(key, value);
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key - key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     */

    public V remove(Object key) {
        if(!hashtable.containsKey(key))
            return null;
        V value = hashtable.get(key);
        hashtable.remove(key);
        return value;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to the specified value.
     * @param key - key with which the specified value is associated
     * @param value - value expected to be associated with the specified key
     * @return true if the value was removed
     */
    public boolean remove(Object key, Object value) {
        if(!hashtable.containsKey(key) && !hashtable.contains(value))
            return false;
        try {
            put((K)key, null);
        }catch(ClassCastException e) {

        }
        return true;
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     * @param key - key with which the specified value is associated
     * @param value - value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key.
     */
    public V replace(K key, V value) {
        if(!hashtable.containsKey(key))
            return null;
        return put(key, value);
    }

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     * @param key - key with which the specified value is associated
     * @param oldValue - value expected to be associated with the specified key
     * @param newValue - value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key.
     */
    public boolean replace(K key, V oldValue, V newValue) {
        if(!hashtable.containsKey(key) && !hashtable.contains(oldValue))
            return false;
        replace(key, newValue);
        return true;
    }

    /**
     * @return the number of key-value mappings in this map.
     */
    public int size() {
        return hashtable.size();
    }

    /*
    public Collection<V> values() {

    }
    */
}