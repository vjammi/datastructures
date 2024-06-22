package io.dev.v2.design.problems;

import java.util.HashMap;
import java.util.Map;

/**
 * 1166. Design File System
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 *     bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
 *     int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 *
 *
 * Example 1:
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 *
 * Example 2:
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 * */
public class FileSystem {

    FileNode root; // /leet/code/

    class FileNode{
        Map<String, FileNode> files;
        String name;
        int value;
        boolean isDir;

        public FileNode(String name, int value){
            this.files = new HashMap();
            this.name  = name;
            this.value = value;
            this.isDir = true; // For now we are just dealing with all folders
        }

    }

    public FileSystem() {
        root = new FileNode("root", 0); // We initialize the file system with an empty root
    }

    public boolean createPath(String path, int value) {
        FileNode current = root;

        String[] dirs = path.split("/");
        // Note that the path.split("/") for path "/leet/code" will return an array {"", "leet", "code"}
        for (int i=1; i<dirs.length; i++){
            String dir = dirs[i];
            if(current.files.get(dir) == null){
                current.files.put(dir, new FileNode(dir, value));
            }
            current = current.files.get(dir);
        }
        return true;
    }

    public int get(String path) {         // /leet/code
        String[] dirs = path.split("/");

        FileNode current = root;
        for (int i=1; i<dirs.length; i++){
            String dir = dirs[i];
            if(current.files.get(dir) == null){
                return -1;
            }
            current = current.files.get(dir);
        }
        return current.value;
    }


    public static void main(String[] args) {

        //    ["FileSystem","createPath","createPath","get","createPath","get"]
        //    [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
        //    ["FileSystem","createPath","get"]
        //    [[],["/a",1],["/a"]]

        // Your FileSystem object will be instantiated and called as such:
        FileSystem obj = new FileSystem();
        boolean param_1 = obj.createPath("/leet",1);
        boolean param_2 = obj.createPath("/leet/code",2);
        int param_3 = obj.get("/leet/code");
        boolean param_4 = obj.createPath("/c/d",1);
        int param_5 = obj.get("/c");


    }


}
