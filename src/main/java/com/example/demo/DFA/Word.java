package com.example.demo.DFA;


/**
 * @author ZLJ
 * @description
 * @date 2022/5/25
 */
public class Word implements Comparable<Word>{
    public char c;
    public List next = null;

    public Word(char c){
        this.c = c;
    }

    @Override
    public int compareTo(Word word) {
        return c - word.c;
    }
}