package com.company;

import java.util.*;

class Node {

    public int value_of_root;

    public Node left;
    public Node right;
    public Node parent;

    int depth = 0;
    int level = 0;
    int drawPos = 0;

    void insert(int value){

        if (this.value_of_root == value) return;

        if (this.value_of_root < value) {

            if (this.right == null) {
                this.right = new Node();
                this.right.value_of_root = value;
                this.right.parent = this;

            } else {
                this.right.insert(value);
            }
        }
        else {

            if (this.left == null) {
                this.left = new Node();
                this.left.value_of_root = value;
                this.left.parent = this;

            } else {
                this.left.insert(value);
            }
        }
    }

    static void printTree(Node root) {

        int depth = depth(root);
        setLevels (root, 0);

        int depthChildCount[] = new int [depth+1];


        LinkedList<Node> q = new LinkedList<Node>();
        q.add(root.left);
        q.add(root.right);

        root.drawPos = (int) Math.pow(2, depth - 1) * H_SPREAD;
        currDrawLevel = root.level;
        currSpaceCount = root.drawPos;
        System.out.print(getSpace(root.drawPos) + root.value_of_root);

        while (!q.isEmpty())
        {
            Node ele = q.pollFirst();
            printElement(ele, depthChildCount, depth, q);
            if (ele == null)
                continue;
            q.add(ele.left);
            q.add(ele.right);
        }
        System.out.println();
    }

    static int currDrawLevel  = -1;
    static int currSpaceCount = -1;
    static final int H_SPREAD = 3;
    static void printElement(Node ele, int depthChildCount[], int depth, LinkedList<Node> q) {
        if (ele == null) return;

        if (ele.level != currDrawLevel) {
            currDrawLevel = ele.level;
            currSpaceCount = 0;
            System.out.println();
            for (int i = 0; i < (depth - ele.level + 1); i++) {
                int drawn = 0;
                if (ele.parent.left != null) {
                    drawn = ele.parent.drawPos - 2 * i - 2;
                    System.out.print(getSpace(drawn) + "/");
                }
                if (ele.parent.right != null) {
                    int drawn2 = ele.parent.drawPos + 2 * i + 2;
                    System.out.print(getSpace(drawn2 - drawn) + "\\");
                    drawn = drawn2;
                }

                Node doneParent = ele.parent;
                for (Node sibling: q) {
                    if (sibling == null) continue;
                    if (sibling.parent == doneParent) continue;
                    doneParent = sibling.parent;

                    if (sibling.parent.left != null) {
                        int drawn2 = sibling.parent.drawPos - 2 * i - 2;
                        System.out.print(getSpace(drawn2 - drawn - 1) + "/");
                        drawn = drawn2;
                    }

                    if (sibling.parent.right != null) {
                        int drawn2 = sibling.parent.drawPos + 2 * i + 2;
                        System.out.print(getSpace(drawn2-drawn-1) + "\\");
                        drawn = drawn2;
                    }
                }
                System.out.println();
            }
        }
        int offset = 0;
        int numDigits = (int) Math.ceil(Math.log10(ele.value_of_root));
        if (ele.parent.left == ele) {
            ele.drawPos = ele.parent.drawPos - H_SPREAD * (depth - currDrawLevel + 1);
            offset += numDigits / 2;
        }
        else {
            ele.drawPos = ele.parent.drawPos + H_SPREAD * (depth - currDrawLevel + 1);
            offset -= numDigits;
        }

        System.out.print (getSpace(ele.drawPos - currSpaceCount + offset) + ele.value_of_root);
        currSpaceCount = ele.drawPos + numDigits / 2;
    }

    public static int depth (Node n) {
        if (n == null) return 0;
        n.depth = 1 + Math.max(depth(n.left), depth(n.right));
        return n.depth;
    }

    static void setLevels (Node r, int level) {
        if (r == null)
            return;
        r.level = level;
        setLevels (r.left, level + 1);
        setLevels (r.right, level + 1);
    }

    static String getSpace (int i) {
        String s = "";
        while (i-- > 0) s += " ";
        return s;
    }

}

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Node root = new Node();
        System.out.println("Введите узлы:");

        String input = in.nextLine();
        String nodes[] = input.split(" ");
        List<Integer> d = new ArrayList<Integer>();

        for (int i = 0; i < nodes.length; i++){
            int f = Integer.parseInt(nodes[i]);
            d.add(f);
        }

        for(int i = 0; i < d.size(); i++){
            int node = d.get(i);
            if(i == 0){
                root.value_of_root = node;
            }else{
                root.insert(node);
            }
        }
        root.printTree(root);
    }
}
