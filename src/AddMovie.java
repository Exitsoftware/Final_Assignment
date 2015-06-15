import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddMovie extends JFrame {
	
	JButton btn_ok = new JButton();
	JButton btn_cancle = new JButton();
	JComboBox combo_hour = new JComboBox();
	JComboBox combo_min = new JComboBox();
	JComboBox combo_place = new JComboBox();
	
	
	final JTextField input_title = new JTextField();
	final JTextField input_time = new JTextField();
	final JTextField input_place = new JTextField();
	
	AddMovie() {
		
		
		String[] arr_hour = new String[24];
		for(int i = 0; i < 24; i++){
			arr_hour[i] = String.valueOf(i);
		}
		String[] arr_min = new String[60];
		for(int i = 0; i < 60; i++){
			arr_min[i] = String.valueOf(i);
		}
		String[] arr_place = {"1","2","3"};
		
		
		
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

		
		combo_hour = new JComboBox(arr_hour);
		combo_min = new JComboBox(arr_min);
		combo_place = new JComboBox(arr_place);
		
		add(label_title);
		add(input_title);
		
		add(label_time);
		JPanel pan_time = new JPanel(new GridLayout(1,2));
		pan_time.add(combo_hour);
		pan_time.add(combo_min);
		add(pan_time);
		
		
		add(label_place);
		add(combo_place);
		


		btn_ok.setText("OK");
		btn_cancle.setText("Cancle");
		
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
