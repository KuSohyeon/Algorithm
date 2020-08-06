//백준(2644번) - 촌수계산

/*
9
7 3
7
1 2
1 3
2 7
2 8
2 9
4 5
4 6
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,m,from,to,p1,p2;
	static ArrayList<Integer> [] arr;
	static boolean [] visit;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();//전체 사람의 수
		p1 = sc.nextInt();//촌수 계산해야 하는 사람 1
		p2 = sc.nextInt();//촌수 계산해야 하는 사람 2
		m = sc.nextInt(); //관계의 개수
		
		arr = new ArrayList[n+1]; //0은 사용안할거니까 n+1개로 만들어주기
		visit = new boolean[n+1];
		
		for(int i=0;i<=n;i++) {
			arr[i]=new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			from = sc.nextInt(); //부모
			to = sc.nextInt(); //자식
			
			arr[from].add(to);//부모와 자식 연결하기
			arr[to].add(from); //부모와 자식의 관계는 양방향
		}
		

		dfs(p1,p2,0);
		if(!visit[p2]) {
			cnt=-1;
		}
		System.out.println(cnt);
		
		sc.close();
	}
	private static void dfs(int start,int end,int count) {
		if(start==end) {
			visit[end]=true;
			cnt=count;
			return;
		}
		visit[start]=true;
		for(int person : arr[start]) {
			if(!visit[person]) {
				dfs(person,end,count+1);
			}
		}
	}
	


}
