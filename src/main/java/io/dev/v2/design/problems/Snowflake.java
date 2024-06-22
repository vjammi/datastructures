package io.dev.v2.design.problems;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.Objects;


/**
 * 2199023255552/(365*24*60*60*1000)=
 *
 * */
//Ref:
// https://apoorvtyagi.tech/generating-unique-ids-in-a-large-scale-distributed-environment
// https://github.com/twitter-archive/snowflake/tree/snowflake-2010
public class Snowflake {

    // Sign bit, Unused (always set to 0)
    private static final int UNUSED_BITS = 1;

    private static final int EPOCH_BITS = 20;
    private static final int NODE_ID_BITS = 5;
    private static final int SEQUENCE_BITS = 6;

    private static final int maxNodeId = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int maxSequence = (int) (Math.pow(2, SEQUENCE_BITS) - 1);

    // Custom Epoch (Fri, 21 May 2021 03:00:20 GMT)
   private static final int DEFAULT_CUSTOM_EPOCH = 1621566020;

    private volatile int lastTimestamp = -1;
    private final int sequence = 0;

    // ???
   private final int nodeId;
   private final int customEpoch;

    public Snowflake() {
        this.nodeId = createNodeId();
        this.customEpoch = DEFAULT_CUSTOM_EPOCH;
    }

    public synchronized int nextId() {
        int currentTimestamp = (int) (Instant.now().toEpochMilli() );//.getEpochSecond() //- customEpoch

        if(currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        lastTimestamp = currentTimestamp;

        return currentTimestamp << (NODE_ID_BITS + SEQUENCE_BITS) | (nodeId << SEQUENCE_BITS) | sequence;
    }

    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (Objects.nonNull(mac))
                    for(byte macPort: mac)
                        sb.append(String.format("%02X", macPort));
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }
        nodeId = nodeId & maxNodeId;
        return nodeId;
    }

    public static void main(String[] args) {
        Snowflake sf = new Snowflake();
        for (int i=0; i<10; i++) {
            System.out.println(sf.nextId());
        }
    }
}
