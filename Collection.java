import java.util.*;

public interface Collection<E> {
    public boolean add(E e);
    public boolean addAll(Collection <? extends E> c);
}