//[백준] 1753번 - 최단경로
/*
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {
	static ArrayList<Node> [] arr;
	static int V,E,K;
	static int [] distance;
	static boolean [] v;
	static int INFI = Integer.MAX_VALUE;
	static class Node implements Comparable<Node>{
		private int index;
		private int cost;
		
		public Node(int index, int cost) {
			super();
			this.index = index;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost == o.cost? Integer.compare(this.index, o.index) : Integer.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt(); //정점의 수
		E = sc.nextInt(); //간선의 개수
		K = sc.nextInt();
		arr = new ArrayList [V+1]; //정점의 개수만큼 생성
		distance = new int[V+1]; //정점의 개수만큼 생성
		v = new boolean[V+1]; //정점의 개수만큼 생성
		
		
		//각 배열의 원소마다 객체 할당
		for(int i=0;i<=V;i++) {
			arr[i] = new ArrayList<Node>();
		}
		
		for(int i=0;i<E;i++) {
			arr[sc.nextInt()].add(new Node(sc.nextInt(),sc.nextInt()));
		}
		
		
		dijkstra(K);
	}
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		Arrays.fill(distance, INFI);
		distance[start]=0;
		
		pq.offer(new Node(start,distance[start])); //처음에는 시작 정점을 넣어주기( 내 정점에서 내 정점으로 가는 비용은 0)
		
		while(!pq.isEmpty()) { //우선순위 큐가 빌때까지 반복하면서
			Node cur = pq.poll();
			
			int minIndex = cur.index; 
			
			v[minIndex] = true; //최소 인덱스니까 방문처리해주기
			
			for(int i=0;i<arr[cur.index].size();i++) {
				Node temp = arr[cur.index].get(i); //값 비교를 위한 임시 노드에 현재 노드에서 연결되는 노드와 가중치 넣어주기
				if(!v[temp.index] && distance[temp.index]>temp.cost+distance[minIndex]) {  
					distance[temp.index] = temp.cost+distance[minIndex];
					pq.offer(new Node(temp.index,distance[temp.index]));
				}
			}
		}
		
		for(int i=1;i<=V;i++) {
			if(distance[i]==INFI) {
				System.out.println("INF");
			}
			else System.out.println(distance[i]);
		}
	}
}
