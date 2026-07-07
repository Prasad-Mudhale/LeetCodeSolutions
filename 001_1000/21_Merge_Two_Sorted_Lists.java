/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class 21_Merge_Two_Sorted_Lists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode h1 = list1;
        ListNode h2 = list2;

        ListNode dummy = new ListNode();
        ListNode head =dummy;

        while(h1!=null && h2!=null){
                if(h1.val > h2.val){
                    dummy.next = h2;
                    h2 =h2.next;
                }else{
                    dummy.next = h1;
                    h1 = h1.next;
                }
                dummy = dummy.next;
        }

        if(h1==null){
            dummy.next= h2;

        }if(h2==null){
            dummy.next=h1;
        }

        return head.next;
        
    }
}  