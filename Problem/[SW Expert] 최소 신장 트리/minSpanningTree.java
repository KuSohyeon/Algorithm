import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//SW Expert 최소 스패닝 트리

/*
1
3 3
1 2 1
2 3 2
1 3 3
*/

public class minSpanningTree {
	
	
	static int T,V,E,A,B,C;
	static Data [] arr;
	static int p[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine()," ");
			V = Integer.parseInt(st.nextToken());//정점의 개수
			E = Integer.parseInt(st.nextToken());//간선의 개수
			
			arr = new Data[E];
			p = new int[V+1];
			makeSet();
			
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine()," ");
				A = Integer.parseInt(st.nextToken());//from
				B = Integer.parseInt(st.nextToken());//to
				C = Integer.parseInt(st.nextToken());//weight
				
				arr[i]= new Data(A,B,C);
			}
			
			Arrays.sort(arr); //가중치 낮은 순으로 정렬
			
			
			int cnt=0;
			long result=0;
			for(int i=0;i<E;i++) {
				if(cnt==V-1) {//최소 신장트리의 간선의 개수는 정점 -1개
					break;
				}
				if(union(arr[i].from,arr[i].to)) {//부모가 다르면 합치기
					result += arr[i].weight;
					cnt++;
				}
			}
			
			
			
			System.out.println("#"+tc+" "+result);
		}
		br.close();
	}
	
	private static boolean union(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y) return false;
		p[y]=x;
		return true;
	}

	private static int findSet(int x) {
		if(x==p[x]) return x;
		else {
			p[x]=findSet(p[x]);
			return p[x];
		}
		
	}

	private static void makeSet() {
		for(int i=0;i<=V;i++) {
			p[i]=i;
		}
	}

	static class Data implements Comparable<Data>{
		int from,to,weight;

		public Data(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Data [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Data o) {
			
			return this.weight - o.weight;
		}
		
	}
}
