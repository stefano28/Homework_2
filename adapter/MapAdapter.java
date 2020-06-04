package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

public class MapAdapter implements HMap {

    private Hashtable hashtable = new Hashtable();

    /**
     * 
     * @param o
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

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
        isNull(key);
        return hashtable.containsKey(key);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     */
    public boolean containsValue(Object value) {
        isNull(value);
        return hashtable.contains(value);
    }

    /**
     * Returns a set view of the mappings contained in this map.
     */
    public HSet entrySet() {
        return new EntrySet();
    }


    /**
     * Compares the specified object with this map for equality. 
     */
    public boolean equals(Object o) {
        isNull(o);
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
        isNull(key);
        return hashtable.get(key);
    }

    /**
     * Returns the hash code value for this map.
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
        Enumeration keys = hashtable.keys();
        while(keys.hasMoreElements()) {
            set.add(keys.nextElement());
        }
        return set;
    }

    /**
     * Associates the specified value with the specified key in this map (optional operation).
     */
    public Object put(Object key, Object value) {
        isNull(key);
        isNull(value);
        return hashtable.put(key, value);
    }

    /**
     * Copies all of the mappings from the specified map to this map (optional operation).
     */
    public void putAll(HMap t) {
        isNull(t);
    }

    /**
     * Removes the mapping for this key from this map if it is present (optional operation).
     */
    public Object remove(Object key) {
        isNull(key);
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

    class HEntry implements HMap.HEntry {

        private Object key;
        private Object value;

        public HEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Compares the specified object with this entry for equality.
         */
        public boolean equals(Object o) {
            HEntry entry = (HEntry)o;
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
            return getKey().hashCode();
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

    private class EntrySet extends SetAdapter implements HSet {
    
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        public void clear() {
            MapAdapter.this.clear();
        }
    
        public boolean contains(Object o) {
            if(o == null)
                throw new NullPointerException();
            HEntry e = (HEntry) o;
            return MapAdapter.this.containsKey(e.getKey());
        }

        public HIterator iterator() {
            return new EntryIterator();
        }

        private class EntryIterator implements HIterator {

            Enumeration keys = hashtable.keys();
            Object lastRetKey = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                lastRetKey = keys.nextElement();
                return new HEntry(lastRetKey, hashtable.get(lastRetKey));
            }

            public void remove() {

                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;
            }
            
        }

    }

}