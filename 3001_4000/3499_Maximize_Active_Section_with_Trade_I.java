public class 3499_Maximize_Active_Section_with_Trade_I {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                totalOnes++;
            }
        }

        List<Integer> zeroLengths = new ArrayList<>();
        int n = t.length();
        int i = 0;

        while (i < n) {
            if (t.charAt(i) == '0') {
                int start = i;
                while (i < n && t.charAt(i) == '0') {
                    i++;
                }
                zeroLengths.add(i - start);
            } else {
                i++;
            }
        }

        int maxDelta = 0;
        for (int j = 0; j < zeroLengths.size() - 1; j++) {
            maxDelta = Math.max(maxDelta, zeroLengths.get(j) + zeroLengths.get(j + 1));
        }

        return totalOnes + maxDelta;
    }
}
}
