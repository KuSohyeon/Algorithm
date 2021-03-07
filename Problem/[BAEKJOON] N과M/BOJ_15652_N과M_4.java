//[백준] N 과 M (4)

import java.util.Scanner;

public class BOJ_15652_N과M_4 {
	static int n, m;
	static int[] answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		answer = new int[m];

		for (int i = 1; i <= n; i++) {
			answer[0] = i;
			dfs(i, 1);
		}
	}

	private static void dfs(int num, int cnt) {
		if (cnt == m) {
			for (int a : answer) {
				System.out.print(a + " ");
			}
			System.out.println();
			return;
		}
		for (int i = num; i <= n; i++) {
			answer[cnt] = i;
			dfs(i, cnt + 1);
		}
	}
}
