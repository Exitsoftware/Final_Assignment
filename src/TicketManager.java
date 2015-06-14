import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class TicketManager extends JFrame{
	
	JLabel label_title = new JLabel();
	JLabel label_time = new JLabel();
	JLabel label_place = new JLabel();
	JLabel label_screen = new JLabel("스크린");
	JButton btn_buy = new JButton("예매");
	
	JPanel pan_north = new JPanel(new GridLayout(2,1));
	JPanel pan_intro = new JPanel(new FlowLayout());
	JPanel pan_sub = new JPanel(new GridLayout(4,1));
	JPanel pan_main = new JPanel(new FlowLayout());
	
	
	Movie movie;
	TicketManager(Movie m){
		
		this.movie = m;
		final HashSet<String> set = movie.getSet();
		
		setTitle("티켓팅");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setSize(720,600);
		Seat s = movie.getSeats();
		int[][] seats = s.getSeats();
		
		label_title.setText(movie.getTitle());
		label_time.setText("시간 : " + movie.getStringHour() +"시 " + movie.getStringMin() + "분 ");
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
		for(int i = 0; i < s.getX(); i++){
			for(int j = 0; j < s.getY(); j++){
				final JButton temp = new JButton();
				
				temp.setText(String.valueOf(++count));
				
				if(set.contains(temp.getText())){
					temp.setEnabled(false);
				}
				else{
					count2++;
				}
				
				temp.setBackground(Color.red);
				temp.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
						if(set.contains(temp.getText())){
							set.remove(temp.getText());
							temp.setForeground(Color.RED);
						}
						else{
							set.add(temp.getText());
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
	
	public Movie getMovie(){
		return movie;
	}
	
}
