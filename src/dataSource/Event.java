package dataSource;

import java.util.Random;

public class Event {
public int[] eventplace;
public int[] eventvalue;
public int eventsize;
public Event(int size) {
	eventplace= new int[size];
	eventvalue= new int[size];
	for(int j=0;j<size;j++) {
		
		Random random = new Random();
		int i2 = random.nextInt(100)+1;
		eventplace[j] =i2;
	}
	
     for(int j=0;j<size;j++) {
		
		Random random = new Random();
		int i2 = random.nextInt(1000)+1;
		eventvalue[j] =i2;
	}
	eventsize=size;
	
	}
	


}
