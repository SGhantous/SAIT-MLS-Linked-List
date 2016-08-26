package utilities;

public class SLLNode
{
	//Attributes
	Object element;
	SLLNode next;

	/**
	 * Constructs a SLLNode with an element
	 * @param element - Object containing element to be added
	 */
	public SLLNode(Object element)
	{
		this(element, null);
	}
	/**
	 * Constructs a SLLNode with an element and a next as specified 
	 * @param element - Object containing element to be added
	 * @param next - link to the next element
	 */
	public SLLNode(Object element, SLLNode next)
	{
		setElement(element);
		setNext(next);
	}

	//Getter and Setter Methods
	/**
	 * Method to return the value of element
	 * @return the element
	 */
	public Object getElement()
	{
		return element;
	}

	/**
	 * Method to set the value of element
	 * @param element the element to set
	 */
	public void setElement(Object element)
	{
		this.element = element;
	}

	/**
	 * Method to return the value of next
	 * @return the next
	 */
	public SLLNode getNext()
	{
		return next;
	}

	/**
	 * Method to set the value of next
	 * @param next the next to set
	 */
	public void setNext(SLLNode next)
	{
		this.next = next;
	}
	

	
	//Operational Methods
}
