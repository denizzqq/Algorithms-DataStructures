import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
/**
 * Class that represents a maze with N*N junctions.
 * 
 * @author Vera Röhr
 */
public class Maze{
    private final int N;
    private Graph M;    //Maze
    public int startnode;
        
	public Maze(int N, int startnode) {
		
        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
	}
	
    public Maze (In in) {
    	this.M = new Graph(in);
    	this.N= (int) Math.sqrt(M.V());
    	this.startnode=0;
    }

	
    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
		// TODO
        if (v < 0 || v >= M.V() || w < 0 || w >= M.V()){
            throw new IllegalArgumentException("chosen numbers of vertices must be in the range of vertices on graph");
        }
        if (v != w){
            M.addEdge(v, w);
        }
    }
    
    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge( int v, int w){
		// TODO
        if (v == w){
            return true;
        }

        if (v < 0 || v >= M.V()){
            return false;
        }

        for (int i : M.adj(v)){
            if(i == w){
                return true;
            }
        }

        return false;
    }	
    
    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
        // TODO
        Graph G = new Graph(N * N);

        int v;
        v = G.V() - 1;

        if (v < 2) {
            return G;
        } else {
            for (int i = 0; i < G.V(); i++) {
                if (i % Math.sqrt(G.V()) != Math.sqrt(G.V()) - 1) {
                    G.adj(i).add(i + 1);
                    G.adj(i + 1).add(i);

                }
                if (i < (G.V() - Math.sqrt(G.V()))) {
                    G.adj(i).add((int) (i + Math.sqrt(G.V())));
                    G.adj((int) (i + Math.sqrt(G.V()))).add(i);
                }
            }
            return G;
        }
    }
    
    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
		// TODO
        Graph maze = mazegrid();
        RandomDepthFirstPaths r = new RandomDepthFirstPaths(maze, startnode);
        r.randomDFS(maze);
        int[] edges = r.edge();
        for(int i = 1; i < edges.length; i++){
            addEdge(i,edges[i]);
        }

    }


    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
		// TODO
            RandomDepthFirstPaths r = new RandomDepthFirstPaths(M, w);
            r.randomDFS(M);
            return r.pathTo(v);

        }
    
    /**
     * @return Graph M
     */
    public Graph M() {
    	return M;
    }

    public static void main(String[] args) {
		// FOR TESTING
    }


}

