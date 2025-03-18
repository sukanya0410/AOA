import java.util.*;

public class PrimsMST {

    // A utility function to find the vertex with the minimum key value from the set
    // of vertices not yet included in MST
    static int minKey(int key[], boolean mstSet[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // A utility function to print the constructed MST stored in parent[]
    static void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < graph.length; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[parent[i]][i]);
        }
    }

    // Function to construct and print MST for a graph represented using adjacency
    // matrix representation
    static void primMST(int graph[][]) {
        int V = graph.length; // Number of vertices in the graph

        // Arrays to store constructed MST and key values
        int parent[] = new int[V];
        int key[] = new int[V];
        boolean mstSet[] = new boolean[V];

        // Initialize all keys as INFINITY and mstSet[] as false
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mstSet, false);

        // Start with the first vertex, set its key to 0
        key[0] = 0;
        parent[0] = -1; // The first node is always the root of MST

        // Construct the MST by picking vertices one by one
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST set
            mstSet[u] = true;

            // Update the key value and parent index of the adjacent vertices of the picked
            // vertex
            // Consider only vertices that are not yet included in MST
            for (int v = 0; v < V; v++) {
                // Check if the edge u-v exists, v is not included in MST, and weight of edge
                // u-v is less than the current key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        printMST(parent, graph);
    }

    public static void main(String[] args) {
        // Example graph represented as a 4x4 adjacency matrix
        int graph[][] = {
                { 0, 2, 0, 6 },
                { 2, 0, 3, 8 },
                { 0, 3, 0, 0 },
                { 6, 8, 0, 0 }
        };

        // Call the function to construct and print the MST
        primMST(graph);
    }
}