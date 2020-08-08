//[SW Expert] 파리 퇴치
/*
2
5 2
1 3 3 6 7
8 13 9 12 8
4 16 11 12 6
2 4 1 23 2
9 13 4 7 3
6 3
29 21 26 9 5 8
21 19 8 0 21 19
9 24 2 11 4 24
19 29 1 0 21 19
10 29 6 18 4 3
29 11 15 3 3 29

출력:
#1 49
#2 159
 */

import java.util.Scanner;

public class FlyAway {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			int N = sc.nextInt();
			int M = sc.nextInt();
			
			int [][] map = new int[N][N];
		
			//입력
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					map[i][j]=sc.nextInt();
				}
			}
			

			
			int max=0;
			for(int i=0;i<N-M;i++) {
				for(int j=0;j<N-M;j++) {
					int result=0;
					for(int m=0;m<M;m++) {
						for(int l=0;l<M;l++) {
							result += map[i+m][j+l];
						}
					}
					if(max<result) {
						max=result;
					}
				}
			}

			
			
			System.out.println("#"+tc+" "+max);
			
		}
		sc.close();
	}
}
