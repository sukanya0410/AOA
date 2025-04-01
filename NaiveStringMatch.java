public class NaiveStringMatch {

    public static void naiveStringMatch(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (text.charAt(i + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index: " + i);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABA";

        naiveStringMatch(text, pattern);
    }
}
