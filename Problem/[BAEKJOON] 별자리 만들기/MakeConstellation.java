//[백준] 별자리만들기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MakeConstellation {
	static int n;
	static double [][] input;
	static double [][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		n = Integer.parseInt(br.readLine());
		
		input = new double [n][2];
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Double.parseDouble(st.nextToken());
			input[i][1] = Double.parseDouble(st.nextToken());
		}
		
		// 행렬 만들기
		map = new double [n][n];
		
		for(int i=0;i<n-1;i++) {
			for(int j=i+1;j<n;j++) {
				double dist = Math.sqrt((Math.pow(Math.abs(input[i][0]-input[j][0]),2) + Math.pow(Math.abs(input[i][1]-input[j][1]),2)));
				map[i][j] =dist;
				map[j][i] = dist;
			}
		}
		
		double answer = prim();
		
		System.out.println(Math.round(answer*100)/100.0);
		
	}
	private static double prim() {
		double result = 0;
		double min = 987654321;
		int idx = 0;
		boolean [] v = new boolean[n];
		double [] distance = new double[n];
		
		Arrays.fill(distance, 987654321);
		distance[0] = 0;
		
		for(int m=0;m<n;m++) {
			min = 987654321;
			idx = 0;
			for(int i=0;i<n;i++) {
				if(!v[i] && min>distance[i]) { // 최소값 찾아주기
					min = distance[i];
					idx = i;
				}
			}
			
			v[idx] = true; // 방문처리
			result += min; // 최소 비용 누적
			
			for(int i=0;i<n;i++) {
				if(idx==i) continue;
				if(!v[i] && distance[i] > map[idx][i]) { // distance 갱신
					distance[i] = map[idx][i];
				}
			}
		}
		return result;
	}
}
