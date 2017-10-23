package core;

import pojo.UnaryExpression;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public UnaryExpression parseUnaryExpression(String input) {
        UnaryExpression unaryExpression;
        if (input.contains(" OR")) {
            unaryExpression = new UnaryExpression();
            unaryExpression.setOperator("$or");
            unaryExpression.setExpressions(new ArrayList<UnaryExpression>());
            for (String s : input.split("OR")) {
                UnaryExpression temp = parseUnaryExpression(s.trim());
                unaryExpression.getExpressions().add(temp);
            }
        } else if (isSpaceBetweenQuotes(input) || input.contains("AND")) {
            unaryExpression = new UnaryExpression();
            unaryExpression.setOperator("$and");
            unaryExpression.setExpressions(new ArrayList<UnaryExpression>());
            for (String s : input.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?|AND")) {
                UnaryExpression temp = parseUnaryExpression(s);
                unaryExpression.getExpressions().add(temp);
            }
        } else if (input.indexOf(">=") == 0) {
            unaryExpression = setExpressionProperties("$gte", parseUnaryExpression(input.substring(2)));
        } else if (input.indexOf(">") == 0) {
            unaryExpression = setExpressionProperties("$gt", parseUnaryExpression(input.substring(1)));
        } else if (input.indexOf("<=") == 0) {
            unaryExpression = setExpressionProperties("$lte", parseUnaryExpression(input.substring(2)));
        } else if (input.indexOf("<") == 0) {
            unaryExpression = setExpressionProperties("$lt", parseUnaryExpression(input.substring(1)));
        } else if (input.indexOf("len(") == 0) {
            unaryExpression = setExpressionProperties("$len", parseUnaryExpression(getNumberBetweenBrackets(input)));
        } else if (input.indexOf("=") == 0) {
            unaryExpression = setExpressionProperties("$eq", parseUnaryExpression(input.substring(1)));
        } else {
            return new UnaryExpression(input);
        }
        return unaryExpression;
    }

    private UnaryExpression setExpressionProperties(String operator, UnaryExpression subExpression) {
        if (subExpression.getOperator() == null || subExpression.getOperator().equals("")) {
            return new UnaryExpression(operator, subExpression.getLiteral());
        } else {
            return new UnaryExpression(operator, subExpression);
        }
    }

    private boolean isSpaceBetweenQuotes (String input) {
        final String regex = "\\s+(?=(?:[^\'\"]*[\'\"][^\'\"]*[\'\"])*[^\'\"]*$)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private String getNumberBetweenBrackets(String input) {
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }
}
