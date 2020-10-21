class ChangeWord {
    static int N, answer;
    static String start, end;
    static String [] input;
    static boolean [] v;
    public int solution(String begin, String target, String[] words) {
        start = begin;
        end = target;
        N = words.length;
        input = new String[N];
        v = new boolean[N];
        answer = 99999;
        for(int i=0;i<N;i++){
            input[i]=words[i];
        }

        dfs(start,0);
        return answer==99999?0:answer;
    }
    static void dfs(String start, int result){
        if(start.equals(end)){
            answer = Math.min(answer,result);
            return;
        }
        for(int i=0;i<N;i++){

            if(v[i]) continue;
            
            int cnt=0;
            for(int j=0;j<start.length();j++){
                if(start.charAt(j)!=input[i].charAt(j)){
                    ++cnt;
                }
            }

            if(cnt>1) continue;

            v[i]=true;


            dfs(input[i],result+1);
            v[i]=false;
          }
        return;

    }

}
