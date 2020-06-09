
package test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

public class ListAdapterTest {

	private ListAdapter l = null;

	/**
	 * 
	 */
	@Before
	public  void setUp() {
		l = new ListAdapter();
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testAddWithParamsFirstPosition() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(o, l.get(0));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testAddWithParamsLastPosition() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(l.size(), o);
		assertEquals(o, l.get(l.size()-1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithParamsWithNullElement() {
		l.add(0, null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsNegative() {
		l.add(-1, new Object());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new Object());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void TestAdd() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertTrue(l.add(o));
		assertEquals(o, l.get(l.size()-1));
		assertTrue(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithNullElement() {
		l.add(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void TestAddAll() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		c.add(o1);
		c.add(o2);
		assertTrue(l.addAll(c));
		assertEquals(o1, l.get(0));
		assertEquals(o2, l.get(1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertFalse(l.addAll(c));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullCollection() {
		l.addAll(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void TestAddAllWithParamsFirstPosition() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		l.add(o1);
		l.add(o2);
		c.add(o3);
		c.add(o4);
		assertTrue(l.addAll(0, c));
		assertEquals(o3, l.get(0));
		assertEquals(o4, l.get(1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void TestAddAllWithParamsLastPosition() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		l.add(o1);
		l.add(o2);
		c.add(o3);
		c.add(o4);
		assertTrue(l.addAll(2, c));
		assertEquals(o3, l.get(2));
		assertEquals(o4, l.get(3));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithParamsWithNullElement() {
		l.addAll(0, null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsNegative() {
		l.addAll(-1, new CollectionAdapter());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new CollectionAdapter());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testClear() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		l.clear();
		assertEquals(0, l.size());
		assertFalse(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testContainsFirstPos() {
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		assertTrue(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testContainsLastPos() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		assertTrue(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testContainsObjectNotContained() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertFalse(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = NullPointerException.class)
	public void testContainsWithNullObject() {
		l.contains(null);
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
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        l.addAll(c);
        assertTrue(l.containsAll(c));
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
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertFalse(l.containsAll(c));
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
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
			if(i % 2 == 0)
        		l.add(o);
            c.add(o);
        }
        assertFalse(l.containsAll(c));
    }
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        l.containsAll(null);
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
        assertEquals(l, otherList);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsFalse() {
		HList otherList = new ListAdapter();
		otherList.add(new Object());
        assertFalse(l.equals(otherList));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testEqualsEmptyList() {
		HList otherList = new ListAdapter();
        assertEquals(l, otherList);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testEqualsEqualToItself() {
		assertTrue(l.equals(l));
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testEqualsWithNull() {
		assertFalse(l.equals(null));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testGet() {
		Object o = new Object();
		l.add(o);
        assertEquals(o, l.get(0));
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsNegative() {
		l.add(new Object());
        l.get(-1);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.get(6);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testHashCodeTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
		assertEquals(l, otherList);
		assertTrue(l.hashCode() == otherList.hashCode());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testHashCodeFalse() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
        }
		assertFalse(l.equals(otherList));
		assertFalse(l.hashCode() == otherList.hashCode());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testIndexOfObjectContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		l.add(o);
		assertEquals(2, l.indexOf(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testIndexOfObjectNotContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertEquals(-1, l.indexOf(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
	public void testIndexOfWithNull() {
		l.indexOf(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIsEmptyTrue() {
        assertTrue(l.isEmpty());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testIsEmptyFalse() {
		l.add(new Object());
        assertFalse(l.isEmpty());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIteratorNextAndHasNext() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HIterator it = l.iterator();
        HList otherList = new ListAdapter();
        while(it.hasNext()) {
            otherList.add(it.next());
        }
        assertEquals(l, otherList);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HIterator it = l.iterator();
        for(int i = 0; i < 4; i++) {
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
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HIterator it = l.iterator();
		it.next();
        it.remove();
        assertEquals(4, l.size());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testLastIndexOfObjectContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		l.add(o);
		assertEquals(5, l.lastIndexOf(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testLastIndexOfObjectNotContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertEquals(-1, l.lastIndexOf(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
	public void testLastIndexOfWithNull() {
		l.lastIndexOf(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testListIteratorNextAndHasNext() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        HList otherList = new ListAdapter();
        while(lit.hasNext()) {
            otherList.add(lit.next());
        }
        assertEquals(l, otherList);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NoSuchElementException.class)
    public void testListIteratorNextNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        for(int i = 0; i < 4; i++) {
            lit.next();
		}
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorPreviousAndHasPrevious() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
        HListIterator lit = l.listIterator();
        HList otherList = new ListAdapter();
        while(lit.hasNext()) {
           lit.next();
		}
		while(lit.hasPrevious()) {
			otherList.add(lit.previous());
		}
		assertEquals(6, otherList.size());
		assertEquals(o, otherList.get(0));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NoSuchElementException.class)
    public void testListIteratorPreviousNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        lit.previous();
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
	public void testListIteratorAdd() {
		Object o1 = new Object();
		l.add(o1);
		HListIterator lit = l.listIterator();
		assertEquals(o1, lit.next());
		lit.previous(); // restore
		for(int i = 0; i < 3; i++) {
            lit.add(new Object());
		}
		Object o2 = new Object();
		lit.add(o2);
		assertEquals(5, l.size());
		assertEquals(o2, lit.previous());
		lit.next(); // restore
		assertEquals(o1, lit.next()); // chiamata a next unaffected dagli add dell'iteratore
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test (expected = NullPointerException.class)
	public void testListIteratorAddWithNullElement() {
		HListIterator lit = l.listIterator();
		lit.add(null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorNextIndexStart() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        assertEquals(0, lit.nextIndex());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorNextIndexMiddle() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.next();
        assertEquals(2, lit.nextIndex());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorNextIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size(), lit.nextIndex());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorPreviousIndexStart() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        assertEquals(-1, lit.previousIndex());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorPreviousIndexMiddle() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.next();
        assertEquals(1, lit.previousIndex());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorPreviousIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size()-1, lit.previousIndex());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorRemove() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
        lit.remove();
        assertEquals(4, l.size());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.remove();
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IllegalStateException.class)
    public void testRemoveAfterAdd() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.add(new Object());
        lit.remove();
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorSetAfterNextFirstPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(0));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorSetAfterNextLastPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext())
			lit.next();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(l.size()-1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorSetAfterPreviousFirstPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.previous();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(0));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testListIteratorSetAfterPreviousLastPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext())
			lit.next();
		lit.previous();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(l.size()-1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IllegalStateException.class)
    public void testSetWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.set(new Object());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IllegalStateException.class)
    public void testSetAfterAdd() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.add(new Object());
        lit.set(new Object());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IllegalStateException.class)
    public void testSetAfterRemove() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.remove();
        lit.set(new Object());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testListIteratorIndex() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit1 = l.listIterator();
		HListIterator lit2 = l.listIterator(2);
		for(int i = 0; i < 2; i++) {
            lit1.next();
		}
        assertEquals(lit1.next(), lit2.next());
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveIndex() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        Object o = new Object();
        l.add(3, o);
        l.remove(3);
        assertFalse(l.contains(o));
    }
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsNegative() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(-1);
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(5);
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveTrue() {
        Object o = new Object();
        l.add(o);
        assertTrue(l.remove(o));
        assertFalse(l.contains(o));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveFalse() {
        Object o = new Object();
        l.add(o);
        assertFalse(l.remove(new Object()));
        assertTrue(l.contains(o));
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveNullOBject() {
        l.remove(null);
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        l.addAll(c);
		assertTrue(l.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(l.contains(cit.next()));
		}
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveAllCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		l.add(new Object());
		assertFalse(l.removeAll(c));
		assertEquals(1, l.size());
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
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		Object o = new Object();
		c.add(o);
		l.add(o);
		assertTrue(l.removeAll(c));
		assertEquals(0, l.size());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        l.removeAll(null);
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
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            c.add(o);
            l.add(o);
		}
		c.add(new Object());
		assertFalse(l.retainAll(c));
		assertEquals(5, l.size());
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
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
			if(i % 2 == 0)
        		c.add(o);
            l.add(o);
		}
		assertTrue(l.retainAll(c));
		assertEquals(3, l.size());
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
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		c.add(new Object());
		assertTrue(l.retainAll(c));
		assertEquals(0, l.size());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        l.retainAll(null);
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void testSet() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		Object o = new Object();
		l.set(1, o);
		assertEquals(o, l.get(1));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsNegative() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.set(-1, new Object());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsGreaterOrEqualToSize() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.set(5, new Object());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test(expected = NullPointerException.class)
    public void testSetWithNull() {
        l.set(0, null);
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(0, l.size());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void TestSize() {
		for(int i = 0; i < 4; i++) {
			l.add(new Object());
		}
        assertEquals(4, l.size());
	}
	
    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void TestSubListChangesPropagation() {
		for(int i = 0; i < 10; i++) {
			l.add(new Object());
		}
		HList subList = l.subList(2, 7);
		Object o = new Object();
		subList.add(o);
		assertEquals(6, subList.size());
		assertEquals(11, l.size());
		assertEquals(o, subList.get(5));
		assertEquals(o, l.get(7));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
	@Test
    public void TestSubListChangesPropagationClear() {
		for(int i = 0; i < 9; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		l.subList(5, 10).clear();
		assertEquals(5, l.size());
		assertFalse(l.contains(o));
	}

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            l.add(i);
        }
        Object[] setArray = l.toArray();
        for(int i = 0; i < l.size(); i++) {
            assertEquals(l.get(i), setArray[i]);
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
        for(int i = 0; i < 10; i++) {
            l.add(i);
        }
        Object[] param = new Object[5];
        Object[] setArray = l.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertEquals(l.get(i), setArray[i]);
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
        for(int i = 0; i < 5; i++) {
            l.add(i);
        }
        Object[] param = new Object[10];
        Object[] setArray = l.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < l.size(); i++) {
            assertEquals(l.get(i), setArray[i]);
        }
        for(int i = l.size(); i < param.length; i++) {
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
        l.toArray(null);
    }

}