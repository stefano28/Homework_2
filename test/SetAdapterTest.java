package test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test case class di SetAdapter
 * @safe.summary La classe testa tutti i metodi di Set
 * @safe.testsuitedesign Ogni metodo viene testato singolarmente
 */
public class SetAdapterTest {

    private HSet set = null;

     /**
      * Metodo di inizializzazione per la classe di test di SetAdapter
      */
    @Before
    public void start() {
        set = new SetAdapter();
    }

    /**
     * Test del metodo add con null
     * @safe.precondition Set inizializzato
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo testa se il metodo add lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        set.add(null);
    }

    /**
     * Test del metodo add con oggetto non contenuto
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo controlla che l'inserimento di un oggetto risulta true e che questo effettivamente inserisce l'oggetto all'interno del set
     */
    @Test()
    public void testAddWithObjNotContained() {
        Object o = new Object();
        assertTrue(set.add(o));
        assertEquals(true, set.contains(o));
    }

    /**
     * Test del metodo addAll con null come parametro 
     * @safe.precondition Set inizializzato
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo testa se il metodo addAll lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        set.addAll(null);
    }

    /**
     * Test del metodo addAll con una HCollection come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Set contenente i valori della HCollection
     * @safe.testcases Il metodo verifica che chiamando il metodo addAll con parametro una HCollection contenente una serie di valori, questi risultano inseriti nel Set
     */
    @Test
    public void testAddAllWithHCollection() {
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
     * Test del metodo clear
     * @safe.precondition Set inizializzato non vuoto
     * @safe.postcondition Set vuoto
     * @safe.testcases Il metodo verifica che dopo la chiamata del metodo clear il set risulta vuoto
     */
    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        set.clear();
        assertEquals(0, set.size());
    }

    /**
     * Test del metodo contains con null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Lancia l'eccezione NullPointerException
     * @safe.testcases Il metodo testa se il metodo contains lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        set.contains(null);
    }

    /**
     * Test del metodo contains con un oggetto contenuto
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se contains di un oggetto inserito nella mappa risulta true
     */
    @Test
    public void testContainsWithObjContained() {
        Object o = new Object();
        set.add(o);
        assertTrue(set.contains(o));
    }

    /**
     * Test del metodo contains con un oggetto non contenuto
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se contains di un oggetto non inserito nella mappa risulta false
     */
    @Test
    public void testContainsWithObjNotContained() {
        Object o = new Object();
        assertFalse(set.contains(o));
    }

    /**
     * Test del metodo containsAll con null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nesuna
     * @safe.testcases Il metodo testa se il metodo containsAll lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        set.containsAll(null);
    }

    /**
     * Test del metodo containsAll con una HCollection di oggetti contenuti
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se containsAll di una HCollection di oggetti inseriti nella mappa risulta true
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.containsAll(c));
    }

    /**
     * Test del metodo containsAll con una HCollection di oggetti non contenuti
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se containsAll di una HCollection di oggetti non inseriti nella mappa risulta false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertFalse(set.containsAll(c));
    }

    /**
     * Test del metodo equals con un Set uguale
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se inserendo gli stessi valori in due Set differenti questi risultano uguali tramita la chiamata di equals
     */
    @Test
    public void testEqualsWithEqualObject() {
        HSet set2 = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            set2.add(i);
            set.add(i);
        }
        assertTrue(set.equals(set2));
    }

    /**
     * Test del metodo equals con un Set diverso
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se due Set differenti risultano diversi tramita la chiamata di equals
     */
    @Test
    public void testEqualsWithNotEqualObject() {
        HSet set2 = new SetAdapter();
        set2.add(new Object());
        assertFalse(set.equals(set2));
    }

    /**
     * Test del metodo HashCode
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se due Set uguali hanno lo stesso hash code
     */
    @Test
    public void testHashCode() {
        HSet s = new SetAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            s.add(o);
            set.add(o);
        }
		assertEquals(set, s);
		assertTrue(set.hashCode() == s.hashCode());
    }


    /**
     * Test del metodo isEmpty - caso vero
     * @safe.precondition Set inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando un Set è vuoto, il suo metodo isEmpty risulta true
     */
    @Test
    public void testIsEmpty() {
        assertTrue(set.isEmpty());
    }

    /**
     * Test del metodo isEmpty - caso falso
     * @safe.precondition Set inizializzata non vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando un Set non è vuoto, il suo metodo isEmpty risulta false
     */
    @Test
    public void testIseEmptyFalse() {
        set.add(new Object());
        assertFalse(set.isEmpty());
    }

    /**
     * Test del metodo iterator
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa il corretto funzionamento dell'teratore all'interno di Set
     */
    @Test
    public void testIterator() {
        for(int i = 0; i < 3; i++) {
            set.add(i);
        }
        HIterator iter = set.iterator();
        HSet set2 = new SetAdapter();
        while(iter.hasNext()) {
            set2.add(iter.next());
        }
        assertTrue(set.equals(set2));
    }
    
    /**
     * Test del metodo remove con null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se remove lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        set.remove(null);
    }

    /**
     * Test del metodo remove con un oggetto contenuto come parametro
     * @safe.precondition Set inizializzato con un oggetto all'interno
     * @safe.postcondition Set con oggetto rimosso
     * @safe.testcases Il metodo verifica che l'oggetto precedentemente inserito nel Set viene eliminato chiamando il remove
     */
    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        assertTrue(set.add(o));
        assertTrue(set.remove(o));
        assertEquals(false, set.contains(o));
    }

    /**
     * Test del metodo removeAll con un null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Lancio di NullPointerException
     * @safe.testcases Il metodo testa se removeAll lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        set.removeAll(null);
    }

    /**
     * Test del metodo removeAll con una HCollection contenuta come parametro
     * @safe.precondition Set inizializzato contenente i valori di una HCollection
     * @safe.postcondition Set con i valori della HCollection rimossi
     * @safe.testcases Il metodo verifica che inserendo dei valori di una HCollection nel Set, quando viene chiamato il removeAll con lo stesso Set questi vengono rimossi
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.removeAll(c));
    }

    /**
     * Test del metodo removeAll con una HCollection non contenuta come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Verifica il comportamento di removeAll passando come parametro una HCollection di valori non contenuti nel Set
     */
    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HSet backup = set;
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertFalse(set.removeAll(c));
        assertTrue(set.equals(backup));
    }

    /**
     * Test del metodo retainAll con null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Lancia l'eccezione NullPointerException
     * @safe.testcases Il metodo testa se retainAll lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        set.retainAll(null);
    }

    /**
     * Test del metodo retainAll con una HCollection contenuta come parametro
     * @safe.precondition Set inizializzato con una serie di valori
     * @safe.postcondition Set contiene solo i valori contenuti nella HCollection
     * @safe.testcases Il metodo testa se retainAll di Set mantiene solo i valori contenuti nella Hcollection
     */
    @Test
    public void testRetainAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            set.add(i);
            c.add(i);
        }
        HSet backup = set;
        set.retainAll(c);
        assertTrue(set.equals(backup));
    }

    /**
     * Test del metodo retainAll con una HCollection non contenuta come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se viene chiamato retainAll su una HCollection i cui valori non sono contenuti nel Set, questo risulta invariato
     */
    @Test
    public void testRetainAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        HSet backup = set;
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.retainAll(c);
        assertTrue(set.equals(backup));
    }

    /**
     * Test del metodo size su un Set vuoto
     * @safe.precondition Set inizializzato vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che un Set vuoto ha size zero
     */
    @Test
    public void testSize() {
        assertTrue(set.size() == 0);
    }

    /**
     * Test del metodo size su un Set non vuoto
     * @safe.precondition Set inizializzato non vuoto
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che un Set con un elemento ha size uno
     */
    @Test
    public void testSizeSetNotEmpty() {
        set.add(new Object());
        assertTrue(set.size() == 1);
    }

    /**
     * Test del metodo toArray con null come parametro
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa se toArray lancia NullPointerException quando viene passato un null
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        set.toArray(null);
    }

    /**
     * Test del metodo toArray
     * @safe.precondition Set inizializzato con una serie di valori
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che inserendo una serie di elementi nel Set, il toArray crea un array che li contiene tutti
     */
    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        Object[] setArray = set.toArray();
        for(int i = 0; i < set.size(); i++) {
            assertTrue(set.contains(setArray[i]));
        }
    }

    /**
     * Test del metodo toArray con parametro di dimensione minore del Set
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando il toArray viene restituito un array composto da valori appartenenti al Set, questi saranno solo alcuni dei valori poichè la dimensione del parametro è minore di quella del Set
     */
    @Test
    public void testToArrayWithParameterSizeSmaller() {
        for(int i = 0; i < 10; i++) {
            set.add(i);
        }
        Object[] array = new Object[5];
        Object[] setArray = set.toArray(array);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(set.contains(setArray[i]));
        }
    }

    /**
     * Test del metodo toArray con parametro di dimensione maggiore del Set
     * @safe.precondition Set inizializzato
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando il toArray viene restituito un array composto da valori appartenenti al Set e da null, poichè di dimensione maggiore a quella di Set
     */
    @Test
    public void testToArrayWithParameterSizeLonger() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        Object[] array = new Object[10];
        Object[] setArray = set.toArray(array);
        for(int i = 0; i < setArray.length; i++) {
            if(i < set.size())
                assertTrue(set.contains(setArray[i]));
            else
                assertEquals(setArray[i], null);
        }
    }

}