import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Raymond {
    List<Node> nodes = new ArrayList();
    boolean exit = false;
    int requests = 0;


    class Node {
        int id;
        Node parent = null;
        Queue<Integer> queue;
        boolean isInCritical = false;
        boolean isRequesting = false;
        boolean hasToken = false;


        //prepare node
        Node(int id) {
            this.id = id;
            queue = new LinkedList<>();
        }

        void setParent(Node n) {
            this.parent = n;
        }

        void sendRequest() {
            if (this.parent != null)
                if (!this.parent.queue.contains(this.id))
                    this.parent.queue.add(this.id);
        }

        void sendToken() {
            if(queue.size()>0) {
                int head = queue.peek();
                Node node = nodes.get(head);
                node.hasToken = true;
                this.hasToken = false;
                queue.remove();
                if (node != this.parent) {
                    this.parent = node;
                }
            }
        }

        void checkIfCanEnterCs() {
            int head = this.queue.peek();
            if (head == this.id) {
                onEnterCritical();
                isRequesting = false;
                isInCritical = true;
            }
        }

        void onEnterCritical() {
            System.out.println("node " + this.id + " entered critical section");
            this.queue.remove();
            onCriticalExit();

        }

        void onCriticalExit() {
            System.out.println("node " + this.id + " exited critical section");
            if (this.queue.size() > 0) {
                sendToken();
            }
            isInCritical = false;
            exit = true;
            requests--;
        }

    }

    //-----------------
    Raymond(int nrOfNodes) {
        for (int i = 0; i < nrOfNodes; i++) {
            nodes.add(new Node(i));
            if (i == 0) {
                nodes.get(i).hasToken = true;
                nodes.get(i).queue.add(nodes.get(i).id);
                nodes.get(i).isRequesting = true;
                requests++;
            }
        }
    }

    void setParentAndRequest(int node, int parent, boolean isRequesting) {
        Node par = nodes.get(parent - 1);
        Node child = nodes.get(node - 1);
        child.setParent(par);
        child.isRequesting = isRequesting;
        if (isRequesting == true) {
            requests++;
            child.queue.add(child.id);
        }
    }

    void start() {
        while (requests > 0) {
            for (Node node : nodes) {
                if (node.isRequesting && node.hasToken) {
                    node.checkIfCanEnterCs();
                }
                if(node.hasToken){
                    node.sendToken();
                }
                if (node.queue.size() > 0) {
                    node.sendRequest();
                }

                if(exit == true){
                    System.out.println(this.toString());
                    exit = false;
                    printRequests();
                }
            }

        }

    }

    void printRequests(){
        for(Node node : nodes){
            if (node.isRequesting){
                System.out.println("I still have request " + node.id);
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node n : nodes) {
            if (n.parent != null) {
                sb.append(n.id + " -> " + (n.parent.id) + " || queue");
                sb.append(n.queue);
                if (n.hasToken) {
                    sb.append(" || I have token\n");
                } else {
                    sb.append("\n");
                }
            } else {
                sb.append(n.id + " -> " + null + " || queue");
                sb.append(n.queue);
                if (n.hasToken) {
                    sb.append(" || I have token\n");
                } else {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

}
