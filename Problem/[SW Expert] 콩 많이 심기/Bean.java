//[SW Expert] 콩 많이 심기(Greedy)
/*
 1
 3 2
 */

import java.util.Scanner;

public class Bean {
	static int n,m,result;
	static int [][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			int cnt=0;
			
			m = sc.nextInt();//열
			n = sc.nextInt();//행
			
			map = new int [n][m];
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]!=0) {//0은 심을 수 있는 구간
						continue;
					}
					map[i][j]=1;//콩심은 곳은 1로 표시
					cnt++;
					if(i+2<n) {
						map[i+2][j]=2;//2는 심을 수 없는 구간
					}
					if(j+2<m) {
						map[i][j+2]=2;
					}
					
					
					
				}
			}
			
			//출력 확인용
//			for(int i=0;i<n;i++) {
//				for(int j=0;j<m;j++) {
//				System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			
		
			System.out.println("#"+tc+" "+cnt);
		}
		sc.close();
	}
}
