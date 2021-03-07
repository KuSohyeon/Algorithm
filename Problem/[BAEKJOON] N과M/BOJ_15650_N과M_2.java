//[백준] N 과 M (2)

import java.util.Scanner;

public class BOJ_15650_N과M_2 {
	static int n, m;
	static boolean[] v;
	static int [] answer;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();

		v = new boolean[n + 1];
		answer = new int [m];

		for (int i = 1; i <= n-m+1; i++) {
			answer[0] = i;
			v[i] = true;
			dfs(i, 1);
			v[i] = false;
		}
	}

	private static void dfs(int num, int cnt) {
		if (cnt == m) {
			for(int a : answer) {
				System.out.print(a + " ");
			}
			System.out.println();
			return;
		}
		for (int i = num+1; i <= n; i++) {
			if(v[i]) continue;
			answer[cnt] = i;
			v[i] = true;
			dfs(i, cnt+1);
			v[i] = false;
		}
	}
}
