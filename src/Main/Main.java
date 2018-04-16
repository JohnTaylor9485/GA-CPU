package Main;

import java.util.Collections;
import java.util.Comparator;

import dataSource.Csvwriter;
import dataSource.Event;
import generations.GenerationList;
import generations.Generation;

public class Main {

	public static void main(String[] args) {

		Event event = new Event(100);
		double[] bestp = new double[10000];
		double min = 100000;
		double max = 0;
		double bestm=0;
		double bestv=0;
		GenerationList gel = new GenerationList();
		for (int i = 0; i < 1000; i++) {
			Generation fg = new Generation(100);
			fg.init();
			gel.rating(fg, event);
			gel.parentgenerations.add(fg);
		}
		bestp[0] = gel.max;
		for (int i = 1; i < 1000; i++) {
			bestp[i] = gel.evolve(1000, event);
			System.out.println(bestp[i]);

		}
		
		
		System.out.print("The best gene sequence:");
		for(int j=0;j<gel.bestg.equipcondition.length;j++) {
			if(gel.bestg.equipcondition[j]) {
				bestm+=event.eventplace[j];
				bestv+=event.eventvalue[j];
			System.out.print("1"+",");
			}
			else
				System.out.print("0"+",");
		}
		System.out.println("\n"+"The money spend is:"+bestm);
		System.out.println("The attack value is:"+bestv);
		
		Csvwriter ncsv = new Csvwriter();
		ncsv.createCSVFile(bestp, "../", "data");

	}
}