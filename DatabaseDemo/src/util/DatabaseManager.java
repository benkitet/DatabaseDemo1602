package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringJoiner;

public final class DatabaseManager {
	private static DatabaseManager instance = null;
	private static Connection connection = null;

	private DatabaseManager() {}

    public static DatabaseManager getInstance() {
    	if(DatabaseManager.instance == null) {
    		DatabaseManager.instance = new DatabaseManager();
    	}
        return DatabaseManager.instance;
    }
    
    public void connect(String dbname) throws SQLException {
    	if(DatabaseManager.connection == null) {
    		try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
    		DatabaseManager.connection = DriverManager.getConnection("jdbc:sqlite:" + dbname);		
    		DatabaseManager.connection.setAutoCommit(false);
    	}
    }
    
    public boolean isConnected(){ return DatabaseManager.connection != null; }
    
    public void disconnect() throws Exception {
		if(DatabaseManager.connection != null) {
			DatabaseManager.connection.close();
			DatabaseManager.connection = null;
		}
    }
    
    public ResultSet select(String table, String[] fields, String[] where) throws SQLException {
    	String sql = "SELECT ";
    	for(String field : fields) {
    		sql += field + ", ";
    	}
    	sql = sql.substring(0, sql.length()-2);
    	sql += " FROM " + table;
    	
    	if(where != null && where.length == 3) {
    		sql += " WHERE " + where[0] + " " + where[1] + " ?";
    	}
    	
		PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(sql);
		if(where != null && where.length == 3) {
			pstmt.setString(1, where[2]);
		}
		return pstmt.executeQuery();
    }
    
    public int insert(String table, String[] fields, String[] values) throws SQLException {
    	int insertedId = 0;
    	String sql = "INSERT INTO " + table + " (";
    	for(String field : fields) {
    		sql += field + ", ";
    	}
    	sql = sql.substring(0, sql.length()-2) + ") VALUES (";
    	
    	for(int i = 0; i < fields.length; i++) {
    		sql += "?, ";
    	}
    	sql = sql.substring(0, sql.length()-2) + ")";
    	
		PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    	for(int i = 1; i <= values.length; i++) {
    		pstmt.setString(i, values[i-1]);
    	}
		if(pstmt.executeUpdate() > 0) {
			DatabaseManager.connection.commit();
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				insertedId = rs.getInt(1);
			}
		}
		return insertedId;
    }
    
    public boolean update(String table, String[] fields, String[] values, String[] where) throws SQLException {
    	String sql = "UPDATE " + table + " SET ";
    	for(String field : fields) {
    		sql += field + " = ?, ";
    	}

    	sql = sql.substring(0, sql.length()-2);
    	
    	if(where != null && where.length == 3) {
    		sql += " WHERE " + where[0] + " " + where[1] + " ?";
    	}
    	
		PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(sql);
    	for(int i = 1; i <= values.length; i++) {
    		pstmt.setString(i, values[i-1]);
    	}
		if(where != null && where.length == 3) {
			pstmt.setString(values.length+1, where[2]);
		}
		if(pstmt.executeUpdate() > 0) {
			DatabaseManager.connection.commit();
			return true;
		} else {
			return false;
		}
    }
    
    public boolean delete(String table, String[] where) throws SQLException {
    	String sql = "DELETE FROM " + table + " ";
    	if(where != null && where.length == 3) {
    		sql += " WHERE " + where[0] + " " + where[1] + " ?";
    	}
    	
		PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(sql);
		if(where != null && where.length == 3) {
			pstmt.setString(1, where[2]);
		}
		if(pstmt.executeUpdate() > 0) {
			DatabaseManager.connection.commit();
			return true;
		} else {
			return false;
		}
    }
    
    public boolean delete(String table, String[][] where) throws SQLException {
    	StringJoiner sj = new StringJoiner(" AND ");
    	if(where != null) {
    		for(String[] condition : where)
    			sj.add(condition[0] + " " + condition[1] + " ?");
    	}
    	String sql = "DELETE FROM " + table + " WHERE " + sj.toString();
    	
		PreparedStatement pstmt = DatabaseManager.connection.prepareStatement(sql);
		if(where != null) {
    		for(int i = 1; i < where.length - 1; i++)
    			pstmt.setString(i, where[i][2]);
    	}
		
		if(pstmt.executeUpdate() > 0) {
			DatabaseManager.connection.commit();
			return true;
		} else {
			return false;
		}
    }
    
}
