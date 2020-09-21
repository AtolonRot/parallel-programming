package edu.coursera.concurrent;

import edu.rice.pcdp.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.rice.pcdp.PCDP.finish;

/**
 * An actor-based implementation of the Sieve of Eratosthenes.
 * <p>
 * TODO Fill in the empty SieveActorActor actor class below and use it from
 * countPrimes to determin the number of primes <= limit.
 */
public final class SieveActor extends Sieve {
    /**
     * {@inheritDoc}
     * <p>
     * TODO Use the SieveActorActor class to calculate the number of primes <=
     * limit in parallel. You might consider how you can model the Sieve of
     * Eratosthenes as a pipeline of actors, each corresponding to a single
     * prime number.
     */
    @Override
    public int countPrimes(final int limit) {
        SieveActorActor sieveActor = new SieveActorActor(2);

        finish(() -> {
           for(int i = 3; i <= limit; i+=2) {
               sieveActor.send(i);
           }
        });

        int nPrime = 0;
        SieveActorActor loopActor = sieveActor;
        while(loopActor != null) {
            nPrime += loopActor.numLocalPrimes;
            loopActor = loopActor.nextActor;
        }
        return nPrime;
    }

    /**
     * An actor class that helps implement the Sieve of Eratosthenes in
     * parallel.
     */
    public static final class SieveActorActor extends Actor {

        private static final int MAX_LOCAL_PRIMES = 1_000;
        private final List<Integer> localPrimes;
        private int numLocalPrimes;
        private SieveActorActor nextActor;

        SieveActorActor(int localPrime) {
            this.localPrimes = new ArrayList<>(MAX_LOCAL_PRIMES);
            this.localPrimes.add(localPrime);
            this.numLocalPrimes = 1;
            this.nextActor = null;
        }

        /**
         * Process a single message sent to this actor.
         * <p>
         * TODO complete this method.
         *
         * @param msg Received message
         */
        @Override
        public void process(final Object msg) {
            final int candidate = (Integer) msg;
            if (candidate <= 0) {
                if(nextActor != null) {
                    nextActor.send(msg);
                }
            } else {
                final boolean locallyPrime = isLocallyPrime(candidate);
                if (locallyPrime) {
                    if (numLocalPrimes < MAX_LOCAL_PRIMES) {
                        localPrimes.add(candidate);
                        numLocalPrimes += 1;
                    } else if (nextActor == null) {
                        nextActor = new SieveActorActor(candidate);
                    } else {
                        nextActor.send(msg);
                    }
                }
            }
        }

        private boolean isLocallyPrime(int candidate) {
            return localPrimes.stream().parallel().noneMatch(p -> candidate % p == 0);
        }
    }
}
