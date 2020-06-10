package test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

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

    private void fillMap() {
        for(int i = 0; i < 5; i++) {
			map.put(i, i);
		}
    }

    public HMap fillMapReturn() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++) {
			m.put(i, i);
        }
        return m;
    }

    /**
     * Test del metodo clear
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Map vuota
     * @safe.testcases Il metodo testa se la mappa viene svuotata correttamente
     */
    @Test
    public void testClear() {
        fillMap();
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
        fillMap();
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
        fillMap();
        HMap m = fillMapReturn();
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
        fillMap();
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
        HMap m = fillMapReturn();
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
        fillMap();
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            assertTrue(map.containsValue(iter.next()));
        }
    }

    
    // Test della classe EntrySet
     

    /**
     * Test del metodo add di EntrySet
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia UnsupportedOperationException
     * @safe.testcases Il verifica che l'add di entrySet lanci UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testEntrySetAdd() {
        HSet set = map.entrySet();
        set.add(null);
    }

    /**
     * Test del metodo addAll di EntrySet
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Lancia UnsupportedOperationException
     * @safe.testcases Il metodo verifica che l'addAll di entrySet lanci UnsupportedOperationException
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testEntrySetAddAll() {
        HSet set = map.entrySet();
        set.addAll(null);
    }

    /**
     * Test dell'iteratore di Entry Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che un iteratore che scorre sull'entrySet di una mappa incontra solo coppie chiave - valore contenute in Map
     */
    @Test
    public void testEntrySetIterator() {
        HMap map2 = fillMapReturn();
        HSet set2 = map2.entrySet();
        HIterator iter2 = set2.iterator();
        fillMap();
        HSet set = map.entrySet();
        HIterator iter = set.iterator();
        while(iter.hasNext()) {
            assertTrue(iter.next().equals(iter2.next()));
        }
    }

    /**
     * Test della riflessione del remove sulla mappa di Entry Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se elimino un elemento dal Set questo viene rimosso anche dalla mappa
     */
    @Test
    public void testEntrySetRemoveReflection() {
        map.put("key","value");
        HSet set = map.entrySet();
        assertTrue(map.containsKey("key"));
        HIterator iter = set.iterator();
        while(iter.hasNext()) {
            HMap.HEntry e = (HMap.HEntry) iter.next();
            if(e.getKey().equals("key")) {
                set.remove(e);
            }
        }
        assertFalse(map.containsKey("key"));
    }

    /**
     * Test di HasNext di Entry Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica il corretto funzionamento del metodo hasNext in caso ci sia un solo elemento nella mappa
     */
    @Test
    public void testEntrySetHasNext() {
        map.put("key","value");
        HSet hSet = map.entrySet();
        HIterator iter = hSet.iterator();
        assertTrue(iter.hasNext());
    }

    // Test della classe Key Set

    /**
     * Test della riflessione del remove sulla mappa di Key Set
     * @safe.precondition Mappa inizializzata con dei valori
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se eilimino tutti gli elementi dalla mappa anche il set sarà vuoto
     */
    @Test
    public void testKeySetRemoveReflection() {
        fillMap();
        HSet set = map.keySet();
        assertFalse(set.isEmpty());
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(set.isEmpty());
        assertTrue(map.isEmpty());
    }

    /**
     * Test di HasNext di Key Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica il corretto funzionamento del metodo hasNext in caso ci sia un solo elemento nella mappa
     */
    @Test
    public void testKeySetHasNext() {
        map.put(0,1);
        HSet set = map.keySet();
        HIterator iter = set.iterator();
        assertTrue(iter.hasNext());
        iter.next();
        iter.remove();
        assertFalse(iter.hasNext());
    }

    /**
     * Test di hasNext di Entry Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica il corretto funzionamento del metodo hasNext in caso ci sia un solo elemento nella mappa
     */
    @Test
    public void testKeySetNext() {
        fillMap();
        HSet hSet = map.keySet();
        HIterator iter = hSet.iterator();
        assertTrue(iter.hasNext());
        while(iter.hasNext()) {
            Object key = iter.next();
            assertTrue(map.containsKey(key));
        }
    }

    /**
     * Test della rimozione da iteratore di Key Set
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica la corretta eliminazione degli elementi con l'iteratore
     */
    @Test(expected = IllegalStateException.class)
    public void testKeySetIteratorRemove() {
        fillMap();
        HSet hSet = map.keySet();
        HIterator iter = hSet.iterator();
        iter.remove();
        assertTrue(iter.hasNext());
        Object key = iter.next();
        assertTrue(map.containsKey(key));
        iter.remove();
        assertFalse(hSet.contains(key));
        iter.remove();
    }

    /**
     * Values
     */

    /**
     * Test della riflessione del remove sulla mappa di Key Set
     * @safe.precondition Mappa inizializzata con dei valori
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se eilimino tutti gli elementi dalla mappa anche il set sarà vuoto
     */
    @Test
    public void testValuesRemoveReflection() {
        fillMap();
        HCollection c = map.values();
        assertFalse(c.isEmpty());
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(c.isEmpty());
        assertTrue(map.isEmpty());
    }

    /**
     * Test di hasNext di Values
     * @safe.precondition Mappa inizializzata con degli elementi
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica il corretto funzionamento del metodo hasNext in una mappa non vuota
     */
    @Test
    public void testValuesHasNext() {
        fillMap();
        HCollection c = map.values();
        HIterator iter = c.iterator();
        assertTrue(iter.hasNext());
    }

    /**
     * Test della rimozione da iteratore di Values
     * @safe.precondition Mappa inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica la corretta eliminazione degli elementi con l'iteratore
     */
    @Test()
    public void testValuesIteratorRemove() {
        map.put(0,1);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        iter.next();
        iter.remove();
        assertTrue(c.isEmpty());
        assertTrue(map.isEmpty());
    }

    @Test
    public void testValuesClear() {
        fillMap();
        HCollection c = map.values();
        c.clear();
        assertEquals(0, c.size());
    }

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        HCollection c = map.values();
        c.contains(null);
    }
}