import random
from decimal import *

getcontext().prec = 20
grid = []
totals = [0]*16
bigSqNum = 128**2
for i in range(bigSqNum): grid.append(0)
print(grid)
for j in range(bigSqNum):
    randNum = random.randint(0, bigSqNum-1)
    grid[randNum] = grid[randNum] + 1
for square in grid:
    totals[square+1] = totals[square+1]+1
e = Decimal(bigSqNum/totals[1])
print(e)
    
