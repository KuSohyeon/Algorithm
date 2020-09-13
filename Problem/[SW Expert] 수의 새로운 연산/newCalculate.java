//[SW Expert] 수의 새로운 연산
/*
2
1 5
3 9
#1 13
#2 26
 */

import java.util.Scanner;

public class newCalculate {
	static int a,b,result,num;
	static int [][] map;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		//p와 q가 10000까지니까 충분히 큰 수로 잡아주기
		num=1;
		map = new int[301][301];
		
		// 배열 먼저 채워주기
		set();
		
			
		for(int tc=1;tc<=T;tc++) {
			
			//입력
			a = sc.nextInt();
			b = sc.nextInt();
			
			
			result=0;
			
			//처리
			int ax=0,ay=0,bx=0,by=0;
			
			for(int i=1;i<=300;i++) {
				for(int j=1;j<=300;j++) {
					if(map[i][j]==a) {
						ax=j;
						ay=i;
					}
					if(map[i][j]==b) {
						bx=j;
						by=i;
					}
				}
			}
			
			result = map[ay+by][ax+bx];
			
			System.out.println("#"+tc+" "+result);
		}
	}
	
	private static void set() { //대각선 순서로 배열 채우기
		for(int i=1;i<301;i++) { //x,y인데 i,j로 하면 우상으로 올라가는 모양으로 잡힘
			
			int ni = i;
			int nj = 1;
			
			while(true) {
				
				if(ni-1<0 || nj>300) break; //범위체크
				map[ni--][nj++]=num++;

			}
			
		}
		
		//사실 얘는 안해줘도 됨 20000까지면 위에서 다 채워짐..
		for(int j=2;j<=300;j++) {
			
			int ni = 300;
			int nj = j;
			
			while(true) {
				
				if(ni-1<0 || nj>300) break;
				
				map[ni--][nj++]=num++;

			}
		}
		
	}

}
