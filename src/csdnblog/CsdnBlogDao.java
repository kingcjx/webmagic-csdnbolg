package csdnblog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CsdnBlogDao {

	private Connection conn = null;
	private Statement stmt = null;

	public CsdnBlogDao() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webmagic?user=root&password=123456";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int add(CsdnBlog csdnBlog) {
		try {
			String sql = "INSERT INTO `webmagic`.`csdnblog` (title,href,userName,date,view) VALUES (?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, csdnBlog.getTitle());
			ps.setString(2, csdnBlog.getHref());
			ps.setString(3, csdnBlog.getUserName());
			ps.setString(4, csdnBlog.getDate());
			ps.setString(5, csdnBlog.getView()+"");
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
