//[백준] 색종이-2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColoredPaper2 {
	static int N,result,moseri;
	static int [][] map;
	static int [] dy = {-1,1,0,0}; 
	static int [] dx = {0,0,-1,1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[101][101];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			patchPaper(x,y);
		}
		
		for(int i=0;i<101;i++) {
			for(int j=0;j<101;j++) {
				if(map[i][j]>0) {
					for(int d=0;d<4;d++) {
						int ni = i + dy[d];
						int nj = j + dx[d];
						
						if(map[ni][nj]==0) result++;
					}
				}
			}
		}
		

		System.out.println(result);
	}

	private static void patchPaper(int x, int y) {
		for(int i=y;i<y+10;i++) {
			for(int j=x;j<x+10;j++) {
				map[i][j]++;
			}
		}
	}
}
