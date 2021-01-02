//[프로그래머스] 소수 찾기

import java.util.*;

class FindDecimalNumber {
    static int N;
    static int [] number, choose, order;
    static boolean [] v;
    static boolean [] visit;
    static HashSet<Integer> hash;
    public static int solution(String numbers) {
        int answer = 0;
        N = numbers.length();
        number = new int[N]; // 숫자 배열
        v = new boolean[N]; // 부분집합 방문 체크 배열
        hash = new HashSet<Integer>();
        
        for(int i=0;i<N;i++){
            number[i]=numbers.charAt(i)-'0';
        }
        
        // 가능한 숫자 수 만큼 조합 반복
        for(int i=1;i<=N;i++) {
        	choose = new int[i];
        	visit = new boolean[i];
        	comb(0,0,i);
        }
        
        answer = hash.size();
        
        return answer;
    }
    // 조합 method
    public static void comb(int start, int cnt, int size){
        if(cnt == size){
        	order = new int[N];
        	perm(0,cnt);
            return;
        }
       for(int i=start;i<N;i++) {
    	   choose[cnt]=number[i];
    	   comb(i+1,cnt+1,size);
       }
    }
    // 순열 method
    public static void perm(int cnt, int size){
        if(cnt == size) {
            check(size);
            return;
        }
        for(int i=0;i<size;i++){
            if(visit[i]) continue;
            visit[i]=true;
            order[cnt]=choose[i];
            perm(cnt+1,size);
            visit[i]=false;
        }
    }
    // 소수 찾는 method
    public static void check(int size){
        String num = "";
        for(int i=0;i<size; i++){
            num += order[i];
        }
        if(size==0) return;
        int numb = Integer.parseInt(num);
        int cnt = 0;
        for(int i=1;i<=numb;i++){
            if(numb%i==0) cnt++;   
            if(cnt>2) return; // 소수가 아니면 바로 리턴 (시간초과 예방)
        }
    
        if(cnt==2) {
            hash.add(numb);
        }
        return;
    }
    public static void main(String[] args) {
		System.out.println(solution("17"));
	}
}