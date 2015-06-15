import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;


public class ChangePW extends JFrame{
	JLabel label_now = new JLabel("현재 비밀번호 입력");
	JLabel label_new = new JLabel("변경 비밀번호 입력");
	JLabel label_new_re = new JLabel("비밀번호 재입력");
	JLabel label_noti = new JLabel("");
	
	JPasswordField input_now = new JPasswordField();
	JPasswordField input_new = new JPasswordField();
	JPasswordField input_new_re = new JPasswordField();
	
	JPanel pan_main = new JPanel(new GridLayout(3,2));
	JPanel pan_south = new JPanel(new GridLayout(2,1));
	
	JButton btn_submit = new JButton("확인");
	User user;
	
	ChangePW(User u){
		this.user = u;
		setTitle("비밀번호 변경");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		label_now.setHorizontalAlignment(JLabel.CENTER);
		label_new.setHorizontalAlignment(JLabel.CENTER);
		label_new_re.setHorizontalAlignment(JLabel.CENTER);
		label_noti.setHorizontalAlignment(JLabel.CENTER);
		
		pan_main.add(label_now);
		pan_main.add(input_now);
		pan_main.add(label_new);
		pan_main.add(input_new);
		pan_main.add(label_new_re);
		pan_main.add(input_new_re);
		
		pan_south.add(label_noti);
		pan_south.add(btn_submit);
		
		add(pan_main, "Center");
		add(pan_south, "South");
		
		btn_submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pw_now = input_now.getText();
				String pw_new = input_new.getText();
				String pw_new_re = input_new_re.getText();
				
				if(pw_now.equals(user.getPw())){
					if(pw_new.equals(pw_new_re)){
						user.setPw(pw_new);
						dispose();
					}
					else{
						label_noti.setText("새로운 비밀번호가 일치하지 않습니다.");
					}
				}
				else{
					label_noti.setText("기존 비밀번호가 일치하지 않습니다.");
				}
				
			}
		});
		
		setSize(300,200);
		setVisible(true);
	}
}
