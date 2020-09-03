//[SW Expert] [S/W 문제해결 응용] 4일차 - 하나로(Kruskal)
/*
10
2
0 0
0 100
1.0
4
0 0 400 400
0 100 0 100
1.0
6
0 0 400 400 1000 2000
0 100 0 100 600 2000
0.3
9
567 5 45674 24 797 29 0 0 0
345352 5464 145346 54764 5875 0 3453 4545 123
0.0005
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class One_Kruskal {
	static long result;
	static List<Data> dist;
	static int N, size;
	static double E;
	static int [] x,y,p,tmp;
	static class Data implements Comparable<Data>{
		int a;
		int b;
		long cost;
		public Data(int a, int b, long cost) {
			super();
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		@Override
		public int compareTo(Data o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			result = 0; //tc마다 초기화
			
			N = Integer.parseInt(br.readLine());
			
			x = new int[N];
			y = new int[N];
			dist = new ArrayList<Data>(); // 간선 정보를 저장할 배열
			tmp = new int[2];
			size = N*(N-1)/2;
			
			// x 좌표 입력
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<N;i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}
			// y좌표 입력
			st = new StringTokenizer(br.readLine().trim());
			for(int i=0;i<N;i++) {
				y[i] = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine()); //환경 부담 세율 실수 E
			
			comb(0,0); // 간선 비용 리스트 만들기
			
			
			System.out.println("#"+tc+" "+Math.round(E * Kruskal()));
		}
	}
	private static long Kruskal() { //kruskal을 이용하여 MST 구하기
		int cnt=0;
		
		Collections.sort(dist);
		makeSet();
		
		for(Data d : dist) {
			if(cnt==N-1) {
				break;
			}
			if(!union(d.a,d.b)) continue;
			result += d.cost;
			cnt++;
		}
		
		return result;
		
	}
	private static void comb(int cnt, int start) { // 두개의 정점을 선택하여 간선비용을 구하는 메소드(무방향 -> 순서x)
		if(cnt==2) { // 2개 뽑으면 바로 간선비용 구하러 가기
			calEdge(tmp);
			return;
		}
		for(int i=start;i<N;i++) {
			tmp[cnt] = i;
			comb(cnt+1,i+1);
		}
		
	}
	private static void calEdge(int [] arr) { // 간선의 비용을 계산하는 메소드
		long distance = (long) (Math.pow(x[arr[0]]-x[arr[1]], 2) + Math.pow(y[arr[0]]-y[arr[1]], 2));
		dist.add(new Data(arr[0],arr[1],distance)); //구한 간선 비용  dist 리스트에 더하기
	}
	//MST - Kruskal에 필요한 메소드
	private static void makeSet() {
		p = new int[N];
		for(int i=0;i<N;i++) {
			p[i]=i;
		}
	}
	private static int findSet(int a) {
		if(a==p[a]) {
			return a;
		}
		return findSet(p[a]);
	}
	private static boolean union(int a, int b) {
		a = findSet(a);
		b = findSet(b);
		if(a == b) return false;
		p[b]=a;
		return true;
	}
}
