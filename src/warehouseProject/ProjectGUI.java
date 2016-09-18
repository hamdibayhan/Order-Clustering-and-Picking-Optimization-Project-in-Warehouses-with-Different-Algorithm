/*        
 *        Developed by Hamdi Bayhan in 2016
 * --->   github.com/HamdiBayhan
 * --->	  linkedin.com/in/hamdi-bayhan-b3133248
 * 
 */
package warehouseProject;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import net.proteanit.sql.DbUtils;
import javax.swing.border.MatteBorder;



public class ProjectGUI {

	private JFrame frame;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField warehouseLengthText;
	private JTextField xAisleWidthText;
	private JTextField yAisleWidthText;
	private JTextField warehouseSubpartHeightText;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;
	private JTextField payloadDetectTxt;
	private JTextField textField;

	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					ProjectGUI window = new ProjectGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProjectGUI() throws ClassNotFoundException, SQLException {
		databaseClass.getConnection();
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 799, 504);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 783, 465);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(253, 245, 230));
		tabbedPane.addTab("Generate", null, panel_1, null);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(59, 146, 47, -59);
		panel_1.add(tabbedPane_1);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_2.setBounds(0, 0, 778, 437);
		panel_1.add(tabbedPane_2);
		
		JPanel panel = new JPanel();
		tabbedPane_2.addTab("Generate and Load Order File", null, panel, null);
		panel.setBackground(new Color(253, 245, 230));
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Generate and Load Order File");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(133, 11, 237, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Number of Order:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_2.setBounds(64, 68, 102, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Number of Seed:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_4.setBounds(64, 93, 102, 14);
		panel.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(245, 65, 149, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(245, 90, 149, 20);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnGenerateWarehouse = new JButton("Generate Order File");
		btnGenerateWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileClass fileObject = new fileClass();
				
				fileClass.numberOfOrder = Integer.parseInt(textField_2.getText());
				fileClass.maxNumberOfSeed = Integer.parseInt(textField_4.getText());
				
				try {
					fileObject.generateOrderList();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnGenerateWarehouse.setBounds(245, 128, 149, 23);
		panel.add(btnGenerateWarehouse);
		
		JButton btnNewButton = new JButton("Load Order File");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(245, 203, 149, 23);
		panel.add(btnNewButton);
		
		JLabel lblLoadOrderFile = new JLabel("Load Order File:");
		lblLoadOrderFile.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblLoadOrderFile.setBounds(64, 205, 102, 18);
		panel.add(lblLoadOrderFile);
		
		payloadDetectTxt = new JTextField();
		payloadDetectTxt.setBounds(245, 266, 149, 20);
		panel.add(payloadDetectTxt);
		payloadDetectTxt.setColumns(10);
		
		JButton detectPayloadBtn = new JButton("Detect Payload");
		detectPayloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ClusterClass.payload = Integer.parseInt(payloadDetectTxt.getText());
				
			}
		});
		detectPayloadBtn.setBounds(245, 313, 149, 23);
		panel.add(detectPayloadBtn);
		
		JLabel lblDetectPayload = new JLabel("Detect Payload:");
		lblDetectPayload.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblDetectPayload.setBounds(64, 269, 102, 14);
		panel.add(lblDetectPayload);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 0, 0));
		panel_2.setBackground(new Color(253, 245, 230));
		tabbedPane_2.addTab("Generate Warehouse", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Warehouse Length:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setBounds(168, 58, 137, 14);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("xAisle Width:");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_6.setForeground(new Color(0, 0, 0));
		lblNewLabel_6.setBounds(168, 86, 106, 14);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblYaisleWidth = new JLabel("yAisle Width:");
		lblYaisleWidth.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblYaisleWidth.setForeground(new Color(0, 0, 0));
		lblYaisleWidth.setBounds(168, 117, 95, 14);
		panel_2.add(lblYaisleWidth);
		
		warehouseLengthText = new JTextField();
		warehouseLengthText.setBounds(382, 52, 126, 20);
		panel_2.add(warehouseLengthText);
		warehouseLengthText.setColumns(10);
		
		xAisleWidthText = new JTextField();
		xAisleWidthText.setBounds(382, 83, 126, 20);
		panel_2.add(xAisleWidthText);
		xAisleWidthText.setColumns(10);
		
		yAisleWidthText = new JTextField();
		yAisleWidthText.setBounds(382, 114, 126, 20);
		panel_2.add(yAisleWidthText);
		yAisleWidthText.setColumns(10);
		
		JLabel lblWarehousesubpartheight = new JLabel("Warehouse Subpart Height:");
		lblWarehousesubpartheight.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblWarehousesubpartheight.setBounds(168, 152, 164, 14);
		panel_2.add(lblWarehousesubpartheight);
		
		warehouseSubpartHeightText = new JTextField();
		warehouseSubpartHeightText.setBounds(382, 149, 126, 20);
		panel_2.add(warehouseSubpartHeightText);
		warehouseSubpartHeightText.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Generate Warehouse");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				generateWarehouse.warehouseLength = Integer.parseInt(warehouseLengthText.getText());
				generateWarehouse.warehouseSubPartHeight = Integer.parseInt(warehouseSubpartHeightText.getText());
				generateWarehouse.xAisleWidth = Integer.parseInt(xAisleWidthText.getText());
				generateWarehouse.yAisleWidth = Integer.parseInt(yAisleWidthText.getText());
				
			}
		});
		
		btnNewButton_1.setBounds(358, 199, 164, 23);
		panel_2.add(btnNewButton_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fileChooser = new JFileChooser();
				
				fileChooser.setCurrentDirectory(new java.io.File("user.home"));
				fileChooser.setDialogTitle("Select a Order List Text File");
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				
				if (fileChooser.showOpenDialog(btnNewButton) == JFileChooser.APPROVE_OPTION) 
				{
					fileClass.file = fileChooser.getSelectedFile();
				}
			}
		});
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(253, 245, 230));
		tabbedPane.addTab("Routing", null, panel_4, null);
		panel_4.setLayout(null);
		
		JRadioButton ToptRadioButton = new JRadioButton("Two-Opt");
		buttonGroup.add(ToptRadioButton);
		ToptRadioButton.setBounds(27, 88, 109, 23);
		panel_4.add(ToptRadioButton);
		
		JRadioButton OoptRadioButton = new JRadioButton("Or-Opt");
		buttonGroup.add(OoptRadioButton);
		OoptRadioButton.setBounds(163, 88, 109, 23);
		panel_4.add(OoptRadioButton);
		
		JLabel lblNearestNeighbor = new JLabel("Nearest Neighbor:");
		lblNearestNeighbor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNearestNeighbor.setBounds(27, 48, 135, 14);
		panel_4.add(lblNearestNeighbor);
		
		JRadioButton SshapeRadioButton = new JRadioButton("S-Shape");
		buttonGroup.add(SshapeRadioButton);
		SshapeRadioButton.setBounds(27, 167, 109, 23);
		panel_4.add(SshapeRadioButton);
		
		JLabel lblSshape = new JLabel("S-Shape:");
		lblSshape.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSshape.setBounds(27, 130, 87, 14);
		panel_4.add(lblSshape);
		
		JLabel lblSelectRoutingWay = new JLabel("Select Routing Way");
		lblSelectRoutingWay.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblSelectRoutingWay.setBounds(262, 11, 135, 27);
		panel_4.add(lblSelectRoutingWay);
		
		JRadioButton rdbtnTwoopt = new JRadioButton("Two-Opt");
		buttonGroup.add(rdbtnTwoopt);
		rdbtnTwoopt.setBounds(27, 252, 109, 23);
		panel_4.add(rdbtnTwoopt);
		
		JRadioButton rdbtnOropt = new JRadioButton("Or-Opt");
		buttonGroup.add(rdbtnOropt);
		rdbtnOropt.setBounds(163, 252, 109, 23);
		panel_4.add(rdbtnOropt);
		
		JButton btnRoutingButton = new JButton("Routing Button");
		btnRoutingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calculateRoutingDistance CRDobject = new calculateRoutingDistance();
				
				
				if(ToptRadioButton.isSelected())
				{
					try {
						CRDobject.implementRouting("NNtwoOpt");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(OoptRadioButton.isSelected())
				{
					try {
						CRDobject.implementRouting("NNorOpt");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(SshapeRadioButton.isSelected())
				{
					try {
						CRDobject.implementRouting("SShape");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(rdbtnTwoopt.isSelected())
				{
					try {
						CRDobject.implementRouting("CWStwoOpt");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else if(rdbtnOropt.isSelected())
				{
					try {
						CRDobject.implementRouting("CWSorOpt");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRoutingButton.setBounds(338, 289, 135, 23);
		panel_4.add(btnRoutingButton);
		
		JLabel lblClarkeWrightSavings = new JLabel("Clarke Wright Savings:");
		lblClarkeWrightSavings.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClarkeWrightSavings.setBounds(27, 212, 171, 23);
		panel_4.add(lblClarkeWrightSavings);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(253, 245, 230));
		tabbedPane.addTab("Cluster with Tabu", null, panel_3, null);
		panel_3.setLayout(null);
		
		textField_3 = new JTextField();
		textField_3.setBounds(370, 93, 142, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTabuKriterleri = new JLabel("Tabu Search Criterion");
		lblTabuKriterleri.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTabuKriterleri.setBounds(273, 24, 191, 17);
		panel_3.add(lblTabuKriterleri);
		
		JButton btnCluster = new JButton("Cluster");
		btnCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				divideAndCluster divideAndClusterObject = new divideAndCluster();
				fileClass fileObject = new fileClass();
				
				Statement myStmt = null;
				try {
					myStmt = databaseClass.con.createStatement();
					DatabaseMetaData dbm = databaseClass.con.getMetaData();
					ResultSet tables = dbm.getTables(null, null, "clusterorder", null);
					if (!(tables.next())) {
					myStmt.executeUpdate("create table clusterorder(id integer,"
					          + "whichInterval integer," + "svsm varchar(50),"+ "ordernumber varchar(500),"  
					          + "seednumber varchar(500));");
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
				
				Map<Integer, List<Integer>> orderMap = new LinkedHashMap<Integer, List<Integer>>();
				Map<Integer, String> orderMap1 = new LinkedHashMap<Integer, String>();
				
				
				try {
					fileObject.readFileOrder(orderMap, orderMap1);
				} catch (FileNotFoundException e2) {
					
					e2.printStackTrace();
				}
				
				try {
					divideAndClusterObject.divideToOrdersWithClock(orderMap1, orderMap);
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnCluster.setBounds(370, 236, 142, 23);
		panel_3.add(btnCluster);
		
		JLabel lblNewLabel = new JLabel("Number of Iteration:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(137, 94, 154, 17);
		panel_3.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(370, 143, 142, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JLabel lblSizeOfMemory = new JLabel("Size of Memory:");
		lblSizeOfMemory.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSizeOfMemory.setBounds(137, 145, 121, 14);
		panel_3.add(lblSizeOfMemory);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(253, 245, 230));
		panel_5.setForeground(new Color(253, 245, 230));
		tabbedPane.addTab("Cluster Table", null, panel_5, null);
		panel_5.setLayout(null);
		
		JScrollPane clustertable = new JScrollPane();
		clustertable.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(173, 255, 47)));
		clustertable.setBounds(10, 11, 758, 375);
		panel_5.add(clustertable);
		
		table = new JTable();
		clustertable.setViewportView(table);
		
		
		JButton clusterorderbutton = new JButton("Cluster Table");
		clusterorderbutton.setBackground(UIManager.getColor("Button.background"));
		clusterorderbutton.setForeground(new Color(255, 0, 0));
		clusterorderbutton.setFont(new Font("Tahoma", Font.BOLD, 12));
		clusterorderbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					String query = "select whichInterval, svsm, ordernumber,seednumber from clusterorder";
					PreparedStatement pst = databaseClass.con.prepareStatement(query);
					
					ResultSet rs= pst.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}
				catch(Exception e){
					
				}
				
			}
		});
		clusterorderbutton.setBounds(311, 397, 156, 39);
		panel_5.add(clusterorderbutton);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(253, 245, 230));
		tabbedPane.addTab("Total Data Table", null, panel_6, null);
		panel_6.setLayout(null);
		
		JScrollPane totaltable = new JScrollPane();
		totaltable.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(173, 255, 47)));
		totaltable.setBounds(10, 11, 758, 366);
		panel_6.add(totaltable);
		
		table_1 = new JTable();
		totaltable.setViewportView(table_1);
		
		JButton btnTotal = new JButton("Total Data Table");
		btnTotal.setBackground(UIManager.getColor("Button.background"));
		btnTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTotal.setForeground(new Color(255, 0, 0));
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "select warehouse_length, xAisle_width, yAisle_width, subpart_height, which_routing, distance from totaldata";
					PreparedStatement pst = databaseClass.con.prepareStatement(query);
					
					ResultSet rs= pst.executeQuery();
					
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
					rs.close();
					pst.close();
				}
				catch(Exception e1){
					
					
				}
			}
		});
		btnTotal.setBounds(317, 388, 153, 38);
		panel_6.add(btnTotal);
		
	}
}
