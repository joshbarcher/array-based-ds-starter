import adts.IOrderedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Tests an implementation of the IOrderedList interface.
 *
 * DO NOT EDIT THIS FILE!
 *
 * @author Josh Archer
 * @version 1.0
 */
public class ListTest
{
    private IOrderedList list;

    //test arrays
    private int[] unorderedTestArray = {5, 8, 7, 4, 3, 6};
    private int[] orderedTestArray = {3, 4, 5, 6, 7, 8};
    private int[] missing = {1, 2, 9, 10};

    /*
        This method should create and return a new list
        for the methods below.
     */
    private IOrderedList createList()
    {
        //instantiate your class here!
        return null;
    }

    /**
     * Creates a new list before each test.
     */
    @Before
    public void setup()
    {
        list = createList();
    }

    /**
     * Tests whether elements can be added to
     * the list successfully.
     */
    @Test
    public void addingElementsTest()
    {
        addUnorderedElements();

        for (int i = 0; i < unorderedTestArray.length; i++)
        {
            Assert.assertTrue("Elements added to list not discoverable with contains()",
                                list.contains(unorderedTestArray[i]));
        }
    }

    /**
     * Tests whether elements are ordered after
     * calling add().
     */
    @Test
    public void orderedElementsTest()
    {
        addUnorderedElements();

        for (int i = 0; i < orderedTestArray.length; i++)
        {
            Assert.assertEquals("Elements added to list are not in ascending order",
                                orderedTestArray[i], list.get(i));
        }
    }

    /**
     * Verifies that elements can be inserted in order
     * at the lower indices of the list.
     */
    @Test
    public void smallestIndexTest()
    {
        addUnorderedElements();

        //add new elements to the lowest index before elements
        list.add(2);
        list.add(1);

        Assert.assertEquals("Elements are not returned in sorted order",
                            1, list.get(0));
        Assert.assertEquals("Elements are not returned in sorted order",
                            2, list.get(1));
    }

    /**
     * Verifies that elements can be inserted in order
     * at the higher indices of the list.
     */
    @Test
    public void largestIndexTest()
    {
        addUnorderedElements();

        //add a new element to the highest index after elements
        list.add(10);
        list.add(9);

        Assert.assertEquals("Elements are not returned in sorted order",
                            9, list.get(unorderedTestArray.length));
        Assert.assertEquals("Elements are not returned in sorted order",
                            10, list.get(unorderedTestArray.length + 1));
    }

    /**
     * Verifies that the list reports the size of the structure
     * correctly.
     */
    @Test
    public void sizeTest()
    {
        addUnorderedElements();

        //list should be empty to start with
        Assert.assertEquals("List should have size " + unorderedTestArray.length +
                            "after calls to add()", unorderedTestArray.length,
                            list.size());
        Assert.assertFalse("List should not be empty after calling add()", list.isEmpty());
    }

    /**
     * Verifies that the list reports the size of the structure
     * correctly.
     */
    @Test
    public void sizeEmptyTest()
    {
        //list should be empty to start with
        Assert.assertEquals("List should have size 0 after no calls to add()",
                            0, list.size());
        Assert.assertTrue("List should be empty after no calls to add()", list.isEmpty());
    }

    /**
     * Verifies that negative indices are rejected.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegativeIndexTest()
    {
        //should throw an exception
        list.get(-1);
    }

    /**
     * Verifies that requests for elements in an empty
     * list are rejected.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexWithEmptyListTest()
    {
        //should throw an exception
        list.get(0);
    }

    /**
     * Verifies that indices that are out of range (too high)
     * are rejected.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void getIndexTooLargeTest()
    {
        addUnorderedElements();

        //should throw an exception
        list.get(unorderedTestArray.length);
    }

    /**
     * Verifies that elements can be found in the list
     * with contains().
     */
    @Test
    public void containsExistsTest()
    {
        addUnorderedElements();

        //make sure elements are discoverable
        for (int i = 0; i < unorderedTestArray.length; i++)
        {
            Assert.assertTrue("Added element not discoverable by contains()",
                                list.contains(unorderedTestArray[i]));
        }
    }

    /**
     * Verifies that elements not in the list cannot be discovered
     * with contains().
     */
    @Test
    public void containsMissingTest()
    {
        addUnorderedElements();

        //make sure elements are discoverable
        for (int i = 0; i < missing.length; i++)
        {
            Assert.assertFalse("Missing element is discoverable by contains()",
                                list.contains(missing[i]));
        }
    }

    /**
     * Verifies that contains() does not return true
     * when the list is empty.
     */
    @Test
    public void containsOnEmptyListTest()
    {
        //contains should always return false on empty list
        Assert.assertFalse("Missing element is discoverable by contains()",
                            list.contains(3));

        //add an element and confirm contains() can discover it
        list.add(3);
        Assert.assertTrue("Element added to list is not discoverable by contains()",
                            list.contains(3));
    }

    /**
     * Removes several elements from the list and verifies that
     * they are no longer in the list.
     */
    @Test
    public void removeExistsTest()
    {
        addUnorderedElements();

        //get a random remove order
        int[] shuffled = shuffleNewArray(unorderedTestArray);

        //remove in random order
        for (int i = 0; i < shuffled.length; i++)
        {
            list.remove(shuffled[i]);
        }

        Assert.assertEquals("List size should be 0 after removing all elements", 0, list.size());
        Assert.assertTrue("List should be empty after removing all elements", list.isEmpty());
    }

    private int[] shuffleNewArray(int[] array)
    {
        Random random = new Random();
        int[] copied = new int[array.length];
        System.arraycopy(array, 0, copied, 0, array.length);

        for (int i = 0; i < copied.length; i++)
        {
            swap(i, random.nextInt(copied.length), copied);
        }

        return copied;
    }

    private void swap(int first, int second, int[] array)
    {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    /**
     * Verifies that missing elements cannot be removed
     * from the list.
     */
    @Test
    public void removeMissingTest()
    {
        addUnorderedElements();

        //each of these removals should throw an exception
        for (int i = 0; i < missing.length; i++)
        {
            try
            {
                list.remove(missing[i]);

                //fail, an exception should have occurred...
                Assert.fail("No exception is thrown when removing a missing element");
            }
            catch (NoSuchElementException ex)
            {
                //do nothing, this is correct...
                assert true;
            }
        }
    }

    /**
     * Verifies that the correct exception is thrown when
     * removing from an empty list.
     */
    @Test(expected = NoSuchElementException.class)
    public void removeOnEmptyListTest()
    {
        //no elements are present, this should give an exception
        list.remove(2);
    }

    /**
     * Verifies that clear() works correctly on an empty
     * list.
     */
    @Test
    public void clearEmptyTest()
    {
        //make sure no errors are encountered
        list.clear();

        Assert.assertEquals("List size should be 0 after calling clear()", 0, list.size());
        Assert.assertTrue("List should be empty after calling clear", list.isEmpty());
    }

    /**
     * Verifies that clear() works correctly on a non-empty
     * list.
     */
    @Test
    public void clearNotEmptyTest()
    {
        addUnorderedElements();

        //make sure no errors are encountered
        list.clear();

        Assert.assertEquals("List size should be 0 after calling clear()", 0, list.size());
        Assert.assertTrue("List should be empty after calling clear", list.isEmpty());
    }

    /**
     * Tests a large number of elements within the list.
     */
    @Test
    public void largeListTest()
    {
        //add enough elements for force a resize
        Random random = new Random();
        for (int i = 1; i <= 1000; i++)
        {
            list.add(random.nextInt(1000));
        }

        //verify elements are in order
        int current = (int)list.get(0);
        int next = (int)list.get(1);

        for (int i = 2; i < 1000; i++)
        {
            Assert.assertTrue("Elements are unordered for a large list",
                              current <= next);
            current = next;
            next = (int)list.get(i);
        }
    }

    /**
     * Verifies that add() works correctly after adding
     * elements, removing all elements, then inserting
     * a new element.
     */
    @Test
    public void addAfterEmptyingListTest()
    {
        addUnorderedElements();

        //remove all elements
        for (int i = 0; i < unorderedTestArray.length; i++)
        {
            list.remove(unorderedTestArray[i]);
        }

        //adding should still be successful
        list.add(2);
        Assert.assertEquals("The size of your list should be 1 after calling add() on an empty list",
                            1, list.size());
    }

    private void addUnorderedElements()
    {
        for (int i = 0; i < unorderedTestArray.length; i++)
        {
            list.add(unorderedTestArray[i]);
        }
        Assert.assertEquals("The size of your list should be " + unorderedTestArray.length +
                            "after calling add()", unorderedTestArray.length, list.size());
    }
}
