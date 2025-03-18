import java.util.Arrays;
import java.util.Comparator;

public class KruskalAlgorithm {

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Subset {
        int parent, rank;
    }

    static int find(Subset subsets[], int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }
        return subsets[i].parent;
    }

    static void union(Subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[xroot].rank > subsets[yroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    static void kruskalMST(Edge edges[], int V) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

        Subset subsets[] = new Subset[V];
        for (int i = 0; i < V; i++) {
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }

        Edge result[] = new Edge[V - 1];
        int e = 0;

        for (int i = 0; i < edges.length && e < V - 1; i++) {
            Edge nextEdge = edges[i];

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) {
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        System.out.println("Edge \tWeight");
        for (int i = 0; i < e; i++) {
            System.out.println(result[i].src + " - " + result[i].dest + " \t" + result[i].weight);
        }
    }

    public static void main(String[] args) {
        int V = 4;
        Edge edges[] = new Edge[] {
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        };

        kruskalMST(edges, V);
    }
}
