//[백준] 동전 0

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Coin0 {
	static int N,K;
	static int [] coin, money;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		money = new int[K+1];
		
		for(int i=0;i<N;i++) {
			coin[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.fill(money, Integer.MAX_VALUE);
		money[0]=0;
		money[1]=1;
		for(int i=2;i<=K;i++) {
			for(int j=N-1;j>=0;j--) {
				if(i<coin[j]) continue;
				money[i]=Math.min(money[i], money[i-coin[j]]+1);
				break;
			}
		}
		
		System.out.println(money[K]);
	}
}
