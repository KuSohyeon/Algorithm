//정올(1863번) - 종교  (Disjoint-set)
/*
10 9
1 2
1 3
1 4
1 5
1 6
1 7
1 8
1 9
1 10
 */

import java.util.Scanner;

public class JongGyo {
	
	static int [] p;
	static int n,m;
	static int cnt;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt(); // 학생 수
		m = sc.nextInt(); // 쌍의 수
		
		p = new int[n+1]; //부모 배열 생성해주기 1~10까지 쓰니까 n+1
		
		makeSet();
		
		int i,j;
		for(int d=0;d<m;d++) { //i,j는 같은 종교를 가진 쌍의 학생들
			i = sc.nextInt(); //학생1
			j = sc.nextInt(); //학생2
			
			union(i,j); //합집합

		}
		for(int d=1;d<=n;d++) {//자기자신이 대빵인 사람을 찾으면 종교개수 세릴 수 있음
			if(p[d]==d) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
		
		sc.close();
	}

	private static void union(int x, int y) {//합치는 메소드
		x=findSet(x);
		y=findSet(y);
		if(x!=y) {
			p[y]=x;
		}
		
	}

	private static void makeSet() { //독립적인 집합 생성해주는 메소드
		for(int i=0;i<=n;i++) {
			p[i]=i;
		}
		
	}
	private static int findSet(int x) { //부모 찾는 메소드
		if(p[x]==x) {
			return x;
		}
		p[x]=findSet(p[x]);
		return p[x];
	}
}
