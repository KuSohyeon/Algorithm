//[SW Expert] [모의 SW 역량테스트]보호필름
/**
 * JAVA
 * 언어
 * 22,412 kb
 * 메모리
 * 701 ms
 * 실행시간
 * 1,803
 * 코드길이
 * Pass
 * 결과
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112 {
	static int D, W, K, result;
	static int[][] film, copy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());

			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			film = new int[D][W];
			copy = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
					copy[i][j] = film[i][j];
				}
			}

			result = 987654321; // tc마다 초기화
			
			if(K==1) { // 1인 경우에는 안해줘도 됨
				System.out.println("#" + tc + " " + 0);
				continue;
			}
			dfs(0,0);

			System.out.println("#" + tc + " " + result);
		}
	}
	private static void dfs(int r, int cnt) {
		if(check()) {
			result = Math.min(result, cnt);
			return;
		}
		if(cnt>=result) return; // 가지치기!! 어차피 최소를 구하는 거니까 최소 횟수보다 넘어간다면 해 볼 필요가 없음!!!!!
		if(r==D) return; // D에서 return해야함!!! D-1에는 D 못가게 하니까 예외 케이스 발생했었음
		for(int n=0;n<3;n++) {
			change(r,n);
			dfs(r+1,n!=2?cnt+1:cnt);
			back(r,n); // 백트래킹
		}
	}
	// 되돌리는 method
	private static void back(int r, int n) {
		if(n==2) return;
		for(int j=0;j<W;j++) {
			copy[r][j]=film[r][j];
		}
	}
	// 약물 주입하는 method
	private static void change(int r, int n) {
		if(n==2) return;
		for(int j=0;j<W;j++) {
			copy[r][j]=n;
		}
	}
	// 보호 필름 검사하는 method
	private static boolean check() {
		for(int j=0;j<W;j++) {
			int cnt=1;
			boolean flag = false;
			for(int i=1;i<D;i++) {
				if(copy[i-1][j]==copy[i][j]) {
					if(++cnt==K) flag = true;
				}
				else cnt=1;
			}
			if(!flag) return false;
		}
		return true;
	}
}
