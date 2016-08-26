package saitMLS.exceptions;
/**
 * 
 * @author Steven Ghantous
 * @version 1.0
 * 
 *Class Description: Constructor class for InvalidPostalCodeException. Displays a message when incorrect postal code format used.
 */
public class InvalidPostalCodeException extends Exception
{
	public InvalidPostalCodeException(String message)
	{
		super(message);
	}
}
