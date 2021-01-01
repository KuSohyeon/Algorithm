public class Test {
    public int[] solution(int[] answers) {
        int[] answer;
        int [] one = {1,2,3,4,5};
        int [] two = {2,1,2,3,2,4,2,5};
        int [] three = {3,3,1,1,2,2,4,4,5,5};
        
        int len = answers.length;
        int [] correct = new int[3];
        int max = 0;
        int cnt = 0;
        
        for(int n = 0;n<3;n++){
            int [] tmp = null;
            if(n==0){
                tmp = one;
            }else if(n==1){
                tmp = two;
            }else{
                tmp = three;
            }
            int idx = 0;
            for(int i=0;i<len;i++){
                if(answers[i] == tmp[idx]) {
                    correct[n]++;
                    if(max<correct[n]){
                        max = correct[n];
                        cnt = 0;
                    }else if(max == correct[n]){
                        cnt++;
                    }
                }
                if(++idx == tmp.length) idx = 0;
            }
        }
        
        if(cnt == 0){ // 가장 높은 점수를 받은 사람이 한 명일 경우
            answer = new int[1];
            for(int i=0;i<3;i++){
                if(max == correct[i]){
                    answer[0]=i+1;
                    break;
                }
            }
        }else{ // 가장 높은 점수를 받은 사람이 여럿일 경우
            answer = new int[cnt+1];
            int index = 0;
            for(int i=0;i<3;i++){
                if(max == correct[i]){
                    answer[index++]=i+1;
                }
            }
        }
        
        return answer;
    }
}