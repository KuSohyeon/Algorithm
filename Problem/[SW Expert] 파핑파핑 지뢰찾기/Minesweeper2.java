//[SWEA] 파핑파핑 지뢰찾기

/**
 * 
56,644 kb
메모리
246 ms
실행시간
2,797
코드길이
 */
/**
 * 
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class Minesweeper2 {
	static int N, end, result;
	static char [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0,-1,-1,1,1}; // 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	static class Pair{
		int i;
		int j;
		public Pair(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Pair [i=" + i + ", j=" + j + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			result = 0; //tc마다 초기화
			N = Integer.parseInt(br.readLine());
			
			v = new boolean[N][N];
			map = new char[N][N];
			
			for(int i=0;i<N;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<N;j++) {
					map[i][j]=ch[j];

				}
			}
			
			// number 배열 숫자로 세팅
			SetNumber();
			
			// 뽑아보기
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(map[i][j]=='0' && !v[i][j]) {
						click(i,j);
						result ++;
					}
				}
			}
			
			// 0 아닌 지뢰 아닌 곳 더해주기(1클릭에 1개밖에 안뜨니까 남은 갯수 더해주면 됨)
			result += jum();
			
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	
	private static int jum() {
		int cnt=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]!='*' && !v[i][j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	// 클릭했을 때 0이면 바로바로 
	private static int click(int r, int c) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.offer(new Pair(r,c));
		v[r][c]=true;
		int cnt=1;
		
		while(!q.isEmpty()) {
			Pair now = q.poll();
			
			if(map[now.i][now.j]=='0') {
				for(int d=0;d<8;d++) {
					int ni = now.i + dy[d];
					int nj = now.j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N || map[ni][nj] =='*' || v[ni][nj]) continue;
					cnt++; // .에서 숫자로 바꾼거 갯수 늘려주기
					v[ni][nj]=true;
					// 그 중 0인 거는 다시 큐에 넣어주고 8방위 찍어주게 하기
					if(map[ni][nj]=='0') q.offer(new Pair(ni,nj)); 
				}
			}
		}
		
		
		return cnt;
	}
	// 주변에 지뢰의 개수를 세팅하는 메소드
	private static void SetNumber() {
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]=='*') continue;
				int cnt=0;
				for(int d=0;d<8;d++) {
					int ni = i + dy[d];
					int nj = j + dx[d];
					
					if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
					if(map[ni][nj]=='*') cnt++;
				}
				map[i][j]= Integer.toString(cnt).charAt(0);
			}
		}
		
	}
}
