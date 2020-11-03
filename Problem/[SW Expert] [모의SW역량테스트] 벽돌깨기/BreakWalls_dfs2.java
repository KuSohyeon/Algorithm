//[SWEA] [모의SW역량테스트] 벽돌깨기 - dfs로 구현
// 시간 줄인 버전
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreakWalls_dfs2 {
	static int N,W,H,result;
	static int [][] map;
	static int [] marble;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			marble = new int[N];
			
			for(int i=0;i<H;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<W;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			result = Integer.MAX_VALUE;
			
			perm(0,map); // 경우의 수 : 중복 순열 만들기
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void perm(int cnt, int [][] map) {
		if(cnt==N) {
			result = Math.min(result, count(map));
			return;
		}
		for(int i=0;i<W;i++) {
			int [][] copy = new int[H][W];
			copyArr(copy,map);
			shoot(i,copy); // 쏘기
			down(copy); // 내리기
			perm(cnt+1, copy);
		}
	}
	// 카피 배열 만들어 주는 method
	private static void copyArr(int[][] copy,int [][] map) {
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				copy[i][j]=map[i][j];
			}
		}
	}
	// 남은 벽돌 세는 method
	private static int count(int [][] copy) {
		int count=0;
		for(int i=0;i<H;i++) {
			for(int j=0;j<W;j++) {
				if(copy[i][j]!=0) {
					count++;
				}
			}
		}
		return count;
	}
	// 벽돌 내려주는 method
	private static void down(int[][] copy) {
		for(int i=H-1;i>=0;i--) {
			for(int j=0;j<W;j++) {
				if(copy[i][j]==0) {
					int ni = i;
					while(true) {
						ni--;
						if(ni<0) break;
						if(copy[ni][j]==0) continue;
						copy[i][j]=copy[ni][j];
						copy[ni][j]=0;
						break;
					}
				}
			}
		}
	}
	// 구슬 위치대로 쏘는 method
	private static void shoot(int l, int[][] copy) {
		for(int i=0;i<H;i++) {
			if(copy[i][l]==0) continue; // 벽돌이 아닌 빈 공간은 넘겨주기
			int num = copy[i][l];
			copy[i][l]=0;
			breakwalls(i,l,num,copy);
			break;
		}
		
	}
	// 벽돌 깨는 재귀 method
	private static void breakwalls(int i, int j, int num, int [][] copy) {
		for(int d=0;d<4;d++) {
			int ni = i;
			int nj = j;
			int tmp = 0;
			while(true) {
				if(++tmp==num) break;
				ni += dy[d];
				nj += dx[d];
				
				if(ni<0 || ni>=H || nj<0 || nj>=W) continue;
				if(copy[ni][nj]==0) continue;
				if(copy[ni][nj]==1) {
					copy[ni][nj]=0;
					continue;
				}
				int nnum = copy[ni][nj];
				copy[ni][nj]=0;
				breakwalls(ni,nj,nnum,copy);
			}
		}
	}
}
