import java.io.Serializable;


public class Seat implements Serializable{
	int place;
	int[][] seats;
	int x;
	int y;
	
	public Seat(int place){
		this.place = place;
		this.x = (place + 2) * 5;
		this.y = (place + 5);
		this.seats = new int[x][y];
	}
	
	public int getPlace(){
		return place;
	}
	
	public int[][] getSeats(){
		return seats;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getTotal(){
		return x*y;
	}
	
	public void setPlace(int place){
		this.place = place;
	}
	
	public void setSeats(int[][] seats){
		this.seats = seats;
	}
	
	
	public void print_seat(){
		for(int i = 0; i < this.x; i++){
			for(int j = 0; j < this.y; j++){
				System.out.print(seats[i][j]+" ");
			}
			System.out.println();
		}
	}
	
}
