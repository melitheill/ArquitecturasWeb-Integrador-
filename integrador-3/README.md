## GRUPO 14
### Integrantes

- Santiago Gonzalez - sagonzalez@alumnos.exa.unicen.edu.ar
- Melina Theill Cabral - mtheill@alumnos.exa.unicen.edu.ar
- Tomas Gomez - tgomez@alumnos.exa.unicen.edu.ar
- Facundo Castro - facucastrosl@gmail.com

### Integrador 3

#### Link al workspace de postman donde se realizaron los test.
[Postman](https://www.postman.com/tgom/arquitecturas/overview)
### Endpoints

```
a) dar de alta un estudiante 
POST /estudiantes
{
  "dni": 87654321,
  "nombre": "María",
  "apellido": "González",
  "edad": 22,
  "genero": "Femenino",
  "nroLU": 56789,
  "ciudadResidencia": "Rosario"
}
```
```
b) matricular un estudiante en una carrera 
POST /matriculas
{
    "id": 150,
    "id_estudiante":10719241,
    "id_carrera":1,
    "anio_inicio":2020,
    "anio_fin":0,
    "antiguedad": 5
}
```
```
c) recuperar todos los estudiantesy especificar algún criterio de ordenamiento simple
GET /estudiantes/list
respuesta:
[
    {
        "dni": 44782708,
        "nombre": "Ham",
        "apellido": "Airy",
        "edad": 27,
        "genero": "Male",
        "ciudadResidencia": "Dashtobod",
        "nroLU": 90958
    },
    {
        "dni": 89370812,
        "nombre": "Stormy",
        "apellido": "Audrey",
        "edad": 26,
        "genero": "Female",
        "ciudadResidencia": "N",
        "nroLU": 93298
    },
    .
    .
    . 
]
```
``` 
d) recuperar un estudiante, en base a su número de libreta universitaria.
GET /estudiantes/lu/{lu}
lu = 90958
respuesta:
{
    "dni": 44782708,
    "nombre": "Ham",
    "apellido": "Airy",
    "edad": 27,
    "genero": "Male",
    "ciudadResidencia": "Dashtobod",
    "nroLU": 90958
}
```
``` 
e) recuperar todos los estudiantes, en base a su género.
GET /estudiantes/genero/{genero}
genero = Female
respuesta:
[
    {
        "dni": 10719241,
        "nombre": "Hanni",
        "apellido": "Harrisson",
        "edad": 49,
        "genero": "Female",
        "ciudadResidencia": "Samal",
        "nroLU": 72976
    },
    {
        "dni": 12950356,
        "nombre": "Domini",
        "apellido": "Larderot",
        "edad": 70,
        "genero": "Female",
        "ciudadResidencia": "Mengyin",
        "nroLU": 84506
    },
    .
    .
    .
]
```
```
f) recuperar las carreras con estudiantes inscriptos y ordenar por cantidad de inscriptos. 
GET /carreras/incriptos
respuesta:
[
    {
        "nombre": "TUDAI",
        "cantidad": 18
    },
    {
        "nombre": "Educacion Fisica",
        "cantidad": 12
    },
    .
    .
    .
]
```
``` 
g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. 
GET /estudiantes/carrerayciudad/{carrera}/{ciudad}
carrera = 15, ciudad = Jiaoyuan
respuesta:
[
    {
        "dni": 71779527,
        "nombre": "Isidro",
        "apellido": "Blackmuir",
        "edad": 66,
        "genero": "Male",
        "ciudadResidencia": "Jiaoyuan",
        "nroLU": 34978
    }
]
```
``` 
h) generar un reporte de las carreras, que para cada carrera incluya información de los inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar los años de manera cronológica. 
GET carreras/informe
respuesta:
[
    {
        "nombreCarrera": "Abogacia",
        "infoPorAnio": {
            "2017": {
                "inscriptos": 3,
                "egresados": 0
            },
            "2018": {
                "inscriptos": 1,
                "egresados": 0
            },
            "2021": {
                "inscriptos": 1,
                "egresados": 0
            },
            "2022": {
                "inscriptos": 0,
                "egresados": 1
            },
            "2023": {
                "inscriptos": 0,
                "egresados": 3
            },
            "2024": {
                "inscriptos": 0,
                "egresados": 1
            }
        }
    },
    .
    .
    .
]
```