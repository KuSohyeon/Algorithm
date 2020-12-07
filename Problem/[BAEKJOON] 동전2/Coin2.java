import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Coin2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int [] dp = new int[k+1];
		HashSet<Integer> coin = new HashSet<Integer>(); // 중복입력이 들어올 수 있기 때문에 HashSet 사용
		
		for(int i=0;i<n;i++) {
			coin.add(Integer.parseInt(br.readLine()));
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0]=0;
		
		for(int i=0;i<=k;i++) {
			Iterator iter = coin.iterator(); // iterator를 이용해야 hastset안의 요소에 접근이 가능
			while(iter.hasNext()) {
				int next = (int) iter.next();
				if(i<next) continue;
				if(dp[i-next]==Integer.MAX_VALUE) continue;
				dp[i] = Math.min(dp[i], dp[i-next]+1);
			}
		}
		
		System.out.println(dp[k]==Integer.MAX_VALUE?-1:dp[k]);
	}
}
