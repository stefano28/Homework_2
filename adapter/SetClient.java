package adapter;

public class SetClient {
    public static void main(String[] args) {
        HSet s = new SetAdapter();
        s.add("ciao");
        System.out.print(s.contains("ciao"));
        s.add(1);
        s.add("ciao");
        s.add(222);
        Object[] v = s.toArray();
        for(int i = 0; i < s.size(); i++) {
            System.out.println(v[i]);
        }
    }
}