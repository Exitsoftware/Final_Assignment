import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditProfile extends JFrame {

	HashMap<String, User> user_set = new HashMap<String, User>();

	JLabel label_name = new JLabel("이름");
	JLabel label_age = new JLabel("나이");
	JLabel label_email = new JLabel("E-mail");
	JLabel label_grade = new JLabel("등급");

	JTextField input_name = new JTextField();
	JTextField input_age = new JTextField();
	JTextField input_email = new JTextField();
	JLabel real_grade = new JLabel();

	JPanel pan_main = new JPanel(new GridLayout(5, 2));
	JPanel pan_btn = new JPanel(new GridLayout(1, 2));

	JButton btn_ok = new JButton("확인");
	JButton btn_change_pw = new JButton("비밀번호 번경");

	EditProfile(final User user) {

		load();

		setTitle("프로필 수정");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		label_name.setHorizontalAlignment(getWidth());
		label_age.setHorizontalAlignment(getWidth());
		label_email.setHorizontalAlignment(getWidth());
		label_grade.setHorizontalAlignment(getWidth());
		real_grade.setHorizontalAlignment(getWidth());

		input_name.setText(user.getName());
		input_age.setText(String.valueOf(user.getAge()));
		input_email.setText(user.getEmail());
		real_grade.setText(user.getGrade());

		pan_main.add(label_name);
		pan_main.add(input_name);
		pan_main.add(label_age);
		pan_main.add(input_age);
		pan_main.add(label_email);
		pan_main.add(input_email);
		pan_main.add(label_grade);
		pan_main.add(real_grade);

		add(pan_main, "Center");

		pan_btn.add(btn_ok);
		pan_btn.add(btn_change_pw);

		add(pan_btn, "South");

		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String name = input_name.getText();
				int age = Integer.parseInt(input_age.getText());
				String email = input_email.getText();
				
				user_set.get(user.getId()).setName(name);
				user_set.get(user.getId()).setAge(age);
				user_set.get(user.getId()).setEmail(email);
				
				save();
				
				dispose();
			}
		});

		btn_change_pw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		setSize(300, 300);
		setVisible(true);
	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("UserSet.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(user_set);
			oos.flush();

			oos.close();
			fos.close();
			
			load();

		} catch (Exception ex) {
		}

	}

	public void load() {
		try {
			FileInputStream fis = new FileInputStream("UserSet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			user_set = (HashMap<String, User>) ois.readObject();
			
			ois.close();
			fis.close();
			
		} catch (Exception ex) {

		}

	}

}
