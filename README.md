# Impretec Admin App

Aplicativo administrativo multiplataforma desenvolvido com Kotlin Multiplatform, Compose Multiplatform e Ktor.

## Pré-requisitos

- JDK 17 ou superior
- Android Studio (para desenvolvimento Android) ou IntelliJ IDEA
- Gradle (o projeto inclui o wrapper Gradle)

## Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/amandazzoc/impretec-admin-app.git
cd impretec-admin-app
```

### 2. Build do projeto

#### Windows:
```bash
.\gradlew.bat build
```

#### Linux/Mac:
```bash
./gradlew build
```

### 3. Executar o projeto

#### Executar o servidor:
```bash
# Windows
.\gradlew.bat :server:run

# Linux/Mac
./gradlew :server:run
```

#### Executar o aplicativo Compose (Desktop):
```bash
# Windows
.\gradlew.bat :composeApp:run

# Linux/Mac
./gradlew :composeApp:run
```

#### Executar no Android:
1. Abra o projeto no Android Studio
2. Conecte um dispositivo Android ou inicie um emulador
3. Execute o módulo `composeApp`

## Estrutura do Projeto

- **composeApp**: Módulo da interface do usuário com Compose Multiplatform
- **server**: Módulo do backend com Ktor
- **shared**: Módulo compartilhado com código comum entre plataformas

## Desenvolvimento

Para desenvolvimento ativo com hot reload, utilize o plugin Compose Hot Reload configurado no projeto.
