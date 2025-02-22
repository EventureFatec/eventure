# Eventure

## Executando
```bash
mvn dependency:resolve
mvn clean compile
mvn -q exec:java
```

## Compilando o .jar e executando-o
```bash
mvn dependency:resolve
mvn clean package
java -jar ./target/Eventure-{versão}.jar
```
> [!NOTE]
> `{versão}` deve ser de acordo com o que é definido no arquivo `pom.xml`, 
> como por exemplo `0.0.1`, ficando `Eventure-0.0.1.jar`.
