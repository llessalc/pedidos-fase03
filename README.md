# pedidos-fase03

## Ajustes necessários para rodar:

- Criar/Atualizar as secrets: 
  - `MYSQL_HOST`, faz referencia a `${MYSQL_HOST}`: deve ser uma url para acessar o banco de dados. Na secrets do Github Actions pasta definir como `localhost`
  - `MYSQL_PASSWORD`, faz referencia a variável de ambiente `${MYSQL_PASSWORD}`: pode ser qualquer senha
  - `URI_REPOSITORY`: esta faz referência a URI do repositório privado do Amazon ECR
  - `AWS_ACCESS_KEY_ID`: Access Key do par de chaves da AWS
  - `AWS_SECRET_ACCESS_KEY`: Secret Access Key do par de chaves da AWS
- As seguintes configurações no POM.xml são necessárias:
  - Adicionar a dependência **javafaker**
  - Adicionar o gerenciador de distribuições, com URL do repositório
  - Adicionar o plugin do Jacoco

```xml
<dependencies>
...
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
</dependencies>
<distributionManagement>
    <repository>
        <id>github</id>
        <name>GitHub Packages</name>
        <url>https://maven.pkg.github.com/{{URL Repositorio}}</url>
    </repository>
</distributionManagement>

<build>
<plugins>
...
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.7</version>
    <executions>
        <execution>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
</plugins>
</build>
```