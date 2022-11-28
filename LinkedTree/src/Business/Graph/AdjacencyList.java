package Business.Graph;



import Business.Tree.RTree.Rectangle;

import java.util.*;


public class AdjacencyList  {
    private User[] nodesList;
    private ArrayList<Edge>[] list;


    /**
     *
     * @param nodesList
     * @param edges
     * @param maxID
     */
    public AdjacencyList (User[] nodesList, Edge[] edges, int maxID) {
        long startTime = System.nanoTime();
        this.nodesList = new User[maxID+1];
        for (User node:nodesList) this.nodesList[node.getId()] = node;
        initList(maxID+1);
        for (Edge edge: edges){
            list[edge.getSourceid()].add(edge);
        }

        System.out.println("loading file took "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

    }

    /**
     * Auxiliary method used to initialize the list as an attribute
     * @param size the size of the list we want to make
     */
    private void initList(int size){
        list = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            list[i] = new ArrayList<>();
        }
    }

    public boolean nodeExists (int id) {
        return nodesList[id] != null;
    }


    /**
     * The implementation of the Depth First Search as seen in class
     * This vis
     * @param node
     */
    public ArrayList<User> DFS (User node) {
        ArrayList<User> myList = new ArrayList<>();
        node.setVisited();
        for (User adjacentUser : getFollowed(node))
            if (adjacentUser != null){
                if (!adjacentUser.isVisited()) {
                    myList.add(adjacentUser);
                    myList.addAll(DFS(adjacentUser));
                }
            }

        return myList;
    }

    /**
     * The implementation of Breadth First Search as seen in class
     * This visits all adjacencies of a vertex before expanding them
     *      ----------> we move horizontally
     * @param node the begging node of the search
     * @return
     */
    public ArrayList<Integer>[] BFS (User node) {
        ArrayList<Integer>[] following = new ArrayList[2];
        // the users are following
        following[0] = new ArrayList<>();
        // the following of the users we are following
        following[1] = new ArrayList<>();
        int i = 0;
        int a = list[node.getId()].size();
        LinkedList<User> myList = new LinkedList<>();
        myList.add(node);
        node.setVisited();
        while(!myList.isEmpty()) {
            User newUser = myList.removeFirst();
            for (Edge e : list[newUser.getId()]) {
                User adjacentUser = nodesList[e.getDestid()];
                if (!adjacentUser.isVisited()) {
                    myList.add(adjacentUser);
                    adjacentUser.setVisited();

                }
                if (i < 2) following[i].add(adjacentUser.getId());

            }
            if (i==1) a--;
            else i++;
            if (a == 0) i++;
        }

        return following;

    }



    public void disconnectedDFS(){
        for(User node: nodesList){
            if(!node.isVisited()){
                DFS(node);
            }
        }
        setUnvisited();
    }

    /**
     * Topo sort as seen in the semester 2 notes, implemented using the psuedo code
     *      -(Slight modification of the DFS)
     *      -keeps adding visited verticies to a stack recursively
     * @return a stack of the users
     */
    public LinkedList<User> topoSort(){
        LinkedList<User> myStack = new LinkedList<>();
        User n;
        while ((n = getNextUnvisited()) != null) {
            visit(n, myStack);
        }
        setUnvisited();
        return myStack;
    }

    private void visit (User node, LinkedList<User> s) {
        node.setVisited();
        for (Edge e : list[node.getId()]) {
            User n = nodesList[e.getDestid()];
            if (!n.isVisited())
                visit(n, s);
        }
        s.addFirst(node);
    }
    /**
     * MArks the user as NOT visited
     */
    public void setUnvisited(){
        for(User node: nodesList){
            if (node == null)
                continue;
            node.unsetVisited();
        }
    }

    public void disconnectedBFS(){
        for(User node: nodesList){
            if(!node.isVisited()){
                BFS(node);
            }
        }
        setUnvisited();
    }


    /**
     * The implementation of dijkstras shortest path algorithm as provided in the 2nd semester notes
     * We start at the given node and search for the path to the
     * @param startUser the desired node to begin the search
     * @param endUser the diesired node to complete the search
     * @return an array containg to the locations to the node desired
     */
    public int[] dijkstra(User startUser, User endUser){
        long newLength;
        int[] walk = new int[nodesList.length];
        long[] length = new long[nodesList.length];
        boolean found = false;
        Arrays.fill(walk, 0);
        Arrays.fill(length, Long.MAX_VALUE);
        walk[startUser.getId()] = startUser.getId();
        length[startUser.getId()] = 0;
        ArrayList<User> frontier = new ArrayList<>();
        frontier.add(startUser);
        while (!frontier.isEmpty()) {
            User currentUser = frontier.remove(0);
            if (currentUser.getId() == endUser.getId())
                continue;
            if (length[currentUser.getId()] >= length[endUser.getId()])
                continue;
            for (Edge e : list[currentUser.getId()]) {
                User n = nodesList[e.getDestid()];
                if ((newLength = length[currentUser.getId()] + e.getTimeStamp()) < length[n.getId()]) {
                    length[n.getId()] = newLength;
                    frontier.add(n);
                    walk[n.getId()] = currentUser.getId();
                }
            }
        }
        return walk;
    }




    public static int[] undoPath (int[] walk, int startUserId, int endUserId) {
        ArrayList<Integer> reversePath = new ArrayList<>();

        int currentId = endUserId;
        reversePath.add(currentId);
        while (currentId != startUserId) {
            currentId = walk[currentId];
            reversePath.add(currentId);
        }

        int[] reverseArray = new int[reversePath.size()];
        for (int i = 0; i < reverseArray.length; i++)
            reverseArray[i] = reversePath.get(reverseArray.length - i - 1);

        return reverseArray;
    }

    /**
     * Obtains the users that are adjacent to the user in the parameters
     * @param node the User to check for the adjacent
     * @return an Arraylist of type user with the adjacent users to the parameter
     */
    public ArrayList<User> getAdjacentUsers (User node) {
        ArrayList<User> nodes = new ArrayList<>();
        for (Edge e : list[node.getId()])
            nodes.add(nodesList[e.getDestid()]);
        return nodes;
    }

    public ArrayList<User> getFollowed(User node){
        ArrayList<User> nodes = new ArrayList<>();
        for(ArrayList<Edge> listy: list){
            for(Edge edgy: listy){
                if(edgy.getDestid() == node.getId()){
                    nodes.add(nodesList[(edgy.getSourceid())]);
                }
            }
        }
        return nodes;
    }

    /**
     * Obtains the next unvisited user from the nodesList
     * @return the User that is NOT visited
     */
    private User getNextUnvisited () {
        for (User n : nodesList) {
            if (n == null)
                continue;
            if (!n.isVisited())
                return n;
        }
        return null;
    }




    public User getUser(int id) {
        return nodesList[id];
    }

    public User getMostFollowed() {
        User mostFollowed = new User(-1,"Error","Error");
        int numFollowers = -1;
        for (int i = 0; i < list.length; i++){
            if (list[i] != null) {if (list[i].size() > numFollowers){ mostFollowed = nodesList[i]; numFollowers = list[i].size();}}
        }
        return mostFollowed;
    }

    public String getLink (int original, int link) {
        return "";
    }

    public void sortPref(ArrayList<User> listy, User mainDude){
        for (int i = 0; i < listy.size(); i++) {
            for (int j = i+1; j < listy.size(); j++) {
                if (calcPref(mainDude, listy.get(j)) > calcPref(mainDude, listy.get(i))) {
                    User temp = listy.get(i);
                    listy.set(i, listy.get(j));
                    listy.set(j, temp);
                }
            }
        }
    }

    private int calcPref(User compareTo, User comparing){
        int scoreOut = 0;
        for(String current: compareTo.getInterests()){
            if(Arrays.asList(comparing.getInterests()).contains(current)){
                scoreOut++;
            }
        }
        return scoreOut;
    }

    public String[] recommendUsers (User ourUser) {
        int usersViewed = 0;
        String[] strings = new String[10];
        RecommendedUser[] recommendations = new RecommendedUser[10];
        for (int i = 0; i <recommendations.length; i++){
            recommendations[i] = new RecommendedUser(-1,-1,false,-1, new User[2]);
        }
        int minPos = 0;
        long startTime = System.nanoTime();
        for (Edge e : list[ourUser.getId()]){
            for (Edge e2 : list[e.getDestid()]){
                User user = nodesList[e2.getDestid()];
                if (nodesList[e2.getDestid()].isVisited()) continue;
                if (user.getId() != ourUser.getId() && doesNotFollow(ourUser,user)){
                    nodesList[e2.getDestid()].setVisited();
                    boolean follows = !doesNotFollow(user,ourUser);
                    int commonInterests = calcPref(ourUser,user);
                    User[] commonF = new User[2];
                    int commonFollows = getNumFollowing(ourUser,user,commonF);
                    RecommendedUser newRec = new RecommendedUser(commonFollows,commonInterests,follows,user.getId(),commonF);
                    if (recommendations[minPos].getScore() < newRec.getScore()){
                        recommendations[minPos] = newRec;
                        strings[minPos] = newRec.getRecommendingString();
                        int minScore = Integer.MAX_VALUE;
                        usersViewed++;
                        for (int i = 0; i <recommendations.length; i++){
                            if (recommendations[i].getScore() < minScore){
                                minScore = recommendations[i].getScore();
                                minPos = i;
                            }
                        }
                    }
                }
            }

        }

        if (usersViewed == 0){

            for (User n: nodesList) {
                if (n == null) continue;
                if (nodesList[n.getId()].isVisited()) continue;
                if (n.getId() != ourUser.getId() && doesNotFollow(ourUser,n)){
                    nodesList[n.getId()].setVisited();
                    boolean follows = !doesNotFollow(n,ourUser);
                    int commonInterests = calcPref(ourUser,n);
                    User[] commonF = new User[2];
                    int commonFollows = getNumFollowing(ourUser,n,commonF);
                    RecommendedUser newRec = new RecommendedUser(commonFollows,commonInterests,follows,n.getId(),commonF);
                    if (newRec.getScore() == 0) continue;
                    if (recommendations[minPos].getScore() < newRec.getScore()){
                        recommendations[minPos] = newRec;
                        strings[minPos] = newRec.getRecommendingString();
                        int minScore = Integer.MAX_VALUE;
                        usersViewed++;
                        for (int i = 0; i <recommendations.length; i++){
                            if (recommendations[i].getScore() < minScore){
                                minScore = recommendations[i].getScore();
                                minPos = i;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

        return strings;
    }

    private int getNumFollowing(User ourUser, User user,User[] common) {
        int score = 0;
        for (Edge e: list[ourUser.getId()]){
            boolean found = false;
            for (int i = 0; i < list[e.getDestid()].size() && !found ;i++){
                if (list[e.getDestid()].get(i).getDestid() == user.getId()){
                    if (score <2) common[score] = nodesList[e.getDestid()];
                    score++;
                    found = true;
                }
            }
        }
        return score;
    }

    private boolean doesNotFollow(User ourUser, User user) {
        for (Edge edge: list[ourUser.getId()]){
            if (edge.getDestid() == user.getId()) return false;
        }
        return true;
    }


    public class RecommendedUser {
        private int commonFollowers;
        private int commonInterests;
        private boolean followsUser;
        private int user;
        private User[] commonF;

        public RecommendedUser(int commonFollowers, int commonInterests, boolean followsUser, int user,User[] commonF) {
            this.commonFollowers = commonFollowers;
            this.commonInterests = commonInterests;
            this.followsUser = followsUser;
            this.user = user;
            this.commonF = commonF;
        }
        public int getUserId(){
            return user;
        }
        public int getScore(){
            return commonFollowers + commonInterests + ((followsUser)? 5:0);
        }
        public String getRecommendingString(){
            if (commonFollowers < 0 ) return "";

            String s = nodesList[user].getPrintData()+"\n\tCriteria: ";
            if (commonInterests > 0){
                s+=" You share "+commonInterests+" interests";
                if (commonFollowers > 0 || followsUser) s+= " and ";
            }
            if (commonFollowers > 0) {
                s+= (commonInterests > 0)?"f":"F";
                s+="ollowed by "+commonFollowers+" - "+ ((commonF[0] != null)? commonF[0].getName()+" ("+commonF[0] .getAlias()+")":"")+ ((commonF[1] != null)? ", "+commonF[1].getName()+" ("+commonF[1] .getAlias()+")":"")+(commonFollowers > 2?" ect":"");
                if (followsUser) s+= " and ";
            }
            if (followsUser){
                s+="they follow you";
            }

            return s;
        }
    }
}