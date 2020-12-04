//[SWEA] 방향 전환

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ChangeOfDirection {
	static int N,x1,x2,y1,y2,result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			st = new StringTokenizer(br.readLine());
			
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			int x = Math.abs(x1-x2);
			int y = Math.abs(y1-y2);
			
			if((x+y)%2==0) {
				result = Math.max(2*x, 2*y);
			}else {
				result = Math.max(x*2, y*2)-1;
			}
			
			System.out.println("#"+tc+" "+result);
		}
	}
}
