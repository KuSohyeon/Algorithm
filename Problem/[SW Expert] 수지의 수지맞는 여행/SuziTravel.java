//[SW Expert] 수지의 수지맞는 여행
/*
3
2 4
CAAB
ADCB
3 6
HFDFFB
AJHGDH
DGAGEH
5 5
IEFCJ
FHFKC
FFALF
HFGCF
HMCHH
 */

import java.util.Scanner;

public class SuziTravel {
	static int [][] map;
	static boolean [] check;
	static int R,C,max;
	static int [] dy = {-1,1,0,0};
	static int [] dx = {0,0,-1,1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			max=0; //tc마다 초기화

			R = sc.nextInt();
			C = sc.nextInt();

			map = new int [R][C];
			check = new boolean[26];

			for(int i=0;i<R;i++) {
				char [] ch = sc.next().toCharArray();
				for(int j=0;j<C;j++) {
					map[i][j]=ch[j];
				}
			}
			
			check[map[0][0]-65]=true; //ABCD Check 'A' = 65(아스키코드)
			dfs(0,0,1);

			System.out.println("#"+tc+" "+max);
		}
	}
	private static void dfs(int i, int j, int cnt) {
		
		max = Math.max(max, cnt);
		
		int ni,nj;
		for(int d=0;d<4;d++) {
			ni = i + dy[d];
			nj = j + dx[d];
			
			if(ni<0 || ni>=R || nj<0 || nj>=C) continue;
			if(check[map[ni][nj]-65]) continue;
			check[map[ni][nj]-65]=true;
			dfs(ni,nj,cnt+1);
			check[map[ni][nj]-65]=false;//백트래킹
			
		}

	}
}
