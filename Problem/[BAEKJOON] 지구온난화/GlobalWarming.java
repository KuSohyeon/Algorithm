//[백준] 지구 온난화

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GlobalWarming {
	static int R, C;
	static char[][] map;
	static int[][] sea;
	static int[] dy = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		// 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// map 크기 상후좌우로 한칸씩 늘려서 범위 밖에 벗어나는거 신경안쓰게 하기
		map = new char[R + 2][C + 2];
		sea = new int[R + 2][C + 2];

		for (int i = 1; i <= R; i++) {
			String s = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = s.charAt(j - 1);
			}
		}

		// 가장자리를 .으로 채워주기
		for (int i = 0; i < R + 2; i++) {
			for (int j = 0; j < C + 2; j++) {
				if (map[i][j] == 'X' || map[i][j] == '.')
					continue;
				map[i][j] = '.';
			}
		}
		
		// 50년 후
		cntSea(); // 주변 바다의 갯수 체크
		changeMap();// 지도체크
		print();// 지도의 크기도 줄여서 출력하는 method

	}
	// 지도의 크기도 줄여서 출력하는 method
	private static void print() {
		int startI = 1, endI = R, startJ = 1, endJ = C;

		loop: for (int i = 1; i <= R; i++) {
			startI = i;
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == 'X')
					break loop;
			}
		}

		loop: for (int i = R; i >= 1; i--) {
			endI = i;
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == 'X')
					break loop;
			}
		}
		loop: for (int j = 1; j <= C; j++) {
			startJ = j;
			for (int i = 1; i <= R; i++) {
				if (map[i][j] == 'X')
					break loop;
			}
		}
		loop: for (int j = C; j >= 1; j--) {
			endJ = j;
			for (int i = 1; i <= R; i++) {
				if (map[i][j] == 'X')
					break loop;
			}
		}
		// 출력
		for (int i = startI; i <= endI; i++) {
			for (int j = startJ; j <= endJ; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	// 수면이 높아지는 method
	private static void changeMap() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (sea[i][j] >= 3) {
					map[i][j] = '.';
				}
			}
		}
	}
	// 바다의 갯수 체크하는 method
	private static void cntSea() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] == '.')
					continue;
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int ni = i + dy[d];
					int nj = j + dx[d];
					if (map[ni][nj] == '.')
						cnt++; // 바다의 갯수 체크
				}
				sea[i][j] = cnt; // 업데이트
			}
		}
	}
}
