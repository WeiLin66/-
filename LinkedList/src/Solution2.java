/**
 * leetcode 203. Remove Linked List Elements
 * solution 2
 */
public class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;
        ListNode n = dummyHead;
        while(n.next != null) {
            if(n.next.val == val){
                ListNode res = n.next;
                n.next = n.next.next;
                res.next = null;
            }else {
                n = n.next;
            }
        }
        return dummyHead.next;
    }
}
