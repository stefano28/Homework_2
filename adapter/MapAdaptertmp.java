package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HMap
 */
public class MapAdaptertmp implements HMap {

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
     * {@inheritDoc}</br>
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        isNull(key);
        return hashtable.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        isNull(value);
        return hashtable.contains(value);
    }

    /**
     * {@inheritDoc}
     */
    public HSet entrySet() {
        return new EntrySet();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public Object get(Object key) {
        isNull(key);
        return hashtable.get(key);
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() { //
        int sum = 0;
        HSet s = entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (HEntry) iterator.next();
            sum += e.hashCode();
        }
        return sum;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
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
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public Object put(Object key, Object value) {
        isNull(key);
        isNull(value);
        return hashtable.put(key, value);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public Object remove(Object key) {
        isNull(key);
        Object value = hashtable.get(key);
        hashtable.remove(key);
        return value;
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
     */
    public HCollection values() { //
        return new ValueCollection();
    }

    /**
     * {@inheritDoc}
     */


    private class ValueCollection extends CollectionAdapter {

        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public void clear() {
            MapAdapter.this.clear();
        }

        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.containsValue(o);
        }

        public boolean equals(Object o){
            if (o == this) {
                return true;
            }
            if (!(o instanceof HCollection)) {
                return false;
            }
            HCollection c = (HCollection) o;
            if (c.size() != size()) {
                return false;
            }
            try {
                return containsAll(c);
            }
            catch (ClassCastException cce)   {
                return false;
            }
            catch (NullPointerException npe) {
                return false;
            }
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public HIterator iterator() {
            return new ValueIterator();
        }

        private class ValueIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return MapAdapter.this.get(lastRetKey);
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            boolean flag = false;
            HSet es = MapAdapter.this.entrySet();
            HIterator it = es.iterator();
            while(it.hasNext()) {
                HMap.HEntry e = (HMap.HEntry) it.next();
                if(e.getValue().equals(o)) {
                    if(MapAdapter.this.remove(e.getKey()) != null) {
                        flag = true;
                    }
                } 
            }
            return flag;
        }

        @Override
        public int size() {
            return MapAdapter.this.size();
        }

    }
    
    class HEntry implements HMap.HEntry {

        private Object key;
        private Object value;

        public HEntry(Object key, Object value) {
            this.key = key;
            this.value = value;
        }

        /**
         * {@inheritDoc}
         */
        public Object getKey() {
            return key;
        }
        /**
         * {@inheritDoc}
         */
        public Object getValue() {
            return value;
        }

        /**
         * {@inheritDoc}
         * @throws NullPointerException {@inheritDoc}
         */
        public Object setValue(Object value) {
            this.value = value;
            return value;
        }

        /**
         * {@inheritDoc}
         */
        public boolean equals(Object o) {
            HEntry entry = (HEntry)o;
            if(entry.getKey().equals(key) && entry.getValue().equals(value))
                return true;
            return false;
        }

        /**
         * {@inheritDoc}
         */
        public int hashCode() {
            return getKey().hashCode();
        }
    }

    private class EntrySet extends SetAdapter {
    
        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public void clear() {
            MapAdapter.this.clear();
        }
    
        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HMap.HEntry e = (HMap.HEntry) o;
            return MapAdapter.this.containsKey(e.getKey());
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public HIterator iterator() {
            return new EntryIterator();
        }

        private class EntryIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return new HEntry(lastRetKey, hashtable.get(lastRetKey));
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }
    }

    private class KeySet extends EntrySet {

        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.containsKey(o);
        }

        @Override
        public HIterator iterator() {
            return new KeyIterator();
        }

        private class KeyIterator implements HIterator {

            private Enumeration keys = h.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return lastRetKey;
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }
    }
}