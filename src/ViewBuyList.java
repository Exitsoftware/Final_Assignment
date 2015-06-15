import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewBuyList extends JFrame {
	HashMap<String, User> user_set = new HashMap<String, User>();
	DefaultTableModel model;
	User user;

	ViewBuyList(User u) {

		this.user = u;
		set_table_data();
		setTitle("예매 내역");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(table.getModel());
		table.setRowSorter(sorter);
		
		add(new JScrollPane(table));
		setSize(500, 300);
		setVisible(true);
	}

	public void set_table_data() {
		String[] col = { "영화 제목", "좌석" };
		String[][] row = new String[user.buy_list.size()][col.length];

		for (int i = 0; i < user.buy_list.size(); i++) {
			HashMap<String, String> map = user.buy_list.get(i);

			Iterator it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				row[i][0] = key;
				row[i][1] = map.get(key);
			}
		}

		model = new DefaultTableModel(row, col);
	}

}