package ee.tlu.base;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;



public class MapsTableModel implements TableModel {

	List<Map> maps;
	Data data;
	
	public MapsTableModel(List<Map> maps) throws SQLException{
		data = new Data();
		this.maps = maps;
	}
	
	
	@Override
	public void addTableModelListener(TableModelListener l) {}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int columnIndex) {
		if(columnIndex == 0){
			return "Name";
		} else if(columnIndex == 1){
			return "Content";
		} else if(columnIndex == 2){
			return "Date";
		}
		return null;
	}

	@Override
	public int getRowCount() {
		return maps.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Map map = maps.get(rowIndex);
		if(columnIndex == 0){
			return map.getName(); 
		} else if(columnIndex == 1){
			return map.getContent();
		} else if(columnIndex == 2){
			return map.prettyTime();
		}
		return null;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// return true on ainuke, mida on vaja, et tabelit muuta saaks
		if (columnIndex==1) {
			return true;
		}
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {}

	@Override
	// Käivitatakse, kui lathri muutmine on lõpetatud
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Map map = maps.get(rowIndex);
		if(columnIndex == 0){
			map.setName((String) aValue);
		} else if(columnIndex == 1){
			map.setContent((String) aValue);
		} else if(columnIndex == 2){
			map.setDate((Date) aValue);;
		}
		updateData(map);
	}
	
	private void updateData(Map map){
		try {
			data.updateMap(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

}
