package saitMLS.problemDomain;

import saitMLS.exceptions.InvalidLegalDescriptionException;

public class CommercialProperty extends Property
{
	String type;
	int noFloors;
	  
	public CommercialProperty() 
	{
		
	}
	  
	public CommercialProperty(long id, String legalDescription, String address, String quadrant, String zone, double askingPrice, 
								String comments, String type, int noFloors) throws InvalidLegalDescriptionException
	{
		
	}

	/**
	 * Method to return the value of type
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * Method to set the value of type
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Method to return the value of noFloors
	 * @return the noFloors
	 */
	public int getNoFloors()
	{
		return noFloors;
	}

	/**
	 * Method to set the value of noFloors
	 * @param noFloors the noFloors to set
	 */
	public void setNoFloors(int noFloors)
	{
		this.noFloors = noFloors;
	}
	
	
}
