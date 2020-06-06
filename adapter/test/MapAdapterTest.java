package adapter.test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test Case Class for MapAdapter
 */
public class MapAdapterTest {

    private HMap map = null;

    /**
     * Bootstrap
     */

    @Before
    public void start() {
        map = new MapAdapter();
    }

    /**
     * TestClear
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
     * TestContainsKey
     */

    @Test(expected = NullPointerException.class)
    public void TestContainsKeyWithNull() {
        map.containsKey(null);
    }

    @Test
    public void testContainsKeyWithObjContained() {
        map.put(1, new Object());
        assertTrue(map.containsKey(1));
    }

    @Test
    public void testContainsKeyWithObjNotContained() {
        assertFalse(map.containsKey(1));
    }

    /**
     * TestContainsValue
     */

    @Test(expected = NullPointerException.class)
    public void TestContainsValueWithNull() {
        map.containsValue(null);
    }

    /**
     * TestEntrySet
     */

    /**
     * TestEquals
     */

    /**
     * TestGet
     */

    /**
     * TestHashCode
     */

    /**
     * TestIsEmpty
     */

    /**
     * TestKeySet
     */

    /**
     * TestPut
     */

    /**
     * TestPutAll
     */

    /**
     * TestRemove
     */

    /**
     * TestSize
     */

    /**
     * TestValues
     */
}