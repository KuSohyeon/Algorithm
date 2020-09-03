//[정올] 해밀턴 순환회로
/*
5
0 14 4 10 20
14 0 7 8 7
4 5 0 7 16
11 7 9 0 2
18 7 17 4 0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CircularCircuit {
	static int [][] map;
	static int N , sum, result,min;
	static boolean[] v;
	static int [] distance;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		v = new boolean [N];
		distance = new int[N+1];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		// min값 최대로 초기화
		min = Integer.MAX_VALUE;
		
		v[0] = true;
		dfs(0,0,0);

		System.out.println(min);
		
		br.close();
		
	}
	private static void dfs(int cnt, int current, int sum) {
		if(sum>min) { //sum이 min보다 크다면 끝까지 계산해 줄 필요없음
			return;
		}
		if(cnt==N-1) { // 다 갔으면 회사로 돌아간 후 return
			if(map[current][0]==0) return; // 만약 회사로 돌아갈 수 있는 방법이 없다면 return
			result = sum + map[current][0];
			min = Math.min(min, result);
			return;
		}
		for(int i= 1;i<N;i++) {
			if(v[i] || map[current][i] == 0) continue; // 방문했던 곳이거나 갈 수없는 곳이면  continue
			v[i] = true;  // 갈 수 있다면 방문체크해주고
			dfs(cnt+1,i,sum + map[current][i]); // dfs로 고고
			v[i]=false; // 백트래킹 
		}
		
	}

}

