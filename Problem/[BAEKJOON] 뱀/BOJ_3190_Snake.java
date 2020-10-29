//[백준] 뱀

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_Snake {
	static int N,K,L,result;
	static int [][] map;
	static Data [] move;
	static int [] dy = {0,1,0,-1}; //우 하 좌 상
	static int [] dx = {1,0,-1,0};
	static class Data{// 뱀의 이동 경로를 저장할 구조체
		int sec;
		int dir;
		public Data(int sec, int dir) {
			super();
			this.sec = sec;
			this.dir = dir;
		}
	}
	static class Snake{ // 뱀의 정보를 저장할 구조체
		int headI;
		int headJ;
		public Snake(int headI, int headJ) {
			super();
			this.headI = headI;
			this.headJ = headJ;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 보드의 크기 N
		
		map = new int[N+1][N+1]; // 1행1열부터 시작
		
		K = Integer.parseInt(br.readLine()); // 사과의 개수 K
		
		for(int k=0;k<K;k++) { // 사과 위치 map에 표시
			st = new StringTokenizer(br.readLine().trim());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c]=-1;
		}
		
		L = Integer.parseInt(br.readLine()); // 뱀 방향 변환 횟수 L
		
		move = new Data[L];
		
		for(int l=0;l<L;l++) {
			st = new StringTokenizer(br.readLine().trim());
			int s = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			move[l]= new Data(s,d=='L'?-1:1); // 방향에 따라 -1,1 로 구분해서 넣어줌
												// -1 : 반시계(왼쪽) 1 : 시계(오른쪽)
		}
		
		game();
		
		System.out.println(result-1); // 벽에 부딪힌 경우 or 자기 자신과 부딪힌 경우에도 시간이 증가하니까 -1 해주기
		
	}
	private static void game() {
		Queue<Snake> q = new LinkedList<Snake>();
		
		int idx=0,dir=0;
		int curI=1, curJ=1;
		q.offer(new Snake(1,1));
		
		map[curI][curJ]=1;
		result = 1;// 시작부터 1초라고 했음 -> map이 0으로 초기화되어있기때문
		while(true) {
			
			Snake cur = q.poll();
			
			result ++; //시간 체크
			
			if(idx<move.length) {
				if(result>move[idx].sec+1) { //시작부터 1초라고 선언했기 때문에 +1한거보다 많아야함
					dir += move[idx].dir; // 회전
					if(dir>=4) { // 인덱스 오류 방지 -> 시계방향
						dir %= 4;
					}else if(dir<0) { // 인덱스 오류 방지 -> 반시계방향
						dir += 4;
					}
					idx++; //다음조건과 비교하기 위해 인덱스 ++
				}
			}
			
			int ni = cur.headI + dy[dir];
			int nj = cur.headJ + dx[dir];
			
			if(ni<1 || nj<1 || ni>N || nj>N ) return; // 벽에 닿거나 자신의 몸에 닿이면 바로 게임 종료
			
			if(map[ni][nj]==-1) {
				map[ni][nj]=result;
				q.offer(new Snake(ni,nj)); // 사과먹으면 꼬리는 그대로 (몸 길이 연장)
			}
			else if(map[ni][nj]==0){
				map[ni][nj]=result;
				q.offer(new Snake(ni,nj)); // 사과없으면 몸길이 그대로 + 꼬리 지워줘야함
				removeTail(); // 꼬리지워주기
			}else return; // 그외의 경우는 자기 자신이니까 게임 종료
			
		}
		
	}
	// 꼬리 지워주는 method
	private static void removeTail() {
		int min = Integer.MAX_VALUE,r=0,c=0;
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				if(map[i][j]>0) {
					if(min>map[i][j]) {
						min = map[i][j];
						r=i;
						c=j;
					}
				}
			}
		}
		map[r][c]=0;
	}
}
