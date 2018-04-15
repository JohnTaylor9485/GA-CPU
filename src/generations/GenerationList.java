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
	public double min = 10000000;
	public double max = 0;
	public generation bestg;

	public static generation clone(generation g) {
		generation ge = new generation(g.equipcondition.length);

		for (int i = 0; i < g.equipcondition.length; i++) {
			ge.equipcondition[i] = g.equipcondition[i];
		}

		return ge;
	}

	public void mutationcild(Event event) {
		for (generation g : childgenerations) {
			for (Boolean b : g.equipcondition) {
				double k = Math.random();
				if (k <= 0.01) {
					if (b)
						b = false;
					else
						b = true;
				}
			}
			if (rating(g, event) > max) {
				max = rating(g, event);
			}
		}
	}

	public double rating(generation g, Event e) {
		int value = 0;
		int weight = 0;
		for (int i = 0; i < g.equipcondition.length; i++) {
			if (g.equipcondition[i]) {
				value += e.eventvalue[i];
				weight += e.eventplace[i];
			}
		}
		double score = value +6*(3000 - weight);
		if (min > score)
			min = score;
		if (max < score)
			max = score;
        bestg=g;
		return score;

	}

	public double[] getchance(Event event) {
		double[] chance = new double[parentgenerations.size()];
		chance[0] = rating(parentgenerations.get(0), event);
		for (int j = 1; j < parentgenerations.size(); j++) {
			chance[j] = chance[j - 1] + rating(parentgenerations.get(j), event) - min;
		}
		return chance;

	}

	public int pickone(double[] ch) {
		double target = Math.random() * ch[ch.length-1];
		int lo = 0;
		int hi = ch.length-1;
		while (hi > lo) {
			int mid = lo + (hi - lo) / 2;
			if (target < ch[mid])
				hi = mid;
			else if (target > ch[mid])
				lo = mid + 1;
		}
		return lo;
	}

	public double evolve(int popSize, Event event) {
		childgenerations = new ArrayList<generation>();
		double[] ch = getchance(event);
		while (childgenerations.size() < popSize) {
			generation p1 = parentgenerations.get(pickone(ch));
			generation p2 = parentgenerations.get(pickone(ch));
			double grade = crossover(p1, p2, childgenerations, event);
			if (max < grade)
				max = grade;

		}

		mutationcild(event);

		parentgenerations = childgenerations;
		childgenerations = new ArrayList<generation>();

		return max;
	}

	public double crossover(generation p1, generation p2, ArrayList<generation> child, Event event) {
		generation g1 = clone(p1);
		generation g2 = clone(p2);
		int size = p1.equipcondition.length;
		int a = ((int) (Math.random() * size)) % size;
		int b = ((int) (Math.random() * size)) % size;
		int min = a > b ? b : a;
		int max = a > b ? a : b;
		for (int i = min; i <= max; i++) {
			boolean t = g1.equipcondition[i];
			g1.equipcondition[i] = g2.equipcondition[i];
			g2.equipcondition[i] = t;
		}
		child.add(g1);
		child.add(g2);
		return rating(g1, event) > rating(g2, event) ? rating(g1, event) : rating(g2, event);

	}

}
