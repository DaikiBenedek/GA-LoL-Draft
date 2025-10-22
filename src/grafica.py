import matplotlib.pyplot as plt
import pandas as pd
import glob

csv_list = glob.glob("output/*.csv")

csv_list = [csv.split("\\")[-1] for csv in csv_list]


for csv in csv_list:
    # Leer los datos
    data = pd.read_csv(csv, names=["Generacion", "Mejor", "Peor",      "Promedio"])

    # Graficar la evolución del fitness
    plt.figure(figsize=(10, 6))
    plt.plot(data["Generacion"], data["Mejor"], label="Mejor Fitness", marker="o")
    plt.plot(data["Generacion"], data["Promedio"], label="Promedio Fitness",    linestyle="--")
    plt.plot(data["Generacion"], data["Peor"], label="Peor Fitness", linestyle=":")
    plt.xlabel("Generación")
    plt.ylabel("Fitness")
    plt.title("Evolución del Fitness por Generación")
    plt.legend()
    plt.grid()
    plt.savefig(f"{csv}.png")
