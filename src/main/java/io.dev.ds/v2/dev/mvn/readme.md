Dependency Management Concepts
To understand what a BOM is and what we can use it for, we first need to learn basic concepts.

What Is Maven POM?
Maven POM is an XML file that contains information and configurations (about the project) that are used by Maven to import dependencies and to build the project.

What Is Maven BOM?
BOM stands for Bill Of Materials. A BOM is a special kind of POM that is used to control the versions of a project’s dependencies and provide a central place to define and update those versions.
BOM provides the flexibility to add a dependency to our module without worrying about the version that we should depend on.

Transitive Dependencies
Maven can discover the libraries that are needed by our own dependencies in our pom.xml and includes them automatically. There's no limit to the number of dependency levels that the libraries are gathered from.
The conflict here comes when 2 dependencies refer to different versions of a specific artifact. Which one will be included by Maven?
The answer here is the “nearest definition”. This means that the version used will be the closest one to our project in the tree of dependencies. This is called dependency mediation.

Let's see the following example to clarify the dependency mediation:
    A -> B -> C -> D 1.4  and  A -> E -> D 1.0
Here project A depends on B and E. 
B and E have their own dependencies which encounter different versions of the D artifact. 
Artifact D 1.0 will be used in the build of A project because the path through E is shorter.


Dependency Management
Simply put, Dependency Management is a mechanism to centralize the dependency information.
When we have a set of projects that inherit a common parent, we can put all dependency information in a shared POM file called BOM.
Notice that the BOM is a normal POM file with a dependencyManagement section where we can include all an artifact's information and versions.
```
<project ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>some-project</groupId>
    <artifactId>Some-BOM</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>Some-BOM</name>
    <description>parent pom</description>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>test</groupId>
                <artifactId>a</artifactId>
                <version>1.2</version>
            </dependency>
            <dependency>
                <groupId>test</groupId>
                <artifactId>b</artifactId>
                <version>1.0</version>
                <scope>compile</scope>
            </dependency>
            <dependency>
                <groupId>test</groupId>
                <artifactId>c</artifactId>
                <version>1.0</version>
                <scope>compile</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```

Using the BOM File
There are 2 ways to use the previous BOM file in our project 

1. We can inherit from the parent:
   the project Test inherits the Some-BOM.
```
<project ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>some-group</groupId>
    <artifactId>Test</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>Test</name>
    <parent>
        <groupId>baeldung</groupId>
        <artifactId>Some-BOM</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </parent>
</project>
```

2. We can also import the BOM.
In larger projects, the approach of inheritance is not efficient because the project can inherit only a single parent. 
Importing is the alternative as we can import as many BOMs as we need.
```
<project ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>baeldung</groupId>
    <artifactId>Test</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>Test</name>
    
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>baeldung</groupId>
                <artifactId>Baeldung-BOM</artifactId>
                <version>0.0.1-SNAPSHOT</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
</project>
```
Spring BOM
We can import the spring-framework-bom in our dependencyManagement section to ensure that all Spring dependencies are at the same version:
```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-framework-bom</artifactId>
            <version>4.3.8.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
We can skip the version attribute when we use the Spring artifacts
```
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
    </dependency>
<dependencies>
```


```
<project xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <artifactId>spring-bom</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <name>spring-bom</name>
    <url>http://maven.apache.org</url>

    <parent>
        <groupId>com.baeldung</groupId>
        <artifactId>parent-modules</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </parent>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework</groupId>
                <artifactId>spring-framework-bom</artifactId>
                <version>${spring-framework-bom.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
        </dependency>
    </dependencies>

    <properties>
        <spring-framework-bom.version>4.3.8.RELEASE</spring-framework-bom.version>
    </properties>

</project>
```