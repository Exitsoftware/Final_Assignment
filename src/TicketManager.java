import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TicketManager extends JFrame{
	
	JLabel label_title = new JLabel();
	JLabel label_time = new JLabel();
	JLabel label_place = new JLabel();

	
	TicketManager(Movie movie){
		
		setTitle("티켓팅");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		Seat s = movie.getSeats();
		int[][] seats = s.getSeats();
		
		label_title.setText(movie.getTitle());
		label_time.setText("시간 : " + movie.getStringHour() +"시 " + movie.getStringMin() + "분 ");
		label_place.setText(String.valueOf(movie.getPlace()) + " 상영관");
		
		JPanel pan_btn = new JPanel(new FlowLayout());
		pan_btn.add(label_title);
		pan_btn.add(label_time);
		pan_btn.add(label_place);
		
		add(pan_btn, "North");
		int count = 0;
		JPanel pan = new JPanel(new FlowLayout());
		for(int i = 0; i < s.getX(); i++){
			for(int j = 0; j < s.getY(); j++){
				JButton temp = new JButton();
				
				temp.setText(String.valueOf(++count));
				if(count == 6){
					temp.setEnabled(false);
				}
				pan.add(temp);
			}
		}
		add(pan, "Center");
	}
}
