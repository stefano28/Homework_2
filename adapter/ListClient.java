package adapter;

public class ListClient {
    public static void main(String[] args) {
        HList list = new ListAdapter();
        try {
            list.add(10, "OutOfBound");
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("OutOfBound catturata");
        }
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add("string");
        System.out.println("Stampa degli insermienti");
        print(list);
        HCollection c = new CollectionAdapter();
        c.add("c1");
        c.add("c2");
        c.add("c3");
        list.addAll(c);
        System.out.println("Stampa addAll collection");
        print(list);

    }

    static void print(HList list) {
        HListIterator iter = list.listIterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}