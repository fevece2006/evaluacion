### NISUM: Evaluación Java ###

**La aplicación**
El desarrollo de la aplicación trata de registro de usuarios.

Se implementa y utiliza las siguientes tecnologías:
Base de datos H2
Java 17
Gradle 8.5

**Acceso a la base de datos**
se ingresa al navegador por la url:
http://localhost:8081/h2-ui

**Documentación - Swaggwer**
http://localhost:8081/swagger-ui/index.html

**Pruebas en postman**
Registro 01: Se registra un usuario con un solo objeto teléfono.
Metodo: POST
URL: http://localhost:8081/swagger-ui/index.html

BODY(raw): 

JSON1:
{
    "name": "Fernando Velasquez",
    "email": "fvelasquez@dominio.cl",
    "password": "Miclave1",
    "phones": [
        {
            "number": "998496195",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}

Registro 02: Se registra un usuario con dos objetos teléfono.
{
    "name": "Lionel Messi",
    "email": "Lmessi@dominio.cl",
    "password": "Miclave2",
    "phones": [
        {
            "number": "123456781",
            "citycode": "1",
            "contrycode": "57"
        },
                {
            "number": "123456782",
            "citycode": "2",
            "contrycode": "58"
        }
    ]
}


