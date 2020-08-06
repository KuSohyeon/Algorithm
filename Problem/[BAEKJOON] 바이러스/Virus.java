//백준(2606번) - 바이러스
/*
7
6
1 2
2 3
1 5
5 2
5 6
4 7
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Virus {
	static int N,M,cnt=0;
	static boolean [] visit;
	static ArrayList<Integer> [] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); //컴퓨터 수
		M = sc.nextInt(); //간선 수
		
		arr = new ArrayList[N+1];
		
		for(int i=0;i<=N;i++) {//배열의 요소마다 객체 생성해주고 사용하기(이거 안하면 nullPoinException남)
			arr[i] = new ArrayList<Integer>();
		}
		
		int from,to;
		for(int i=0;i<M;i++) {
			from = sc.nextInt();
			to = sc.nextInt();
			//무방향이니까 양쪽으로 추가해주기
			arr[from].add(to);
			arr[to].add(from);
		}
		
		bfs();
		System.out.println(cnt);
		
		sc.close();
	}
	private static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		visit = new boolean[N+1]; //1부터 N개라서 N=1해줌 , 0안쓸거야
		
		queue.offer(1);
		visit[1]=true;
		
		int cur;
		while(!queue.isEmpty()) {
			cur=queue.poll(); 
			//to-do
			for(int idx : arr[cur]) { //ArrayList의 cur행의 요소들 넣어주기
				if(visit[idx]) {
					continue;
				}
				queue.offer(idx);
				visit[idx]=true;
				cnt++;
				
			}
		}
		
	}
}
