package test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;

public class ListIteratorTest {

    private ListAdapter list = null;
    
	/**
	 * 
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

    /**
     * Test di listIterator
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo si occupa di testare se l'iteratore scorre correttamente la lista inserendo tutti gli elementi che incontra in un altra lista, alla fine controlla se le due sono uguali
     */
    @Test
    public void testIterator() {
        fillList();
        HIterator iter = list.iterator();
        HList list2 = new ListAdapter();
        while(iter.hasNext()) {
            list2.add(iter.next());
        }
        assertEquals(list, list2);
    }
    
    /**
     * Test di hasNext di listIterator
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nesuna
     * @safe.testcases Il metodo verifica che inserendo un elemento hasNext dall'indice zero ritorna true
     */
    @Test
    public void testListIteratorHasNext() {
        fillList();
        HListIterator iter = list.listIterator();
        assertTrue(iter.hasNext());
    }

    /**
     * Test di next di listIterator
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nesuna
     * @safe.testcases Il metodo verifica che inserendo un elemento o questo sarà il primo ad essere attraversato dall'iteratore
     */
    @Test
    public void testListIteratorNext() {
        Object o = new Object();
        list.add(o);
        HListIterator iter = list.listIterator();
        assertEquals(iter.next(), o);
    }
    
    /**
     * Test del metodo next di listIterator con errore di next
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NoSuchElementException
     * @safe.testcases Il metodo verifica che quando l'iteratore non trova più elementi con next lancia NoSuchElementException
     */
	@Test(expected = NoSuchElementException.class)
    public void testListIteratorEnd() {
        fillList();
        HListIterator iter = list.listIterator();
        for(int i = 0; i < 10; i++) {
            iter.next();
		}
    }
    
    /**
     * Test del metodo previous di listIterator
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che in una List contenente un oggetto, il previous dell'iteratore sarà quell'elemento
     */
	@Test
    public void testListIteratorPrevious() {
        Object o = new Object();
        list.add(o);
        HListIterator iter = list.listIterator();
        while(iter.hasNext())
            iter.next();
        assertEquals(iter.previous(), o);
    }

    /**
     * Test del metodo add di listIterator
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che l'inserimento con iteratore avviene correttamente
     */
	@Test
	public void testListIteratorAdd() {
        HListIterator iter = list.listIterator();
		Object o = new Object();
        iter.add(o);
        while(iter.hasNext())
		    assertEquals(o, iter.next());
    }
    
    /**
     * Test del metodo add di listIterator con null
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Lancia NullPointerException
     * @safe.testcases Il metodo verifica che quando si inserisce un elemento null viene lanciata NullPointerException
     */
	@Test (expected = NullPointerException.class)
	public void testListIteratorAddWithNull() {
		HListIterator iter = list.listIterator();
		iter.add(null);
    }
    
    /**
     * Test del metodo remove di listIterator
     * @safe.precondition Lista inizializzata con elemento
     * @safe.postcondition Elemento rimosso
     * @safe.testcases Il metodo verifica che eseguendo il remove su una lista contenente un elemento questa risulta vuota
     */
	@Test
    public void testListIteratorRemove() {
        fillList();
        HListIterator iter = list.listIterator();
        while(iter.hasNext()) {
            iter.next();
            iter.remove();
        }
        assertEquals(0, list.size());
    }

    /**
     * Test del metodo nextIndex
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo verifica che se l'iteratore è in posizione zero il nextIndex sarà 1
     */
	@Test
    public void testListIteratorNextIndex() {
        fillList();
        HListIterator iter = list.listIterator();
        iter.next();
        assertEquals(1, iter.nextIndex());
    }
    
    /**
     * Test del metodo previousIndex
     * @safe.precondition Lista inizializzata
     * @safe.postcondition Nessuna
     * @safe.testcases Il metodo testa che in un iteratore a fine lista il previousIndex sarà size - 1
     */
	@Test
    public void testListIteratorPreviousIndex() {
        fillList();
        HListIterator iter = list.listIterator();
        while(iter.hasNext())
            iter.next();
        assertEquals(4, iter.previousIndex());
    }
}