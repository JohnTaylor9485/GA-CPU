package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataSource.Event;
import generations.GenerationList;
import generations.generation;

class GenerationListTest {

	@Test
	void testclone() {
		generation g=new generation(10);
		g.init();
		GenerationList ge1 = new GenerationList();
		generation gc=ge1.clone(g);
		assertArrayEquals(g.equipcondition, gc.equipcondition);
	}
	
	
	@Test
	void testgeneration() {
		generation g=new generation(10);
		g.init();
		assertEquals(g.equipcondition.length, 10);
	}
	
	@Test
	void testgenerationinit() {
		generation g=new generation(10);
		g.init();
		int count=0;
		for(Boolean b:g.equipcondition)
			{if(b)
			count++;}
		assertFalse(count==0);
	}
	
	@Test
	void testevent() {
		Event e=new Event(10);
		assertEquals(e.eventsize,10);
		assertEquals(e.eventplace.length,10);
		assertEquals(e.eventvalue.length,10);
		
	}
	
	@Test
	void testeventeffect() {
		Event e=new Event(10);
		for(int i:e.eventplace) {
			assertFalse(i>100);
		}
	}
	
	@Test
	void testcrossover() {
		generation p1=new generation(10);
		p1.init();
		generation p2=new generation(10);
		p2.init();
		Event event=new Event(10);
		ArrayList<generation> childarray= new ArrayList<>();
		GenerationList ge2 = new GenerationList();
		ge2.crossover(p1, p2, childarray, event);
		assertEquals(childarray.size(), 2);
		
	}
	
	@Test
	void testpickone() {
		double[] test=new double[10];
		for(int j=1;j<10;j++) {
			test[j]=test[j-1]+Math.random()*10;
		}
		
		GenerationList ge2 = new GenerationList();
		int re=ge2.pickone(test);
		assertFalse(re>10);
	}
	

	

}
