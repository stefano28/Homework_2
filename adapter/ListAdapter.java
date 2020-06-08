package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Adapter for HList
 */
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
     * @inheritDoc
     */
    public void add(int index, Object element) throws ArrayIndexOutOfBoundsException {
        isNull(element);
        vector.insertElementAt(element, index);
    }

    /**
     * Appends the specified element to the end of this list (optional operation).
     */
    public boolean add(Object o) {
        isNull(o);
        vector.addElement(o);
        return true;
    }

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator (optional operation).
     */
    public boolean addAll(HCollection c) { //
        isNull(c);
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(o);
            flag = true;
        }
        return flag;
    }

    /**
     * @inheritDoc
     */
    public boolean addAll(int index, HCollection c) { //
        isNull(c);
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException();
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
     * Removes all of the elements from this list (optional operation).
     */
    public void clear() {
        vector.removeAllElements();
    }

    /**
     * Returns true if this list contains the specified element.
     */
    public boolean contains(Object o) {
        isNull(o);
        return vector.contains(o);
    }

    /**
     * Returns true if this list contains the specified element.
     */
    public boolean containsAll(HCollection c) { //
        isNull(c);
        HIterator it = c.iterator();
        while(it.hasNext()) {
            if(!contains(it.next()))
                return false;
        }
        return true;
    }

    /**
     * Compares the specified object with this list for equality.
     */
    public boolean equals(Object o) { //
        if(o == this)
            return true;
        if (!(o instanceof HList))
            return false;
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
     * @inheritDoc
     */
    public Object get(int index) throws ArrayIndexOutOfBoundsException {
        return vector.get(index);
    }

    /**
     * Returns the hash code value for this list.
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
     * @inheritDoc
     */
    public int indexOf(Object o) {
        isNull(o);
        return vector.indexOf(o);
    }

    /**
     * Returns true if this list contains no elements.
     */
    public boolean isEmpty() {
        return vector.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator() {
        return new Iterator();
    }


    /**
     * @inheritDoc
     */
    public int lastIndexOf(Object o) {
        isNull(o);
        return vector.lastIndexOf(o);
    }

    /**
     * @inheritDoc
     */
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    /**
     * @inheritDoc
     */
    public HListIterator listIterator(int index) {
        return new ListIterator(index);
    }

    /**
     * @inheritDoc
     */
    public Object remove(int index) {
        Object o = get(index);
        vector.removeElementAt(index);
        return o;
    }

    /**
     * Removes the first occurrence in this list of the specified element (optional operation).
     */
    public boolean remove(Object o) {
        isNull(o);
        return vector.removeElement(o);
    }

    /**
     * Removes from this list all the elements that are contained in the specified collection (optional operation).
     */
    public boolean removeAll(HCollection c) { //
        isNull(c);
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            remove(o);
            flag = true;
        }
        return flag;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection (optional operation).
     */
    public boolean retainAll(HCollection c) {
        isNull(c);
        boolean flag = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!c.contains(o)) {
                remove(o);
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @inheritDoc
     */
    public Object set(int index, Object element) {
        isNull(element);
        Object o = get(index);
        vector.setElementAt(element, index);
        return o;
    }

    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return vector.size();
    }

    /**
     * @inheritDoc
     */
    public HList subList(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray() {
        Object[] v = new Object[size()];
        HIterator it = iterator();
        int i = 0;
        while(it.hasNext()) {
            v[i] = it.next();
            i++;
        }
        return v;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     */
    public Object[] toArray(Object[] a) {
        return toArray();
    }

    private class Iterator implements HIterator { //

        protected int cursor = 0;
        protected int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            if(!hasNext())
                throw new NoSuchElementException();
            Object o = get(cursor);
            lastRet = cursor;
            cursor++;
            return o;
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            ListAdapter.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
        }
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

    private class SubList extends ListAdapter {
        int from = 0;
        int to = 0;
        
        public SubList(int from, int to) {
            this.from = from;
            this.to = to;
        }

        private void isValid(int index) {
            if(index > to || index < from)
                throw new IndexOutOfBoundsException();
        }

        public void add(int index, Object element) {
            isValid(index);
            super.add(index + from, element);
            to++;
        }

        public boolean add(Object o) {
            super.add(to, o);
            to++;
            return true;
        }

        public boolean addAll(HCollection c) {
            super.addAll(to, c);
            to += c.size();
            return true;
        }

        public boolean addAll(int index, HCollection c) {
            isValid(index);
            super.addAll(index + from, c);
            to += c.size();
            return true;
        }

        public void clear() {
            int i = from;
            while(i < to) {
                super.remove(i);
                i++;
            }
        }

        public Object get(int index) {
            isValid(index);
            return super.get(index + from);
        }

        public int indexOf(Object o) {
            int index = super.indexOf(o);
            isValid(index);
            return index;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public HIterator iterator() {
            return new SubListIterator(0);
        }

        public int lastIndexOf(Object o) {
            int index = super.lastIndexOf(o);
            if(index < from || index >= to) {
                return -1;
            }
            return index;
        }

        public HListIterator listIterator() {
            return new SubListIterator(0);
        }

        public HListIterator listIterator(int index) {
            return new SubListIterator(index);
        }

        public Object remove(int index) {
            isValid(index);
            Object o = super.remove(from + index);
            to--;
            return o;
        }

        public boolean remove(Object o) {
            isNull(o);
            if(super.remove(o)) {
                to--;
                return true; 
            }
            return false;
        }

        public boolean removeAll(HCollection c) {
            HIterator cIter = c.iterator();
            boolean result = false;
            while(cIter.hasNext()) {
                if(remove(cIter.next())) {
                    to--;
                    result = true;
                }
            }
            return result;
        }

        public boolean retainAll(HCollection c) {
            isNull(c);
            HIterator iter = iterator();
            boolean result = false;
            while(iter.hasNext()) {
                Object value = iter.next();
                if(!c.contains(value)) {
                    iter.remove();
                    to--;
                    result = true;
                }
            }
            return result;
        }

        public Object set(int index, Object element) {
            isValid(index);
            isNull(element);
            return super.set(from + index, element);
        }

        public int size() {
            return to - from;
        }

        private class SubListIterator implements HListIterator {
            private HListIterator it = null;

            SubListIterator(int index) {
                it = ListAdapter.this.listIterator(index);
            }

            public boolean hasNext() {
                return nextIndex() < to;
            }

            public Object next() {
                if(hasNext()) { // hasNext() di subList, che controlla indici
                    return it.next(); // next usa l'hasNext() di List
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
                return it.nextIndex() - from;
            }

            public int previousIndex() {
                return it.previousIndex() - from;
            }

            public void remove() {
                it.remove();
                to--;
            }

            public void set(Object o) {
                it.set(o);
            }

            public void add(Object o) {
                it.add(o);
                to++;
            }
        }
    }
}