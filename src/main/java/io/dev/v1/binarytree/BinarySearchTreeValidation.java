package io.dev.v1.binarytree;


public class BinarySearchTreeValidation {

    private TreeNode root;

    class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode(int x) {
             val = x;
         }
     }

    public boolean isValidBST(){
        return isValidBST(root, root.val, "");
    }

    // PreOrder +AB
    // InOrder  A+B
    // PostOrder AB+
    public boolean isValidBST(TreeNode node, int rootVal, String direction) {
        boolean returnVal = false;
        if (node == null){
            return true;
        }

        System.out.println("Node Val: " +node.val + "     Root Val: " +rootVal);

        if (direction.equals("left")){

            if(node.left != null) {
                if (node.left.val <= node.val && node.left.val <= rootVal) {
                    System.out.println("Valid Left: " + node.left.val);
                } else {
                    System.out.println("Left Tree >>>> Invalid Left: ");
                    return false;
                }
            }
            if (node.right != null) {
                if (node.right.val > node.val && node.right.val <= rootVal) {
                    System.out.println("Valid Right: " + node.right.val);
                } else {
                    if (node.right != null) {
                        System.out.println("Left Tree >>>> Invalid Right: ");
                        return false;
                    }
                }
            }
        }

        returnVal = isValidBST(node.left, rootVal, "left");
        if (returnVal == false) {
            return false;
        }

        if (direction.equals("right")) {
            if (node.left != null) {
                if (node.left.val <= node.val && node.left.val > rootVal) {
                    System.out.println("Valid Left: " + node.left.val);
                } else {
                    System.out.println("Right Tree >>>> Invalid Left: ");
                    return false;
                }
            }
            if(node.right != null) {
                if (node.right.val > node.val && node.right.val > rootVal) {
                    System.out.println("Valid Right: " + node.right.val);
                } else {
                    System.out.println("right Tree >>>> Invalid Right: ");
                    return false;
                }
            }
        }


        returnVal = isValidBST(node.right, rootVal, "right");
        return returnVal != false;
    }

    public void addElementsToTree(){
        TreeNode node;
        node = new TreeNode(100);
        root = node;
        node = new TreeNode(50);
        root.left = node;
        node = new TreeNode(150);
        root.right = node;

        node = new TreeNode(25);
        root.left.left = node;
        node = new TreeNode(75);
        root.left.right = node;

        node = new TreeNode(125);
        root.right.left = node;
        node = new TreeNode(175);
        root.right.right = node;
    }

    public static void main(String[] args){
     BinarySearchTreeValidation validBST = new BinarySearchTreeValidation();

     validBST.addElementsToTree();
     validBST.isValidBST();

    }

}