package generations;

import java.util.ArrayList;
import java.util.Random;

import dataSource.Event;

public class generation {
public int[] distribution;
public double efficiency;
public double consumption;
public double t1;
double t2;
double t3;
double t4;
double max=0;
public generation(int size) {
	distribution=new int[size];
	}

public void init() {
	for(int i=0;i<20;i++) {
		Random random=new Random();
		int r= random.nextInt(4);
		distribution[i]=r;
	}
}
public int[] getDistribution() {
	return distribution;
}


public double rating(Event event) {
	return  efficiency-500000*consumption/event.eventsize;
}

public void setDistribution(int[] distribution) {
	this.distribution = distribution;
}
public double getEfficiency(Event event) {
	
	
	
	return efficiency;
}

public void settime(Event event) {
	t1=0;
	t2=0;
	t3=0;
	t4=0;
	for(int i=0;i<distribution.length;i++) {
		if(distribution[i]==0) {
			t1+=event.eventlist[i]/200;
		if(max<t1)
			max=t1;}
		if(distribution[i]==1) {
			t2+=event.eventlist[i]/230;
		if(max<t2)
			max=t2;}
		if(distribution[i]==2) {
			t3+=event.eventlist[i]/290;
			if(max<t3)
				max=t3;}
		if(distribution[i]==3) {
			t4+=event.eventlist[i]/360;
		if(max<t4)
			max=t4;}
			}
}
public void setEfficiency(Event event) {
	this.efficiency=event.eventsize/max;
}

public double getConsumption() {
	return consumption;
}
public void setConsumption() {
	this.consumption=t1/60*26+t2/60*19+t3/60*34+t4/60*30;
}

}
