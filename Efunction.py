##EFunction
import random
from decimal import *
getcontext().prec = 30
range_ = 3000

def something():
    grid = []
    totals = [0]*16
    bigSqNum = 273**2
    for i in range(bigSqNum): grid.append(0)
    ##print(grid)
    for j in range(bigSqNum):
        randNum = random.randint(0, bigSqNum-1)
        grid[randNum] = grid[randNum] + 1
    for square in grid:
        totals[square+1] = totals[square+1]+1
    return(Decimal(bigSqNum/totals[1]))

total_e = Decimal(0)
for i in range(range_):
    total_e = total_e + something()
print(Decimal(total_e/range_))
