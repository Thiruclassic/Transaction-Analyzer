# Transaction-Analyzer
Project to analyse the transaction in the CSV file

## What's included?

1. Gradle Plugins
- java plugin
- spotless plugin

2. Libraries
- opencsv
- junit

### Building

```
$ ./gradlew clean spotlessApply build
```

### Testing

```
$ ./gradlew clean test
```
## Running the Application 

Application can be executed by passing input parameters via Command Line args or System Properties. Please find the instruction below:

### Running the Application with Command Line arguments

Jar file can be found in the folder build/libs folder once the project is built.

```
$  java -jar transaction-analyzer-1.0-SNAPSHOT.jar "<absolute-file-path>" "<merchant-name>" "<from-Date>" "<to-Date>"
```

### Running the Application with System Property

Jar file can be found in the folder build/libs folder once the project is built.

```
$  java -jar -DcsvFileName="<absolute-file-path>"  -DmerchantName="<merchant-name>" -DfromDate="<from-Date>" -DtoDate="<to-Date>" transaction-analyzer-1.0-SNAPSHOT.jar
```


