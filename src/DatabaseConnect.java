import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DatabaseConnect {

	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;
	
	public DatabaseConnect() {
		// TODO Auto-generated constructor stub
	}
	
	public ResultSet connect(String s){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connect = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/data_mining","root","27537422Aa");

		statement = connect.createStatement();
		
		resultSet = statement.executeQuery(s);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return resultSet;
	}
}
