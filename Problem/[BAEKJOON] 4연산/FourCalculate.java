//[백준] 4연산

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FourCalculate {
	static long s,t;
	static boolean [] v;
	static class Data{
		long num;
		String pos;
		public Data(long num, String pos) {
			super();
			this.num = num;
			this.pos = pos;
		}
		@Override
		public String toString() {
			return "Data [num=" + num + ", pos=" + pos + "]";
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		s = sc.nextLong();
		t = sc.nextLong();
		
		if(s==t) System.out.println(0);
		else bfs();
		
		
	}
	private static void bfs() {
		Queue<Data> q = new LinkedList<Data>();
		v = new boolean[(int) t+1];
		
		q.offer(new Data(s,""));
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			
			if(cur.num == t) {
				System.out.println(cur.pos);
				return;
			}
			
			long tmp = 0;
			String tmpPos = "";
			for(int p=0;p<4;p++) {
				switch(p) {
				case 0: 
					tmp = cur.num * cur.num;
					tmpPos = cur.pos+"*";
					break;
				case 1:
					tmp = cur.num + cur.num;
					tmpPos = cur.pos+"+";
					break;
				case 2:
					tmp = cur.num - cur.num;
					tmpPos = cur.pos+"-";
					break;
				case 3:
					tmp = cur.num / cur.num;
					tmpPos = cur.pos+"/";
					break;
				}
				if(tmp>t || tmp<1 || v[(int) tmp]) continue;
				v[(int) tmp]=true;
				q.offer(new Data(tmp,tmpPos));
			}
		}
		
		System.out.println(-1);
	}
}
