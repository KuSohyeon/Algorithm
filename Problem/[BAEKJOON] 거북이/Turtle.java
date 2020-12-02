//[백준] 거북이

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Turtle {
	static int [] dy = {-1,0,1,0};// 상 우 하 좌
	static int [] dx = {0,1,0,-1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			
			int up = 0, down = 0, left = 0, right = 0, dir = 0, x=0, y=0;
			
			 char [] ch = br.readLine().toCharArray();
			 
			 for(int i=0;i<ch.length;i++) {
				 char now = ch[i];
				 if(now=='L') {
					 if(--dir<0) {
						 dir=3;
					 }
				 }
				 else if(now=='R') {
					 if(++dir>3) {
						 dir=0;
					 }
				 }else if(now=='F') {
					 y += dy[dir];
					 x += dx[dir];
				 }else {
					 y -= dy[dir];
					 x -= dx[dir];
				 }
					 
				 if(up > y)up = y;
				 if(down < y) down = y;
				 if(right > x) right = x;
				 if(left < x) left = x;
				 
			 }// end ch for 
			 
			 int result = (up-down)*(right-left);
			 System.out.println(result);
			 
		}// end tc for
	}
}
