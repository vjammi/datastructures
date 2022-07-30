package ds.patterns.design;

import java.util.HashMap;

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
        boolean isfile = false;
        String content;
    }

    File root;

    public InMemoryFileSystem() {
        root = new File();
    }





}
