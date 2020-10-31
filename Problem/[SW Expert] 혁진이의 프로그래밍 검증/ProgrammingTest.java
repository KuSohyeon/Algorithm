//[SWEA] 혁진이의 프로그래밍 검증

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class ProgrammingTest {
	static int R,C;
	static String result;
	static char[][] input;
	static boolean [][][][] check;
	static int [] dy = {-1,1,0,0}; // 상 하 좌 우
	static int [] dx = {0,0,-1,1};
	static class Point{
		int i;
		int j;
		int dir;
		int memory;
		public Point(int i, int j, int dir, int memory) {
			super();
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.memory = memory;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", dir=" + dir + ", memeory=" + memory + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			result = "NO"; //  tc마다 초기화
			st = new StringTokenizer(br.readLine().trim());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			input = new char[R][C];
			check = new boolean [R][C][4][16]; // 방향 메모리 저장할 배열
			
			
			for(int i=0;i<R;i++) {
				char [] ch = br.readLine().toCharArray();
				for(int j=0;j<C;j++) {
					input[i][j]=ch[j];
				}
			}
			
			start();
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void start() {
		Queue<Point> q = new LinkedList<Point>();
		
		q.offer(new Point(0, 0,3,0));
		
		while(!q.isEmpty()) { // 안끝날 경우는 어떻게 할 것인지 생각해보기
			Point cur = q.poll();
			char now = input[cur.i][cur.j];
			switch (now) {
			case '>': 
				cur.dir = 3;
				break;
			case '<': 
				cur.dir = 2;
				break;
			case '^': 
				cur.dir = 0;
				break;
			case 'v': 
				cur.dir = 1;
				break;
			case '_':
				cur.dir = (cur.memory)==0?3:2;
				break;
			case '|':
				cur.dir = (cur.memory)==0?1:0;
				break;
			case '.': // 아무것도 하지않는다.
				break;
			case'@':
				result = "YES";
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				(cur.memory) = now-'0';
				break;	
			case '+':
				if(++(cur.memory)==16) {
					(cur.memory)=0;
				}
				break;
			case '-':
				if(--(cur.memory)==-1) {
					(cur.memory) = 15;
				}
				break;
			}
			
			if(now=='?') { //4방향으로 전환한거 넣어주기 (확률은 동일)
				for(int d=0;d<4;d++) {
					int ni = cur.i + dy[d];
					int nj = cur.j + dx[d];
					
					if(ni==-1) {
						ni = R-1;
					}else if(ni==R) {
						ni = 0;
					}else if(nj==-1) {
						nj = C-1;
					}else if(nj==C) {
						nj = 0;
					}
					
					if(check[ni][nj][d][(cur.memory)]) continue;
					check[ni][nj][d][(cur.memory)]=true;
					q.offer(new Point(ni, nj,d,cur.memory));
				}
				continue;
			}
			
			int ni = cur.i + dy[cur.dir];
			int nj = cur.j + dx[cur.dir];
			
			if(ni==-1) {
				ni = R-1;
			}else if(ni==R) {
				ni = 0;
			}else if(nj==-1) {
				nj = C-1;
			}else if(nj==C) {
				nj = 0;
			}
			
			if(check[ni][nj][cur.dir][cur.memory]) continue;
			check[ni][nj][cur.dir][cur.memory]=true;
			q.offer(new Point(ni, nj,cur.dir,cur.memory));
			
		}
		
	}
}
