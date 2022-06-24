package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        new Solution().combine(4, 2);
    }

    public void combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        combineHelper(n, k, 1, result, path);

        for (List<Integer> pa : result) {
            for (Integer ele : pa) {
                System.out.print(ele + "->");
            }
            System.out.println();
        }
    }

    public void combineHelper(int n, int k, int startIndex, List<List<Integer>> result,
            LinkedList<Integer> path) {

        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            combineHelper(n, k, i + 1, result, path);
            path.removeLast();
        }
    }

}
