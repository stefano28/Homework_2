package test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

public class ListAdapterSublistTest {

	private HList list = null;

	/**
	 * 
	 */
	@Before
	public  void setUp() {
		HList l = fillListReturn();
        HList sublist = l.subList(0, 3);
        list = sublist;
	}

    public HList fillListReturn() {
        HList l = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			l.add(i);
        }
        return l;
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
		list.add(10, new Object());
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
		list.add(10, c);
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
        list.get(-1);
	}

    /**
     * Test del metodo get
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che inserendo un oggetto in posizione zero il metodo get mi ritorna quella posizione
     */
	@Test
    public void testGet() {
        assertEquals(0, list.get(0));
    }
    
    @Test
    public void checkReflection() {
		HList l = fillListReturn();
        HList sublist = l.subList(0, 3);
        list = sublist;
    }

}