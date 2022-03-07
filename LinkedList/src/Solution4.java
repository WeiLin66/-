public class Solution4 {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null){
            return null;
        }
        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {6,2,6,3,4,5,6,1};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);

        Solution4 solution = new Solution4();
        solution.removeElements(listNode, 6);
        System.out.println(listNode);
    }
}
