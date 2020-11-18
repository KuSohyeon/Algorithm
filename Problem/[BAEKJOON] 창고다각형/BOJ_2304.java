///[백준] 창고다각형

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2304 {
	static int N;
	static int[] map;
	static Pair[] input;

	static class Pair implements Comparable<Pair> {
		int x;
		int y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(this.x, o.x);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		input = new Pair[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			input[i] = new Pair(x, y);
		}

		Arrays.sort(input);

		map = new int[input[N - 1].x + 1];
		// 일단 sort후 1차원 배열에 setting
		for (int i = 0; i < N; i++) {
			int x = input[i].x;
			int y = input[i].y;
			map[x] = y;
		}

		// 앞에서 부터 기준이랑 비교하면서 채우기 -> 기준보다 커지는 경우 기준 바꿔주기
		int result = 0, index = 0;

		for (int i = 1; i < map.length; i++) {
			if (map[index] > map[i])
				continue;
			for (int j = index; j < i; j++) {
				map[j] = map[index];
			}
			index = i;
		}

		// 뒤에서부터 놓친 부분 다시 채우기
		index = map.length - 1;

		for (int i = index - 1; i >= 0; i--) {
			if (map[index] > map[i])
				continue;
			for (int j = index - 1; j > i; j--) {
				map[j] = map[index];
			}
			index = i;
		}
		
		// 결과
		for (int m : map) {
			result += m;
		}
		System.out.println(result);
	}

}
