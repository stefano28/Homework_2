package test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Test case class for SetAdapter
 */
public class MapAdapterValuesTest {

    private HCollection c = null;
    private Object v1 = null;
    private Object v2 = null;
    private Object value1 = null;
    private Object value2 = null;

    /**
     * 
     */
    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        c = map.values();
        HIterator it = c.iterator();
        v1 = it.next();
        v2 = it.next();
        MapAdapter map2 = new MapAdapter();
        map2.put(Integer.valueOf(10), Integer.valueOf(11));
        map2.put(Integer.valueOf(11), Integer.valueOf(12));
        HCollection s2 = map2.values();
        HIterator it2 = s2.iterator();
        value1 = it2.next();
        value2 = it2.next();
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        c.add(new Object());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        c.add(new CollectionAdapter());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testClear() {
        c.clear();
        assertEquals(0, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsTrue() {
        assertTrue(c.contains(v1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsFalse() {
        assertFalse(c.contains(value1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        c.contains(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
        assertTrue(c.containsAll(coll));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
        assertFalse(c.containsAll(coll));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(value1);
        assertFalse(c.containsAll(coll));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        c.containsAll(null);
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
        HCollection otherCollection = map.values();
        assertEquals(c, otherCollection);
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
        assertFalse(c.equals(otherSet));
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
        HCollection otherCollection = map.values();
        assertEquals(c, otherCollection);
		assertTrue(c.hashCode() == otherCollection.hashCode());
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
		assertFalse(c.equals(otherSet));
		assertFalse(c.hashCode() == otherSet.hashCode());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void TestIsEmptyTrue() {
        c.clear();
        assertTrue(c.isEmpty());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIsEmptyFalse() {
        assertFalse(c.isEmpty());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIteratorNextAndHasNext() {
        HIterator it = c.iterator();
        HCollection otherCollection = new CollectionAdapter();
        while(it.hasNext()) {
            otherCollection.add(it.next());
        }
        assertEquals(c, otherCollection);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = c.iterator();
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
		HIterator it = c.iterator();
		it.next();
        it.remove();
        assertEquals(1, c.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveTrue() {
        assertTrue(c.remove(v1));
        assertEquals(false, c.contains(v1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveFalse() {
        assertFalse(c.remove(value1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        c.remove(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
		assertTrue(c.removeAll(coll));
		HIterator cit = coll.iterator();
		while(cit.hasNext()) {
			assertFalse(c.contains(cit.next()));
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
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
		assertFalse(c.removeAll(coll));
        assertEquals(2, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(v2);
		assertTrue(c.removeAll(coll));
		assertEquals(1, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        c.removeAll(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
		assertFalse(c.retainAll(coll));
		assertEquals(2, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
		assertTrue(c.retainAll(coll));
		assertEquals(1, c.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection coll = new CollectionAdapter();
		coll.add(value1);
		assertTrue(c.retainAll(coll));
		assertEquals(0, c.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        c.retainAll(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSizeEmpty() {
        c.clear();
        assertEquals(0, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSize() {
        assertEquals(2, c.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testToArray() {
        Object[] setArray = c.toArray();
        for(int i = 0; i < c.size(); i++) {
            assertTrue(c.contains(setArray[i]));
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
        Object[] setArray = c.toArray(param);
        assertEquals(2, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(c.contains(setArray[i]));
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
        Object[] setArray = c.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < c.size(); i++) {
            assertTrue(c.contains(setArray[i]));
        }
        for(int i = c.size(); i < param.length; i++) {
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
        c.toArray(null);
    }

}

