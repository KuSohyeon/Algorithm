// [백준 ] - 도시 분할 계획
/*
7 12
1 2 3
1 3 2
3 2 1
2 5 2
3 4 4
7 3 6
5 1 5
1 6 2
6 4 1
6 5 3
4 5 3
6 7 4
출력 : 8
 */

import java.util.Arrays;
import java.util.Scanner;

public class SeperateCity {
	static Data [] arr;
	static int N,M;
	static int [] p;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new Data[M];

		findset();

		for(int i=0;i<M;i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();

			arr[i]=new Data(from,to,weight);

		}

		Arrays.sort(arr); //가중치 오름차순으로 정렬

		int sum=0;
		int max=0;

		//집 연결하기
		for(int i=0;i<arr.length;i++) {
			if(union(arr[i].from,arr[i].to)) { 
				if(max<arr[i].weight) {//가장 가중치가 높은 집을 하나 빼주기
					max=arr[i].weight; 
				}
				sum+=arr[i].weight;//
			}
		}

		System.out.println(sum-max);
	}
	public static void findset() {
		p = new int[N+1];
		for(int i=1;i<=N;i++) {
			p[i]=i;
		}

	}
	public static int findSet(int x) {
		if(p[x]==x) {
			return x;
		}
		return p[x]=findSet(p[x]);
	}

	public static boolean union(int x,int y) {
		x = findSet(x);
		y = findSet(y);

		if(x!=y) {
			p[y]=x;
			return true;
		}
		return false;
	}
	static class Data implements Comparable<Data>{

		int from;
		int to;
		int weight;

		public Data(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}


		@Override
		public int compareTo(Data o) {

			return this.weight-o.weight;

		}

	}

}
