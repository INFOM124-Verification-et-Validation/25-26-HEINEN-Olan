# Specification-based Testing

## 1. Goal, inputs and outputs
- Goal: prochaine position de Clyde
- Input domain: map, joueur, position de Clyde
- Output domain: direction

## 2. Explore the program (if needed)

## 3. Identify input and output partitions

### Input partitions

#### Individual inputs
- Distance partitions:
    - D1: Clyde is within 8 grid spaces of Pac-man (distance < 8)
    - D2: Clyde is beyond 8 grid spaces of Pac-man (distance > 8)
    - D3: Clyde is at exactly 8 grid spaces of Pac-man (distance = 8)
    - 
- Obstacle direction partitions:
  - O1: Path of Clyde is free
  - O2: Path of Clyde is blocked
  - O3: Clyde has multiple valid moves
#### Combinations of input values
- Distance < 8 & path free
- Distance < 8 & path blocked
- Distance < 8 & multiple moves

- Distance > 8 & path free
- Distance > 8 & path blocked
- Distance > 8 & multiple moves

- Distance = 8 & path free
- Distance = 8 & path blocked
- Distance = 8 & multiple moves
### Output partitions
- Empty direction
- Direction towards Pac-man
- Direction away from Pac-man
## 4. Identify boundaries
- Not valid cases:
  - Multiple Clydes on the map
  - Multiple Pac-man on the map
  - Clyde is on Pac-man
  - Clyde does not have a square partition
  - Pac-man does not have a square partition

- Pac-man at the edge of the board partition
- Pac-man goes from one edge of the map to the other
## 5. Select test cases
- Distance < 8:
  - T1: Path free => Direction away from Pac-man
  - T2: Path blocked => Empty direction
  - T3: multiple moves => Direction away from Pac-man
- Distance > 8:
  - T4: Path free => Direction towards from Pac-man
  - T5: Path blocked => Empty direction
  - T6: multiple moves => Direction towards from Pac-man
- Distance = 8:
  - T7: Path free => Empty direction
  - T8: Path blocked => Empty direction
  - T9: multiple moves => Empty direction

- Boundaries:
  - T10: Multiple Clydes on the map
  - T11: Multiple Pac-man on the map
  - T12: Clyde is on Pac-man