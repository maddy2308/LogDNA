package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Parser;
import pojo.UnaryExpression;

public class Solution {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        Parser solution = new Parser();
        UnaryExpression unaryExpression = solution.parseUnaryExpression(">400");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("<500");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("len(8)");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("<=300");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression(">=300 <500");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("<300 OR <500");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("error OR info");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression("=\"TEST DATA\" OR >len(9)");
        System.out.println(gson.toJson(unaryExpression));
        unaryExpression = solution.parseUnaryExpression(">len(9)");
        System.out.println(gson.toJson(unaryExpression));
    }

}

