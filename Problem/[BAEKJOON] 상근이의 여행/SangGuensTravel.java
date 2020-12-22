//[백준] 상근이의 여행
// 최소 비행기 종류의 수....

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SangGuensTravel {
	static int N,M,airplane;
	static boolean [] v;
	static List<Integer> [] list;
	static class Data implements Comparable<Data>{
		int now;
		int cnt;
		public Data(int now, int cnt) {
			super();
			this.now = now;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Data o) {
			return Integer.compare(this.cnt,o.cnt);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			list = new ArrayList [N+1];
			v= new boolean[N+1];
			
			for(int i=1;i<=N;i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			for(int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
				list[b].add(a);
			}
			
			System.out.println(N-1); // 어차피 왕복타더라도 비행기의 종류는 같다;;;
		}
	}
}
