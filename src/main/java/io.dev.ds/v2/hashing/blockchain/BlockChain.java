package dev.vjammi.ds.v2.hashing.blockchain;

import com.google.gson.GsonBuilder;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BlockChain {
    private List<Block> blockchain;

    public BlockChain(List<Block> blockchain) {
        this.blockchain = blockchain;
    }

    public boolean isChainValid(LinkedList<Block> blockChain) throws UnsupportedEncodingException {
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/LinkedList.html
        // Iterator<Block> descendingIterator = blockChain.descendingIterator();

        Iterator<Block> iterator = blockChain.iterator();
        Block previousBlock = null;
        while (iterator.hasNext()){
            Block currentBlock = iterator.next();
            if (previousBlock != null) {
                System.out.println(currentBlock.data() + " " + currentBlock.hash() + " " + previousBlock.hash() + " " + currentBlock.previousHash() + " " + currentBlock.timestamp() + " ");
                if (!previousBlock.hash().equals(currentBlock.previousHash())) return false;
            }else {
                System.out.println(currentBlock.data() + " " + currentBlock.hash() + " " + previousBlock + " " + currentBlock.previousHash() + " " + currentBlock.timestamp() + " ");
            }
            previousBlock = currentBlock;
        }
        return true;
    }

    private void buildBlockChain(LinkedList<Block> blockchain) throws UnsupportedEncodingException {
        Block genesis = new Block("genesisBlock", null);
        blockchain.add(genesis);

        Block block1 = new Block("block1", genesis.hash());
        blockchain.add(block1);

        Block block2 = new Block("block2", block1.hash());
        blockchain.add(block2);

        Block block3 = new Block("block3", block2.hash());
        blockchain.add(block3);

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        LinkedList<Block> blockchain = new LinkedList<>();
        BlockChain chain = new BlockChain(blockchain);
        chain.buildBlockChain(blockchain);
        chain.isChainValid(blockchain);
    }
}
