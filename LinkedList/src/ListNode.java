public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Illegal arr");
        }

        next = new ListNode(arr[0]);
        ListNode cur = next;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        cur.next = null;
    }


    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList [");
        ListNode n = next;
        while (n != null) {
            res.append(n.val);
            res.append("-->");
            n = n.next;

        }
        res.append("null]");
        return res.toString();
    }

}
