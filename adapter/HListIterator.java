package adapter;

public interface HListIterator extends HIterator{
    public boolean hasPrevious();
    public Object previous();
    public int nextIndex();
    public int previousIndex();
    public void set(Object o);
    public void add(Object o);
}