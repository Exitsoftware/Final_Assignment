import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicketManager extends JFrame {

	JLabel label_title = new JLabel();
	JLabel label_time = new JLabel();
	JLabel label_place = new JLabel();
	JLabel label_screen = new JLabel("스크린");
	JButton btn_buy = new JButton("예매");

	JPanel pan_north = new JPanel(new GridLayout(2, 1));
	JPanel pan_intro = new JPanel(new FlowLayout());
	JPanel pan_sub = new JPanel(new GridLayout(4, 1));
	JPanel pan_main = new JPanel(new FlowLayout());

	HashMap<String, User> user_set = new HashMap<String, User>();

	Movie movie;
	User user;

	TicketManager(Movie m, User u) {

		this.movie = m;
		this.user = u;

		final HashSet<String> set = movie.getSet();

		setTitle("티켓팅");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		if (movie.getPlace() == 1) {
			setSize(720, 600);
		}
		else if (movie.getPlace() == 2){
			setSize(720,800);
		}
		else if (movie.getPlace() == 3){
			setSize(1120,620);
		}
		super.setResizable(false);
		
		Seat s = movie.getSeats();
		int[][] seats = s.getSeats();

		label_title.setText(movie.getTitle());
		label_time.setText("시간 : " + movie.getStringHour() + "시 "
				+ movie.getStringMin() + "분 ");
		label_place.setText(String.valueOf(movie.getPlace()) + " 상영관");
		label_screen.setHorizontalAlignment(JLabel.CENTER);

		pan_intro.add(label_title);
		pan_intro.add(label_time);
		pan_intro.add(label_place);

		pan_sub.add(btn_buy);
		pan_north.add(pan_intro);
		pan_north.add(label_screen);

		add(pan_north, "North");
		add(pan_sub, "East");

		int count = 0;
		int count2 = 0;
		for (int i = 0; i < s.getX(); i++) {
			for (int j = 0; j < s.getY(); j++) {
				final JButton temp = new JButton();

				temp.setText(String.valueOf(++count));

				if (set.contains(temp.getText())) {
					temp.setEnabled(false);
				} else {
					count2++;
				}

				temp.setBackground(Color.red);
				temp.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						HashMap<String, String> temp_map = new HashMap<String, String>();
						temp_map.put(movie.getTitle(), temp.getText());

						if (set.contains(temp.getText())) {
							set.remove(temp.getText());
							user.minusSum(1);
							user.buy_list.remove(temp_map);
							if (user.getSum() < 10
									&& user.getGrade().equals("C")) {
								user.gradeDown();
							} else if (user.getSum() < 30
									&& user.getGrade().equals("B")) {
								user.gradeDown();
							} else if (user.getSum() < 50
									&& user.getGrade().equals("A")) {
								user.gradeDown();
							}
							temp.setForeground(Color.RED);
						} else {
							set.add(temp.getText());
							user.addSum(1);
							if (user.getSum() >= 10
									&& user.getGrade_index() < 1) {
								user.gradeUp();
							} else if (user.getSum() >= 30
									&& user.getGrade_index() < 2) {
								user.gradeUp();
							} else if (user.getSum() >= 50
									&& user.getGrade_index() < 3) {
								user.gradeUp();
							}

							user.buy_list.add(temp_map);
							temp.setForeground(Color.BLUE);
						}

						System.out.println(temp.getText());
					}
				});
				pan_main.add(temp);
			}
		}
		add(pan_main, "Center");
	}

	public Movie getMovie() {
		return movie;
	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("UserSet.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			user_set.put(user.getId(), user);

			oos.writeObject(user_set);
			oos.flush();

			fos.close();
			oos.close();

			load();
		} catch (Exception ex) {

		}
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

}