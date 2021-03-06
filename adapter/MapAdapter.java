
package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HMap
 */
public class MapAdapter implements HMap {

    private Hashtable hashtable = new Hashtable();

    /**
     * Controlla se l'oggetto è null
     * @param o oggetto che deve essere analizzato
     * @throws NullPointerException se l'oggetto risulta null
     */
    protected void isNull(Object o) {
        if(o == null)
            throw new NullPointerException();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo chiama hashtable.clear()</p>
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo controlla se l'hashtable contiene la key</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsKey(Object key) {
        isNull(key);
        return hashtable.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo controlla se l'hashtable contiene il value</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsValue(Object value) {
        isNull(value);
        return hashtable.contains(value);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce un Set formato da Entry rappresentativo dei mapping di Map</p>
     */
    public HSet entrySet() {
        return new EntrySet();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo controlla se l'hashtable di un oggetto contiene lo stesso mapping</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean equals(Object obj) {
        HMap m = (HMap) obj;
        return entrySet().equals(m.entrySet());
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo chiama hashtable.get(Object key)</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public Object get(Object key) {
        isNull(key);
        return hashtable.get(key);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo calcola l'hashcode di ogni entry restituendo la loro somma</p>
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
     * <p>Questo metodo chiama hashtable.isEmpty()</p>
     */
    public boolean isEmpty() {
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo crea un set contenente le chiavi della mappa</p>
     */
    public HSet keySet() {
        return new KeySet();
    }
 
    /**
     * {@inheritDoc}
     * <p>Questo metodo chiama hashtable.put(key, value)</p>
     * @throws NullPointerException {@inheritDoc}
     */
    public Object put(Object key, Object value) {
        isNull(key);
        isNull(value);
        return hashtable.put(key, value);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metofo chiama hashtable.put(key, value) per ogni HMap t</p>
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
     * <p>Questo metodo chiama hashtable.remove(key)</p>
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
     * <p>Questo metodo chiama hashtable.size()</p>
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce una HCollection dei valori contenuti</p>
     */
    public HCollection values() {
        return new Values();
    }

    private class Values extends CollectionAdapter {

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
            isNull(o);
            return MapAdapter.this.containsValue(o);
        }

        public boolean equals(Object o){
            if (o == this)
                return true;

            HCollection ccast = null;

            try {
                ccast = (HCollection) o;
            }catch(ClassCastException e) {
                return false;
            }
            
            HCollection c = (HCollection) o;
            if (c.size() != size())
                return false;

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

        /**
         * This method check if the collection is empty
         */
        public boolean isEmpty() {
            return size() == 0;
        }

        /**
         * This method returns an iterator over the collection
         */
        public HIterator iterator() {
            return new EntryIterator();
        }

        /**
         * Iterator class for the collection
         */
        private class EntryIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object last = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                last = keys.nextElement();
                return MapAdapter.this.get(last);
            }

            public void remove() {
                if(last == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(last);
                last = null;
            }
            
        }

        public boolean remove(Object o) {
            isNull(o);
            boolean result = false;
            HSet set = MapAdapter.this.entrySet();
            HIterator iter = set.iterator();
            while(iter.hasNext()) {
                HMap.HEntry e = (HMap.HEntry) iter.next();
                if(e.getValue().equals(o) && MapAdapter.this.remove(e.getKey()) != null) {
                    result = true;
                } 
            }
            return result;
        }

        public int size() {
            return MapAdapter.this.size();
        }

    }

    static class Entry implements HMap.HEntry {
        
        private Object key = null;
        private Object value = null;

        protected void isNull(Object o) {
            if(o == null)
                throw new NullPointerException();
        }

        public Entry(Object key, Object value) {
            isNull(key);
            isNull(value);
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
            isNull(value);
            Object result = this.value;
            this.value = value;
            return result;
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
            if(getKey() == null)
                return 0;
            return (getKey()==null   ? 0 : getKey().hashCode()) ^(getValue()==null ? 0 : getValue().hashCode());
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
            isNull(o);
            HMap.HEntry e = (HMap.HEntry) o;
            Object key = e.getKey();
            if(MapAdapter.this.containsKey(key))
                return true;
            return false;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public HIterator iterator() {
            return new EntryIterator();
        }

        private class EntryIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object obj = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                obj = keys.nextElement();
                Object value = hashtable.get(obj);
                return new Entry(obj, value);
            }

            public void remove() {
                try {
                    isNull(obj);
                } catch(NullPointerException e) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(obj);
                obj = null;
            }
            
        }

        public boolean remove(Object o) {
            isNull(o);
            HMap.HEntry e = (HMap.HEntry) o;
            Object obj = e.getKey();
            if(MapAdapter.this.remove(obj) != null)
                return false;
            return true;
        }

        public int size() {
            return MapAdapter.this.size();
        }
    
    }
   
    private class KeySet extends EntrySet {

        public boolean contains(Object o) {
            isNull(o);
            return MapAdapter.this.containsKey(o);
        }

        public HIterator iterator() {
            return new KeyIterator();
        }

        private class KeyIterator implements HIterator {

            private Enumeration keys = hashtable.keys();
            private Object obj = null;

            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            public Object next() {
                obj = keys.nextElement();
                return obj;
            }

            public void remove() {
                if(obj == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(obj);
                obj = null;
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