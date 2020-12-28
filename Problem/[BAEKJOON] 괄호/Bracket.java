//[백준] 괄호
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bracket {
	public static void main(String [] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int num = 0;
		
		for(int i=0;i<n;i++) {
			String s = br.readLine();
			num = 0;
			boolean flag = false;
			for(int j=0;j<s.length();j++) {
				if(s.charAt(j)=='(') num++;
				else num--;
				if(num<0) { // 여는괄호 없이 닫는 괄호 먼저 나오면 VPS 아님
					flag = true;
					System.out.println("NO");
					break;
				}
			}
			if(!flag) {
				// 짝이 맞다면 0일 것
				System.out.println(num==0?"YES":"NO");
			}
			
		}
	}
}
