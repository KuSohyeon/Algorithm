//[Programmers] 이중우선순위큐

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class TwoPriorityQueue {
    public int[] solution(String[] operations) { 
        	int[] answer = new int[2];
	        List<Integer> list = new ArrayList<Integer>();
	        
	        for(int i=0;i<operations.length;i++) {
	        	if(operations[i].charAt(0)=='I') { //삽입
	        		list.add(Integer.parseInt(operations[i].substring(2, operations[i].length())));
	        	}else if(operations[i].charAt(0)=='D'){ // 삭제 연산
                    if(list.size()==0) continue; // 큐가 비었을때는 연산 무시
	        		if(operations[i].charAt(2)=='1') { // 최대값 삭제
	        			Collections.sort(list, new Comparator<Integer>() {
							@Override
							public int compare(Integer o1, Integer o2) {
								return Integer.compare(o2, o1);
							}
						});
	        			list.remove(0);
	        		}else { // 최소값 삭제
	        			Collections.sort(list);
	        			list.remove(0);
	        		}
	        	}
	        		
	        }
	        
	        Collections.sort(list, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2, o1);
				}
			});
                                                
            if(list.size()==0){ // 큐가 비었을 경우에는 [0,0] 반환
                answer[0]=0;
	            answer[1]=0;
                                                
	        return answer;
            }              
            // 그렇지 않으면 최대값, 최소값 반환
	        answer[0]=list.get(0);
	        answer[1]=list.get(list.size()-1);
                                                
	        return answer;
    }
}