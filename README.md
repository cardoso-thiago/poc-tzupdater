# Uso do Timezone Updater Tool com Docker

Essa POC tem como objetivo exemplificar o processo de uso da ferramenta [Timezone Updater Tool](https://www.oracle.com/java/technologies/javase/tzupdater-readme.html) com o Docker.

## Como executar o projeto

- Para o processo normal, sem alteração do timezone, basta verificar o arquivo `Dockerfile`. Para rodar, execute os seguintes comandos:
  - `docker build -t poc-tzupdater .`
  - `docker run -p 8080:8080 poc-tzupdater:latest`
  - E em outro terminal: `curl localhost:8080/datetime`

O retorno deve ser exatamente a data e a hora atual, no seguinte formato:

```shell
25/02/2023 19:29:08
```

- Para o processo com atualização da tabela de horário de verão, basta verificar o arquivo `DockerfileTZUpdater`. Para rodar, execute os seguintes comandos:
  - `docker build -t poc-tzupdater -f DockerfileTZUpdater .`
  - `docker run -p 8080:8080 poc-tzupdater:latest`
  - E em outro terminal: `curl localhost:8080/datetime`

O retorno deve ser exatamente a data e a hora atual, no seguinte formato:

```shell
25/02/2023 19:29:08
```

- Para o processo com atualização da tabela de horário de verão de forma manual, basta verificar o arquivo `DockerfileTZUpdaterManual`. Para rodar, execute os seguintes comandos:
  - `docker build -t poc-tzupdater -f DockerfileTZUpdaterManual .`
  - `docker run -p 8080:8080 poc-tzupdater:latest`
  - E em outro terminal: `curl localhost:8080/datetime`

O retorno deve ter a data atual, mas nesse caso, com o arquivo alterado manualmente, a hora deve estar incrementada:

```shell
25/02/2023 20:29:08
```

### Como alterar o arquivo manualmente

- Fazer o download da última versão do arquivo em `https://www.iana.org/time-zones/repository/tzdata-latest.tar.gz`
- Descompactar o arquivo. No Linux, para esse projeto, foi executado o comando: `mkdir -p iana-table/extracted && tar -xvf iana-table/tzdata-latest.tar.gz -C iana-table/extracted`
- Abrir o arquivo `iana-table/extracted/southamerica` e procurar as regras para `Brazil`.
- Para o teste, foi adicionada a seguinte linha para simular o início do horário de verão em Fevereiro: `Rule	Brazil	2023	only	-	Feb	Sun>=1	0:00	1:00	-`
- Após a alteração no arquivo, compactar o conteúdo novamente. No Linux, para esse projeto, foi executado o comando: `tar -czvf iana-table/tzdata-latest.tar.gz -C iana-table/extracted/ $(ls -A iana-table/extracted/)`
  - Podem existir outras formas de gerar o arquivo compactado novamente, o importante aqui é verificar que na raiz do arquivo zipado, os arquivos de configuração estejam diretamente na raiz. Isso pode ser validado com o comando `tar --list -zf iana-table/tzdata-latest.tar.gz`
- Após isso, basta seguir o processo do `DockerfileTZUpdaterManual` e verificar o resultado.