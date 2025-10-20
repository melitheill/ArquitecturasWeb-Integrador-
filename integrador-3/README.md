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
POST /estudiante
Body:
{
  "id": 99999998,
  "nombre": "Javier",
  "apellido": "Mascherano",
  "edad": 49,
  "genero": "Male",
  "ciudad": "Catamarca",
  "lu": 729767
}

Response:
{
  "id": 99999998,
  "nombre": "Javier",
  "apellido": "Mascherano",
  "edad": 49,
  "genero": "Male",
  "ciudad": "Catamarca",
  "lu": 729767
}

```
```
b) matricular un estudiante en una carrera 
POST /matricula/matricular
Body:
{
  "estudiante": {
    "id": 71779527
  },
  "carrera": {
    "id": 14
  }
}

Response:
{
  "id": {
    "estudiante": {
      "id": 71779527,
      "nombre": null,
      "apellido": null,
      "edad": 0,
      "genero": null,
      "ciudad": null,
      "lu": 0
    },
    "carrera": {
      "id": 14,
      "carrera": null,
      "duracion": 0
    }
  },
  "inscripcion": 2025,
  "graduacion": 0,
  "antiguedad": 0
}
```
```
c) recuperar todos los estudiantes y especificar algún criterio de ordenamiento simple
GET /estudiante/orden/{orden}

los criterios pueden ser: "id","nombre", "apellido", "edad", "genero", "ciudad", "LU".
en caso de no aclarar ningun criterio se ordenan por apellido.

Response:
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
GET /estudiante/lu/{lu}
lu = 90958

Response:
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
GET /estudiante/genero/{genero}
genero = Female

Response:
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
GET /carrera/incriptos
Response:
[
  {
    "cantidad": 17,
    "nombre": "TUDAI"
  },
  {
    "cantidad": 12,
    "nombre": "Educacion Fisica"
  },
  {
    "cantidad": 10,
    "nombre": "Ingenieria de Sistemas"
  },
  {
    "cantidad": 10,
    "nombre": "Psicologia"
  }
  .
  .
  .
]
```
``` 
g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. 
GET /estudiante/carrerayciudad/{carrera}/{ciudad}
carrera = Arte, ciudad = Jiaoyuan

Response:
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
GET carrera/reporte

Response:
[
  {
    "carrera": "Abogacia",
    "infoAnual": {
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