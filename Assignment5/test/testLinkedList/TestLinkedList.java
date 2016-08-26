package testLinkedList;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.SLL;

public class TestLinkedList
{
	SLL list;
	Integer one, two, three, four, five;
	
	@Before
	public void setUp() throws Exception
	{
		list = new SLL();
		one =  new Integer(12);
		two = new Integer(23);
		three = new Integer(44);
		four = new Integer(59);
		five = new Integer(68);
	}


	@After
	public void tearDown() throws Exception
	{
		list = null;
		one = null;
		two = null;
		three = null;
		four = null;
		five = null;
		
	}

	
	@Test
	public void testAppendToEnd1()
	{
		int expected = 68;
		
		list.append(one);
		list.append(two);
		list.append(three);
		list.append(four);
		list.append(five);
		
		Integer item = (Integer)list.get(4);
		int actual = item.intValue();
		assertEquals("The wrong number was appended to the end of the list", expected, actual);
		assertEquals("The size of the list is not correct", 5, list.size());
		
	}
	
	@Test
	public void testAppendToEnd2()
	{
		int expected = 44;
		
		list.append(one);
		list.append(two);
		list.append(three);

		
		Integer item = (Integer)list.get(2);
		int actual = item.intValue();
		assertEquals("The wrong number was appended to the end of the list", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
		
	}
	
	@Test
	public void testAddFirst1()
	{
		int expected = 12;
		list.append(two);
		list.append(three);
		list.append(four);
		list.add(one);
		
		Integer item = (Integer)list.get(0);
		int actual = item.intValue();
		assertEquals("The wrong number was added to the beginning of the list", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());
	}
	
	@Test
	public void testAddFirst2()
	{
		int expected = 68;
		list.append(one);
		list.append(two);
		list.append(three);
		list.append(four);
		list.add(five);
		
		Integer item = (Integer)list.get(0);
		int actual = item.intValue();
		assertEquals("The wrong number was added to the beginning of the list", expected, actual);
		assertEquals("The size of the list is not correct", 5, list.size());
	}

	@Test
	public void testAddFirst3()
	{
		int expected = 44;
		list.append(four);
		list.append(five);
		list.add(three);
		
		Integer item = (Integer)list.get(0);
		int actual = item.intValue();
		assertEquals("The wrong number was added to the beginning of the list", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
	}
	
	@Test
	public void testAddPosition1()
	{
		int expected = 23;
		list.append(one);
		list.add(two,0);
		
		Integer item = (Integer)list.get(0);
		int actual = item.intValue();
		assertEquals("The number was not added to the correct position", expected, actual);
		assertEquals("The size of the list is not correct", 2, list.size());
	}
	
	@Test
	public void testAddPosition2()
	{
		int expected = 59;
		list.append(one);
		list.append(two);
		list.append(three);
		list.add(four, 2);
		
		Integer item = (Integer)list.get(2);
		int actual = item.intValue();
		assertEquals("The number was not added to the correct position", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());
	}
	
	@Test
	public void testAddPosition3()
	{
		list.append(one);
		list.append(two);
		
		
		try
		{
			list.add(three, 3);
			fail("IndexOutOfBoundsException has not been raised");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testAddPosition4()
	{
		list.append(one);
		
		try
		{
			list.add(two,-1);
			fail("IndexOutOfBoundsException has not been raised");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
		
	}
	
	@Test
	public void testAddPosition5()
	{
		int expected = 59;
		list.append(one);
		list.append(two);
		list.append(three);
		list.add(four, 3);
		
		Integer item = (Integer)list.get(3);
		int actual = item.intValue();
		assertEquals("The number was not added to the correct position", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());
	}
	
	@Test
	public void testRemoveFirst1()
	{
		int expected = 23;
		list.append(two);
		list.append(one);
		list.append(three);
		
		Integer removed = (Integer)list.remove();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 2, list.size());
	}
	
	@Test
	public void testRemoveFirst2()
	{
		int expected = 12;
		list.append(one);
		list.append(five);
		list.append(two);
		list.append(three);
		list.append(four);
		
		Integer removed = (Integer)list.remove();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());
	}
	
	@Test
	public void testRemoveFirst3()
	{
		int expected = 59;
		list.append(four);
		
		Integer removed = (Integer)list.remove();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());
	}
	
	@Test
	public void testRemoveLast1()
	{
		int expected = 44;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer removed = (Integer)list.removeLast();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 2, list.size());
	}
	
	@Test
	public void testRemoveLast2()
	{
		int expected = 12;
		list.append(one);
		
		Integer removed = (Integer)list.removeLast();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());
	}
	
	@Test
	public void testRemoveLast3()
	{
		int expected = 68;
		list.append(one);
		list.append(two);
		list.append(three);
		list.append(four);
		list.append(five);
		
		Integer removed = (Integer)list.removeLast();
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());
	}
	
	@Test
	public void testRemoveLast4()
	{
		Object expected = null;
		
		Object actual = list.removeLast();
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());
		
	}
	
	@Test
	public void testRemovePosition1()
	{
		int expected = 12;
		list.append(one);
		
		Integer removed = (Integer)list.remove(0);
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());
	}
	
	@Test
	public void testRemovePosition2()
	{
		int expected = 23;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer removed = (Integer)list.remove(1);
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 2, list.size());
	}
	
	@Test
	public void testRemovePosition3()
	{
		int expected = 44;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer removed = (Integer)list.remove(2);
		int actual = removed.intValue();
		
		assertEquals("The wrong number was removed from the list", expected, actual);
		assertEquals("The size of the list is not correct", 2, list.size());
	}
	
	@Test
	public void testRemovePosition4()
	{
		list.append(one);
		
		try
		{
			list.remove(-1);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testRemovePosition5()
	{
		list.append(one);
		
		try
		{
			list.remove(1);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetFirst1()
	{
		int expected = 12;
		list.append(one);
		
		Integer returned = (Integer)list.get();
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 1, list.size());
	}
	
	@Test
	public void testGetFirst2()
	{
		int expected = 44;
		list.append(three);
		list.append(four);
		list.append(five);
		
		Integer returned = (Integer)list.get();
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
	}
	
	@Test
	public void testGetFirst3()
	{
		Object expected = null;
		Object returned = list.get();
		
		assertEquals("The wrong number was returned", expected, returned);
		assertEquals("The size of the list is not correct", 0, list.size());
	}
	
	@Test
	public void testGetLast1()
	{
		int expected = 44;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer returned = (Integer)list.getLast();
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testGetLast2()
	{
		int expected = 68;
		list.append(five);
		
		Integer returned = (Integer)list.getLast();
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 1, list.size());		
	}
	
	@Test
	public void testGetLast3()
	{
		int expected = 12;
		list.append(four);
		list.append(five);
		list.append(three);
		list.append(one);
		
		Integer returned = (Integer)list.getLast();
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 4, list.size());		
	}
	
	@Test
	public void testGetLast4()
	{
		Object expected = null;
		Object returned = list.getLast();
		
		assertEquals("The wrong number was returned", expected, returned);
		assertEquals("The size of the list is not correct", 0, list.size());		
	}
	
	@Test
	public void testGetPosition1()
	{
		int expected = 12;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer returned = (Integer)list.get(0);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());	
	}
	
	@Test
	public void testGetPosition2()
	{
		int expected = 59;
		list.append(four);
		
		Integer returned = (Integer)list.get(0);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 1, list.size());	
	}
	
	@Test
	public void testGetPosition3()
	{
		int expected = 59;
		list.append(five);
		list.append(four);
		list.append(three);
		
		Integer returned = (Integer)list.get(1);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());	
	}
	
	@Test
	public void testGetPosition4()
	{
		int expected = 44;
		list.append(one);
		list.append(two);
		list.append(three);
		
		Integer returned = (Integer)list.get(2);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());	
	}
	
	@Test
	public void testGetPosition5()
	{
		
		list.append(one);
		
		try
		{
			list.get(1);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testGetPosition6()
	{
		
		list.append(two);
		list.append(three);
		list.append(four);
		
		try
		{
			list.get(3);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}	
	
	@Test
	public void testGetPosition7()
	{
		
		list.append(two);
		list.append(three);
		list.append(four);
		
		try
		{
			list.get(-1);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testSet1()
	{
		int expected = 59;
		list.append(one);
		list.append(two);
		list.append(three);
		
		
		Integer returned = (Integer)list.set(four, 2);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testSet2()
	{
		int expected = 23;
		list.append(one);
				
		Integer returned = (Integer)list.set(two, 0);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 1, list.size());		
	}
	
	@Test
	public void testSet3()
	{
		list.append(one);
		list.append(two);
				
		try
		{
			list.set(three, 3);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testSet4()
	{
		int expected = 59;
		list.append(one);
		list.append(two);
		list.append(three);
				
		Integer returned = (Integer)list.set(four, 1);
		int actual = returned.intValue();
		
		assertEquals("The wrong number was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testSet5()
	{
		list.append(one);
						
		try
		{
			list.set(two, -1);
			fail("IndexOutOfBoundsException has not been raised.");
		}
		catch (IndexOutOfBoundsException e)
		{
			assertTrue(true);
		}
	}
	
	@Test
	public void testContains1()
	{
		boolean expected = true;
		list.append(one);
		list.append(two);
		list.append(three);
		
		boolean actual = list.contains(two);
		
		assertEquals("The number was not contained in the list", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
	}
	
	@Test
	public void testContains2()
	{
		boolean expected = true;
		list.append(three);
		list.append(four);
		list.append(five);
		
		boolean actual = list.contains(five);
		
		assertEquals("The number was not contained in the list", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
	}
	
	@Test
	public void testContains3()
	{
		boolean expected = false;
		list.append(three);
		list.append(four);
		list.append(five);
		
		boolean actual = list.contains(one);
		
		assertEquals("The number was contained in the list", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());
	}
	
	@Test
	public void testIndexOf1()
	{
		int expected = 0;
		list.append(one);
		list.append(two);
		list.append(three);
		
		int actual = list.indexOf(one);
		
		assertEquals("The wrong index value was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testIndexOf2()
	{
		int expected = 2;
		list.append(one);
		list.append(two);
		list.append(three);
		
		int actual = list.indexOf(three);
		
		assertEquals("The wrong index value was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testIndexOf3()
	{
		int expected = -1;
		list.append(one);
		list.append(two);
		list.append(three);
		
		int actual = list.indexOf(four);
		
		assertEquals("The wrong index value was returned", expected, actual);
		assertEquals("The size of the list is not correct", 3, list.size());		
	}
	
	@Test
	public void testIndexOf4()
	{
		int expected = -1;
		
		int actual = list.indexOf(four);
		
		assertEquals("The wrong index value was returned", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());		
	}
	
	@Test
	public void testIsEmpty1()
	{
		boolean expected = false;
		list.append(one);
		
		boolean actual = list.isEmpty();
		
		assertEquals("The list was registered as empty", expected, actual);
		assertEquals("The size of the list is not correct", 1, list.size());
	}
	
	@Test
	public void testIsEmpty2()
	{
		boolean expected = true;
				
		boolean actual = list.isEmpty();
		
		assertEquals("The list was registered as empty", expected, actual);
		assertEquals("The size of the list is not correct", 0, list.size());
	}
}
