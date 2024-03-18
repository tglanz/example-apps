//package tglanz.examples;
//
//import jdk.incubator.vector.DoubleVector;
//import jdk.incubator.vector.VectorOperators;
//import jdk.incubator.vector.VectorSpecies;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.concurrent.*;
//
//public class CalculatePiVectorized implements Example {
//
//    class PiWorker implements Callable<Double> {
//
//        private final double x0;
//        private final double x1;
//        private final double dx;
//
//        public PiWorker(double x0, double x1, double dx) {
//            this.x0 = x0;
//            this.x1 = x1;
//            this.dx = dx;
//        }
//
//        @Override
//        public Double call() throws Exception {
//            VectorSpecies<Double> species = DoubleVector.SPECIES_PREFERRED;
//            double[] buffer = new double[species.length()];
//
//            DoubleVector ones = DoubleVector.zero(species).add(1);
//
//            double sum = 0;
//
//            double x = x0 + (dx / 2);
//            while (x < x1) {
//                for (int idx = 0; idx < buffer.length && x < x1; ++idx) {
//                    buffer[idx] = x;
//                    x += dx;
//                }
//
//                sum += ones
//                    .sub(DoubleVector.fromArray(species, buffer, 0).pow(2))
//                    .sqrt()
//                    .mul(dx)
//                    .reduceLanes(VectorOperators.ADD);
//            }
//
//            return sum;
//        }
//    }
//
//    @Override
//    public String name() {
//        return "calculate-pi-vectorized";
//    }
//
//    /**
//     * A unit circle is defined by $x^2 + y^2 = 1$.
//     * Or, $y = \sqrt{1 - x^2}$, is the y value of the unit circle in the upper half plane.
//     * The area below the curve $\sqrt{1 - x^2}$ for x in [-1, 1] is half of the area of the unit circle.
//     * Meaning, $\int_{-1}^{1}{\sqrt{1 - x^2} dx} = \frac{\pi}{2}.
//     *
//     * To summarize, pi is equal to 2 times the integral value of f in the section [-1, 1].
//     */
//    @Override
//    public void run() {
//
//        var startInstant = Instant.now();
//
//        long bins = Integer.MAX_VALUE;
//        int nThreads = 12;
//
//        // constants
//        final double x0 = -1;
//        final double x1 = 1;
//
//        // computed
//        double dx = Math.abs(x0 - x1) / bins;
//        double partitionSize = Math.abs(x0 - x1) / (double)nThreads;
//
//        ExecutorService executor = Executors.newFixedThreadPool(nThreads);
//        Collection<Future<Double>> futures = new ArrayList<>(nThreads);
//
//        for (int tid = 0; tid < nThreads; ++tid) {
//            var tx0 = x0 + (tid * partitionSize);
//            var tWork = new PiWorker(tx0, tx0 + partitionSize, dx);
//            futures.add(executor.submit(tWork));
//        }
//
//        Double pi = 0.0;
//        for (var future : futures) {
//            try {
//                pi += future.get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        executor.shutdown();
//
//        pi *= 2;
//        var duration = Duration.between(startInstant, Instant.now());
//
//        System.out.printf("Pi: %f\n", pi);
//        System.out.printf("Took: %sms\n", duration.toMillis());
//        System.out.printf("Thread Count: %d\n", nThreads);
//        System.out.printf("Bins: %d\n", bins);
//    }
//}
