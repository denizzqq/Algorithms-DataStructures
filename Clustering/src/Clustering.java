import java.util.*;
import java.awt.Color;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * This class solves a clustering problem with the Prim algorithm.
 */
public class Clustering {
	EdgeWeightedGraph G;
	List <List<Integer>>clusters; 
	List <List<Integer>>labeled; 
	
	/**
	 * Constructor for the Clustering class, for a given EdgeWeightedGraph and no labels.
	 * @param G a given graph representing a clustering problem
	 */
	public Clustering(EdgeWeightedGraph G) {
            this.G=G;
	    clusters= new LinkedList <List<Integer>>();
	}
	
    /**
	 * Constructor for the Clustering class, for a given data set with labels
	 * @param in input file for a clustering data set with labels
	 */
	public Clustering(In in) {
            int V = in.readInt();
            int dim= in.readInt();
            G= new EdgeWeightedGraph(V);
            labeled=new LinkedList <List<Integer>>();
            LinkedList labels= new LinkedList();
            double[][] coord = new double [V][dim];
            for (int v = 0;v<V; v++ ) {
                for(int j=0; j<dim; j++) {
                	coord[v][j]=in.readDouble();
                }
                String label= in.readString();
                    if(labels.contains(label)) {
                    	labeled.get(labels.indexOf(label)).add(v);
                    }
                    else {
                    	labels.add(label);
                    	List <Integer> l= new LinkedList <Integer>();
                    	labeled.add(l);
                    	labeled.get(labels.indexOf(label)).add(v);
                    	System.out.println(label);
                    }                
            }
             
            G.setCoordinates(coord);
            for (int w = 0; w < V; w++) {
                for (int v = 0;v<V; v++ ) {
                	if(v!=w) {
                	double weight=0;
                    for(int j=0; j<dim; j++) {
                    	weight= weight+Math.pow(G.getCoordinates()[v][j]-G.getCoordinates()[w][j],2);
                    }
                    weight=Math.sqrt(weight);
                    Edge e = new Edge(v, w, weight);
                    G.addEdge(e);
                	}
                }
            }
	    clusters= new LinkedList <List<Integer>>();
	}

	public List<List<Integer>> connectedComponents(LinkedList<Edge> edgeList) {
		int size = G.V();

		UF unionFind = new UF(size);

		edgeList.forEach(((edge) -> {
			int either = edge.either();
			unionFind.union(either, edge.other(either));
		}));

		LinkedList<List<Integer>> clusters = new LinkedList<>();
		for (int i = 0; i < size; i++) {
			clusters.add(new LinkedList<>());
		}

		for (int i = 0; i < size; i++) {
			clusters.get(unionFind.find(i)).add(i);
		}

		clusters = clusters.stream().filter((cluster) -> !cluster.isEmpty()).collect(Collectors.toCollection(LinkedList::new));

		return clusters;
	}
	
    /**
	 * This method finds a specified number of clusters based on a MST.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * @param numberOfClusters number of expected clusters
	 */
	public void findClusters(int numberOfClusters){
		// TODO
		PrimMST mst = new PrimMST(G);
		LinkedList<Edge> edgeList = (LinkedList<Edge>) mst.edges();

		Collections.sort(edgeList);

		LinkedList<Edge> finalEdgeList = new LinkedList<>();
		for (int i = 0; i < edgeList.size() - (numberOfClusters - 1); i++) {
			finalEdgeList.add(edgeList.get(i));
		}

		clusters = connectedComponents(finalEdgeList);
 	}

	/**
	 * This method finds clusters based on a MST and a threshold for the coefficient of variation.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * The edges are removed based on the threshold given. For further explanation see the exercise sheet.
	 *
	 * @param threshold for the coefficient of variation
	 */
	public void findClusters(double threshold) {
		// TODO
		PrimMST mst = new PrimMST(G);
		LinkedList<Edge> edgeList = (LinkedList<Edge>) mst.edges();

		Collections.sort(edgeList);

		LinkedList<Edge> finalEdgeList = new LinkedList<>();
		edgeList.forEach(edge -> {
			finalEdgeList.add(edge);

			if (threshold < coefficientOfVariation(finalEdgeList)) {
				finalEdgeList.removeLast();
			}
		});

		clusters = connectedComponents(finalEdgeList);
	}
	
	/**
	 * Evaluates the clustering based on a fixed number of clusters.
	 * @return array of the number of the correctly classified data points per cluster
	 */
	public int[] validation() {
		// TODO
		int d;
		int size = clusters.size();
		int[] validate = new int[size];

		for (int i = 0; i < size; i++) {
			d = 0;
			for (int j = 0; j < labeled.get(i).size(); j++) {
				for (int item : clusters.get(i)) {
					if (item == labeled.get(i).get(j)) {
						validate[i] = ++d;
					}
				}
			}
		}

		return validate;
	}
	
	/**
	 * Calculates the coefficient of variation.
	 * For the formula see exercise sheet.
	 * @param part list of edges
	 * @return coefficient of variation
	 */
	public double coefficientOfVariation(List <Edge> part) {
		// TODO
		if (part.size() == 0) return 0.0;

		double sum = part.stream().map(Edge::weight).mapToDouble(Double::valueOf).sum();
		double powSum = part.stream().map(item -> Math.pow(item.weight(), 2)).mapToDouble(Double::valueOf).sum();
		double average = part.stream().map(Edge::weight).mapToDouble(Double::valueOf).average().getAsDouble();

		double standardDeviation = Math.sqrt((powSum / part.size())-Math.pow(sum / part.size(),2));
		double coOfVar = standardDeviation/average;

		return coOfVar;
	}
	
	/**
	 * Plots clusters in a two-dimensional space.
	 */
	public void plotClusters() {
		int canvas=800;
	    StdDraw.setCanvasSize(canvas, canvas);
	    StdDraw.setXscale(0, 15);
	    StdDraw.setYscale(0, 15);
	    StdDraw.clear(new Color(0,0,0));
		Color[] colors= {new Color(255, 255, 255), new Color(128, 0, 0), new Color(128, 128, 128), 
				new Color(0, 108, 173), new Color(45, 139, 48), new Color(226, 126, 38), new Color(132, 67, 172)};
	    int color=0;
		for(List <Integer> cluster: clusters) {
			if(color>colors.length-1) color=0;
		    StdDraw.setPenColor(colors[color]);
		    StdDraw.setPenRadius(0.02);
		    for(int i: cluster) {
		    	StdDraw.point(G.getCoordinates()[i][0], G.getCoordinates()[i][1]);
		    }
		    color++;
	    }
	    StdDraw.show();
	}
	

    public static void main(String[] args) {
		// FOR TESTING
    }
}

