public class Client {
    public static void main(String[] args) {
        MapAdapter m = new MapAdapter();
        m.put(1, "ciao");
        System.out.println(m.containsKey(1));
        System.out.println(m.get(1));
        m.remove(1);
        System.out.println(m.containsKey(1));
        m.put(2, "casa");
        System.out.println(m.get(2));
        System.out.println(m.size());
        m.clear();
        System.out.println(m.size());
    }
}