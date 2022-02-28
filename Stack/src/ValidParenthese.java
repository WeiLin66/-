/**
 * leetcode 20. Valid Parentheses
 */

import java.util.Stack;

public class ValidParenthese {

    /**
     * 給定一個只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判斷字符串是否有效
     *
     * @param s 傳入字串
     * @return 入匹配則返回true，否則返回false
     */
    public boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        ArrayStack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c.equals('(') || c.equals('{') || c.equals('[')) {
                stack.push(s.charAt(i));
            } else {
               if(stack.isEmpty()){
                   return false;
               }

               char topChar = stack.pop();
               if(c == ')' && topChar != '('){
                   return false;
               }else if(c == '}' && topChar != '{'){
                    return false;
               }else if(c == ']' && topChar != '['){
                    return false;
               }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParenthese test = new ValidParenthese();
        System.out.println(test.isValid("(]]"));
    }
}
