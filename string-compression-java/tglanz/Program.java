package tglanz;

import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Arrays;

public class Program {

  static class DisjointSet {

    int[] parents;

    /**
     * Does something
     */
    public DisjointSet(int size) {
      parents = new int[size];
      for (int i = 0; i < parents.length; ++i) {
        parents[i] = i;
      }
    }

    public int find(int value) {
      while (parents[value] != value) {
        value = parents[value];
      }

      return value;
    }

    public void union(int a, int b) {
      a = find(a);
      b = find(b);

      if (a == b) {
        return;
      }

      parents[b] = a;
    }

    public void print() {
      for (var i : parents) {
        System.out.print(i);
        System.out.print(", ");
      }

      System.out.println();
    }
  }

  public static int numIslands(char[][] grid) {
    int nr = grid.length;
    int nc = grid[0].length;
    var ds = new DisjointSet(nr * nc);

    for (int row = 0; row < nr; ++row) {
      for (int col = 0; col < nc; ++col) {
        if (grid[row][col] == '1') {
          grid[row][col] = '0';
          int index = row * nc + col;

          if (row < nr - 1 && grid[row + 1][col] == '1') {
            ds.union(index, (row + 1) * nc + col);
          }

          if (col < nc - 1 && grid[row][col + 1] == '1') {
            ds.union(index, row * nc + (col + 1));
          }
        }
      }
    }

    var roots = new HashSet<Integer>(nc * nr);
    for (int idx = 0; idx < nc * nr; ++idx) {
      int root = ds.find(idx);
      roots.add(root);
    }

    ds.print();
    System.out.printf("roots: %s\n", roots);

    return roots.size();
  }

  public static void main(String[] args) {
    char[][] grid = new char[][] {
      {'1', '1', '0', '0', '0'},
      {'1', '1', '0', '0', '0'},
      {'0', '0', '1', '0', '0'},
      {'0', '0', '0', '1', '1'},
    };

    var answer = numIslands(grid);
    System.out.printf("Number of islands: %d\n", answer);
  }
}
