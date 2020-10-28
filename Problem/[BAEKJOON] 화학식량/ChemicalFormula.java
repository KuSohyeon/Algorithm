import java.util.Scanner;
import java.util.Stack;

public class ChemicalFormula {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		char [] ch = sc.next().toCharArray();
		Stack<Integer> stack = new Stack<Integer>();
		for(int i=0;i<ch.length;i++) {
			if(ch[i]=='(') stack.add(-1); 
			else if(ch[i]=='H') {
				stack.add(1);
			}else if(ch[i]=='C') {
				stack.add(12);
			}else if(ch[i]=='O') {
				stack.add(16);
			}else if(ch[i]==')') { // 닫힌 괄호를 만났을 때 여는 괄호 만날때까지 안에 있는거 다 더해주기
				int cal=0;
				while(true) {
					int tmp = stack.pop();
					if(tmp==-1) break;
					cal+=tmp;
				}
				stack.add(cal);
			}else {// 숫자일 경우
				int tmp = stack.pop();
				if(tmp==0) {// 빈괄호일 경우를 대비 (없어도 됨)
					while(tmp==0) {
						tmp = stack.pop();
					}
				}
				stack.add(tmp*(ch[i]-'0')); // 스택의 맨 위에꺼 꺼내서 숫자랑 곱해주고 다시 넣어주기
			}
			
		}
		
		int result=0;
		// 마지막 합치기
		while(!stack.empty()) {
			result += stack.pop();
		}
		
		System.out.println(result);
		
	}
}
