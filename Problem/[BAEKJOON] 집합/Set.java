//[백준] 집합
/*
26
add 1
add 2
check 1
check 2
check 3
remove 2
check 1
check 2
toggle 3
check 1
check 2
check 3
check 4
all
check 10
check 20
toggle 10
remove 20
check 10
check 20
empty
check 1
toggle 1
check 1
toggle 1
check 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Set {
	static int set=0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		
		
		for(int i=0;i<test;i++) {
			st = new StringTokenizer(br.readLine());
			int num=0;
			
			switch(st.nextToken()) {
			case "add":
				num = Integer.parseInt(st.nextToken());
				set |= (1<<num);
				break;
			case "remove":
				num = Integer.parseInt(st.nextToken());
				set &= ~(1<<num);
				break;
			case "check":
				num = Integer.parseInt(st.nextToken());
				if((set&(1<<num))!=0) {
					sb.append("1");
					sb.append("\n");
				}else {
					sb.append("0");
					sb.append("\n");
				}
				break;
			case "toggle":
				num = Integer.parseInt(st.nextToken());
				if((set&(1<<num))==0) {
					set |= (1<<num);
				}else set &= ~(1<<num);
				break;
			case "all":
				set = (1<<21)-1; 
				break;
			case "empty":
				set = 0;
				break;
			}
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}
