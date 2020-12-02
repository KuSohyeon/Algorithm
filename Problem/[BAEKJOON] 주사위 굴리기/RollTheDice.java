//[백준] 주사위 굴리기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RollTheDice {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dice, input;
	static int[] dy = { 0, 0, 0, -1, 1 };// 동 서 북 남
	static int[] dx = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		input = new int[K];
		dice = new int[6];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < K; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// 처리
		Game();
	}

	private static void Game() {
		// 초기 세팅
		int ni = x;
		int nj = y;
		if (map[ni][nj] != 0) {
			dice[5] = map[ni][nj];
			map[ni][nj] = 0;
		}
		for (int i = 0; i < K; i++) {
			ni += dy[input[i]];
			nj += dx[input[i]];

			if (ni < 0 || ni >= N || nj < 0 || nj >= M) {// 바깥으로 이동하려고 하는 경우 명령 무시 및 좌표 되돌리기
				ni -= dy[input[i]];
				nj -= dx[input[i]];
				continue; 
			}
			change(input[i]); // 주사위 굴리기
			if (map[ni][nj] != 0) { // map이 0이 아닐 경우에는 바닥으로 복사 후 맵 0처리
				dice[5] = map[ni][nj];
				map[ni][nj] = 0;
			} else { // map이 0일 경우 주사위 바닥에 있는 숫자 복사
				map[ni][nj] = dice[5];
			}
			// 윗면 출력
			System.out.println(dice[0]);
		}
	}

	// 주사위 굴리는 method
	private static void change(int dir) {
		int tmp = dice[0];
		switch (dir) {
		case 1: // 동
			dice[0] = dice[3];
			dice[3] = dice[5];
			dice[5] = dice[2];
			dice[2] = tmp;
			break;
		case 2: // 서
			dice[0] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[3];
			dice[3] = tmp;
			break;
		case 3: // 북
			dice[0] = dice[4];
			dice[4] = dice[5];
			dice[5] = dice[1];
			dice[1] = tmp;
			break;
		case 4: // 남
			dice[0] = dice[1];
			dice[1] = dice[5];
			dice[5] = dice[4];
			dice[4] = tmp;
			break;
		}
	}

}
