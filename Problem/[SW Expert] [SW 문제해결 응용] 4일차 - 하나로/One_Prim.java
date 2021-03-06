//[SW Expert] [S/W 문제해결 응용] 4일차 - 하나로(Prim)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class One_Prim {
	private static int N;
	private static long [][] adjMatrix;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			
			int [] x = new int[N];
			int [] y = new int[N];
			
			// N개의 섬 x 좌표 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<N;i++) {
				x[i]=Integer.parseInt(st.nextToken());
			}
			// N개의 섬 y 좌표 
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<N;i++) {
				y[i]=Integer.parseInt(st.nextToken());
			}
			
			// 인접 행렬 구하기
			adjMatrix = new long[N][N];
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					adjMatrix[i][j] = adjMatrix[j][i] = getDistance(x[i],x[j],y[i],y[j]);
				}
			}
			// 환경 세율 실수
			double E = Double.parseDouble(br.readLine());
			
			
			
			System.out.println("#"+tc+" "+Math.round(E*makeMST()));
		}
	}

	private static long makeMST() {//Prim 코드에 필요한거 넣어주기
		
		long [] minEdge = new long[N];
		boolean [] visited = new boolean[N];
		
		long result = 0; // 최소 신장트리 비용 저장
		int cnt=0; // 처리할 정점 수
		
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0; // 0섬을 시작섬으로
		
		
		while(true) {
			
			// 1단계 : 신장트리에 포함되지 않은 정점 중에서 최소간선 비용의 정점을 선택
			long min = Long.MAX_VALUE;
			int minNo = 0;
			
			for(int i=0;i<N;i++) {
				if(!visited[i] && min>minEdge[i]) {
					min = minEdge[i];
					minNo= i;
				}
			}
			
			visited[minNo] = true; // 정점 방문 처리(신장트리에 포함시킴)
			result += min; // 간선 비용 누적
			if(++cnt == N) break;
			
			// 2단계 : 선택된 정점에서 신장트리에 포함되지 않은 다른 정점들로의 간선 비용을 고려하여 minEdge 업데이트
			for(int i=0;i<N;i++) {
				if(!visited[i] && adjMatrix[minNo][i]>0 && minEdge[i] > adjMatrix[minNo][i]) {
					minEdge[i] = adjMatrix[minNo][i];
				}
			}
		}
		
		
		
		
		return result;
	}

	private static long getDistance(int x1, int x2, int y1, int y2) {
		long dist = 0;
		dist = (long) (Math.pow((x1-x2), 2) +  Math.pow((y1-y2), 2));
		return dist;
	}
}
