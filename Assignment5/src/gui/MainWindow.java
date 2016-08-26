/**
 * Created on Jan 27, 2009
 *
 * Project: assignment2BaseGUI
 */
package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Steven Ghantous
 * @version 1.0
 * 
 * Class Description: Constructs window for SAIT MLS.
 */
public class MainWindow 
{
	//Attributes
	private JFrame				frame;
	private JButton				btnMain;
	private JButton				btnClient;
	private JButton				btnResidential;
	private JButton				btnCommercial;
	private Vector<JPanel>		panelList;
	private MyActionListener	actionListener;
	
	//Constructor
	public MainWindow()
	{
		actionListener = new MyActionListener();
		panelList = new Vector<JPanel>(4);
		
		//fill panel list with JPanels associated with each button
		panelList.add(createMainPanel());
		
		panelList.add(new ClientPanel());
		
		panelList.add(new ResidentialPanel());
		
		panelList.add(new CommercialPanel());
		createWindow();
	}
	
	private void createWindow()
	{
		frame = new JFrame("SAIT MLS");
		frame.setBounds(100,100,800,600);
		frame.setResizable(false);
				
		frame.setLayout(new BorderLayout(5,5));
		
		//Panel for the buttons located in the north section
		JPanel pnlNorth = new JPanel(new GridLayout(1,4));
		Border buttonEdge = BorderFactory.createRaisedBevelBorder();
		
		//button for main window
		btnMain = new JButton("Main");
		btnMain.setBorder(buttonEdge);
		btnMain.addActionListener(actionListener);
		pnlNorth.add(btnMain);
		
		//button for client window
		btnClient = new JButton("Client");
		btnClient.setBorder(buttonEdge);
		btnClient.addActionListener(actionListener);
		pnlNorth.add(btnClient);
		
		//button for residential window
		btnResidential = new JButton("Residential");
		btnResidential.setBorder(buttonEdge);
		btnResidential.addActionListener(actionListener);
		pnlNorth.add(btnResidential);
		
		//button for commercial window
		btnCommercial = new JButton("Commercial");
		btnCommercial.setBorder(buttonEdge);
		btnCommercial.addActionListener(actionListener);
		pnlNorth.add(btnCommercial);
		
		frame.add(pnlNorth,BorderLayout.NORTH);
		frame.add(panelList.get(0));
		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener (new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				//ClientBroker
				System.exit(0);
		  }	
		});
		
		frame.setVisible(true);
	}
	
	private JPanel createMainPanel()
	{
		
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);
		
		JLabel lblMain = new JLabel("The Main Screen",SwingConstants.CENTER);
		lblMain.setForeground(Color.BLUE);
		lblMain.setFont(new Font("Copperplate Gothic Bold",Font.BOLD,40));
		lblMain.setBounds(0, 0, 790, 65);
		lblMain.setBorder(BorderFactory.createBevelBorder(0));
		pnlMain.add(lblMain);
		
		
		return pnlMain;
	}
	
	/*****************************INNER CLASSES*****************************/
	
	private class MyActionListener implements ActionListener
	{
		//Attributes
		private JPanel 			pnlCurrent;
		@Override
		public void actionPerformed(ActionEvent e)
		{
			for(int i = 0; i < panelList.size(); i++)
			{
				pnlCurrent = panelList.get(i);
				frame.remove(pnlCurrent);
				pnlCurrent.setVisible(false);
			}
			
			if(e.getSource() == btnMain)
			{
				pnlCurrent = panelList.get(0);
				frame.add(pnlCurrent,BorderLayout.CENTER);
				pnlCurrent.setVisible(true);
				
			}
			
			else if(e.getSource() == btnClient)
			{
				pnlCurrent = panelList.get(1);
				frame.add(pnlCurrent,BorderLayout.CENTER);
				pnlCurrent.setVisible(true);
				
			}
			else if(e.getSource() == btnResidential)
			{
				pnlCurrent = panelList.get(2);
				frame.add(pnlCurrent,BorderLayout.CENTER);
				pnlCurrent.setVisible(true);
			}
			else if(e.getSource() == btnCommercial)
			{
				pnlCurrent = panelList.get(3);
				frame.add(pnlCurrent,BorderLayout.CENTER);
				pnlCurrent.setVisible(true);
			}
		}
		
	}
}
