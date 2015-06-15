import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SearchMovie extends JFrame {
	int index;

	JLabel label_intro = new JLabel("검색 값을 입력해주세요.");
	JButton btn_search = new JButton("검색");
	JPanel pan_north = new JPanel(new GridLayout(3, 1));
	JPanel pan_north2 = new JPanel(new GridLayout(1, 2));
	JPanel pan_north3 = new JPanel(new GridLayout(1, 4));
	JPanel pan_main = new JPanel(new GridLayout());
	JTextField input_search = new JTextField();

	JRadioButton rbtn_title = new JRadioButton("제목");
	JRadioButton rbtn_time = new JRadioButton("시간");
	JRadioButton rbtn_place = new JRadioButton("상영관");
	JRadioButton rbtn_seat = new JRadioButton("잔여 좌석");

	ButtonGroup bg = new ButtonGroup();
	DefaultTableModel model;

	JTable table;

	String[] col = { "영화제목", "시간", "상영관", "잔여좌석" };
	String[][] row;

	SearchMovie(final ArrayList<Movie> movie_list, final User user) {

		model = new DefaultTableModel(row, col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		TableRowSorter sorter = new TableRowSorter(table.getModel());
		table.setRowSorter(sorter);

		setTitle("영화 검색");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		label_intro.setHorizontalAlignment(getWidth());

		pan_north.add(label_intro);
		pan_north2.add(input_search);
		pan_north2.add(btn_search);
		pan_north.add(pan_north2);
		bg.add(rbtn_title);
		bg.add(rbtn_time);
		bg.add(rbtn_place);
		bg.add(rbtn_seat);

		rbtn_title.setSelected(true);

		pan_north3.add(rbtn_title);
		pan_north3.add(rbtn_time);
		pan_north3.add(rbtn_place);
		pan_north3.add(rbtn_seat);
		pan_north.add(pan_north3);

		add(pan_north, "North");
		add(new JScrollPane(table), "Center");

		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				System.out.println(index);
				if (e.getClickCount() == 2) {
					Movie temp = movie_list.get(index);
					final TicketManager ticketManager = new TicketManager(temp,
							user);
					ticketManager.setVisible(true);
					dispose();

					ticketManager.btn_buy
							.addActionListener(new ActionListener() {

								@Override
								public void actionPerformed(ActionEvent e) {
									// TODO Auto-generated method stub
									ticketManager.dispose();
								}
							});
				}

			}
		});

		btn_search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String search_value = input_search.getText();

				// jtable 초기화
				model.setNumRows(0);
				if (rbtn_title.isSelected()) {
					for (int i = 0; i < movie_list.size(); i++) {
						Movie temp = movie_list.get(i);

						if (temp.getTitle().contains(search_value)) {
							String title = temp.getTitle();
							String hour = temp.getStringHour();
							String min = temp.getStringMin();
							int place = temp.getPlace();

							model.addRow(new String[] {
									title,
									hour + "시 " + min + "분",
									String.valueOf(place),
									String.valueOf(temp.getSeats().getTotal()
											- temp.set.size()) });
						}
					}

				} else if (rbtn_time.isSelected()) {
					for (int i = 0; i < movie_list.size(); i++) {
						Movie temp = movie_list.get(i);
						try {
							if (temp.getHour() >= Integer
									.parseInt(search_value)) {
								String title = temp.getTitle();
								String hour = temp.getStringHour();
								String min = temp.getStringMin();
								int place = temp.getPlace();

								model.addRow(new String[] {
										title,
										hour + "시 " + min + "분",
										String.valueOf(place),
										String.valueOf(temp.getSeats()
												.getTotal() - temp.set.size()) });
							}
						} catch (Exception ex) {
							System.out.println(ex);
						}
					}

				} else if (rbtn_place.isSelected()) {
					for (int i = 0; i < movie_list.size(); i++) {
						Movie temp = movie_list.get(i);
						try {
							if (temp.getPlace() == Integer
									.parseInt(search_value)) {
								String title = temp.getTitle();
								String hour = temp.getStringHour();
								String min = temp.getStringMin();
								int place = temp.getPlace();

								model.addRow(new String[] {
										title,
										hour + "시 " + min + "분",
										String.valueOf(place),
										String.valueOf(temp.getSeats()
												.getTotal() - temp.set.size()) });
							}
						} catch (Exception ex) {
							System.out.println(ex);
						}
					}
				} else if (rbtn_seat.isSelected()) {

					for (int i = 0; i < movie_list.size(); i++) {
						try {
							Movie temp = movie_list.get(i);
							Seat temp_seat = temp.getSeats();

							if (temp_seat.getTotal() - temp.set.size() >= Integer
									.parseInt(search_value)) {
								String title = temp.getTitle();
								String hour = temp.getStringHour();
								String min = temp.getStringMin();
								int place = temp.getPlace();

								model.addRow(new String[] {
										title,
										hour + "시 " + min + "분",
										String.valueOf(place),
										String.valueOf(temp.getSeats()
												.getTotal() - temp.set.size()) });
							}
						} catch (Exception ex) {
							System.out.println(ex);
						}
					}

				}

			}
		});

		setSize(500, 400);
		setVisible(true);
	}
}