package saitMLS.exceptions;
/**
 * 
 * @author Steven Ghantous
 * @version 1.0
 * 
 *Class Description: Constructor class for InvalidPhoneNumberException. Displays a message when incorrect phone number format used.
 */
public class InvalidPhoneNumberException extends Exception
{
	public InvalidPhoneNumberException(String message)
	{
		super(message);
	}
}
