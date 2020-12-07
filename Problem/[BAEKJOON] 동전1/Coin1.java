//[백준] 동전 1

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin1 {
	static int N, K;
	static int[] coin, money;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		coin = new int[N];
		money = new int[K + 1];

		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		money[0] = 1;

		for (int j = 0; j < N; j++) {
			for (int i = 1; i <= K; i++) {
				if (i < coin[j])
					continue;
				money[i] += money[i - coin[j]];
			}
		}

		System.out.println(money[K]);
	}
}
