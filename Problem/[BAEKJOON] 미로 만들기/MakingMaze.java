//[백준] 미로 만들기

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MakingMaze {
	static int N;
	static char [][] map;
	static int [] dy = {1,0,-1,0}; // 하 좌 상 우
	static int [] dx = {0,-1,0,1}; // 하 좌 상 우
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		String s = br.readLine();
		
		// 어디서 출발하는지 모르기 때문에 배열의 크기 넉넉하게 잡아주기 (0<N<50)
		map = new char [101][101];
		
		// 중간에서 시작
		int i = 50, j=50, dir=0;
		int minI=50,maxI=50,minJ=50,maxJ=50;
		map[i][j]='.';
		
		for(int n=0;n<N;n++) {
			switch(s.charAt(n)) {
			case 'R':
				if(++dir==4) { 
					dir=0;
				}
				break;
			case 'L':
				if(--dir==-1) {
					dir=3;
				}
				break;
			case 'F':
				i += dy[dir];
				j += dx[dir];
				map[i][j]='.';
				maxI = Math.max(maxI, i);
				minI = Math.min(minI, i);
				maxJ = Math.max(maxJ, j);
				minJ = Math.min(minJ, j);
				break;
			}
		}
		
		// 접근한 지점만 출력해주기
		for(int a=minI;a<=maxI;a++) {
			for(int b=minJ;b<=maxJ;b++) {
				System.out.print(map[a][b]=='.'?'.':'#');	
			}
				System.out.println();
		}
		
	}
}
