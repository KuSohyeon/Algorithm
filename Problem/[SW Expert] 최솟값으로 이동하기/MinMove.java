//[SW Expert] 3349. 최솟값으로 이동하기
/*
1
4 3 3
1 1
3 3
4 1
# 5
 */

import java.util.Scanner;

public class MinMove {
	static int W,H,N,result;
	static Pair [] location;
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			//입력
			W = sc.nextInt();
			H = sc.nextInt();
			N = sc.nextInt();
			
			location = new Pair[N];
			
			for(int i=0;i<N;i++) {
				location[i] = new Pair(sc.nextInt(),sc.nextInt()); //객체로 할당해서 넣어주기
			}
			
			result = 0; 
			
			for(int i=0;i<N-1;i++) {
				int x = location[i].x - location[i+1].x;
				int y = location[i].y - location[i+1].y;
				
				if(x*y>0) { //우상 또는 좌하
					result += Math.min(Math.abs(x),Math.abs(y)); //대각선 이동
					result += Math.abs(Math.abs(x)-Math.abs(y)); //나머지는 가로, 세로 이동
				}
				else {
					result+=Math.abs(x)+Math.abs(y);
				}
				
			}
		
			
			System.out.println("#"+tc+" "+result);
		}// end tc
		sc.close();
	}// end main
}// end class
