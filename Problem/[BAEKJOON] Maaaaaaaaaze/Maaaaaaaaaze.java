//[백준] Maaaaaaaaaze

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Maaaaaaaaaze {
	static int result = Integer.MAX_VALUE;
	static int cnt = 0;
	static int[][][] map, input;
	static int [] order;
	static boolean [] visited;
	static boolean[][][] v;
	static int[][] tmp;
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, 0, -1, 1 };
	static int[] dz = { 1, -1, 0, 0, 0, 0 };
	static class Point implements Comparable<Point> {
		int z;
		int i;
		int j;
		int cnt;

		public Point(int z, int i, int j, int cnt) {
			super();
			this.z = z;
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Point [z=" + z + ", i=" + i + ", j=" + j + ", cnt=" + cnt + "]";
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		input = new int[5][5][5];
		map = new int[5][5][5];
		tmp = new int[5][5];
		visited = new boolean[5];
		order = new int[5];

		for (int z = 0; z < 5; z++) {
			for (int i = 0; i < 5; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 5; j++) {
					input[z][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		perm(0);

		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	// 층의 순서를 정하는 method
	private static void perm(int cnt) {
		if(cnt==5) {
			copy(order);
			dfs(0);
		}
		for(int i=0;i<5;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			order[cnt] = i;
			perm(cnt+1);
			visited[i]=false;
		}
	}
	// 층 순서 정했으니 map에 복사 method
	private static void copy(int[] order) {
		for(int l=0;l<5;l++) {
			for(int i=0;i<5;i++) {
				for(int j=0;j<5;j++) {
					map[l][i][j] = input[order[l]][i][j];
				}
			}
		}
	}
	// 판 돌리는 순서 method
	private static void dfs(int high) {
		if (high == 5) {
			bfs();
			return;
		}
		for (int d = 0; d < 4; d++) {
			rotation(high);
			dfs(high + 1);
		}
	}
	// 미로찾기
	private static void bfs() {
		if(map[0][0][0]==0) return; // 입구가 막혀있으면 바로 return
		PriorityQueue<Point> pq = new PriorityQueue();
		v = new boolean[5][5][5];

		v[0][0][0] = true;
		pq.offer(new Point(0, 0, 0, 0));

		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if (cur.cnt > result)
				continue;
			if ( cur.z == 4 && cur.i == 4 && cur.j == 4) {
				if(result>cur.cnt) {
					result = cur.cnt;
				}
				break;
			} 
			
			for (int d = 0; d < 6; d++) {
				int nz = cur.z + dz[d];
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];
				if (nz<0 || nz>=5 || ni < 0 || ni >= 5 || nj < 0 || nj >= 5 || map[nz][ni][nj] == 0 || v[nz][ni][nj])
					continue;
				v[nz][ni][nj] = true;
				pq.offer(new Point(nz, ni, nj, cur.cnt + 1));
			}
		}
	}
	// 시계방향으로 90도 회전 method
	private static void rotation(int high) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				tmp[j][4 - i] = map[high][i][j];
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				map[high][i][j] = tmp[i][j];
			}
		}
	}
}
