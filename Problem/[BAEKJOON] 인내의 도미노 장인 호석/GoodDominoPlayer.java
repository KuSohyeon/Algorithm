//[백준] 인내의 도미노 장인 호석

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GoodDominoPlayer {
	static int N,M,R,result;
	static int [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0};// 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 세로
		M = Integer.parseInt(st.nextToken()); // 가로
		R = Integer.parseInt(st.nextToken()); // 라운드 횟수
		
		map = new int[N+1][M+1];
		v = new boolean[N+1][M+1];
		
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int r, c;
		char d;
		for(int i=0;i<R;i++) {
			// 공격
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			d = st.nextToken().charAt(0);
			int dir = direction(d); // 방향을 int값으로 변환
			if(!v[r][c]) // 쓰러진 도미노가 아닌 경우에만 공격이 허용
				attack(r,c,dir);
			// 수비
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			defence(r,c);
		}

		// 출력
		print();
		
	}
	// 수비 method : 쓰러진 도미노 (true)를 일으키기 (false)
	private static void defence(int r, int c) {
		v[r][c]=false;
	}
	// 공격 method -> 재귀 이용
	private static void attack(int r, int c, int d) {
		if(v[r][c]) return; // 이미 쓰러진 곳이면 return
		if(!v[r][c]) result++; // 처음 쓰러뜨릴 때만 점수 획득
		v[r][c]=true; // 쓰러뜨리기
		int nr = r, nc = c;
		for(int i=1;i<map[r][c];i++) {
			nr += dy[d];
			nc += dx[d];
			if(nr<=0 || nr>N || nc<=0 || nc>M) break; // 배열의 밖으로 나가면 도미노 중단
			attack(nr,nc,d); // 재귀 보내기
		}
	}
	// character형 방향을 int형으로 바꿔주기
	private static int direction(char d) {
		int dir =0;
		switch (d) {
		case 'N': 
			dir = 0;
			break;
		case 'S':
			dir = 1;
			break;
		case 'W':
			dir = 2;
			break;
		case 'E':
			dir = 3;
			break;
		}
		return dir;
	}
	// 출력 method
	private static void print() {
		System.out.println(result);
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				System.out.print(v[i][j]?"F ":"S ");
			}
			System.out.println();
		}
	}
}
