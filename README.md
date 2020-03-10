[![Build Status](https://travis-ci.com/mP1/walkingkooka-text-printer.svg?branch=master)](https://travis-ci.com/mP1/walkingkooka-text-printer.svg?branch=master)
[![Coverage Status](https://coveralls.io/repos/github/mP1/walkingkooka-text-printer/badge.svg?branch=master)](https://coveralls.io/github/mP1/walkingkooka-text-printer?branch=master)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/mP1/walkingkooka-text-printer.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-text-printer/context:java)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/mP1/walkingkooka-text-printer.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/mP1/walkingkooka-text-printer/alerts/)



A library that establishes simple but elegant interfaces for printing text with devices to create elegant readable
structured text rather than difficult to read sea of text of found in tools and programs that print output.

# Abstractions
Some simple abstractions and examples are included below.



## IndentingPrinter
This library provides several building blocks including `IndentingPrinter` which supports fine grained multi levels of 
indentation to all printed lines with a scope. 

The tree/indented output taken from executing the [j2cl-maven-plugin](https://github.com/mP1/j2cl-maven-plugin) below is
 simply a `IndentedPrinter` printing lines and strategically increasing and decreasing indentation.
  
The warning text under Messages is simply `java.io.Writer` passed to the Google Closure compiler that delegates writes
to the same `IndentedPrinter` which takes care of adding indentation whenever line breaks appear in the text written.

```text
Parameters
Command line arguments
    --compilation_level
      ADVANCED_OPTIMIZATIONS
    --define
      jre.checkedMode=DISABLED
      jre.checks.checkLevel=MINIMAL
      jsinterop.checks=DISABLED
    --dependency_mode
      PRUNE
    --entry_point
      example.helloworld.app
    --externs
    --js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.google.jsinterop-jsinterop-annotations-jar-1.0.2-e7070f9ca213d9d8ad6eb4062fde8e0b7ea365a0/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.google.jsinterop-jsinterop-annotations-jar-1.0.2-e7070f9ca213d9d8ad6eb4062fde8e0b7ea365a0/5-transpiled-java-to-javascript/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.google.jsinterop-jsinterop-annotations-jar-2.0.0-0c6ca8f0b559f74c7b90ff8bc93eff1d37b4e487/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.google.jsinterop-jsinterop-annotations-jar-2.0.0-0c6ca8f0b559f74c7b90ff8bc93eff1d37b4e487/5-transpiled-java-to-javascript/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.j2cl-bootstrap-zip-jszip-0.4-20191027.175500-7-c588733906cc0656ff61eaa2cb57d8904319a99d/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.j2cl-gwt-internal-annotations-jar-0.4-20191027.175458-7-6a01ea30a4632696d452e8feb7a27b14a2b117b4/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.j2cl-gwt-internal-annotations-jar-0.4-20191027.175458-7-6a01ea30a4632696d452e8feb7a27b14a2b117b4/5-transpiled-java-to-javascript/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.j2cl-jre-zip-jszip-0.4-20191027.175459-7-aa98a3e329e7297811a7b6a80c373cdb1a4ed9bc/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.jsinterop-base-jar-1.0.0-20191022.032823-31-5209040226920488171142c602f5e04ab058c9c6/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/javax.annotation-jsr250-api-jar-1.0-b249b88c55e18a4a9706b890441cc294ce393236/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/javax.annotation-jsr250-api-jar-1.0-b249b88c55e18a4a9706b890441cc294ce393236/5-transpiled-java-to-javascript/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/walkingkooka-example-hello-world-single-war-1.0-fee838384c60cb62c9b6cdc18f28c223096eb409/1-unpack/output/**/*.js
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/walkingkooka-example-hello-world-single-war-1.0-fee838384c60cb62c9b6cdc18f28c223096eb409/5-transpiled-java-to-javascript/output/**/*.js
    --js_output_file
      /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/walkingkooka-example-hello-world-single-war-1.0-fee838384c60cb62c9b6cdc18f28c223096eb409/6-closure-compiler-output/output/Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/example-hello-world-single/example-hello-world-single.js
    --language_out
      ECMASCRIPT5
  *** END ***
Closure compiler
Exit code
  0
Messages
  
  WARNING - unknown @define variable jsinterop.checks
  
  /Users/miroslav/repos-github/j2cl-maven-plugin/target/it-tests/example-hello-world-single/target/j2cl-maven-plugin-cache/com.vertispan.j2cl-jre-zip-jszip-0.4-20191027.175459-7-aa98a3e329e7297811a7b6a80c373cdb1a4ed9bc/1-unpack/output/java/lang/AbstractStringBuilder.impl.java.js:20: WARNING - property m_append__char on interface module$exports$java$lang$Appendable$impl is not implemented by type module$exports$java$lang$AbstractStringBuilder$impl
  class AbstractStringBuilder extends j_l_Object {
  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
```


## PrintedLineHandler

The `PrintedLineHandler` is mapper that is called for each and every completed line of text printed. The mapper can change
the text or line ending and then print.



# walkingkooka-text-pretty
The [walkingkooka-text-printer](https://github.com/mP1/walkingkooka-text-pretty) sub project adds support for much more
advanced concepts such as formatting individual column attributes including alignment and more, leveraging the basic functionality
within this project. 



# Dependencies

- junit!
- walkingkooka

## Getting the source

You can either download the source using the "ZIP" button at the top
of the github page, or you can make a clone using git:

```
git clone git://github.com/mP1/walkingkooka-text-printer.git
```
