package saitMLS.persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.problemDomain.CommercialProperty;
import utilities.List;

public class CommercialPropertyBroker implements Broker
{
	private static final String 					SERIALIZED_FILE = "res/comprop.ser";
	private static final String 					INPUT_FILE = 		"res/comprop.txt";
	private static CommercialPropertyBroker			propertyBroker;
	private ArrayList<CommercialProperty> 			propertyList;
	private long 									nextId;
	

	/**
	 * Method to create a commercial property broker if one not already created.
	 * @return propertyBroker - Commercial Property Broker
	 */
	public static CommercialPropertyBroker getBroker()
	{
		File comPropFile = new File (SERIALIZED_FILE);
		if(comPropFile.exists())
		{
			if(propertyBroker == null)
			{
				propertyBroker = new CommercialPropertyBroker();
				propertyBroker.loadSerializedFile();
				propertyBroker.findHighId();
			}
		}
		else if (propertyBroker == null)
		{
			propertyBroker = new CommercialPropertyBroker();
			propertyBroker.loadCommercialPropertyLinkedList();
			propertyBroker.findHighId();
		}
		
		return propertyBroker;
	}
	


	@Override
	public List search(Object paramObject)
	{
	    CommercialProperty search = (CommercialProperty)paramObject;
	    ArrayList searchResults = new ArrayList();
	    if (search.getId() != 0L) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getId() == search.getId()) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    if (search.getQuadrant() != null) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getQuadrant().compareToIgnoreCase(search.getQuadrant()) == 0) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    if (search.getZone() != null) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getZone().compareToIgnoreCase(search.getZone()) == 0) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    if (search.getAskingPrice() > 0.0D) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getAskingPrice() >= search.getAskingPrice()) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    if (search.getType() != null) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getType().compareToIgnoreCase(search.getType()) == 0) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    if (search.getNoFloors() > 0) 
	    {
	    	for (int i = 0; i < this.propertyList.size(); i++)
	    	{
	    		CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
	    		if (comProp.getNoFloors() == search.getNoFloors()) 
	    		{
	    			searchResults.add(comProp);
	    		}
	    	}
	    }
	    return (List) searchResults;
	}

	@Override
	public boolean persist(Object paramObject)
	{
	    CommercialProperty comProp = (CommercialProperty)paramObject;
	    boolean result = false;
	    if (comProp.getId() == 0L)
	    {
	    	long id = ++ this.nextId;
	    	comProp.setId(id);
	    	result = this.propertyList.add(comProp);
	    }
	    else
	    {
	    	int i = this.propertyList.indexOf(comProp);
	    	CommercialProperty updated = (CommercialProperty)this.propertyList.set(i, comProp);
	    	if (updated != null) 
	    	{
	    		result = true;
	    	}
	    }
	    return result;
	}

	@Override
	public boolean remove(Object paramObject)
	{
	    CommercialProperty comProp = (CommercialProperty)paramObject;
	    
	    int i = this.propertyList.indexOf(comProp);
	    CommercialProperty removed = (CommercialProperty)this.propertyList.remove(i);
	    if (removed == null) 
	    {
	      return false;
	    }
	    return true;
	}

	@Override
	public void closeBroker()
	{
		saveSerializedFile();
	    propertyBroker = null;
	}
	/***********************************************INNER METHODS***********************************************************/
	/**
	 * Method to load Commercial Property Text file into Commercial Property Array List
	 */
	private void loadCommercialPropertyLinkedList()
	{
		this.propertyList = new ArrayList(210);
		try
		{
			FileReader inputFile = new FileReader(INPUT_FILE);
			BufferedReader fin = new BufferedReader(inputFile);
			
			String input = fin.readLine();
			while(input != null)
			{
				StringTokenizer st = new StringTokenizer(input, ";");
				this.nextId += 1L;
				CommercialProperty comProp = new CommercialProperty(this.nextId, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
																	Double.parseDouble(st.nextToken()), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
				this.propertyList.add(comProp);
				fin.readLine();
			}
			fin.close();
			saveSerializedFile();
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
	    catch (FileNotFoundException e)
	    {
	      e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    catch (InvalidLegalDescriptionException e)
	    {
	      e.printStackTrace();
	    }
	}
	
	/**
	 * Method to find the highest current Id assigned to a Commercial Property
	 */
	private void findHighId()
	{
		for (int i = 0; i < this.propertyList.size(); i++)
		{
			CommercialProperty comProp = (CommercialProperty)this.propertyList.get(i);
			if (comProp.getId() > this.nextId) 
			{
				this.nextId = comProp.getId();
			}
		}
	}
	
	/**
	 * Method to save Commercial Property List to a Serialized file.
	 */
	private void saveSerializedFile()
	{
		try
		{
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
			outStream.writeObject(this.propertyList);
			outStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to load Serialized file in Commercial Property Array List
	 */
	private void loadSerializedFile()
	{
		try
		{
			ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
			this.propertyList = ((ArrayList)inStream.readObject());
			inStream.close();
		}
	    catch (FileNotFoundException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (ClassNotFoundException e)
	    {
	    	e.printStackTrace();
	    }
	}
}
