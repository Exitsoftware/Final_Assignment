import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MovieManager extends JFrame {
	int index;
	boolean is_manager = false;

	ArrayList<Movie> movie_list = new ArrayList<Movie>();
	HashMap<String, User> user_set = new HashMap<String, User>();

	Scanner s = new Scanner(System.in);
	String[] titles;
	JList listview = new JList();

	JPanel pan_btn = new JPanel(new GridLayout(7, 1));
	JLabel label_id;

	JButton btn_AddMoive = new JButton("영화 추가");
	JButton btn_RemoveMovie = new JButton("영화 삭제");
	JButton btn_SearchMovie = new JButton("영화 검색");
	JButton btn_EditProfile = new JButton("내 정보 수정");
	JButton btn_ViewUserManager = new JButton("유저 관리");
	JButton btn_ViewBuy = new JButton("예매 내역");
	JButton btn_refresh = new JButton("새로고침");
	final JTable table;

	DefaultTableModel model;
	User user;

	MovieManager(User u) {
		user = u;

		if (user.getId().equals("manager") || user.getId().equals("admin")) {
			is_manager = true;
		}

		load();
		
		String[][] row = new String[movie_list.size()][4];
		String[] col = { "영화제목", "시간", "상영관", "잔여좌석" };

		for (int i = 0; i < movie_list.size(); i++) {
			Movie temp = movie_list.get(i);
			String str_hour = temp.getStringHour();
			String str_min = temp.getStringMin();
			row[i][0] = temp.getTitle();
			row[i][1] = str_hour + "시 " + str_min + "분";
			row[i][2] = String.valueOf(temp.getPlace());
			row[i][3] = String.valueOf(temp.getSeats().getTotal()  - temp.set.size());
		}
		
		model = new DefaultTableModel(row, col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		setTitle("MovieManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		
		label_id = new JLabel("안녕하세요 " + user.getName() + "님");
		label_id.setHorizontalAlignment(getWidth());
		
		if (!is_manager) {
			pan_btn.add(btn_SearchMovie);
			pan_btn.add(btn_EditProfile);
			pan_btn.add(btn_ViewBuy);
			pan_btn.add(btn_refresh);
		}
		else{
			pan_btn.add(btn_AddMoive);
			pan_btn.add(btn_RemoveMovie);
			pan_btn.add(btn_SearchMovie);
			pan_btn.add(btn_EditProfile);
			pan_btn.add(btn_ViewUserManager);
			pan_btn.add(btn_ViewBuy);
			pan_btn.add(btn_refresh);
		}
		
		table = new JTable(model);
		add(new JScrollPane(table), "Center");
		add(pan_btn, "East");
		add(label_id, "North");
		
		btn_ViewBuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewBuyList viewbuylist = new ViewBuyList(user);
			}
		});
		
		btn_AddMoive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final AddMovie add = new AddMovie();
				add.setVisible(true);

				add.btn_ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							FileInputStream fis = new FileInputStream(
									"Movielist.dat");
							ObjectInputStream ois = new ObjectInputStream(fis);

							movie_list = (ArrayList) ois.readObject();

							fis.close();
							ois.close();
							
							String title = add.input_title.getText();
							String time = add.input_time.getText();
							int hour = Integer.parseInt((String) add.combo_hour
									.getSelectedItem());
							int min = Integer.parseInt((String) add.combo_min
									.getSelectedItem());
							int place = Integer
									.parseInt((String) add.combo_place
											.getSelectedItem());

							
							movie_list.add(new Movie(title, hour, min, place));

							save();
							add.dispose();
							
							Movie temp = movie_list.get(movie_list.size() - 1);

							model.addRow(new String[] { title,
									hour + "시 " + min + "분",
									String.valueOf(place),
									String.valueOf(temp.getSeats().getTotal()) });
							
							
							table.repaint();
							load();

						} catch (Exception ex) {
							try {
								String title = add.input_title.getText();
								int hour = Integer
										.parseInt((String) add.combo_hour
												.getSelectedItem());
								int min = Integer
										.parseInt((String) add.combo_min
												.getSelectedItem());
								int place = Integer
										.parseInt((String) add.combo_place
												.getSelectedItem());

								movie_list.add(new Movie(title, hour, min,
										place));

								save();
								add.dispose();
								load();

							} catch (Exception ex2) {
							}
						}

					}
				});
			}
		});

		btn_RemoveMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});

		btn_EditProfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// user_load();
				final EditProfile edit_profile = new EditProfile(user);

				edit_profile.btn_ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						String name = edit_profile.input_name.getText();
						int age = Integer.parseInt(edit_profile.input_age
								.getText());
						String email = edit_profile.input_email.getText();


						edit_profile.user_set.get(user.getId()).setName(name);
						edit_profile.user_set.get(user.getId()).setAge(age);
						edit_profile.user_set.get(user.getId()).setEmail(email);

						edit_profile.save();
						edit_profile.dispose();

						user_load();

					}
				});

			}
		});

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
				// TODO Auto-generated method stub
				index = table.getSelectedRow();
				System.out.println(index);
				if (e.getClickCount() == 2) {
					
					Movie temp = movie_list.get(index);
					final TicketManager ticketManager = new TicketManager(temp, user);
					ticketManager.setVisible(true);
					ticketManager.btn_buy.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							ticketManager.save();
							ticketManager.dispose();
							
							save();
							load();
						}
					});
				}

			}
		});

		btn_ViewUserManager.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				UserManager UM = new UserManager();

			}
		});

		btn_SearchMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchMovie searchmovie = new SearchMovie(movie_list, user);
			}
		});
		
		btn_refresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				refresh();
			}
		});
		setSize(800, 300);
		setVisible(true);
	}

	public void refresh() {
		load();
		user_load();
		

	}

	public void user_load() {
		try {
			FileInputStream fis = new FileInputStream("UserSet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			user_set = (HashMap<String, User>) ois.readObject();

			user = user_set.get(user.getId());

			fis.close();
			ois.close();

			System.out.println(user.getName());
			label_id.setText("안녕하세요 " + user.getName() + "님");
			label_id.repaint();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("Movielist.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			movie_list = (ArrayList) ois.readObject();
			titles = new String[movie_list.size()];

			for (int i = 0; i < movie_list.size(); i++) {
				Movie temp = movie_list.get(i);
				titles[i] = temp.getTitle();
				System.out.println(temp.toString());
			}
			

			fis.close();
			ois.close();
			
			model.fireTableDataChanged();
			table.updateUI();
			System.out.println("refesh");

		} catch (Exception ex) {
		}
	}

	public void save() {
		try {

			FileOutputStream fos = new FileOutputStream("Movielist.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(movie_list);
			oos.reset();

			oos.close();
			fos.close();
			load();
			
		} catch (Exception ex) {

		}
	}

	public void remove() {
		movie_list.remove(index);
		model.removeRow(index);
		save();
		load();
	}

}