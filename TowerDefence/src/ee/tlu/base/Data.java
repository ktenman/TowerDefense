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
import java.util.Scanner;



import static ee.tlu.base.Config.*;
public class Data {
	
	static Connection connection;
	Scanner sc = new Scanner(System.in);
	private String name, content;
	private int id;
	private int count;
	private int[] ids;
	
	public Data() throws SQLException {
		
		connection = DriverManager.getConnection(address);
	/*	System.out.println("Sisesta nimi: ");
		name = sc.nextLine();
		System.out.println("Sisesta kaart: ");
		content = sc.nextLine();
		addMap(name, content);
		System.out.println("Sisesta mitu id tahad kustutada: ");
		count = sc.nextInt();
		ids = new int[count];
		for (int i = 0; i < ids.length; i++) {
			System.out.printf("Sisesta id %d/%d: ", i+1, ids.length);
			ids[i] = sc.nextInt();	
		}
		deleteMaps(ids);
		*/

		////
	/*	System.out.println("Sisu muutmine: ");
		System.out.println("Sisesta nimi: ");
		name = sc.nextLine();
		System.out.println("Sisesta kaart: ");
		content = sc.nextLine();
		System.out.println("Sisesta id: ");
		id = sc.nextInt();
		updateMap(name, content, id);*/
		
		for (Map map: getMaps()) {
			System.out.println(map.toString());
		}
		List < Map > maps = getMaps();
		System.out.println(maps.get(0).getContent());
		String koma, mapString = "";
		for (int i = 0; i < maps.get(0).getContent().length(); i++) {
			koma = "";
			if ((i + 1) % 20 == 0) {
				koma = ",";
			}
			String s = maps.get(0).getContent().charAt(i) + " " + koma;
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
		System.out.println(Arrays.deepToString(map));	
	}
	
	private static void addMap(String name, String map) throws SQLException {
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
	
	public void updateMap(String name, String content, int id) throws SQLException{
		String s = "UPDATE `if14_towerdefence`.`towerdefence` SET `name` = ?, `content` = ? WHERE `towerdefence`.`id` = ?";
		PreparedStatement statement = connection.prepareStatement(s);
		statement.setString(1, name);
		statement.setString(2, content);
		statement.setInt(3, id);
		statement.execute();
	}
	
	public List<Map> getMaps() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("SELECT * FROM `towerdefence`");
		List<Map> maps = new ArrayList<>();
		while (result.next()) {
			maps.add(new Map(result.getInt("id"), result.getString("name"),
					result.getString("content"), result.getString("date")));
		}
		return maps;
	}
	

	public static void main(String[] args) throws SQLException{
		Data ins = new Data();	
	}

}
