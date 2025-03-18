public class FloydWarshall {

    static final int INF = 99999;

    public static void floydWarshall(int graph[][]) {
        int V = graph.length;

        int dist[][] = new int[V][V];

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] == 0 && i != j) {
                    dist[i][j] = INF;
                } else {
                    dist[i][j] = graph[i][j];
                }
            }
        }

        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        printSolution(dist);
    }

    public static void printSolution(int dist[][]) {
        int V = dist.length;
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int graph[][] = {
                { 0, 3, INF, INF, INF, INF },
                { 3, 0, 1, INF, INF, INF },
                { INF, 1, 0, 7, INF, 2 },
                { INF, INF, 7, 0, 1, 3 },
                { INF, INF, INF, 1, 0, 1 },
                { INF, INF, 2, 3, 1, 0 }
        };

        floydWarshall(graph);
    }
}
