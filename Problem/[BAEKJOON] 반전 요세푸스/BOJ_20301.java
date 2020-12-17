//[백준] 반전 요세푸스

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BOJ_20301 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		List<Integer> list = new ArrayList<>();

		int N = sc.nextInt();
		int K = sc.nextInt();
		int M = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		int index = 0, cnt = 0;
		boolean flag = true;
		while (list.size() != 0) {
			if (cnt == M) { // M명의 사람이 제거 될 경우 방향 전환
				flag = !flag;
				cnt = 0;
			}
			if (flag) { // 오른쪽의 K번째 사람 제거
				index += K - 1; // 제거를 하게 되면 index 하나씩 땡겨지기 때문에 -1 해줘야함 (초기에는 0번째 사람 부터 세리므로 -1)

				if (index >= list.size()) {
					index %= list.size();
				}

				sb.append(list.get(index)).append("\n");
				list.remove(list.get(index));

				cnt++; // 제거한 사람 수 증가
			} else { // 왼쪽의 K번째 사람 제거
				index -= K;
				if (K >= list.size()) {
					index %= list.size();
				}
				if (index < 0) {
					index += list.size();
				}

				sb.append(list.get(index)).append("\n");
				list.remove(list.get(index));

				cnt++;// 제거한 사람 수 증가
			}
		}

		System.out.println(sb.toString());
	}
}
