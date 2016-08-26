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

import saitMLS.exceptions.InvalidLegalDescriptionException;
import saitMLS.exceptions.InvalidNumberOfBathroomsException;
import saitMLS.persistance.ClientBroker;
import saitMLS.persistance.CommercialPropertyBroker;
import saitMLS.problemDomain.CommercialProperty;
import saitMLS.problemDomain.ResidentialProperty;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * 
 * @author Steven Ghantous
 * @version 1.0
 * 
 *Class Description: Creates a CommercialPanel window and displays for the user. Connects front end of SAIT MLS to back end of SAIT MLS.
 */
public class CommercialPanel extends JPanel
{
	private CommercialPropertyBroker broker = CommercialPropertyBroker.getBroker();
	private JRadioButton		radPropertyId, radCityQuadrant, radZoningType, radPricePoint, radBuildingType, radFloors;
	private ButtonGroup			grpSearch;
	private JList				lstResults;
	private MyActionListener	actionListener;
	private DefaultListModel	listModel;
	private JButton				btnClearSearch, btnSearch, btnSave, btnDelete, btnClear;
	private JLabel lblCommercialPropertyId;
	private JTextField txtCommercialId, txtLegalDescription, txtAddress, txtAskingPrice, txtFloors, txtComments;
	private JLabel lblPropertyLegalDescription;
	private JLabel lblPropertyAddress;
	private JLabel lblCityQuadrant;
	private JComboBox cmbQuadrant, cmbType, cmbZoning;
	private JLabel lblZoningProperty;

	/**
	 * Creates the commercial panel window for user interaction. Includes text fields, radio buttons, combo boxes, lists, actionlistener, buttons
	 * @return Commercial panel to main panel.
	 */
	public CommercialPanel()
	{
		listModel = new DefaultListModel();
		actionListener = new MyActionListener();
		grpSearch = new ButtonGroup();
		setLayout(null);
		
		JLabel lblCommercial = new JLabel("Commercial Property Management Screen");
		lblCommercial.setBounds(0, 0, 790, 65);
		lblCommercial.setForeground(Color.BLUE);
		lblCommercial.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommercial.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 28));
		lblCommercial.setBorder(BorderFactory.createBevelBorder(0));
		add(lblCommercial);
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBounds(2, 67, 395, 132);
		pnlSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlSearch);
		pnlSearch.setLayout(null);
		
		JLabel lblSelectTypeOf = new JLabel("Select type of search to be performed");
		lblSelectTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectTypeOf.setBounds(12, 31, 217, 16);
		pnlSearch.add(lblSelectTypeOf);
		
		JLabel lblSearchCommercial = new JLabel("Search Commercial Property");
		lblSearchCommercial.setBounds(85, 13, 207, 16);
		lblSearchCommercial.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlSearch.add(lblSearchCommercial);
		
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
		
		radBuildingType = new JRadioButton("Building Type");
		radBuildingType.setBounds(184, 76, 154, 25);
		radBuildingType.addActionListener(actionListener);
		pnlSearch.add(radBuildingType);
		grpSearch.add(radBuildingType);
		
		radFloors = new JRadioButton("Number of Floors");
		radFloors.setBounds(184, 101, 154, 25);
		radFloors.addActionListener(actionListener);
		pnlSearch.add(radFloors);
		grpSearch.add(radFloors);
		
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
		
		JLabel lblCommercialInformation = new JLabel("Commercial Property Information");
		lblCommercialInformation.setBounds(87, 13, 280, 16);
		lblCommercialInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlInfo.add(lblCommercialInformation);
		
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
		
		lblCommercialPropertyId = new JLabel("Commercial Property Id:");
		lblCommercialPropertyId.setBounds(54, 54, 147, 16);
		pnlInfo.add(lblCommercialPropertyId);
		
		txtCommercialId = new JTextField();
		txtCommercialId.setBounds(202, 51, 116, 22);
		txtCommercialId.setEditable(false);
		pnlInfo.add(txtCommercialId);
		txtCommercialId.setColumns(10);
		
		lblPropertyLegalDescription = new JLabel("Property Legal Description:");
		lblPropertyLegalDescription.setBounds(40, 89, 157, 16);
		pnlInfo.add(lblPropertyLegalDescription);
		
		txtLegalDescription = new JTextField();
		txtLegalDescription.setBounds(202, 89, 132, 22);
		pnlInfo.add(txtLegalDescription);
		txtLegalDescription.setColumns(10);
		
		lblPropertyAddress = new JLabel("Property Address:");
		lblPropertyAddress.setBounds(92, 124, 116, 16);
		pnlInfo.add(lblPropertyAddress);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(202, 124, 165, 22);
		pnlInfo.add(txtAddress);
		txtAddress.setColumns(10);
		
		lblCityQuadrant = new JLabel("City Quadrant:");
		lblCityQuadrant.setBounds(112, 159, 95, 16);
		pnlInfo.add(lblCityQuadrant);
		
		cmbQuadrant = new JComboBox();
		cmbQuadrant.setModel(new DefaultComboBoxModel(new String[] {"NW", "NE", "SW", "SE"}));
		cmbQuadrant.setBounds(202, 159, 47, 22);
		pnlInfo.add(cmbQuadrant);
		
		lblZoningProperty = new JLabel("Zoning Property:");
		lblZoningProperty.setBounds(100, 194, 103, 16);
		pnlInfo.add(lblZoningProperty);
		
		cmbZoning = new JComboBox();
		cmbZoning.setModel(new DefaultComboBoxModel(new String[] {"I1", "I2", "I3", "I4"}));
		cmbZoning.setBounds(202, 194, 47, 22);
		pnlInfo.add(cmbZoning);
		
		JLabel lblPropertyAskingPrice = new JLabel("Property Asking Price:");
		lblPropertyAskingPrice.setBounds(69, 229, 137, 16);
		pnlInfo.add(lblPropertyAskingPrice);
		
		txtAskingPrice = new JTextField();
		txtAskingPrice.setBounds(202, 229, 116, 22);
		pnlInfo.add(txtAskingPrice);
		txtAskingPrice.setColumns(10);
		
		JLabel lblPropertyType = new JLabel("Property Type:");
		lblPropertyType.setBounds(110, 264, 95, 16);
		pnlInfo.add(lblPropertyType);
		
		cmbType = new JComboBox();
		cmbType.setEnabled(false);
		cmbType.setModel(new DefaultComboBoxModel(new String[] {"O", "M"}));
		cmbType.setBounds(202, 264, 47, 22);
		pnlInfo.add(cmbType);
		
		JLabel lblNoOfFloors = new JLabel("No. of Floors:");
		lblNoOfFloors.setBounds(117, 299, 85, 16);
		pnlInfo.add(lblNoOfFloors);
		
		txtFloors = new JTextField();
		txtFloors.setBounds(202, 299, 116, 22);
		pnlInfo.add(txtFloors);
		txtFloors.setColumns(10);
		
		JLabel lblCommentsAboutProperty = new JLabel("Comments about Property:");
		lblCommentsAboutProperty.setBounds(41, 334, 165, 16);
		pnlInfo.add(lblCommentsAboutProperty);
		
		txtComments = new JTextField();
		txtComments.setBounds(202, 334, 165, 22);
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
			CommercialProperty commercial = new CommercialProperty();
			/***************************************SET BACKGROUND COLOR FOR SEARCH FIELD***********************************
			 * Changes the background color of fields to highlight what is being searched.
			 */
			if(e.getSource() == radPropertyId || e.getSource() == radPricePoint || e.getSource() == radCityQuadrant || e.getSource() == radBuildingType || e.getSource() == radZoningType || e.getSource() == radFloors)
			{
				
				if(radPropertyId.isSelected())
				{
					txtCommercialId.setBackground(Color.YELLOW);
					txtCommercialId.setEditable(true);
					txtCommercialId.requestFocus();
				}
				else
				{
					txtCommercialId.setBackground(Color.WHITE);
					txtCommercialId.setEditable(false);
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
				
				if(radBuildingType.isSelected())
				{
					cmbType.setBackground(Color.YELLOW);
					cmbType.requestFocus();
				}
				else
				{
					cmbType.setBackground(Color.WHITE);
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
				
				if(radFloors.isSelected())
				{
					txtFloors.setBackground(Color.YELLOW);
					txtFloors.requestFocus();
				}
				else
				{
					txtFloors.setBackground(Color.WHITE);
				}
			}
			
			/***************************************SEARCH THROUGH PROPERTY LIST***************************************\
			 * Searches the commercial property list using user parameters. Adds results to array list. Displays array list in JList.
			 */
			else if(e.getSource() == btnSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				if(radPropertyId.isSelected() && txtCommercialId.getText() != null)
				{
					commercial.setId(Long.parseLong(txtCommercialId.getText()));;
					searchResult = (ArrayList)broker.search(commercial);
					listModel.addElement(searchResult.get(0));
				}
				else if(radPricePoint.isSelected() && txtAskingPrice.getText() != null)
				{
					commercial.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					searchResult = (ArrayList)broker.search(commercial);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radCityQuadrant.isSelected())
				{
					commercial.setQuadrant((String)cmbQuadrant.getSelectedItem());
					searchResult = (ArrayList)broker.search(commercial);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radBuildingType.isSelected())
				{
					commercial.setType((String)cmbType.getSelectedItem());
					searchResult = (ArrayList)broker.search(commercial);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radZoningType.isSelected())
				{
					commercial.setZone((String)cmbZoning.getSelectedItem());
					searchResult = (ArrayList)broker.search(commercial);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radFloors.isSelected() && txtFloors.getText() != null)
				{
					commercial.setNoFloors(Integer.parseInt(txtFloors.getText()));
					searchResult = (ArrayList)broker.search(commercial);
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
					if(txtCommercialId.getText() != null)
					{
						commercial.setId(Long.parseLong(txtCommercialId.getText()));
					}
					else
					{
						commercial.setId(0);
					}
					commercial.setLegalDescription(txtLegalDescription.getText());
					commercial.setAddress(txtAddress.getText());
					commercial.setQuadrant((String)cmbQuadrant.getSelectedItem());
					commercial.setZone((String)cmbZoning.getSelectedItem());
					commercial.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					commercial.setType((String)cmbType.getSelectedItem());
					commercial.setNoFloors(Integer.parseInt(txtFloors.getText()));
					commercial.setComments(txtComments.getText());
					broker.persist(commercial);
				}
				catch (InvalidLegalDescriptionException event)
				{
					event.printStackTrace();
				}

			}
			
			/***************************DELETE PROPERTY***********************************************
			 * Deletes displayed property from the list of properties.
			 */
			else if(e.getSource() == btnDelete)
			{
				try
				{
					if(txtCommercialId.getText() != null)
					{
						commercial.setId(Long.parseLong(txtCommercialId.getText()));
					}
					else
					{
						commercial.setId(0);
					}
					commercial.setLegalDescription(txtLegalDescription.getText());
					commercial.setAddress(txtAddress.getText());
					commercial.setQuadrant((String)cmbQuadrant.getSelectedItem());
					commercial.setZone((String)cmbZoning.getSelectedItem());
					commercial.setAskingPrice(Double.parseDouble(txtAskingPrice.getText()));
					commercial.setType((String)cmbType.getSelectedItem());
					commercial.setNoFloors(Integer.parseInt(txtFloors.getText()));
					commercial.setComments(txtComments.getText());
					broker.remove(commercial);
				}
				catch (InvalidLegalDescriptionException event)
				{
					event.printStackTrace();
				}
			}
			
			/********************************CLEAR FIELDS****************************************
			 * Clears property fields.
			 */
			else if(e.getSource() == btnClear)
			{
				txtCommercialId.setText("");
				txtLegalDescription.setText("");
				txtAddress.setText("");
				cmbQuadrant.setSelectedIndex(0);
				cmbZoning.setSelectedIndex(0);
				txtAskingPrice.setText("");
				cmbType.setSelectedIndex(0);
				txtFloors.setText("");
				txtComments.setText("");
				
				grpSearch.clearSelection();
				
				txtCommercialId.setBackground(Color.WHITE);
				txtCommercialId.setEditable(false);
				txtAskingPrice.setBackground(Color.WHITE);
				cmbQuadrant.setBackground(Color.WHITE);
				cmbType.setBackground(Color.WHITE);
				cmbZoning.setBackground(Color.WHITE);
				txtFloors.setBackground(Color.WHITE);
				
			}
			
			/************************************CLEAR RESULTS********************************
			 * Removes all elements from JList model.
			 */
			else if(e.getSource() == btnClearSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				grpSearch.clearSelection();
				
				txtCommercialId.setBackground(Color.WHITE);
				txtCommercialId.setEditable(false);
				txtAskingPrice.setBackground(Color.WHITE);
				cmbQuadrant.setBackground(Color.WHITE);
				cmbType.setBackground(Color.WHITE);
				cmbZoning.setBackground(Color.WHITE);
				txtFloors.setBackground(Color.WHITE);
			}
		}
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if(lstResults.getSelectedIndex() >= 0)
			{
				CommercialProperty c = (CommercialProperty)searchResult.get(lstResults.getSelectedIndex());
				
				txtCommercialId.setEditable(false);
				txtCommercialId.setText(c.getId() + "");
				txtLegalDescription.setText(c.getLegalDescription());
				txtAddress.setText(c.getAddress());
				cmbQuadrant.setSelectedItem(c.getQuadrant());
				cmbZoning.setSelectedItem(c.getZone());
				txtAskingPrice.setText(c.getAskingPrice() + "");
				cmbType.setSelectedItem(c.getType());
				txtFloors.setText(c.getNoFloors() + "");
				txtComments.setText(c.getComments());
			}
			
		}
	}
}


