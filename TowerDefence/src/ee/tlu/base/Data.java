package ee.tlu.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JTable;

import static ee.tlu.base.Config.*;
public class Data {
	
	static Connection connection;
	Scanner sc = new Scanner(System.in);
	private String name, content;
	private int id;
	private int count;
	private int[] ids;
	private int sort;
	
	public Data() throws SQLException {
		
		connection = DriverManager.getConnection(address);
		
		////
	/*	System.out.println("Sisu muutmine: ");
		System.out.println("Sisesta nimi: ");
		name = sc.nextLine();
		System.out.println("Sisesta kaart: ");
		content = sc.nextLine();
		System.out.println("Sisesta id: ");
		id = sc.nextInt();
		updateMap(name, content, id);*/
		
		 System.out.println(getMap(4));
	/*	for (Map map: getMaps()) {
			System.out.println(map.toString());
		}
		List < Map > maps = getMaps();
		System.out.println(maps.get(0).getDate());
		String comma, mapString = "";
		for (int i = 0; i < maps.get(0).getContent().length(); i++) {
			comma = "";
			if ((i + 1) % 20 == 0) {
				comma = ",";
			}
			String s = maps.get(0).getContent().charAt(i) + " " + comma;
			mapString += s;
		}
		String[] rows = mapString.split(",");
		String[][] matrix = new String[rows.length][];
		int r = 0;
		for (String row: rows) {
			matrix[r++] = row.split(" ");
		}
		int[][] map = new int[15][20];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j]=Integer.parseInt(matrix[i][j]);
			}
		}
		System.out.println(Arrays.deepToString(matrix));
		System.out.println(Arrays.deepToString(map));	*/
		
	}
	

	public static void addMap(String name, String map) throws SQLException {
		PreparedStatement statement = connection.prepareStatement("INSERT INTO towerdefence (name, content) VALUES (?, ?)");
		statement.setString(1, name);
		statement.setString(2, map);
		statement.execute();
	}
	
	public static void deleteMaps(int[] ids) throws SQLException{
		for(int id: ids){
			PreparedStatement statement = connection.prepareStatement("DELETE FROM towerdefence WHERE id=?");
			statement.setInt(1, id);	
			statement.execute();
		}
	}
	
	public void updateMap(Map map) throws SQLException{
		String s = "UPDATE `if14_towerdefence`.`towerdefence` SET `name` = ?, `content` = ? WHERE `towerdefence`.`id` = ?";
		PreparedStatement statement = connection.prepareStatement(s);
		statement.setString(1, map.getName());
		statement.setString(2, map.getContent());
		statement.setInt(3, map.getId());
		statement.execute();
	}
	
	public List<Map> getMaps() throws SQLException {
		String sort_string_for_MySQL = getSortStringForMySQL();
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM `towerdefence`");
		List<Map> maps = new ArrayList<>();
		while (result.next()) {
			maps.add(new Map(result.getInt("id"), result.getString("name"),
					result.getString("content"), result.getString("date")));
		}
		return maps;
	}
	
	private String getSortStringForMySQL() {
		if(sort == 0){
			return "ORDER BY name";
		} else if(sort == 1){
			return "ORDER BY content";
		} else if(sort == 2){
			return "ORDER BY date";
		}
		return null;
	}

	public JTable getTable(List<Map> maps) throws SQLException{
		return new JTable(new MapsTableModel(maps));		
	}
	
	public void setSort(int sort){
		this.sort = sort;
	}
	

	public static void main(String[] args) throws SQLException{
		Data ins = new Data();	
	}
	
	public Map getMap(int map_id) throws SQLException {
		Map map = new Map(0, null, null, "2015-05-12 23:25:44.0");
		Statement statement = connection.createStatement();
		String s = "SELECT * FROM towerdefence WHERE id=5";
		ResultSet result = statement.executeQuery(s);
		return map;
	}

}
