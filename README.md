# Documentacao

# Tecnologias utilizadas
- Java 8
- Spring boot 2
- Spring Data Rest
- Spring Cache
- Spring Security
- Spring Security com JWT(Java Web Token)
- Docker

- Pre-requisito:
  - Java 8
  - Docker
  - Maven 3

# Como executar os testes:
$ mvn clean test

# Subindo a aplicação apontando para o banco Local (H2).
$ mvn clean package
$ cd target/
$ java -jar *.jar

# Subindo a aplicação em um container Docker.
$ mvn clean package
$ bash scripts/build.sh
$ bash scripts/run.sh
 