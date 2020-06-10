
package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

public class MapAdapter implements HMap {

    private Hashtable hashtable = new Hashtable();

    /**
     * Check if the Object is null.
     * @param o object to be analyzed.
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls hashtable.clear()</p>
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     * <p>This method check if the hashtable contains the key</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        isNull(key);
        return hashtable.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * <p>This method check if the hashtable contains the value</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        isNull(value);
        return hashtable.contains(value);
    }

    /**
     * {@inheritDoc}
     * <p></p>
     */
    public HSet entrySet() {
        return new EntrySet();
    }


    /**
     * {@inheritDoc}
     * <p>This method check if the hashtable of Object o contains the same mapping</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean equals(Object obj) {
        HMap m = (HMap) obj;
        return entrySet().equals(m.entrySet());
    }

    /**
     * {@inheritDoc}
     * <p>This method calls hashtable.get(Object key)</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public Object get(Object key) {
        isNull(key);
        return hashtable.get(key);
    }

    /**
     * {@inheritDoc}
     * <p>This method calculate the total hash code of this map. The total hashcode is the sum of the hash codes of each entry</p>
     */
    public int hashCode() {
        int sum = 0;
        HSet s = entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (Entry) iterator.next();
            sum += e.hashCode();
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     * <p>This method calls hashtable.isEmpty()</p>
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>This method create a set with the map's keys</p>
     */
    public HSet keySet() {
        return new KeySet();
    }
 
    /**
     * {@inheritDoc}
     * <p>This method calls hashtable.put(key, value)</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public Object put(Object key, Object value) {
        isNull(key);
        isNull(value);
        return hashtable.put(key, value);
    }

    /**
     * {@inheritDoc}
     * <p>This method calls hashtable.put(key, value) for each element inside HMap t</p>
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void putAll(HMap t) {
        isNull(t);
        HSet s = t.entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (Entry) iterator.next();
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * {@inheritDoc}
     * <p>This method calls hashtable.remove(key)</p>
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
     * <p>This method calls hashtable.size()</p>
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
     * <p></p>
     */
    public HCollection values() {
        return new Values();
    }

    /**
     * This class create a collection of map's values.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.
     */
    private class Values extends CollectionAdapter {

        /**
         * This operation is not supported
         */
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        /**
         * This operation is not supported
         */
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        /**
         * This method calls the clear() method from MapAdapter
         */
        public void clear() {
            MapAdapter.this.clear();
        }

        /**
         * This method calls the containsValue(o) method from MapAdapter
         */
        public boolean contains(Object o) {
            isNull(o);
            return MapAdapter.this.containsValue(o);
        }

        public boolean equals(Object o){ //
            if (o == this)
                return true;
                
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

        public boolean isEmpty() {
            return size() == 0;
        }

        public HIterator iterator() {
            return new Iterator();
        }

        private class Iterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object lastRetKey = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                lastRetKey = keys.nextElement();
                return MapAdapter.this.get(lastRetKey);
            }

            public void remove() {
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;
            }
            
        }

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

        public int size() {
            return MapAdapter.this.size();
        }

    }

    static class Entry implements HMap.HEntry {
        
        private Object key = null;
        private Object value = null;

        Entry(Object key, Object value) {
            if(key == null || value == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object value) {
            if(value == null) {
                throw new NullPointerException();
            }
            Object tmp = this.value;
            this.value = value;
            return tmp;
        }

        public boolean equals(Object o) {
            Entry e = (Entry) o;
            if((getKey()==null ?
            e.getKey()==null : getKey().equals(e.getKey()))  &&
           (getValue()==null ?
            e.getValue()==null : getValue().equals(e.getValue()))) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (getKey()==null   ? 0 : getKey().hashCode()) ^
            (getValue()==null ? 0 : getValue().hashCode());
        }

    }

    private class EntrySet extends SetAdapter {
    
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
            if(o == null) {
                throw new NullPointerException();
            }
            HMap.HEntry e = (HMap.HEntry) o;
            return MapAdapter.this.containsKey(e.getKey());
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public HIterator iterator() {
            return new EntryIterator();
        }

        private class EntryIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object lastRetKey = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                lastRetKey = keys.nextElement();
                return new Entry(lastRetKey, hashtable.get(lastRetKey));
            }

            public void remove() {
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;
            }
            
        }

        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HMap.HEntry e = (HMap.HEntry) o;
            return MapAdapter.this.remove(e.getKey()) != null;
        }

        public int size() {
            return MapAdapter.this.size();
        }
    
    }
   
    private class KeySet extends EntrySet {

        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.containsKey(o);
        }

        public HIterator iterator() {
            return new KeyIterator();
        }

        private class KeyIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object lastRetKey = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                lastRetKey = keys.nextElement();
                return lastRetKey;
            }

            public void remove() {
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;
            }
            
        }

        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.remove(o) != null;
        }

    }

}