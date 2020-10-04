//[백준] 거스름돈
/*
입력 : 13
출력 : 5
입력 : 14
출력 : 5
 */
import java.util.Arrays;
import java.util.Scanner;

public class Change {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); // 거스름 돈
		
		if(n==1 || n==3) { // 거슬러 줄 수 없을 경우 -1 출력
			System.out.println(-1);
			return;
		}
		
		int [] coin = new int[n+1];
		Arrays.fill(coin, Integer.MAX_VALUE);
		coin[0]=coin[1]=0;
		
		for(int i=2;i<=n;i++) {
			if(i/2>=1) { // 2원을 사용할 수 있을 경우
				coin[i]=Math.min(coin[i], coin[i-2]+1);
			}
			if(i/5>=1 && (i%5)%2==0) { // 5원을 사용할 수 있을 경우
				coin[i]=Math.min(coin[i], coin[i-5]+1);
			}
		}
		
		System.out.println(coin[n]);
	}
}
