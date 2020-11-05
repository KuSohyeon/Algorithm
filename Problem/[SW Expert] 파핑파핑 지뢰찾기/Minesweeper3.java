//[SWEA] 파핑파핑 지뢰찾기

/**
45,828 kb
메모리
259 ms
실행시간
1,794
코드길이
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Minesweeper3 {
	static int N, end, result;
	static char [][] map;
	static boolean [][] v;
	static int [] dy = {-1,1,0,0,-1,-1,1,1}; // 상 하 좌 우 좌상 우상 좌하 우하
	static int [] dx = {0,0,-1,1,-1,1,-1,1};
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
					if(map[i][j]=='*') {
						v[i][j]=true;
					}

				}
			}
			
			result = 0; 
			
			// 뽑아보기
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!v[i][j] & checkNum(i,j)==0) {// 방문하지 않았고, 
						click(i,j);
						result ++;
					}
				}
			}
			
			// 0 아닌 지뢰 아닌 곳 더해주기(1클릭에 1개밖에 안뜨니까 남은 갯수 더해주면 됨)
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!v[i][j]) result++;
				}
			}
			
			// 출력
			System.out.println("#"+tc+" "+result);
		}
	}
	// 0인 위치를 클릭하는 메소드
	private static void click(int i, int j) {
		v[i][j]=true;
		for(int d=0;d<8;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N || v[ni][nj]) continue;
			v[ni][nj]=true;
			map[i][j] = Integer.toString(checkNum(ni,nj)).charAt(0);
			if(map[i][j]=='0') click(ni,nj);
			
		}
		
	}
	// 현재 위치에 지뢰 갯수를 반환하는 메소드
	private static int checkNum(int i, int j) {
		int cnt=0;
		for(int d=0;d<8;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N) continue;
			if(map[ni][nj]=='*') cnt++;
		}
		return cnt;
	}
}
