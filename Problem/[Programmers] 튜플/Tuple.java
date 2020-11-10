import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Tuple {
	public static int[] solution(String s) {
       s = s.substring(2, s.length()-2).replace("},{", "-");
       String [] srr = s.split("-");
       // 길이 순으로 sort
	   Arrays.sort(srr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}
		});
    
	   ArrayList<Integer> list = new ArrayList<Integer>();
	   for(String tmp : srr) {
		   String [] val = tmp.split(","); // ','기준으로 다시 쪼개주기(숫자만 남음)
		   
		   for(int i=0;i<val.length;i++) {
			   int num = Integer.valueOf(val[i]);
			   if(!list.contains(num)) { // 중복이 없는 튜플이 주어짐
				   list.add(num);
			   }
		   }
	   }
	   
		int[] answer = new int[list.size()];
		for(int i=0;i<list.size();i++) {
			answer[i]=list.get(i);
		}
		
        return answer;
    }
	public static void main(String[] args) {
		int [] arr = solution("{{1,2,3},{2,1},{1,2,4,3},{2}}");
		System.out.println(Arrays.toString(arr));
	}
}
