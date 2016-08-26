package utilities;

public class SLL implements List
{
	//Attributes
	private SLLNode 	head;
	private SLLNode 	tail;
	private int 		size;
	
	//Operational Methods
	@Override
	public boolean append(Object element)
	{
		SLLNode nodeToBeAdded = new SLLNode(element);
		
		if(!isEmpty())
		{
			SLLNode current = head;
			while(current.next != null)
			{
				current = current.next;
			}
			current.next = nodeToBeAdded;
			tail = nodeToBeAdded;
		}
		else
		{			
			tail = head = nodeToBeAdded;
		}
		size++;
		return true;
	}

	@Override
	public boolean add(Object element)
	{
		SLLNode nodeToBeAdded = new SLLNode(element);
		if(!isEmpty())
		{
			nodeToBeAdded.next = head;
			head = nodeToBeAdded;
		}
		else
		{
			tail = head = nodeToBeAdded;
		}
		size++;
		return true;
	}

	@Override
	public boolean add(Object element, int position)
			throws IndexOutOfBoundsException
	{
		if (position < 0 || position > size)
			throw new IndexOutOfBoundsException();
		
		if (position == size)
		{
			append(element);
			return true;
		}
		else if (position == 0)
		{
			add(element);
			return true;
		}
		SLLNode current = head;
		for(int i = 0; i < position - 1; i++)
			current = current.next;
		SLLNode nodeToBeAdded = new SLLNode(element);
		nodeToBeAdded.next = current.next;
		current.next = nodeToBeAdded;
		size++;		
		return true;
	}

	@Override
	public void clear()
	{
		tail = head = null;
		size = 0;		
	}

	@Override
	public Object remove()
	{
		if(!isEmpty())
		{
			SLLNode removed = head;
			head = head.next;
			if(head == null)
			{
				tail = head;
			}
			size--;
			return removed.element;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object removeLast()
	{
		SLLNode removed = null;
		if(!isEmpty())
		{
			if (size == 1)
			{
				removed = head;
				head = head.next;
				if(head == null)
				{
					tail = head;
				}
				size--;
				return removed.element;
			}
			else
			{
				removed = head;
				while(removed != tail)
					removed = removed.next;
				tail = removed;
				tail.next = null;
				size--;
				return removed.element;
				
			}
		}
		else
			return null;
	}

	@Override
	public Object remove(int position) throws IndexOutOfBoundsException
	{
		SLLNode delete = head;
		SLLNode prev = null;
		
		if(position < 0 || position >= size)
			throw new IndexOutOfBoundsException();
		if(position == 0)
		{
			delete = head;
			head = head.next;
			if(head == null)
			{
				tail = head;
			}
			size--;		
		}
		else
		{
			for(int i = 0; i < position; i ++)
			{
				prev = delete;
				delete = delete.next;
			}
			prev.next = delete.next;
			size--;
		}
		
		return delete.element;
	}

	@Override
	public Object get()
	{
		if(!isEmpty())
		{
			return head.element;
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public Object getLast()
	{
		if(!isEmpty())
		{
			return tail.element;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Object get(int position) throws IndexOutOfBoundsException
	{
		if(position < 0 || position >= size)
			throw new IndexOutOfBoundsException("The index is outside of the current bounds of list: index = " + position);
		
		SLLNode current = head;
		int i = 0;
		
		while(i < position)
		{
			current = current.next;
			i++;
		}
		
		return current.element;

	}

	@Override
	public Object set(Object element, int position)
			throws IndexOutOfBoundsException
	{
		if(position < 0 || position >= size)
			throw new IndexOutOfBoundsException();
		if(!isEmpty())
		{
			SLLNode current = head;
			for(int i = 0; i < position; i ++)
				current = current. next;
			current.element = element;
			return current.element;
		}
		else
			return null;
	}

	@Override
	public boolean contains(Object element)
	{
		SLLNode current = head;
		for(int i = 0; i < size; i ++)
		{
			if(current.element.equals(element))
				return true;
			current = current.next;
		}
		return false;
	}

	@Override
	public int indexOf(Object element)
	{
		if(!isEmpty())
		{
			SLLNode current = head;
			int i = 0;
			boolean endOfList = false;
			for(i=0; !(current.element).equals(element) && !endOfList; i++)
			{				
				if(current.next == null)
				{
					endOfList = true;
				}
				else
					current = current.next;
			}
			if (i == size)
				return -1;
			return i;
		}
		else
			return -1;
	}

	@Override
	public int size()
	{
		if(isEmpty())
			return size = 0;
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return head==null;
	}
	
	/**
	 * Method to print the content of list
	 */
	private void printList()
	{
		for(int i = 0; i < size; i++)
		{
			System.out.println(get(i));
		}
	}

	public static void main(String args[])
	{
		SLL myList = new SLL();
		myList.append("one");
		myList.append("two");
		myList.append("three");
		myList.append("four");
		myList.append("five");
		myList.removeLast();
		myList.printList();
		System.out.println(myList.size);
	}


}
