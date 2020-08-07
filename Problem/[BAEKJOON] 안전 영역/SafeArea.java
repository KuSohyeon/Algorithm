//백준(2468번) - 안전영역
/*
5
6 8 2 6 2
3 2 3 4 6
6 7 3 3 2
7 2 5 3 6
8 9 5 2 7
*
출력 : 5 
 */

import java.util.Scanner;

public class SafeArea {
	static int [][] map;
	static boolean [][] v;
	static int N,height;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();//배열의 크기

		map = new int[N][N];
		
		//입력
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j]=sc.nextInt();
				if(height < map[i][j]) {
					height = map[i][j];//최대 높이 구하기
				}
			}
		}

		
		int max=0;
		
		for(int x = 0; x<= height;x++) {
			v = new boolean[N][N];//물높이 달라질때 마다 visit 생성해주기
			setVisit(x); //물높이에 맞게 visit 배열 표시해주기
			int cnt=0;//높이마다 섬의 개수 초기화해주기 
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!v[i][j]) {//체크 안한 영역만 하기~
						cnt++;//섬의 개수 세기
						dsf(i,j); 
					}
				}
			}
			if(max<cnt) {//제일 큰걸로 바꿔주기
				max=cnt;
			}
		}
		
		System.out.println(max);
		
		sc.close();
	}
	private static void setVisit(int height) { //물의 높이에 맞게 visit을 세팅해주는 메소드
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]<=height) {
					v[i][j]=true;
				}
			}
		}
	}
	private static void dsf(int i, int j) { //dsf
		v[i][j]=true;
		int ni,nj;
		for(int d=0;d<4;d++) {
			ni = i + dy[d];
			nj = j + dx[d];
			
			if(ni<0 || ni>=N || nj<0 || nj>=N) {
				continue;
			}
			if(v[ni][nj]) {
				continue;
			}
			dsf(ni,nj);
		}
		
	}
}
