import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class SqlProjectGUI extends JFrame {
	
	private int flag;
	
	private JPanel contentPane;
	private JTable fieldTable;
	private JTable projectTable;
	private JTable engineersTable;
	
	private JTextField FieldNoTF;
	private JTextField FieldNameTF;
	private JTextField FieldSpecializationTF;
	private JTextField ProjectDescriptionTF;
	private JTextField ProjectNameTF;
	private JTextField ProjectBegginingDateTF;
	private JTextField ProjectCostumerNameTF;
	private JTextField ProjectsNoTF;
	private JTextField EngineersIdTF;
	private JTextField EngineersDateOfBirthTF;
	private JTextField EngineersFirstNameTF;
	private JTextField EngineersLastNameTF;
	private JTextField EngineersCountryAddressTF;
	private JTextField EngineersCityAddressTF;
	private JTextField EngineersStreetNoAddressTF;
	private JTextField EngineersStreetNameAddressTF;
	private JTextField EngineersStreetApartmentNoAddressTF;
	private JTextField EngineersFieldNoTF;
	private JTextField engineerPhoneID;
	private JTextField engineerPhonePhone;
	private JTextField engineerProject_FieldNO_TF;
	private JTextField engineerProject_ID_TF;
	private JTextField engineerProject_ProjectNO_TF;
	private JTextField engineerProject_ProjectName_TF;
	private JTextField engineerProject_Grade_TF;
	private JButton engineerViewProjectsBtn;
	private JLabel projectsMilestoneLabel;
	private JTextField projectsMilestoneProjectNO_TF;
	private JTextField projectsMilestoneDate_TF;
	private JTextField projectsMilestoneDescription_TF;
	private JTextField projectsMilestoneMoney_TF;
	private JButton addProjectsMilestone;
	private JLabel projectsDevelopmentToolsLabel;
	private JTextField projectsDT_ProjectNO_TF;
	private JTextField projectsDT_Stage_TF;
	private JTextField projectsDT_ToolName_TF;
	private JButton addDevelopmentToolsBtn;
	private JTextField EngineerAgeTF;
	
	private ArrayList<JTextField> tfList;
	/**
	 * Create the frame.
	 */
	public SqlProjectGUI(Connection con) {	
		tfList = new ArrayList<JTextField>();
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ General GUI Contents ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 10, 1290, 1010);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 1265, 960);
		contentPane.add(tabbedPane);
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Field GUI Contents ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		JPanel fieldPanel = new JPanel();
		fieldPanel.setBackground(Color.decode("#3d3d5c"));
		tabbedPane.addTab("Fields", null, fieldPanel, null);
		fieldPanel.setLayout(null);
		
		/* Field "View Table" Button */
		JButton fieldViewTableBtn = new JButton("View Table");		
		fieldViewTableBtn.setForeground(Color.BLACK);
		fieldViewTableBtn.setBackground(Color.decode("#008B8B"));
		fieldViewTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				//Set all text fields to ""
				resetTextFields(tfList);
				showTable(con,"software_dept",fieldTable);				
			}
		});
		fieldViewTableBtn.setBounds(10, 56, 160, 28);
		fieldPanel.add(fieldViewTableBtn);
		
		/* Field "Add" Button */
		JButton fieldAddBtn = new JButton("Add");
		fieldAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(FieldNoTF.getText().isEmpty()) && !(FieldNameTF.getText().isEmpty()) && !(FieldSpecializationTF.getText().isEmpty()))
				{
					try {
						  String query = " insert ignore into software_dept (field_NO, name, specialize)" + " values (?, ?, ?)";
					      // create the mysql insert preparedstatement
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setInt (1, Integer.parseInt(FieldNoTF.getText()));
					      preparedStmt.setString (2, FieldNameTF.getText());
					      preparedStmt.setString   (3, FieldSpecializationTF.getText());				      
					      // execute the preparedstatement
					      preparedStmt.executeUpdate();
					      preparedStmt.close();
					      resetTextFields(tfList);
					      showTable(con,"software_dept",fieldTable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		fieldAddBtn.setForeground(Color.BLACK);
		fieldAddBtn.setBackground(Color.decode("#008B8B"));
		fieldAddBtn.setBounds(180, 56, 160, 28);
		fieldPanel.add(fieldAddBtn);
		
		/* Field "Update" Button */
		JButton fieldUpdateBtn = new JButton("Update");
		fieldUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(FieldNoTF.getText().isEmpty()) && !(FieldNameTF.getText().isEmpty()) && !(FieldSpecializationTF.getText().isEmpty()))
				{
					try {
						int row = fieldTable.getSelectedRow();
						String value = (fieldTable.getModel().getValueAt(row, 0).toString());
						String query = "update software_dept set name=?, specialize=? where field_NO="+value;
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, FieldNameTF.getText());
						pst.setString(2, FieldSpecializationTF.getText());
						pst.executeUpdate();
						resetTextFields(tfList);
						showTable(con,"software_dept",fieldTable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		fieldUpdateBtn.setForeground(Color.BLACK);
		fieldUpdateBtn.setBackground(Color.decode("#008B8B"));
		fieldUpdateBtn.setBounds(350, 56, 160, 28);
		fieldPanel.add(fieldUpdateBtn);
		
		/* Field "Delete" Button */
		JButton fieldDeleteBtn = new JButton("Delete");
		fieldDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(FieldNoTF.getText().isEmpty()) && !(FieldNameTF.getText().isEmpty()) && !(FieldSpecializationTF.getText().isEmpty()))
				{
					DeleteData(con,"software_dept","field_NO", fieldTable);				
					resetTextFields(tfList);
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);			
			}
		});
		fieldDeleteBtn.setForeground(Color.BLACK);
		fieldDeleteBtn.setBackground(Color.decode("#008B8B"));
		fieldDeleteBtn.setBounds(520, 56, 160, 28);
		fieldPanel.add(fieldDeleteBtn);
		
		/* Field Table Panel */
		JScrollPane fieldTableScrollPanel = new JScrollPane();
		fieldTableScrollPanel.setBounds(10, 95, 1235, 188);
		fieldPanel.add(fieldTableScrollPanel);
		
		/* Field Table */
		fieldTable = new JTable();
		fieldTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = fieldTable.getSelectedRow();
				TableModel model = fieldTable.getModel();
				FieldNoTF.setText(model.getValueAt(i, 0).toString());
				FieldNameTF.setText(model.getValueAt(i, 1).toString());
				FieldSpecializationTF.setText(model.getValueAt(i, 2).toString());	
				FieldNoTF.setEditable(false);
			}
		});
		fieldTableScrollPanel.setViewportView(fieldTable);
		
		/* Field Text Fields */
		FieldNoTF = new JTextField();
		FieldNoTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FieldNoTF.setEditable(true);
			}
		});
		FieldNoTF.setBounds(10, 294, 410, 20);
		fieldPanel.add(FieldNoTF);
		FieldNoTF.setColumns(10);

		
		FieldNameTF = new JTextField();
		FieldNameTF.setBounds(421, 294, 410, 20);
		fieldPanel.add(FieldNameTF);
		FieldNameTF.setColumns(10);
		
		FieldSpecializationTF = new JTextField();
		FieldSpecializationTF.setBounds(832, 294, 410, 20);
		fieldPanel.add(FieldSpecializationTF);
		FieldSpecializationTF.setColumns(10);
		
		tfList.add(FieldNoTF);
		tfList.add(FieldNameTF);
		tfList.add(FieldSpecializationTF);
				
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Projects GUI Contents ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		JPanel projectsPanel = new JPanel();
		projectsPanel.setBackground(Color.decode("#3d3d5c"));
		tabbedPane.addTab("Projects", null, projectsPanel, null);
		projectsPanel.setLayout(null);
		
		/* Projects "View Table" Button */
		JButton projectViewTableBtn = new JButton("View Table");
		projectViewTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 1;
				resetTextFields(tfList);
				showTable(con,"projects",projectTable);	
			}
		});
		projectViewTableBtn.setForeground(Color.BLACK);
		projectViewTableBtn.setBackground(Color.decode("#008B8B"));
		projectViewTableBtn.setBounds(10, 11, 160, 28);
		projectsPanel.add(projectViewTableBtn);
		
		/* Projects "Add" Button */
		JButton projectAddBtn = new JButton("Add");
		projectAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) &&  !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty()))
				{
					try {
						  String query = " insert ignore into projects (project_NO, project_name, description, beginning_date, costumer_name)" + " values (?, ?, ?, ?, ?)";
					      // create the mysql insert preparedstatement
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setInt (1, Integer.parseInt(ProjectsNoTF.getText()));
					      preparedStmt.setString (2, ProjectNameTF.getText());
					      preparedStmt.setString   (3, ProjectDescriptionTF.getText());
					      preparedStmt.setDate(4, java.sql.Date.valueOf(ProjectBegginingDateTF.getText()));
					      preparedStmt.setString   (5, ProjectCostumerNameTF.getText());			      
					      // execute the preparedstatement
					      preparedStmt.executeUpdate();
					      preparedStmt.close();
					      resetTextFields(tfList);
					      showTable(con,"projects",projectTable);	
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		projectAddBtn.setForeground(Color.BLACK);
		projectAddBtn.setBackground(Color.decode("#008B8B"));
		projectAddBtn.setBounds(180, 11, 160, 28);
		projectsPanel.add(projectAddBtn);
		
		/* Projects "Update" Button */
		JButton projectUpdateBtn = new JButton("Update");
		projectUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) &&  !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty()))
				{
					try {
						int row = projectTable.getSelectedRow();
						String value = (projectTable.getModel().getValueAt(row, 0).toString());
						String query = "update projects set project_name=?, description=?, beginning_date=?, costumer_name=? where project_NO="+value;
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, ProjectNameTF.getText());
						pst.setString(2, ProjectDescriptionTF.getText());
						pst.setDate(3, java.sql.Date.valueOf(ProjectBegginingDateTF.getText()));
						pst.setString(4, ProjectCostumerNameTF.getText());
						pst.executeUpdate();
						resetTextFields(tfList);
						showTable(con,"projects",projectTable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					}
				}
				else if(!(projectsMilestoneProjectNO_TF.getText().isEmpty()) && !(projectsMilestoneDate_TF.getText().isEmpty()) && !(projectsMilestoneDescription_TF.getText().isEmpty()) && !(projectsMilestoneMoney_TF.getText().isEmpty()))
				{
					try
					{
						int row = projectTable.getSelectedRow();
						String value = (projectTable.getModel().getValueAt(row, 2).toString());
						String query = "update milestone set milestone_date=?, milestone_submit=?, milestone_money_received=? where milestone_submit='"+value+"'";				
						PreparedStatement pst = con.prepareStatement(query);
						pst.setDate(1, java.sql.Date.valueOf(projectsMilestoneDate_TF.getText()));
						pst.setString(2, projectsMilestoneDescription_TF.getText());
						pst.setInt (3, Integer.parseInt(projectsMilestoneMoney_TF.getText()));
						pst.executeUpdate();
						String query1 = "select project_NO as Project_Number, milestone_date as Date, milestone_submit as Description, milestone_money_received as Money_Recived from milestone where project_NO="+projectsMilestoneProjectNO_TF.getText() +  " group by milestone_submit";
						PreparedStatement pst1 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst1.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst1.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(!(projectsDT_ProjectNO_TF.getText().isEmpty()) && !(projectsDT_Stage_TF.getText().isEmpty()) && !(projectsDT_ToolName_TF.getText().isEmpty()))
				{
					try
					{
						int row = projectTable.getSelectedRow();
						String value = (projectTable.getModel().getValueAt(row, 2).toString());
						String query = "update helping_tools set stage_name=?, tool_name=? where tool_name='"+value+"'";				
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, projectsDT_Stage_TF.getText());
						pst.setString(2, projectsDT_ToolName_TF.getText());
						pst.executeUpdate();
						String query1 = "select project_NO as Project_Number, stage_name as Stage_Name, tool_name as Tool_Name from helping_tools where project_NO="+projectsDT_ProjectNO_TF.getText() + " group by tool_name order by stage_name";
						PreparedStatement pst2 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst2.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						//Set all text fields to ""
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		projectUpdateBtn.setForeground(Color.BLACK);
		projectUpdateBtn.setBackground(Color.decode("#008B8B"));
		projectUpdateBtn.setBounds(350, 11, 160, 28);
		projectsPanel.add(projectUpdateBtn);
		
		/* Projects "Delete" Button */
		JButton projectDeleteBtn = new JButton("Delete");
		projectDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) && !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty()))
				{
					resetTextFields(tfList);
					DeleteData(con,"projects", "project_NO", projectTable);
				}
				else if(!(projectsMilestoneProjectNO_TF.getText().isEmpty()) && !(projectsMilestoneDate_TF.getText().isEmpty()) && !(projectsMilestoneDescription_TF.getText().isEmpty()) && !(projectsMilestoneMoney_TF.getText().isEmpty()))
				{
					try {				
						flag=2;
						int row = projectTable.getSelectedRow();
						String value = (projectTable.getModel().getValueAt(row, 2).toString());
						String query = "delete from milestone where milestone_submit='"+value+"'";
						PreparedStatement pst = con.prepareStatement(query);
						pst.executeUpdate();
						pst.close();
						DefaultTableModel model = (DefaultTableModel)projectTable.getModel();
						model.setRowCount(0);
						String query1 = "select project_NO as Project_Number, milestone_date as Date, milestone_submit as Description, milestone_money_received as Money_Recived from milestone where project_NO="+projectsMilestoneProjectNO_TF.getText() +  " group by milestone_submit";
						PreparedStatement pst1 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst1.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst1.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(!(projectsDT_ProjectNO_TF.getText().isEmpty()) && !(projectsDT_Stage_TF.getText().isEmpty()) && !(projectsDT_ToolName_TF.getText().isEmpty()))
				{
					try {				
						flag=3;
						int row = projectTable.getSelectedRow();
						String value = (projectTable.getModel().getValueAt(row, 2).toString());
						String query = "delete from helping_tools where tool_name='"+value+"'";
						PreparedStatement pst = con.prepareStatement(query);
						pst.executeUpdate();
						pst.close();
						DefaultTableModel model = (DefaultTableModel)projectTable.getModel();
						String query1 = "select project_NO as Project_Number, stage_name as Stage_Name, tool_name as Tool_Name from helping_tools where project_NO="+projectsDT_ProjectNO_TF.getText() + " group by tool_name order by stage_name";
						PreparedStatement pst2 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst2.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						//Set all field tab text fields to ""
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		projectDeleteBtn.setForeground(Color.BLACK);
		projectDeleteBtn.setBackground(Color.decode("#008B8B"));
		projectDeleteBtn.setBounds(520, 11, 160, 28);
		projectsPanel.add(projectDeleteBtn);
		
		/* Projects Buttons */
		
		/* Development Tools Button */
		JButton projectsDevelopmentToolsBtn = new JButton("Development Tools");
		projectsDevelopmentToolsBtn.setForeground(Color.BLACK);
		projectsDevelopmentToolsBtn.setBackground(Color.decode("#008B8B"));
		projectsDevelopmentToolsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((projectTable.getSelectedRow()>=0) && (!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) && !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty())))
				{
					try {
						flag = 3;
						String query = "select project_NO as Project_Number, stage_name as Stage_Name, tool_name as Tool_Name from helping_tools where project_NO="+ProjectsNoTF.getText() + " group by tool_name order by stage_name";
						PreparedStatement pst = con.prepareStatement(query);
						ResultSet rs;
						rs = pst.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		projectsDevelopmentToolsBtn.setBounds(350, 56, 160, 28);
		projectsPanel.add(projectsDevelopmentToolsBtn);
		
		/* Milestones Button */
		JButton projectsMilestonesBtn = new JButton("Milestones");
		projectsMilestonesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((projectTable.getSelectedRow()>=0) && (!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) && !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty())))
				{
					try {
						flag = 2;
						String query = "select project_NO as Project_Number, milestone_date as Date, milestone_submit as Description, milestone_money_received as Money_Received from milestone where project_NO="+ProjectsNoTF.getText() + " group by milestone_submit order by milestone_date";
						PreparedStatement pst = con.prepareStatement(query);
						ResultSet rs;
						rs = pst.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		projectsMilestonesBtn.setForeground(Color.BLACK);
		projectsMilestonesBtn.setBackground(Color.decode("#008B8B"));
		projectsMilestonesBtn.setBounds(690, 56, 160, 28);
		projectsPanel.add(projectsMilestonesBtn);
		
		/* Engineers by field Button */
		JButton projectsEngineersByField = new JButton("Engineers By Field");
		projectsEngineersByField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((projectTable.getSelectedRow()>=0) && (!(ProjectsNoTF.getText().isEmpty()) && !(ProjectNameTF.getText().isEmpty()) && !(ProjectDescriptionTF.getText().isEmpty()) && !(ProjectBegginingDateTF.getText().isEmpty()) && !(ProjectCostumerNameTF.getText().isEmpty())))
				{
					try {
						flag = 0;
						String query = "select works_on.field_NO as Field_Number, engineers.name_first as First_Name,engineers.name_last as Last_Name from works_on,engineers where works_on.project_NO="+ProjectsNoTF.getText() +  " AND works_on.id=engineers.id group by works_on.id order by works_on.field_NO asc";
						PreparedStatement pst = con.prepareStatement(query);
						ResultSet rs;
						rs = pst.executeQuery();
						projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		projectsEngineersByField.setForeground(Color.BLACK);
		projectsEngineersByField.setBackground(Color.decode("#008B8B"));
		projectsEngineersByField.setBounds(520, 56, 160, 28);
		projectsPanel.add(projectsEngineersByField);
		
		/* Top 3 projects Button */
		JButton projectsTopBtn = new JButton("Top Projects");
		projectsTopBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					flag = 0;
					String query = "select project_NO as Project_Number, project_name as Project_Name, avg(grade) as Average from works_on group by project_NO order by avg(grade) desc limit 3";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs;
					rs = pst.executeQuery();
					projectTable.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		projectsTopBtn.setForeground(Color.BLACK);
		projectsTopBtn.setBackground(Color.decode("#008B8B"));
		projectsTopBtn.setBounds(180, 56, 160, 28);
		projectsPanel.add(projectsTopBtn);
		
		/* Bottom 3 projects Button */
		JButton projectsBottomBtn = new JButton("Bottom Projects");
		projectsBottomBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				try {
					flag = 0;
					String query = "select project_NO as Project_Number, project_name as Project_Name, avg(grade) as Average from works_on group by project_NO order by avg(grade) asc limit 3";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs;
					rs = pst.executeQuery();
					projectTable.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		projectsBottomBtn.setForeground(Color.BLACK);
		projectsBottomBtn.setBackground(Color.decode("#008B8B"));
		projectsBottomBtn.setBounds(10, 56, 160, 28);
		projectsPanel.add(projectsBottomBtn);
		
		/* Add Milestone Button */
		addProjectsMilestone = new JButton("Submit!");
		addProjectsMilestone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(projectsMilestoneProjectNO_TF.getText().isEmpty()) && !(projectsMilestoneDate_TF.getText().isEmpty()) && !(projectsMilestoneDescription_TF.getText().isEmpty()) && !(projectsMilestoneMoney_TF.getText().isEmpty()))
				{
					try {
						  flag = 2;
						  String query = " insert ignore into milestone (project_NO, milestone_date, milestone_submit, milestone_money_received)" + " values (?, ?, ?, ?)";
						  // create the mysql insert preparedstatement
						  PreparedStatement preparedStmt;
						  preparedStmt = con.prepareStatement(query);
						  preparedStmt.setInt (1, Integer.parseInt(projectsMilestoneProjectNO_TF.getText()));
						  preparedStmt.setDate(2, java.sql.Date.valueOf(projectsMilestoneDate_TF.getText()));
						  preparedStmt.setString(3, projectsMilestoneDescription_TF.getText());						  
						  preparedStmt.setInt (4, Integer.parseInt(projectsMilestoneMoney_TF.getText()));		
						  // execute the preparedstatement
						  preparedStmt.executeUpdate();
						  preparedStmt.close();
						//Set all field tab text fields to ""
						  String query1 = "select project_NO as Project_Number, milestone_date as Date, milestone_submit as Description, milestone_money_received as Money_Recived from milestone where project_NO="+projectsMilestoneProjectNO_TF.getText() +  " group by milestone_submit";
						  PreparedStatement pst = con.prepareStatement(query1);
						  ResultSet rs;
						  rs = pst.executeQuery();
						  projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						  pst.close();
						  resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		addProjectsMilestone.setBounds(573, 349, 86, 20);
		projectsPanel.add(addProjectsMilestone);
		
		/* Add Development Tool Button */
		addDevelopmentToolsBtn = new JButton("Submit!");
		addDevelopmentToolsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(projectsDT_ProjectNO_TF.getText().isEmpty()) && !(projectsDT_Stage_TF.getText().isEmpty()) && !(projectsDT_ToolName_TF.getText().isEmpty()))
				{
					try {
						  flag = 3;
						  String query = " insert ignore into helping_tools (project_NO, stage_name, tool_name)" + " values (?, ?, ?)";
						  // create the mysql insert preparedstatement
						  PreparedStatement preparedStmt;
						  preparedStmt = con.prepareStatement(query);
						  preparedStmt.setInt (1, Integer.parseInt(projectsDT_ProjectNO_TF.getText()));
						  preparedStmt.setString(2, projectsDT_Stage_TF.getText());		
						  preparedStmt.setString(3, projectsDT_ToolName_TF.getText());						  	
						  // execute the preparedstatement
						  preparedStmt.executeUpdate();
						  preparedStmt.close();
						  
						  String query1 = "select project_NO, stage_name, tool_name from helping_tools where project_NO="+projectsDT_ProjectNO_TF.getText() + " group by tool_name order by stage_name";
						  PreparedStatement pst = con.prepareStatement(query1);
						  ResultSet rs;
						  rs = pst.executeQuery();
						  projectTable.setModel(DbUtils.resultSetToTableModel(rs));
						  pst.close();
						  //Set all field tab text fields to ""
						  resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		addDevelopmentToolsBtn.setBounds(477, 403, 86, 20);
		projectsPanel.add(addDevelopmentToolsBtn);
		
		/* Projects Table Panel */
		JScrollPane projectTableScrollPanel = new JScrollPane();
		projectTableScrollPanel.setBounds(10, 95, 1235, 188);
		projectsPanel.add(projectTableScrollPanel);
		
		/* Projects Table */
		projectTable = new JTable();
		projectTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag == 1)
				{
					int i = projectTable.getSelectedRow();
					TableModel model = projectTable.getModel();
					ProjectsNoTF.setText(model.getValueAt(i, 0).toString());
					ProjectNameTF.setText(model.getValueAt(i, 1).toString());
					ProjectDescriptionTF.setText(model.getValueAt(i, 2).toString());
					ProjectBegginingDateTF.setText(model.getValueAt(i, 3).toString());
					ProjectCostumerNameTF.setText(model.getValueAt(i, 4).toString());		
					ProjectsNoTF.setEditable(false);
				}
				if(flag == 2)
				{
					int i = projectTable.getSelectedRow();
					TableModel model = projectTable.getModel();
					projectsMilestoneProjectNO_TF.setText(model.getValueAt(i, 0).toString());
					projectsMilestoneDate_TF.setText(model.getValueAt(i, 1).toString());
					projectsMilestoneDescription_TF.setText(model.getValueAt(i, 2).toString());
					projectsMilestoneMoney_TF.setText(model.getValueAt(i, 3).toString());
				}
				if(flag == 3)
				{
					int i = projectTable.getSelectedRow();
					TableModel model = projectTable.getModel();
					projectsDT_ProjectNO_TF.setText(model.getValueAt(i, 0).toString());
					projectsDT_Stage_TF.setText(model.getValueAt(i, 1).toString());
					projectsDT_ToolName_TF.setText(model.getValueAt(i, 2).toString());
				}
			}
		});
		projectTableScrollPanel.setViewportView(projectTable);
		
		/* Projects Text Fields */
		ProjectsNoTF = new JTextField();
		ProjectsNoTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProjectsNoTF.setEditable(true);
			}
		});
		ProjectsNoTF.setBounds(10, 294, 245, 20);
		projectsPanel.add(ProjectsNoTF);
		ProjectsNoTF.setColumns(10);
		
		ProjectNameTF = new JTextField();
		ProjectNameTF.setBounds(257, 294, 245, 20);
		projectsPanel.add(ProjectNameTF);
		ProjectNameTF.setColumns(10);
		
		ProjectDescriptionTF = new JTextField();
		ProjectDescriptionTF.setBounds(504, 294, 245, 20);
		projectsPanel.add(ProjectDescriptionTF);
		ProjectDescriptionTF.setColumns(10);
				
		ProjectBegginingDateTF = new JTextField();
		ProjectBegginingDateTF.setBounds(751, 294, 245, 20);
		projectsPanel.add(ProjectBegginingDateTF);
		ProjectBegginingDateTF.setColumns(10);
		
		ProjectCostumerNameTF = new JTextField();
		ProjectCostumerNameTF.setBounds(998, 294, 245, 20);
		projectsPanel.add(ProjectCostumerNameTF);
		ProjectCostumerNameTF.setColumns(10);
		
		projectsMilestoneLabel = new JLabel("Add Milestone:");
		projectsMilestoneLabel.setForeground(new Color(0, 255, 255));
		projectsMilestoneLabel.setBounds(10, 345, 100, 28);
		projectsPanel.add(projectsMilestoneLabel);
		
		projectsMilestoneProjectNO_TF = new JTextField();
		projectsMilestoneProjectNO_TF.setBounds(189, 349, 86, 20);
		projectsPanel.add(projectsMilestoneProjectNO_TF);
		projectsMilestoneProjectNO_TF.setColumns(10);
		projectsMilestoneProjectNO_TF.setEditable(false);
		
		projectsMilestoneDate_TF = new JTextField();
		projectsMilestoneDate_TF.setBounds(285, 349, 86, 20);
		projectsPanel.add(projectsMilestoneDate_TF);
		projectsMilestoneDate_TF.setColumns(10);
		
		projectsMilestoneDescription_TF = new JTextField();
		projectsMilestoneDescription_TF.setBounds(381, 349, 86, 20);
		projectsPanel.add(projectsMilestoneDescription_TF);
		projectsMilestoneDescription_TF.setColumns(10);
		
		projectsMilestoneMoney_TF = new JTextField();
		projectsMilestoneMoney_TF.setBounds(477, 349, 86, 20);
		projectsPanel.add(projectsMilestoneMoney_TF);
		projectsMilestoneMoney_TF.setColumns(10);
		
		projectsDevelopmentToolsLabel = new JLabel("Add Development Tool:");
		projectsDevelopmentToolsLabel.setForeground(new Color(0, 255, 255));
		projectsDevelopmentToolsLabel.setBounds(10, 399, 160, 28);
		projectsPanel.add(projectsDevelopmentToolsLabel);
		
		projectsDT_ProjectNO_TF = new JTextField();
		projectsDT_ProjectNO_TF.setBounds(189, 403, 86, 20);
		projectsPanel.add(projectsDT_ProjectNO_TF);
		projectsDT_ProjectNO_TF.setColumns(10);
		projectsDT_ProjectNO_TF.setEditable(false);
		
		projectsDT_Stage_TF = new JTextField();
		projectsDT_Stage_TF.setBounds(285, 403, 86, 20);
		projectsPanel.add(projectsDT_Stage_TF);
		projectsDT_Stage_TF.setColumns(10);
		
		projectsDT_ToolName_TF = new JTextField();
		projectsDT_ToolName_TF.setBounds(381, 403, 86, 20);
		projectsPanel.add(projectsDT_ToolName_TF);
		projectsDT_ToolName_TF.setColumns(10);
		
		tfList.add(ProjectsNoTF);
		tfList.add(ProjectNameTF);
		tfList.add(ProjectDescriptionTF);
		tfList.add(ProjectBegginingDateTF);
		tfList.add(ProjectCostumerNameTF);
		tfList.add(projectsMilestoneProjectNO_TF);
		tfList.add(projectsMilestoneDate_TF);
		tfList.add(projectsMilestoneDescription_TF);
		tfList.add(projectsMilestoneMoney_TF);
		tfList.add(projectsDT_ProjectNO_TF);
		tfList.add(projectsDT_Stage_TF);
		tfList.add(projectsDT_ToolName_TF);
							
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Engineers GUI Contents ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		JPanel engineersPanel = new JPanel();
		engineersPanel.setBackground(Color.decode("#3d3d5c"));
		tabbedPane.addTab("Engineers", null, engineersPanel, null);
		engineersPanel.setLayout(null);
		
		/* Engineers "View Table" Button */
		JButton engineersViewTableBtn = new JButton("View Table");
		engineersViewTableBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=1;
				resetTextFields(tfList);
			    try {
			    	String query = "ALTER TABLE engineers ADD Age int";
			    	//String query = "IF NOT EXISTS( SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE table_schema = 'sqlproject' AND table_name = 'engineers' AND column_name = 'Age')  THEN ALTER TABLE engineers ADD Age int";
			    	PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.executeUpdate();
						
			    	setAge(con);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					
				}
			    
				showTable(con,"engineers",engineersTable);			
			}
		});
		engineersViewTableBtn.setForeground(Color.BLACK);
		engineersViewTableBtn.setBackground(Color.decode("#008B8B"));
		engineersViewTableBtn.setBounds(10, 11, 160, 28);
		engineersPanel.add(engineersViewTableBtn);
		
		/* Engineers "Add" Button */
		JButton engineersAddBtn = new JButton("Add");
		engineersAddBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(EngineersIdTF.getText().isEmpty()) && !(EngineersDateOfBirthTF.getText().isEmpty()) && !(EngineersFirstNameTF.getText().isEmpty()) && !(EngineersLastNameTF.getText().isEmpty()) && !(EngineersCountryAddressTF.getText().isEmpty()) && !(EngineersCityAddressTF.getText().isEmpty()) && !(EngineersStreetNoAddressTF.getText().isEmpty()) && !(EngineersStreetNameAddressTF.getText().isEmpty()) && !(EngineersStreetApartmentNoAddressTF.getText().isEmpty()))
				{
					try {
						  String query = " insert ignore into engineers (id, field_NO, date_of_birth, name_first, name_last, address_country, address_city, address_street_NO, address_street_name, address_street_apartment_NO)" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					      // create the mysql insert preparedstatement
					      PreparedStatement preparedStmt = con.prepareStatement(query);
					      preparedStmt.setInt (1, Integer.parseInt(EngineersIdTF.getText()));
					      preparedStmt.setInt (2, Integer.parseInt(EngineersFieldNoTF.getText()));
					      preparedStmt.setDate (3, java.sql.Date.valueOf(EngineersDateOfBirthTF.getText()));
					      preparedStmt.setString   (4, EngineersFirstNameTF.getText());
					      preparedStmt.setString   (5, EngineersLastNameTF.getText());
					      preparedStmt.setString   (6, EngineersCountryAddressTF.getText());
					      preparedStmt.setString   (7, EngineersCityAddressTF.getText());
					      preparedStmt.setInt   (8, Integer.parseInt(EngineersStreetNoAddressTF.getText()));
					      preparedStmt.setString   (9, EngineersStreetNameAddressTF.getText());
					      preparedStmt.setInt   (10, Integer.parseInt(EngineersStreetApartmentNoAddressTF.getText()));			
					      // execute the preparedstatement
					      preparedStmt.executeUpdate();
					      preparedStmt.close();
					      //Set all engineers tab text fields to ""
					      resetTextFields(tfList);
					      setAge(con);
					      showTable(con,"engineers",engineersTable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					} catch(IllegalArgumentException iae){
						JOptionPane.showMessageDialog(null, "Invalid date input!", "Error!", 2);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);
			}
		});
		engineersAddBtn.setForeground(Color.BLACK);
		engineersAddBtn.setBackground(Color.decode("#008B8B"));
		engineersAddBtn.setBounds(180, 11, 160, 28);
		engineersPanel.add(engineersAddBtn);
		
		/* Engineers "Update" Button */
		JButton engineersUpdateBtn = new JButton("Update");
		engineersUpdateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(EngineersIdTF.getText().isEmpty()) && !(EngineersDateOfBirthTF.getText().isEmpty()) && !(EngineersFirstNameTF.getText().isEmpty()) && !(EngineersLastNameTF.getText().isEmpty()) && !(EngineersCountryAddressTF.getText().isEmpty()) && !(EngineersCityAddressTF.getText().isEmpty()) && !(EngineersStreetNoAddressTF.getText().isEmpty()) && !(EngineersStreetNameAddressTF.getText().isEmpty()) && !(EngineersStreetApartmentNoAddressTF.getText().isEmpty()))
				{
					try {
						int row = engineersTable.getSelectedRow();
						String value = (engineersTable.getModel().getValueAt(row, 0).toString());
						String query = "update engineers set field_NO=?, date_of_birth=?, name_first=?, name_last=?, address_country=?, address_city=?, address_street_NO=?, address_street_name=?, address_street_apartment_NO=? where id="+value;				
						PreparedStatement pst = con.prepareStatement(query);
						pst.setInt(1, Integer.parseInt(EngineersFieldNoTF.getText()));
						pst.setDate(2, java.sql.Date.valueOf(EngineersDateOfBirthTF.getText()));
						pst.setString(3, EngineersFirstNameTF.getText());
						pst.setString(4, EngineersLastNameTF.getText());
						pst.setString(5, EngineersCountryAddressTF.getText());
						pst.setString(6, EngineersCityAddressTF.getText());
						pst.setInt(7, Integer.parseInt(EngineersStreetNoAddressTF.getText()));
						pst.setString(8, EngineersStreetNameAddressTF.getText());
						pst.setInt(9, Integer.parseInt(EngineersStreetApartmentNoAddressTF.getText()));
						pst.executeUpdate();
						resetTextFields(tfList);
					    setAge(con);
						showTable(con,"engineers",engineersTable);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch(NumberFormatException nfe){
						JOptionPane.showMessageDialog(null, "Invalid data input!", "Error!", 2);
					}
				}
				else if(!(engineerPhoneID.getText().isEmpty()) && !(engineerPhonePhone.getText().isEmpty()))
				{
					try {
						int row = engineersTable.getSelectedRow();
						String value = (engineersTable.getModel().getValueAt(row, 1).toString());
						String query = "update phones set phone=? where phone="+value;				
						PreparedStatement pst = con.prepareStatement(query);
						pst.setString(1, engineerPhonePhone.getText());
						pst.executeUpdate();
						String query2 = "select id as ID, phone as Phone_Number from phones where id="+engineerPhoneID.getText() +  " group by Phone_Number";
						PreparedStatement pst2 = con.prepareStatement(query2);
						ResultSet rs;
						rs = pst2.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(!(engineerProject_FieldNO_TF.getText().isEmpty()) && !(engineerProject_ID_TF.getText().isEmpty()) && !(engineerProject_ProjectNO_TF.getText().isEmpty()) && !(engineerProject_ProjectName_TF.getText().isEmpty()) && !(engineerProject_Grade_TF.getText().isEmpty()))
				{
					try {
						int row = engineersTable.getSelectedRow();
						String value = (engineersTable.getModel().getValueAt(row, 2).toString());
						String query = "update works_on set project_NO=?,project_name=?,grade=? where project_NO="+value;				
						PreparedStatement pst = con.prepareStatement(query);
						pst.setInt(1, Integer.parseInt(engineerProject_ProjectNO_TF.getText()));
						pst.setString(2, engineerProject_ProjectName_TF.getText());
						pst.setInt(3, Integer.parseInt(engineerProject_Grade_TF.getText()));
						pst.executeUpdate();
						flag=3;
						String query1 = "select field_NO as Field_Nnumber, id as ID, project_NO as Project_Number, project_name as Project_Name, grade as Grade from works_on where id="+engineerProject_ID_TF.getText() +  " group by project_NO";
						PreparedStatement pst2 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst2.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);

			}
		});
		engineersUpdateBtn.setForeground(Color.BLACK);
		engineersUpdateBtn.setBackground(Color.decode("#008B8B"));
		engineersUpdateBtn.setBounds(350, 11, 160, 28);
		engineersPanel.add(engineersUpdateBtn);
		
		/* Engineers "Delete" Button */
		JButton engineersDeleteBtn = new JButton("Delete");
		engineersDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(EngineersIdTF.getText().isEmpty()) && !(EngineersDateOfBirthTF.getText().isEmpty()) && !(EngineersFirstNameTF.getText().isEmpty()) && !(EngineersLastNameTF.getText().isEmpty()) && !(EngineersCountryAddressTF.getText().isEmpty()) && !(EngineersCityAddressTF.getText().isEmpty()) && !(EngineersStreetNoAddressTF.getText().isEmpty()) && !(EngineersStreetNameAddressTF.getText().isEmpty()) && !(EngineersStreetApartmentNoAddressTF.getText().isEmpty()))
				{
					resetTextFields(tfList);
					DeleteData(con,"engineers", "id", engineersTable);
				}
				else if(!(engineerPhoneID.getText().isEmpty()) && !(engineerPhonePhone.getText().isEmpty()))
				{

					try {					
						int row = engineersTable.getSelectedRow();
						String value = (engineersTable.getModel().getValueAt(row, 1).toString());
						String query = "delete from phones where phone="+value;
						PreparedStatement pst = con.prepareStatement(query);
						pst.executeUpdate();
						pst.close();
						DefaultTableModel model = (DefaultTableModel)engineersTable.getModel();
						model.setRowCount(0);
						String query2 = "select id as ID, phone as Phone_Number from phones where id="+engineerPhoneID.getText() +  " group by Phone_Number";
						PreparedStatement pst2 = con.prepareStatement(query2);
						ResultSet rs;
						rs = pst2.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(!(engineerProject_FieldNO_TF.getText().isEmpty()) && !(engineerProject_ID_TF.getText().isEmpty()) && !(engineerProject_ProjectNO_TF.getText().isEmpty()) && !(engineerProject_ProjectName_TF.getText().isEmpty()) && !(engineerProject_Grade_TF.getText().isEmpty()))
				{
					try {					

						int row = engineersTable.getSelectedRow();
						String value = (engineersTable.getModel().getValueAt(row, 2).toString());
						String value2 = (engineersTable.getModel().getValueAt(row, 1).toString());
						String query = "delete from works_on where project_NO="+value +" AND id="+value2;
						PreparedStatement pst = con.prepareStatement(query);
						pst.executeUpdate();
						pst.close();					
						DefaultTableModel model = (DefaultTableModel)engineersTable.getModel();
						model.setRowCount(0);					
						String query1 = "select field_NO as Field_Nnumber, id as ID, project_NO as Project_Number, project_name as Project_Name, grade as Grade from works_on where id="+engineerProject_ID_TF.getText() +  " group by project_NO";
						PreparedStatement pst2 = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst2.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst2.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Please fill all the required data.", "Error!", 2);			
			}
		});
		engineersDeleteBtn.setForeground(Color.BLACK);
		engineersDeleteBtn.setBackground(Color.decode("#008B8B"));
		engineersDeleteBtn.setBounds(520, 11, 160, 28);
		engineersPanel.add(engineersDeleteBtn);
		
		/* Engineers Buttons */
		
		/* Busy Engineers Button */
		JButton engineersBusyBtn = new JButton("Busy Engineers");
		engineersBusyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					flag = 0;
					String query = "select name_first as First_Name,name_last as Last_Name from engineers where id in(select id from works_on group by id having avg(project_NO)=(select avg(project_NO) from projects))";
					PreparedStatement pst = con.prepareStatement(query);
					ResultSet rs;
					rs = pst.executeQuery();
					engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					resetTextFields(tfList);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		engineersBusyBtn.setForeground(Color.BLACK);
		engineersBusyBtn.setBackground(Color.decode("#008B8B"));
		engineersBusyBtn.setBounds(10, 56, 160, 28);
		engineersPanel.add(engineersBusyBtn);
		
		/* Add Phone Button */
		JButton addPhoneBtn = new JButton("Submit!");
		addPhoneBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(engineerPhoneID.getText().isEmpty()) && !(engineerPhonePhone.getText().isEmpty()))
				{
					try {
						  flag = 2;
						  String query = " insert ignore into phones (id, phone)" + " values (?, ?)";
						  // create the mysql insert preparedstatement
						  PreparedStatement preparedStmt;
						  preparedStmt = con.prepareStatement(query);
						  preparedStmt.setInt (1, Integer.parseInt(engineerPhoneID.getText()));
						  preparedStmt.setInt (2, Integer.parseInt(engineerPhonePhone.getText()));		      
						  // execute the preparedstatement
						  preparedStmt.executeUpdate();
						  preparedStmt.close();
						//Set all field tab text fields to ""
						  String query1 = "select id as ID, phone as Phone_Number from phones where id="+engineerPhoneID.getText() +  " group by phone";
						  PreparedStatement pst = con.prepareStatement(query1);
						  ResultSet rs;
						  rs = pst.executeQuery();
						  engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						  pst.close();
						  resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		addPhoneBtn.setBounds(325, 349, 86, 20);
		engineersPanel.add(addPhoneBtn);	
		
		/* View Phones Button */
		JButton engineerViewPhonesBtn = new JButton("View Phones");
		engineerViewPhonesBtn.setForeground(Color.BLACK);
		engineerViewPhonesBtn.setBackground(new Color(0, 139, 139));
		engineerViewPhonesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((engineersTable.getSelectedRow()>=0) && (!(EngineersIdTF.getText().isEmpty()) && !(EngineersDateOfBirthTF.getText().isEmpty()) && !(EngineersFirstNameTF.getText().isEmpty()) && !(EngineersLastNameTF.getText().isEmpty()) && !(EngineersCountryAddressTF.getText().isEmpty()) && !(EngineersCityAddressTF.getText().isEmpty()) && !(EngineersStreetNoAddressTF.getText().isEmpty()) && !(EngineersStreetNameAddressTF.getText().isEmpty()) && !(EngineersStreetApartmentNoAddressTF.getText().isEmpty())))
				{
					try {
						flag = 2;
						String query = "select id as ID, phone as Phone_Number from phones where id="+EngineersIdTF.getText() +  " group by Phone_Number";
						PreparedStatement pst = con.prepareStatement(query);
						ResultSet rs;
						rs = pst.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						resetTextFields(tfList); 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		engineerViewPhonesBtn.setBounds(180, 56, 160, 28);
		engineersPanel.add(engineerViewPhonesBtn);
		
		/* Add Project Button */
		JButton addProjectBtn = new JButton("Submit!");
		addProjectBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(engineerProject_FieldNO_TF.getText().isEmpty()) && !(engineerProject_ID_TF.getText().isEmpty()) && !(engineerProject_ProjectNO_TF.getText().isEmpty()) && !(engineerProject_ProjectName_TF.getText().isEmpty()) && !(engineerProject_Grade_TF.getText().isEmpty()))
				{
					try {
						  String query = " insert ignore into works_on (field_NO, id, project_NO, project_name, grade)" + " values (?, ?, ?, ?, ?)";
						  // create the mysql insert preparedstatement
						  PreparedStatement preparedStmt;
						  preparedStmt = con.prepareStatement(query);
						  preparedStmt.setInt (1, Integer.parseInt(engineerProject_FieldNO_TF.getText()));
						  preparedStmt.setInt (2, Integer.parseInt(engineerProject_ID_TF.getText()));	
						  preparedStmt.setInt (3, Integer.parseInt(engineerProject_ProjectNO_TF.getText()));	
						  preparedStmt.setString(4, engineerProject_ProjectName_TF.getText());
						  preparedStmt.setInt (5, Integer.parseInt(engineerProject_Grade_TF.getText()));	
						  // execute the preparedstatement
						  preparedStmt.executeUpdate();
						  preparedStmt.close();
						//Set all field tab text fields to ""
						  String query1 = "select field_NO as Field_Nnumber, id as ID, project_NO as Project_Number, project_name as Project_Name, grade as Grade from works_on where id="+engineerProject_ID_TF.getText() +  " group by project_NO";
						  PreparedStatement pst = con.prepareStatement(query1);
						  ResultSet rs;
						  rs = pst.executeQuery();
						  engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						  pst.close();
						  resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		addProjectBtn.setBounds(616, 403, 86, 20);
		engineersPanel.add(addProjectBtn);
		
		/* View Projects Button */
		engineerViewProjectsBtn = new JButton("View Projects");
		engineerViewProjectsBtn.setForeground(Color.BLACK);
		engineerViewProjectsBtn.setBackground(new Color(0, 139, 139));
		engineerViewProjectsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((engineersTable.getSelectedRow()>=0) && (!(EngineersIdTF.getText().isEmpty()) && !(EngineersDateOfBirthTF.getText().isEmpty()) && !(EngineersFirstNameTF.getText().isEmpty()) && !(EngineersLastNameTF.getText().isEmpty()) && !(EngineersCountryAddressTF.getText().isEmpty()) && !(EngineersCityAddressTF.getText().isEmpty()) && !(EngineersStreetNoAddressTF.getText().isEmpty()) && !(EngineersStreetNameAddressTF.getText().isEmpty()) && !(EngineersStreetApartmentNoAddressTF.getText().isEmpty())))
				{
					try {
						flag=3;
						String query1 = "select field_NO as Field_Number, id as ID, project_NO as Project_Number, project_name as Project_Name, grade as Grade from works_on where id="+EngineersIdTF.getText() +  " group by project_NO";
						PreparedStatement pst = con.prepareStatement(query1);
						ResultSet rs;
						rs = pst.executeQuery();
						engineersTable.setModel(DbUtils.resultSetToTableModel(rs));
						pst.close();
						resetTextFields(tfList);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		engineerViewProjectsBtn.setBounds(350, 56, 160, 28);
		engineersPanel.add(engineerViewProjectsBtn);
		
			
		/* Engineers Table Panel */
		JScrollPane engineersTableScrollPanel = new JScrollPane();
		engineersTableScrollPanel.setBounds(10, 95, 1235, 188);
		engineersPanel.add(engineersTableScrollPanel);
		
		/* Projects Table */
		engineersTable = new JTable();
		engineersTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(flag==1)
				{
					int i = engineersTable.getSelectedRow();
					TableModel model = engineersTable.getModel();
					EngineersIdTF.setText(model.getValueAt(i, 0).toString());
					EngineersFieldNoTF.setText(model.getValueAt(i, 1).toString());
					EngineersDateOfBirthTF.setText(model.getValueAt(i, 2).toString());
					EngineersFirstNameTF.setText(model.getValueAt(i, 3).toString());
					EngineersLastNameTF.setText(model.getValueAt(i, 4).toString());
					EngineerAgeTF.setText(model.getValueAt(i, 5).toString());
					EngineersCountryAddressTF.setText(model.getValueAt(i, 6).toString());
					EngineersCityAddressTF.setText(model.getValueAt(i, 7).toString());
					EngineersStreetNoAddressTF.setText(model.getValueAt(i, 8).toString());
					EngineersStreetNameAddressTF.setText(model.getValueAt(i, 9).toString());
					EngineersStreetApartmentNoAddressTF.setText(model.getValueAt(i, 10).toString());
					EngineersIdTF.setEditable(false);
				}
				if(flag==2)
				{
					int i = engineersTable.getSelectedRow();
					TableModel model = engineersTable.getModel();
					engineerPhoneID.setText(model.getValueAt(i, 0).toString());
					engineerPhonePhone.setText(model.getValueAt(i, 1).toString());
				}
				if(flag==3)
				{
					int i = engineersTable.getSelectedRow();
					TableModel model = engineersTable.getModel();
					engineerProject_FieldNO_TF.setText(model.getValueAt(i, 0).toString());
					engineerProject_ID_TF.setText(model.getValueAt(i, 1).toString());
					engineerProject_ProjectNO_TF.setText(model.getValueAt(i, 2).toString());
					engineerProject_ProjectName_TF.setText(model.getValueAt(i, 3).toString());
					engineerProject_Grade_TF.setText(model.getValueAt(i, 4).toString());
				}
			}
		});
		engineersTableScrollPanel.setViewportView(engineersTable);
		
		/* Engineers Text Fields */
		EngineersIdTF = new JTextField();
		EngineersIdTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EngineersIdTF.setEditable(true);
			}
		});
		EngineersIdTF.setBounds(10, 294, 110, 20);
		engineersPanel.add(EngineersIdTF);
		EngineersIdTF.setColumns(10);
		
		EngineersFieldNoTF = new JTextField();
		EngineersFieldNoTF.setBounds(122, 294, 110, 20);
		engineersPanel.add(EngineersFieldNoTF);
		EngineersFieldNoTF.setColumns(10);
		
		EngineersDateOfBirthTF = new JTextField();
		EngineersDateOfBirthTF.setBounds(234, 294, 110, 20);
		engineersPanel.add(EngineersDateOfBirthTF);
		EngineersDateOfBirthTF.setColumns(10);
		
		EngineersFirstNameTF = new JTextField();
		EngineersFirstNameTF.setBounds(346, 294, 110, 20);
		engineersPanel.add(EngineersFirstNameTF);
		EngineersFirstNameTF.setColumns(10);
		
		EngineersLastNameTF = new JTextField();
		EngineersLastNameTF.setBounds(458, 294, 110, 20);
		engineersPanel.add(EngineersLastNameTF);
		EngineersLastNameTF.setColumns(10);
		
		EngineerAgeTF = new JTextField();
		EngineerAgeTF.setBounds(570, 294, 111, 20);
		engineersPanel.add(EngineerAgeTF);
		EngineerAgeTF.setColumns(10);
		EngineerAgeTF.setEditable(false);
		
		EngineersCountryAddressTF = new JTextField();
		EngineersCountryAddressTF.setBounds(682, 294, 111, 20);
		engineersPanel.add(EngineersCountryAddressTF);
		EngineersCountryAddressTF.setColumns(10);
		
		EngineersCityAddressTF = new JTextField();
		EngineersCityAddressTF.setBounds(794, 294, 111, 20);
		engineersPanel.add(EngineersCityAddressTF);
		EngineersCityAddressTF.setColumns(10);
		
		EngineersStreetNoAddressTF = new JTextField();
		EngineersStreetNoAddressTF.setBounds(906, 294, 111, 20);
		engineersPanel.add(EngineersStreetNoAddressTF);
		EngineersStreetNoAddressTF.setColumns(10);
		
		EngineersStreetNameAddressTF = new JTextField();
		EngineersStreetNameAddressTF.setBounds(1018, 294, 111, 20);
		engineersPanel.add(EngineersStreetNameAddressTF);
		EngineersStreetNameAddressTF.setColumns(10);
		
		EngineersStreetApartmentNoAddressTF = new JTextField();
		EngineersStreetApartmentNoAddressTF.setBounds(1130, 294, 111, 20);
		engineersPanel.add(EngineersStreetApartmentNoAddressTF);
		EngineersStreetApartmentNoAddressTF.setColumns(10);
		
		JLabel engineerPhoneLabel = new JLabel("Add Phone Number:");
		engineerPhoneLabel.setForeground(new Color(0, 255, 255));
		engineerPhoneLabel.setBounds(10, 345, 116, 28);
		engineersPanel.add(engineerPhoneLabel);
		
		engineerPhoneID = new JTextField();
		engineerPhoneID.setBounds(133, 349, 86, 20);
		engineersPanel.add(engineerPhoneID);
		engineerPhoneID.setColumns(10);
		engineerPhoneID.setEditable(false);
		
		
		engineerPhonePhone = new JTextField();
		engineerPhonePhone.setBounds(229, 349, 86, 20);
		engineersPanel.add(engineerPhonePhone);
		engineerPhonePhone.setColumns(10);
		
		JLabel engineerProjectAdd = new JLabel("Add Project:");
		engineerProjectAdd.setForeground(new Color(0, 255, 255));
		engineerProjectAdd.setBounds(10, 399, 107, 28);
		engineersPanel.add(engineerProjectAdd);
		
		engineerProject_FieldNO_TF = new JTextField();
		engineerProject_FieldNO_TF.setBounds(133, 403, 86, 20);
		engineersPanel.add(engineerProject_FieldNO_TF);
		engineerProject_FieldNO_TF.setColumns(10);
		engineerProject_FieldNO_TF.setEditable(false);
		
		engineerProject_ID_TF = new JTextField();
		engineerProject_ID_TF.setBounds(229, 403, 86, 20);
		engineersPanel.add(engineerProject_ID_TF);
		engineerProject_ID_TF.setColumns(10);
		engineerProject_ID_TF.setEditable(false);
		
		engineerProject_ProjectNO_TF = new JTextField();
		engineerProject_ProjectNO_TF.setBounds(325, 403, 86, 20);
		engineersPanel.add(engineerProject_ProjectNO_TF);
		engineerProject_ProjectNO_TF.setColumns(10);
		
		engineerProject_ProjectName_TF = new JTextField();
		engineerProject_ProjectName_TF.setBounds(421, 403, 86, 20);
		engineersPanel.add(engineerProject_ProjectName_TF);
		engineerProject_ProjectName_TF.setColumns(10);
		
		engineerProject_Grade_TF = new JTextField();
		engineerProject_Grade_TF.setBounds(520, 403, 86, 20);
		engineersPanel.add(engineerProject_Grade_TF);
		engineerProject_Grade_TF.setColumns(10);
		
		tfList.add(EngineersIdTF);
		tfList.add(EngineersFieldNoTF);
		tfList.add(EngineersDateOfBirthTF);
		tfList.add(EngineersFirstNameTF);
		tfList.add(EngineersLastNameTF);
		tfList.add(EngineerAgeTF);	
		tfList.add(EngineersCountryAddressTF);
		tfList.add(EngineersCityAddressTF);
		tfList.add(EngineersStreetNoAddressTF);
		tfList.add(EngineersStreetNameAddressTF);
		tfList.add(EngineersStreetApartmentNoAddressTF);
		tfList.add(engineerPhoneID);
		tfList.add(engineerPhonePhone);	
		tfList.add(engineerProject_FieldNO_TF);
		tfList.add(engineerProject_ID_TF);
		tfList.add(engineerProject_ProjectNO_TF);
		tfList.add(engineerProject_ProjectName_TF);
		tfList.add(engineerProject_Grade_TF);
									
	}

	/* Function to handle "View Table" Buttons */
	public static void showTable(Connection con, String tableName, JTable tmpTable){
		try {
			String query = null;
			switch (tableName)
			{
				case "software_dept":
				{
					query = "select field_NO as Field_Number,name as Name, specialize as Specialize from software_dept";
					break;
				}			
				case "projects":
				{
					query = "select project_NO as Project_Number,project_name as Project_Name,description as Description,beginning_date as Beginning_Date,costumer_name as Costumer_Name from projects";
					break;
				}
				case "engineers":
				{
					query = "select id as ID,field_NO as Field_Number,date_of_birth as Date_Of_Birth,name_first as First_Name,name_last as Last_Name,Age,address_country as Country,address_city as City,address_street_NO as Street_Number,address_street_name as Street_Name,address_street_apartment_NO as Apartment_Number from engineers";
					break;
				}
				case "phones":
				{
					query = "select id as ID,phone as Phone from phones group by phone order by id";
					break;
				}
			}
			PreparedStatement pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tmpTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/* Function to handle "Delete" Buttons */
	public static void DeleteData(Connection con, String tableName, String columnName, JTable tmpTable){
		try {
			int row = tmpTable.getSelectedRow();
			String value = (tmpTable.getModel().getValueAt(row, 0).toString());
			String query = "delete from "+ tableName+ " where "+columnName+"="+value;
			PreparedStatement pst = con.prepareStatement(query);
			pst.executeUpdate();
			pst.close();
			
			DefaultTableModel model = (DefaultTableModel)tmpTable.getModel();
			model.setRowCount(0);
			showTable(con,tableName,tmpTable);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	
	/* Function to keep age column in engineers table updated */
	public static void setAge(Connection con) throws SQLException
	{
		String query2 = "UPDATE engineers SET Age = DATE_FORMAT(FROM_DAYS(TO_DAYS(NOW())-TO_DAYS(date_of_birth)), '%Y')+0";
		PreparedStatement preparedStmt = con.prepareStatement(query2);
		preparedStmt.executeUpdate();
		preparedStmt.close();
	}
	
	/* Function that set all text fields to "" */
	public static void resetTextFields(ArrayList<JTextField> tmp)
	{
		for(int i=0; i<tmp.size(); i++)
		{
			tmp.get(i).setText("");
		}
	}
}

