package saitMLS.persistance;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;
import saitMLS.persistance.Broker;
import saitMLS.problemDomain.Client;
import utilities.List;
import fileHandling.*;

import java.io.*;
import java.util.*;



/**
 * ClientBroker.java
 *
 * @author Steven Ghantous
 * @version 1.0 
 * 
 * Class Description:  ClientBroker class that reads in a list of clients from a text file and builds into a bin file. 
 * Contains back end functions for front end of SAIT MLS. Takes in user input to add or save to client bin file. Also
 * allows user to search for clients.
 */
public class ClientBroker implements Broker
{
	//Constants
	private static final String BINARY_FILE = "res/clients.bin";
	private static final String TEXT_FILE = "res/clients.txt";
	private static final String MODE = "rws";
	
	//Attributes
	private static ClientBroker			broker;
	private RandomAccessFile			raf;
	private Long						highId;
	
	//Constructors
	/**
	 * Private constructor to adhere to a Singleton Pattern where only
	 * one instance reference to a ClientBroker class is ever generated.
	 * @throws FileNotFoundException if the binary file clients.bin is
	 * not found.
	 */
	private ClientBroker() throws FileNotFoundException
	{
		raf = new RandomAccessFile(BINARY_FILE,MODE);
	}
	
	
	//Operational Methods
	/**
	 * Static method that is the completion of the Singleton Pattern and
	 * generates the single reference to the ClientBroker object by checking
	 * to see if it already exists in memory. Also, checks to see if the
	 * binary file exists and if it doesn't load the text file of data into
	 * the binary file.
	 * @return the reference to the ClientBroker object.
	 */
	public static ClientBroker getClientBroker()
	{
		File file = new File(BINARY_FILE);
		
		try
		{
			if(file.exists())
			{
				if(broker == null)
				{
					broker = new ClientBroker();
				}
			}
			else
			{
				if(broker == null)
				{
					broker = new ClientBroker();
					broker.loadBinaryFile();
				}
				
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return broker;
	}
	
	/**
	 * Method to add a Client object to the end of a RandomAccessFile.
	 * @param c the Client object being added
	 * @return  true if the Client object is added and false if it isn't.
	 */
	@Override
	public boolean persist(Object o)
	{
		Client c = (Client) o;
		try
		{
			if(c.getId() == 0)
			{	
				findHighestCurrentId();
				raf.seek(raf.length());
				c.setId(highId + 1);
				writeRecord(c);
				return true;
			}
			else if(c.getId() > 0)
			{
				long offset = getClientOffset(c);
				raf.seek(offset);
				writeRecord(c);
				return true;
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return false;
	}
	
	/**
	 * Method to logically delete the supplied object from  the
	 * RandomAccessFile by changing the active attribute from
	 * true to false to indicate the record has been deleted.
	 * @param c the Client object to be removed.
	 * @return true if the object is deleted and false if it isn't
	 */
	@Override
	public boolean remove(Object o)
	{
		Client c = (Client) o ;
		boolean flag = false;
		try
		{
			long offset = getClientOffset(c);
			if(offset >= 0)
			{
				raf.seek(offset);
				raf.writeBoolean(false);
				flag = true;
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return flag;
		}
		return flag;
	}
	
	/**
	 * Method to open the text file and transfer the data to a binary file as
	 * a RandomAccessFile.
	 * @throws InvalidPhoneNumberException 
	 * @throws InvalidPostalCodeException 
	 */
	private void loadBinaryFile()
	{
		FileInput fin = new FileInput(TEXT_FILE);
		
		String line = fin.readLine();
		int i = 1;
		
		while(line != null)
		{
			try
			{
				Client c = new Client(line);
				c.setId(i);
				writeRecord(c);
				i++;
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InvalidPhoneNumberException e)
			{
				e.printStackTrace();
			}
			catch (InvalidPostalCodeException e)
			{
				e.printStackTrace();
			}
			line = fin.readLine();
		}
		System.out.println("created binary file");
		fin.closeInputFile();
	}
	
	/**
	 * Method to close the random access file and null the reference to the
	 * Singleton Pattern object ClientBroker.
	 */
	@Override
	public void closeBroker()
	{
		try
		{
			raf.close();
			broker = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to search Client database and return list of results
	 * @return searchResults - list of search results.
	 */
	public List search(Object o)
	{
		ArrayList searchResults = new ArrayList();
		Client c = (Client) o;
		if (c.getId() > 0)
		{
			searchResults = searchId(c);
		}
		else if (c.getFirstName() != null)
		{
			searchResults = searchFirstName(c);
		}
		else if (c.getLastName() != null)
		{
			searchResults = searchLastName(c);
		}
		else if (c.getPostalCode() != null)
		{
			searchResults = searchPostalCode(c);
		}
		else if (c.getPhoneNumber() != null)
		{
			searchResults = searchPhoneNumber(c);
		}
		else if (c.getClientType() != 0)
		{
			searchResults = searchClientType(c);
		}
		return (List)searchResults;
	}
	
	/**
	 * Method to return search results with id as search condition.
	 * @param c - client object that has id being searched for.
	 * @return results - an array list of search results matching id.
	 */
	private ArrayList searchId(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(getClientOffset(c));
			Client client = readRecord();
			if(client.getId() == c.getId() && c.isActive())
				results.add(client);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
			
	}

	/**
	 * Method to return search results with client type as search condition.
	 * @param c - client object that has client type being searched for.
	 * @return results - an array list of search results matching client type.
	 */
	private ArrayList searchClientType(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(0L);
			for(int i = 0; i < raf.length(); i += Client.SIZE)
			{
				Client client = readRecord();
				if(client.isActive() == true && client.getClientType() == c.getClientType())
					results.add(client);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Method to return search results with phone number as search condition.
	 * @param c - client object that has phone number being searched for.
	 * @return results - an array list of search results matching phone number.
	 */
	private ArrayList searchPhoneNumber(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(0L);
			for(int i = 0; i < raf.length(); i += Client.SIZE)
			{
				Client client = readRecord();
				if(client.isActive() == true && client.getPhoneNumber().compareToIgnoreCase(c.getPhoneNumber()) == 0)
					results.add(client);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Method to return search results with postal code as search condition.
	 * @param c - client object that has postal code being searched for.
	 * @return results - an array list of search results matching postal code.
	 */
	private ArrayList searchPostalCode(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(0L);
			for(int i = 0; i < raf.length(); i += Client.SIZE)
			{
				Client client = readRecord();
				if(client.isActive() == true && client.getPostalCode().compareToIgnoreCase(c.getPostalCode()) == 0)
					results.add(client);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Method to return search results with last name as search condition.
	 * @param c - client object that has last name being searched for.
	 * @return results - an array list of search results matching last name.
	 */
	private ArrayList searchLastName(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(0L);
			for(int i = 0; i < raf.length(); i += Client.SIZE)
			{
				Client client = readRecord();
				if(client.isActive() == true && client.getLastName().compareToIgnoreCase(c.getLastName()) == 0)
					results.add(client);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

	/**
	 * Method to return search results with first name as search condition.
	 * @param c - client object that has first name being searched for.
	 * @return results - an array list of search results matching first name.
	 */
	private ArrayList searchFirstName(Client c)
	{
		ArrayList results = new ArrayList();
		try
		{
			raf.seek(0L);
			for(int i = 0; i < raf.length(); i += Client.SIZE)
			{
				Client client = readRecord();
				if(client.isActive() == true && client.getFirstName().compareToIgnoreCase(c.getFirstName()) == 0)
					results.add(client);
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}


	/**
	 * Method to find highest current id and set to high level attribute highId.
	 */
	private void findHighestCurrentId()
	{
		
		try
		{
			raf.seek(raf.length() - Client.SIZE);
			raf.readBoolean();
			highId = raf.readLong();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**************************PRIVATE METHODS******************************/
	/**
	 * Method to read a Client object from the RandomAccessFile.
	 * @return the Client record as an object read the file.
	 * @throws IOException when access to the file is lost.
	 * @throws InvalidPostalCodeException 
	 * @throws InvalidPhoneNumberException 
	 */
	private Client readRecord() throws IOException
	{
		Client c = new Client();
		try
		{
			c.setActive(raf.readBoolean());
			c.setId(raf.readLong());
			c.setFirstName(raf.readUTF().trim());
			c.setLastName(raf.readUTF().trim());
			c.setAddress(raf.readUTF().trim());
			c.setPostalCode(raf.readUTF().trim());
			c.setPhoneNumber(raf.readUTF().trim());
			c.setClientType(raf.readChar());
		}
		catch (InvalidPhoneNumberException e)
		{
			e.printStackTrace();
		}
		catch (InvalidPostalCodeException e)
		{
			e.printStackTrace();
		}
		return c;
	}
	
	/**
	 * Method to write a Client object to the RandomAccessFile
	 * at the current position of the FilePointer.
	 * @throws IOException when access to the file is lost.
	 */
	private void writeRecord(Client c) throws IOException
	{
		raf.writeBoolean(c.isActive());
		raf.writeLong(c.getId());
		raf.writeUTF(String.format("%23s", c.getFirstName()));
		raf.writeUTF(String.format("%23s", c.getLastName()));
		raf.writeUTF(String.format("%50s", c.getAddress()));
		raf.writeUTF(String.format("%7s", c.getPostalCode()));
		raf.writeUTF(String.format("%12s", c.getPhoneNumber()));
		raf.writeChar(c.getClientType());
		
		
	}
	
	/**
	 * Method to locate a Client record in the RandomAccessFile given the
	 * id of the Client item to find.
	 * @param client Client record to locate
	 * @return the byte offset from the beginning of the RandomAccessFile
	 * or -1 if no match is found. 
	 * @throws InvalidPhoneNumberException 
	 * @throws InvalidPostalCodeException 
	 */
	private long getClientOffset(Client client)
	{
		try
		{
			raf.seek(0L);
			
			for(long i = 0L; i < raf.length(); i += Client.SIZE)
			{
				Client c = readRecord();
				if(c.equals(client) && c.isActive())
				{
					return i;
				}
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1L;
		}
		return -1L;
	}


	
}
