package com.example.demo.DFA;


public class Main {
    public static void main(String[] args) throws Exception{
     SensitiveWordFilter.loadWordFromFile("D:/SensitiveWordList.txt");
        StringBuilder stringBuilder = new StringBuilder();


            stringBuilder.append("123TM,D123");

        String s = stringBuilder.toString();
        String result = null;
            result = SensitiveWordFilter.Filter(s);

        System.out.println(result);

    }
}
