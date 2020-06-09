package test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Test case class for SetAdapter
 */
public class MapAdapterKeySetTest {

    private HSet s = null;
    private Object e1 = null;
    private Object e2 = null;
    private Object entry1 = null;
    private Object entry2 = null;

    /**
     * Metodo di inizializzazione per la classe di test di MapAdapterKeySet
     */

    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        s = map.keySet();
        HIterator it = s.iterator();
        e1 = it.next();
        e2 = it.next();
        MapAdapter map2 = new MapAdapter();
        map2.put(Integer.valueOf(10), Integer.valueOf(11));
        map2.put(Integer.valueOf(11), Integer.valueOf(12));
        HSet set2 = map2.keySet();
        HIterator it2 = set2.iterator();
        entry1 = it2.next();
        entry2 = it2.next();
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        s.add(new Object());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        s.add(new CollectionAdapter());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testClear() {
        s.clear();
        assertEquals(0, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsTrue() {
        assertTrue(s.contains(e1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsFalse() {
        assertFalse(s.contains(entry1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        s.contains(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(e2);
        assertTrue(s.containsAll(c));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(entry2);
        assertFalse(s.containsAll(c));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(entry1);
        assertFalse(s.containsAll(c));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        s.containsAll(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.keySet();
        assertEquals(s, otherSet);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(s.equals(otherSet));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testHashCodeTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.keySet();
		assertEquals(s, otherSet);
		assertTrue(s.hashCode() == otherSet.hashCode());
	}
    
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testHashCodeFalse() {
        HSet otherSet = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            otherSet.add(new Object());
        }
		assertFalse(s.equals(otherSet));
		assertFalse(s.hashCode() == otherSet.hashCode());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void TestIsEmptyTrue() {
        s.clear();
        assertTrue(s.isEmpty());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIsEmptyFalse() {
        assertFalse(s.isEmpty());
	}


    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIteratorNextAndHasNext() {
        HIterator it = s.iterator();
        HSet otherSet = new SetAdapter();
        while(it.hasNext()) {
            otherSet.add(it.next());
        }
        assertEquals(s, otherSet);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = s.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
		}
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testIteratorRemove() {
		HIterator it = s.iterator();
		it.next();
        it.remove();
        assertEquals(1, s.size());
	}
    
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveTrue() {
        assertTrue(s.remove(e1));
        assertEquals(false, s.contains(e1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveFalse() {
        assertFalse(s.remove(entry1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        s.remove(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
		assertTrue(s.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(s.contains(cit.next()));
		}
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(entry2);
		assertFalse(s.removeAll(c));
        assertEquals(2, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(e2);
		assertTrue(s.removeAll(c));
		assertEquals(1, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        s.removeAll(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(e2);
		assertFalse(s.retainAll(c));
		assertEquals(2, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
		assertTrue(s.retainAll(c));
		assertEquals(1, s.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection c = new CollectionAdapter();
		c.add(entry1);
		assertTrue(s.retainAll(c));
		assertEquals(0, s.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        s.retainAll(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSizeEmpty() {
        s.clear();
        assertEquals(0, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSize() {
        assertEquals(2, s.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testToArray() {
        Object[] setArray = s.toArray();
        for(int i = 0; i < s.size(); i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testToArrayWithParameterSizeSmaller() {
        Object[] param = new Object[1];
        Object[] setArray = s.toArray(param);
        assertEquals(2, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testToArrayWithParameterSizeLonger() {
        Object[] param = new Object[10];
        Object[] setArray = s.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < s.size(); i++) {
            assertTrue(s.contains(setArray[i]));
        }
        for(int i = s.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        s.toArray(null);
    }

}