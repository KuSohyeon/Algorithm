//[SW Expert] 서로소집합(Disjoint-Set)
/*
1
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1
 */

import java.util.Scanner;

public class DisjointSet {
	static int [] p;
	static int n,m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int tc=1;tc<=T;tc++) {
			
			n = sc.nextInt(); //집합 개수
			m = sc.nextInt(); //연산 개수
			
			p = new int[n+1];//1~n개 사용
			
			makeSet();
			
			StringBuilder result = new StringBuilder();
			
			for(int i=0;i<m;i++) {
				int mode = sc.nextInt();
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				switch(mode) {
				case 0:
					union(a,b);
					break;
				case 1:
					if(findSet(a)==findSet(b)) {
						result.append("1");
					}
					else result.append("0");
				}
			}
			
			
			System.out.println("#"+tc+" "+result.toString());

		}
		sc.close();
	}
	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		
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
		for(int i=1;i<=n;i++) {
			p[i]=i;
		}
		
	}
}
