//[백준] 숫자카드2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumberCard2 {
	static int N,M;
	static int [] sanguen;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		sanguen = new int[20000001];

		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			sanguen[Integer.parseInt(st.nextToken())+10000000]++;
		}
		
		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<M;i++) {
			sb.append(sanguen[Integer.parseInt(st.nextToken())+10000000]).append(" ");
		}
		System.out.println(sb);
	}

}
