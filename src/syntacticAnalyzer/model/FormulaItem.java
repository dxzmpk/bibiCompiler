package syntacticAnalyzer.model;

import java.util.Arrays;

public class FormulaItem {

    private int pointAlign;

    char c;

    private String head;

    private String reap;

    private char[] first;

    private char foreSee;

    public FormulaItem(int pointAlign, String head, String reap, char[] first, char foreSee) {
        this.pointAlign = pointAlign;
        this.head = head;
        this.reap = reap;
        this.first = first;
        this.foreSee = foreSee;
    }

    public FormulaItem(int pointAlign, String head, String reap) {
        this.pointAlign = pointAlign;
        this.head = head;
        this.reap = reap;
    }

    public FormulaItem(String line){

    }

    public char hasNext(){
        if(pointAlign == reap.length() ) return '#';
        else return c;
    }

    public FormulaItem nextFormula(){
        return new FormulaItem(pointAlign+1, head, reap, first, foreSee);
    }

    public int getPointAlign() {
        return pointAlign;
    }

    public void setPointAlign(int pointAlign) {
        this.pointAlign = pointAlign;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getReap() {
        return reap;
    }

    public void setReap(String reap) {
        this.reap = reap;
    }

    public char[] getFirst() {
        return first;
    }

    public void setFirst(char[] first) {
        this.first = first;
    }

    public char getForeSee() {
        return foreSee;
    }

    public void setForeSee(char foreSee) {
        this.foreSee = foreSee;
    }

    @Override
    public String toString() {
        return "FormulaItem{" +
                "pointAlign=" + pointAlign +
                ", head='" + head + '\'' +
                ", reap='" + reap + '\'' +
                ", first=" + Arrays.toString(first) +
                ", foreSee=" + foreSee +
                '}';
    }
}
