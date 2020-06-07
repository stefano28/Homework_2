package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HMap
 */
public class MapAdapter implements HMap {

    private Hashtable hashtable = new Hashtable();

    /**
     * Check if the Object is null
     * @param o
     * @throws NullPointerException
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

    /**
     * @inheritDoc
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * @inheritDoc
     */
    public boolean containsKey(Object key) {
        isNull(key);
        return hashtable.containsKey(key);
    }

    /**
     * @inheritDoc
     */
    public boolean containsValue(Object value) {
        isNull(value);
        return hashtable.contains(value);
    }

    /**
     * @inheritDoc
     */
    public HSet entrySet() {
        return new EntrySet();
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    public Object get(Object key) {
        isNull(key);
        return hashtable.get(key);
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * @inheritDoc
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
     * @inheritDoc
     */
    public Object put(Object key, Object value) {
        isNull(key);
        isNull(value);
        return hashtable.put(key, value);
    }

    /**
     * @inheritDoc
     */
    public void putAll(HMap t) { //
        isNull(t);
        HSet s = t.entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (HEntry) iterator.next();
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * @inheritDoc
     */
    public Object remove(Object key) {
        isNull(key);
        Object value = hashtable.get(key);
        hashtable.remove(key);
        return value;
    }

    /**
     * @inheritDoc
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * @inheritDoc
     */
    public HCollection values() { //
        HSet es = entrySet();
        HIterator it = es.iterator();
        HCollection vc = new CollectionAdapter();
        while(it.hasNext()) {
            HEntry e = (HEntry) it.next();
            vc.add(e.getValue());
        }
        return vc;
    }

    /**
     * @inheritDoc
     */
    class HEntry implements HMap.HEntry {

        private Object key;
        private Object value;

        public HEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * @inheritDoc
         */
        public Object getKey() {
            return key;
        }

        /**
         * @inheritDoc
         */
        public Object getValue() {
            return value;
        }

        /**
         * @inheritDoc
         */
        public Object setValue(Object value) {
            this.value = value;
            return value;
        }

        /**
         * @inheritDoc
         */
        public boolean equals(Object o) {
            HEntry entry = (HEntry)o;
            if(entry.getKey().equals(key) && entry.getValue().equals(value))
                return true;
            return false;
        }

        /**
         * @inheritDoc
         */
        public int hashCode() {
            return getKey().hashCode();
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

                if(lastRetKey == null)
                    throw new IllegalStateException();
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;
            }
            
        }

    }

}