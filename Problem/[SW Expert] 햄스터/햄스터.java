//[SW Expert] 햄스터
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 햄스터 {
	static int N, X, M, max; // 집의 갯수, 집의 최대 정원, 기록의 수, 지금까지 알려진 최대 햄스터 수
	static int[] ans;
	static int[] cage;
	static int[] sum;
	static Data[] datas;

	static class Data {
		int l, r, s;

		public Data(int l, int r, int s) {
			super();
			this.l = l;
			this.r = r;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Data [l=" + l + ", r=" + r + ", s=" + s + "]";
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			max = -1; // tc마다 초기화

			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			datas = new Data[M];
			cage = new int[N + 1];
			sum = new int[N + 1];
			ans = new int[N + 1];

			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				datas[i] = new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
			}
			solve(1);
			if (max == -1) {
				System.out.println("#" + tc + " " + max);
			} else {
				StringBuilder sb = new StringBuilder("#" + tc + " ");
				for (int i = 1; i < ans.length; i++) {
					sb.append(ans[i]).append(" ");
				}
				System.out.println(sb.toString());
			}
		}
	}

	static void solve(int idx) {
		// idx가 N번째 집까지 완료한 후에 N+1에 도달하면 기저파트
		if (idx == N + 1) {
			// datas 배열에 들어있는 햄스터 마리수에 일치하는 조합인지 검사.
			for (int i = 0; i < M; i++) {
				// data[i].l부터 data[i].r까지의 햄스터 마리수가 data[i].s 마리가 아니라면
				if (sum[datas[i].r] - sum[datas[i].l - 1] != datas[i].s) {
					return;
				}
			}
			// 여기 까지 왔다는 건 M개의 기록을 모두 만족한다는 것
			if (max < sum[N]) {
				// 갱신
				max = sum[N];
				for (int i = 1; i <= N; i++) {
					ans[i] = cage[i];
				}
			}
			return;
		}

		for (int hamster = 0; hamster <= X; hamster++) {
			cage[idx] = hamster;
			sum[idx] = sum[idx - 1] + hamster;
			// 다음 집 햄스터 넣어주러 가자
			solve(idx + 1);
		}
	}
}
