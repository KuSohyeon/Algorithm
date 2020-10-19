//[SW Expert] 음식 배달

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FoodDelivery {
	static int N,result;
	static int [][] map;
	static boolean [] v;
	static List<Store> store;
	static class Store{
		int i;
		int j;
		int cost;
		public Store(int i, int j, int cost) {
			super();
			this.i = i;
			this.j = j;
			this.cost = cost;
		}
		@Override
		public String toString() {
			return "Store [i=" + i + ", j=" + j + ", cost=" + cost + "]";
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			result = Integer.MAX_VALUE; //TC마다 초기화
			
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			store = new ArrayList<Store>();
			
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j=0;j<N;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]>1) {
						store.add(new Store(i,j,map[i][j]));
					}
				}
			}
			
			v = new boolean[store.size()];
			subset(0);
			
			System.out.println("#"+tc+" "+result);
		}
	}
	private static void subset(int cnt) {
		if(cnt==store.size()) {
			deliveryFee(); // 음식점 다 골랐으니 배달 거리 구하러 가기
			return;
		}
		store.get(cnt);
		v[cnt]=true;
		subset(cnt+1);
		v[cnt]=false;
		subset(cnt+1);
		
		
	}
	// 배달 거리 구하기
	private static void deliveryFee() {
		int len = 0; // 선택된 음식 배달점의 개수
		for(int i=0;i<v.length;i++) {
			if(v[i]) {
				len++;
			}
		}
		
		if(len==0 || len>10) return; // 음식배달집의 개수는 10개를 넘지 않는다
		Store [] food = new Store [len];
		
		// 선택된 음식점 세팅
		int cnt=0;
		for(int i=0;i<v.length;i++) {
			if(v[i]) {
				food[cnt++]=store.get(i);
			}
		}

		// 집에서 가장 가까운 음식점 거리 구하기 
		int fee=0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]!=1) continue;
				int dist = Integer.MAX_VALUE;
				for(int f=0;f<len;f++) {
					Store now = food[f];
					int tmp = Math.abs(now.i-i)+Math.abs(now.j-j);
					if(dist>tmp) {
						dist = tmp;
					}
				}
				fee += dist; // 비용에 최소 비용 업데이트
			}
		}

		for(int i=0;i<len;i++) {
			fee += food[i].cost; // 음식점 운용비도 더해주기
		}
		
		result = Math.min(result, fee); //최소값으로 업데이트
	}
}
