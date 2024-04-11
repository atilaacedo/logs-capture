# Application Logging System com Log4j

Este projeto implementa um sistema de captura e gerenciamento de logs de aplicação robusto usando Java e a biblioteca Log4j. Destina-se a fornecer uma visão clara das operações da aplicação, auxiliar na depuração e fornecer dados auditáveis para transações e eventos do sistema.

## Começando

Estas instruções irão obter uma cópia do projeto operacional em sua máquina local para fins de desenvolvimento e teste.

### Pré-requisitos

Antes de começar, instale o seguinte software:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) versão 8 ou superior
- [Maven](https://maven.apache.org/) para gerenciamento de dependências e construção do projeto
- Uma IDE de sua escolha (recomendada: [IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/), ou [Visual Studio Code](https://code.visualstudio.com/))

### Instalação

Siga estes passos para configurar o projeto localmente:

1. Clone o repositório para a sua máquina local:

    ```bash
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2. Instale as dependências do projeto usando Maven:

    ```bash
    mvn install
    ```

3. Crie um arquivo `log4j2.xml` no diretório `src/main/resources` para configurar o Log4j.

4. Execute a aplicação usando Maven:

    ```bash
    mvn exec:java -Dexec.mainClass="com.seuprojeto.Main"
    ```

## Caso de Uso
<br/>
![CASODEUSO](/images/image.png)
<br/>


## Uso

Inclua chamadas de log no seu código conforme necessário. Por exemplo:

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuaClasse {
    private static final Logger logger = LogManager.getLogger(SuaClasse.class);
    
    public void seuMetodo() {
        logger.info("Informação relevante para o log.");
        try {
            // Sua lógica de negócios aqui
        } catch (Exception e) {
            logger.error("Erro capturado no método seuMetodo", e);
```
## Configuração

Configure o Log4j com o arquivo `log4j2.xml`. Aqui está um exemplo básico:

```xml
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>

        }
    }
}
```
## Construído Com

- **Java** - A linguagem de programação usada
- **Log4j2** - Biblioteca de logging utilizada
- **Maven** - Gerenciamento de Dependências

## Reconhecimentos

- Agradeça a qualquer um cujo código foi usado
- Inspiração
- etc


