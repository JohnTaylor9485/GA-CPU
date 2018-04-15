package generations;

import java.util.ArrayList;
import java.util.Random;

import dataSource.Event;

public class generation {
public boolean[] equipcondition;

public generation(int size) {
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
