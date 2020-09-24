//[SW Expert] 최장 증가 부분 수열
/*
2
5
1 3 2 5 4 
6
4 2 3 1 5 6
#1 3
#2 4
 */

import java.util.Scanner;

public class LIS {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			int N = sc.nextInt();
			int [] arr = new int[N];
			int [] LIS = new int[N];
			
			for(int i=0;i<N;i++) {
				arr[i]=sc.nextInt();
			}
			
			int max=0;
			
			for(int i=0;i<N;i++) {
				LIS[i]=1;
				for(int j=0;j<=i-1;j++) {
					if(arr[i]>arr[j]) {
						LIS[i] = Math.max(LIS[i],LIS[j]+1);
					}
				}
				max = Math.max(max, LIS[i]);
			}
			

			System.out.println("#"+tc+" "+max);
		}
	}
}
