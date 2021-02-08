//[백준] 나무 탈출

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class EscapeTree {
	static int N, result;
	static List<Integer>[] list;
	static List<Integer> leafNode;
	static int[] arr;
	static boolean [] v;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N + 1];
		arr = new int[N + 1];
		v = new boolean[N+1];
		leafNode = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
			arr[a]++;
			arr[b]++;
		}

		dfs(1, 0);

		System.out.println(result % 2 == 1 ? "Yes" : "No");

	}

	private static void dfs(int now, int cnt) {
		v[now]=true;
		if(now != 1 && arr[now] == 1) {
			result += cnt;
			return;
		}
		for(int i=0;i<list[now].size();i++) {
			int next = list[now].get(i);
			if(v[next]) continue;
			dfs(next, cnt+1);
			v[next]=false;
		}
	}
}
