package Sliver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 풀이 시간 :1시간10분 ..
 *
 * 이 문제를 풀려면... 펠린드롬이  뭔지 알아야함
 * [펠린드롬] 이란?
 * => 회문(回文) 또는 팰린드롬(palindrome)은 거꾸로 읽어도 제대로 읽는 것과 같은 문장이나 낱말, 숫자, 문자열(sequence of characters) 등이다
 * ex) ABABA 앞에서 부터 읽던 뒤에서 부터 읽던 서로 같음
 */

public class Main1213 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine(); //입력값

        int[] alpa = new int[26];
        for (int i = 0; i < str.length(); i++) {
            alpa[str.charAt(i) - 65]++;
        }

        /**
         두 가지 경우로 나눌 수 있음

         1. 문자열의길이가 '짝수'인경우
         => 짝수인경우 , 개수가 홀수인 알바펫이 존재하면 안됨.
         2. 문자열의 길이가 '홀수'인경우
         => 홀수인 경우 , 개수가 홀수인 알바펫은 단 하나만 존재해야함.

         결론:  개수가 홀수개인 알파벳 개수가  1 보다 크면 안됨
         */


        int n = 0; //개수가 홀수인 알바벳 개수
        for (int i = 0; i < alpa.length; i++) {
            if (alpa[i] % 2 == 1) {
                n++;
            }
        }
        //팰린드롬 문자열을 만들 수 없는 상황 : 개수가 홀수개인 알파벳 개수가 1 보다 큰 경우
        if (n > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }


        StringBuilder sb = new StringBuilder(); //정답을 저장할 변수
        char middle = '0'; //홀수개인 알파벳이 있다면 저장할거임. -> 있다는건 중간에 반드시 홀수개인 알파벳이 들어가야 팰린드롬이 됨

        //중간 기준으로 앞에 올 문자열 만들기
        for (int i = 0; i < alpa.length; i++) {

            if (alpa[i] % 2 == 1) { //홀수개인 알파벳이 있는경우
                middle = (char) (i + 65);
            }
            // A가 4개있다고 한다면 절반만의 개수만큼만 저장해준다.
            // 그 이유는 뒤에도 거꾸로 똑같이 문자를 저장해야되기때문이다.
            for (int j = 0; j < alpa[i] / 2; j++) {
                sb.append((char) (i + 65));
            }
        }

           if (middle != '0') { //중간에 넣어야할 문자가 존재한다면 넣어준다.
            sb.append(middle);
          }

        //뒤에 올 문자열 만들기 (거꾸로)
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < alpa[i] / 2; j++) {
                sb.append((char) (i + 65));
            }
        }

        System.out.println(sb);

    }

}

/**
 * 문제에서 팰린드롬으로 만들 수 있는 문자열이 여러개라면 사전순으로 가장 앞으로 오는 문자열로 출력하라했음.
 * 하지만 위의 풀이라면   alpa [] 0~25 (인덱스 번호)->  A~Z 순 으로 문자를 저장하기때문에 사전순으로 가장 앞에 오는 문자열이 나올 수 밖에없음.
 */

/**
  첫 접근
 1. 입력받은 문자열의 알파벳으로 모든 경우의 문자열을 구하려했음. (완탐)
 2. 모든 경우에 대해서 회문이 되는지 검토
 3. 회문이 되는 문자열이 있다면 리스트에 저장함
 4. 다 찾고 나면 저장된 문자열을 사전순으로 정렬 하여 가장 앞에오는 인덱스 번호의 (0) 의 문자열을 출력하여 정답을 찾으려했음

 => 여기서 문제는
 1번의 경우에서 모든 경우의 수를 찾는 로직을 짜려고 하니 최악의 경우 최대 알파벳 50개 라고 했는데
 이 50개를 가지고 서로다른 문자열을 만들자 하니.. 경우의 수가... 50x49x48x47 .... 50!(?)
 =>30414093201713378043612608166064768844377641568960512000000000000 -> 일단 2억번 넘어가서 제한시간 2초 넘음
 =>그래서 모든 경우를 찾는건 아니라 생각했음

 => 그럼 회문이 되는 조건을 찾아야 된다는건데
 =>문자열의 길이가 짝수거나 홀수일때 경우가 다를테니 어떨때 회문이 될 수 있는지 예시를 들어가며 찾으려했음
 => 그래서

 1. 문자열의길이가 '짝수'인경우
 => 짝수인경우 , 개수가 홀수인 알바펫이 존재하면 안됨.
 2. 문자열의 길이가 '홀수'인경우
 => 홀수인 경우 , 개수가 홀수인 알바펫은 단 하나만 존재해야함.

 이렇게 발견.

 이렇게 생각하고 하드 코딩함 (전에 짠거 보면 reverse하고 난리났음)

 처음 제출하고 코드 정리좀 했음 (겹치는 코드가 있어서)
 */

