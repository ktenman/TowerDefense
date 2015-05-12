package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import data.Tile;
import data.TileGrid;

public class Leveler {
	static java.sql.Connection connection;
	
	public static void saveMap(String mapName, TileGrid grid) throws SQLException{
		String mapData = "";
		for(int i = 0; i < 20 ; i++){
			for(int j=0; j < 15; j++){
				mapData += getTileId(grid.GetTile(i, j));
			}
		}
		 
		 connection = DriverManager.getConnection("jdbc:mysql://localhost/if14_hardo_j?user=if14&password=ifikas2014");
		 addMap(mapName, mapData);
		/*
		try {
			File file = new File(mapName);
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(mapData);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public static String getTileId(Tile t){
		String ID ="E";
		switch(t.getType()){
		case Grass:
			ID ="0";
			break;
		case Dirt:
			ID ="1";
			break;
		case Water:
			ID ="2";
			break;
		/*case NULL:
			ID = "3";
			break;
			*/
		}
		return ID;
	}
	public static void addMap(String mapName,String mapData) {
		System.out.println(mapData);
		System.out.println(mapName);
		try {
			java.sql.PreparedStatement statement = connection.prepareStatement("INSERT INTO TD_map (nimi, map) VALUES (?, ?)");
			statement.setString(1, mapName);
			statement.setString(2, mapData);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
