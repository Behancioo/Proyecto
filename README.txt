Sistema de Evaluación basado en la Taxonomía de Bloom

Autor: Benjamin Ignacio Anguita Covarrubias

Descripción general
-------------------
Este sistema permite administrar pruebas educativas clasificadas según la taxonomía de Bloom. Las preguntas pueden ser de selección múltiple o verdadero/falso, y se presentan de una en una con navegación, evaluación automática y revisión de respuestas.

Instrucciones para ejecutar
---------------------------
1. Abrir el proyecto en un IDE.
2. Tener Java 17 o superior instalado.
3. Compilar y ejecutar 'Main.java' desde el paquete 'src/frontend'.
4. Al iniciar, se debe cargar un archivo '.csv' con los ítems.

Formato del archivo de ítems
----------------------------
El archivo debe tener una pregunta por línea, con el siguiente formato:

TIPO;NIVEL;ENUNCIADO;OPCIONES;RESPUESTA_CORRECTA;TIEMPO_SEG

- TIPO: MULTIPLE o VF
- NIVEL: RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR
- ENUNCIADO: texto de la pregunta
- OPCIONES: solo para MULTIPLE, separadas por |
- RESPUESTA_CORRECTA: texto exacto (para MULTIPLE) o VERDADERO/FALSO (para VF)
- TIEMPO_SEG: tiempo estimado en segundos

Ejemplo:
MULTIPLE;APLICAR;¿Cuál es el resultado de 2+2?;2|3|4|5;4;30
VF;RECORDAR;Java es un lenguaje compilado.;;VERDADERO;20

- Las preguntas no se repiten entre ejecuciones distintas (se asume control externo).
- La navegación guarda automáticamente las respuestas.
- El usuario puede revisar todas sus respuestas luego de enviar la prueba.
- El resumen muestra porcentajes de acierto por tipo de ítem y por nivel de Bloom.

Estructura del código
---------------------
- backend: lógica, modelo de datos, carga y evaluación.
- frontend: interfaz gráfica Swing dividida en paneles (PanelCargaArchivo, PanelPrueba, PanelResumen, PanelRevision).
