package adapter;

public interface HList extends HCollection {
    public void add(int index, Object element);
    public boolean addAll(int index, HCollection c);
    public Object get(int index);
    public int indexOf(Object o);
    public int lastIndexOf(Object o);
    public HListIterator listIterator();
    public HListIterator listIterator(int index);
    public Object remove(int index);
    public Object set(int index, Object element);
    public HList subList(int fromIndex, int toIndex);
}