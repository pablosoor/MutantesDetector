![image](https://github.com/user-attachments/assets/9401013a-7e0a-4524-86c4-943d32decbf7)

#  Detector de Mutantes 🧲

Este proyecto utiliza **Spring Boot** para detectar si un ADN pertenece a un mutante, evaluando secuencias de cuatro secuencias repetitivas (A, T, C, G) en direcciones verticales, horizontales y diagonales. Se implementan validaciones para la longitud y los caracteres del ADN, así como un sistema de excepciones para manejar errores. Se utilizan repositorios para interactuar con la base de datos, y un servicio de estadísticas proporciona datos sobre la proporción de mutantes a humanos, mejorando la gestión y análisis de los datos en el sistema.

---

## Pre-Requisitos 🛜

- Cuenta en Render
- Postman para pruebas
- Base de datos H2
---
## Requerimientos de Software 🧑‍💻

| Programas                | Versión |
|--------------------------|---------|
| Spring Boot              | 3.3.4   |
| Java                     | 17      |
| Maven                    | 3.8.6   |
| JUnit                    | 5.9.3   |
---
## Herramientas 🛠️

| Programas                | Descripción                               |
|--------------------------|-------------------------------------------|
| Postman                  | Cliente REST para probar la API          |
| H2                       | Base de datos en memoria para el desarrollo  |
| JUnit                    | Para pruebas unitarias                    |
---
## Clonar Repositorio, Acceder a Base de datos y Visualizar Mutantes

| ⚠️ Clonar Repositorio de GitHub ⚠️ |
|------------------------------------|
| Clonar el repositorio ➡️ `git clone https://github.com/pablosoor/ParcialMutantes.git` |
| Acceder al directorio ➡️ `cd ParcialMutantes` |
| Instalar dependencias (ejecutar Maven) ➡️ `mvn clean install` |
| Ejecutar la aplicación ➡️ `mvn spring-boot:run` |

---

| ⚠️ Acceder a Base de datos ⚠️ |
|-----------------------------------|
| Acceder a la consola H2 ➡️ Navegar a `http://localhost:9000/h2-console` en el navegador |

---

| ⚠️ Visualizar Mutantes ⚠️ |
|-------------------------------|
| Verificar la aplicación en Postman ⬇️ |
| - **URL para verificar ADN ➡️** `http://localhost:9000/api/v1/mutantes` |
| - **URL para estadísticas ➡️** `http://localhost:9000/api/v1/mutantes/statistics` |
| - **URL para visualizar mutante en particular ➡️** `http://localhost:9000/api/v1/mutantes/{{id}}` |


---

## Operaciones de la API 💻

### **1. Obtener Mutantes** ✅
**Método**: `GET`  
**URL**: `http://localhost:9000/api/v1/mutantes`  

**Respuesta**:
```json
[
    {
        "id": 1,
        "adn": [
            "ATCG",
            "ATCG",
            "ATCG",
            "ATCG"
        ],
        "esMutante": true
    },
    {
        "id": 3,
        "adn": [
            "AAAA",
            "AAAA",
            "AAAA",
            "AAAA"
        ],
        "esMutante": false
    }
]
```

### **2. Estadísticas de ADN** 📈
**Método**: `POST`  
**URL**: `http://localhost:9000/api/v1/mutantes/statistics`  

**Cuerpo de la solicitud**:
```json
{
    "count_human_dna": 2.0, 
    "count_mutant_dna": 3.0, 
    "ratio": 1.5
}
```

### **3. Eliminar un Mutante**❎
**Método**: DELETE
**URL**: http://localhost:9000/api/v1/mutantes/{{id}}
**Descripción**: Elimina de la base de datos el ID solicitado.

---

## 🧠  Lógica del Proyecto de Detección de Mutantes

Este proyecto utiliza **Spring Boot** para detectar si un ADN pertenece a un mutante, evaluando secuencias de cuatro letras idénticas en las siguientes direcciones:

- **Verticales**
- **Horizontales**
- **Diagonales**
---
### ✅ Validaciones y Manejo de Errores

- Se implementan validaciones para:
  - Longitud del ADN
  - Caracteres del ADN
- Se utiliza un sistema de excepciones para manejar errores específicos.
---
### 📊 Interacción con la Base de Datos

- **Repositorios** para interactuar con la base de datos.
- **Servicio de estadísticas** que proporciona datos sobre la proporción de mutantes a humanos, mejorando la gestión y análisis de los datos en el sistema.

---
## 📏  Lógica de Validación

La clase `AdnValidator` incluye métodos para validar el ADN:

- **`validateDnaLength`**: Verifica que la longitud total del ADN no supere los 1000 caracteres y que haya al menos una cadena.
- **`validateDnaCharacters`**: Asegura que el ADN contenga únicamente caracteres válidos (A, T, C, G).
  
---
### 🚫 Excepciones Personalizadas

Se definen excepciones personalizadas para manejar errores específicos:

- **`InvalidDnaException`**
- **`MutanteNotFoundException`**

---
## 🧪 Lógica de Pruebas

La clase `MutanteServiceTest` implementa pruebas con **JUnit** para validar la funcionalidad del servicio `MutanteService`:

- Verifica que las operaciones de guardar, buscar y eliminar mutantes funcionen correctamente.
- Asegura la detección de mutantes en:
  - Filas
  - Columnas
  - Diagonales
- Garantiza que se identifiquen correctamente tanto los ADN mutantes como los no mutantes, lo que asegura la fiabilidad del sistema.
  
---
🌟 **¡ proyecto de detección de mutantes en render!** 🌟  
Acceso a la aplicación en el siguiente enlace: (https://mutantesdetector.onrender.com)

**Documentacion(PDF)de nivel 3 del proyecto**➡️➡️https://drive.google.com/drive/folders/1kV4fibeY9PKwUscqcSs_ZtL3yALY39HB?usp=sharing
