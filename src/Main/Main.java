package Main;

import java.util.Collections;
import java.util.Comparator;

import dataSource.Event;
import generations.GenerationList;
import generations.generation;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
    Event event= new Event(20);
    event.seteventsize();
    double[] bestp=new double[20000];
    double min=100000;
    double max=0;
    GenerationList gel=new GenerationList();
	for(int i=0;i<1000;i++) {
		generation fg=new generation(20);
		fg.init();
		fg.settime(event);
		fg.setConsumption();
		fg.setEfficiency(event);
		
//			System.out.println(fg.t1);
//			System.out.println(fg.efficiency);
		if(min>fg.rating(event))
		min=fg.rating(event);
		if(max<fg.rating(event))
		max=fg.rating(event);
		gel.parentgenerations.add(fg);
	}
	//System.out.println(max);
	bestp[0]=max;
	gel.min=min;
	for(int i=1;i<10000;i++) {
	bestp[i]=gel.evolve(1000, event);
	//System.out.println(bestp[i]);
	}
	
	
	
	
//	for(double i:bestp) {
//		System.out.println(i);
//	}
	
	
	
	
	
	
	
//	Collections.sort(gel.parentgenerations,new Comparator<generation>() {
//		public int compare(generation o1,generation o2) {
//			if (o1.rating(event)>o2.rating(event))
//				return 1;
//			else if (o1.rating(event)==o2.rating(event)) {
//				return 0;
//			}else {
//				return -1;
//			}
//		}
//	});
	
	
	
	
}
}