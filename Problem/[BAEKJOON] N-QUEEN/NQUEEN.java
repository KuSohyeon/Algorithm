//[백준] N-QUEEN
import java.util.Scanner;

public class NQUEEN {
	static int N, result;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0,-1,-1,1,1};// 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		v = new boolean[N][N];
		
		for(int i=0;i<N;i++) { // 첫 번째 행 탐색
			v[0][i]=true;
			dfs(1,i,0);
			v[0][i]=false;
		}
		
		System.out.println(result);
	}
	private static void dfs(int i, int j, int cnt) {
		if(i==N) { // N행이면 탐색 끝
			result++;
			return;
		}
		for(int c=0;c<N;c++) {
			if(c==j) continue; // 이전에 놓았던 곳과 같은 열이면 continue;
			if(!check(i,c)) continue; // 현재 좌표 가능한지 확인
			v[i][c]=true; // 방문 처리
			dfs(i+1,c,cnt+1); // 다음 행 탐색하러가기
			v[i][c]=false; //백트래킹
		}
		
	}
	private static boolean check(int i, int j) {
		// 1. 8 방향으로 있는 지 체크
		for(int d=0;d<8;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			if(ni<0 || ni>= N || nj<0 || nj>=N) continue;
			if(v[ni][nj]) return false;
			if(d>3) { // 대각선 라인에 퀸이 있으면 안됨
				while(true) {
					ni += dy[d];
					nj += dx[d];
					if(ni<0 || ni>=N || nj<0 || nj>=N) break;
					if(v[ni][nj]) return false;
				}
			}
		}
		// 2. 같은 열에 퀸 있는지 체크
		for(int r=0;r<i;r++) {
			if(v[r][j]) return false;
		}
		return true;
	}
}
