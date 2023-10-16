import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        int result = Double.compare(o1.getPathCost(), o2.getPathCost());
        if (result != 0) return result;
        return o1.compareTo(o2);
    }
}
