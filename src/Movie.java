import java.io.Serializable;
import java.util.HashSet;


public class Movie implements Serializable{
	
	HashSet<String> set;
	
	String title;
	int hour;
	int min;
	int place;
	Seat seats;
	
	Movie(String title, int hour, int min, int place){
		this.title = title;
		this.hour = hour;
		this.min = min;
		this.place = place;
		this.seats = new Seat(this.place);
		this.set = new HashSet<String>();
	}
	
	public String getTitle(){
		return title;
	}
	public String getStringHour(){
		return String.valueOf(hour);
	}
	public int getHour(){
		return hour;
	}
	public String getStringMin(){
		return String.valueOf(min);
	}
	public int getMin(){
		return min;
	}
	public int getPlace(){
		return place;
	}
	public Seat getSeats(){
		return seats;
	}
	
	public HashSet<String> getSet(){
		return set;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public void setHour(int hour){
		this.hour = hour;
	}
	public void setMin(int min){
		this.min = min;
	}
	public void setHour_String(String hour){
		this.hour = Integer.parseInt(hour);
	}
	public void setMin_String(String min){
		this.min = Integer.parseInt(min);
	}
	public void setPlace(int place){
		this.place = place;
	}
	public void setSeats(Seat seats){
		this.seats = seats;
	}
	public void setSet(HashSet<String> set){
		this.set = set;
	}
	
	public String toString(){
		return "제목 : " + getTitle() + " 시간 : " + getStringHour() + "시 " + getStringMin() + "분" + " 상영관 : " + getPlace();
	}
	
}
