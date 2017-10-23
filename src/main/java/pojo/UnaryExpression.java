package pojo;

import java.util.List;

public class UnaryExpression {
    private String operator;
    private String literal;
    private UnaryExpression subExpression;
    private List<UnaryExpression> expressions;

    public UnaryExpression() {
    }

    public UnaryExpression(String literal) {
        this.literal = literal;
    }

    public UnaryExpression(String operator, String literal) {
        this.operator = operator;
        this.literal = literal;
    }

    public UnaryExpression(String operator, UnaryExpression subExpression) {
        this.operator = operator;
        this.subExpression = subExpression;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLiteral() {
        return literal;
    }

    public void setLiteral(String literal) {
        this.literal = literal;
    }

    public UnaryExpression getSubExpression() {
        return subExpression;
    }

    public void setSubExpression(UnaryExpression subExpression) {
        this.subExpression = subExpression;
    }

    public List<UnaryExpression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<UnaryExpression> expressions) {
        this.expressions = expressions;
    }
}