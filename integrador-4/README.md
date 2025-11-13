## GRUPO 14
### Integrantes

- Santiago Gonzalez - sagonzalez@alumnos.exa.unicen.edu.ar
- Melina Theill Cabral - mtheill@alumnos.exa.unicen.edu.ar
- Tomas Gomez - tgomez@alumnos.exa.unicen.edu.ar
- Facundo Castro - facucastrosl@gmail.com

### Integrador 4

#### Link al workspace de postman donde se realizaron los test.
[Postman](https://www.postman.com/tgom/arquitecturas/overview)

### Detalles a tener en cuenta
Por decisiones de implementacion, las facturas se generan a partir de 
los viajes y los valores se calculan con el precio que existe en tafifa.
Por ende, para que funcione bien toda la carga de datos a partir de los distintos csv,
se recomienda iniciarlos en el siguiente orden:
- Tarifa
- Factura
- Viaje

El resto de los microservicios no siguen un orden especifico pero se recomienda tener activos todos al mismo tiempo
para no tener problemas en las consultas entre microservicios.


En el microservicio gateway, dentro del paquete config en el archivo SecurityConfig se encuentra el filtro de roles para
cada microservicio. Implementamos la aplicacion con 2 roles, ADMIN y USER. La forma que elegimos para filtrar la entrada
a los servicios fue habilitar la entrada de cualquier persona tenga o no rol (porque se considera que nadie tiene acceso
a la aplicacion si no es ADMIN o USER) a los endpoints de los usuarios, y que el resto de los endpoints requiera permisos
de administrador.

Para testear los endpoints se deja comentada una linea 50 y activa la linea 49 de este archivo SecurityConfig. Comentando
la 49 y descomentando la 50, se logra libre acceso a toda la aplicacion mediante el gateway, para eliminar la necesidad
de tener un token y que el testing sea mucho mas sencillo.

De todas formas, sabiendo que no es lo ideal, se pueden cargar de forma predeterminada los 2 tipos de usuarios, un admin
cuyo username y password es "admin", y un user cuyo username y password es "user". Conectandose a /api/authenticate mediante
un metodo POST se puede obtener el token necesario para la validacion. En caso de querer usar estos datos, se necesita descomentar
las lineas 20 a 23 del archivo GatewayApplication.


