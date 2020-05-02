
# Statement Coverage Tool

CS 6367.001 - Software Testing, Validation and Verification - S20

Group 5 - Project Phase-1

Sampath Kumar Grandhi (SXG180072)

Aditya Mathur (AXM180195)

Shashidhar Krovvidi  (SXK180203)

Satya Sarvani Baru (SXB180153)

## Requirements

Maven 3.6.0

Java JDK 9+

## Usage

Clone this repostory and in the `StatementCoverageTool` directory run

```
$ mvn clean 
$ mvn install
```
Copy the generated JavaAgent-1.0-SNAPSHOT.jar file from target to a project under test.
```
$ cp target/JavaAgent-1.0-SNAPSHOT.jar [destination]
```

Add these lines lines to the `pom.xml` file of the target project

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-surefire-plugin</artifactId>
  <configuration>
    <argLine>-javaagent:./JavaAgent-1.0-SNAPSHOT.jar
    </argLine>
   <properties>
    <property>
      <name>listener</name>
      <value>StatementCoverageTool.JUnitListener</value>
    </property>
   </properties>
  </configuration>
</plugin>
```

In the same folder of the project under test run:
```
$ mvn test
```
The Statement Coverage log is generated and stored in `StatementCoverageLog` directory in the `StatementCoverage.txt` file. 
## References
[1] (https://github.com/lamichhaneashish/AutomatedCodeCoverageTool)
