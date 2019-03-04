package opt.test;

import java.util.Arrays;

import dist.DiscreteDependencyTree;
import dist.DiscreteUniformDistribution;
import dist.Distribution;

import opt.DiscreteChangeOneNeighbor;
import opt.EvaluationFunction;
import opt.GenericHillClimbingProblem;
import opt.HillClimbingProblem;
import opt.NeighborFunction;
import opt.RandomizedHillClimbing;
import opt.SimulatedAnnealing;
import opt.example.*;
import opt.ga.CrossoverFunction;
import opt.ga.DiscreteChangeOneMutation;
import opt.ga.SingleCrossOver;
import opt.ga.GenericGeneticAlgorithmProblem;
import opt.ga.GeneticAlgorithmProblem;
import opt.ga.MutationFunction;
import opt.ga.StandardGeneticAlgorithm;
import opt.prob.GenericProbabilisticOptimizationProblem;
import opt.prob.MIMIC;
import opt.prob.ProbabilisticOptimizationProblem;
import shared.FixedIterationTrainer;

import java.text.DecimalFormat;


/**
 * A test using the flip flop evaluation function
 * @author Andrew Guillory gtg008g@mail.gatech.edu
 * @version 1.0
 */
public class FlipFlopTest {
    /** The n value */

    public static void main(String[] args) {

        int N = 20000;
        for (int bitLength = 1; bitLength < 1001; bitLength += 50) {
        int[] ranges = new int[bitLength];
        DecimalFormat dif = new DecimalFormat("0.000");
        Arrays.fill(ranges, 2);
        EvaluationFunction ef = new FlipFlopEvaluationFunction();
        Distribution odd = new DiscreteUniformDistribution(ranges);
        NeighborFunction nf = new DiscreteChangeOneNeighbor(ranges);
        MutationFunction mf = new DiscreteChangeOneMutation(ranges);
        CrossoverFunction cf = new SingleCrossOver();
        Distribution df = new DiscreteDependencyTree(.1, ranges);
        HillClimbingProblem hcp = new GenericHillClimbingProblem(ef, odd, nf);
        GeneticAlgorithmProblem gap = new GenericGeneticAlgorithmProblem(ef, odd, mf, cf);
        ProbabilisticOptimizationProblem pop = new GenericProbabilisticOptimizationProblem(ef, odd, df);

        double starttime = System.nanoTime();
        double endtime;

        RandomizedHillClimbing rhc = new RandomizedHillClimbing(hcp);
        FixedIterationTrainer fit = new FixedIterationTrainer(rhc, N);
        fit.train();
        System.out.printf("%d,%f,",bitLength,ef.value(rhc.getOptimal()));

        endtime = System.nanoTime();
        double time_elapsed = endtime - starttime;

    //    Convert nanoseconds to seconds
        time_elapsed /= Math.pow(10,9);
        //System.out.printf("Time Elapsed: %s s %n", dif.format(time_elapsed));
         starttime = System.nanoTime();

        SimulatedAnnealing sa = new SimulatedAnnealing(100, .95, hcp);
        fit = new FixedIterationTrainer(sa, N);
        fit.train();
        System.out.printf("%f,",ef.value(sa.getOptimal()));

        endtime = System.nanoTime();
         time_elapsed = endtime - starttime;

    //    Convert nanoseconds to seconds
        time_elapsed /= Math.pow(10,9);
        //System.out.printf("Time Elapsed: %s s %n", dif.format(time_elapsed));
         starttime = System.nanoTime();

        StandardGeneticAlgorithm ga = new StandardGeneticAlgorithm(200, 100, 20, gap);
        fit = new FixedIterationTrainer(ga, N);
        fit.train();
        System.out.printf("%f,",ef.value(ga.getOptimal()));

        endtime = System.nanoTime();
         time_elapsed = endtime - starttime;

    //    Convert nanoseconds to seconds
        time_elapsed /= Math.pow(10,9);
        //System.out.printf("Time Elapsed: %s s %n", dif.format(time_elapsed));
         starttime = System.nanoTime();

        MIMIC mimic = new MIMIC(200, 5, pop);
        fit = new FixedIterationTrainer(mimic, N);
        fit.train();
        System.out.printf("%f\n",ef.value(mimic.getOptimal()));

        endtime = System.nanoTime();
         time_elapsed = endtime - starttime;

    //    Convert nanoseconds to seconds
        time_elapsed /= Math.pow(10,9);
        //System.out.printf("Time Elapsed: %s s %n", dif.format(time_elapsed));
    }

    }
}
