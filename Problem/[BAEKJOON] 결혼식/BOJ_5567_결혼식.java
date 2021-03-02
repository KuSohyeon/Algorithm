// [백준] 결혼식
// 상근이가 결혼식에 초대할 사람의 수를 구하는 프로그램을 작성
// 친구의 친구까지만 초대
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_5567_결혼식 {
	static int n, m;
	static List[] friends;
	static boolean[] v;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		friends = new ArrayList[n + 1];
		v = new boolean[n + 1];

		for (int i = 1; i <= n; i++) {
			friends[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향
			friends[a].add(b);
			friends[b].add(a);
		}

		for (int i = 0; i < friends[1].size(); i++) {
			// 내 친구를 기준으로 깊이우선탐색
			int now = (int) friends[1].get(i);
			dfs(now, 1);
		}

		int result = 0;
		for (int i = 2; i <= n; i++) {
			if (v[i]) { // 친구 혹은 친구의 친구이면 초대할 사람이니까 count++
				result++;
			}
		}
		// 출력
		System.out.println(result);
	}

	private static void dfs(int person, int cnt) {
		if (cnt > 2) { // 친구의 친구보다 먼 사이면 바로 return
			return;
		}
		v[person] = true; // 방문체크
		for (int i = 0; i < friends[person].size(); i++) {
			int now = (int) friends[person].get(i);
			if (!v[now]) { // 친구의 친구 중 초대하지 않은 친구만 탐색
				dfs(now, cnt + 1);
			}
		}

	}
}
