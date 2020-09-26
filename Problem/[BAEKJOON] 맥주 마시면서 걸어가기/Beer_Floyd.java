// [백준] 맥주마시면서 걸어가기 (플로이드-와샬)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beer_Floyd {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			int N = Integer.parseInt(br.readLine());

			int [][] loc = new int[N+2][N+2];
			boolean [][] D = new boolean[N+2][N+2];
			
			for(int i=0;i<N+2;i++) {
				st = new StringTokenizer(br.readLine().trim());
				loc[i][0]=Integer.parseInt(st.nextToken()); // x 좌표
				loc[i][1]=Integer.parseInt(st.nextToken());	// y 좌표			
			}
			// 인접 행렬 만들기
			for(int i=0;i<N+2;i++) {
				for(int j=0;j<N+2;j++) {
					if(i!=j && Math.abs(loc[i][0]-loc[j][0])+Math.abs(loc[i][1]-loc[j][1])<=1000) { // 자기자신으로 가는 거 안되고 거리가 1000이하면 갈 수 있는 거 표시해주기
						D[i][j]=true; 
					}
				}
			}
			// 플로이드-와샬
			for(int k=0;k<N+2;k++) {//경유지
				for(int i=0;i<N+2;i++) {//출발지
					if(k==i) continue; // 경유지 = 출발지 경우 걸러주기
					for(int j=0;j<N+2;j++) {//도착지
						if(i==j || k==j) continue; //경유지=출발지=도착지 경우 걸러주기
						if(!D[i][j]) { // i에서 j에서 갈 수 없는 경우
							if(D[i][k] && D[k][j]) D[i][j]=true; // 경유지 거쳐서 갈 수 있으면 true로 만들어주기 
						}
					}
				}
			}
			
			if(D[0][N+1]) System.out.println("happy"); // 출발지에서 편의점 통해서 락페까지 갈 수 있을 경우 happy
			else System.out.println("sad"); // 도중에 맥주 다 마시면 sad

		}
		br.close();

	}
}
