//[백준] 색종이 만들기
// 분할정복

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeColorPaper {
	static int N,white,blue;
	static int [][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0,0,N);
		
		System.out.println(white); // 하얀색 색종이 수
		System.out.println(blue); // 파란색 색종이 수
		
	}
	private static void dfs(int i, int j, int n) {
		if(check(i,j,n)) { //정사각형 안에 한 색깔로 이루어져 있는지 검사
			if(map[i][j]==0) white++; // 흰색일 경우 흰종이 ++
			else blue++; // 파란색일 경우 파란종이 ++
			return; // 리턴해서 그만 해주기
		}
		// 4개로 분할해서 재귀
		dfs(i,j,n/2);
		dfs(i,j+n/2,n/2);
		dfs(i+n/2,j,n/2);
		dfs(i+n/2,j+n/2,n/2);
	}
	// 색종이가 한 색깔로 이루어져 있는지 판단
	private static boolean check(int r, int c, int n) {
		for(int i=r;i<r+n;i++) {
			for(int j=c;j<c+n;j++) {
				if(map[i][j]!=map[r][c]) return false;
			}
		}
		return true;
	}
}
