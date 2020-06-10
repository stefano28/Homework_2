package test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Test case class for SetAdapter
 */
public class MapAdapterEntrySetTest {

    private HSet s = null;
    private HMap.HEntry k1 = null;
    private HMap.HEntry k2 = null;
    private HMap.HEntry key1 = null;
    private HMap.HEntry key2 = null;

    /**
     * Set up
     */

    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        s = map.entrySet();
        HIterator it = s.iterator();
        k1 = (HMap.HEntry) it.next();
        k2 = (HMap.HEntry) it.next();
        MapAdapter map2 = new MapAdapter();
        map2.put(Integer.valueOf(10), Integer.valueOf(11));
        map2.put(Integer.valueOf(11), Integer.valueOf(12));
        HSet set2 = map2.entrySet();
        HIterator it2 = set2.iterator();
        key1 = (HMap.HEntry) it2.next();
        key2 = (HMap.HEntry) it2.next();
    }

    /**
     * TestAdd
     */

    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        s.add(new Object());
    }

    /**
     * TestAddAll
     */

    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        s.add(new CollectionAdapter());
    }

    /**
     * TestClear
     */

    @Test
    public void testClear() {
        s.clear();
        assertEquals(0, s.size());
    }

    /**
     * TestContains
     */

    @Test
    public void testContainsTrue() {
        assertTrue(s.contains(k1));
    }

    @Test
    public void testContainsFalse() {
        assertFalse(s.contains(key1));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsClassCast() {
        s.contains(new Object());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        s.contains(null);
    }

    /**
     * TestContainsAll
     */

    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(k1);
        c.add(k2);
        assertTrue(s.containsAll(c));
    }

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(key1);
        c.add(key2);
        assertFalse(s.containsAll(c));
    }

    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(k1);
        c.add(key1);
        assertFalse(s.containsAll(c));
    }

    @Test(expected = ClassCastException.class)
    public void testContainsAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.containsAll(c);
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        s.containsAll(null);
    }

    /**
     * TestEquals
     */

    @Test
    public void testEqualsTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.entrySet();
        assertEquals(s, otherSet);
    }

    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(s.equals(otherSet));
    }

    /**
     * Test hashCode
     */

	@Test
    public void testHashCodeTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.entrySet();
		assertEquals(s, otherSet);
		assertTrue(s.hashCode() == otherSet.hashCode());
	}
	
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
     * TestIsEmpty
     */

    @Test
    public void TestIsEmptyTrue() {
        s.clear();
        assertTrue(s.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(s.isEmpty());
	}

    /**
     * TestIterator
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

	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = s.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
		}
	}
	
	@Test
    public void testIteratorRemove() {
		HIterator it = s.iterator();
		it.next();
        it.remove();
        assertEquals(1, s.size());
	}
    
    /**
     * TestRemove
     */

    @Test
    public void testRemoveTrue() {
        assertTrue(s.remove(k1));
        assertEquals(false, s.contains(k1));
    }

    @Test
    public void testRemoveFalse() {
        assertFalse(s.remove(key1));
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveClassCast() {
        s.remove(new Object());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        s.remove(null);
    }

    /**
     * TestRemoveAll
     */

    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(k1);
		assertTrue(s.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(s.contains(cit.next()));
		}
    }

    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(key1);
        c.add(key2);
		assertFalse(s.removeAll(c));
        assertEquals(2, s.size());
    }

    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(key1);
        c.add(k2);
		assertTrue(s.removeAll(c));
		assertEquals(1, s.size());
    }

    @Test(expected = ClassCastException.class)
    public void testRemoveAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.removeAll(c);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        s.removeAll(null);
    }

    /**
     * TestRetainAll
     */

    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(k1);
        c.add(k2);
		assertFalse(s.retainAll(c));
		assertEquals(2, s.size());
    }

    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(k1);
		assertTrue(s.retainAll(c));
		assertEquals(1, s.size());
	}

	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection c = new CollectionAdapter();
		c.add(key1);
		assertTrue(s.retainAll(c));
		assertEquals(0, s.size());
	}
    
    @Test(expected = ClassCastException.class)
    public void testRetainAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.retainAll(c);
    }

	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        s.retainAll(null);
	}

    /**
     * TestSize
     */

    @Test
    public void testSizeEmpty() {
        s.clear();
        assertEquals(0, s.size());
    }

    @Test
    public void testSize() {
        assertEquals(2, s.size());
    }

    /**
     * TestToArray
     */

    @Test
    public void testToArray() {
        Object[] setArray = s.toArray();
        for(int i = 0; i < s.size(); i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameterSizeSmaller() {
        Object[] param = new Object[1];
        Object[] setArray = s.toArray(param);
        assertEquals(2, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

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

    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        s.toArray(null);
    }

}