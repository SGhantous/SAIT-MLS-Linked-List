package saitMLS.problemDomain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;

/**
 * Client.java
 *
 * @author Steven Ghantous
 * @version 1.0 
 * 
 * Class Description:  Data container class for a Client object.
 */
public class Client
{
	//Constants
	public static final int			SIZE = 136;
	
	//Attributes
	private long 			id; //8 bytes
	private String			firstName; //2 bytes overhead + 23 bytes for characters = 25 bytes
	private String			lastName; //2 bytes overhead + 23 bytes for characters = 25 bytes
	private String			address; //2 bytes overhead + 50 bytes for characters = 52 bytes
	private String			postalCode; //2 bytes overhead + 7 bytes for character = 9 bytes
	private String			phoneNumber; //2 bytes overhead + 12 bytes for characters = 14 bytes
	private char			clientType; //2 bytes
	private boolean			active; //1 byte
	private Pattern			pattern;
	private Matcher			matcher;
	
	//Constructors
	/**
	 * Default constructor
	 * Sets all instance level variables to their default values.
	 */
	public Client()
	{
		setActive(true);//boolean default is false. Sets to true for new Client object.
	}
	/**
	* User defined constructor that allows the programmer to initialize
	* instance attributes at the time of declaration of the Client object
	* instance. This constructor tokenizes a given string of the following
	* format:
	* 		Fred;Flintstone;34 Flintrock Way;T3R 5B6;403-295-9076;C
	* @param line text string of a Client in the proper format.
	 * @throws InvalidPostalCodeException 
	 * @throws InvalidPhoneNumberException 
	*/
	public Client (String line) throws InvalidPostalCodeException, InvalidPhoneNumberException
	{
		StringTokenizer st = new StringTokenizer(line, ";");
		
		setFirstName(st.nextToken());
		setLastName(st.nextToken());
		setAddress(st.nextToken());
		setPostalCode(st.nextToken());
		setPhoneNumber(st.nextToken());
		setClientType(st.nextToken().charAt(0));
		setActive(true);
	}
	
	//Getter and Setter Methods
	/**
	 * Method to return the value of Client id
	 * @return id - value of the Client id
	 */
	public long getId()
	{
		return id;
	}
	
	/**
	 * Method to set the value of Client id
	 * @param id - value of the Client id 
	 */
	public void setId(long id)
	{
		this.id = id;
	}
	
	/**
	 * Method to return the Client firstName
	 * @return the firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Method to set the Client firstName
	 * @param firstName - Client firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Method to return the Client lastname
	 * @return the lastName
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * Method to set the Client lastName
	 * @param lastName - Client lastname
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * Method to return the Client address
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Method to set the Client address
	 * @param address - Client address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	/**
	 * Method to return the Client postalCode
	 * @return the postalCode
	 */
	public String getPostalCode()
	{
		return postalCode;
	}
	
	/**
	 * Method to set the Client postalCode
	 * @param postalCode - Client postalCode
	 */
	public void setPostalCode(String postalCode) throws InvalidPostalCodeException
	{
		if(validatePostalCode(postalCode) == false)
			throw new InvalidPostalCodeException("Postal Code must follow format: X0X 0X0");
		else
			this.postalCode = postalCode;
	}
	
	/**
	 * Method to validate if postalCode entered is correct format
	 * @param postalCode - postalCode to be validated
	 * @return if postalCode valid or not
	 */
	public boolean validatePostalCode(String postalCode)
	{
		String postalRegex = "[A-Z][0-9][A-Z]\\s[0-9][A-Z][0-9]";
		
		if(postalCode.matches(postalRegex) == false && postalCode != null)
			return false;
		return true;
	}
	
	/**
	 * Method to return the Client phoneNumber
	 * @return the phoneNumber
	 */
	public String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	/**
	 * Method to set the Client phoneNumber
	 * @param phoneNumber - Client phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) throws InvalidPhoneNumberException
	{
		if(validatePhoneNumber(phoneNumber) == false)
			throw new InvalidPhoneNumberException("Phone Number must follow format: 111-222-3333");
		else
			this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Method to validate if phoneNumber entered is correct format
	 * @param phoneNumber - phoneNumber to be validated
	 * @return if phoneNumber valid or not
	 */
	public boolean validatePhoneNumber(String phoneNumber)
	{
		String phoneRegex = "(\\d{3}-\\d{3}-\\d{4})";
		pattern = Pattern.compile(phoneRegex);
		matcher = pattern.matcher(phoneNumber);
		if(matcher.matches())
			return true;
		return false;
	}
	/**	 
	 * Method to return the Client type 
	 * @return the clientType
	 */
	public char getClientType()
	{
		return clientType;
	}
	
	/**
	 * Method to set the Client type
	 * @param clientType - Client type
	 */
	public void setClientType(char clientType)
	{
		this.clientType = clientType;
	}
	
	/**
	 * Method to check if Client is active
	 * @return if active
	 */
	public boolean isActive()
	{
		return active;
	}
	
	/**
	 * Method to set the Client active value
	 * @param active - Client active value
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}
	
	@Override
	public boolean equals(Object o)
	{
		Client c = (Client) o;
		if (this.id == c.id)
			return true;
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return id + " " + firstName + " " + lastName + " " + postalCode + " " + phoneNumber + " " + clientType;
	}
	
	
}
