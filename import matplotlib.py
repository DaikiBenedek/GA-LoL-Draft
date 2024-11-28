import matplotlib.pyplot as plt
import pandas as pd

# Leer los datos
data = pd.read_csv("resultados.csv", names=["Generacion", "Mejor", "Peor", "Promedio"])

# Graficar la evolución del fitness
plt.figure(figsize=(10, 6))
plt.plot(data["Generacion"], data["Mejor"], label="Mejor Fitness", marker="o")
plt.plot(data["Generacion"], data["Promedio"], label="Promedio Fitness", linestyle="--")
plt.plot(data["Generacion"], data["Peor"], label="Peor Fitness", linestyle=":")
plt.xlabel("Generación")
plt.ylabel("Fitness")
plt.title("Evolución del Fitness por Generación")
plt.legend()
plt.grid()
plt.show()