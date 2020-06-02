package adapter;

public class CollectionClient {
    public static void main(String[] args) {
        HCollection c = new CollectionAdapter();
        c.add("ciao");
    }
}