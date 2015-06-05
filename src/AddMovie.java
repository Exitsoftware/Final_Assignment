import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMovie extends JFrame {
	AddMovie() {
		setTitle("영화 추가");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(4, 2));

		JLabel label_title = new JLabel();
		JLabel label_time = new JLabel();
		JLabel label_place = new JLabel();

		label_title.setText("영화 제목");
		label_time.setText("시간");
		label_place.setText("상영관");

		label_title.setHorizontalAlignment(getWidth());
		label_time.setHorizontalAlignment(getWidth());
		label_place.setHorizontalAlignment(getWidth());

		final JTextField input_title = new JTextField();
		final JTextField input_time = new JTextField();
		final JTextField input_place = new JTextField();

		add(label_title);
		add(input_title);
		add(label_time);
		add(input_time);
		add(label_place);
		add(input_place);

		JButton btn_ok = new JButton();
		JButton btn_cancle = new JButton();

		btn_ok.setText("OK");
		btn_cancle.setText("Cancle");
		btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					FileInputStream fis = new FileInputStream("Movielist.dat");
					ObjectInputStream ois = new ObjectInputStream(fis);

					ArrayList<Movie> movielist = (ArrayList) ois.readObject();

					fis.close();
					ois.close();

					String title = input_title.getText();
					String time = input_time.getText();
					int place = Integer.parseInt(input_place.getText());

					movielist.add(new Movie(title, time, place));

					FileOutputStream fos = new FileOutputStream("Movielist.dat");
					ObjectOutputStream oos = new ObjectOutputStream(fos);

					oos.writeObject(movielist);
					oos.reset();

					oos.close();
					fos.close();

					dispose();
				} catch (Exception ex) {
					try {
						String title = input_title.getText();
						String time = input_time.getText();
						int place = Integer.parseInt(input_place.getText());

						ArrayList<Movie> movielist = new ArrayList<Movie>();

						movielist.add(new Movie(title, time, place));

						FileOutputStream fos = new FileOutputStream(
								"Movielist.dat", true);
						ObjectOutputStream oos = new ObjectOutputStream(fos);

						oos.writeObject(movielist);
						oos.reset();

						oos.close();
						fos.close();

						dispose();
					} catch (Exception ex2) {
					}
				}

			}
		});
		btn_cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(btn_ok);
		add(btn_cancle);

		setSize(300, 300);
	}
}
