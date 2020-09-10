//[SWEA] 홈 방범 서비스

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HomeSecurity {
	static int [][] map;
	static int N,M,result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			// 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			result=0;
			//모든 map[i][j]마다 마름모 생성해야함
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					// map[i][j] 칸마다 모든 크기의 마름모 생성
					for(int k=1;k<=N+1;k++) { //N보다 조금 큰 것 까지 마름모 만들어보기
						int tmp = cntHome(i,j,k);
						if(tmp*M >= (k*k+(k-1)*(k-1))) { // 서비스 센터가 손해는 보면 안됨
							result = Math.max(result,tmp);// 그 중에서 가장 많이 서비스 제공받기
						}
					}
				}
			}
			
			
			System.out.println("#"+tc+" "+result);
		}
		
		
	}
	static int cntHome(int a, int b,int k) {
		int cnt=0;
		// a b로부터 k 거리만큼의 마름모 생성
		for(int r = a-k;r<=a+k;r++) {
			for(int c = b-k;c<=b+k;c++) {
				// 상 하 좌 우의 이동거리가 최대 k-1칸인 녀석만 고르자
				if(r>=0 && c>=0 && r<N && c<N && (Math.abs(a-r)+Math.abs(b-c)<k)) {
					if(map[r][c]==1) cnt++; // 집이면 집 개수 늘려주기
				}
			}
		}
		
		return cnt;
	}
}
