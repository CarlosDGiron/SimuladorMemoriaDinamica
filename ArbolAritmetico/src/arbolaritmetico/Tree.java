/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arbolaritmetico;

/**
 *
 * @author cana0
 */
public class Tree {
    Node root;
    
    public Tree(){
        root=null;
    }
    
    public Tree(String expression){
        root=createFromExpression(expression);
    }
    
    public void reset(){
        root=null;
    }
    
    public void setRoot(String value){
        root=new Node(value);
    }
    
    public Node createSubTree(Node left, Node rigth, Node parent){
        parent.left=left;
        parent.rigth=rigth;
        return parent;
    }
    
    public boolean isEmpty(){
        return root==null;
    }
    
    public String getPreorder(Node subtree, String treeBuffer){
        String treeInPreorder="";
        if(subtree!=null){
            treeInPreorder=treeBuffer+subtree.value+'\n'+getPreorder(subtree.left,treeBuffer)+getPreorder(subtree.rigth,treeBuffer);
        }
        return treeInPreorder;
    }
    
    public String getInorder(Node subtree, String treeBuffer){
        String treeInorder="";
        if(subtree!=null){
            treeInorder=treeBuffer+getInorder(subtree.left,treeBuffer)+subtree.value+'\n'+getInorder(subtree.rigth,treeBuffer);
        }
        return treeInorder;
    }
    
    public String getPostorder(Node subtree, String treeBuffer){
        String treeInorder="";
        if(subtree!=null){
            treeInorder=treeBuffer+getPostorder(subtree.left,treeBuffer)+getPostorder(subtree.rigth,treeBuffer)+subtree.value+'\n';
        }
        return treeInorder;
    }
    
    private int operatorPriority(char operator){
        int priority=100;
        switch(operator){
            case '^' -> priority=30;
            case '*' -> priority=20;
            case '/' -> priority=20;
            case '+' -> priority=10;
            case '-' -> priority=10;
            default->priority=0;
        }
        return priority;
    }
    
    private boolean isOperator(char c){
        switch (c){
            case '(' :
            case ')' :
            case '^' :
            case '*' :
            case '/' :
            case '+' :
            case '-' :
                return true;
            default:
                return false;
        }
    }
    
    Node createFromExpression(String expression) {
        StackTree operatorStack= new StackTree();
        StackTree operandStack= new StackTree();
        boolean isDigit=false;
        Node iteratorNode, leftOperand, rigthOperand, operatorNode;
        for(char currentCharacter:expression.toCharArray()){
            iteratorNode=new Node(String.valueOf(currentCharacter));
            if(isOperator(currentCharacter)){
                isDigit=false;
                switch(currentCharacter){
                    case '(':
                        operatorStack.insert(iteratorNode);
                        break;
                    case ')':
                        while(!operatorStack.isEmpty() && !operatorStack.getStart().value.toString().equals("(")){
                            rigthOperand=operandStack.getNext();
                            leftOperand=operandStack.getNext();
                            operatorNode=operatorStack.getNext();
                            operatorNode=createSubTree(leftOperand,rigthOperand,operatorNode);
                            operandStack.insert(operatorNode);
                        }
                        iteratorNode=operatorStack.getNext();
                        break;
                    default:
                        while(!operatorStack.isEmpty()&& operatorPriority(currentCharacter)<=operatorPriority(operatorStack.getStart().value.charAt(0))){
                            rigthOperand=operandStack.getNext();
                            leftOperand=operandStack.getNext();
                            operatorNode=operatorStack.getNext();
                            operatorNode=createSubTree(leftOperand,rigthOperand,operatorNode);
                            operandStack.insert(operatorNode);
                        }
                        operatorStack.insert(iteratorNode);
                }
            }
            else{
                if(!isDigit){
                    isDigit=true;
                    operandStack.insert(iteratorNode);  
                }else{
                    String digitConcat= operandStack.getNext().value.toString()+iteratorNode.value.toString();
                    iteratorNode=new Node(digitConcat);
                    operandStack.insert(iteratorNode);
                }
            }
        }
        while(!operatorStack.isEmpty()){
            rigthOperand=operandStack.getNext();
            leftOperand=operandStack.getNext();
            operatorNode=operatorStack.getNext();
            operatorNode=createSubTree(leftOperand,rigthOperand,operatorNode);
            operandStack.insert(operatorNode);
        }
        return operandStack.getNext();
    }
    
    private double evaluateTree(Node subTree){
        double result=0;
        if(isOperator(subTree.value.charAt(0))){
            return Double.parseDouble(subTree.value);
        }
        else{
            switch(subTree.value.charAt(0)){
                case '^' :
                    result+=Math.pow(evaluateTree(subTree.left),evaluateTree(subTree.rigth));
                    break;
            case '*' :
                result+=evaluateTree(subTree.left)*evaluateTree(subTree.rigth);
                break;
            case '/' :                
                result+=evaluateTree(subTree.left)/evaluateTree(subTree.rigth);
                break;
            case '+' :
                result+=evaluateTree(subTree.left)+evaluateTree(subTree.rigth);
                break;
            case '-' :                
                result+=evaluateTree(subTree.left)-evaluateTree(subTree.rigth);
                break;
            }
        }
        return result;
    }
    int countFullNodes(Node subTree){
        if(subTree==null){
            return 0;
        }
        else{
            if(subTree.left!=null&&subTree.rigth!=null){
                return countFullNodes(subTree.left)+countFullNodes(subTree.rigth)+1;
            }
            return countFullNodes(subTree.left)+countFullNodes(subTree.rigth);
        }
    }    
}
