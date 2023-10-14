import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchAlgo implements ISearchAlgo {
    @Override
    public Node execute(Node root, String goal) {
        Queue<Node> frontier = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);
            if (current.getLabel().equals(goal)) return current;
            else {
                for (Node child :
                        current.getChildrenNodes()) {
                    if (!explored.contains(child) && !frontier.contains(child)) {
                        frontier.add(child);
                        child.setParent(current);
                    }
                }
            }
        }

        return null;
    }

    @Override
    public Node execute(Node root, String start, String goal) {
        Queue<Node> frontier = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        boolean flag = false;
        frontier.add(root);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);
            if (current.getLabel().equals(goal)) return current;
            if (current.getLabel().equals(start) && !flag) {
                frontier.clear();
                explored.clear();
                frontier.add(current);
                current.setParent(null);
                flag = true;
            }
            for (Node child :
                    current.getChildrenNodes()) {
                if (!frontier.contains(child) && !explored.contains(child)) {
                    frontier.add(child);
                    child.setParent(current);
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
        ISearchAlgo algo1 = new BreadthFirstSearchAlgo();
        Node result = algo1.execute(nodeS, "G");
        System.out.println("BFS: " + NodeUtils.printPath(result));
        System.out.println("BFS: " + NodeUtils.printPath(algo1.execute(nodeS, "A", "H")));
    }
}
