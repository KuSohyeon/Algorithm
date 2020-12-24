import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Palindrome {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int [] alpha = new int[26]; // 대문자 알파벳 수만큼 배열 잡아주기
		
		String s = br.readLine();
		
		for(int i=0;i<s.length();i++) {
			alpha[s.charAt(i)-'A']++; // 해당 인덱스에 알파벳 수 증가시키기
		}
		
		int odd=0 , index = 0;
		
		char [] crr = new char[s.length()];
		
		for(int i=0;i<26;i++) {
			if(alpha[i]%2!=0) {
				odd++;
				index = i;
			}
		}
		
		if(odd>1) { // 만약 홀수가 2개 이상인 경우 대칭 나올 수 없음
			System.out.println("I'm Sorry Hansoo");
			System.exit(0);
		}
		else if(s.length()%2==0 && odd==1) { // 만약 문자의 길이가 짝수인데 홀수갯수의 알파벳이 있을 경우 대칭이 나올 수 없음
			System.out.println("I'm Sorry Hansoo");
			System.exit(0);
		}
		else {
			if(s.length()%2==0) { // 짝수인 경우
				int low = 0, high = s.length()-1;
				for(int i=0;i<26;i++) {
					if(low>=high) break;
					if(alpha[i]==0) continue;
					crr[low++] = (char)(i+'A');
					alpha[i]--;
					crr[high--] = (char)(i + 'A');
					alpha[i]--;
					if(alpha[i] > 1) i--;
				}
			}else { // 홀수인 경우
				int low = 0, high = s.length()-1;
				for(int i=0;i<26;i++) {
					if(low>=high) break;
					if(alpha[i]<=1) continue;
					crr[low++] = (char)(i+'A');
					alpha[i]--;
					crr[high--] = (char)(i + 'A');
					alpha[i]--;
					if(alpha[i]>1) i--;
				}
				crr[low] = (char)(index + 'A'); // 중간에 홀수인거 넣어주기
			}
		}
		
		String start = "";
		String end = "";
		int N = s.length();
		for(int i=0;i<crr.length;i++) {
			start += crr[i];
			end += crr[N-1-i];
		}
		
		
		if(start.equals(end)) {// 대칭일 경우
			System.out.println(start);
		}else {// 대칭이 아닌 경우
			System.out.println("I'm Sorry Hansoo");
		}
	}
}
