package ie.wit.map.common.model.database;

import ie.wit.map.common.exceptions.InvalidEntryException;
import ie.wit.map.common.model.Area;
import ie.wit.map.common.model.Building;
import ie.wit.map.common.model.Photo;
import ie.wit.map.common.model.Place;

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
	public static List<Photo> getAllPhotos(){
		Statement statement = connect();
		List<Photo> list = new ArrayList<>();
		ResultSet res = null;
		if (checkConnection()){
			try{
				res = statement.executeQuery("SELECT * FROM photos");
				while(res.next()){
					String path = res.getString("photo_path");
					int building = res.getInt("building");
					Photo photo = new Photo(path, building);
					list.add(photo);
				}
				closeConnection(statement);
				System.out.println(list.size());
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
	public static boolean addPhoto(Photo photo){
		Statement statement = connect();
		if(checkConnection()){
			String path  = photo.getPath();
			int building = photo.getBuilding();
			try {
				statement.executeUpdate("INSERT INTO photos VALUES ('" + path + "', " + building + ");");
				closeConnection(statement);
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeConnection(statement);
		return false;
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
			String gui = building.getGuiArea();
			try {
				String sql = "INSERT INTO buildings VALUES (" + buildId + ", '" + buildName + "'," + numRooms + ", '" + type + "', '" + gui + "' );";
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
			String guilId = area.getGuiArea();
			try {
				String sql = "INSERT INTO areas VALUES (" + areaId + ", '" + areaName + "', '" + areaType + "', '" + areaId + "' );";
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

	public static void removeFromDatabase(int id, boolean isBuilding)
	{
		Statement statement = connect();
		String sql;
		if (isBuilding) {
			sql = "DELETE FROM buildings WHERE buildingId = " + id + ";";
		} else {
			sql = "DELETE FROM areas WHERE areaId = " + id + ";";
		}
		try {
			statement.executeUpdate(sql);
			closeConnection(statement);
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(statement);
		}
	}

	public static boolean updateBuilding(Building building)
	{
		Statement statement = connect();
		try {
			String name = building.getName();
			String type = building.getType();
			String gui = building.getGuiArea();
			int numRooms = building.getNumRooms();
			int id = building.getId();
			statement.executeUpdate("UPDATE buildings SET buildingName = '" + name +
					"', numberOfRooms = " + numRooms + ", guiArea = '" + gui + "', buildingType = '" +
					type + "' WHERE buildingId = " + id);
			closeConnection(statement);
			System.out.println("update successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(statement);
			return false;
		}
	}

	public static boolean updateArea(Area area)
	{
		Statement statement = connect();
		try {
			String name = area.getName();
			String type = area.getType();
			String gui = area.getGuiArea();
			int id = area.getId();
			statement.executeUpdate("UPDATE areas SET areaName ='" + name + "', areaType = '" +
					type + "', guiArea ='" + gui + "' WHERE areaId = " + id);
			closeConnection(statement);
			System.out.println("update successful");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			closeConnection(statement);
			return false;
		}
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
