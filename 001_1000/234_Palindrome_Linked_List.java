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
class 234_Palindrome_Linked_List {
    public boolean isPalindrome(ListNode head) {

        ListNode l1 = head;
        ListNode l2 = head;

        // ListNode curr = l1;
        ListNode prev = null;
        

        while(l2!=null && l2.next != null){
            // l1 = l1.next;
            l2 = l2.next.next;

            ListNode nextNode = l1.next;
            l1.next = prev;
            prev = l1;
            l1 = nextNode;
        }

        if(l2!=null){
            l1 = l1.next;
        }


        ListNode curr = prev;
        while(curr!=null){
            if(curr.val != l1.val){
                return false;
            }

            curr = curr.next;
        l1 = l1.next;
            
        }

        return true;

        
        
    }
}