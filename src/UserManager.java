import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class UserManager extends JFrame {
	HashMap<String, User> user_set = new HashMap<String, User>();
	DefaultTableModel model;
	
	UserManager() {
		// int count = user_set.size();
		load();
		add_user();
		setTitle("유저 관리");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		JTable table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(table.getModel());
		table.setRowSorter(sorter);
		add(new JScrollPane(table));
		setSize(1000, 800);
		setVisible(true);
	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("UserSet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			user_set = (HashMap<String, User>) ois.readObject();

			Iterator<String> it = user_set.keySet().iterator();
			while (it.hasNext()) {
				String temp = it.next();
				System.out.println(temp);
			}
			fis.close();
			ois.close();

		} catch (Exception ex) {

		}
	}
	public void add_user(){
		String[] col = { "ID", "PW", "이름", "나이", "Email", "등급", "누적 합계" };
		String[][] row = new String[user_set.size()][col.length];
		
		Iterator it = user_set.keySet().iterator();
		int i = 0;
		while (it.hasNext()) {

			User temp = user_set.get(it.next());
			row[i][0] = temp.getId();
			row[i][1] = temp.getPw();
			row[i][2] = temp.getName();
			row[i][3] = String.valueOf(temp.getAge());
			row[i][4] = temp.getEmail();
			row[i][5] = temp.getGrade();
			row[i][6] = String.valueOf(temp.getSum());
			
			i++;
		}
		model = new DefaultTableModel(row,col);
	}
}
