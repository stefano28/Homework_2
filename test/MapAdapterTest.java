package test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test case class di MapAdapterTest
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
     * Test del metodo entrySet
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
     * Test del metodo equals con una mappa uguale come paramtro
     * @safe.precondition Mappa inizializzata con mapping uguale a quella passata per parametro
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che map.equals risulti true quando si passa una mappa identica
     */
    @Test
    public void testEqualsWithEqualsMap() {
        HMap m = new MapAdapter();
        assertTrue(map.equals(m));
    }

    /**
     * Test del metodo equals con una mappa diversa come parametro
     * @safe.precondition Mappa inizializzata con mapping diverso a quella passata per paramtero
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che map.equals risulti false quando si passa una mappa diversa
     */
    @Test
    public void testEqualsWithDifferentMap() {
        HMap m = new MapAdapter();
        m.put(0, new Object());
        assertFalse(map.equals(m));
    }

    /**
     * Test del metodo get passando come parametro un oggetto null
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia l'eccezione NullPointerException
     * @safe.testcases Il metodo verifica che si lanci l'eccezione NullPointerException quando si passa come parametro un oggetto null
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithNull() {
        map.get(null);
    }

    /**
     * Test del metodo get passando come parametro un oggetto che è contenuto
     * @safe.precondition Mappa inizializzata che contiene l'oggetto
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che il get di quella chiave restituisce lo stesso oggetto precedentemente inserito
     */
    @Test
    public void testGetWithKeyContained() {
        Object o = new Object();
        map.put(0, o);
        Object cmp = map.get(0);
        assertTrue(cmp.equals(o));
    }

    /**
     * Test del metodo get passando come parametro un oggetto che non è contenuto
     * @safe.precondition Mappa inizializzata che non contiene l'oggetto
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che il get di quella chiave restituisce lo stesso oggetto precedentemente inserito
     */
    @Test
    public void testGetWithKeyNotContained() {
        Object cmp = map.get(0);
        assertTrue(cmp == null);   
    }

    /**
     * Test del metodo hashcode
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che due hash code di mappe uguali sono uguali
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
     * Test del metodo isEmpty - caso vero
     * @safe.precondition Mappa inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una mappa è vuota, il suo metodo isEmpty risulta true
     */
    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());
    }

    /**
     * Test del metodo isEmpty - caso falso
     * @safe.precondition Mappa inizializzata non vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una Set non è vuoto, il suo metodo isEmpty risulta false
     */
    @Test
    public void testIsEmptyFalse() {
        Object o = new Object();
        map.put(0, o);
        assertFalse(map.isEmpty());
    }

    /**
     * Test del metodo KeySet
     * @safe.precondition Mappa inizializzata al cui interno ci sono una serie di valori (chiavi)
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che inserendo nella mappa una serie di valori di un Set, questa quando viene chiamato keySet restituisce la stessa serie di valori
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
     * Test del metodo put con parametri null
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia l'eccezione NullPointerException
     * @safe.testcases Il metodo verifica che in caso di put con elementi null viene lanciata NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNull() {
        map.put(null, null);
    }

    /**
     * Test del metodo put
     * @safe.precondition Mappa Inizializzata
     * @safe.postcondition Elemento viene inserito
     * @safe.testcases Il metodo verifica che dopo l'inserimento di un mapping key - value, questo poi sia effettivamente stato inserito
     */
    @Test
    public void testPut() {
        map.put(0, 1);
        assertTrue(map.get(0).equals(1));
    }

    /**
     * Test del metodo putAll con null come parametro
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che in caso di putAll con elementi null viene lanciata NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        map.putAll(null);
    }

    /**
     * Test del metodo putAll
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Mappa contenente tutti gli elementi di un'altra mappa
     * @safe.testcases Il metodo verifica che creando una mappa e inserendovi una serie di elementi, richiamando il putAll su un'altra mappa passando questa come parametro, tutti gli elementi vengono inseriti
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
     * Test del metodo remove con null come parametro
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che la chiamata del metodo remove passando un null come parametro genera il lancio di NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        map.remove(null);
    }

    /**
     * Test del metodo remove con un oggetto contenuto
     * @safe.precondition Mappa inizializzata con all'interno un oggetto
     * @safe.postcondition L'oggetto viene rimosso dalla mappa
     * @safe.testcases Il metodo verifica che la chiamata del metodo remove passando un oggetto contenuto elimina effettivamente l'oggetto dalla mappa
     */
    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        map.put(0, o);
        map.remove(0);
        assertFalse(map.containsKey(0));
    }

    /**
     * Test del metodo remove con un oggetto che non è contenuto
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che la chiamata del metodo remove passando un oggetto non contenuto elimina ritorna null
     */
    @Test
    public void testRemoveWithObjNotContained() {
        assertTrue(map.remove(0) == null);
    }

    /**
     * Test del metodo size in una mappa vuota
     * @safe.precondition Mappa inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una mappa è vuota size ritorna 0
     */
    @Test
    public void testSize() {
        assertTrue(map.size() == 0);
    }

    /**
     * Test del metodo size con mappa non vuota
     * @safe.precondition Mappa inizializzata non vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una mappa ha un elemento size ritorna 1
     */
    @Test
    public void testSizeMapNotEmpty() {
        map.put(0, new Object());
        assertTrue(map.size() == 1);
    }

    /**
     * Test del metodo values
     * @safe.precondition Mappa inizializzata con una serie di valori
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che una mappa al cui interno è presente una serie di elementi, il metodo values restituisce una HCollection composta dagli stessi elementi
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

}