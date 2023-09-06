# Descripcion
Microservicio de usuarios el cual mapea los usuarios y sus cargos, podemos obtener todos o algun usuario por id.

# Requerimientos
- Docker
- Si no tiene docker es necesario java 17.
- Eureka desplegado.
- Base de datos Posgresql desplegada.
- 
## Controladores
El microservicio tiene incluido swagger para la documentacion de los enpoints, accede a el en http://localhost:8080/swagger-ui.html

## Despliegue
Podemos correr eureka facilmente de dos maneras:

- Con gradle (sin instalacion):
1. Use el siguiente comando en la raiz del proyecto, no necesita compilar el codigo fuente ni tener gradle instalado:
```shell
./gradlew bootRun
```

- Con docker:

1. Creamos y corremos el contenedor, importante exponer el puerto 8080:
```shell
docker run -p 8080:8080 --name <container_name> jhojanlopez/inventory_system_users
```

2. Si eureka y la base de datos no estan en la misma red virutal agregar las varibles de entorno de acuerdo a su red:
```shell
docker run -p 8080:8080 -e HOST_DB=<host_db> -e HOST_EUREKA=<host_eureka> --name <container_name> jhojanlopez/inventory_system_users
```


