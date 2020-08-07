//백준(5014번) - 스타트링크(BFS)

/*
 10 1 10 2 1
 출력 : 6
 100 2 1 1 0
 출력 : use the stairs
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StartLink {
	static int [] elevator;
	static boolean [] v;
	static int move;
	static int F,S,G,U,D;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//입력
		F = sc.nextInt(); //건물 층수 정보
		S = sc.nextInt(); //강호 위치
		G = sc.nextInt(); //스타트링크 위치 
		U = sc.nextInt(); //up 층
		D = sc.nextInt(); //down 층
		
		elevator = new int [F+1];
		v = new boolean [F+1];
		
		
		bfs(new Data(S,G,0));
		
		if(v[G]) {
			System.out.println(move);
		}
		else {
			System.out.println("use the stairs");
		}
		
		
		
		sc.close();
		
		
	}
	private static void bfs(Data data) {
		Queue<Data> q = new LinkedList<>();
		
		q.offer(data);
		v[data.start] = true;
		
		Data cur;
		int floor;
		while(!q.isEmpty()) {
			
			cur=q.poll();
			if(cur.start == cur.end) {
				move = cur.cnt;
			}
			
			floor = cur.start+U;
			if(floor<=F && !v[floor]) {
				q.offer(new Data(floor,cur.end,cur.cnt+1));
				v[floor]=true;
			}
			floor = cur.start-D;
			if(floor>0 && !v[floor]) {
				q.offer(new Data(floor,cur.end,cur.cnt+1));
				v[floor]=true;
			}
	
			
		}
		
	}
	static class Data{
		int start;
		int end;
		int cnt;
		
		public Data(int start, int end, int cnt) {
			this.start = start;
			this.end = end;
			this.cnt = cnt;
		}
		
		@Override
		public String toString() {
			return "Data [start=" + start + ", end=" + end + ", cnt=" + cnt + "]";
		}
		
		
	}
}
