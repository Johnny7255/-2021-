
import java.util.*;

public class BinarySearchTree 
{
   private Node root;
   
   /**插入元素*/
   public void insert(int value) 
   {
	  root=insertRec(value,root);
   }
   
   /**查询元素是否存在*/
   public boolean containElement(int value) 
   {
	   return searchRec(value,root)==null;
   }
   
   /**删除*/
   public void delete(int value) 
   {
	   deleteRec(value,root);
   }
   
   /**先序遍历*/
   public void preorder(Node node)
   {
	   if(node==null) return;
	   System.out.print(node.value);
	   if(node.left!=null) inorder(node.left);
	   if(node.right!=null) inorder(node.right);
   }
   
   /**中序遍历*/
   public void inorder(Node node)
   {
	   if(node==null) return;
	   if(node.left!=null) inorder(node.left);
	   System.out.print(node.value);
	   if(node.right!=null) inorder(node.right);
   }
   
   /**后先序遍历*/
   public void postorder(Node node)
   {
	   if(node==null) return;
	   if(node.left!=null) inorder(node.left);
	   if(node.right!=null) inorder(node.right);
	   System.out.print(node.value);
   }
   
   public void layerorder(Node node) 
   {
	   Queue<Node> queue=new LinkedList<Node>();
	   queue.add(node);
	   while(!queue.isEmpty()) 
	   {
		   Node head=queue.poll();
		   System.out.print(head.value);
		   if(head.left!=null) queue.add(head.left);
		   if(head.right!=null)queue.add(head.right);
	   }
   }
   
   /**
    * 每一次递归，都会寻找下一个应该插入的位置，在该插入的地方插入值
    * */
   private Node insertRec(int value,Node current) 
   {
	   //提出节点，并且更换节点
	   if(current==null) return new Node(value);
	   else if(current.compareTo(value)>0) current.left=insertRec(value,current.left);
	   else current.right=insertRec(value,current.right);
	   return current;
   }
   
   /**
    * 递归地查找下一个可能出现该点的位置
    * */
   private Node searchRec(int value,Node current) 
   {
	   if(current==null) return null;
	   int sym=current.compareTo(value);
	   
	   if(sym==0) return current;
	   else if(sym>0) return searchRec(value,current.right);
	   else return searchRec(value,current.left);
   }
   
   /**递归删除目标节点*/
   private Node deleteRec(int value,Node current) 
   {		   
	  if(current==null) return null;
	   int sym=current.compareTo(value);
	   if(sym==0) 
	   {
		 //没只有一个子节点，直接删除
		   if(current.left==null&&current.right==null) return null;
		   
		   //只有一个子节点，更换
		   else if(current.left!=null) return current.left;
		   else if(current.right!=null) return current.right;
		   
		   //有两个子节点，用右子树的子节点替换被删的，然后递归删除
		   else  
		   {
			   current.value=findMinNode(current).value;
			   current.right=deleteRec(value,current);
			   return current;
		   }
	   }
	   
	   //以下内容为寻找节点，以及替换节点
	   else if(sym>0) current.right=deleteRec(value,current.right);
	   else current.left=deleteRec(value,current.left);
	   return current;
   }
   
   /**寻找某个节点的最小子节点*/
   private Node findMinNode(Node current) 
   {
	   if(current==null) return null;
	   while(current.left!=null) 
	   {
		   current=current.left;
	   }
	   return current;
   }
   
}

class Node
{
	public Node left;
	public Node right;
	int value;
	
	public Node(int value)
	{
		this.value=value;
		left=null;
		right=null;
	}
	
	public int compareTo(int b) 
	{
		return value-b;
	}
}