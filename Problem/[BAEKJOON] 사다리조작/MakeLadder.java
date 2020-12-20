//[백준] 사다리 조작

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakeLadder {
	static int N, M, H, size, result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			map[y - 1][x-1] = 1;
		}

		result = Integer.MAX_VALUE;
		size = H * N;

		dfs(0, 0);

		System.out.println(result==Integer.MAX_VALUE?-1:result);
	}
	private static void dfs(int num, int cnt) {
		if (check()) { // 사다리 만족하는지 확인하는 method
			if(result>cnt) {
				result = cnt;
			}
			if(result==0) { // 0보다 작은 경우는 없으니 시스템 종료
				System.out.println(0);
				System.exit(0);
			}
			return;
		}
		if(num>=size) return; // 2차원 배열의 크기가 넘어가면 return
		if (cnt >= 3) return; // 최대 사다리 개수 = 3
		
		int y = num / N;
		int x = num % N;
		
		if(map[y][x]==1) dfs(num+2,cnt); // 만약 이미 선인 경우 그 옆도 안되니까 바로 +2해서 보내주기
		else { // 사다리를 놓을 수 있는지 없는지 판단하는 경우
			int possible=0;
			if(x-1>=0 && map[y][x-1]==0) {
				possible++;
			}else if(x-1<0) {
				possible++;
			}
			if(x+1<N-1 && map[y][x+1]==0) {
				possible++;
			}else if(x==N-2) {
				possible++;
			}
			if(possible==2) {
				map[y][x]=1;
				dfs(num+2, cnt+1);
				map[y][x]=0; // 백트래킹
			}
			dfs(num+1,cnt); // 일단 그냥 안하고 보내주는것도 해주기
		}
	}
	// 사다리가 조작되어있는지 확인하는 method
	private static boolean check() {
		for (int n = 0; n < N; n++) {
			int i = 0, j = n;
			while (i < H) {
				if (map[i][j] == 1) { // 만약 1이라면 오른쪽으로 움직이기 + 한칸 내리기
					j++;
					i++;
				} else {
					if(j-1>=0 && map[i][j-1]==1) { // 왼쪽이 1이면 왼쪽으로 움직이기 + 한칸 내리기
						j--;
						i++;
					}else i++; // 그 외 모두 그냥 내려가기
				}
			}
			if (j != n) // 한 칸이라도 안맞을 경우 false 리턴
				return false;
		}
		// 모두 경우가 만족할 때 true리턴
		return true;
	}
}
