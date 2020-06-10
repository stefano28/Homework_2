package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Adapter for HSet
 */
public class ListAdapter implements HList {

    private Vector vector = new Vector();

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
     * <p>Questo metodo effettua un inserimento nel vector richiamando vector.insertElement(element, index)</p>
     */
    public void add(int index, Object element) {
        isNull(element);
        try {
            vector.insertElementAt(element, index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo aggiunge un elemento al vector richiamando vector.addElement(o)</p>
     */
    public boolean add(Object o) {
        isNull(o);
        vector.addElement(o);
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.addElement(o) per ogni elmento della HCollection</p>
     */
    public boolean addAll(HCollection c) {
        isNull(c);
        boolean result = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(o);
            result = true;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.add(index, o) per ogni elmento della HCollection</p>
     */
    public boolean addAll(int index, HCollection c) {
        isNull(c);
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();
        boolean result = false;
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            Object o = iter.next();
            add(index, o);
            result = true;
            index++;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo rimuove tutti gli elementi richiamando vector.removeAllElements()</p>
     */
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo verifica se un oggetto appartiene alla lista richiamando vector.contains(o)</p>
     */
    public boolean contains(Object o) {
        isNull(o);
        return vector.contains(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo verifica se ogni elemento di una HCollection appartiene alla lista</p>
     */
    public boolean containsAll(HCollection c) {
        isNull(c);
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            if(!contains(iter.next()))
                return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo verifica se due liste sono uguali</p>
     */
    public boolean equals(Object o) {
        try {
            isNull(o);
        }catch(NullPointerException e) {
            return false;
        }
        if(o == this)
            return true;
        HList casted = null;
        try {
            casted = (HList)o;
        } catch(ClassCastException e) {
            return false;
        }
        if(casted.size() != size())
            return false;
        HListIterator iter = listIterator();
        HListIterator oiter = casted.listIterator();
        while (iter.hasNext() && oiter.hasNext()) {
            if(!iter.next().equals(oiter.next())) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo richiama vector.elementAt(index)</p>
     */
    public Object get(int index) {
        try {
            return vector.elementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo calcola l'hashcode totale della lista</p>
     */
    public int hashCode() { //
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.indexOf(o)</p>
     */
    public int indexOf(Object o) {
        isNull(o);
        return vector.indexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo richiama vector.isEmpty()</p>
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce un iteratore sulla lista</p>
     */
    public HIterator iterator() {
        return new Iterator();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.lastIndexOf(o)</p>
     */
    public int lastIndexOf(Object o) {
        isNull(o);
        return vector.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce un listIterator</p>
     */
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce un listIterator a partire da un indice</p>
     */
    public HListIterator listIterator(int index) {
        return new ListIterator(index);
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo richiama vector.removeElement(index)</p>
     */
    public Object remove(int index) {
        Object o = get(index);
        vector.removeElementAt(index);
        return o;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.removeElement(o)</p>
     */
    public boolean remove(Object o) {
        isNull(o);
        return vector.removeElement(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo rimuove tutti gli elementi presenti appartenenti a una HCollection</p>
     */
    public boolean removeAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator cit = c.iterator();
        while(cit.hasNext()) {
            if(remove(cit.next())) { // Contiene controllo null
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo rimuove tutti gli elementi non presenti nella HCollection</p>
     */
    public boolean retainAll(HCollection c) {
        isNull(c);
        boolean flag = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!c.contains(o)) {
                it.remove();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo richiama vector.setElement(element, index)</p>
     */
    public Object set(int index, Object element) {
        isNull(element);
        Object o = get(index);
        vector.setElementAt(element, index);
        return o;
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo richiama vector.size()</p>
     */
    public int size() {
        return vector.size();
    }

    /**
     * {@inheritDoc}
     * <p>Questo metodo restituisce una sublist</p>
     */
    public HList subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }


    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo restituisce un array contenente tutti gli elementi della lista</p>
     */
    public Object[] toArray(){
        Object[] v = new Object[size()];
        HIterator it = iterator();
        for(int i = 0; it.hasNext(); i++) {
            v[i] = it.next();
        }
        return v;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     * <p>Questo metodo inserisce tutti gli elementi della List in un array e lo restituisce, se il paramtero ha lunghezza maggiore
     * della size() il restante spazio verrà riempito da null, viceversa verranno troncati gli elementi</p>
     */
    public Object[] toArray(Object[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        Object[] v = new Object[a.length];
        HIterator it = iterator();
        if(a.length >= size()) {
            for(int i = 0; i < a.length && it.hasNext(); i++) {
                v[i] = it.next();
            }
            return v;
        }
        v = new Object[size()];
        for(int i = 0; i < size(); i++) {
            if(it.hasNext())
                v[i] = it.next();
            else
                v[i] = null;
        }
        return v;
    }

    private class ListIterator extends Iterator implements HListIterator {
        
        ListIterator(int index) {
            cursor = index;
        }

        public void add(Object o) {
            ListAdapter.this.add(cursor, o);
            cursor++;
            lastRet = -1;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursor--;
            Object o = get(cursor);
            lastRet = cursor;
            return o;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(Object o) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.set(lastRet, o); //Da testare indici limite
        }

    }

    private class Iterator implements HIterator {

        protected int cursor = 0;
        protected int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Object o = get(cursor);
            lastRet = cursor;
            cursor++;
            return o;
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
        }
    }

    private class SubList extends ListAdapter {
        private int offset = -1;
        private int size = -1;
        private ListAdapter l = null;
        
        public SubList(ListAdapter list, int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex > list.size() || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            l = list;
            offset = fromIndex;
            size = toIndex - fromIndex;
        }
    
        private void boundCheck(int index) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
        }
    
        private void boundCheckForAdd(int index) {
            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
        }
    
        public void add(int index, Object element) {
            boundCheckForAdd(index);
            l.add(offset + index, element);
            size++;
        }
    
        public boolean add(Object o) {
            l.add(offset + size, o);
            size++;
            return true;
        }
    
        public boolean addAll(HCollection c) {
            return addAll(size, c);
        }
    
        public boolean addAll(int index, HCollection c) {
            boundCheckForAdd(index);
            int cSize = c.size();
            if(cSize == 0) {
                return false;
            }
            l.addAll(offset + index, c);
            size += cSize;
            return true;
        }
    
        public void clear() {
            HIterator it = iterator();
            while(it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }
    
        public Object get(int index) {
            boundCheck(index);
            return l.get(offset + index);
        }
    
        public int indexOf(Object o) {
            int index = l.indexOf(o);
            if(index < offset || index >= (offset + size)) {
                return -1;
            }
            return index - offset;
        }
    
        public boolean isEmpty() {
            return size == 0;
        }
    
        public HIterator iterator() {
            return new SubListIterator(0);
        }
    
        public int lastIndexOf(Object o) {
            int index = l.lastIndexOf(o);
            if(index < offset || index >= (offset + size)) {
                return -1;
            }
            return index - offset;
        }
    
        public HListIterator listIterator() {
            return new SubListIterator(0);
        }
    
        public HListIterator listIterator(int index) {
            return new SubListIterator(index);
        }
    
        private class SubListIterator implements HListIterator {
            private HListIterator it = null;
    
            SubListIterator(int index) {
                it = ListAdapter.this.listIterator(index+offset);
            }
    
            public boolean hasNext() {
                return nextIndex() < size;
            }
    
            public Object next() {
                if(hasNext()) {
                    return it.next();
                }
                else {
                    throw new NoSuchElementException();
                }
            }
    
            public boolean hasPrevious() {
                return previousIndex() >= 0;
            }
    
            public Object previous() {
                if(hasPrevious()) {
                    return it.previous();
                }
                else {
                    throw new NoSuchElementException();
                }
            }
    
            public int nextIndex() {
                return it.nextIndex() - offset;
            }
    
            public int previousIndex() {
                return it.previousIndex() - offset;
            }
    
            public void remove() {
                it.remove();
                size--;
            }
    
            public void set(Object o) {
                it.set(o);
            }
    
            public void add(Object o) {
                it.add(o);
                size++;
            }
        }
    
        public Object remove(int index) {
            boundCheck(index);
            Object o = l.remove(offset + index);
            size--;
            return o;
        }
    
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HIterator it = iterator();
            while(it.hasNext()) {
                if(it.next().equals(o)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    
        public boolean removeAll(HCollection c) {
            if(c == null) {
                throw new NullPointerException();
            }
            boolean flag = false;
            HIterator cit = c.iterator();
            while(cit.hasNext()) {
                if(remove(cit.next())) {
                    flag = true;
                }
            }
            return flag;
        }
    
        public boolean retainAll(HCollection c) {
            if(c == null) {
                throw new NullPointerException();
            }
            boolean flag = false;
            HIterator it = iterator();
            while(it.hasNext()) {
                Object o = it.next();
                if(!c.contains(o)) {
                    it.remove();
                    flag = true;
                }
            }
            return flag;
        }
    
        public Object set(int index, Object element) {
            if(element == null) {
                throw new NullPointerException();
            }
            boundCheck(index);
            return l.set(offset + index, element);
        }
    
        public int size() {
            return size;
        }
    }
    

}