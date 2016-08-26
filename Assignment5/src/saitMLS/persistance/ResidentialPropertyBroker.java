package saitMLS.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.problemDomain.ResidentialProperty;
import utilities.List;

public class ResidentialPropertyBroker implements Broker
{
	  private static final String 					SERIALIZED_FILE = "res/resprop.ser";
	  private static final String 					TEXT_FILE = "res/resprop.txt";
	  private static ResidentialPropertyBroker 		propertyBroker;
	  private ArrayList<ResidentialProperty> 		propertyList;
	  private long 									nextId;
	
	/**
	 * Method to create a residential property broker if one not already created.
	 * @return propertyBroker - Residential Property Broker
	 */
	  public static ResidentialPropertyBroker getBroker()
	  {
		File file = new File("res/resprop.ser");
	    if (file.exists())
	    {
	    	if (propertyBroker == null)
	    	{
	    		propertyBroker = new ResidentialPropertyBroker();
	    		propertyBroker.loadSerializedFile();
	    		propertyBroker.findHighestCurrentId();
	    	}
	    }
	    else if (propertyBroker == null)
	    {
	    	propertyBroker = new ResidentialPropertyBroker();
	    	propertyBroker.loadResidentialPropertyLinkedList();
	    	propertyBroker.findHighestCurrentId();
	    }
	    return propertyBroker;
	  }

	  public boolean persist(Object o)
	  {
		  ResidentialProperty resProp = (ResidentialProperty)o;
		  boolean result = false;
		  if (resProp.getId() == 0L)
		  {
			  resProp.setId(++this.nextId);
			  result = this.propertyList.add(resProp);
		  }
		  else
		  {
			  int index = this.propertyList.indexOf(resProp);
			  ResidentialProperty old = (ResidentialProperty)this.propertyList.set(index, resProp);
			  if (old != null) 
			  {
				  result = true;
			  }
		  }
		  return result;
	  }
	  
	  public boolean remove(Object o)
	  {
		  ResidentialProperty resProp = (ResidentialProperty)o;
	    
		  int index = this.propertyList.indexOf(resProp);
		  ResidentialProperty old = (ResidentialProperty)this.propertyList.remove(index);
		  if (old == null) 
		  {
			  return false;
		  }
		  return true;
	  }
	  
	  public List search(Object o)
	  {
		  ResidentialProperty resProp = (ResidentialProperty)o;
		  ArrayList searchResults = new ArrayList();
		  if (resProp.getId() != 0L) 
		  {
			  return searchId(searchResults, resProp.getId());
		  }
		  if (resProp.getQuadrant() != null) 
		  {
			  return searchQuadrant(searchResults, resProp.getQuadrant());
		  }
		  if (resProp.getZone() != null) 
		  {
			  return searchZone(searchResults, resProp.getZone());
		  }
		  if (resProp.getAskingPrice() != 0.0D) 
		  {
			  return searchAskingPrice(searchResults, resProp.getAskingPrice());
		  }
		  if (resProp.getBedrooms() != 0) 
		  {
			  return searchBedrooms(searchResults, resProp.getBedrooms());
		  }
		  if (resProp.getGarage() != 0) 
		  {
			  return searchGarageType(searchResults, resProp.getGarage());
		  }
		  return (List) searchResults;
	  }
	  
	  public void closeBroker()
	  {
		  saveSerializedFile();
		  propertyBroker = null;
	  }

/*********************************************INNER METHODS*********************************************/
	  /**
	   * Method to load a text file of Residential Properties into an array list.
	   */
	  private void loadResidentialPropertyLinkedList()
	  {
		  this.propertyList = new ArrayList(320);
		  try
		  {
			  FileReader input = new FileReader(TEXT_FILE);
			  BufferedReader finput = new BufferedReader(input);
	      
			  String line = finput.readLine();
			  while (line != null)
			  {
				  StringTokenizer st = new StringTokenizer(line, ";");
				  this.nextId += 1L;
				  ResidentialProperty resProp = new ResidentialProperty(this.nextId, st.nextToken(), st.nextToken(), st.nextToken(), 
						  						st.nextToken(), Double.parseDouble(st.nextToken()), st.nextToken(), Double.parseDouble(st.nextToken()), 
						  						Double.parseDouble(st.nextToken()), Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
	        
				  this.propertyList.add(resProp);
				  line = finput.readLine();
			  }
			  finput.close();
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
		  catch (InvalidNumberOfBathroomsException e)
		  {
			  e.printStackTrace();
		  }
	  }
	  
	  /**
	   * Method to find the highest current ID assigned to a Residential Property.
	   */
	  private void findHighestCurrentId()
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getId() > this.nextId) 
			  {
				  this.nextId = resProp.getId();
			  }
		  }
	  }
	  
	  /**
	   * Method to load Serialized File of Residential Properties into an array list.
	   */
	  private void loadSerializedFile()
	  {
		  try
		  {
			  ObjectInputStream input = new ObjectInputStream(new FileInputStream(SERIALIZED_FILE));
			  this.propertyList = ((ArrayList)input.readObject());
			  input.close();
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
	  
	  /**
	   * Method to save Residential Property List to a Serialized File.
	   */
	  private void saveSerializedFile()
	  {
		  try
		  {
			  ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(SERIALIZED_FILE));
			  output.writeObject(this.propertyList);
			  output.close();
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
	   * Method to search Residential Property List with ID as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param id - ID of Residential Property being searched for
	   * @return list - updated search results list
	   */
	  private List searchId(ArrayList list, long id)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getId() == id) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
	  
	  /**
	   * Method to search Residential Property List with City Quadrant as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param quadrant - City Quadrant being searched for
	   * @return list - updated search results list
	   */
	  private List searchQuadrant(ArrayList list, String quadrant)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getQuadrant().compareToIgnoreCase(quadrant) == 0) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
	  
	  /**
	   * Method to search Residential Property List with Zone as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param zone - Zone being searched for
	   * @return list - updated search results list
	   */
	  private List searchZone(ArrayList list, String zone)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getZone().compareToIgnoreCase(zone) == 0) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
	  
	  /**
	   * Method to search Residential Property List with Asking Price as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param price - Asking Price being searched for
	   * @return list - updated search results list
	   */
	  private List searchAskingPrice(ArrayList list, double price)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getAskingPrice() >= price) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
	  
	  /**
	   * Method to search Residential Property List with Number of Bedrooms as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param bedrooms - Number of Bedrooms being searched for.
	   * @return list - updated search results list
	   */
	  private List searchBedrooms(ArrayList list, int bedrooms)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getBedrooms() == bedrooms) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
	  
	  /**
	   * Method to search Residential Property List with Garage Type as search parameter.
	   * @param list - list of previous search results being added onto
	   * @param garage - Garage type being searched for
	   * @return list - updated search results list
	   */
	  private List searchGarageType(ArrayList list, char garage)
	  {
		  for (int i = 0; i < this.propertyList.size(); i++)
		  {
			  ResidentialProperty resProp = (ResidentialProperty)this.propertyList.get(i);
			  if (resProp.getGarage() == garage) 
			  {
				  list.add(resProp);
			  }
		  }
		  return (List) list;
	  }
}

