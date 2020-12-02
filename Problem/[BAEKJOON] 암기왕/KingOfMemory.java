////[백준] 암기왕

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class KingOfMemory {
	static int N,M,start,end;
	static int [] one, two;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			HashMap<Integer, Boolean> map = new HashMap<Integer,Boolean>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				map.put(Integer.parseInt(st.nextToken()), true);
			}
			
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				if(map.containsKey(Integer.parseInt(st.nextToken()))){
					sb.append("1");
				}else sb.append("0");
				if(i!=M-1) {
					sb.append("\n");
				}
			}
			System.out.println(sb.toString());
		}
			
	}
	
}

////런타임 에러
////아마 오버플로우
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Main {
//static int N,M,start,end;
//static int [] one, two;
//public static void main(String[] args) throws Exception {
//	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	StringTokenizer st;
//	
//	int T = Integer.parseInt(br.readLine());
//	
//	for(int tc=1;tc<=T;tc++) {
//		
//		N = Integer.parseInt(br.readLine());
//		one = new int[N];
//		
//		st = new StringTokenizer(br.readLine());
//		for(int i=0;i<N;i++) {
//			one[i]=Integer.parseInt(st.nextToken());
//		}
//		
//		M = Integer.parseInt(br.readLine());
//		two = new int[N];
//		
//		st = new StringTokenizer(br.readLine());
//		for(int i=0;i<M;i++) {
//			two[i]=Integer.parseInt(st.nextToken());
//		}
//		
//		Arrays.sort(one);
//
//		for(int i=0;i<M;i++) {
//			System.out.println(find(two[i]));
//		}
//	}
//}
//private static int find(int i) {
//	start = 0;
//	end = N;
//	while(start<end) {
//		int mid = (start+end)/2;
//		if(one[mid]==i) {
//			return 1;
//		}else if(Integer.compare(one[mid], i)<0){
//			start=mid+1;
//		}else {
//			end=mid-1;
//		}
//	}
//	return 0;
//}
//}

