




从头到尾遍历

两两交换

注意 0个、1个节点的特殊情况

最后返回新的头结点(表示一个新链表)

// most vote

// 递归写法要观察本级递归的解决过程，形成抽象模型，因为递归本质就是不断重复相同的事情。而不是去思考完整的调用栈，一级又一级，无从下手。如图所示，我们应该关注一级调用小单元的情况，也就是单个f(x)。

// 递归
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }
}


struct ListNode* swapPairs(struct ListNode* head){

        if(head == NULL || head->next == NULL){
            return head;
        }
        struct ListNode *next = head->next;
        head->next = swapPairs(next->next);
        next->next = head;
        return next;
}




// 我自己尝试做的
// 2020年12月31日20:25:38
struct ListNode* swapPairs(struct ListNode* head){
    struct ListNode* currNode = head;

    while (currNode != NULL || currNode->next != NULL){

        // 交换
        currNode->next = currNode->next->next;  //指针【指向-->】节点
        currNode->next->next = currNode;
        // newHead = currNode->next->next;

        // 遍历下一个节点
        currNode = currNode->next;

    }

    return p;
}

// leetcode代码测试：超出时间限制








