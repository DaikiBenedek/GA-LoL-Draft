#!/bin/bash

# Compilar los archivos
javac -d bin src/*.java

# Ejecutar el programa
java -cp bin SimpleDemoGA

# Crear graficas
python3 src/grafica.py
