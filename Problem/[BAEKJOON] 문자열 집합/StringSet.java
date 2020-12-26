//[백준] 문자열 집합

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StringSet {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		String [] set = new String[N];
		
		for(int i=0;i<N;i++) {
			set[i]=br.readLine();
		}
		
		int result = 0;
		for(int i=0;i<M;i++) {
			String s = br.readLine();
			for(int j=0;j<N;j++) {
				if(s.equals(set[j])) {
					result++;
					break;
				}
			}
		}
		
		
		System.out.println(result);
	}
	
}
