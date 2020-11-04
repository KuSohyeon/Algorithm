//[백준] 스도쿠

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
public class Sudoku {
	static int [][] map;
	static List<Point> list;
	static class Point{
		int i;
		int j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new int[9][9];
		list = new ArrayList<Point>();
		
		for(int i=0;i<9;i++) {
			char [] ch = br.readLine().toCharArray();
			for(int j=0;j<9;j++) {
				map[i][j]=ch[j]-'0';
				if(map[i][j]==0) {
					list.add(new Point(i,j));
				}
			}
		}
		
		dfs(0);
		
	}
	private static void dfs(int cnt) {
		if(cnt==list.size()) {
			for(int i=0;i<9;i++) {
				for(int j=0;j<9;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		Point now = list.get(cnt);
		for(int i=1;i<=9;i++) {
			if(!isPossible(now.i,now.j,i)) continue;
			map[now.i][now.j]=i;
			dfs(cnt+1);
			map[now.i][now.j]=0;
		}
		
	}
	private static boolean isPossible(int r, int c, int num) {
		
		for(int i = 0;i<9;i++) {
			if(map[i][c]==num) {
				return false;
			}
		}
		
		for(int i = 0;i<9;i++) {
			if(map[r][i] == num) {
				return false;
			}
		}
		
		int starti = r/3 *3;
		int startj = c/3 *3;
		
		for(int i = starti;i<starti+3;i++) {
			for(int j=startj;j<startj+3;j++) {
				if(map[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
	
}
