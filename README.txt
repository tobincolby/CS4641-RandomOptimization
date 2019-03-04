To run the analysis, download ABAGAIL and download my project from the following github link:

https://github.com/tobincolby/CS4641-RandomOptimization

Then, place the java files in the "code"
directory of this project into "src/opt/test". Additionally, place the files in
the "data" directory into the "src/opt/test".

Optimization Problems Running

1. Run "ant"                                            (to compile everything)
2. Run "java -cp ABAGAIL.jar opt.test.FlipFlopTest"     (run flip flop tests)
3. Run "java -cp ABAGAIL.jar opt.test.KnapsackTest"     (run knapsack tests)
4. Run "java -cp ABAGAIL.jar opt.test.MaxKColoringTest" (run coloring tests)

Neural Net Running
1. Run "ant"						(compile everything)
2. Run "java -cp ABAGAIL.jar opt.test.Proj2Test rhc"        (run random hill climbing)
3. Run "java -cp ABAGAIL.jar opt.test.Proj2Test sa"        (run simmulated annealing)
4. Run "java -cp ABAGAIL.jar opt.test.Proj2Test ga"        (run genetic algorithms)



For steps 1-5, all relevant data is printed to stdout. This data is redirected to
csv files for visualization using Microsoft Excel.

This will produce all data that was used for the analysis of the algorithms.
