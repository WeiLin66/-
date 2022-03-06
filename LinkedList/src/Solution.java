/**
 * leetcode 203. Remove Linked List Elements
 * solution 1
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode n = head;
            head = head.next;
            n.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode resNode = head;
        while (resNode.next != null) {
            if (resNode.next.val == val) {
                resNode.next = resNode.next.next;
            } else {
                resNode = resNode.next;
            }
        }
        return head;
    }
}
