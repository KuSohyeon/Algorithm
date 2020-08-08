//[SW Expert] 창용 마을 무리의 개수
/*
2
6 5
1 2
2 5
5 1
3 4
4 6
6 8
1 2
2 5
5 1
3 4
4 6
5 4
2 4
2 3

출력:

#1 2
#2 1
 */

import java.util.Scanner;

public class ChangyongTown {
	static int N,M;
	static int [] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			
			makeSet();
			
			for(int i=0;i<M;i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				union(a,b);
			}
			
			int result=0;//tc마다 초기화
			for(int i=1;i<=N;i++) {
				if(p[i]==i) {
					result++;
				}
			}
			
			System.out.println("#"+tc+" "+result);
		}
		sc.close();
	}
	private static void union(int a, int b) {
		a=findSet(a);
		b=findSet(b);
		if(a!=b) {
			p[b]=a;
		}
		
	}
	private static int findSet(int a) {
		if(p[a]==a) {
			return a;
		}
		return p[a]=findSet(p[a]);
	}
	private static void makeSet() {
		p = new int[N+1];
		
		for(int i=1;i<=N;i++) {
			p[i]=i;
		}
		
	}
}
