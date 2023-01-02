# klondike-java

A port of my project https://github.com/speevy/klondike-rust/ to java. 
Just for testing the features of the new java 17, and the lastest spring frameworks, including spring native and GraalVM.

In order to build the native image: `mvn -Pnative native:compile`.

In order to build a container with the native image: `mvn -Pnative spring-boot:build-image`.

For further reference: https://blogs.oracle.com/java/post/go-native-with-spring-boot-3-and-graalvm 
