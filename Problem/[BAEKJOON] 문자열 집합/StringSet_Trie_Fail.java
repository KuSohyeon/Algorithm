//[백준] 문자열 집합
// 트라이 연습 - 시간초과
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StringSet_Trie_Fail {
	static class TrieNode{
		TrieNode [] child;
		boolean isTerminal;
		public TrieNode() {
			this.child = new TrieNode[26];
			this.isTerminal = false;
		}
		@Override
		public String toString() {
			return "TrieNode [child=" + Arrays.toString(child) + ", isTerminal=" + isTerminal + "]";
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		TrieNode[] trieNode = new TrieNode[N];
		int idx = 0;
		TrieNode node = null;
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			trieNode[i] = new TrieNode();
			node = trieNode[i];
			for(int j=0;j<s.length();j++) {
				idx = s.charAt(j)-'a';
				node.child[idx] = new TrieNode();
				if(j==s.length()-1) {
					node.child[idx].isTerminal=true;
				}else {
					node = node.child[idx];
				}
			}
		}
		
		int result = 0; // 정답을 관리할 변수
		
		for(int i=0;i<M;i++) {
			String s = br.readLine();
			// N개 검사 해보기
			for(int j=0;j<N;j++) { 
						// 입력받은 문자열을 바탕으로 비교하기
						node = trieNode[j];
						boolean check =  false;
						for(int l=0;l<s.length();l++) {
							idx = s.charAt(l)-'a';
							if(node.child[idx] == null) break;
							node = node.child[idx];
							if(l==s.length()-1 && node.isTerminal) check = true;
						}
						if(check) {
							result ++;
							break;
						}
			}
		}
		
		System.out.println(result);
	}
}
