
package test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test case class di ListAdapter
 * @safe.summary La classe testa tutti i metodi di list
 * @safe.testsuitedesign Ogni metodo viene testato singolarmente
 */
public class ListAdapterTest {

	private ListAdapter list = null;

    /**
     * Metodo di inizializzazione per la classe di test di ListAdapter
     */
	@Before
	public  void setUp() {
		list = new ListAdapter();
    }

    private void fillList() {
        for(int i = 0; i < 5; i++) {
			list.add(i, i);
		}
    }

    public HCollection fillCollection() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
			c.add(i);
        }
        return c;
    }

    public HList fillListReturn() {
        HList l = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			l.add(i);
        }
        return l;
    }
    
    /**
     * Test del metodo add con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithNull() {
		list.add(0, null);
    }

    /**
     * Test del metodo add con null come parametro (senza indice)
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithNullWithoutIndex() {
		list.add(null);
	}
    
    /**
     * Test del metodo add in caso di indice non valido
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia IndexOutOfBoundsException
     * @safe.testcases Il metodo verifica il lancio di IndexOutOfBoundsException in caso di indice non valido
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithInvalidIndex() {
		list.add(1, new Object());
	}

    /**
     * Test del metodo add
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lista con oggetto inserito
     * @safe.testcases Il metodo si occupa di inserire un elemento nella lista e verificarne la sua presenza
     */
	@Test
	public void testAdd() {
		Object val = new Object();
		list.add(0, val);
		assertEquals(val, list.get(0));
	}

    /**
     * Test del metodo addAll con null come parametro (senza indice)
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullHCollectionWithoutIndex() {
		list.addAll(null);
    }
    
    /**
     * Test del metodo addAll con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullHCollection() {
		list.addAll(0, null);
	}

    /**
     * Test del metodo addAll con indice invalido
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia IndexOutOfBoundsException
     * @safe.testcases Il metodo verifica che in caso di indice non valido viene lanciata IndexOutOfBoundsException
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithInvalidIndex() {
        HCollection c = fillCollection();
		list.add(1, c);
	}

    /**
     * Test del metodo addAll
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando addAll con una HCollection i valori di questa vengono tutti inseriti nellla lista
     */
	@Test
	public void TestAddAll() {
        HCollection c = new CollectionAdapter();
        Object obj1 = 1;
        Object obj2 = 2;
        c.add(obj1);
        c.add(obj2);
		assertTrue(list.addAll(c));
		assertEquals(obj1, list.get(0));
		assertEquals(obj2, list.get(1));
	}

    /**
     * Test del metodo clear
     * @safe.precondition Lista inizializzata non vvuota
     * @safe.postcondition La lista risulta vuota
     * @safe.testcases Il metodo verifica che dopo la chiamata del clear la lista risulta vuota
     */
	@Test
	public void testClear() {
		Object o = new Object();
		list.add(o);
		list.clear();
		assertEquals(0, list.size());
	}

    /**
     * Test del metodo contains con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test (expected = NullPointerException.class)
	public void testContainsWithNullObject() {
		list.contains(null);
	}

    /**
     * Test del metodo contains con oggetto contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo inserisce un elemento nella lista e poi richiama il contains di quell'elemento
     */
	@Test
	public void testContainsWithObjContained() {
		Object o = new Object();
		list.add(o);
		assertTrue(list.contains(o));
	}

    /**
     * Test del metodo contains con oggetto non contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo chiama contains di un elemento non contenuto nella lista
     */
	@Test
	public void testContainsObjectNotContained() {
		assertFalse(list.contains(new Object()));
	}

    /**
     * Test del metodo containsAll con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si chiama containsAll con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        list.containsAll(null);
	}

    /**
     * Test del metodo containsAll con HCollection contenuta come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una lista e una collection contengono gli stessi elementi containsAll ritorna true
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = fillCollection();
        fillList();
        assertTrue(list.containsAll(c));
    }

    /**
     * Test del metodo containsAll con HCollection non contenuta come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando una lista e una collection non contengono gli stessi elementi containsAll ritorna false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = fillCollection();
        assertFalse(list.containsAll(c));
    }
	
    /**
     * Test del metodo equals - caso vero
     * @safe.precondition Lista inizializzata con gli stessi elementi di un'altra
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che il metodo equals ritorni true quando gli passo due liste che hanno subito gli stessi inserimenti
     */
    @Test
    public void testEquals() {
        HList list1 = fillListReturn();
        HList list2 = fillListReturn();
        assertEquals(list1, list2);
    }

    /**
     * Test del metodo equals - caso falso
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che il metodo equals ritorni false quando gli passo due liste che non hanno subito gli stessi inserimenti
     */
    @Test
    public void testEqualsFail() {
        list.add(0);
		HList list2 = fillListReturn();
        assertFalse(list.equals(list2));
	}

    /**
     * Test del metodo get con indice non valido
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia IndexOutOfBoundsException
     * @safe.testcases Il metodo testa che se chiamo get con un indice non valido viene lanciata IndexOutOfBoundsException
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testGetInvalidIndex() {
        list.get(1);
	}

    /**
     * Test del metodo get
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che inserendo un oggetto in posizione zero il metodo get mi ritorna quella posizione
     */
	@Test
    public void testGet() {
		Object o = new Object();
		list.add(o);
        assertEquals(o, list.get(0));
	}

    /**
     * Test del metodo hashCode
     * @safe.precondition Lista inizializzata uguale ad un'altra
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che due liste identiche hanno lo stesso hashcode
     */
	@Test
    public void testHashCode() {
        fillList();
        HList list1 = fillListReturn();
		assertEquals(list, list1);
		assertTrue(list.hashCode() == list1.hashCode());
    }
    
    /**
     * Test del metodo IndexOf con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando chiama IndexOf con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
	public void testIndexOfWithNull() {
		list.indexOf(null);
	}

    /**
     * Test del metodo indexOf con oggetto contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che inserendo un elemento in prima posizione indexOf ritorna 0
     */
	@Test
    public void testIndexOfObjectContained() {
        Object o = new Object();
        list.add(o);
        assertEquals(0, list.indexOf(o));
	}

    /**
     * Test del metodo indexOf con oggetto non contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che indexOf di un elemento non contenuto nella lista ritorna -1
     */
	@Test
    public void testIndexOfObjectNotContained() {
		Object o = new Object();
		assertEquals(-1, list.indexOf(o));
	}

    /**
     * Test del metodo isEmpty - caso vero
     * @safe.precondition Lista inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che con una lista vuota isEmpty ritorna true
     */
    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
	}
	
    /**
     * Test del metodo isEmpty - caso falso
     * @safe.precondition Lista inizializzata non vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che con una lista non vuota isEmpty ritorna false
     */
	@Test
    public void testIsEmptyFalse() {
		fillList();
        assertFalse(list.isEmpty());
	}

    
    /**
     * Test del metodo lastIndexOf con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si chiama lastIndexOf con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
	public void testLastIndexOfWithNull() {
		list.lastIndexOf(null);
	}

    /**
     * Test del metodo lastIndexOf con un oggetto più volte contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che l'indice di un elemento presente due volte nella lista è 1
     */
	@Test
    public void testLastIndexOfObjectContained() {
        Object o = new Object();
        list.add(o);
        list.add(o);
        assertEquals(1, list.lastIndexOf(o));
	}

    /**
     * Test del metodo lastIndexOf con un oggetto non contenuto
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando lastIndexOf su un oggetto non contenuto questo ritorna -1
     */
	@Test
    public void testLastIndexOfObjectNotContained() {
        fillList();
		Object o = new Object();
		assertEquals(-1, list.lastIndexOf(o));
	}
	
    /**
     * Test del metodo remove con indice invalido
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia IndexOutOfBoundsException
     * @safe.testcases Il metodo controlla che venga lanciata IndexOutOfBoundsException in caso di indice invalido
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveInvalidIndex() {
        list.remove(2);
    }
    
    /**
     * Test del metodo remove con un null
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si chiama remove con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        list.remove(null);
	}
	
    /**
     * Test del metodo remove - caso vero
     * @safe.precondition Lista inizializzata con elemento
     * @safe.postcondition Lista con elemento rimosso
     * @safe.testcases Il metodo verifica che il metodo remove elimina effettivamente un oggetto dalla lista
     */
    @Test
    public void testRemove() {
        Object obj = new Object();
        list.add(obj);
        assertTrue(list.remove(obj));
        assertFalse(list.contains(obj));
    }

    /**
     * Test del metodo remove - caso falso
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che il metodo remove non elimina un oggetto dalla lista se questo non è contenuto
     */
    @Test
    public void testRemoveFail() {
        assertFalse(list.remove(new Object()));
	}
    
	
    /**
     * Test del metodo removeAll con null
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si chiama removeAll con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        list.removeAll(null);
    }
    
    /**
     * Test del metodo removeAll con una HCollection contenuta
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lista senza i valori della HCollection
     * @safe.testcases Il metodo verifica che se chiamo removeAll di una HCollection contenuta in una lista questa risulterà vuota
     */
    @Test
    public void testRemoveAllHCollectionContained() {
        HCollection c = fillCollection();
        list.addAll(c);
        list.removeAll(c);
		assertTrue(list.isEmpty());
    }

    /**
     * Test del metodo removeAll con una HCollection non contenuta
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se chiamo removeAll su una lista che non contiene valori della HCollection ritornerà false
     */
    @Test
    public void testRemoveAllCollectionNotContained() {
        HCollection c = fillCollection();
		assertFalse(list.removeAll(c));
	}


    /**
     * Test del metodo retainAll con null
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si chiama retainAll con un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        list.retainAll(null);
	}

    /**
     * Test del metodo retainAll con una HCollection contenuta
     * @safe.precondition Lista inizializzata contenente i valori di una HCollection
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se chiamo retainAll su una lista che contiene tutti i valori della HCollection questa non cambia
     */
    @Test
    public void testRetainAllAllWithHCollectionContained() {
        HCollection c = fillCollection();
        fillList();
        list.retainAll(c);
		assertEquals(5, list.size());
    }

    /**
     * Test del metodo retainAll con una HCollection non contenuta
     * @safe.precondition Lista inizializzata con un elemento
     * @safe.postcondition Lista vuota 
     * @safe.testcases Il metodo verifica che se chiamo retainAll su una lista che non contiene elementi della collection questa verrà svuotata
     */
	@Test
    public void testRetainAllWithHCollectionNotContained() {
        HCollection c = fillCollection();
        list.add("string");
        list.retainAll(c);
		assertEquals(0, list.size());
	}

    /**
     * Test del metodo set con indice invalido
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia IndexOutOfBoundsException
     * @safe.testcases Il metodo testa che chiamando testSet con indice invalido viene lanciata IndexOutOfBoundsException
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testSetInvalidIndex() {
        list.set(-1, new Object());
	}
	
    /**
     * Test del metodo set con null
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testSetWithNull() {
        list.set(0, null);
	}

    /**
     * Test del metodo set
     * @safe.precondition Lista inizializzata con degli elementi
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che set modifica il valore dell'oggetto in posizione 3
     */
	@Test
    public void testSet() {
        fillList();
		Object o = new Object();
		list.set(3, o);
		assertEquals(o, list.get(3));
	}

    /**
     * Test del metodo size con lista vuota
     * @safe.precondition Lista inizializzata vuota
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che se una lista è vuota, size ritorna zero
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(0, list.size());
    }

    /**
     * Test del metodo size con lista non vuota
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se inserisco 5 elementi nella lista la sua size sarà di 5
     */
    @Test
    public void TestSizeNotEmpty() {
        fillList();
        assertEquals(5, list.size());
	}

    /**
     * Test del metodo toArray
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se chiamo toArray suna lista contenente alcuni elementi questo mi ritorna un array contenente gli stessi elementi
     */
    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }
        Object[] setArray = list.toArray();
        for(int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), setArray[i]);
        }
    }

    /**
     * Test del metodo toArray con parametro di lunghezza minore della lista
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando il toArray viene restituito un array composto da valori appartenenti al Set, questi saranno solo alcuni dei valori poichè la dimensione del parametro è minore di quella della List
     */
    @Test
    public void testToArrayWithParameterSizeSmaller() {
        for(int i = 0; i < 10; i++) {
            list.add(i);
        }
        Object[] param = new Object[5];
        Object[] setArray = list.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertEquals(list.get(i), setArray[i]);
        }
    }

    /**
     * Test del metodo toArray con parametro di dimensione maggiore della lista
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che chiamando il toArray viene restituito un array composto da valori appartenenti al Set e da null, poichè di dimensione maggiore a quella di List
     */
    @Test
    public void testToArrayWithParameterSizeLonger() {
        for(int i = 0; i < 5; i++) {
            list.add(i);
        }
        Object[] param = new Object[10];
        Object[] setArray = list.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), setArray[i]);
        }
        for(int i = list.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

    /**
     * Test del metodo toArray con null come parametro
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che quando si chiama toArray con un elemento null viene lanciata NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        list.toArray(null);
    }

}