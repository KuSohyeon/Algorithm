//[백준] 문서검색
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SearchDocument {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		
		int cnt = 0;
		
		for(int i=0;i<a.length();i++) {
			if(i + b.length()>a.length()) break;
			String tmp = a.substring(i, i + b.length());
			if(b.equals(tmp)){
				cnt++;
				i += b.length()-1; // 문자열 길이만큼 건너뛰기 (for문 특성상 어차피 ++ 되니 -1해주기)
			}
			// 같지 않은 경우 그 다음 문자열 검사 (for문에서 자동으로 그다음 단어로 넘어감)
		}
		
		System.out.println(cnt);
	}
}
