package io.dev.v2.trees.construct;


import java.util.Stack;

/**
    Definition of ExpressionTreeNode:

    Input: ["2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"]
    Output: {[-],[*],[/],[2],[6],[+],[+],#,#,#,#,[23],[7],[1],[2]}

    Explanation:
    The expression tree will be like


                         [ - ]

                     /          \

                [ * ]              [ / ]

              /     \           /         \

            [ 2 ]  [ 6 ]      [ + ]        [ + ]

                             /    \       /      \

                           [ 23 ][ 7 ] [ 1 ]   [ 2 ] .




            ( (field1=1 OR field2=2) AND (field3=3 AND field4=4) )
            "(", "(", "field1=1", "OR", "field2=2", ")", "AND", "(", "field3=3", "AND", "field4=4", ")", ")"
            "(", "(", "(","field1", "*", "1", ")", "OR", "(", "field2", "*", "2", ")", ")", "AND", "(", "(", "field3", "*, "3", ")", "AND", "(", "field4", "*", "4", ")", ")", ")"


    After building the tree, you just need to return root node `[-]`.
* */

public class ConstructBinaryExpressionTreeFromAnArray {

    public static void main(String[] args) {
        ConstructBinaryExpressionTreeFromAnArray tree = new ConstructBinaryExpressionTreeFromAnArray();
        //String[] expression1 = {"2","*","6","-","(","23","+","7",")","/","(","1","+","2",")"};
        String[] expression2 = {"(", "(", "field1=1", "OR", "field2=2", ")", "AND", "(", "field3=3", "AND", "field4=4", ")", ")"};
        String[] expression3 = {
                                "(",
                                        "(",
                                            "(","11", "*", "1", ")",
                                            "-",
                                            "(", "22", "*", "2", ")",
                                        ")",
                                    "+",
                                        "(",
                                            "(", "33", "*", "3", ")",
                                            "-",
                                            "(", "44", "*", "4", ")",
                                        ")",
                                ")"
                               };
        String[] expression4 = {
                                "(",
                                        "(",
                                            "(","field1", ":", "1", ")",
                                            "OR",
                                            "(", "field2", ":", "2", ")",
                                        ")",
                                    "AND",
                                        "(",
                                            "(", "field3", ":", "3", ")",
                                            "OR",
                                            "(", "field4", ":", "4", ")",
                                        ")",
                                ")"
                                };
        TreeNode root = tree.build(expression4);
        System.out.println(root);
    }

    public class TreeNode {
        public String symbol;
        public TreeNode left, right;
        public TreeNode(String symbol) {
            this.symbol = symbol;
            this.left = this.right = null;
        }
    }

    public TreeNode build(String[] expression) {
        if (expression == null || expression.length == 0) {
            return null;
        }

        Stack<String> operator = new Stack<>();
        Stack<TreeNode> operand = new Stack<>();

        for (int i=0; i< expression.length; i++) {

            String token = expression[i];
            if ("(".equals(token)) {
                operator.push(token);
            } else if (")".equals(token)) {
                // pop out till "("
                while (!operator.isEmpty() && !"(".equals(operator.peek())) {
                    String poppedOperator = operator.pop(); // *, -,
                    buildTreeNode(poppedOperator, operand);
                }
                operator.pop(); // Now pop the "("
            } else if (getPriority(token) > 0) { // is operator
                while (!operator.isEmpty() && getPriority(operator.peek()) >= getPriority(token)) {
                    buildTreeNode(operator.pop(), operand);
                }
                operator.push(token);
            } else { // is digit
                operand.push(new TreeNode(token));
            }

        }

        while (!operator.isEmpty()) {
            buildTreeNode(operator.pop(), operand);
        }

        return operand.isEmpty() ? null : operand.peek();
    }

    private int getPriority(String token) {
        if ("AND".equals(token) || "OR".equals(token)) {
            return 1;
        }
        if (":".equals(token) || "=".equals(token)) {
            return 2;
        }
        return 0;
    }

    private void buildTreeNode(String opt, Stack<TreeNode> operandStack) {
        TreeNode node = new TreeNode(opt);
        node.right = operandStack.pop();
        node.left = operandStack.pop();
        operandStack.push(node);
    }
}