//[백준] 목장 건설하기
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuildingPasture {
	static int M, N, result;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M + 1][N + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				int in = Integer.parseInt(st.nextToken());
				if (in == 0) {// 장애물일때는 어차피 map[i][j]=0
					map[i][j] = 1;
					// 점화식
					map[i][j] = min(map[i - 1][j - 1], map[i][j - 1], map[i - 1][j]) + 1;
					// 가장 큰 사각형
					result = result > map[i][j] ? result : map[i][j];
				}
			}
		}
		System.out.println(result);
	}
	// 최소값 구하는 method
	private static int min(int a, int b, int c) {
		int min = 987654321;
		min = a > b ? b : a;
		min = min > c ? c : min;
		return min;
	}

}
