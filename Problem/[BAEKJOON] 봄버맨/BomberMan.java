//[백준] 봄버맨1,2
/*
 * 가장 처음에 봄버맨은 일부칸에 폭탄을 설치해 놓는다.
 * 다음 1초동안 아무것도 하지 않는다.
 * 다음 1초동안 폭탄이 설치되어있는 모든 칸에 폭탄은 설치한다.
 * 1초가 지난후 3초전에 설치된 폭탄이 모두 폭발한다.
 * 3과4를 반복한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BomberMan {
	static int R, C, N;
	static int[][] map;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = ch[j] == '.' ? 0 : 3; //int 형 배열로 바꿔서 관리하기
			}
		}
		
		//초기값 -> 전체폭탄 -> 폭발값 -> 전체폭탄 (일정한 패턴을 가지고 있음 -> 즉 N번 반복할 필요가 없다.)
		if(N==1) { //1초 후와 5초 후가 다른 반례가 발생하기 때문에 1초는 따로 빼줘야함
			print();
			System.exit(0);
		}
		else if (N % 2 == 0) { // 짝수초는 폭탄 설치하는 시간 -> 다 폭탄인거 바로 출력해버리기
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					System.out.print('O');
				}
				System.out.println();
			}
			System.exit(0);
		} else if (N % 4 == 1) { 
			N = 5;
		} else if (N % 4 == 3) {
			N = 3;
		}
		Go(N); // 봄버맨 출동
		print();
	}
	
	private static void Go(int n) {
		for (int i = 2; i <= n; i++) {
			if (i % 2 == 0) {
				install(i+3); // 2초 4초 일때는 폭탄 설치해주기
			}
			else {
				boom(i); // 현 시간 = 폭탄 시간이랑 같은것만 터뜨리기
			}
		}
	}
	// 폭탄 터지는 method -> 연쇄작용X
	private static void boom(int t) { 
		boolean [][] v = new boolean[R][C];
		for(int i=0;i<R;i++) {
			for(int j=0;j<C;j++) {
				if(map[i][j]==t && !v[i][j]) {
					v[i][j]=true;
					map[i][j]=0;
					for(int d=0;d<4;d++) {
						int ni = i + dy[d];
						int nj = j + dx[d];
						if(ni<0 || ni>=R || nj<0 || nj>=C || map[ni][nj]==t || v[ni][nj]) continue;
						map[ni][nj]=0;
						v[ni][nj]=true;
					}
				}
			}
		}
	}
	// 폭탄 설치하는 method
	private static void install(int t) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == 0) {
					map[i][j] = t; 
				}
			}
		}
	}
	// 출력 method
	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] == 0 ? '.' : 'O');
			}
			System.out.println();
		}
	}
}
