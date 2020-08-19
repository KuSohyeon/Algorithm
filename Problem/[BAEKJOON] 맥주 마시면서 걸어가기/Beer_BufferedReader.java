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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beer_BufferedReader {
	static String feel;
	static Data [] arr;
	static boolean [] v;
	static int conv;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<T;tc++) {
			feel = "sad";
			conv = Integer.parseInt(br.readLine());
			
			//편의점 수 + 상근이 집 + 페스티벌 좌표
			arr = new Data[conv+2];
			v = new boolean[conv+2];
			
			//좌표 다 넣어주기
			for(int i=0;i<conv+2;i++) {
				st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				arr[i] = new Data(x,y);
			}
			
			bfs();
			
			System.out.println(feel);
			
		}
		br.close();
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
			for(int i=1;i<conv+2;i++) {//배열 돌아가면서 조건에 맞는 편의점 넣어주기(순서상관X)
				if(v[i]) {
					continue;
				}
				if(Math.abs(cur.x - arr[i].x)+Math.abs(cur.y-arr[i].y)<=1000) { 
					q.offer(arr[i]);//조건 충족할때만 큐에 넣어주기
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
