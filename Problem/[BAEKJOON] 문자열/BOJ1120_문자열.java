//[백준] 문자열

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1120_문자열 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		String one = st.nextToken();
		String two = st.nextToken();
		
		int length1 = one.length();
		int length2 = two.length();
		
		int result = 0;
		
		if(length1 != length2) {
			if(length1>length2) {
				result = compareWord(two, one);
			}else {
				result = compareWord(one, two);
			}
		
		}else {
			for(int i=0;i<length1;i++) {
				if(one.charAt(i)!=two.charAt(i)) result++;
			}
		}
		
		System.out.println(result);
}

	private static int compareWord(String small, String big) {
		int smallLen = small.length();
		int bigLen = big.length();
		
		int size = bigLen-smallLen;
		int answer = Integer.MAX_VALUE;
		
		for(int i=0;i<=size;i++) {
			int cnt=0;
			for(int j=0;j<smallLen;j++) {
				if(small.charAt(j)!=big.charAt(i+j)) cnt++;
			}
			answer = Integer.min(answer, cnt);
		}
		
		return answer;
	}
}
