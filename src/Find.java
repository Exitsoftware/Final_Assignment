import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Find extends JFrame{
	HashMap<String, User> user_set = new HashMap<String, User>();
	
	JLabel label_id = new JLabel("ID");
	JLabel label_name = new JLabel("이름");
	JLabel label_email = new JLabel("E - mail");
	JLabel intro = new JLabel("회원님의 이름, 나이와 이메일을 입력해주세요.");
	JLabel status = new JLabel("");
	
	JTextField input_id = new JTextField();
	JTextField input_name = new JTextField();
	JTextField input_email = new JTextField();
	
	JPanel main_pan = new JPanel(new GridLayout(3,2));
	JPanel pan_btn = new JPanel(new GridLayout(2,1));
	
	JButton btn_find_pw = new JButton("비밀번호 찾기");
	
	Find(HashMap<String, User> set){
		
		this.user_set = set;
		
		setTitle("비밀번호 찾기");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());	
		
		intro.setHorizontalAlignment(getWidth());
		label_id.setHorizontalAlignment(getWidth());
		label_name.setHorizontalAlignment(getWidth());
		label_email.setHorizontalAlignment(getWidth());
		status.setHorizontalAlignment(getWidth());
		
		main_pan.add(label_id);
		main_pan.add(input_id);
		main_pan.add(label_name);
		main_pan.add(input_name);
		main_pan.add(label_email);
		main_pan.add(input_email);
		
		add(intro,"North");
		add(main_pan,"Center");
		
		pan_btn.add(status);
		pan_btn.add(btn_find_pw);
		add(pan_btn,"South");
		
		btn_find_pw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = input_id.getText();
				String name = input_name.getText();
				String email = input_email.getText();
				
				find_pw(id, name, email);
			}
		});
		
		
		
		setSize(300,300);
		setVisible(true);
	}
	public void find_pw(String id, String name, String email){
		if(user_set.keySet().contains(id)){
			User temp = user_set.get(id);
			
			if(name.equals(temp.getName()) && email.equals(temp.getEmail())){
				status.setText("회원님의 비밀번호는 " + temp.getPw() + " 입니다.");
			}
			else{
				status.setText("아이디와 정보가 일치하지 않습니다.");
			}
		}
		else{
			status.setText("일치하는 아이디가 없습니다.");
		}
	}
}
