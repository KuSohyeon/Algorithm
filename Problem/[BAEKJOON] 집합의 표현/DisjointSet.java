//백준(1717번) - 집합의 표현(Disjoint-Set)
/*
7 8
0 1 3
1 1 7
0 7 6
1 7 1
0 3 7
0 4 2
0 1 1
1 1 1

출력
NO
NO
YES
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DisjointSet {

	static int n,m;
	static int [] p;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());//집합의 개수
		m = Integer.parseInt(st.nextToken()); //연산의 개수
		
		p = new int [n+1];//1부터 사용
		makeSet();
		
		int mode,a,b;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine()," ");
			mode = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			switch(mode) {
			case 0:
				union(a,b);
				break;
			case 1:
				System.out.println(findSet(a)==findSet(b)?"YES":"NO");
				break;
			}
		}
	}
	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a!=b) {
			p[b]=a;
		}
		
	}
	private static int findSet(int a) {
		if(a==p[a]) { 
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
