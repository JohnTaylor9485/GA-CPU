package dataSource;

import java.util.Random;

public class Event {
public int[] eventlist;
public int eventsize=0;
public Event(int size) {
	eventlist= new int[size];
	for(int j=0;j<size;j++) {
		
		Random random = new Random();
		int i2 = random.nextInt(20)+1;
		eventlist[j] = 1024*i2;
	}
	}
	
public int[] geteventlist(){
	return this.eventlist;
}

public void seteventsize() {
	for(int i:eventlist) {
		eventsize+=i;
	}
}

}
