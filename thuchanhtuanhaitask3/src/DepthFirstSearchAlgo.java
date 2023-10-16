import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        ArrayList<Node> explored = new ArrayList<>();
        Stack<Node> frontiers = new Stack<>();
        frontiers.push(root);
        while (!frontiers.isEmpty()) {
            Node current = frontiers.pop();
            explored.add(current);
            if (current.getLabel().equals(goal)) return current;
            else {
                for (Node child :
                        current.getChildrenNodes()) {
//                    if (!frontiers.contains(child) && !explored.contains(child)) {
                        frontiers.push(child);
                        child.setParent(current);
//                    }
                }
            }
        }
        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        ArrayList<Node> explored = new ArrayList<>();
        Stack<Node> frontiers = new Stack<>();
        boolean flag = false;
        frontiers.push(root);
        while (!frontiers.isEmpty()) {
            Node current = frontiers.pop();
            explored.add(current);
            if (current.getLabel().equals(goal)) return current;
            if (current.getLabel().equals(start) && !flag) {
                explored.clear();
                frontiers.clear();
                flag = true;
                current.setParent(null);
                frontiers.push(current);
            } else {
                for (Node child :
                        current.getChildrenNodes()) {
//                    if (!frontiers.contains(child) && !explored.contains(child)) {
                        frontiers.push(child);
                        child.setParent(current);
//                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");
        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);
        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);
        nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2);
        nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6);
        nodeF.addEdge(nodeG, 1);
        ISearchAlgo algo1 = new DepthFirstSearchAlgo();
        Node result = algo1.execute(nodeS, "G");
        System.out.println("BFS: " + NodeUtils.printPath(result));
        System.out.println("BFS: " + NodeUtils.printPath(algo1.execute(nodeS, "A", "H")));
    }
}
