package adapter;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;


public class SetAdapterTest {

    private HSet set = null;

    /**
     * Bootstrap
     */

    @Before
    public void start() {
        System.out.println("Inizio dei test della classe SetAdapter");
        set = new SetAdapter();
    }

    /**
     * End of SetAdapter test
     */

    @After
    public void end() {
        System.out.println("Fine dei test della classe SetAdapter");
        set = new SetAdapter();
    }

    /**
     * TestAdd
     */

    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        System.out.println("Test di Set.add(Object o) con null come parametro");
        set.add(null);
    }

    @Test()
    public void testAddWithObjNotContained() {
        System.out.println("Test di Set.add(Object o) con un oggetto non contenuto come parametro");
        Object o = new Object();
        assertTrue(set.add(o));
        assertEquals(true, set.contains(o));
    }


    @Test()
    public void testAddWithObjContained() {
        System.out.println("Test di Set.add(Object o) con un oggetto contenuto come parametro");
        Object o = new Object();
        assertTrue(set.add(o));
        assertFalse(set.add(o));
        assertEquals(true, set.contains(o));
    }

    /**
     * TestAddAll
     */

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        System.out.println("Test di Set.addAll(HCollection c) con null come parametro");
        set.addAll(null);
    }

    @Test
    public void testAddAllWithHCollection() {
        System.out.println("Test di Set.addAll(HCollection c) con una HCollection");
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertTrue(set.addAll(c));
        for(int i = 0; i < c.size(); i++) {
            assertTrue(set.contains(i));
        }
    }

    /**
     * TestClear
     */

    @Test
    public void testClear() {
        System.out.println("Test di Set.clear()");
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        set.clear();
        assertEquals(0, set.size());
    }

    /**
     * TestContains
     */

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        System.out.println("Test di Set.contains(Object o) con null come parametro");
        set.contains(null);
    }

    @Test
    public void testContainsWithObjContained() {
        System.out.println("Test di Set.contains(Object o) con un oggetto contenuto come parametro");
        Object o = new Object();
        set.add(o);
        assertTrue(set.contains(o));
    }

    @Test
    public void testContainsWithObjNotContained() {
        System.out.println("Test di Set.contains(Object o) con un oggetto non contenuto come parametro");
        Object o = new Object();
        set.add(o);
        Object o2 = new Object();
        assertFalse(set.contains(o2));
    }

    /**
     * TestContainsAll
     */

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        System.out.println("Test di Set.containsAll(HCollection c) con null come parametro");
        set.containsAll(null);
    }

    @Test
    public void testContainsAllWithHCollectionContained() {
        System.out.println("Test di Set.containsAll(HCollection c) con una HCollection contenuta come parametro");
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.containsAll(c));
    }

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        System.out.println("Test di Set.contains(HCollection c) con una HCollection non contenuta come parametro");
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertFalse(set.containsAll(c));
    }

    /**
     * TestEquals
     */

    @Test
    public void testEqualsWithEqualObject() {
        System.out.println("Test di Set.equals(Object c) con un oggetto uguale");
        HSet set2 = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            set2.add(i);
            set.add(i);
        }
        assertTrue(set.equals(set2));
    }

    @Test
    public void testEqualsWithNotEqualObject() {
        System.out.println("Test di Set.equals(Object c) con un oggetto diverso");
        HSet set2 = new SetAdapter();
        set2.add(new Object());
        assertFalse(set.equals(set2));
    }

    /**
     * TestIsEmpty
     */

    @Test
    public void TestIsEmpty() {
        System.out.println("Test di Set.isEmpty()");
        assertTrue(set.isEmpty());
    }

    /**
     * TestIterator
     */

    @Test
    public void TestIterator() {
        //
    }
    
    /**
     * TestRemove
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        System.out.println("Test di Set.remove(Object o) con null come parametro");
        set.remove(null);
    }

    @Test
    public void testRemoveWithObjContained() {
        System.out.println("Test di Set.remove(Object o) con un oggetto contenuto come parametro");
        Object o = new Object();
        assertTrue(set.add(o));
        assertTrue(set.remove(o));
        assertEquals(false, set.contains(o));
    }

    @Test
    public void testRemoveWithObjNotContained() {
        System.out.println("Test di Set.remove(Object o) con un oggetto non contenuto come parametro");
        Object o = new Object();
        set.add(o);
        set.remove(new Object());
        assertEquals(true, set.contains(o));
    }

    /**
     * TestRemoveAll
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        System.out.println("Test di Set.removeAll(HCollection c) con null come parametro");
        set.removeAll(null);
    }

    @Test
    public void testRemoveAllWithHCollectionContained() {
        System.out.println("Test di Set.removeAll(HCollection c) con una HCollection contenuta come parametro");
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.removeAll(c));
    }

    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        System.out.println("Test di Set.removeAll(HCollection c) con una HCollection contenuta come parametro");
        HSet backup = set;
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertTrue(set.equals(backup));
    }

    /**
     * TestRetainAll
     */

    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        System.out.println("Test di Set.retainAll(HCollection c) con null come parametro");
        set.retainAll(null);
    }

    /*
    @Test
    public void testRetainAllWithHCollectionContained() {
        System.out.println("Test di Set.retainAll(HCollection c) con una HCollection contenuta come parametro");
        HCollection c = new CollectionAdapter();
        HSet backup = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            set.add(i);
            if(i > 2) {
                c.add(i);
                backup.add(i);
            }
        }
        set.retainAll(c);
        assertTrue(set.equals(backup));
    }
    */

    @Test
    public void testRetainAllWithHCollectionNotContained() {
        System.out.println("Test di Set.retainAll(HCollection c) con una HCollection non contenuta come parametro");
        HCollection c = new CollectionAdapter();
        HSet backup = set;
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.retainAll(c);
        assertTrue(set.equals(backup));
    }
}