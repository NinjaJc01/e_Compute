# e_Compute
This project is a collection of ways to calculate, compute or guesstimate Euler's number, e.
Most of these use the Brother's summation, however one Python example notably uses a Poisson distribution, with lambda = 1 to calculate 1/e and then flips this fraction.
Programs are a mixture of my work and the work of collaborators.
No promises that these work, although much of the Python has been checked. The Java (particularly when threaded) is much much faster than the python, for example on my i5-2500k, the Java takes less than 20% of the time for much larger limits (Python unthreaded to Java threaded with work distribution, limit 30000 and precision 90001)

