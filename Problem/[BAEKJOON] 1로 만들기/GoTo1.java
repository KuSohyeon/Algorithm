//[백준] 1로 만들기
// 배열을 이용하여 앞에서 부터 채우면 뒤에는 앞에서 연산한거 갖다 쓰기

import java.util.Arrays;
import java.util.Scanner;

public class GoTo1 {
	static int [] num;
	static int N;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		num = new int[N+1]; //1차원 배열을 이용
		
//		Arrays.fill(num, Integer.MAX_VALUE);
//		
//		num[0]=0;
//		num[1]=0;
//		
//		int min;
//		for(int i=2;i<=N;i++) {
//			min = Integer.MAX_VALUE;
//			if(i-1>=1 && min>num[i-1]+1) min = num[i-1]+1; 
//			if(i%2==0 && i/2>=1 && min>num[i/2]+1) min = num[i/2]+1;
//			if(i%3==0 && i/3>=1 && min>num[i/3]+1) min = num[i/3]+1;
//			
//			num[i] = min;
//		}
		
		num[1]=0;
		
		for(int i=2;i<=N;i++) {
			num[i]=num[i-1]+1;
			
			if(i%3==0) {
				num[i]=Math.min(num[i], num[i/3]+1);
			}else if(i%2==0) {
				num[i]=Math.min(num[i], num[i/2]+1);
			}
		}
		
		System.out.println(num[N]);
	}

}
