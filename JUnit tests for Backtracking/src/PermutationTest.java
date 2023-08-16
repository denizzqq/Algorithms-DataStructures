import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PermutationTest {
	PermutationVariation p1;
	PermutationVariation p2;
	public int n1;
	public int n2;
	int cases=1;
	
	void initialize() {
		n1=4;
		n2=6;
		Cases c= new Cases();
		p1= c.switchforTesting(cases, n1);
		p2= c.switchforTesting(cases, n2);
	}
	

	@Test
	void testPermutation() {
		initialize();
		// TODO
		assertEquals(n1, p1.original.length);
		assertEquals(n2, p2.original.length);

		Set<Integer> set1 = new HashSet<>();
		Set<Integer> set2 = new HashSet<>();

		for (int element: p1.original) {
			set1.add(element);
		}
		for (int element: p2.original) {
			set2.add(element);
		}

		assertEquals(n1, set1.size());
		assertEquals(n2, set2.size());

		assertNotNull(p1.allDerangements);
		assertNotNull(p2.allDerangements);

		assertTrue(p1.allDerangements.isEmpty());
		assertTrue(p2.allDerangements.isEmpty());
	}

	@Test
	void testDerangements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();
		p2.derangements();

		double result = 0;
		for (int i = 0; i <= n1; i++) {
			int fact = 1;
			for (int j = 2; j <= i; j++) {
				fact = fact * j;
			}
			result += Math.pow(-1, i) / fact;
		}

		double totalFact = 1;
		for (int j = 2; j <= n1; j++) {
			totalFact = totalFact * j;
		}

		int count1 = (int) (result * totalFact);

		result = 0;
		for (int i = 0; i <= n2; i++) {
			int fact = 1;
			for (int j = 2; j <= i; j++) {
				fact = fact * j;
			}
			result += Math.pow(-1, i) / fact;
		}

		totalFact = 1;
		for (int j = 2; j <= n2; j++) {
			totalFact = totalFact * j;
		}

		int count2 = (int) (result * totalFact);

		assertEquals(count1, p1.allDerangements.size());
		assertEquals(count2, p2.allDerangements.size());

		for (int[] derangement : p1.allDerangements) {
			for (int i = 0; i < n1; i++) {
				int element = p1.original[i];
				assertNotEquals(element, derangement[i]);
			}
		}
		for (int[] derangement : p2.allDerangements) {
			for (int i = 0; i < n2; i++) {
				int element = p2.original[i];
				assertNotEquals(element, derangement[i]);
			}
		}
	}
	
	@Test
	void testsameElements() {
		initialize();
		//in case there is something wrong with the constructor
		fixConstructor();
		// TODO
		p1.derangements();
		p2.derangements();
		for (int[] derangement : p1.allDerangements) {
			Set<Integer> set1 = new HashSet<>();
			Set<Integer> set2 = new HashSet<>();

			for (int element: p1.original) {
				set1.add(element);
			}
			for (int element: p2.original) {
				set2.add(element);
			}

			assertEquals(n1, set1.size());
			assertEquals(n2, set2.size());

			for (int element : derangement)	{
				assertTrue(Arrays.asList(p1.original).contains(element));
			}
		}
	}
	
	void setCases(int c) {
		this.cases=c;
	}
	
	public void fixConstructor() {
		//in case there is something wrong with the constructor
		p1.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n1;i++)
			p1.original[i]=2*i+1;
		
		p2.allDerangements=new LinkedList<int[]>();
		for(int i=0;i<n2;i++)
			p2.original[i]=i+1;
	}
}


