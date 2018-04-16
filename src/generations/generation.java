package generations;

import java.util.ArrayList;
import java.util.Random;

import dataSource.Event;

public class Generation {
public boolean[] equipcondition;

public Generation(int size) {
	equipcondition=new boolean[size];
	}

public void init() {
	for(int i=0;i<equipcondition.length;i++) {
		
		double r= Math.random();
		if(r>0.5)
		equipcondition[i]=true;
		else
			equipcondition[i]=false;
	}
}

}
