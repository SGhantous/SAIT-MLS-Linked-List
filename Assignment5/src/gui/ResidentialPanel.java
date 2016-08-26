/**
 * Author: Steven Ghantous 339250
 * March 2, 2015
 * Version 1.0
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;
import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.persistance.ClientBroker;
import saitMLS.persistance.ResidentialPropertyBroker;
import saitMLS.problemDomain.Client;
import saitMLS.problemDomain.ResidentialProperty;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * 
 * @author Steven Ghantous
 * @version 1.0
 * 
 *Class Description: Creates a ResidentialPanel window and displays for the user. Connects front end of SAIT MLS to back end of SAIT MLS.
 */
public class ResidentialPanel extends JPanel
{
	private ResidentialPropertyBroker broker = ResidentialPropertyBroker.getBroker();
	private JRadioButton		radPropertyId, radCityQuadrant, radZoningType, radPricePoint, radBedrooms, radGarageType;
	private ButtonGroup			grpSearch;
	private JList				lstResults;
	private MyActionListener	actionListener;
	private DefaultListModel	listModel;
	private JButton				btnClearSearch, btnSearch, btnSave, btnDelete, btnClear;
	private JTextField 			txtResidentialId, txtLegalDescription, txtAddress, txtAskingPrice, txtSquareFootage, txtBathrooms, txtBedrooms, txtComments;
	private JLabel 				lblCityQuadrant;
	private JComboBox 			cmbGarage, cmbQuadrant, cmbZoning;
	private JLabel 				lblCommentsAboutProperty;
	/**
	 * Creates the residential panel window for user interaction. Includes text fields, radio buttons, combo boxes, lists, actionlistener, buttons
	 * @return Residential panel to main panel.
	 */
	public ResidentialPanel()
	{
		listModel = new DefaultListModel();
		actionListener = new MyActionListener();
		grpSearch = new ButtonGroup();
		setLayout(null);
		
		JLabel lblResidential = new JLabel("Residential Property Management Screen");
		lblResidential.setBounds(0, 0, 790, 65);
		lblResidential.setForeground(Color.BLUE);
		lblResidential.setHorizontalAlignment(SwingConstants.CENTER);
		lblResidential.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
		lblResidential.setBorder(BorderFactory.createBevelBorder(0));
		add(lblResidential);
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBounds(2, 67, 395, 132);
		pnlSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlSearch);
		pnlSearch.setLayout(null);
		
		JLabel lblSelectTypeOf = new JLabel("Select type of search to be performed");
		lblSelectTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectTypeOf.setBounds(12, 31, 217, 16);
		pnlSearch.add(lblSelectTypeOf);
		
		JLabel lblSearchResidential = new JLabel("Search Residential Property");
		lblSearchResidential.setBounds(85, 13, 207, 16);
		lblSearchResidential.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlSearch.add(lblSearchResidential);
		
		radPropertyId = new JRadioButton("Property Id");
		radPropertyId.setBounds(22, 50, 154, 25);
		radPropertyId.addActionListener(actionListener);
		pnlSearch.add(radPropertyId);
		grpSearch.add(radPropertyId);
		
		radCityQuadrant = new JRadioButton("City Quadrant");
		radCityQuadrant.setBounds(22, 76, 154, 25);
		radCityQuadrant.addActionListener(actionListener);
		pnlSearch.add(radCityQuadrant);
		grpSearch.add(radCityQuadrant);
		
		radZoningType = new JRadioButton("Zoning Type");
		radZoningType.setBounds(22, 101, 154, 25);
		radZoningType.addActionListener(actionListener);
		pnlSearch.add(radZoningType);
		grpSearch.add(radZoningType);
		
		radPricePoint = new JRadioButton("Price Point");
		radPricePoint.setBounds(184, 50, 154, 25);
		radPricePoint.addActionListener(actionListener);
		pnlSearch.add(radPricePoint);
		grpSearch.add(radPricePoint);
		
		radBedrooms = new JRadioButton("Number of Bedrooms");
		radBedrooms.setBounds(184, 76, 154, 25);
		radBedrooms.addActionListener(actionListener);
		pnlSearch.add(radBedrooms);
		grpSearch.add(radBedrooms);
		
		radGarageType = new JRadioButton("Garage Type");
		radGarageType.setBounds(184, 101, 154, 25);
		radGarageType.addActionListener(actionListener);
		pnlSearch.add(radGarageType);
		grpSearch.add(radGarageType);
		
		btnClearSearch = new JButton("Clear Search");
		btnClearSearch.setBounds(137, 505, 125, 25);
		btnClearSearch.addActionListener(actionListener);
		add(btnClearSearch);
		
		JPanel pnlResults = new JPanel();
		pnlResults.setBounds(2, 201, 395, 293);
		pnlResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlResults);
		pnlResults.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 395, 293);
		pnlResults.add(scrollPane);
		
		lstResults = new JList(listModel);
		lstResults.setBounds(0, 0, 1, 1);
		lstResults.setToolTipText("");
		lstResults.addListSelectionListener(actionListener);
		scrollPane.setViewportView(lstResults);
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(398, 67, 392, 463);
		pnlInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlInfo);
		pnlInfo.setLayout(null);
		
		JLabel lblResidentialInformation = new JLabel("Residential Property Information");
		lblResidentialInformation.setBounds(87, 13, 280, 16);
		lblResidentialInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlInfo.add(lblResidentialInformation);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(12, 409, 80, 25);
		btnSearch.addActionListener(actionListener);
		pnlInfo.add(btnSearch);
		
		btnSave = new JButton("Save");
		btnSave.setBounds(104, 409, 80, 25);
		btnSave.addActionListener(actionListener);
		pnlInfo.add(btnSave);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(196, 409, 80, 25);
		btnDelete.addActionListener(actionListener);
		pnlInfo.add(btnDelete);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(288, 409, 80, 25);
		btnClear.addActionListener(actionListener);
		pnlInfo.add(btnClear);
		
		JLabel lblResidentialPropertyId = new JLabel("Residential Property Id:");
		lblResidentialPropertyId.setBounds(53, 57, 146, 16);
		pnlInfo.add(lblResidentialPropertyId);
		
		txtResidentialId = new JTextField();
		txtResidentialId.setBounds(196, 57, 82, 22);
		txtResidentialId.setEditable(false);
		pnlInfo.add(txtResidentialId);
		txtResidentialId.setColumns(10);
		
		JLabel lblPropertyLegalDescription = new JLabel("Property Legal Description:");
		lblPropertyLegalDescription.setBounds(33, 87, 159, 16);
		pnlInfo.add(lblPropertyLegalDescription);
		
		txtLegalDescription = new JTextField();
		txtLegalDescription.setBounds(196, 87, 116, 22);
		pnlInfo.add(txtLegalDescription);
		txtLegalDescription.setColumns(10);
		
		JLabel lblPropertyAddress = new JLabel("Property Address:");
		lblPropertyAddress.setBounds(85, 117, 110, 16);
		pnlInfo.add(lblPropertyAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(196, 117, 171, 22);
		pnlInfo.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblCityQuadrant = new JLabel("City Quadrant:");
		lblCityQuadrant.setBounds(105, 147, 95, 16);
		pnlInfo.add(lblCityQuadrant);
		
		cmbQuadrant = new JComboBox();
		cmbQuadrant.setModel(new DefaultComboBoxModel(new String[] {"NW", "NE", "SE", "SW"}));
		cmbQuadrant.setBounds(196, 147, 47, 22);
		pnlInfo.add(cmbQuadrant);
		
		JLabel lblZoningOfProperty = new JLabel("Zoning of Property:");
		lblZoningOfProperty.setBounds(78, 177, 116, 16);
		pnlInfo.add(lblZoningOfProperty);
		
		cmbZoning = new JComboBox();
		cmbZoning.setModel(new DefaultComboBoxModel(new String[] {"R1", "R2", "R3", "R4"}));
		cmbZoning.setBounds(196, 177, 47, 22);
		pnlInfo.add(cmbZoning);
		
		JLabel lblPropertyAskingPrice = new JLabel("Property Asking Price:");
		lblPropertyAskingPrice.setBounds(62, 207, 132, 16);
		pnlInfo.add(lblPropertyAskingPrice);
		
		txtAskingPrice = new JTextField();
		txtAskingPrice.setBounds(196, 207, 116, 22);
		pnlInfo.add(txtAskingPrice);
		txtAskingPrice.setColumns(10);
		
		JLabel lblBuildingSquareFootage = new JLabel("Building Square Footage:");
		lblBuildingSquareFootage.setBounds(44, 237, 146, 16);
		pnlInfo.add(lblBuildingSquareFootage);
		
		txtSquareFootage = new JTextField();
		txtSquareFootage.setBounds(196, 237, 116, 22);
		pnlInfo.add(txtSquareFootage);
		txtSquareFootage.setColumns(10);
		
		JLabel lblNoOfBathrooms = new JLabel("No. of Bathrooms:");
		lblNoOfBathrooms.setBounds(83, 267, 108, 16);
		pnlInfo.add(lblNoOfBathrooms);
		
		txtBathrooms = new JTextField();
		txtBathrooms.setBounds(196, 267, 58, 22);
		pnlInfo.add(txtBathrooms);
		txtBathrooms.setColumns(10);
		
		JLabel lblNoOfBedrooms = new JLabel("No. of Bedrooms:");
		lblNoOfBedrooms.setBounds(87, 297, 103, 16);
		pnlInfo.add(lblNoOfBedrooms);
		
		txtBedrooms = new JTextField();
		txtBedrooms.setBounds(196, 297, 58, 22);
		pnlInfo.add(txtBedrooms);
		txtBedrooms.setColumns(10);
		
		JLabel lblGarageType = new JLabel("Garage Type:");
		lblGarageType.setBounds(108, 327, 83, 16);
		pnlInfo.add(lblGarageType);
		
		cmbGarage = new JComboBox();
		cmbGarage.setEnabled(false);
		cmbGarage.setModel(new DefaultComboBoxModel(new String[] {"N", "D", "A"}));
		cmbGarage.setBounds(196, 327, 35, 22);
		pnlInfo.add(cmbGarage);
		
		lblCommentsAboutProperty = new JLabel("Comments about Property:");
		lblCommentsAboutProperty.setBounds(33, 357, 162, 16);
		pnlInfo.add(lblCommentsAboutProperty);
		
		txtComments = new JTextField();
		txtComments.setBounds(196, 357, 171, 22);
		pnlInfo.add(txtComments);
		txtComments.setColumns(10);
	}
	/**********************************************INNER CLASSES**************************************************/
	private class MyActionListener implements ActionListener, ListSelectionListener
	{
		//Attributes
		private JPanel 			pnlCurrent;
		private ArrayList	searchResult;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ResidentialProperty residential = new ResidentialProperty();
			/***************************************SET BACKGROUND COLOR FOR SEARCH FIELD***********************************
			 * Changes the background color of fields to highlight what is being searched.
			 */
			if(e.getSource() == radPropertyId || e.getSource() == radPricePoint || e.getSource() == radCityQuadrant || e.getSource() == radBedrooms || e.getSource() == radZoningType || e.getSource() == radGarageType)
			{
				
				if(radPropertyId.isSelected())
				{
					txtResidentialId.setBackground(Color.YELLOW);
					txtResidentialId.setEditable(true);
					txtResidentialId.requestFocus();
				}
				else
				{
					txtResidentialId.setBackground(Color.WHITE);
					txtResidentialId.setEditable(false);
				}
				
				if(radPricePoint.isSelected())
				{
					txtAskingPrice.setBackground(Color.YELLOW);
					txtAskingPrice.requestFocus();
				}
				else
				{
					txtAskingPrice.setBackground(Color.WHITE);
				}
				
				if(radCityQuadrant.isSelected())
				{
					cmbQuadrant.setBackground(Color.YELLOW);
					cmbQuadrant.requestFocus();
				}
				else
				{
					cmbQuadrant.setBackground(Color.WHITE);
				}
				
				if(radBedrooms.isSelected())
				{
					txtBedrooms.setBackground(Color.YELLOW);
					txtBedrooms.requestFocus();
				}
				else
				{
					txtBedrooms.setBackground(Color.WHITE);
				}
				
				if(radZoningType.isSelected())
				{
					cmbZoning.setBackground(Color.YELLOW);
					cmbZoning.requestFocus();
				}
				else
				{
					cmbZoning.setBackground(Color.WHITE);
				}
				
				if(radGarageType.isSelected())
				{
					cmbGarage.setBackground(Color.YELLOW);
					cmbGarage.requestFocus();
				}
				else
				{
					cmbGarage.setBackground(Color.WHITE);
				}
			}
			
			/***************************************SEARCH THROUGH PROPERTY LIST***************************************\
			 * Searches the residential property list using user parameters. Adds results to array list. Displays array list in JList.
			 */
			else if(e.getSource() == btnSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				if(radPropertyId.isSelected() && txtResidentialId.getText() != null)
				{
					residential.setId(Long.parseLong(txtResidentialId.getText()));;
					searchResult = (ArrayList)broker.search(residential);
					listModel.addElement(searchResult.get(0));
				}
				else if(radPricePoint.isSelected() && txtAskingPrice.getText() != null)
				{
					residential.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					searchResult = (ArrayList)broker.search(residential);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radCityQuadrant.isSelected())
				{
					residential.setQuadrant((String)cmbQuadrant.getSelectedItem());
					searchResult = (ArrayList)broker.search(residential);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radBedrooms.isSelected() && txtBedrooms.getText() != null)
				{
					residential.setBedrooms(Integer.parseInt(txtBedrooms.getText()));
					searchResult = (ArrayList)broker.search(residential);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radZoningType.isSelected())
				{
					residential.setZone((String)cmbZoning.getSelectedItem());
					searchResult = (ArrayList)broker.search(residential);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radGarageType.isSelected())
				{
					residential.setGarage(((String)cmbGarage.getSelectedItem()).charAt(0));
					searchResult = (ArrayList)broker.search(residential);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
			}
			
			/******************************SAVE PROPERTY***************************************
			 * Saves user entered property or user updated property.
			 */
			else if(e.getSource() == btnSave)
			{
				try
				{
					if(txtResidentialId.getText() != null)
					{
						residential.setId(Long.parseLong(txtResidentialId.getText()));
					}
					else
					{
						residential.setId(0);
					}
					residential.setLegalDescription(txtLegalDescription.getText());
					residential.setAddress(txtAddress.getText());
					residential.setQuadrant((String)cmbQuadrant.getSelectedItem());
					residential.setZone((String)cmbZoning.getSelectedItem());
					residential.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					residential.setArea(Double.parseDouble(txtSquareFootage.getText()));
					residential.setBathrooms(Double.parseDouble(txtBathrooms.getText()));
					residential.setBedrooms(Integer.parseInt(txtBedrooms.getText()));
					residential.setGarage(((String)cmbGarage.getSelectedItem()).charAt(0));
					residential.setComments(txtComments.getText());
					broker.persist(residential);
	
				}
				catch (InvalidLegalDescriptionException event)
				{
					event.printStackTrace();
				}
				catch (NumberFormatException event)
				{
					event.printStackTrace();
				}
				catch (InvalidNumberOfBathroomsException event)
				{
					event.printStackTrace();
				} catch (Exception e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			/***************************DELETE PROPERTY***********************************************
			 * Deletes displayed property from the list of properties.
			 */
			else if(e.getSource() == btnDelete)
			{
				try
				{
					if(txtResidentialId.getText() != null)
					{
						residential.setId(Long.parseLong(txtResidentialId.getText()));
					}
					else
					{
						residential.setId(0);
					}
					residential.setLegalDescription(txtLegalDescription.getText());
					residential.setAddress(txtAddress.getText());
					residential.setQuadrant((String)cmbQuadrant.getSelectedItem());
					residential.setZone((String)cmbZoning.getSelectedItem());
					residential.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					residential.setArea(Double.parseDouble(txtSquareFootage.getText()));
					residential.setBathrooms(Double.parseDouble(txtBathrooms.getText()));
					residential.setBedrooms(Integer.parseInt(txtBedrooms.getText()));
					residential.setGarage(((String)cmbGarage.getSelectedItem()).charAt(0));
					residential.setComments(txtComments.getText());
					broker.remove(residential);
				}
				catch (InvalidLegalDescriptionException event)
				{
					event.printStackTrace();
				}
				catch (NumberFormatException event)
				{
					event.printStackTrace();
				}
				catch (InvalidNumberOfBathroomsException event)
				{
					event.printStackTrace();
				}
			}
			
			/********************************CLEAR FIELDS****************************************
			 * Clears property fields.
			 */
			else if(e.getSource() == btnClear)
			{
				txtResidentialId.setText("");
				txtLegalDescription.setText("");
				txtAddress.setText("");
				cmbQuadrant.setSelectedIndex(0);
				cmbZoning.setSelectedIndex(0);
				txtAskingPrice.setText("");
				txtSquareFootage.setText("");
				txtBathrooms.setText("");
				txtBedrooms.setText("");
				cmbGarage.setSelectedIndex(0);
				txtComments.setText("");
				
				grpSearch.clearSelection();
				
				txtResidentialId.setBackground(Color.WHITE);
				txtResidentialId.setEditable(false);
				txtAskingPrice.setBackground(Color.WHITE);
				cmbQuadrant.setBackground(Color.WHITE);
				txtBedrooms.setBackground(Color.WHITE);
				cmbZoning.setBackground(Color.WHITE);
				cmbGarage.setBackground(Color.WHITE);
				
			}
			
			/************************************CLEAR RESULTS********************************
			 * Removes all elements from JList model.
			 */
			else if(e.getSource() == btnClearSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				grpSearch.clearSelection();
				
				txtResidentialId.setBackground(Color.WHITE);
				txtResidentialId.setEditable(false);
				txtAskingPrice.setBackground(Color.WHITE);
				cmbQuadrant.setBackground(Color.WHITE);
				txtBedrooms.setBackground(Color.WHITE);
				cmbZoning.setBackground(Color.WHITE);
				cmbGarage.setBackground(Color.WHITE);
			}
		}
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if(lstResults.getSelectedIndex() >= 0)
			{
				ResidentialProperty r = (ResidentialProperty)searchResult.get(lstResults.getSelectedIndex());
				
				txtResidentialId.setEditable(false);
				txtResidentialId.setText(r.getId() + "");
				txtLegalDescription.setText(r.getLegalDescription());
				txtAddress.setText(r.getAddress());
				cmbQuadrant.setSelectedItem(r.getQuadrant());
				cmbZoning.setSelectedItem(r.getZone());
				txtAskingPrice.setText(r.getAskingPrice() + "");
				txtSquareFootage.setText(r.getArea() + "");
				txtBathrooms.setText(r.getBathrooms() + "");
				txtBedrooms.setText(r.getBedrooms() + "");
				cmbGarage.setSelectedItem(r.getGarage());
				txtComments.setText(r.getComments());
			}
			
		}
	}
}