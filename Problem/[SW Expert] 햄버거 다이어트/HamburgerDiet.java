//[SW Expert] 햄버거 다이어트(부분집합)
/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
 */

import java.util.Scanner;

public class HamburgerDiet {
	static boolean [] isSelected;
	static int N, L,result;
	static int [][] food;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc = 1;tc<=T;tc++) {
			
			N = sc.nextInt(); //음식 개수
			L = sc.nextInt(); //칼로리 제한
			
			food = new int[N][N];
			isSelected = new boolean[N];
			result=0; //tc마다 초기화
			
			for(int i =0;i<N;i++) {
				food[i][0] = sc.nextInt();//점수
				food[i][1] = sc.nextInt();//칼로리
			}
			
			subset(0, 0);
			
			

			
			System.out.println("#"+tc+" "+result);
		}
		sc.close();
	}
	private static void subset(int cnt, int kcal) {
		if(cnt==N) {
			int sum=0;
			if(kcal<=L) {
				for(int i=0;i<N;i++) {
					if(isSelected[i]) {
						sum+=food[i][0];
					}
				}
				if(result<sum) {
					result=sum;
				}
			}
			return;
		}
		isSelected[cnt]=true;
		subset(cnt+1,kcal+food[cnt][1]);
		isSelected[cnt]=false;
		subset(cnt+1,kcal);
		
	}
}
