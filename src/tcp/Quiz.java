/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

/**
 *
 * @author Thắng Bùi
 */
public class Quiz {
    private String question;
    private String answer;
    private String suggestion;
    public Quiz() {
    }

    public Quiz(String question, String answer, String suggestion) {
        this.question = question;
        this.answer = answer;
        this.suggestion = suggestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "Quiz{" + "question=" + question + ", answer=" + answer + ", suggestion=" + suggestion + '}';
    }
    
    
    
}
