//[백준] 숫자판 점프

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JumpNumber {
	static int [][] map;
	static Set<String> set; 
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		map = new int[5][5];
		set = new HashSet<>();
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				map[i][j]=sc.nextInt();
			}
		}
		
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				String num = "";
				num += map[i][j];
				dfs(i, j, 1, num);
			}
		}
		
		System.out.println(set.size());
		
	}
	private static void dfs(int i, int j, int cnt, String number) {
		if(cnt == 6) {
			set.add(number);
			return;
		}
		number += map[i][j];
		for(int d=0;d<4;d++) {
			int ni = i + dy[d];
			int nj = j + dx[d];
			if(ni<0 || ni>=5 || nj<0 || nj>= 5) continue;
			dfs(ni,nj,cnt+1, number+map[ni][nj]);
		}
		
	}
}
