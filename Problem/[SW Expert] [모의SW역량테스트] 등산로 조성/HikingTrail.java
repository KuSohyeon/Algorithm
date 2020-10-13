//[SWEA] [모의 SW 역량 테스트] 등산길 조성
// dfs 백트래킹 이용, 파라미터로 boolean형  check 사용하기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HikingTrail {
	static int N, K, result, high;
	static int[][] map, copy;
	static boolean[][] v;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			copy = new int[N][N];
			v = new boolean[N][N];
			high = 0; // tc마다 초기화

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					copy[i][j] = map[i][j];
					high = Math.max(high, map[i][j]);
				}
			}

			result = 0; // tc마다 초기화

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == high) { // 등산로는 꼭대기부터 시작
						v[i][j] = true;
						dfs(i, j, 1, false);
						v[i][j] = false;
					}
				}
			}

			System.out.println("#" + tc + " " + result);
		}
	}

	private static void dfs(int i, int j, int cnt, boolean check) {

		for (int d = 0; d < 4; d++) { //상 하 좌 우만 이동 가능
			int ni = i + dy[d];
			int nj = j + dx[d];

			if (ni < 0 || ni >= N || nj < 0 || nj >= N || v[ni][nj]) // 방문체크 범위 체크
				continue;
			// 아직 깎은 경험이 없고, 깎을 수 있다면
			if (!check && copy[ni][nj] >= copy[i][j] && copy[ni][nj] - K < copy[i][j]) {
				copy[ni][nj] = copy[i][j] - 1; // 최대길이로 하려면 현재값보다 -1 작으면 됨
				check = true;					// 깎은 티 내기
			} else if (copy[ni][nj] >= copy[i][j]) continue;// 깎을 수 없는 경우는 넘겨주기
				
			v[ni][nj] = true; // 방문체크
			dfs(ni, nj, cnt + 1, check); // dfs해주기
			if(copy[ni][nj]!=map[ni][nj]) { // dfs return 후 돌아온 좌표가 원본이랑 다르다면 
				check=false;				// 바꾼지점이니까 원상복귀
				copy[ni][nj]=map[ni][nj];	// 바꾼지점이니까 원상복귀
			}
			v[ni][nj] = false; // 백트래킹

		}
		
		// 등산로 큰 값으로 갱신
		result = Math.max(result, cnt);

	}
}
