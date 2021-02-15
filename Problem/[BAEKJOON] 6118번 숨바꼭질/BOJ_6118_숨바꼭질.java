//[백준] 숨바꼭질

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6118_숨바꼭질 {
	static List[] map;
	static int N, M, count, dist, num;
	static boolean[] v;

	static class Node {
		int node;
		int dist;

		public Node(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Node [node=" + node + ", dist=" + dist + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			map[a].add(b);
			map[b].add(a);
		}

		bfs(1);

		System.out.println(num + " " + dist + " " + count);

	}

	private static void bfs(int start) {
		Queue<Node> q = new LinkedList<>();
		v = new boolean[N + 1];

		v[1] = true;
		q.offer(new Node(1, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.dist > dist) {
				num = cur.node;
				dist = cur.dist;
				count = 1;
			} else if (cur.dist == dist) {
				if (num > cur.node) {
					num = cur.node;
				}
				count++;
			}

			for (int i = 0; i < map[cur.node].size(); i++) {
				int next = (int) map[cur.node].get(i);
				if (v[next]) continue;
				v[next] = true;
				q.offer(new Node(next, cur.dist + 1));
			}
		}

	}
}
