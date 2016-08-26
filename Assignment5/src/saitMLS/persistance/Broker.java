package saitMLS.persistance;

import java.util.ArrayList;

import utilities.List;

public interface Broker
{
	/**
	 * Method to search Property database and return list of results.
	 * @param paramObject - Object being searched for
	 * @return searchResults - list of search results
	 */
	public List search (Object paramObject);
	
	/**
	 * Method to add a Property object to the end of a Singly Linked List.
	 * @param paramObject - the Property object being added.
	 * @return true if the Property is added and false if it isn't.
	 * @throws Exception
	 */
	public boolean persist (Object paramObject) throws Exception;
	
	/**
	 * Method to physically delete the supplied object from the Singly Linked List.
	 * @param paramObject - Property object to be removed.
	 * @return true if the Property is delete and false if it isn't.
	 */
	public boolean remove (Object paramObject);
	
	/**
	 * Method to close the serialized file.
	 */
	public void closeBroker();
}
