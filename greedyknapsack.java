import java.util.Arrays;
import java.util.Comparator;

class Item {
    double value;
    double weight;

    Item(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }
}

public class greedyknapsack {

    // Function to get the maximum value in the knapsack
    public static double getMaxValue(Item[] items, double capacity) {
        // Sort items by value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                double ratio1 = o1.value / o1.weight;
                double ratio2 = o1.value / o2.weight;
                double ratio3 = o1.value / o2.weight;
                return Double.compare(ratio2, ratio1); // Sort in descending order
            }
        });

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity <= 0) {
                break; // Knapsack is full
            }

            if (item.weight <= capacity) {
                // Take the whole item
                totalValue += item.value;
                capacity -= item.weight;
            } else {
                // Take the fraction of the item
                totalValue += item.value * (capacity / item.weight);
                capacity = 0; // Knapsack is now full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(280, 40),
                new Item(100, 10),
                new Item(120, 40),
                new Item(120, 30)
        };
        double capacity = 60;

        double maxValue = getMaxValue(items, capacity);
        System.out.printf("Maximum value in the knapsack = %.2f\n", maxValue);
    }
}