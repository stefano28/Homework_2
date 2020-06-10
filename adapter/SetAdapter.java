package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter for HSet
 */
public class SetAdapter implements HSet {

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
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo inserisce un elemento nll'hashtable usando il metodo hashtable.put(o)</p>
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
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo esegue un put nell'hashtable per ogni elemento della HCollection</p>
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
     * <p>Questo metodo richiama hashtable.clear()</p>
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama hashtable.containsKey(o)</P>
     */
    public boolean contains(Object o) {
        isNull(o);
        return hashtable.containsKey(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama contains(o) per ogni elemento della HCollection</p>
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
     * <p>Questo metodo confronta se le due hashtable hanno gli stessi mapping</p>
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
     * <p>Questo metodo calcola l'hashcode di ogni elemento restituendo la loro somma</p>
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
     * <p>Questo metodo richiama hashtable.isEmpty()</p>
     */
    public boolean isEmpty(){
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce un iterator sul Set</p>
     */
    public HIterator iterator(){
        return new SetIterator();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama hashtable.remove()</p>
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
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama remove() per ogni elemento della HCollection</p>
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
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama remove() se l'elemento non è contenuto per ogni elemento della HCollection</p>
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
     * <p>Questo metodo richiama hashtable.size()</p>
     */
    public int size(){
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo inserisce tutti gli elementi del Set in un array e lo restituisce</p>
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
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo inserisce tutti gli elementi del Set in un array e lo restituisce, se il paramtero ha lunghezza maggiore
     *  della size() il restante spazio verrà riempito da null, viceversa verranno troncati gli elementi</p>
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

        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        public Object next() {
            last = keys.nextElement();
            return last;
        }

        public void remove() {
            if(last == null)
                throw new IllegalStateException();
            SetAdapter.this.remove(last);
            last = null;
        }
    }
}