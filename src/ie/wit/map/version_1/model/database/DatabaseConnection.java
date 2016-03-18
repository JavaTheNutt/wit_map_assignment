package ie.wit.map.version_1.model.database;

import ie.wit.map.version_1.exceptions.InvalidEntryException;
import ie.wit.map.version_1.model.Area;
import ie.wit.map.version_1.model.Building;
import ie.wit.map.version_1.model.Place;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will handle the database connection and all querys to the database
 */
public class DatabaseConnection
{
	// TODO: 18/03/2016 refactor so that there is each time the database is queried, a new connection is opened and closed
	private static final String username = "root";
	private static final String password = "root";
	private static final String host = "localhost:3306";
	private static final String database = "wit_map_db";
	private static final String dbURL = "jdbc:mysql://" + host + "/" + database + "?user=" + username + "&password=" + password;
	private static final String buildingCol01 = "buildingId";
	private static final String buildingCol02 = "buildingName";
	private static final String buildingCol03 = "numberRooms";
	private static Connection myConnection;

	private static Statement connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			myConnection = DriverManager.getConnection(dbURL);
			return myConnection.createStatement();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}

	public static boolean checkConnection()
	{
		return connect() != null;
	}

	public static List<Place> getAllFromRecords()
	{
		Statement statement = connect();
		List<Place> list = new ArrayList<>();
		ResultSet res = null;
		if (checkConnection()) {
			try {
				res = statement.executeQuery("SELECT * FROM buildings");
				while (res.next()) {
					int numRooms = res.getInt("numberOfRooms");
					int id = res.getInt("buildingId");
					String buildingName = res.getString("buildingName");
					String buildingType = res.getString("buildingType");
					String guiArea = res.getString("guiArea");
					Building building;
					building = new Building(id, buildingName, numRooms, buildingType, guiArea);
					list.add(building);
				}
				res = statement.executeQuery("SELECT * FROM areas");
				while (res.next()) {
					int areaId = res.getInt("areaId");
					String areaName = res.getString("areaName");
					String areaType = res.getString("areaType");
					String guiArea = res.getString("guiArea");
					Area area = new Area(areaId, areaName, areaType, guiArea);
					list.add(area);
				}
				res.close();
				closeConnection(statement);
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				closeConnection(statement);
				return null;
			}
		}
		closeConnection(statement);
		return null;
	}

	public static boolean addBuilding(Building building) throws InvalidEntryException
	{
		Statement statement = connect();
		if (checkConnection()) {
			if (!building.isTypeValid()) {
				throw new InvalidEntryException("The building type does not match allowed entries");
			}
			int buildId = building.getId();
			String buildName = building.getName();
			int numRooms = building.getNumRooms();
			String type = building.getType();
			try {
				String sql = "INSERT INTO buildings VALUES (" + buildId + ", '" + buildName + "'," + numRooms + ", '" + type + "');";
				int success = statement.executeUpdate(sql); //returns the number of records changed, 0 is an error
				closeConnection(statement);
				return true;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				closeConnection(statement);
				return false;
			}
		}
		closeConnection(statement);
		return false;
	}

	public static boolean addArea(Area area) throws InvalidEntryException
	{
		Statement statement = connect();
		if (checkConnection()) {
			if (!area.isTypeValid()) {
				throw new InvalidEntryException("The area type does not match allowed entries");
			}
			int areaId = area.getId();
			String areaName = area.getName();
			String areaType = area.getType();
			try {
				String sql = "INSERT INTO areas VALUES (" + areaId + ", '" + areaName + "', '" + areaType + "' );";
				statement.executeUpdate(sql);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			closeConnection(statement);
			return true;
		}
		closeConnection(statement);
		return false;
	}

	private static void closeConnection(Statement statement)
	{
		if (checkConnection()) {
			try {
				statement.close();
				myConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
