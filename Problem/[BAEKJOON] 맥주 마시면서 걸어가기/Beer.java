//[ BAEKJOON ] 맥주 마시면서 걸어가기(BFS)

/*
2
2
0 0
1000 0
1000 1000
2000 1000
2
0 0
1000 0
2000 1000
2000 2000

출력 : 
happy
sad
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Beer {
	static String feel;
	static Data [] arr;
	static boolean [] v;
	static int conv;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int tc=0;tc<T;tc++) {
			feel = "sad";
			conv = sc.nextInt();
			
			arr = new Data[conv+2];
			v = new boolean[conv+2];
			

			for(int i=0;i<conv+2;i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
				arr[i] = new Data(x,y);
			}
			
			bfs();
			
			System.out.println(feel);
			
		}
		sc.close();
	}
	
	private static void bfs() {
		Queue<Data> q = new LinkedList<>();
		
		Data start = arr[0];
		Data end = arr[conv+1];
		
		q.offer(start);
		v[0]=true;
		Data cur;
		
		while(!q.isEmpty()) {

			cur = q.poll();
			if(cur.equals(end)) {
				feel = "happy";
				break;
			}
			for(int i=1;i<conv+2;i++) {
				if(v[i]) {
					continue;
				}
				if(Math.abs(cur.x - arr[i].x)+Math.abs(cur.y-arr[i].y)<=1000) {
					q.offer(arr[i]);
					v[i]=true;
	
				}
			}

		}

		
	}

	static class Data{
		int x;
		int y;
		
		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Data [x=" + x + ", y=" + y + "]";
		}

	}
}
