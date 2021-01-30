//[백준] 에너지 모으기
import java.util.Scanner;

public class BOJ_16198_GetEnergy {
	static int N, result;
	static int[] W;
	static boolean [] v;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		W = new int[N];
		v = new boolean[N];

		for (int i = 0; i < N; i++) {
			W[i] = sc.nextInt();
		}

		dfs(0,0);

		System.out.println(result);
	}
	private static void dfs(int cnt, int energy) {
		if(cnt == N-2) {
			result = Math.max(result, energy);
			return;
		}
		for(int i=1;i<N-1;i++) {
			if(!v[i]) {
				int newEnergy = check(i);
				if(newEnergy == 0) continue;
				v[i]=true;
				dfs(cnt+1, energy+newEnergy);
				v[i]=false;
			}
		}
	}
	private static int check(int idx) {
		int small = 0, big = 0;
		for(int i=0;i<N;i++) {
			if(!v[i] && i<idx) {
				small = W[i];
			}
			if(!v[i] && i>idx) {
				big = W[i];
				break;
			}
		}
		return small*big;
	}
}
