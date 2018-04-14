package generations;

import java.util.ArrayList;
import java.util.Random;

import dataSource.Event;

public class GenerationList {
public GenerationList() {
		
		parentgenerations = new ArrayList<generation>();
		childgenerations = new ArrayList<generation>();
	}

public ArrayList<generation> parentgenerations;
public ArrayList<generation> childgenerations;
public double min;
public double best=0;



public static generation clone(generation g) {  
    if (g == null || g.distribution == null) {  
        return null;  
    }  
    generation ge = new generation(g.distribution.length);  
   
    for (int i = 0; i < g.distribution.length; i++) {  
        ge.distribution[i] = g.distribution[i];  
    }  
    
    return ge;  
}

public void mutation(generation p1) {  
    if(p1 ==null ||p1.distribution==null) {
    	return;
    }
    for(int i=0;i<p1.getDistribution().length;i++) {
    	Random random=new Random();
    	int chance =random.nextInt(10000);
    	if(chance<100) {
    		int change=random.nextInt(4);
    		while(p1.distribution[i]==change) {
    			change=random.nextInt(4);
    		}
    		p1.distribution[i]=change;
    	}
    }
    
} 


public generation pickone(Event event) {
	double[] chance=new double[1000];
	chance[0]=parentgenerations.get(0).rating(event);
	for(int j=1;j<parentgenerations.size();j++) {
		chance[j]=chance[j-1]+parentgenerations.get(j).rating(event)-min;
	}
	
	double target= Math.random() * (chance[999] + 1) ;
	
//  Math.random() * (chance[999] + 1) ;
    
	
	
	
	int lo=0;
	int hi=999;
	
	while(hi>lo) {
		 int mid=lo+(hi-lo)/2;
		if(target<chance[mid])
			hi=mid;
		else if(target>chance[mid])
			lo=mid+1;
			}

	return parentgenerations.get(lo);
	
}



public double evolve(int popSize,Event event) {  
    childgenerations = new ArrayList<generation>();  
    while (childgenerations.size() < popSize) {  
        generation p1 = pickone(event);  
        generation p2 = pickone(event); 
        p1.settime(event);
        p1.setConsumption();
        p1.setEfficiency(event);
        p2.settime(event);
        p2.setConsumption();
        p2.setEfficiency(event);
        double value=crossover(p1, p2, childgenerations,event);
       
        if(best<value)
        	best=value;
        
    }  
    parentgenerations=childgenerations;
    childgenerations = new ArrayList<generation>();
    System.out.println(best);
    return best;
}







public double crossover(generation p1, generation p2,ArrayList<generation> child,Event event) {  
//    if (p1 == null || p2 == null) {   
//        return 0.0;  
//    }  
//    if (p1.getDistribution() == null || p2.getDistribution() == null) {  
//        return 0.0;  
//    }  
//    if (p1.distribution.length != p2.distribution.length) {  
//        return 0.0;  
//    }  
    generation g1 = clone(p1); 
    generation g2 = clone(p2);
    g1.settime(event);
    g2.settime(event);
    g1.setConsumption();
    g1.setEfficiency(event);
    g2.setConsumption();
    g2.setEfficiency(event);
    int size = 20;  
    int a = ((int) (Math.random() * size)) % size;  
    int b = ((int) (Math.random() * size)) % size;  
    int min = a > b ? b : a;  
    int max = a > b ? a : b;  
    for (int i = min; i <= max; i++) {  
        int t = g1.distribution[i];  
        g1.distribution[i] = g2.distribution[i];  
        g2.distribution[i] = t;  
    }  
    g1.rating(event);
    g2.rating(event);
   child.add(g1);
   child.add(g2);
   //System.out.println(g1.max);
   return g1.rating(event)>g2.rating(event) ? g1.rating(event):g2.rating(event);
    
} 

}
