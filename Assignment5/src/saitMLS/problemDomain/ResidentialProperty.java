package saitMLS.problemDomain;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;

public class ResidentialProperty extends Property
{
	double area;
	double bathrooms;
	int bedrooms;
	char garage;
	  
	public ResidentialProperty() 
	{
		
	}
	  
	public ResidentialProperty(long id, String legalDescription, String address, String quadrant, String zone, double askingPrice, String comments, 
								double area, double bathrooms, int bedrooms, char garage) throws InvalidLegalDescriptionException, InvalidNumberOfBathroomsException
	{
		
	}

	/**
	 * Method to return the value of area
	 * @return the area
	 */
	public double getArea()
	{
		return area;
	}

	/**
	 * Method to set the value of area
	 * @param area the area to set
	 */
	public void setArea(double area)
	{
		this.area = area;
	}

	/**
	 * Method to return the value of bathrooms
	 * @return the bathrooms
	 */
	public double getBathrooms()
	{
		return bathrooms;
	}

	/**
	 * Method to set the value of bathrooms
	 * @param bathrooms the bathrooms to set
	 */
	public void setBathrooms(double bathrooms) throws InvalidNumberOfBathroomsException
	{
		this.bathrooms = bathrooms;
	}

	/**
	 * Method to return the value of bedrooms
	 * @return the bedrooms
	 */
	public int getBedrooms()
	{
		return bedrooms;
	}

	/**
	 * Method to set the value of bedrooms
	 * @param bedrooms the bedrooms to set
	 */
	public void setBedrooms(int bedrooms)
	{
		this.bedrooms = bedrooms;
	}

	/**
	 * Method to return the value of garage
	 * @return the garage
	 */
	public char getGarage()
	{
		return garage;
	}

	/**
	 * Method to set the value of garage
	 * @param garage the garage to set
	 */
	public void setGarage(char garage)
	{
		this.garage = garage;
	}
	
	
}
