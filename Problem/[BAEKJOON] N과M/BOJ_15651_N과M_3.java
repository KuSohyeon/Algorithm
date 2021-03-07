//[백준] N 과 M (3)
//System.out.println으로 출력하면 시간초과 -> StringBuilder 사용

import java.util.Scanner;

public class BOJ_15651_N과M_3 {
	static int n, m;
	static int [] answer;
	static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();

		n = sc.nextInt();
		m = sc.nextInt();

		answer = new int [m];

		for (int i = 1; i <= n; i++) {
			answer[0] = i;
			dfs(i, 1);
		}
		
		System.out.println(sb.toString());
	}

	private static void dfs(int num, int cnt) {
		if (cnt == m) {
			for(int a : answer) {
				sb.append(a).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 1; i <= n; i++) {
			answer[cnt] = i;
			dfs(i, cnt+1);
		}
	}
}
