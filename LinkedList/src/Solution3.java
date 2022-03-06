/**
 * leetcode 203. Remove Linked List Elements
 * solution 3
 */
public class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        ListNode n;
        dummyHead.next = head;

        while(dummyHead.next != null && dummyHead.next.val == val){
            ListNode res = dummyHead.next;
            dummyHead.next = dummyHead.next.next;
            res = null;
        }

        n = dummyHead.next;

        if(n == null){
            return null;
        }else if(n.next == null){
            return n;
        }

        n.next = removeElements(n.next, val);
        return n;
    }

    public static void main(String[] args) {
        int[] arr = {6,2,6,3,4,5,6,1};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);

        Solution3 solution3 = new Solution3();
        solution3.removeElements(listNode, 6);
        System.out.println(listNode);
    }
}
