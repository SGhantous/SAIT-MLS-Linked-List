package saitMLS.problemDomain;

import java.io.Serializable;

import saitMLS.exceptions.InvalidLegalDescriptionException;

public class Property implements Serializable
{
	long id;
	String legalDescription;
	String address;
	String quadrant;
	String zone;
	double askingPrice;
	String comments;
	  
	public Property()
	{
		
	}
	public Property(long id, String legalDescription, String address, String quadrant, String zone, double askingPrice, String comments) throws InvalidLegalDescriptionException
	{
			  
	}
	  
	/**
	 * Method to return the value of id
	 * @return the id
	 */
	public long getId()
	{
		return id;
	}
	/**
	 * Method to set the value of id
	 * @param id the id to set
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	/**
	 * Method to return the value of legalDescription
	 * @return the legalDescription
	 */
	public String getLegalDescription()
	{
		return legalDescription;
	}
	/**
	 * Method to set the value of legalDescription
	 * @param legalDescription the legalDescription to set
	 */
	public void setLegalDescription(String legalDescription) throws InvalidLegalDescriptionException
	{
		this.legalDescription = legalDescription;
	}
	/**
	 * Method to return the value of address
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	/**
	 * Method to set the value of address
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	/**
	 * Method to return the value of quadrant
	 * @return the quadrant
	 */
	public String getQuadrant()
	{
		return quadrant;
	}
	/**
	 * Method to set the value of quadrant
	 * @param quadrant the quadrant to set
	 */
	public void setQuadrant(String quadrant)
	{
		this.quadrant = quadrant;
	}
	/**
	 * Method to return the value of zone
	 * @return the zone
	 */
	public String getZone()
	{
		return zone;
	}
	/**
	 * Method to set the value of zone
	 * @param zone the zone to set
	 */
	public void setZone(String zone)
	{
		this.zone = zone;
	}
	/**
	 * Method to return the value of askingPrice
	 * @return the askingPrice
	 */
	public double getAskingPrice()
	{
		return askingPrice;
	}
	/**
	 * Method to set the value of askingPrice
	 * @param askingPrice the askingPrice to set
	 */
	public void setAskingPrice(double askingPrice)
	{
		this.askingPrice = askingPrice;
	}
	/**
	 * Method to return the value of comments
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}
	/**
	 * Method to set the value of comments
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	
}
