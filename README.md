# java8-commons [![Build Status](https://travis-ci.org/rinfield/java8-commons.svg?branch=master)](https://travis-ci.org/rinfield/java8-commons)

The standard Java8 libraries fail to provide enough methods for manipulation of
 its core classes. java8-commons provides extra classes and methods.
 This project is still under development and API may changes.

## Goal
 * Take full advantages of Java8 features
 * Simple & Easy to use
 * Typesafe
 * Light-weight
 * Interoperable with Java8 standard classes


## Stream Extensions
You can use thin wrappers for `Stream` with nice extended methods inspired by
 Scala collections. Extended methods provide nice handling of infinite streams.
 Also, you can use all the methods defined at `Stream`. 

Stream extension has two interfaces.
 1. `InfiniteStream`
  * a stream that has infinite amount of elements.
  * cannot call Terminal Operations. (forbidden, this leads to infinite loop.)
  * can call Terminal Short Circuit Operations. 
    (allowed but may leads to infinite loop. so use with care.)
 1. `FiniteStream`
  * a stream that has finite amount of elements.
  * can call all of operations.


### Sample Codes
### Reading File Text
```java
package com.github.rinfield.java8.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.rinfield.java8.stream.Item;
import com.github.rinfield.java8.stream.Streamz;

public class ReadFile {

    public static void main(final String[] args) throws IOException {
        try (final BufferedReader br = Files.newBufferedReader(Paths
            .get("LICENSE.txt"))) {
            Streamz
                // no need to handle exception in lambda expression.
                .generateSuppress(br::readLine)
                // takeWhile converts InfiniteStream to FiniteStream
                .takeWhile(Item::isNotNull).forEach(Item::println);
        }
    }
}
```
You cannot write like this using standard `Stream` class, because standard
 `Stream` does not have `takeWhile` method and there is no simple way to ends
 the stream.

Above sample code is pretty much equivalent to ancient codes below:
```java
    // <snip!>
    public static void main(final String[] args) throws IOException {
        try (final BufferedReader br = Files.newBufferedReader(Paths
            .get("LICENSE.txt"))) {

            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
```


### How to use
#### Use Maven3 to add dependency
You need to configure `pom.xml` to add java8-commons as dependency.

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>sample-app</artifactId>
  <version>0.0.1-SNAPSHOT</version>
  <dependencies>
    <!-- Add java8-commons dependency -->
    <dependency>
      <groupId>com.github.rinfield</groupId>
      <artifactId>java8-commons</artifactId>
      <version>0.0.1-SNAPSHOT</version>
    </dependency>
  </dependencies>
  <repositories>
    <!-- Add jfrog oss snapshot repository -->
    <repository>
      <id>oss-jfrog-artifactory-snapshots</id>
      <name>oss-jfrog-artifactory-snapshots</name>
      <url>http://oss.jfrog.org/artifactory/oss-snapshot-local</url>
    </repository>
  </repositories>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>1.8</java.version>
  </properties>
  <build>
    <plugins>
      <!-- compile as Java8 source -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.2</version>
        <configuration>
          <source>${java.version}</source>
          <target>${java.version}</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```


#### Instantiation (`Streamz` Factory Class)
```java
import com.github.rinfield.java8.stream.Streamz;

// Single element
FiniteStream<Integer> singleIntStream = Streamz.of(1);

// Multiple elements
FiniteStream<Integer> multiIntStream = Streamz.of(1, 2, 3);

// infinite stream
InfiniteStream<Integer> infiniteStream = Streamz.iterate(0, i -> ++i);
```

#### Notable Extended Methods
Given: 
```java
FiniteStream<Object> stream = Streamz.of(1, "2");
```

###### `list()`
toooooo long to type `stream.collect(Collectors.toList());` isn't it?
```java
List<Object> res = stream.list(); //=> List(1, "2")
```

###### `mkString()`
```java
String res1 = stream.mkString(); //=> "12"
String res2 = stream.mkString(", "); //=> "1, 2"
String res3 = stream.mkString("[", ", ", "]"); //=> "[1, 2]"
```

###### `zipWithIndex()`
```java
FiniteStream<Tuple<Object, Integer>> res = stream.zipWithIndex(); //=> FiniteStream(Tuple(1, 0), Tuple("2", 1))
```

###### `grouped()`
```java
FiniteStream<FiniteStream<Object>> res = stream.grouped(); //=> FiniteStream(FiniteStream(1, "2"))
```


### Stream Concatenation
 Concat Patterns                    | Result Type   
 ---------------------------------- | --------------
 InfiniteStream + InfiniteStream => | InfiniteStream
 InfiniteStream + FiniteStream   => | InfiniteStream
 InfiniteStream + Single Element => | InfiniteStream
 FiniteStream   + InfiniteStream => | InfiniteStream
 FiniteStream   + FiniteStream   => | FiniteStream
 FiniteStream   + Single Element => | FiniteStream


## Functional Interface Extensions
Functional Interfaces which of the single abstract method has `throws` declaration.
So, you don't need to write messy `try`-`catch` block in lambda expression.
 * ThrowingSupplier
 * ThrowingFunction
 * TODO...


## Utility class Extensions
 * Try
 * Tuple
 
 
## Contribution
 * Licensed under Apache2 License
 * Contribution Welcome! 