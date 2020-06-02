package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

// Questa lista non accetta elementi null

public class ListAdapter implements HList {

    private Vector v = new Vector();

    /**
     *
     */
    @Override
    public void add(int index, Object element) {
        if(element == null) {
            throw new NullPointerException();
        }
        try {
            v.insertElementAt(element, index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

     /**
     *
     */
    @Override
    public boolean add(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        v.addElement(o);
        return true;
    }

    /**
     * 
    */
    @Override
    public boolean addAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(o); // Contiene controllo null
            flag = true;
        }
        return flag;
    }

    /**
     * 
    */
    @Override
    public boolean addAll(int index, HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(index, o); // Contiene controllo null
            flag = true;
            index++;
        }
        return flag;
    }

    /**
     * 
    */
    @Override
    public void clear() {
        v.removeAllElements();
    }

    /**
     * 
    */
    @Override
    public boolean contains(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.contains(o);
    }

    /**
     * 
    */
    @Override
    public boolean containsAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        HIterator it = c.iterator();
        while(it.hasNext()) {
            if(!contains(it.next()))
                return false;
        }
        return true;
    }

    /**
     * 
    */
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if (!(o instanceof HList)) {
            return false;
        }

        HListIterator it1 = listIterator();
        HListIterator it2 = ((HList) o).listIterator();
        while (it1.hasNext() && it2.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(it1.hasNext() || it2.hasNext());
    }

    /**
     * 
    */
    @Override
    public Object get(int index) {
        try {
            return get(index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 
    */
    @Override
    public int hashCode() {
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * 
    */
    @Override
    public int indexOf(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.indexOf(o);
    }

    /**
     * 
    */
    @Override
    public boolean isEmpty() {
        return v.isEmpty();
    }

    /**
     * 
    */
    @Override
    public HIterator iterator() {
        return new Iterator();
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

    /**
     * 
    */
    @Override
    public int lastIndexOf(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.lastIndexOf(o);
    }

    /**
     * 
    */
    @Override
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    /**
     * 
    */
    @Override
    public HListIterator listIterator(int index) {
        return new ListIterator(index);
    }

    private class ListIterator extends Iterator implements HListIterator {
        
        ListIterator(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            Object o = get(--cursor);
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

        public void add(Object o) {
            ListAdapter.this.add(cursor, o);
            cursor++;
            lastRet = -1;
        }

    }

    /**
     * 
    */
    @Override
    public Object remove(int index) {
        Object o = get(index);  // Contiene controllo bounds
        v.removeElementAt(index);
        return o;
    }

    /**
     * 
    */
    @Override
    public boolean remove(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.removeElement(o);
    }

    /**
     * 
    */
    @Override
    public boolean removeAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            remove(o); // Contiene controllo null
            flag = true;
        }
        return flag;
    }

    /**
     * 
    */
    @Override
    public boolean retainAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!c.contains(o)) {
                remove(o); // Contiene controllo null
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 
    */
    @Override
    public Object set(int index, Object element) {
        if(element == null) {
            throw new NullPointerException();
        }
        Object o = get(index); // Throws IndexOutOfBoundsException
        v.setElementAt(element, index);
        return o;
    }

    /**
     * 
    */
    @Override
    public int size() {
        return v.size();    //Integer.maxValue??
    }

    /**
     * 
    */
    @Override
    public HList subList(int fromIndex, int toIndex) {
        return null;
    }

    /**
     * 
    */
    @Override
    public Object[] toArray() {
        return null;
    }

    /**
     * 
    */
    @Override
    public Object[] toArray(Object[] a) {
        return null;
    }

}