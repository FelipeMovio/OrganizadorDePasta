# 📦 Organizador Automático de Downloads

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Status](https://img.shields.io/badge/status-ativo-success)
![License](https://img.shields.io/badge/license-MIT-green)
![Platform](https://img.shields.io/badge/platform-Windows-lightgrey)

---

## 🚀 Sobre o Projeto

O **Organizador Automático de Downloads** é uma aplicação desenvolvida em Java que organiza automaticamente os arquivos da pasta **Downloads** do usuário.

Ele funciona de forma contínua, detectando novos arquivos em tempo real e movendo-os para pastas específicas com base no tipo.

---

## ⚙️ Funcionalidades

✅ Organização automática ao iniciar
✅ Monitoramento em tempo real da pasta Downloads
✅ Classificação de arquivos por tipo
✅ Criação automática de pastas
✅ Tratamento de arquivos com nomes duplicados
✅ Evita mover arquivos ainda em download

---

## 🧠 Como Funciona

A aplicação possui dois modos principais:

### 🔹 1. Organização Inicial

Ao iniciar, o sistema:

* Varre todos os arquivos da pasta Downloads
* Classifica cada arquivo
* Move para a pasta correspondente

---

### 🔹 2. Monitoramento em Tempo Real

Utilizando a API `WatchService` do Java:

* O sistema fica escutando a pasta Downloads
* Detecta novos arquivos automaticamente
* Aguarda o download finalizar
* Move o arquivo para o destino correto

---

## 🔄 Fluxo de Execução

```text
Início do programa
   ↓
Organização inicial dos arquivos
   ↓
Delay de segurança
   ↓
Ativação do monitoramento
   ↓
Novo arquivo detectado
   ↓
Verificação de download completo
   ↓
Movimentação automática
```

---

## 📂 Estrutura de Pastas

Após a execução:

```bash
Downloads/
 ├── Imagens/
 ├── Documentos/
 ├── Videos/
 ├── Compactados/
 └── Outros/
```

---

## 🧩 Estrutura do Projeto

```bash
src/
 ├── Main.java
 ├── service/
 │    ├── OrganizadorService.java
 │    └── WatcherService.java
 ├── util/
 │    └── TipoArquivoUtil.java
```

---

## 🛠️ Tecnologias Utilizadas

* Java 17+
* API `WatchService` (Java NIO)
* Programação Orientada a Objetos (POO)

---

## ▶️ Como Executar

### 🔹 1. Compilar

```bash
javac -d out src/Main.java src/service/*.java src/util/*.java
```

---

### 🔹 2. Gerar o JAR

```bash
jar cfe organizador.jar Main -C out .
```

---

### 🔹 3. Executar

```bash
java -jar organizador.jar
```

---

## ⚡ Executar automaticamente com o Windows

1. Crie um arquivo `.bat`:

```bat
@echo off
cd /d "C:\caminho\do\projeto"
start "" javaw -jar organizador.jar
```

2. Pressione `Win + R`
3. Digite:

```text
shell:startup
```

4. Coloque o `.bat` nessa pasta

---

## 🧪 Logs (opcional)

Para salvar logs:

```bat
java -jar organizador.jar >> log.txt 2>&1
```

---

## ⚠️ Observações

* O projeto foi testado em ambiente Windows
* Pode haver variações de comportamento dependendo do navegador (downloads)
* Recomenda-se não utilizar diretamente em pastas sincronizadas (ex: OneDrive)

---

## 🎯 Objetivo

Este projeto demonstra:

* Automação de tarefas reais
* Monitoramento de sistema de arquivos
* Boas práticas em Java
* Estruturação de código em camadas

---


## 👨‍💻 Autor

Felipe Movio

---

## 📄 Licença

Este projeto está sob a licença MIT.
