import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame{
	
	JLabel label_id = new JLabel();
	JLabel label_pw = new JLabel();
	JTextField input_id = new JTextField();
	JPasswordField input_pw = new JPasswordField();
	JButton btn_enter = new JButton();
	JButton btn_find = new JButton("비밀번호 찾기");
	JPanel pan_input = new JPanel(new GridLayout(2,2));
	JPanel pan = new JPanel(new GridLayout(2,1));
	JPanel pan_btn = new JPanel(new GridLayout(1,2));
	JLabel label_noti = new JLabel();
	HashMap<String, User> user_set = new HashMap<String, User>();
	
	String id;
	String pw;
	
	Login(){
		
		load();
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(2,1));
		
		label_id.setText("ID");
		label_id.setHorizontalAlignment(getWidth());
		label_pw.setText("PW");
		label_pw.setHorizontalAlignment(getWidth());
		label_noti.setText("ID와 PW를 입력해주세요.");
		label_noti.setHorizontalAlignment(getWidth());
		
		pan_input.add(label_id);
		pan_input.add(input_id);
		pan_input.add(label_pw);
		pan_input.add(input_pw);
		
		add(pan_input);
		btn_enter.setText("Login");
		pan.add(label_noti);
		pan_btn.add(btn_find);
		pan_btn.add(btn_enter);		
		pan.add(pan_btn);
		add(pan);
		
		// 로그인 버튼 클릭
		btn_enter.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
				id = input_id.getText();
				pw = input_pw.getText();
				
				set_login(id, pw);
				
			}
		});
		
		// 찾기 버튼 클릭
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				load();
				Find findview = new Find(user_set);
				
			}
		});
		
		
		setSize(400,120);
		setVisible(true);
	}
	
	public void save(){
		try{
			FileOutputStream fos = new FileOutputStream("UserSet.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(user_set);
			oos.flush();
			
			
			fos.close();
			oos.close();
			
			load();
		}
		catch(Exception ex){
			
		}
	}
	public void load(){
		try{
			FileInputStream fis = new FileInputStream("UserSet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			user_set = (HashMap<String, User>) ois.readObject();
			
			Iterator<String> it = user_set.keySet().iterator();
			while(it.hasNext()){
				String temp = it.next();
				System.out.println(temp);
			}
			fis.close();
			ois.close();
			
		}
		catch(Exception ex){
			
		}
	}
	public void log_list(String msg){
		System.out.println(msg+"\n\n");
		Iterator<String> it = user_set.keySet().iterator();
		while(it.hasNext()){
			String temp = it.next();
			System.out.println(temp);
		}
	}
	public void set_login(String id, String pw){
		
		if(user_set.keySet().contains(id)){
			if(user_set.get(id).getPw().equals(pw)){
				label_noti.setText("Success");
				MovieManager MM = new MovieManager(user_set.get(id));
				dispose();
			}
			else{
				System.out.println("Wrong Password");
				label_noti.setText("Wrong Password");
			}
		}
		else{
			// 아이디가 없을 때
			
			label_noti.setText("Create Id");
			Signup signup = new Signup(id, pw, user_set);
		
			
		}
	}
	
	public static void main(String[] args) {
		Login login = new Login();
		
	}
}
