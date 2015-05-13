package ee.tlu.base;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;


public class Gui extends JApplet implements MouseListener {
		
	List<Map> maps = new ArrayList<>();
	Data data = null;
	JScrollPane scroll_pane = null;
	JTable table = null;
	JPanel main_panel = new JPanel();
	JButton update_button = new JButton("Update");
	JButton delete_map_button = new JButton("Delete");
	JButton add_map_button = new JButton("Add");
	JButton edit_map_button = new JButton("Edit");
	JPanel south_panel = new JPanel(); 
	JPanel update_button_panel = new JPanel();
	JPanel delete_map_button_panel = new JPanel();
	JPanel add_map_button_panel = new JPanel();
	JPanel edit_map_button_panel = new JPanel();
	
	public void init(){
		setMyLayout();
		getTable();
		createTableView();
		setVisible(true);
	}
	
	private void getTable(){				
		try{
			data = new Data();
			maps = data.getMaps();
			table = data.getTable(maps);
			TableColumn column = null;
			for (int i = 0; i < 3; i++) {
			    column = table.getColumnModel().getColumn(i);
			    if (i == 1) {
			        column.setPreferredWidth(200); //third column is bigger
			    } else {
			        column.setPreferredWidth(50);
			    }
			}
			
			
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private void createTableView(){	
		// Tabeli "vaade"
		scroll_pane = new JScrollPane(table);
		main_panel.add(scroll_pane);
		setTableColumnWidth();
		table.getTableHeader().addMouseListener(this);
	}
	
	private void setTableColumnWidth() {
		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 1) {
		        column.setPreferredWidth(200); //teine column on suurem
		    } else {
		        column.setPreferredWidth(50);
		    }
		}
	}

	private void setMyLayout(){
		setSize(700, 600);
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, main_panel);
		add(BorderLayout.SOUTH, south_panel);
		update_button_panel.add(update_button);
		delete_map_button_panel.add(delete_map_button);
		add_map_button_panel.add(add_map_button);
		edit_map_button_panel.add(edit_map_button);
		south_panel.add(update_button_panel);
		south_panel.add(delete_map_button_panel);
		south_panel.add(add_map_button_panel);
		south_panel.add(edit_map_button_panel);
		update_button.addMouseListener(this);
		delete_map_button.addMouseListener(this);
		add_map_button.addMouseListener(this);
		edit_map_button.addMouseListener(this);

	}
	
	private void updateTable(){
		try {
			maps = data.getMaps();
			table = data.getTable(maps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    scroll_pane.getViewport().remove(table);
		scroll_pane.getViewport().add(table);
		setTableColumnWidth();
		// hiirekuular läheb header'is pärast tabeli uuesti loomist kaduma
		table.getTableHeader().addMouseListener(this);
	}
	
	private int[] returnSelectedRowsNumbers(){	
		return table.getSelectedRows();
	}
	
	private int[] getmapsIdsByRowNumbers(int[] selected_rows_ids){
		int maps_ids[] = new int[selected_rows_ids.length];
		for(int i=0; i<selected_rows_ids.length; i++){
			maps_ids[i] = maps.get(selected_rows_ids[i]).getId();
		}
		return maps_ids;
	}
	
	private void updateDatabaseManagerSortValue(int clicked_header_column_id){
		if(clicked_header_column_id == 0){
			data.setSort(0);
		} else if(clicked_header_column_id == 1){
			data.setSort(1);
		} else if(clicked_header_column_id == 2){
			data.setSort(2);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == update_button) {
			updateTable();
		} else if (e.getSource() == delete_map_button) {
			int selected_rows_ids[] = returnSelectedRowsNumbers();
			if (selected_rows_ids.length == 0) {
				JOptionPane.showMessageDialog(this, "No maps selected.");
			} else {
				try {
					int confirmation = JOptionPane.showConfirmDialog(this, "Sure?");
					if (confirmation == 0) {
						int[] maps_ids = getmapsIdsByRowNumbers(selected_rows_ids);
						for (int i : maps_ids) {
							System.out.println(i);
						}
						System.out.println(maps_ids);
						data.deleteMaps(maps_ids);
						updateTable();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} else if (e.getSource() == table.getTableHeader()) {
			int clicked_header_column_id = table.columnAtPoint(e.getPoint());
			updateDatabaseManagerSortValue(clicked_header_column_id);
			updateTable();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

}

