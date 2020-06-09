package test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test case class for MapAdapter
 */
public class MapAdapterTest {

    private HMap map = null;

    /**
     * Metodo di inizializzazione per la classe di test di MapAdapter
     */
    @Before
    public void start() {
        map = new MapAdapter();
    }

    /**
     * Test del metodo clear
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Map vuota
     * @safe.testcases Il metodo testa se la mappa viene svuotata correttamente
     */
    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            map.put(i, new Object());
        }
        map.clear();
        assertEquals(0, map.size());
    }

    /**
     * Test del metodo containsKey con null come parametro
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia una eccezione NullPointerException
     * @safe.testcases Il metodo testa se viene impedito l'inserimento di oggetti null
     */
    @Test(expected = NullPointerException.class)
    public void testContainsKeyWithNull() {
        map.containsKey(null);
    }

    /**
     * Test del metodo containsKey con un oggetto contenuto come parametro
     * @safe.precondition Mappa inizializzata che contiene l'oggetto
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che dopo l'inserimento la mappa contenga effettivamente l'oggetto
     */
    @Test
    public void testContainsKeyWithObjContained() {
        map.put(1, new Object());
        assertTrue(map.containsKey(1));
    }

    /**
     * Test del metodo containsKey con un oggetto non contenuto come parametro
     * @safe.precondition Mappa inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica se è contenuto un elemento con chiave 1 in una mappa vuota
     */
    @Test
    public void testContainsKeyWithObjNotContained() {
        assertFalse(map.containsKey(1));
    }

    /**
     * Test del metodo containsKey con un null come parametro
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia una eccezione NullPointerException
     * @safe.testcases Il metodo testa se viene impedito l'inserimento di oggetti null
     */
    @Test(expected = NullPointerException.class)
    public void testContainsValueWithNull() {
        map.containsValue(null);
    }

    /**
     * Test del metodo containsValue con un oggetto contenuto come parametro
     * @safe.precondition Mappa inizializzata che contiene l'oggetto
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che dopo l'inserimento la mappa contenga effettivamente l'oggetto
     */
    @Test
    public void testContainsValueWithObjContained() {
        Object o = new Object();
        map.put(1, o);
        assertTrue(map.containsValue(o));
    }

    /**
     * Test del metodo containsValue con un oggetto non contenuto come parametro
     * @safe.precondition Mappa inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica se è contenuto un oggetto o in una mappa vuota
     */
    @Test
    public void testContainsValueWithObjNotContained() {
        Object o = new Object();
        assertFalse(map.containsValue(o));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEntrySet() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.entrySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            HEntry e = (HEntry)iter.next();
            Object key = e.getKey();
            assertTrue(map.get(key).equals(e.getValue()));
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEntrySetFail() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.entrySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            HEntry e = (HEntry)iter.next();
            Object key = e.getKey();
            assertFalse(map.get(key).equals(new Object()));
        }
    }


    /**
     * Test del metodo equals
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testEqualsWithNull() {
        map.containsValue(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsWithEqualsMapping() {
        HMap m = new MapAdapter();
        assertTrue(map.equals(m));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testEqualsWithDifferentMapping() {
        HMap m = new MapAdapter();
        m.put(0, new Object());
        assertFalse(map.equals(m));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithNull() {
        map.get(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testGetWithKeyContained() {
        Object o = new Object();
        map.put(0, o);
        Object cmp = map.get(0);
        assertTrue(cmp.equals(o));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testGetWithKeyNotContained() {
        Object cmp = map.get(0);
        assertTrue(cmp == null);   
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testHashCode() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            m.put(i, o);
            map.put(i, o);
        }
		assertEquals(map, m);
		assertTrue(map.hashCode() == m.hashCode());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testHashCodeFail() {
        assertFalse(map.hashCode() == -1);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testIsEmptyFalse() {
        Object o = new Object();
        map.put(0, o);
        assertFalse(map.isEmpty());
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testKeySet() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.keySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            assertTrue(map.containsKey(iter.next()));
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNull() {
        map.put(null, null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testPut() {
        map.put(0, 1);
        assertTrue(map.get(0).equals(1));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testPutFail() {
        map.put(0, 1);
        assertFalse(map.get(0).equals(0));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        map.putAll(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testPutAll() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++)
            m.put(i, i);
        map.putAll(m);
        for(int i = 0; i < 5; i++)
            assertTrue(map.get(i).equals(i));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testPutAllFail() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++)
            m.put(i, i);
        map.putAll(m);
        for(int i = 0; i < 5; i++)
            assertFalse(map.get(i).equals(new Object()));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        map.remove(null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        map.put(0, o);
        map.remove(0);
        assertFalse(map.containsKey(0));
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testRemoveWithObjNotContained() {
        assertTrue(map.remove(0) == null);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSize() {
        assertTrue(map.size() == 0);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSizeIncremented() {
        map.put(0, new Object());
        assertTrue(map.size() == 1);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testSizeFail() {
        assertFalse(map.size() == -1);
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testValues() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            assertTrue(map.containsValue(iter.next()));
        }
    }

    /**
     * 
     * @safe.precondition
     * @safe.postcondition
     * @safe.testcases
     */
    @Test
    public void testValuesFail() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            iter.next();
            assertFalse(map.containsValue(new Object()));
        }
    }

}