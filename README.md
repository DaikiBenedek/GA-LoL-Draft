# 🧬 Final Project – Evolutionary Computing
## Genetic Algorithm for Optimal Team Selection in League of Legends

This program is an implementation of a **Genetic Algorithm** applied to team selection in *League of Legends*.  
It allows the user to explore evolutionary optimization by configuring key parameters of the simulation.

### How it works

1. When you run the program, it asks you to provide:
   - **Number of individuals** per generation (population size)  
   - **Number of generations** to simulate  
   - **Number of iterations** per generation
   - **Banned champions**
   - **Picked opponent's champions**

2. During execution, the algorithm evaluates each individual (team configuration) and calculates its fitness value with multiple team compositions.

📈 Output

Example output:
```bash
output/
├── generacion_1.csv
├── generacion_2.csv
└── generacion_n.csv
```
1. The program prints in terminal for each generation:
   - **Generation** – the number of generation
   - **Best fitness** – the highest fitness value in the population    
   - **Worst fitness** – the lowest fitness value in the population  
   - **Average fitness** – the mean fitness of the population
   - **Final summary** - containing all the previous information, time, the worst team composition and the best team composition
3. The program generates a **CSV file** with three columns per generation:
   - **Best fitness** – the highest fitness value in the population  
   - **Worst fitness** – the lowest fitness value in the population  
   - **Average fitness** – the mean fitness of the population

4. Additionally, the program automatically generates a **graph** showing how fitness evolved over generations, allowing you to visualize:
   - The improvement of the best solutions  
   - The behavior of the worst and average solutions  
   - Overall convergence trends

### Purpose

This setup demonstrates the **dynamics of evolutionary computation**, providing insight into:
- How populations evolve over time  
- The effect of selection and mutation on solution quality  
- Visualization of algorithm performance per generation

---

## ⚙️ Requirements

Before running the project, make sure the following dependencies are installed:

```bash
pip install matplotlib pandas
```
If you don’t have pip installed:
```bash
sudo apt install python3-pip
pip3 install matplotlib pandas
```
▶️ Execution

Navigate to the main project folder (420003533), where the run.sh file is located.

Run the following command in your terminal:

Ejecuta el siguiente comando en terminal:
```bash
./run.sh
```

This will execute the genetic algorithm.
The program will generate:
The Optimal Team Selection against the opponent's team in terminal.
.csv files with results inside the output/ folder.


```bash
🧩 Project Structure
├── 420003533/
│   ├── bin/         # Compiled .class files
│   ├── src/         # Source code
│   ├── output/      # Generated CSVs and charts
│   ├── run.sh       # Execution script
│   └── README.md
```

🧠 Algorithm Overview

The project uses a genetic algorithm to optimize team selection by:

- Validating champion positions

- Considering “banned” or restricted champions

- Evaluating team fitness using a custom fitness function

Two modes of execution are available:

- Random mode – generates random team compositions.

- Manual mode – allows the user to manually choose champions by name.

🧾 Champion List

Below is the complete list of available champions per position:

🛡️ Position 0 – Top

Jax, Gnar, Rumble, Renekton, Ksante, Gragas, Olaf, Camille, Kennen, Sion, Urgot, Sett, Ornn, Darius, Jayce

⚔️ Position 1 – Jungle

Sejuani, Vi, Nocturne, Maokai, Xin Zhao, Wukong, Ivern, Nidalee, Zyra, Brand, Kindred, Amumu, Jarvan IV, Nunu, Lee Sin, Morgana, Lillia, Viego, Fiddlesticks, Elise, Skarner

💪 Position 2 – Mid

Ahri, Sylas, Orianna, Smolder, Aurora, Akali, Tristana, Syndra, Galio, Neeko, Lucian, LeeBlanc, Cassiopeia, Yasuo, Hwei, Yone, Swain, Ryze, Seraphine, Kassadin

🎯 Position 3 – ADC

Kai’Sa, Ezreal, Ashe, Kalista, Jhin, Xayah, Miss Fortune, Ziggs, Varus, Caitlyn, Jinx, Draven, Zeri, Twitch

💫 Position 4 – Support

Rell, Rakan, Leona, Renata Glasc, Alistar, Braum, Poppy, Nautilus, Pyke, Nami, Lux, Taric, Lulu, Tahm Kench, Senna, Bardo, Blitzcrank

