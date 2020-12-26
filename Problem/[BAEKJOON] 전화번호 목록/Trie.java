import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Trie {
	// TrieNode 클래스
    static class TrieNode{
        TrieNode[] child;
        boolean isTerminal; // 문자열의 끝인지 판단하는 멤버 변수

        public TrieNode() {
            this.child = new TrieNode[10];
            this.isTerminal = false;
        }

		@Override
		public String toString() {
			return "TrieNode [child=" + Arrays.toString(child) + ", isTerminal=" + isTerminal + "]";
		}
        
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int n = 0;
        String str = "";
        String[] arr = null;
        TrieNode root = null;
        TrieNode node = null;
        int idx = 0;
        String result = "";
        for(int t=1; t<=T; ++t) {
            n = Integer.parseInt(br.readLine());
            arr = new String[n];
            for(int i=0; i<n; ++i) {
                arr[i] = br.readLine();
            }
            Arrays.sort(arr);
            root = new TrieNode();
            node = root;
            result = "YES";
            breakPoint:
            for(int i=0; i<n; ++i) {
                str = arr[i];
                node = root;
                for(int j=0; j<str.length(); ++j) {
                    idx = str.charAt(j) - '0';
                    if(node.isTerminal && node.child[idx] != null) {
                        result = "NO";
                        break breakPoint;
                    }
                    if(node.child[idx] == null) {
                        node.child[idx] = new TrieNode();
                    }
                    if(j == str.length()-1) {
                        node.isTerminal = true;
                    }else {
                        node = node.child[idx];
                    }
                }
            }
            bw.write(result+"\n");
        }
        bw.close();
        br.close();

    }

}