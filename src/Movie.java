import java.io.Serializable;


public class Movie implements Serializable{
	String title;
	String time;
	int place;
	Seat seats;
	
	Movie(String title, String time, int place){
		this.title = title;
		this.time = time;
		this.place = place;
		this.seats = new Seat(this.place);
	}
	
	public String getTitle(){
		return title;
	}
	public String getTime(){
		return time;
	}
	public int getPlace(){
		return place;
	}
	public Seat getSeats(){
		return seats;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	public void setTime(String time){
		this.time = time;
	}
	public void setPlace(int place){
		this.place = place;
	}
	public void setSeats(Seat seats){
		this.seats = seats;
	}
	
	public String toString(){
		return "제목 : " + getTitle() + " 시간 : " + getTime() + " 상영관 : " + getPlace();
	}
	
}
