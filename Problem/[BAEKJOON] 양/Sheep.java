import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sheep {
	static int R, C, sheep, wolves;
	static char[][] map;
	static boolean[][] v;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		v = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != '#' && !v[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(sheep + " " + wolves);
	}

	private static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		int shp = 0, wov = 0;

		q.offer(new Point(r, c));
		v[r][c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			if (map[cur.i][cur.j] == 'o') shp++;
			else if (map[cur.i][cur.j] == 'v') wov++;

			for (int d = 0; d < 4; d++) {
				int ni = cur.i + dy[d];
				int nj = cur.j + dx[d];

				if (ni < 0 || ni >= R || nj < 0 || nj >= C || v[ni][nj] || map[ni][nj] =='#') continue;
				
				v[ni][nj] = true;
				q.offer(new Point(ni, nj));
			}
		}
		
		if(wov>=shp) wolves += wov;
		else sheep += shp;
	}
}
