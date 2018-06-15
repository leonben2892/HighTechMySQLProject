import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlData {
	
	//This method will populate the database with data
	public static void createDataBase(Connection con, PreparedStatement create, Statement statement) throws SQLException
	{
		con.setAutoCommit(false);
		try {
			//Software Department table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS software_dept"
					+ "(field_NO int not null,"					
					+ "name varchar(255),"
					+ "specialize varchar(255),"
					+ "PRIMARY KEY(field_NO))");
			create.executeUpdate();
			
			//Insert into Software_Department table
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (1, 'Marketing', 	'Marketing')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (2, 'Hardware', 	'Support')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (3, 'Software', 	'C++')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (4, 'Software', 	'C#')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (5, 'Software', 	'Java')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (6, 'Databases', 	'SQL')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (7, 'Web', 		'Angular')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (8, 'Web', 		'JavaScript')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (9, 'Web', 		'HTML')");
			statement.executeUpdate("INSERT ignore INTO software_dept " + "VALUES (10,'Web', 		'CSS')");
			
			//Engineers table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS engineers"
					+ "(id int,"
					+ "field_NO int,"
					+ "date_of_birth date,"
					+ "name_first varchar(255),"
					+ "name_last varchar(255),"
					+ "address_country varchar(255),"
					+ "address_city varchar(255),"
					+ "address_street_NO int,"
					+ "address_street_name varchar(255),"
					+ "address_street_apartment_NO int,"
					+ "foreign key (field_NO) references software_dept(field_NO) on delete cascade,"
					+ "PRIMARY KEY(id))");
			create.executeUpdate();
			
			//Insert into Engineers table
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (10000	,1, '1991-01-01', 'Ross',	 	'Geller',	'Israel', 'Tel-Aviv', 	00, 'Bograshov',	 0)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (11111	,2, '1992-02-02', 'Rachel',	 	'Green', 	'Israel', 'Ramat-Gan', 	11, 'Ana-Frank',	 1)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (22222	,3, '1993-03-03', 'Monica', 	'Dorman', 	'Israel', 'Ramat-Gan', 	22, 'Ana-Frank',	 2)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (33333	,4, '1994-04-04', 'Chandller', 	'Bing', 	'Israel', 'Jerusalem', 	33, 'Nahala',		 3)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (44444	,5, '1995-05-05', 'Joey', 		'Tribiani', 'Israel', 'Holon', 		44, 'Ben-Gurion', 	 4)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (55555	,6, '1996-06-06', 'Phoebe', 	'Bofe', 	'Israel', 'Bat-Yam', 	55, 'Tayelet',		 5)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (66666	,7, '1997-07-07', 'Mona', 		'Halili', 	'Israel', 'Tel-Aviv', 	66, 'Jabotinzski',   6)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (77777	,8, '1998-08-08', 'Mike', 		'Tyson', 	'Israel', 'Petah-Tikva',77, 'Ramat-Yosef',   7)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (15151	,3, '1997-07-07', 'Mona', 		'Levi', 	'Israel', 'Tel-Aviv', 	66, 'Jabotinzski',   6)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (88888	,9, '1999-09-09', 'Jude', 		'Stevens', 	'Israel', 'Holon', 		88, 'Giborim', 	 	 8)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (99999	,10, '2000-10-10', 'Jake', 		'Black', 	'Israel', 'Haifa', 		99, 'Boaz', 		 9)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (12121	,7, '2007-10-10', 'Sharon', 	'Lee', 		'Israel', 'Eilat', 		2,  'Eilat-st', 	 13)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (13131	,1, '2007-10-10', 'James', 		'Dean', 	'Israel', 'Eilat', 		2,  'Eilat-st', 	 17)");
			statement.executeUpdate("INSERT ignore INTO engineers " + "VALUES (14141	,3, '2007-10-10', 'James', 		'Franco', 	'Israel', 'Eilat', 		2,  'Eilat-st', 	 99)");
					
			//Phone table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS phones"
					+ "(id int not null,"
					+ "phone int,"
					+ "foreign key (id) references engineers(id) on delete cascade)");
			create.executeUpdate();
			
			//Insert into phone table
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (10000, 0547567997)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (10000, 0547567998)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (10000, 0547567999)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (11111, 0507067494)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (22222, 0534447991)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (33333, 0547561125)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (44444, 0507567898)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (55555, 0501245890)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (55555, 0501245891)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (55555, 0501245892)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (55555, 0501245893)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (66666, 0537545666)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (77777, 0524444561)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (77777, 0524444562)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (88888, 0527545567)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (99999, 0537507548)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (15151, 0537502328)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (12121, 0543507548)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (13131, 0537343248)");
			statement.executeUpdate("INSERT ignore INTO phones " + "VALUES (14141, 0535675348)");
			
			//Projects table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS projects"
					+ "(project_NO int not null,"
					+ "project_name varchar(255),"
					+ "description varchar(255),"				
					+ "beginning_date date,"
					+ "costumer_name varchar(255),"
					+ "PRIMARY KEY (project_NO, project_name))");
			create.executeUpdate();
			
			//Insert into Projects table
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (100,  'name1',  'blabla1',  '2013-01-01', 'Company1')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (200,  'name2',  'blabla2',  '2013-02-02', 'Company2')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (300,  'name3',  'blabla3',  '2013-03-03', 'Company3')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (400,  'name4',  'blabla4',  '2013-04-04', 'Company4')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (500,  'name5',  'blabla5',  '2013-05-05', 'Company5')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (600,  'name6',  'blabla6',  '2013-06-06', 'Company6')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (700,  'name7',  'blabla7',  '2013-07-07', 'Company7')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (800,  'name8',  'blabla8',  '2013-08-08', 'Company8')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (900,  'name9',  'blabla9',  '2013-09-09', 'Company9')");
			statement.executeUpdate("INSERT ignore INTO projects " + "VALUES (1000, 'name10', 'blabla10', '2013-01-01', 'Company10')");
			
			//Milestone table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS milestone"
					+ "(project_NO int not null,"
					+ "milestone_date DATE,"
					+ "milestone_submit varchar(255),"
					+ "milestone_money_received int,"
					+ "foreign key(project_NO) references projects(project_NO) on delete cascade)");
			create.executeUpdate();
			
			//Insert into Milestone table
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (100,		'2015-02-01',	'Progress1',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (100,		'2016-02-01',	'Progress2',  20000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (200,		'2015-03-02',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (300,		'2015-04-03',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (400,		'2015-05-04',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (500,		'2016-06-05',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (600,		'2016-07-06',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (700,		'2016-08-07',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (800,		'2016-09-08',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (900,		'2017-10-09',	'Progress',  10000)");
			statement.executeUpdate("INSERT ignore INTO milestone " + "VALUES (1000,	'2017-02-01',	'Progress',  10000)");
			
			//Works_on table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS works_on"
					+ "(field_NO int not null,"
					+ "id int,"
					+ "project_NO int,"
					+ "project_name varchar(255),"
					+ "grade int,"
					+ "foreign key(field_NO) references software_dept(field_NO) on delete cascade,"
					+ "foreign key(id) references engineers(id) on delete cascade,"
					+ "foreign key(project_NO, project_name) references projects(project_NO, project_name) on delete cascade)");
			create.executeUpdate();
			
			//Insert into Works_on table
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	100,	'name1',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	200,	'name2',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	300,	'name3',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	400,	'name4',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	500,	'name5',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	600,	'name6',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	700,	'name7',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	800,	'name8',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	900,	'name9',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	10000,	1000,	'name10',  8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (2,	11111,	100,	'name1',   9)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (2,	11111,	200,	'name2',   5)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (2,	11111,	1000,	'name10',  0)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (3,	22222,	300,	'name3',   9)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (4,	33333,	400,	'name4',   6)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (5,	44444,	500,	'name5',   7)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (6,	55555,	600,	'name6',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (7,	66666,	100,	'name1',   7)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (7,	66666,	700,	'name7',   7)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (3,	15151,	100,	'name1',   6)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (8,	77777,	800,	'name8',   9)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (9,	88888,	900,	'name9',   4)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	99999,	100,	'name1',   1)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (2,	99999,	200,	'name2',   7)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (3,	99999,	300,	'name3',   5)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (4,	99999,	400,	'name4',   9)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (5,	99999,	500,	'name5',   4)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (6,	99999,	600,	'name6',   6)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (7,	99999,	700,	'name7',   5)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (8,	99999,	800,	'name8',   2)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (9,	99999,	900,	'name9',   3)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (10,	99999,	1000,	'name10',  4)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (1,	13131,	100,	'name1',   8)");
			statement.executeUpdate("INSERT ignore INTO works_on " + "VALUES (3,	14141,	100,	'name1',   6)");
			
			//Stage table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS stage"
					+ "(stage_name varchar(255),"
					+ " PRIMARY KEY(stage_name))");
			create.executeUpdate();
			
			//Insert into stage table
			statement.executeUpdate("INSERT ignore INTO stage " + "VALUES ('Requirments definition')");
			statement.executeUpdate("INSERT ignore INTO stage " + "VALUES ('Secondery')");
			statement.executeUpdate("INSERT ignore INTO stage " + "VALUES ('Coding')");
			statement.executeUpdate("INSERT ignore INTO stage " + "VALUES ('Tests')");
			statement.executeUpdate("INSERT ignore INTO stage " + "VALUES ('Configuration Management')");
			
			//Tools table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tools"
					+ "(tool_name varchar(255),"
					+ " PRIMARY KEY(tool_name))");
			create.executeUpdate();
		
			//Insert into tools table
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('Doors')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('Floors')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('Rhapsody')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('GitHub')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('Visual')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('Eclipse')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('KlocWork')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('QC')");
			statement.executeUpdate("INSERT ignore INTO tools " + "VALUES ('GIT')");
			
			//Project helping tools table
			create = con.prepareStatement("CREATE TABLE IF NOT EXISTS helping_tools"
					+ "(project_NO int,"
					+ "stage_name varchar(255),"
					+ "tool_name varchar(255),"
					+ "foreign key (project_NO) references projects(project_NO) on delete cascade,"
					+ "foreign key (stage_name) references stage(stage_name) on delete cascade,"
					+ "foreign key (tool_name) references tools(tool_name) on delete cascade)");
			create.executeUpdate();
		
			//Insert into helping tools table
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Requirments definition',	'Doors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Secondery',				'Rhapsody')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Coding',					'Visual')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Tests',					'QC')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (100,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (200,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (300,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (400,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (500,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (600,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (700,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (800,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (900,	'Configuration Management',	'GIT')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Requirments definition',	'Floors')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Secondery',				'GitHub')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Coding',					'CodeBlocks')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Coding',					'Eclipse')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Tests',					'KlocWork')");
			statement.executeUpdate("INSERT ignore INTO helping_tools " + "VALUES (1000,'Configuration Management',	'GIT')");
			
			con.commit();
		} catch (SQLException e1) {
			con.rollback();
			System.out.println("Changes are rollbacked and  not applied to the database.");
		}
		
	}
}
