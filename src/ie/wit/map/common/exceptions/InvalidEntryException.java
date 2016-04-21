package ie.wit.map.common.exceptions;

/**
 * Created by Joe on 12/03/2016.
 */
public class InvalidEntryException extends Exception
{
	public InvalidEntryException()
	{
		super("This entry is invalid");
	}

	public InvalidEntryException(String message)
	{
		super(message);
	}
}
