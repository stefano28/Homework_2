package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList {

    private Vector vector = new Vector();

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
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     */
    public boolean add(Object o) {
        isNull(o);
        vector.addElement(o);
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     */
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean contains(Object o) {
        isNull(o);
        return vector.contains(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     */
    public int indexOf(Object o) {
        isNull(o);
        return vector.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public HIterator iterator() {
        return new Iterator();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public int lastIndexOf(Object o) {
        isNull(o);
        return vector.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    /**
     * {@inheritDoc}
     */
    public HListIterator listIterator(int index) {
        return new ListIterator(index);
    }

    /**
     * {@inheritDoc}
     */
    public Object remove(int index) {
        Object o = get(index);
        vector.removeElementAt(index);
        return o;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean remove(Object o) {
        isNull(o);
        return vector.removeElement(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
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
     */
    public Object set(int index, Object element) {
        isNull(element);
        Object o = get(index);
        vector.setElementAt(element, index);
        return o;
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return vector.size();
    }

    /**
     * {@inheritDoc}
     */
    public HList subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }


    /**
     * Returns an array containing all of the elements in this set.
     * @return an array containing all of the elements in this set.
     * @throws NullPointerException if the specified array is null.
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
     * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this set are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this set.
     * @throws NullPointerException if the specified array is null.
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
                if(hasNext()) { // hasNext() di subList, che controlla indici
                    return it.next(); // next usa l'hasNext() di ListAdapter
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