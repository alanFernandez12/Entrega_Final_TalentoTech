# Entrega Final - API de artículos

API REST desarrollada con Spring Boot para administrar artículos y asociarlos a categorías.

## Requisitos

- Java 21
- MySQL

## Configuración de la base de datos

La aplicación lee la configuración desde `src/main/resources/application.properties`. Por defecto, espera una instancia local de MySQL y la base de datos `sistema_stock`.

1. Iniciá MySQL.
2. Creá la base de datos si todavía no existe:

   ```sql
   CREATE DATABASE sistema_stock;
   ```

3. Configurá las credenciales de MySQL en `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/sistema_stock
   spring.datasource.username=root
   spring.datasource.password=TU_CLAVE
   ```

   Reemplazá `TU_CLAVE` por la contraseña local de MySQL. No subas credenciales reales al repositorio.

Hibernate está configurado con `spring.jpa.hibernate.ddl-auto=update`, por lo que actualiza el esquema al iniciar la aplicación.

## Ejecutar la aplicación

Desde la carpeta del proyecto, en PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

En Linux o macOS:

```bash
./mvnw spring-boot:run
```

Cuando Spring Boot indique que la aplicación inició correctamente, estará disponible en `http://localhost:8080`.

## Swagger UI / OpenAPI

Abrí Swagger UI en:

<http://localhost:8080/swagger-ui/index.html>

La especificación OpenAPI en formato JSON está disponible en:

<http://localhost:8080/v3/api-docs>

En Swagger, desplegá los endpoints de `/api/articulos`, seleccioná **Try it out**, completá el cuerpo cuando corresponda y ejecutá la operación.

## Endpoints de artículos

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/api/articulos` | Lista los artículos |
| `GET` | `/api/articulos/{id}` | Obtiene un artículo por código |
| `POST` | `/api/articulos` | Crea un artículo |
| `PUT` | `/api/articulos/{id}` | Actualiza un artículo existente |
| `DELETE` | `/api/articulos/{id}` | Elimina un artículo |

El cuerpo de `POST` y `PUT` usa `ArticuloDTO`. Ejemplo:

```json
{
  "nombre": "Teclado",
  "precio": 25000,
  "categoriaId": 1
}
```

`categoriaId` debe identificar una categoría existente en la base de datos. Para crear un artículo sin categoría, se puede enviar `null` u omitir ese campo si la relación lo permite.

Respuestas habituales: `201 Created` al crear, `200 OK` al consultar o actualizar, `204 No Content` al eliminar y `404 Not Found` cuando el código solicitado no existe.
