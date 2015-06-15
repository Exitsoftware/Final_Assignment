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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Signup extends JFrame {
	// ArrayList<User> user_list = new ArrayList<User>();
	HashMap<String, User> user_set = new HashMap<String, User>();

	JTextField input_name;
	JTextField input_age;
	JTextField input_email;

	String id;
	String pw;

	Signup(final String id, final String pw, final HashMap<String, User> set) {

		this.id = id;
		this.pw = pw;
		this.user_set = set;

		load();
		setTitle("회원가입");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		JLabel label_intro = new JLabel("회원정보를 입력해 주시길 바랍니다.");
		label_intro.setHorizontalAlignment(getWidth());
		add(label_intro, "North");

		JPanel main_pan = new JPanel(new GridLayout(3, 2));

		JLabel label_name = new JLabel("이름");
		JLabel label_age = new JLabel("나이");
		JLabel label_email = new JLabel("이메일");

		label_name.setHorizontalAlignment(getWidth());
		label_age.setHorizontalAlignment(getWidth());
		label_email.setHorizontalAlignment(getWidth());

		input_name = new JTextField();
		input_age = new JTextField();
		input_email = new JTextField();

		main_pan.add(label_name);
		main_pan.add(input_name);
		main_pan.add(label_age);
		main_pan.add(input_age);
		main_pan.add(label_email);
		main_pan.add(input_email);
		add(main_pan, "Center");

		JPanel pan_btn = new JPanel(new GridLayout(1, 2));
		JButton btn_ok = new JButton("OK");
		JButton btn_cancle = new JButton("Cancle");
		pan_btn.add(btn_ok);
		pan_btn.add(btn_cancle);

		add(pan_btn, "South");

		btn_ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = input_name.getText();
					int age = Integer.valueOf(input_age.getText());
					String email = input_email.getText();

					user_set.put(id, new User(id, pw, name, age, email));

					save();

					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "입력 데이터 값이 잘못되었습니다.",
							"데이터 입력 오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btn_cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(400, 300);
		setVisible(true);
	}

	public void load() {
		try {

			FileInputStream fis = new FileInputStream("UserSet.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);

			user_set = (HashMap<String, User>) ois.readObject();

			fis.close();
			ois.close();

		} catch (Exception ex) {

		}

	}

	public void save() {
		try {
			FileOutputStream fos = new FileOutputStream("UserSet.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(user_set);
			oos.flush();

			fos.close();
			oos.close();
			// load();

		} catch (Exception ex) {

		}

	}

}
