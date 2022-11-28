package Business.Table;

import Presentation.Visualisation.HashTables.HashHistogram;

public class Table {
    private final int r;
    private final HashNode[] hashTable;
    private int occupied = 0;

    public Table (int num_values, Business[] businesses){
        r = num_values*3;
        hashTable = new HashNode[r];
        long startTime = System.nanoTime();
        for (Business business : businesses) {
            insert(business);
        }
        System.out.println("inserted: "+num_values+"\nunique positions: "+occupied);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

    }

    /**
     * The insert function of the table
     * @param business containing the information about the business to be inserted
     */
    public void insert (Business business){
        String key = business.getName();
        HashHistogram.addBusiness(business.getDay());
        int pos = hash(key);

        if (hashTable[pos] == null){
            hashTable[pos] = new HashNode(key,business);
            occupied++;
        }else{
            hashTable[pos].add(new HashNode(key,business));
            //hashTable[quadraticProbingRehash(hash(key),0)] =  new HashNode(key,business);
        }
    }


    public void remove(String name) {
        int pos = hash(name);
        Business deleted;
        if (hashTable[pos].sameKey(name)){
            deleted = hashTable[pos].getBusiness(name);
            hashTable[pos] = hashTable[pos].getNext();
        }else {
            deleted = hashTable[pos].delete(name);
        }
        if (deleted != null){
            HashHistogram.deleteBusiness(deleted.getDay());
        }
    }

    public int hash(String key){
        int val =0;
        int count = 1;
        for (char a: key.toCharArray()){
            val+=(a*(count)*(count++))%r;
        }
        return val%r;
    }

    /**
     * a getter used to obtain the array of hashnodes
     * @return the array of hashnodes
     */
    public HashNode[] getHashTable() {
        return hashTable;
    }


    public int unfold(int pos){
        char[] a = String.valueOf(pos).toCharArray();
        int newVal = 0;
        for (int i = 0; i < a.length;i = i +2){
            if (i+1 >= a.length){
                newVal += a[i];
            }else {
                newVal += Integer.valueOf(a[i]+a[i+1]);
            }

        }
        return newVal%r;
    }

    public int linearProbingRehash(int key, int i){

        return key+i;
    }

    private static final int c1 = 5;
    private static final int c2 = 3;
    public int quadraticProbingRehash(int key, int i){
        int pos = (key + c1*i + i*i*c2)%r;
        if (hashTable[pos] != null){
            return quadraticProbingRehash(key, (i+1)%r);
        }
        return pos;
    }


    /**
     * Used to obtain the string of information for printing in the option check business
     * @param key the key of the business in hashtable
     * @return the string of information
     */
    public String buildBusinessString(String key){
        Business temp = getValue(key);
        if(temp == null)
            return "We are unable to locate your business";
        return "Name: " + temp.getName() +"\nDay: " + temp.getDay() + "\nPrice: " + temp.getPrice() + "€";
    }

    public Business getValue (String key) {
        int pos = hash(key);
        /*if (hashTable[pos].sameKey(key)) {
            return hashTable[pos].getBusiness();
        }*/
        if (hashTable[pos] == null)
            return null;

        if (hashTable[pos].hasKey(key)) {
            return hashTable[pos].getBusiness(key);
        }
        return null;
    }



}
