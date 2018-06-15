import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) throws Exception
	{
		//Create connection to the database
		Connection con = getConnection();
		PreparedStatement create = null;
		Statement statement = con.createStatement();
		SqlData sqldata = new SqlData();
		
		//Populate the database with data
		//SqlData.createDataBase(con, create, statement);
		
		//Upload GUI
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SqlProjectGUI frame = new SqlProjectGUI(con);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Function to create connection to the database
	public static Connection getConnection() throws Exception{
		try{
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/sqlproject?autoReconnect=true&useSSL=false";
			String username = "root";
			String password = "951753leon";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}