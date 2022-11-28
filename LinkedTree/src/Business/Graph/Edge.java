package Business.Graph;

import java.sql.Time;

/**
 * A class used to represent an edge within our linked tree
 * The following attributes are provided by the project statement
 */

public class Edge {
    private int sourceid;
    private int destid;
    private Time timeStamp;
    private int numbInteractions;

    // order of the graph is number of users
    // size of the graph is the number of follows


    public Edge(int sourceid, int destid, Time timeStamp, int numbInteractions) {
        this.sourceid = sourceid;
        this.destid = destid;
        this.timeStamp = timeStamp;
        this.numbInteractions = numbInteractions;
    }

    public int getSourceid () {
        return sourceid;
    }

    public int getDestid () {
        return destid;
    }


    public long getTimeStamp() {
        return timeStamp.getTime();
    }
}
