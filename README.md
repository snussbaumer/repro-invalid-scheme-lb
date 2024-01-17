# repro-invalid-scheme-lb

repro project to show "invalid scheme [lb]" problem

run it with `mvn test` and you'll get `DemoApplicationTests.test:37 Status expected:<200 OK> but was:<500 INTERNAL_SERVER_ERROR>`
due to and error `Invalid scheme [lb]`

now change spring-boot-parent version to 3.1.5 in pom.xml and run `mvn test` again and you'll see that the test runs fine.

the problem exists in with versions 3.1.6 and 3.1.7