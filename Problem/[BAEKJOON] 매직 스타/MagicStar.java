import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//[백준] 매직 스타
public class MagicStar {
	static char[][] map;
	static List<Data> list, point;
	static boolean[] v;
	static int[] dy = { 0, 0, -1, -1, 1, 1 };// 좌 우 좌상 우상 좌하 우하
	static int[] dx = {-1, 1, -1, 1, -1, 1 };

	static class Data {
		int i;
		int j;

		public Data(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Data [i=" + i + ", j=" + j + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[5][9];
		v = new boolean[12];
		list = new ArrayList<Data>();
		point = new ArrayList<Data>();

		for (int i = 0; i < 5; i++) {
			String s = br.readLine();
			for (int j = 0; j < 9; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'x') {
					list.add(new Data(i, j));
				} else if (map[i][j] != '.') {
					v[map[i][j] - 'A'] = true;
				}
			}
		}
		
		// 별 위치 리스트에 추가
		point.add(new Data(0,4));
		point.add(new Data(4,4));
		point.add(new Data(1,1));
		point.add(new Data(3,1));
		dfs(0);
	}
	private static void dfs(int idx) {
		if (idx == list.size()) {
			if(!check()) return; // 검사
			print();
			System.exit(0);
		}
		Data now = list.get(idx);
		for (int i = 0; i < 12; i++) {
			if (v[i]) continue;
			v[i] = true;
			map[now.i][now.j] = (char) (i + 'A');
			dfs(idx + 1);
			// 백트래킹
			v[i] = false;
			map[now.i][now.j] = 'x';
		}
	}
	// 노가다...
	private static boolean check() {
		for(int i=0;i<4;i++) {
			if(i==0) { // (0,4)인 경우 좌하 우하 검사
				for (int d = 4; d <= 5; d++) {
					int sum = map[0][4] - 'A';
					int ni = 0;
					int nj = 4;
					while (true) {
						ni += dy[d];
						nj += dx[d];
						if(map[ni][nj]=='.') break;
						sum += map[ni][nj] - 'A';
					}
					if(sum != 22) return false; // 0부터 시작하니까 1씩 4번 빼지면 22
				}
			}else if(i==1) {// (4,4)인 경우 좌상 우상 검사
				for (int d = 2; d <= 3; d++) {
					int sum = map[4][4] - 'A';
					int ni = 4;
					int nj = 4;
					while (true) {
						ni += dy[d];
						nj += dx[d];
						if(map[ni][nj]=='.') break;
						sum += map[ni][nj] - 'A';
					}
					if(sum != 22) return false;
				}
			}else {
				Data now = point.get(i);
				int sum = map[now.i][now.j] - 'A';
				int ni = now.i;
				int nj = now.j;
				while(true) {
					ni += 2*dy[1];
					nj += 2*dx[1];
					if(nj>=9) break;
					sum += map[ni][nj] - 'A';
				}
				if(sum != 22) return false;
			}
		}
		return true;
	}
	private static void print() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
