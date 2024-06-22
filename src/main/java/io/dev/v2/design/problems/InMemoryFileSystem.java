package io.dev.v2.design.problems;

import java.util.*;

/**
 * 588 Design In-Memory File System
 *  Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *     FileSystem()
 *          Initializes the object of the system.
 *
 *     List<String> ls(String path)
 *         If path is a file path, returns a list that only contains this file's name.
 *         If path is a directory path, returns the list of file and directory names in this directory.
 *         The answer should in lexicographic order.
 *
 *     void mkdir(String path)
 *          Makes a new directory according to the given path.
 *          The given directory path does not exist.
 *          If the middle directories in the path do not exist, you should create them as well.
 *
 *     void addContentToFile(String filePath, String content)
 *         If filePath does not exist, creates that file containing given content.
 *         If filePath already exists, appends the given content to original content.
 *
 *     String readContentFromFile(String filePath)
 *         Returns the content in the file at filePath.
 *
 * Example:
 *  Input
 *      ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
 *      [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
 *  Output
 *      [null, [], null, null, ["a"], "hello"]
 *
 * Explanation
 *  FileSystem fileSystem = new FileSystem();
 *  fileSystem.ls("/");                         // return []
 *  fileSystem.mkdir("/a/b/c");
 *  fileSystem.addContentToFile("/a/b/c/d", "hello");
 *  fileSystem.ls("/");                         // return ["a"]
 *  fileSystem.readContentFromFile("/a/b/c/d"); // return "hello"
 *
 * */
public class InMemoryFileSystem {

    class File {
        HashMap <String, File> files = new HashMap<>();
        boolean isFile = false;
        String content;
    }

    File root;

    public InMemoryFileSystem() {
        root = new File();
    }

    public List < String > ls(String path) {
        File current = root;
        List<String> files = new ArrayList<>();

        if (!path.equals("/")) {
            String[] dirs = path.split("/");
            for (int i = 1; i < dirs.length; i++) {
                String dir = dirs[i];

                current = current.files.get(dir);
            }
            if (current.isFile) {
                files.add(dirs[dirs.length - 1]);
                return files;
            }
        }

        List<String> filesToReturn = new ArrayList<>(current.files.keySet());
        Collections.sort(filesToReturn);

        return filesToReturn;
    }

    public void mkdir(String path) {
        File current = root;
        String[] dirs = path.split("/");
        for (int i = 1; i < dirs.length; i++) {
            String dir = dirs[i];
            if (!current.files.containsKey(dir))
                current.files.put(dir, new File());
            current = current.files.get(dir);
        }
    }

    public void addContentToFile(String filePath, String content) {
        File current = root;
        String[] dirs = filePath.split("/");

        for (int i = 1; i < dirs.length - 1; i++) {
            String dir = dirs[i];
            current = current.files.get(dir);
        }

        String dir = dirs[dirs.length - 1];
        if (!current.files.containsKey(dir)) {
            current.files.put(dir, new File());
        }

        current = current.files.get(dir);
        current.isFile = true;
        current.content = current.content + content; // append to existing content
    }

    public String readContentFromFile(String filePath) {
        File current = root;
        String[] dirs = filePath.split("/");
        for (int i = 1; i < dirs.length - 1; i++) {
            current = current.files.get(dirs[i]);
        }
        String dir = dirs[dirs.length - 1];
        return current.files.get(dir).content;
    }

}
