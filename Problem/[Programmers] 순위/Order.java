//[프로그래머스] 순위
/*
 * 내가 이긴 배열, 내가 진 배열을 만들어서
 * 내가 이긴 배열 + 내가 진 배열 = 사람 수 이면 순위를 알 수 있음
 * 내가 이긴 사람, 진 사람을 구하는 방법은 깊이우선탐색을 이용하기
 */

import java.util.ArrayList;
import java.util.List;
public class Order {
	static int [] win,lose;
    static boolean [] v;
    static List<Integer> [] list;
    public static void main(String[] args) {
		
    	int n = 5;
    	int [][] result = {{4,3},{4,2},{3,2},{1,2},{2,5}};
    	System.out.println(solution(5,result));
		
	}
    public static int solution(int n, int[][] results) {
        int answer = 0;
        list = new ArrayList [n+1]; 
        win = new int[n+1]; // 내가 이긴 사람 수 배열
        lose = new int[n+1]; // 내가 진 사람 수 배열
        
        // 값 setting
        for(int i=1;i<=n;i++){
            list[i] = new ArrayList<Integer>();
        }
        
        for(int i=0;i<results.length;i++){
            int win = results[i][0];
            int lose = results[i][1];
            list[win].add(lose);
        }
        
        // dfs 시작
        for(int i=1;i<=n;i++){
            v = new boolean[n+1];
            dfs(0,i,i);
        }
        
        // 내가 이긴 사람 수 + 내가 진 사람 수 = 사람 수이면 순위를 정확하게 알 수 있는 사람
        for(int i=1;i<=n;i++){
            if(win[i]+lose[i]==n) answer++;
        }
        
        return answer;
    }
    static void dfs(int cnt, int loser, int now){
        if(v[loser]) return; // 중복 케이스 제거
        v[loser]=true; // 방문체크
        win[now]++; // 이긴 횟수 ++
        if(now!=loser){ // 처음 시작 중복 방지용 
            lose[loser]++;
        }
        for(int i=0;i<list[loser].size();i++){
            int next = list[loser].get(i); //내가 이긴 사람
            dfs(cnt+1, next, now); // 그 사람이 이긴 사람으로 다시 깊이우선탐색
        }
        
    }
}
