package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

public class ListAdapter implements HList {

    private Vector vector = new Vector();

    public void add(int index, Object element) {
        if(element == null)
            throw new NullPointerException();
        try {
            vector.insertElementAt(element, index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean add(Object o) {
        if(o == null)
            throw new NullPointerException();
        vector.addElement(o);
        return true;
    }

    public boolean addAll(HCollection c) { //
        if(c == null)
            throw new NullPointerException();
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(o);
            flag = true;
        }
        return flag;
    }

    public boolean addAll(int index, HCollection c) { //
        if(c == null)
            throw new NullPointerException();
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

    public void clear() {
        vector.removeAllElements();
    }

    public boolean contains(Object o) {
        if(o == null)
            throw new NullPointerException();
        return vector.contains(o);
    }

    public boolean containsAll(HCollection c) { //
        if(c == null)
            throw new NullPointerException();
        HIterator it = c.iterator();
        while(it.hasNext()) {
            if(!contains(it.next()))
                return false;
        }
        return true;
    }

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

    public Object get(int index) {
        try {
            return get(index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int hashCode() { //
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    public int indexOf(Object o) {
        if(o == null)
            throw new NullPointerException();
        return vector.indexOf(o);
    }

    public boolean isEmpty() {
        return vector.isEmpty();
    }

    public HIterator iterator() {
        return new Iterator();
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

    public int lastIndexOf(Object o) {
        if(o == null)
            throw new NullPointerException();
        return vector.lastIndexOf(o);
    }

    public HListIterator listIterator() {
        return new ListIterator(0);
    }

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

    public Object remove(int index) {
        Object o = get(index);
        vector.removeElementAt(index);
        return o;
    }

    public boolean remove(Object o) {
        if(o == null)
            throw new NullPointerException();
        return vector.removeElement(o);
    }

    public boolean removeAll(HCollection c) { //
        if(c == null)
            throw new NullPointerException();
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            remove(o);
            flag = true;
        }
        return flag;
    }

    public boolean retainAll(HCollection c) {
        if(c == null)
            throw new NullPointerException();
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

    public Object set(int index, Object element) {
        if(element == null) {
            throw new NullPointerException();
        }
        Object o = get(index);
        vector.setElementAt(element, index);
        return o;
    }

    public int size() {
        return vector.size();
    }

    public HList subList(int fromIndex, int toIndex) {
        return new SubList(fromIndex, toIndex);
    }

    public Object[] toArray() {
        return null;
    }

    public Object[] toArray(Object[] a) {
        return null;
    }

    private class SubList extends ListAdapter {
        int from = 0;
        int to = 0;
        
        public SubList(int from, int to) {
            this.from = from;
            this.to = to;
        }

        public void add(int index, Object element) {
            super.add(index + from, element);
        }

        public boolean add(Object o) {
            super.add(from + to, o);
            from++;
            return true;
        }

        public boolean addAll(HCollection c) {
            return super.addAll(from + to, c);

        }

        public boolean addAll(int index, HCollection c) {
            return super.addAll(index + from, c);

        }

        public void clear() {
            int i = to;
            while(i < from) {
                super.remove(i);
                i++;
            }
        }

        public boolean contains(Object o) {
            return true;
        }

        public boolean containsAll(HCollection c) {
            return true;
        }

        public boolean equals(Object o) {
            return true;
        }

        public Object get(int index) {
            return null;
        }

        public int hashCode() {
            return 0;
        }

        public int indexOf(Object o) {
            return 0;
        }

        public boolean isEmpty() {
            return to == from;
        }

        public Iterator iterator() {
            return null;
        }

        public int lastIndexOf(Object o) {
            return 0;
        }

        public HListIterator listIterator() {
            return null;
        }

        public HListIterator listIterator(int index) {
            return null;
        }

        public Object remove(int index) {
            return super.remove(from + index);
        }

        public boolean remove(Object o) {
            return true;
        }

        public boolean removeAll(HCollection c) {
            return true;
        }

        public boolean retainAll(HCollection c) {
            return true;
        }

        public Object set(int index, Object element) {
            return null;
        }

        public int size() {
            return to - from;
        }

        public Object[] toArray() {
            return null;
        }

        public Object[] toArray(Object[] a) {
            return null;
        }

    }

}