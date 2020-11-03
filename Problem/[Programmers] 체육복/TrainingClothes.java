//[프로그래머스] 체육복 
// 그리디

import java.util.ArrayList;
import java.util.List;

public class TrainingClothes {
	 public static int solution(int n, int[] lost, int[] reserve) {
	        int answer = 0;
	        boolean [] s = new boolean[n+1];
	        
	        // 자기 자신이 체육복 잃어버린 경우 찾아주기
	        List<Integer> tmp = new ArrayList();
	        
	        for(int i=0;i<lost.length;i++){
	            for(int j=0;j<reserve.length;j++){
	            	if(lost[i]==reserve[j]) {
	            		tmp.add(lost[i]);
	            	}
	            }
	         }
	        
	        // 자기 자신이 체육복을 잃어버린 경우 여분 자기꺼 입어야하니까 그거 빼고 다시 리스트로 만들어주기
	        List<Integer> los = new ArrayList();
	        List<Integer> res = new ArrayList();
	        
	        for(int i=0;i<lost.length;i++) {
	        	boolean flag = false;
	        	 for(int j=0;j<tmp.size();j++) {
	        		if(lost[i]==tmp.get(j)) { 
	        			flag = true;
	        			break;
	        		}
	        	 }
	        	 if(!flag) {
	        		 los.add(lost[i]);
	        	 }
	        }
	        
	        for(int i=0;i<reserve.length;i++) {
	        	boolean flag = false;
	        	for(int j=0;j<tmp.size();j++) {
	        		if(reserve[i]==tmp.get(j)) { 
	        			flag = true;
	        			break;
	        		}
	        	}
	        	if(!flag) {
	        		res.add(reserve[i]);
	        	}
	        }
	        
	        // 앞 뒤로 빌릴 수 있는 사람 확인
	        for(int i=0;i<los.size();i++){
	            for(int j=0;j<res.size();j++){
	                if(los.get(i)-1 == res.get(j)){
	                    if(s[res.get(j)]) continue;
	                    s[res.get(j)]=true;
	                    break;
	                }
	                if(los.get(i)+1 == res.get(j)){
	                	if(s[res.get(j)]) continue;
	                	s[res.get(j)]=true;
	                	break;
	                }
	            }
	        }
	        
	        // 빌려준 경우 확인
	        int cnt = 0;
	        for(int i=1;i<=n;i++){
	            if(s[i]) cnt++;
	         }
	        
	        // 답 = 전체 인원수 - 잃어버린 학생 + 빌려 입은 학생
	        answer = n-los.size() + cnt;
	        return answer;
	    }
	 public static void main(String[] args) {
		 int n = 5;
		 int [] lost = {2,4};
		 int [] reserve = {1,3,5};
		System.out.println(solution(n,lost,reserve));
	}
}
