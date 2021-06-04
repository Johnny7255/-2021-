
import java.util.*;

public class BinarySearchTree 
{
   private Node root;
   
   /**����Ԫ��*/
   public void insert(int value) 
   {
	  root=insertRec(value,root);
   }
   
   /**��ѯԪ���Ƿ����*/
   public boolean containElement(int value) 
   {
	   return searchRec(value,root)==null;
   }
   
   /**ɾ��*/
   public void delete(int value) 
   {
	   deleteRec(value,root);
   }
   
   /**�������*/
   public void preorder(Node node)
   {
	   if(node==null) return;
	   System.out.print(node.value);
	   if(node.left!=null) inorder(node.left);
	   if(node.right!=null) inorder(node.right);
   }
   
   /**�������*/
   public void inorder(Node node)
   {
	   if(node==null) return;
	   if(node.left!=null) inorder(node.left);
	   System.out.print(node.value);
	   if(node.right!=null) inorder(node.right);
   }
   
   /**���������*/
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
    * ÿһ�εݹ飬����Ѱ����һ��Ӧ�ò����λ�ã��ڸò���ĵط�����ֵ
    * */
   private Node insertRec(int value,Node current) 
   {
	   //����ڵ㣬���Ҹ����ڵ�
	   if(current==null) return new Node(value);
	   else if(current.compareTo(value)>0) current.left=insertRec(value,current.left);
	   else current.right=insertRec(value,current.right);
	   return current;
   }
   
   /**
    * �ݹ�ز�����һ�����ܳ��ָõ��λ��
    * */
   private Node searchRec(int value,Node current) 
   {
	   if(current==null) return null;
	   int sym=current.compareTo(value);
	   
	   if(sym==0) return current;
	   else if(sym>0) return searchRec(value,current.right);
	   else return searchRec(value,current.left);
   }
   
   /**�ݹ�ɾ��Ŀ��ڵ�*/
   private Node deleteRec(int value,Node current) 
   {		   
	  if(current==null) return null;
	   int sym=current.compareTo(value);
	   if(sym==0) 
	   {
		 //ûֻ��һ���ӽڵ㣬ֱ��ɾ��
		   if(current.left==null&&current.right==null) return null;
		   
		   //ֻ��һ���ӽڵ㣬����
		   else if(current.left!=null) return current.left;
		   else if(current.right!=null) return current.right;
		   
		   //�������ӽڵ㣬�����������ӽڵ��滻��ɾ�ģ�Ȼ��ݹ�ɾ��
		   else  
		   {
			   current.value=findMinNode(current).value;
			   current.right=deleteRec(value,current);
			   return current;
		   }
	   }
	   
	   //��������ΪѰ�ҽڵ㣬�Լ��滻�ڵ�
	   else if(sym>0) current.right=deleteRec(value,current.right);
	   else current.left=deleteRec(value,current.left);
	   return current;
   }
   
   /**Ѱ��ĳ���ڵ����С�ӽڵ�*/
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