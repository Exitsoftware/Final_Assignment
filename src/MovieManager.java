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
	
	ArrayList<Movie> movie_list = new ArrayList<Movie>();
	HashMap<String, User> user_set = new HashMap<String, User>();
	
	Scanner s = new Scanner(System.in);
	String[] titles;
	JList listview = new JList();
	
	JPanel pan_btn = new JPanel(new GridLayout(4, 1));
	
	JButton btn_AddMoive = new JButton("영화 추가");
	JButton btn_RemoveMovie = new JButton("영화 삭제");
	JButton btn_SearchMovie = new JButton("영화 검색");
	JButton btn_EditProfile = new JButton("내 정보 수정");

	DefaultTableModel model;
	
	MovieManager(final User user) {
		
		load();
		
		setTitle("MovieManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		String[][] row = new String[movie_list.size()][4];
		String[] col = {"영화제목", "시간", "상영관", "잔여좌석"};
		
		for(int i = 0; i < movie_list.size(); i++){
			Movie temp = movie_list.get(i);
			String str_hour = temp.getStringHour();
			String str_min = temp.getStringMin();
			row[i][0] = temp.getTitle();
			row[i][1] = str_hour + "시 " + str_min + "분";
			row[i][2] = String.valueOf(temp.getPlace());
			row[i][3] = String.valueOf(temp.getSeats().getTotal());
			
		}
		
		model = new DefaultTableModel(row,col);
		
		JTable table = new JTable(model);
		
		
		JLabel label_id = new JLabel("안녕하세요 " + user.getName() + "님");
		label_id.setHorizontalAlignment(getWidth());
		add(label_id, "North");

	
		pan_btn.add(btn_AddMoive);
		pan_btn.add(btn_RemoveMovie);
		pan_btn.add(btn_SearchMovie);
		pan_btn.add(btn_EditProfile);
		
		add(pan_btn, "East");

//		JScrollPane scroll = new JScrollPane(listview);
//		add(scroll, "Center")
		
		add(new JScrollPane(table),"Center");

		add(label_id, "North");
		
		
		
		
		
		
		
		
		
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
				user_load();
				EditProfile edit_profile = new EditProfile(user);
				
			}
		});
		
		btn_SearchMovie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				UserManager UM = new UserManager();
				
			}
		});
		
		listview.addMouseListener(new MouseListener() {

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
				index = listview.getSelectedIndex();
				if (e.getClickCount() == 2) {
					System.out.println(listview.getSelectedValue());
					Movie temp = movie_list.get(index);
					TicketManager ticketManager = new TicketManager(temp);
					ticketManager.setVisible(true);
				}
			}
		});

		setSize(300, 300);
		setVisible(true);
	}

	
	public void refresh(){
		load();
		user_load();
		
		
	}
	
	public void user_load(){
		try {
			FileInputStream fis = new FileInputStream("Movielist.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			user_set = (HashMap<String, User>) ois.readObject();


			fis.close();
			ois.close();


		} catch (Exception ex) {
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

			listview.setListData(titles);
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

		} catch (Exception ex) {

		}
	}

	public void remove() {
		movie_list.remove(index);
		save();
		load();
	}

}
