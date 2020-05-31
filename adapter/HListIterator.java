package adapter;

public interface HListIterator {
    public boolean hasNext();
    public Object next();
    public boolean hasPrevious();
    public Object previous();
    public int nextIndex();
    public int previousIndex();
    public void remove();
    public void set(Object o);
    public void add(Object o);
}