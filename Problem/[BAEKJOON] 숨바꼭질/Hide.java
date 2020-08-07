//백준(1697번) - 숨바꼭질
/*
 * 5 17
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Hide {
	static int N,M;
	static boolean [] v;
	static Queue<Data> q ;
	static int sec;
	static int [] move= {1,-1,2};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();//수빈
		M = sc.nextInt();//동생

		v = new boolean [100000+1];

		bfs(N, M);

		System.out.println(sec);

	}
	static void bfs(int start, int end) {
		q = new LinkedList<Data>();

		
		q.offer(new Data(start,0));
		v[start]=true;

		int idx;
		while(!q.isEmpty()) {
			Data cur;
			cur = q.poll();
			//멈추기
			if(cur.start==M){
				sec = cur.cnt;
				break;
			}
			//앞으로 한칸
			if(cur.start+1<=100000 && !v[cur.start+1]) {
				idx = cur.start+1;
				q.offer(new Data(idx,cur.cnt+1));
				v[idx]=true;
			}
			//뒤로한칸
			if(cur.start-1>=0 && !v[cur.start- 1]) {
				idx = cur.start-1;
				q.offer(new Data(idx,cur.cnt+1));
				v[idx]=true;
			}
			//앞으로 점프
			if(cur.start*2<=100000 && !v[cur.start*2]) {
				idx = cur.start*2;
				q.offer(new Data(idx,cur.cnt+1));
				v[idx]=true;
			}


		}


	}
	static class Data{

		int start;
		int cnt;

		public Data(int start, int cnt) {
			this.start = start;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Data [start=" + start + ", cnt=" + cnt + "]";
		}


	}

}
