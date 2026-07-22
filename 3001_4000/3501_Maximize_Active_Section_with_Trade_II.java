import java.util.*;


class Group {
    int start;
    int length;

    Group(int start, int length) {
        this.start = start;
        this.length = length;
    }
}


class SparseTable {
    private int n;
    private int[][] st;

    public SparseTable(int[] nums) {
        this.n = nums.length;
        if (n == 0) return;
        int K = 32 - Integer.numberOfLeadingZeros(n);
        st = new int[K][n];

        for (int i = 0; i < n; i++) {
            st[0][i] = nums[i];
        }

        for (int i = 1; i < K; i++) {
            for (int j = 0; j + (1 << i) <= n; j++) {
                st[i][j] = Math.max(st[i - 1][j], st[i - 1][j + (1 << (i - 1))]);
            }
        }
    }

    public int query(int l, int r) {
        if (l > r) return 0;
        int i = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(st[i][l], st[i][r - (1 << i) + 1]);
    }
}

public class 3501_Maximize_Active_Section_with_Trade_II {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                totalOnes++;
            }
        }

        List<Group> zeroGroups = new ArrayList<>();
        int[] zeroGroupIdx = new int[n];
        Arrays.fill(zeroGroupIdx, -1);

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    zeroGroups.get(zeroGroups.size() - 1).length++;
                } else {
                    zeroGroups.add(new Group(i, 1));
                }
            }
            zeroGroupIdx[i] = zeroGroups.size() - 1;
        }

        int m = zeroGroups.size();
        List<Integer> ans = new ArrayList<>(queries.length);

        if (m == 0) {
            for (int i = 0; i < queries.length; i++) {
                ans.add(totalOnes);
            }
            return ans;
        }

        int[] adjacentGains = new int[m - 1];
        for (int i = 0; i < m - 1; i++) {
            adjacentGains[i] = zeroGroups.get(i).length + zeroGroups.get(i + 1).length;
        }

        SparseTable st = new SparseTable(adjacentGains);

        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];

            int gL = zeroGroupIdx[l];
            int gR = zeroGroupIdx[r];

            int leftLen = 0;
            if (s.charAt(l) == '0') {
                int startI = zeroGroups.get(gL).start;
                int length = zeroGroups.get(gL).length;
                leftLen = length - (l - startI);
            }

            int rightLen = 0;
            if (s.charAt(r) == '0') {
                int startI = zeroGroups.get(gR).start;
                rightLen = r - startI + 1;
            }

            int startAdjacent = gL + 1;
            int endAdjacent = (s.charAt(r) == '0') ? gR - 1 : gR;

            int startAdjIdx = startAdjacent;
            int endAdjIdx = endAdjacent - 1;

            int active = totalOnes;

            if (s.charAt(l) == '0' && s.charAt(r) == '0' && gL + 1 == gR) {
                active = Math.max(active, totalOnes + leftLen + rightLen);
            } else if (startAdjIdx <= endAdjIdx) {
                active = Math.max(active, totalOnes + st.query(startAdjIdx, endAdjIdx));
            }

            int boundaryCheck = (s.charAt(r) == '1') ? gR : gR - 1;
            if (s.charAt(l) == '0' && gL + 1 <= boundaryCheck) {
                active = Math.max(active, totalOnes + leftLen + zeroGroups.get(gL + 1).length);
            }

            if (s.charAt(r) == '0' && gL < gR - 1) {
                active = Math.max(active, totalOnes + rightLen + zeroGroups.get(gR - 1).length);
            }

            ans.add(active);
        }

        return ans;
    }
}
    

