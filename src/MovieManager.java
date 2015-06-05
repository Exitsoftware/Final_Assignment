import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;

public class MovieManager extends JFrame {

	ArrayList<Movie> movie_list = new ArrayList<Movie>();
	Scanner s = new Scanner(System.in);

	MovieManager() {
		setTitle("MovieManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());

		String[] fuck = new String[30];
		for (int i = 0; i < 30; i++) {
			fuck[i] = String.valueOf(i + 1);
		}

		JButton btn_AddMoive = new JButton();
		btn_AddMoive.setText("영화 추가");
		add(btn_AddMoive);

		JButton btn_RemoveMovie = new JButton();
		btn_RemoveMovie.setText("영화 삭제");
		add(btn_RemoveMovie);

		JPanel pan = new JPanel(new BorderLayout());
		JList listview = new JList(fuck);
		add(pan);
		pan.add(listview, "Center");

		btn_AddMoive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMovie add = new AddMovie();
				add.setVisible(true);

			}
		});

		setSize(300, 300);
		setVisible(true);
	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("Movielist.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			
			
		} catch (Exception ex) {
		}
		
	}

	public static void main(String[] args) {
		MovieManager MM = new MovieManager();
	}
}
