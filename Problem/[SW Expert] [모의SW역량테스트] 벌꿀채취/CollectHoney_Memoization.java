//[SWEA] [모의SW역량테스트] 벌꿀채취
// 메모이제이션 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CollectHoney_Memoization {
	static int N, M, C;
	static int[][] map, maxMap;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine().trim());

			N = Integer.parseInt(st.nextToken()); // map의 크기 N
			M = Integer.parseInt(st.nextToken()); // 벌통의 개수 M
			C = Integer.parseInt(st.nextToken()); // 꿀을 채취할 수 있는 최대 양 C
			
			map = new int[N][N];
			maxMap = new int[N][N];
			
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 출력
			System.out.println("#" + tc + " " + getMaxBenefit());
		}
	}
	private static int getMaxBenefit() {
		makeMaxMap();
		return processCombination();
	}
	private static int processCombination() {
		int max=0, aBenefit = 0, bBenefit = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) { // 일꾼 A 선택
				aBenefit = maxMap[i][j];
				// 일꾼 B 선택
				// 같은 행
				bBenefit=0;
				for(int j2 = j+M;j2<=N-M;j2++) {
					if(bBenefit<maxMap[i][j2]) bBenefit = maxMap[i][j2];
				}
				// 다른 행
				for(int i2 = i+1;i2<N;i2++) {
					for(int j2 = 0;j2<=N-M;j2++) {
						if(bBenefit<maxMap[i2][j2]) bBenefit = maxMap[i2][j2];
					}
				}
				
				max = Math.max(aBenefit+bBenefit, max);
			}
		}
		
		return max;
	}
	private static void makeMaxMap() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<=N-M;j++) {
				makeMaxSubset(i,j,0,0,0);
			}
		}
	}
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
		// 용량 체크
		if(sum>C) return;
		// 최대값으로 업데이트
		if(cnt==M) {
			maxMap[i][j-M]=Math.max(maxMap[i][j-M], powSum);
			return;
		}
		// 선택
		makeMaxSubset(i,j+1,cnt+1, sum+map[i][j], powSum+map[i][j]*map[i][j]);
		// 비선택
		makeMaxSubset(i,j+1,cnt+1, sum, powSum);
	}
}
