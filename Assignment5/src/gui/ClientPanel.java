/**
 * Author: Steven Ghantous 339250
 * March 2, 2015
 * Version 1.0
 */
package gui;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.BevelBorder;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import saitMLS.exceptions.InvalidPhoneNumberException;
import saitMLS.exceptions.InvalidPostalCodeException;
import saitMLS.persistance.ClientBroker;
import saitMLS.problemDomain.Client;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
/**
 * 
 * @author Steven Ghantous
 * @version 1.0
 * 
 *Class Description: Creates a ClientPanel window and displays for the user. Connects front end of SAIT MLS to back end of SAIT MLS.
 */
public class ClientPanel extends JPanel
{
	ClientBroker broker = ClientBroker.getClientBroker();
	private ButtonGroup			grpSearch;
	private JRadioButton		radClientId, radFirstName, radLastName, radPostal, radPhone, radClientType;
	private JTextField 			txtClientId, txtFirstName, txtLastName, txtAddress, txtPostal, txtPhone;
	private JComboBox			cmbClientType;
	private JButton				btnClearSearch, btnSearch, btnSave, btnDelete, btnClear;
	private MyActionListener	actionListener;
	private DefaultListModel	listModel;
	private JList				lstResults;
	
	/**
	 * Creates the client panel window for user interaction. Includes text fields, radio buttons, combo boxes, lists, actionlistener, buttons
	 * @return Client panel to main panel.
	 */
	public ClientPanel()
	{
		listModel = new DefaultListModel();
		actionListener = new MyActionListener();
		grpSearch = new ButtonGroup();
		setLayout(null);
		
		JLabel lblClient = new JLabel("Client Management Screen");
		lblClient.setBounds(0, 0, 790, 65);
		lblClient.setForeground(Color.BLUE);
		lblClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblClient.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,40));
		lblClient.setBorder(BorderFactory.createBevelBorder(0));
		add(lblClient);
		
		JPanel pnlSearch = new JPanel();
		pnlSearch.setBounds(2, 67, 395, 132);
		pnlSearch.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlSearch);
		pnlSearch.setLayout(null);
		
		JLabel lblSelectTypeOf = new JLabel("Select type of search to be performed");
		lblSelectTypeOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSelectTypeOf.setBounds(12, 31, 217, 16);
		pnlSearch.add(lblSelectTypeOf);
		
		JLabel lblSearchClients = new JLabel("Search Clients");
		lblSearchClients.setBounds(141, 13, 92, 16);
		lblSearchClients.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlSearch.add(lblSearchClients);
		
		radClientId = new JRadioButton("Client Id");
		radClientId.setBounds(22, 50, 154, 25);
		radClientId.addActionListener(actionListener);
		pnlSearch.add(radClientId);
		grpSearch.add(radClientId);
		
		radFirstName = new JRadioButton("First Name");
		radFirstName.setBounds(22, 76, 154, 25);
		radFirstName.addActionListener(actionListener);
		pnlSearch.add(radFirstName);
		grpSearch.add(radFirstName);
		
		radLastName = new JRadioButton("Last Name");
		radLastName.setBounds(22, 101, 154, 25);
		radLastName.addActionListener(actionListener);
		pnlSearch.add(radLastName);
		grpSearch.add(radLastName);
		
		radPostal = new JRadioButton("Postal Code");
		radPostal.setBounds(184, 50, 154, 25);
		radPostal.addActionListener(actionListener);
		pnlSearch.add(radPostal);
		grpSearch.add(radPostal);
		
		radPhone = new JRadioButton("Phone Number");
		radPhone.setBounds(184, 76, 154, 25);
		radPhone.addActionListener(actionListener);
		pnlSearch.add(radPhone);
		grpSearch.add(radPhone);
		
		radClientType = new JRadioButton("Client Type");
		radClientType.setBounds(184, 101, 154, 25);
		radClientType.addActionListener(actionListener);
		pnlSearch.add(radClientType);
		grpSearch.add(radClientType);
		
		
		JPanel pnlInfo = new JPanel();
		pnlInfo.setBounds(398, 67, 392, 463);
		pnlInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlInfo);
		pnlInfo.setLayout(null);
		
		JLabel lblClientInformation = new JLabel("Client Information");
		lblClientInformation.setBounds(141, 13, 116, 16);
		lblClientInformation.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlInfo.add(lblClientInformation);
		
		JLabel lblClientId = new JLabel("Client Id:");
		lblClientId.setBounds(110, 93, 56, 16);
		pnlInfo.add(lblClientId);
		
		txtClientId = new JTextField();
		txtClientId.setBounds(178, 90, 116, 22);
		txtClientId.setEditable(false);
		pnlInfo.add(txtClientId);
		txtClientId.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(95, 129, 67, 16);
		pnlInfo.add(lblFirstName);
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(178, 126, 177, 22);
		pnlInfo.add(txtFirstName);
		txtFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(97, 165, 71, 16);
		pnlInfo.add(lblLastName);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(178, 162, 177, 22);
		pnlInfo.add(txtLastName);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(111, 201, 56, 16);
		pnlInfo.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(178, 198, 177, 22);
		pnlInfo.add(txtAddress);
		
		JLabel lblPostal = new JLabel("Postal Code:");
		lblPostal.setBounds(90, 237, 81, 16);
		pnlInfo.add(lblPostal);
		
		txtPostal = new JTextField();
		txtPostal.setColumns(10);
		txtPostal.setBounds(178, 234, 116, 22);
		pnlInfo.add(txtPostal);
		
		JLabel lblPhone = new JLabel("Phone Number:");
		lblPhone.setBounds(73, 273, 104, 16);
		pnlInfo.add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(178, 270, 116, 22);
		pnlInfo.add(txtPhone);
		
		JLabel lblClientType = new JLabel("Client Type:");
		lblClientType.setBounds(93, 309, 93, 16);
		pnlInfo.add(lblClientType);
		
		cmbClientType = new JComboBox();
		cmbClientType.setBounds(178, 306, 56, 22);
		cmbClientType.setModel(new DefaultComboBoxModel(new String[] {"R", "C"}));
		pnlInfo.add(cmbClientType);
		
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
		
		JPanel pnlResults = new JPanel();
		pnlResults.setBounds(2, 201, 395, 293);
		pnlResults.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(pnlResults);
		pnlResults.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 395, 293);
		pnlResults.add(scrollPane);
		
		lstResults = new JList(listModel);
		lstResults.addListSelectionListener(actionListener);
		lstResults.setBounds(0, 0, 1, 1);
		
		
		lstResults.setToolTipText("");
		scrollPane.setViewportView(lstResults);
		
		btnClearSearch = new JButton("Clear Search");
		btnClearSearch.setBounds(137, 505, 125, 25);
		btnClearSearch.addActionListener(actionListener);
		add(btnClearSearch);

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
			Client client = new Client();
			/***************************************SET BACKGROUND COLOR FOR SEARCH FIELD***********************************
			 * Changes the background color of fields to highlight what is being searched.
			 */
			if(e.getSource() == radClientId || e.getSource() == radFirstName || e.getSource() == radLastName || e.getSource() == radPostal || e.getSource() == radPhone || e.getSource() == radClientType)
			{
				
				if(radClientId.isSelected())
				{
					txtClientId.setBackground(Color.YELLOW);
					txtClientId.setEditable(true);
					txtClientId.requestFocus();
				}
				else
				{
					txtClientId.setBackground(Color.WHITE);
					txtClientId.setEditable(false);
				}
				
				if(radFirstName.isSelected())
				{
					txtFirstName.setBackground(Color.YELLOW);
					txtFirstName.requestFocus();
				}
				else
				{
					txtFirstName.setBackground(Color.WHITE);
				}
				
				if(radLastName.isSelected())
				{
					txtLastName.setBackground(Color.YELLOW);
					txtLastName.requestFocus();
				}
				else
				{
					txtLastName.setBackground(Color.WHITE);
				}
				
				if(radPhone.isSelected())
				{
					txtPhone.setBackground(Color.YELLOW);
					txtPhone.requestFocus();
				}
				else
				{
					txtPhone.setBackground(Color.WHITE);
				}
				
				if(radPostal.isSelected())
				{
					txtPostal.setBackground(Color.YELLOW);
					txtPostal.requestFocus();
				}
				else
				{
					txtPostal.setBackground(Color.WHITE);
				}
				
				if(radClientType.isSelected())
				{
					cmbClientType.setBackground(Color.YELLOW);
					cmbClientType.requestFocus();
				}
				else
				{
					cmbClientType.setBackground(Color.WHITE);
				}
			}
			
			/***************************************SEARCH THROUGH CLIENT LIST***************************************\
			 * Searches the client list using user parameters. Adds results to array list. Displays array list in JList.
			 */
			else if(e.getSource() == btnSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				if(radClientId.isSelected() && txtClientId.getText() != null)
				{
					client.setId(Long.parseLong(txtClientId.getText()));
					searchResult = (ArrayList)broker.search(client);
					listModel.addElement(searchResult.get(0));
				}
				else if(radFirstName.isSelected() && txtFirstName.getText() != null)
				{
					client.setFirstName(txtFirstName.getText());
					searchResult = (ArrayList)broker.search(client);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radLastName.isSelected() && txtLastName.getText() != null)
				{
					client.setLastName(txtLastName.getText());
					searchResult = (ArrayList)broker.search(client);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radPhone.isSelected() && txtPhone.getText() != null)
				{
					try
					{
						client.setPhoneNumber(txtPhone.getText());
					}
					catch (InvalidPhoneNumberException event)
					{
						JOptionPane.showMessageDialog(null, "Phone number must follow format: 111-111-1111");
					}
					searchResult = (ArrayList)broker.search(client);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radPostal.isSelected() && txtPostal.getText() != null)
				{
					try
					{
						client.setPostalCode(txtPostal.getText());
					}
					catch (InvalidPostalCodeException event)
					{
						JOptionPane.showMessageDialog(null, "Postal Code must follow format: X0X 0X0");
					}
					searchResult = (ArrayList)broker.search(client);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
				else if(radClientType.isSelected())
				{
					client.setClientType(((String) cmbClientType.getSelectedItem()).charAt(0));
					searchResult = (ArrayList)broker.search(client);
					for(int i = 0; i < searchResult.size(); i++)
					{
						listModel.addElement(searchResult.get(i));
					}
				}
			}
			
			/******************************SAVE CLIENT***************************************
			 * Saves user entered client or user updated client.
			 */
			else if(e.getSource() == btnSave)
			{
				try
				{
					if(txtClientId.getText() != null)
					{
						client.setId(Long.parseLong(txtClientId.getText()));
					}
					else
					{
						client.setId(0);
					}
					client.setFirstName(txtFirstName.getText());
					client.setLastName(txtLastName.getText());
					client.setAddress(txtAddress.getText());
					client.setPostalCode(txtPostal.getText());
					client.setPhoneNumber(txtPhone.getText());
					client.setClientType(((String)cmbClientType.getSelectedItem()).charAt(0));
					broker.persist(client);
				}
				catch (InvalidPostalCodeException event)
				{
					JOptionPane.showMessageDialog(null, "Postal Code must follow format: X0X 0X0");
				}
				catch (InvalidPhoneNumberException event)
				{
					JOptionPane.showMessageDialog(null, "Phone number must follow format: 111-111-1111");
				}
			}
			
			/***************************DELETE CLIENT***********************************************
			 * Deletes displayed client from the list of clients.
			 */
			else if(e.getSource() == btnDelete)
			{
				try
				{
					client.setId(Long.parseLong(txtClientId.getText()));
					client.setFirstName(txtFirstName.getText());
					client.setLastName(txtLastName.getText());
					client.setAddress(txtAddress.getText());
					client.setPostalCode(txtPostal.getText());
					client.setPhoneNumber(txtPhone.getText());
					client.setClientType(((String) cmbClientType.getSelectedItem()).charAt(0));
					broker.remove(client);
				}
				catch (InvalidPostalCodeException event)
				{
					JOptionPane.showMessageDialog(null, "Postal Code must follow format: X0X 0X0");
				}
				catch (InvalidPhoneNumberException event)
				{
					JOptionPane.showMessageDialog(null, "Phone number must follow format: 111-111-1111");
				}
			}
			
			/********************************CLEAR FIELDS****************************************
			 * Clears client fields.
			 */
			else if(e.getSource() == btnClear)
			{
				txtClientId.setText("");
				txtFirstName.setText("");
				txtLastName.setText("");
				txtAddress.setText("");
				txtPostal.setText("");
				txtPhone.setText("");
				cmbClientType.setSelectedIndex(0);
				
				grpSearch.clearSelection();
				
				txtClientId.setBackground(Color.WHITE);
				txtClientId.setEditable(false);
				txtFirstName.setBackground(Color.WHITE);
				txtLastName.setBackground(Color.WHITE);
				txtPhone.setBackground(Color.WHITE);
				txtPostal.setBackground(Color.WHITE);
				cmbClientType.setBackground(Color.WHITE);
			}
			
			/************************************CLEAR RESULTS********************************
			 * Removes all elements from JList model.
			 */
			else if(e.getSource() == btnClearSearch)
			{
				listModel.removeAllElements();
				lstResults.clearSelection();
				
				grpSearch.clearSelection();
				
				txtClientId.setBackground(Color.WHITE);
				txtClientId.setEditable(false);
				txtFirstName.setBackground(Color.WHITE);
				txtLastName.setBackground(Color.WHITE);
				txtPhone.setBackground(Color.WHITE);
				txtPostal.setBackground(Color.WHITE);
				cmbClientType.setBackground(Color.WHITE);
			}
		}
		@Override
		public void valueChanged(ListSelectionEvent e)
		{
			if(lstResults.getSelectedIndex() >= 0)
			{
				Client c = (Client)searchResult.get(lstResults.getSelectedIndex());
				
				txtClientId.setEditable(false);
				txtClientId.setText(c.getId() + "");
				txtFirstName.setText(c.getFirstName());
				txtLastName.setText(c.getLastName());
				txtAddress.setText(c.getAddress());
				txtPostal.setText(c.getPostalCode());
				txtPhone.setText(c.getPhoneNumber());
				cmbClientType.setSelectedItem(c.getClientType());
			}
			
		}
	}
}


