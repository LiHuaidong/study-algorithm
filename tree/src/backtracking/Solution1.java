package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution1 {

    public static void main(String[] args) {
        new Solution1().combine(9, 3, 9);
    }

    public void combine(int n, int k, int target) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        combineHelper(n, k, target, 1, result, path);

        for (List<Integer> pa : result) {
            for (Integer ele : pa) {
                System.out.print(ele + "->");
            }
            System.out.println();
        }
    }

    public void combineHelper(int n, int k, int target, int startIndex, List<List<Integer>> result,
            LinkedList<Integer> path) {
        if (path.size() == k) {
            if (sum(path) == target) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            combineHelper(n, k, target, i + 1, result, path);
            path.removeLast();
        }
    }

    public int sum(List<Integer> path) {
        int sum = 0;
        if (path == null || path.isEmpty()) {
            return sum;
        }
        for (Integer tmp : path) {
            sum += tmp;
        }
        return sum;
    }

}
