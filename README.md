[![Tests](https://github.com/rodrigoArceUnrn/user-service/actions/workflows/maven.yml/badge.svg?event=push)](https://github.com/rodrigoArceUnrn/user-service/actions/workflows/maven.yml) [![codecov](https://codecov.io/gh/rodrigoArceUnrn/user-service/graph/badge.svg?token=RHN82TL6MX)](https://codecov.io/gh/rodrigoArceUnrn/user-service)

# User Service

User Service es una aplicación Java diseñada para gestionar usuarios de manera eficiente y segura. Este proyecto proporciona una solución robusta para la gestión de usuarios, con características como autenticación y autorización mediante JWT, persistencia de datos en una base de datos MySQL y comunicación asincrónica a través de RabbitMQ.

## Descripción

El servicio de usuario es una parte fundamental de muchas aplicaciones, ya que gestiona la información de los usuarios, como la autenticación, los perfiles y los permisos. User Service se centra en proporcionar una base sólida para la gestión de usuarios, con un enfoque en la seguridad, el rendimiento y la escalabilidad.

## Características principales

- Autenticación y autorización seguras utilizando JWT.
- Persistencia de datos en una base de datos MySQL.
- Comunicación asincrónica a través de RabbitMQ para procesos no bloqueantes.
- Automatización de la construcción, verificación de estilo y cobertura de código con GitHub Actions.

## Requisitos previos

Para ejecutar este proyecto localmente, necesitarás tener instalado lo siguiente en tu sistema:

- Java Development Kit (JDK) 17
- Maven
- MySQL
- RabbitMQ

## Configuración del Entorno de Desarrollo

1. **Clonar el Repositorio:**
   ```bash
   git clone https://github.com/rodrigoArceUnrn/user-service.git
   cd user-service
   ```
2. **Compilar el Proyecto:**
 ```bash
   mvn clean install
   ```
3. **Configurar la Base de Datos MySQL:**
   Asegúrate de que tienes MySQL instalado y configurado en tu sistema. Luego, configura las siguientes propiedades en el archivo **application.properties** de tu proyecto:
 ```bash
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/store_users?useUnicode=true&characterEncoding=utf8&createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=
    spring.jpa.show-sql=false
   ```
4. **Configurar la Seguridad y JWT:**
   Configura las siguientes propiedades en el archivo **application.properties** para la autenticación y autorización:
```bash
    spring.security.user.name=rodrigoa
    spring.security.user.password=Rodri123
    
    jwt.secret_key=secretKey
    jwt.token-prefix=bearer
    jwt.header=Authorization
   ```

5. **Configurar RabbitMQ:**
   Si estás utilizando RabbitMQ para la mensajería, configura las siguientes propiedades en el archivo **application.properties**:
```bash
    spring.rabbitmq.host=localhost
    spring.rabbitmq.port=5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    rabbit.queue.name=changeClientData
   ```

## Ejecutar los Flujos de Trabajo de GitHub Actions

Este proyecto utiliza GitHub Actions para automatizar la compilación, la verificación de estilo y la cobertura de código. Los flujos de trabajo se activan automáticamente en cada empuje a la rama principal (main) y en cada solicitud de extracción (pull request) hacia la rama principal.

Los flujos de trabajo disponibles son:

- Java Build: Compila el proyecto Java.
- Checkstyle: Ejecuta la verificación de estilo de código.
- Code Coverage: Carga los informes de cobertura de código en Codecov.