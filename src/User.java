import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class User implements Serializable{
	
	String id;
	String pw;
	String name;
	int age;
	int grade_index;
	String grade;
	String[] grades = {"D", "C", "B", "A"};
	String email;
	int sum;
	ArrayList<HashMap<String, String>> buy_list;
	
	
	User(String id, String pw, String name, int age, String email){
		
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.grade_index = 0;
		this.grade = grades[grade_index];
		this.email = email;
		this.sum = 0;
		this.buy_list = new ArrayList<HashMap<String, String>>();
		
	}
	public String getId(){
		return id;
	}
	public String getPw(){
		return pw;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String getGrade(){
		return grade;
	}
	public String getEmail(){
		return email;
	}
	public int getGrade_index(){
		return grade_index;
	}
	public int getSum(){
		return sum;
	}
	public ArrayList<HashMap<String, String>> getList(){
		return buy_list;
	}

	public void setPw(String pw){
		this.pw = pw;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setGrade(String grade){
		this.grade = grade;
	}
	public void setEmail(String email){
		this.email= email;
	}
	public void setSum(int sum){
		this.sum = sum;
	}
	public void setList(ArrayList<HashMap<String, String>> list){
		this.buy_list = list;
	}
	public void addSum(int add){
		this.sum += add;
	}
	public void minusSum(int num){
		this.sum -= num;
	}
	public void gradeUp(){
		if(this.grade_index < 3){
			this.grade_index++;
		}
		this.grade = grades[grade_index];
	}
	public void gradeDown(){
		if(this.grade_index > 0){
			this.grade_index--;
		}
		this.grade = grades[grade_index];
	}
	
	
	public String toString(){
		return getName() + " " + String.valueOf(getAge()) + " " + getGrade() + " " + getEmail();
	}
}

