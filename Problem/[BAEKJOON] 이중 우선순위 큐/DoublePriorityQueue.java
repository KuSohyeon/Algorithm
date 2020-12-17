//[백준] 이중 우선순위 큐

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DoublePriorityQueue {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				char ch = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				if (ch == 'I') {
					if(!map.containsKey(num)) {
						map.put(num, 1);
					}else {
						map.put(num, 1+map.get(num));
					}
				} else {
					if(map.isEmpty()) continue;
					if(num==1) {
						int max = map.lastKey();
						if(map.get(max)==1) {
							map.remove(max);
						}else {
							map.put(max, map.get(max)-1);
						}
					}else {
						int min = map.firstKey();
						if(map.get(min)==1) {
							map.remove(min);
						}else {
							map.put(min, map.get(min)-1);
						}
					}
				}
			}
			
			if(!map.isEmpty()) {
				System.out.println(map.lastKey()+" "+map.firstKey());
			}else {
				System.out.println("EMPTY");
			}

		}
	}
}
