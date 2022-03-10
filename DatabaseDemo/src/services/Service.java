package services;

import java.sql.SQLException;

import util.DatabaseManager;
public abstract class Service {
	protected DatabaseManager dbm = DatabaseManager.getInstance();
	private String databaseName = "database_08022022.db";
	
	public Service() {
		try {
			dbm.connect(databaseName);			
		} catch (SQLException e) {
			System.out.println("Fehler beim Verbinden mit der Datenbank.");
		}
	}
}