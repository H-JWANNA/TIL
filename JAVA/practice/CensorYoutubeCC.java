package com.codestates.section1.java_basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.util.Arrays;

public class CensorYoutubeCC {
    public static void main(String[] args) throws IOException {
        // 입력받을 객체 생성
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // 데이터 ( {id, pw}, {금칙어 리스트}, {순화된 언어 리스트} )
        final String[][] account = {{"admin", "0000"}, {"H-JWANNA", "1234"}, {"kimcoding", "5678"}};
        final String[] forbiddenWord = {"시발", "씨발", "ㅅㅂ", "미친", "병신", "꺼져", "존나"};
        final String[] pureWord = {"사랑", "희망", "기쁨", "용기", "좋아"};

        // 안내문 출력
        System.out.print("[시스템] 유튜브 계정의 아이디를 입력하세요 (Ex - admin) : ");
        String id = input.readLine();
        System.out.print("[시스템] 유튜브 계정의 비밀번호를 입력하세요 (Ex - 0000) : ");
        String pw = input.readLine();

        // 계정 검사
        boolean isWrong = true;

        for (String[] strings : account) {
            if (id.equals(strings[0]) && pw.equals(strings[1])) {
                isWrong = false;
                break;
            }
        }

        // 틀리면 종료
        if (isWrong) {
            System.out.println("[경고] 유튜브 계정의 아이디 및 비밀번호를 다시 확인해주세요.");
            System.exit(0);
        }

        // 로그인 성공 - 자막 생성
        System.out.printf("[안내] 안녕하세요 %s님\n", id);
        System.out.println("[안내] 유튜브 영상의 자막을 생성해주세요.");
        String[] sub = input.readLine().split(" "); // 단어 단위로 배열 생성

        // 단어 순화 로직
        Random rand = new Random();

        for (int i = 0 ; i < sub.length ; i++) {

            // 만약 sub[i]가 나쁜말에 포함되어 있으면?
            if (Arrays.asList(forbiddenWord).contains(sub[i])) {
                // 랜덤한 착한말로 바꾼다.
                sub[i] = pureWord[rand.nextInt(pureWord.length)];
            }
        }

        // 결과 출력
        System.out.println("=".repeat(30));
        System.out.println("[알림] 자막 순화 프로그램 결과입니다.");
        System.out.print(">>> ");
        for (String subs : sub) {
            System.out.print(subs + " ");
        }
        System.out.println("\n[안내] 프로그램을 종료합니다.");

    }
}
