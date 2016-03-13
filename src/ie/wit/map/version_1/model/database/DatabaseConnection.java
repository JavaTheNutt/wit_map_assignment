package ie.wit.map.version_1.model.database;

import ie.wit.map.version_1.exceptions.InvalidEntryException;
import ie.wit.map.version_1.model.Area;
import ie.wit.map.version_1.model.Building;
import ie.wit.map.version_1.model.Place;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will handle the database connection and all querys to the database
 */
public class DatabaseConnection
{
	private static final String username = "root";
	private static final String password = "root";
	private static final String host = "localhost:3306";
	private static final String database = "wit_map_db";
	private static final String dbURL = "jdbc:mysql://" + host + "/" + database + "?user=" + username + "&password=" + password;
	private static final String buildingCol01 = "buildingId";
	private static final String buildingCol02 = "buildingName";
	private static final String buildingCol03 = "numberRooms";
	private static Statement statement;

	public static void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			java.sql.Connection myConnection = DriverManager.getConnection(dbURL);
			statement = myConnection.createStatement();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static boolean checkConnection()
	{
		return statement != null;
	}

	public static List<Place> getAllFromRecords()
	{
		List<Place> list = new ArrayList<>();
		try {
			ResultSet res = statement.executeQuery("SELECT * FROM buildings");
			while (res.next()) {
				int numRooms = res.getInt("numberOfRooms");
				int id = res.getInt("buildingId");
				String buildingName = res.getString("buildingName");
				String buildingType = res.getString("buildingType");
				Building building;
				building = new Building(id, buildingName, numRooms, buildingType);
				list.add(building);
			}
			res = statement.executeQuery("SELECT * FROM areas");
			while (res.next()) {
				int areaId = res.getInt("areaId");
				String areaName = res.getString("areaName");
				String areaType = res.getString("areaType");
				Area area = new Area(areaId, areaName, areaType);
				list.add(area);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean addBuilding(Building building) throws InvalidEntryException
	{
		if (!building.isTypeValid()) {
			throw new InvalidEntryException("The building type does not match allowed entries");
		}
		int buildId = building.getId();
		String buildName = building.getName();
		int numRooms = building.getNumRooms();
		String type = building.getType();
		try {
			String sql = "INSERT INTO buildings VALUES (" + buildId + ", '" + buildName + "'," + numRooms + ", '" + type + "');";
			statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public static boolean addArea(Area area) throws InvalidEntryException
	{
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
		return true;
	}

}
