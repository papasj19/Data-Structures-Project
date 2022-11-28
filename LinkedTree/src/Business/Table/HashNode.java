package Business.Table;

import Presentation.Visualisation.HashTables.HashHistogram;

public class HashNode {
    private String key;
    private Business business;
    private HashNode next;

    public HashNode(String key,Business business) {
        this.key = key;
        this.business = business;
    }

    public void add(HashNode node) {
        if (next == null){
            next = node;
        } else {
            next.add(node);
        }
    }

    public Business delete(String name){
        if (next == null) {
            System.out.println("Node not found!");
            return null;
        } else {
            if (next.sameKey(name)) {
                Business deleted = next.business;
                next = next.next;
                return deleted;
            }
            else
                return next.delete(name);
        }
    }

    public boolean sameKey(String key){
        return business.getName().equals(key);
    }

    /**
     * A getter to obtain the business at this hashnode
     * @return the business
     */
    public Business getBusiness(String key){
        if (sameKey(key)) return business;

        if (next == null){
            return null;
        } else {
            return next.getBusiness(key);
        }
    }

    /**
     * Used to obtain the key of the hashtable
     * @return a string of the key
     */
    public String getKey() {
        return key;
    }

    public HashNode getNext() {
        return next;
    }

    public boolean hasKey(String key) {
        if (sameKey(key)) return true;

        if (next == null){
            return false;
        } else {
            return next.sameKey(key);
        }
    }


}
