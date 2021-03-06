/**
 * A component of a library for
 * <a href="http://www.geog.leeds.ac.uk/people/a.turner/projects/MoSeS">MoSeS</a>.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA.
 */
package uk.ac.leeds.ccg.andyt.projects.moses.process;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;
import uk.ac.leeds.ccg.andyt.census.cas.Census_CAS001DataRecord;
import uk.ac.leeds.ccg.andyt.census.cas.Census_CAS002DataRecord;
import uk.ac.leeds.ccg.andyt.census.core.Census_CASDataRecord;
import uk.ac.leeds.ccg.andyt.census.cas.ks.Census_CASKS008DataRecord;
import uk.ac.leeds.ccg.andyt.census.cas.ks.Census_CASKS020DataRecord;
import uk.ac.leeds.ccg.andyt.census.cas.ks.Census_CASKS09bDataRecord;
import uk.ac.leeds.ccg.andyt.census.cas.ks.Census_CASKS09cDataRecord;
import uk.ac.leeds.ccg.andyt.census.sar.Census_ISARDataHandler;
import uk.ac.leeds.ccg.andyt.census.sar.Census_ISARDataHandler.AgeSexType;
import uk.ac.leeds.ccg.andyt.census.sar.Census_ISARDataRecord;
import uk.ac.leeds.ccg.andyt.data.converter.Data_AgeConverter;
import uk.ac.leeds.ccg.andyt.projects.moses.util.Moses_Collections;

public class GeneticAlgorithm_ISARHP_ISARCEP extends GeneticAlgorithm {

    IndividualCensus_ISARHP_ISARCEP _IndividualCensus_ISARHP_ISARCEP;

    /** Creates a new instance of GeneticAlgorithm
     * @param aCASDataRecord */
    public GeneticAlgorithm_ISARHP_ISARCEP(Census_CASDataRecord aCASDataRecord) {
        this._CASDataRecord = aCASDataRecord;
    }

    /** Creates a new instance of GeneticAlgorithm
     * @param _IndividualCensus_ISARHP_ISARCEP
     * @param _CASDataRecord */
    public GeneticAlgorithm_ISARHP_ISARCEP(
            IndividualCensus_ISARHP_ISARCEP _IndividualCensus_ISARHP_ISARCEP,
            Census_CASDataRecord _CASDataRecord) {
        this._IndividualCensus = _IndividualCensus_ISARHP_ISARCEP;
        this._IndividualCensus_ISARHP_ISARCEP = _IndividualCensus_ISARHP_ISARCEP;
        this._CASDataRecord = _CASDataRecord;
        this._CASKS002DataRecord = _CASDataRecord.getCASKS002DataRecord();
        this._RandomSeed = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._RandomSeed;
        this._Random = new Random(
                _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._RandomSeed);
        this._ConvergenceThreshold = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._ConvergenceThreshold;
        this._InitialPopulationSize = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._InitialPopulationSize;
        this._MaxNumberOfMutationsPerChild = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._MaxNumberOfMutationsPerChild;
        this._MaxNumberOfMutationsPerParent = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._MaxNumberOfMutationsPerParent;
        this._MaxNumberOfSolutions = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._MaxNumberOfSolutions;
        this._NumberOfOptimisationIterations = _IndividualCensus_ISARHP_ISARCEP._GeneticAlgorithm._NumberOfOptimisationIterations;
    }

    /**
     * @return Object[] result:
     * result[0] tConstraintsAndPopulation_ISARHP_ISARCEP:
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * population[0] = tHPHRPFemale_Age_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_Age_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNon-HRP_Vector;
     * population[5] = tCEPFemale_Age_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_Age_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     * result[1] BigDecimal fitness
     */
    public Object[] getOptimisedResult_ObjectArray() {
        TreeMap tInitialResult_TreeMap = getInitialResult_TreeMap(_InitialPopulationSize);
        return getOptimisedResult_ObjectArray(tInitialResult_TreeMap);
    }

    /**
     * @return Object[] result:
     * result[0] tConstraintsAndPopulation_ISARHP_ISARCEP:
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * population[0] = tHPHRPFemale_Age_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_Age_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNon-HRP_Vector;
     * population[5] = tCEPFemale_Age_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_Age_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     * result[1] BigDecimal fitness
     * @param existingSolutions_TreeMap
     * A TreeMap of solutions in the same format as what is returned.
     */
    public Object[] getOptimisedResult_ObjectArray(
            TreeMap existingSolutions_TreeMap) {
        int n = 0; // n = 1, 0
        BigDecimal aFitness;
        BigDecimal bFitness;
        int convergenceCounter = 0;
        boolean aSwitch = true;
        boolean convergence0 = false;
        boolean convergence1 = false;
        int aMaxNumberOfMutationsPerChild = _MaxNumberOfMutationsPerChild;
        BigDecimal preOptimisingFitness = (BigDecimal) existingSolutions_TreeMap.firstKey();
        String preOptimisingBestFitString = preOptimisingFitness.toString();
        TreeMap aSolutions_TreeMap = (TreeMap) existingSolutions_TreeMap.clone();
        for (int optimisationIteration = 0; optimisationIteration <= _NumberOfOptimisationIterations; optimisationIteration++) {
            aFitness = (BigDecimal) aSolutions_TreeMap.firstKey();
            if (aFitness.compareTo(BigDecimal.ZERO) == 1) {
                if (convergenceCounter < _ConvergenceThreshold) {
                    if (_NumberOfOptimisationIterations > 0) {
                        if ((optimisationIteration * 10) % _NumberOfOptimisationIterations == 0) {
                            this._IndividualCensus.log(
                                    "Iteration " + optimisationIteration + " out of " + _NumberOfOptimisationIterations + " ");
                            n = 1;
                        } else {
                            n = 0;
                        }
                    }
                    aSolutions_TreeMap = getOptimisedResult_ObjectArray(
                            n,
                            aSolutions_TreeMap,
                            aMaxNumberOfMutationsPerChild);
                    bFitness = (BigDecimal) aSolutions_TreeMap.firstKey();
                    if (aFitness.compareTo(bFitness) == 0) {
                        convergenceCounter++;
                    } else {
                        if (aFitness.compareTo(bFitness) == 1) {
                            convergence0 = false;
                            convergence1 = false;
                            convergenceCounter = 0;
                        }
                    }
                } else {
                    if (convergence0 && convergence1) {
                        this._IndividualCensus.log(
                                "Convergence at iteration " + optimisationIteration + " out of " + _NumberOfOptimisationIterations);
                        break;
                    } else {
                        convergenceCounter = 0;
                        if (aSwitch) {
                            aMaxNumberOfMutationsPerChild /= 2;
                            if (aMaxNumberOfMutationsPerChild > 0) {
                                this._IndividualCensus.log(
                                        "Iteration " + optimisationIteration + " out of " + _NumberOfOptimisationIterations + " _MaxNumberOfMutationsPerChild reduced to " + aMaxNumberOfMutationsPerChild + " fitness " + aFitness);
                            } else {
                                aMaxNumberOfMutationsPerChild = 1;
                                aSwitch = false;
                                convergence0 = true;
                            }
                        } else {
                            aMaxNumberOfMutationsPerChild *= 2;
                            if (aMaxNumberOfMutationsPerChild <= _MaxNumberOfMutationsPerChild) {
                                this._IndividualCensus.log(
                                        "Iteration " + optimisationIteration + " out of " + _NumberOfOptimisationIterations + " _MaxNumberOfMutationsPerChild increased to " + aMaxNumberOfMutationsPerChild + " fitness " + aFitness);
                                // convergenceCounter = 0;
                            } else {
                                aMaxNumberOfMutationsPerChild = _MaxNumberOfMutationsPerChild;
                                aSwitch = true;
                                convergence1 = true;
                            }
                        }
                    }
                }
            } else {
                break;
            }
        }
        // Get best solution and pack it up.
        // It seems that for parallel processing the packing is important.
        Object[] tOptimisedResult_ObjectArray = new Object[2];
        Object firstKey = aSolutions_TreeMap.firstKey();
        BigDecimal fitness = (BigDecimal) firstKey;
        Object[] tConstraintsAndPopulation_ISARHP_ISARCEP = (Object[]) ((HashSet) aSolutions_TreeMap.get(firstKey)).iterator().next();
//        Object[] population = (Object[]) tConstraintsAndPopulation_ISARHP_ISARCEP[1];
//        if (population != null) {
//            this._IndividualCensus.log(
//                    "population != null");
//            this._IndividualCensus.log(
//                    "population.length " + population.length);
//            this._IndividualCensus.log(
//                    " population[0] instanceof HashMap " + (population[0] instanceof HashMap) +
//                    " population[1] instanceof Vector " + (population[1] instanceof Vector) +
//                    " population[2] instanceof HashMap " + (population[2] instanceof HashMap) +
//                    " population[3] instanceof Vector " + (population[3] instanceof Vector) +
//                    " population[4] instanceof Vector " + (population[4] instanceof Vector) +
//                    " population[5] instanceof HashMap " + (population[5] instanceof HashMap) +
//                    " population[6] instanceof Vector " + (population[6] instanceof Vector) +
//                    " population[7] instanceof HashMap " + (population[7] instanceof HashMap) +
//                    " population[8] instanceof Vector " + (population[8] instanceof Vector));
//        } else {
//            this._IndividualCensus.log(
//                    "population == null");
//        }
        tOptimisedResult_ObjectArray[0] = tConstraintsAndPopulation_ISARHP_ISARCEP;
        tOptimisedResult_ObjectArray[1] = fitness;
        this._IndividualCensus.log(
                "Pre optimisation bestFit " + preOptimisingBestFitString
                + " Post optimisation bestFit " + ((BigDecimal) firstKey).toString());
        return tOptimisedResult_ObjectArray;
    }

    /**
     * @return TreeMap tInitialResult_TreeMap:
     * tInitialResult_TreeMap keys are BigDecimal representing fitness
     * tInitialResult_TreeMap values are Object[] tConstraintsAndPopulation_ISARHP_ISARCEP
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * population[0] = tHPHRPFemale_Age_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_Age_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNonHRP_Vector;
     * population[5] = tCEPFemale_Age_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_Age_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     * @param _InitialPopulationSize
     * The number of different population solutions to be returned.
     */
    private TreeMap getInitialResult_TreeMap(
            int _InitialPopulationSize) {
        TreeMap tInitialResult_TreeMap = new TreeMap();
        Object[] tConstraintsAndPopulation_ISARHP_ISARCEP;
        HashSet solutions;
        Object fitness;
        for (int i = 0; i < _InitialPopulationSize; i++) {
            tConstraintsAndPopulation_ISARHP_ISARCEP = _IndividualCensus_ISARHP_ISARCEP.getConstraintsAndPopulation_ISARHP_ISARCEP(
                    _CASDataRecord);
            fitness = getFitness(
                    tConstraintsAndPopulation_ISARHP_ISARCEP);
            if (tInitialResult_TreeMap.containsKey(fitness)) {
                ((HashSet) tInitialResult_TreeMap.get(fitness)).add(
                        tConstraintsAndPopulation_ISARHP_ISARCEP);
            } else {
                solutions = new HashSet();
                solutions.add(tConstraintsAndPopulation_ISARHP_ISARCEP);
                tInitialResult_TreeMap.put(fitness, solutions);
            }
        }
        return tInitialResult_TreeMap;
    }

    /**
     * Logs the number of aSolutions_TreeMap solutions for each key (fitness).
     * @return TreeMap tOptimisedResult_TreeMap:
     * tOptimisedResult_TreeMap keys are BigDecimal representing fitness
     * tOptimisedResult_TreeMap values are Object[]
     * tConstraintsAndPopulation_ISARHP_ISARCEP
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * population[0] = tHPHRPFemale_Age_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_Age_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNonHRP_Vector;
     * population[5] = tCEPFemale_Age_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_Age_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     * @param n
     * The maximum number of solution that will be returned.
     * @param aSolutions_TreeMap
     * aSolutions_TreeMap keys are BigDecimal representing fitness
     * aSolutions_TreeMap values are Object[] population
     * population[0] ...
     * @param _MaxNumberOfMutationsPerChild
     * Maximum number of DataRecords that can be swapped for a mutation.
     * There are potentially two types of mutation, those for household
     * populations and those for communal establishment populations.
     */
    private TreeMap getOptimisedResult_ObjectArray(
            int n,
            TreeMap aSolutions_TreeMap,
            int _MaxNumberOfMutationsPerChild) {
        Iterator iterator = aSolutions_TreeMap.keySet().iterator();
        Object key = null;
        Object nextKey = null;
        BigDecimal _Fitness;
        int count = 0;
        int count0 = 0;
        while (iterator.hasNext()) {
            key = iterator.next();
            if (count < n) {
                count += ((HashSet) aSolutions_TreeMap.get(key)).size();
                _Fitness = (BigDecimal) key;
                this._IndividualCensus.log("" + (count - count0) + " solutions with _Fitness = " + _Fitness);
                count0 = count;
            }
        }
        aSolutions_TreeMap = breedSolutions_TreeMap(
                aSolutions_TreeMap,
                _MaxNumberOfMutationsPerChild);
        count = 0;
        iterator = aSolutions_TreeMap.keySet().iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            if (iterator.hasNext()) {
                nextKey = iterator.next();
            } else {
                nextKey = null;
            }
            count0 = count;
            HashSet hashSet0 = (HashSet) aSolutions_TreeMap.get(key);
            count += hashSet0.size();
            if (count > _MaxNumberOfSolutions) {
                HashSet tempHashSet = new HashSet();
                Iterator iterator0 = ((HashSet) aSolutions_TreeMap.get(key)).iterator();
                int int0 = Math.min((count - count0), _MaxNumberOfSolutions);
                for (int i = 0; i < int0; i++) {
                    tempHashSet.add(iterator0.next());
                }
                aSolutions_TreeMap.remove(key);
                aSolutions_TreeMap.put(key, tempHashSet);
                break;
            }
        }
        if (nextKey == null) {
            return aSolutions_TreeMap;
        } else {
            return new TreeMap(aSolutions_TreeMap.headMap(nextKey));
        }
    }

    /**
     * @return A TreeMap solutions_TreeMap:
     * keys are BigDecimal fitness values;
     * values are HashSets of Object[] tConstraintsAndPopulation_ISARHP_ISARCEP
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * * population[0] = tHPHRPFemale_Age_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_Age_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNonHRP_Vector;
     * population[5] = tCEPFemale_Age_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_Age_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     * Breeding and selection can be done in many ways.
     * One strategy is to allow every solution in existingSolutions_TreeMap to
     * breed and make it more likely that those which are fitter survive.
     * With constraints that do not apply across the entire population, two
     * or more solutions can be taken and components of their population can be
     * swapped maintaining.
     * With mutation it is best to allow mutation of multiple parts of the 
     * population simultaneously to allow for individuals with certain 
     * characteristics to be swapped with those with similar characteristics, 
     * but in different parts of the population. For example, if there was a 
     * correct number of people in poor health in a solution, but really more of 
     * them are in the CEP not the HP, mutating both simultaneously allows for 
     * a solution to be found whereby this number is kept the same. 
     * @param existingSolutions_TreeMap
     * A TreeMap of solutions with keys being measure of fitness and values
     * being HashSets of populations.
     * @param aMaxNumberOfMutationsPerChild
     * Maximum number of DataRecords that can be swapped for a mutation.
     */
    private TreeMap breedSolutions_TreeMap(
            TreeMap existingSolutions_TreeMap,
            int aMaxNumberOfMutationsPerChild) {
        TreeMap mutatedSolutions_TreeMap = mutate(
                existingSolutions_TreeMap,
                aMaxNumberOfMutationsPerChild);
//        TreeMap solutions_TreeMap = crossover(
//                mutatedSolutions_TreeMap),
//                aMaxNumberOfMutationsPerChild);
//        return solutions_TreeMap;
        return mutatedSolutions_TreeMap;
    }

    /**
     * @return 
     * @param solutions_TreeMap
     * @param aMaxNumberOfMutationsPerChild
     *
     */
    private TreeMap mutate(
            TreeMap solutions_TreeMap,
            int aMaxNumberOfMutationsPerChild) {
        // Consider a mutation rate rather than _MaxNumberOfMutationsPerChild...
        TreeMap tResult_TreeMap = new TreeMap();
        //tResult_TreeMap.putAll(solutions_TreeMap);
        //BigDecimal solutionParentFitness;
        HashSet aResult_HashSet;
        int aNumberOfMutations;
        int aMaxNumberOfMutationsPerChild0 = aMaxNumberOfMutationsPerChild;
        BigDecimal solutionChildFitness;
        Object key;
        HashSet aIndexesToSwap_HashSet;
        //int aIndex;
        HashSet solutions_HashSet;
        Object[] solutionParent;
        Object[] solutionChild;
        /*
         * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
         * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
         * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
         * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
         * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
         * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
         * population[0] = tHPHRPFemale_Age_Vector_HashMap;
         * population[1] = tHPHRPFemale_Vector;
         * population[2] = tHPHRPMale_Age_Vector_HashMap;
         * population[3] = tHPHRPMale_Vector;
         * population[4] = tHPNonHRP_Vector;
         * population[5] = tCEPFemale_Age_Vector_HashMap;
         * population[6] = tCEPFemale_Vector;
         * population[7] = tCEPMale_Age_Vector_HashMap;
         * population[8] = tCEPMale_Vector;
         */
        HashMap parentHPHRPFemale_Age_Vector_HashMap;
        Vector parentHPHRPFemale_Vector;
        HashMap parentHPHRPMale_Age_Vector_HashMap;
        Vector parentHPHRPMale_Vector;
        Vector parentHPNonHRP_Vector;
        HashMap parentCEPFemale_Age_Vector_HashMap;
        Vector parentCEPFemale_Vector;
        HashMap parentCEPMale_Age_Vector_HashMap;
        Vector parentCEPMale_Vector;
        /*
         * parentHPHRPFemale_Age_Vector_HashMap = (HashMap) _ParentPopulation[0];
         * parentHPHRPFemale_Vector = (Vector) _ParentPopulation[1];
         * parentHPHRPMale_Age_Vector_HashMap = (HashMap) _ParentPopulation[2];
         * parentHPHRPMale_Vector = (Vector) _ParentPopulation[3];
         * parentHPNonHRP_Vector = (Vector) _ParentPopulation[4];
         * parentCEPFemale_Age_Vector_HashMap = (HashMap) _ParentPopulation[5];
         * parentCEPFemale_Vector = (Vector) _ParentPopulation[6];
         * parentCEPMale_Age_Vector_HashMap = (HashMap) _ParentPopulation[7];
         * parentCEPMale_Vector = (Vector) _ParentPopulation[8];
         */

        //ISARDataRecord aISARDataRecord;
        Iterator aIterator;
        Iterator bIterator;

        // Mutate HPHRP Female
        HashMap childHPHRPFemale_Age_Vector_HashMap;
        Vector childHPHRPFemale_Vector;
        // Go through each solution for each fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] parentPopulation = (Object[]) solutionParent[1];
                parentHPHRPFemale_Age_Vector_HashMap = (HashMap) parentPopulation[0];
                parentHPHRPFemale_Vector = (Vector) parentPopulation[1];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    if (parentHPHRPFemale_Vector.size() > 0) {
                        // Initialise child
                        solutionChild = new Object[solutionParent.length];
                        solutionChild[0] = tConstraints;
                        solutionChild[1] = new Object[parentPopulation.length];
                        Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                        if (aMaxNumberOfMutationsPerChild0 > parentHPHRPFemale_Vector.size()) {
                            aMaxNumberOfMutationsPerChild = parentHPHRPFemale_Vector.size();
                        }
                        childHPHRPFemale_Vector = (Vector) parentHPHRPFemale_Vector.clone();
                        childHPHRPFemale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                                parentHPHRPFemale_Age_Vector_HashMap);
                        // Mutate
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentHPHRPFemale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPHRPISARDataRecords(
                                    childHPHRPFemale_Age_Vector_HashMap,
                                    childHPHRPFemale_Vector,
                                    aIndexesToSwap_HashSet);
                            solutionChildPopulation[0] = childHPHRPFemale_Age_Vector_HashMap;
                            solutionChildPopulation[1] = childHPHRPFemale_Vector;
                            solutionChildPopulation[2] = parentPopulation[2];
                            solutionChildPopulation[3] = parentPopulation[3];
                            solutionChildPopulation[4] = parentPopulation[4];
                            solutionChildPopulation[5] = parentPopulation[5];
                            solutionChildPopulation[6] = parentPopulation[6];
                            solutionChildPopulation[7] = parentPopulation[7];
                            solutionChildPopulation[8] = parentPopulation[8];
                            solutionChildFitness = getFitness(solutionChild);
                            if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                                ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                            } else {
                                aResult_HashSet = new HashSet();
                                aResult_HashSet.add(solutionChild);
                                tResult_TreeMap.put(
                                        solutionChildFitness,
                                        aResult_HashSet);
                            }
                        }
                    }
                }
            }
        }

        // Mutate HPHRP Male
        HashMap childHPHRPMale_Age_Vector_HashMap;
        Vector childHPHRPMale_Vector;
        // Go through each solution for each _Fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] _ParentPopulation = (Object[]) solutionParent[1];
                parentHPHRPMale_Age_Vector_HashMap = (HashMap) _ParentPopulation[2];
                parentHPHRPMale_Vector = (Vector) _ParentPopulation[3];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    if (parentHPHRPMale_Vector.size() > 0) {
                        // Initialise child
                        solutionChild = new Object[solutionParent.length];
                        solutionChild[0] = tConstraints;
                        solutionChild[1] = new Object[_ParentPopulation.length];
                        Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                        if (aMaxNumberOfMutationsPerChild0 > parentHPHRPMale_Vector.size()) {
                            aMaxNumberOfMutationsPerChild = parentHPHRPMale_Vector.size();
                        }
                        childHPHRPMale_Vector = (Vector) parentHPHRPMale_Vector.clone();
                        childHPHRPMale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                                parentHPHRPMale_Age_Vector_HashMap);
                        // Mutate
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentHPHRPMale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPHRPISARDataRecords(
                                    childHPHRPMale_Age_Vector_HashMap,
                                    childHPHRPMale_Vector,
                                    aIndexesToSwap_HashSet);
                            solutionChildPopulation[0] = _ParentPopulation[0];
                            solutionChildPopulation[1] = _ParentPopulation[1];
                            solutionChildPopulation[2] = childHPHRPMale_Age_Vector_HashMap;
                            solutionChildPopulation[3] = childHPHRPMale_Vector;
                            solutionChildPopulation[4] = _ParentPopulation[4];
                            solutionChildPopulation[5] = _ParentPopulation[5];
                            solutionChildPopulation[6] = _ParentPopulation[6];
                            solutionChildPopulation[7] = _ParentPopulation[7];
                            solutionChildPopulation[8] = _ParentPopulation[8];
                            solutionChildFitness = getFitness(solutionChild);
                            if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                                ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                            } else {
                                aResult_HashSet = new HashSet();
                                aResult_HashSet.add(solutionChild);
                                tResult_TreeMap.put(
                                        solutionChildFitness,
                                        aResult_HashSet);
                            }
                        }
                    }
                }
            }
        }

        // Mutate HPNonHRP
        Vector childHPNonHRP_Vector;
        // Go through each solution for each _Fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] parentPopulation = (Object[]) solutionParent[1];
                parentHPNonHRP_Vector = (Vector) parentPopulation[4];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    if (parentHPNonHRP_Vector.size() > 0) {
                        // Initialise child
                        solutionChild = new Object[solutionParent.length];
                        solutionChild[0] = tConstraints;
                        solutionChild[1] = new Object[parentPopulation.length];
                        Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                        if (aMaxNumberOfMutationsPerChild0 > parentHPNonHRP_Vector.size()) {
                            aMaxNumberOfMutationsPerChild = parentHPNonHRP_Vector.size();
                        }
                        childHPNonHRP_Vector = (Vector) parentHPNonHRP_Vector.clone();
                        // Mutate
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentHPNonHRP_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPNonHRPISARDataRecords(
                                    childHPNonHRP_Vector,
                                    aIndexesToSwap_HashSet);
                            solutionChildPopulation[0] = parentPopulation[0];
                            solutionChildPopulation[1] = parentPopulation[1];
                            solutionChildPopulation[2] = parentPopulation[2];
                            solutionChildPopulation[3] = parentPopulation[3];
                            solutionChildPopulation[4] = childHPNonHRP_Vector;
                            solutionChildPopulation[5] = parentPopulation[5];
                            solutionChildPopulation[6] = parentPopulation[6];
                            solutionChildPopulation[7] = parentPopulation[7];
                            solutionChildPopulation[8] = parentPopulation[8];
                            solutionChildFitness = getFitness(solutionChild);
                            if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                                ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                            } else {
                                aResult_HashSet = new HashSet();
                                aResult_HashSet.add(solutionChild);
                                tResult_TreeMap.put(
                                        solutionChildFitness,
                                        aResult_HashSet);
                            }
                        }
                    }
                }
            }
        }

        // Mutate CEP Female
        HashMap childCEPFemale_Age_Vector_HashMap;
        Vector childCEPFemale_Vector;
        // Go through each solution for each _Fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] parentPopulation = (Object[]) solutionParent[1];
                parentCEPFemale_Age_Vector_HashMap = (HashMap) parentPopulation[5];
                parentCEPFemale_Vector = (Vector) parentPopulation[6];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    if (parentCEPFemale_Vector.size() > 0) {
                        // Initialise child
                        solutionChild = new Object[solutionParent.length];
                        solutionChild[0] = tConstraints;
                        solutionChild[1] = new Object[parentPopulation.length];
                        Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                        if (aMaxNumberOfMutationsPerChild0 > parentCEPFemale_Vector.size()) {
                            aMaxNumberOfMutationsPerChild = parentCEPFemale_Vector.size();
                        }
                        childCEPFemale_Vector = (Vector) parentCEPFemale_Vector.clone();
                        childCEPFemale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                                parentCEPFemale_Age_Vector_HashMap);
                        // Mutate
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentCEPFemale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeCEPISARDataRecords(
                                    childCEPFemale_Age_Vector_HashMap,
                                    childCEPFemale_Vector,
                                    aIndexesToSwap_HashSet);
                            solutionChildPopulation[0] = parentPopulation[0];
                            solutionChildPopulation[1] = parentPopulation[1];
                            solutionChildPopulation[2] = parentPopulation[2];
                            solutionChildPopulation[3] = parentPopulation[3];
                            solutionChildPopulation[4] = parentPopulation[4];
                            solutionChildPopulation[5] = childCEPFemale_Age_Vector_HashMap;
                            solutionChildPopulation[6] = childCEPFemale_Vector;
                            solutionChildPopulation[7] = parentPopulation[7];
                            solutionChildPopulation[8] = parentPopulation[8];
                            solutionChildFitness = getFitness(solutionChild);
                            if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                                ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                            } else {
                                aResult_HashSet = new HashSet();
                                aResult_HashSet.add(solutionChild);
                                tResult_TreeMap.put(solutionChildFitness, aResult_HashSet);
                            }
                        }
                    }
                }
            }
        }

        // Mutate CEP Male
        HashMap childCEPMale_Age_Vector_HashMap;
        Vector childCEPMale_Vector;
        // Go through each solution for each _Fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] parentPopulation = (Object[]) solutionParent[1];
                parentCEPMale_Age_Vector_HashMap = (HashMap) parentPopulation[7];
                parentCEPMale_Vector = (Vector) parentPopulation[8];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    if (parentCEPMale_Vector.size() > 0) {
                        // Initialise child
                        solutionChild = new Object[solutionParent.length];
                        solutionChild[0] = tConstraints;
                        solutionChild[1] = new Object[parentPopulation.length];
                        Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                        if (aMaxNumberOfMutationsPerChild0 > parentCEPMale_Vector.size()) {
                            aMaxNumberOfMutationsPerChild = parentCEPMale_Vector.size();
                        }
                        childCEPMale_Vector = (Vector) parentCEPMale_Vector.clone();
                        childCEPMale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                                parentCEPMale_Age_Vector_HashMap);
                        // Mutate
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentCEPMale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeCEPISARDataRecords(
                                    childCEPMale_Age_Vector_HashMap,
                                    childCEPMale_Vector,
                                    aIndexesToSwap_HashSet);
                            solutionChildPopulation[0] = parentPopulation[0];
                            solutionChildPopulation[1] = parentPopulation[1];
                            solutionChildPopulation[2] = parentPopulation[2];
                            solutionChildPopulation[3] = parentPopulation[3];
                            solutionChildPopulation[4] = parentPopulation[4];
                            solutionChildPopulation[5] = parentPopulation[5];
                            solutionChildPopulation[6] = parentPopulation[6];
                            solutionChildPopulation[7] = childCEPMale_Age_Vector_HashMap;
                            solutionChildPopulation[8] = childCEPMale_Vector;
                            solutionChildFitness = getFitness(solutionChild);
                            if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                                ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                            } else {
                                aResult_HashSet = new HashSet();
                                aResult_HashSet.add(solutionChild);
                                tResult_TreeMap.put(solutionChildFitness, aResult_HashSet);
                            }
                        }
                    }
                }
            }
        }

        // Mutate All Simultaneously
        // Go through each solution for each _Fitness
        aIterator = solutions_TreeMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            //solutionParentFitness = (BigDecimal) key;
            solutions_HashSet = (HashSet) solutions_TreeMap.get(key);
            // Go through each solution
            bIterator = solutions_HashSet.iterator();
            while (bIterator.hasNext()) {
                solutionParent = (Object[]) bIterator.next();
                Object[] tConstraints = (Object[]) solutionParent[0];
                Object[] parentPopulation = (Object[]) solutionParent[1];
                parentHPHRPFemale_Age_Vector_HashMap = (HashMap) parentPopulation[0];
                parentHPHRPFemale_Vector = (Vector) parentPopulation[1];
                parentHPHRPMale_Age_Vector_HashMap = (HashMap) parentPopulation[2];
                parentHPHRPMale_Vector = (Vector) parentPopulation[3];
                parentHPNonHRP_Vector = (Vector) parentPopulation[4];
                parentCEPFemale_Age_Vector_HashMap = (HashMap) parentPopulation[5];
                parentCEPFemale_Vector = (Vector) parentPopulation[6];
                parentCEPMale_Age_Vector_HashMap = (HashMap) parentPopulation[7];
                parentCEPMale_Vector = (Vector) parentPopulation[8];
                // Produce children
                for (int childID = 0; childID <= _MaxNumberOfMutationsPerParent; childID++) {
                    // Initialise child
                    solutionChild = new Object[solutionParent.length];
                    solutionChild[0] = tConstraints;
                    solutionChild[1] = new Object[parentPopulation.length];
                    Object[] solutionChildPopulation = (Object[]) solutionChild[1];
                    // Mutate HPHRPFemale
                    aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                    if (aMaxNumberOfMutationsPerChild0 > parentHPHRPFemale_Vector.size()) {
                        aMaxNumberOfMutationsPerChild = parentHPHRPFemale_Vector.size();
                    }
                    childHPHRPFemale_Vector = (Vector) parentHPHRPFemale_Vector.clone();
                    childHPHRPFemale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                            parentHPHRPFemale_Age_Vector_HashMap);
                    if (aMaxNumberOfMutationsPerChild > 0) {
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    childHPHRPFemale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPHRPISARDataRecords(
                                    childHPHRPFemale_Age_Vector_HashMap,
                                    childHPHRPFemale_Vector,
                                    aIndexesToSwap_HashSet);
                        }
                    }
                    // Mutate HPHRPMale
                    aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                    if (aMaxNumberOfMutationsPerChild0 > parentHPHRPFemale_Vector.size()) {
                        aMaxNumberOfMutationsPerChild = parentHPHRPFemale_Vector.size();
                    }
                    childHPHRPMale_Vector = (Vector) parentHPHRPMale_Vector.clone();
                    childHPHRPMale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                            parentHPHRPMale_Age_Vector_HashMap);
                    if (aMaxNumberOfMutationsPerChild > 0) {
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    childHPHRPMale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPHRPISARDataRecords(
                                    childHPHRPMale_Age_Vector_HashMap,
                                    childHPHRPMale_Vector,
                                    aIndexesToSwap_HashSet);
                        }
                    }
                    // Mutate HPNonHRP
                    aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                    if (aMaxNumberOfMutationsPerChild0 > parentHPNonHRP_Vector.size()) {
                        aMaxNumberOfMutationsPerChild = parentHPNonHRP_Vector.size();
                    }
                    childHPNonHRP_Vector = (Vector) parentHPNonHRP_Vector.clone();
                    if (aMaxNumberOfMutationsPerChild > 0) {
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    childHPNonHRP_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeHPNonHRPISARDataRecords(
                                    childHPNonHRP_Vector,
                                    aIndexesToSwap_HashSet);
                        }
                    }
                    // Mutate CEPFemale
                    aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                    if (aMaxNumberOfMutationsPerChild0 > parentCEPFemale_Vector.size()) {
                        aMaxNumberOfMutationsPerChild = parentCEPFemale_Vector.size();
                    }
                    childCEPFemale_Vector = (Vector) parentCEPFemale_Vector.clone();
                    childCEPFemale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                            parentCEPFemale_Age_Vector_HashMap);
                    if (aMaxNumberOfMutationsPerChild > 0) {
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    childCEPFemale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeCEPISARDataRecords(
                                    childCEPFemale_Age_Vector_HashMap,
                                    childCEPFemale_Vector,
                                    aIndexesToSwap_HashSet);
                        }
                    }
                    // Mutate CEPMale
                    aMaxNumberOfMutationsPerChild = aMaxNumberOfMutationsPerChild0;
                    if (aMaxNumberOfMutationsPerChild0 > parentCEPMale_Vector.size()) {
                        aMaxNumberOfMutationsPerChild = parentCEPMale_Vector.size();
                    }
                    childCEPMale_Vector = (Vector) parentCEPMale_Vector.clone();
                    childCEPMale_Age_Vector_HashMap = GeneticAlgorithm.copy_Key_Vector_HashMap(
                            parentCEPMale_Age_Vector_HashMap);
                    if (aMaxNumberOfMutationsPerChild > 0) {
                        aNumberOfMutations = _Random.nextInt(aMaxNumberOfMutationsPerChild);
                        if (aNumberOfMutations > 0) {
                            aIndexesToSwap_HashSet = Moses_Collections.getRandomIndexes_HashSet(
                                    parentCEPMale_Vector,
                                    aNumberOfMutations,
                                    _Random);
                            exchangeCEPISARDataRecords(
                                    childCEPMale_Age_Vector_HashMap,
                                    childCEPMale_Vector,
                                    aIndexesToSwap_HashSet);
                        }
                    }
                    // Add solution
                    solutionChildPopulation[0] = childHPHRPFemale_Age_Vector_HashMap;
                    solutionChildPopulation[1] = childHPHRPFemale_Vector;
                    solutionChildPopulation[2] = childHPHRPMale_Age_Vector_HashMap;
                    solutionChildPopulation[3] = childHPHRPMale_Vector;
                    solutionChildPopulation[4] = childHPNonHRP_Vector;
                    solutionChildPopulation[5] = childCEPFemale_Age_Vector_HashMap;
                    solutionChildPopulation[6] = childCEPFemale_Vector;
                    solutionChildPopulation[7] = childCEPMale_Age_Vector_HashMap;
                    solutionChildPopulation[8] = childCEPMale_Vector;
                    solutionChildFitness = getFitness(solutionChild);
                    if (tResult_TreeMap.containsKey(solutionChildFitness)) {
                        ((HashSet) tResult_TreeMap.get(solutionChildFitness)).add(solutionChild);
                    } else {
                        aResult_HashSet = new HashSet();
                        aResult_HashSet.add(solutionChild);
                        tResult_TreeMap.put(solutionChildFitness, aResult_HashSet);
                    }
                }
            }
        }

        tResult_TreeMap.putAll(solutions_TreeMap);
        return tResult_TreeMap;
    }

    /**
     * Exchanges some ISARDataRecords in tCEP_Age_Vector_HashMap
     * ensuring constraints are met.
     * @param tCEP_Age_Vector_HashMap
     * @param dataRecordsIndexesToSwapHashSet
     * @param tCEP_Vector
     */
    public void exchangeCEPISARDataRecords(
            HashMap tCEP_Age_Vector_HashMap,
            Vector tCEP_Vector,
            HashSet dataRecordsIndexesToSwapHashSet) {
        /*
         * Initialise toSwapOutCEP_Age_Vector_HashMap,
         * toSwapInCEP_Age_Vector_HashMap,
         * toSwapInCEP_Vector.
         */
        HashMap toSwapOutCEP_Age_Vector_HashMap = new HashMap();
        HashMap toSwapInCEP_Age_Vector_HashMap = new HashMap();
        Vector toSwapInCEP_Vector = new Vector();
        Iterator aIterator;
        Census_ISARDataRecord aISARDataRecord;
        Census_ISARDataRecord bISARDataRecord;
        int aAge_int;
        short aAge_short;
        short bAge_short;
        int age7;
        // 0,5,8,10,15,16,20,25,30,45,60,65,75,85,90
        boolean sex = true;
        AgeSexType bAgeSexType;
        Object key;
        Object value;
        short type;
        aIterator = dataRecordsIndexesToSwapHashSet.iterator();
        while (aIterator.hasNext()) {
            aISARDataRecord = (Census_ISARDataRecord) tCEP_Vector.elementAt((Integer) aIterator.next());
            aAge_int = aISARDataRecord.getAgeInt();
            sex = aISARDataRecord.get_SEX();
            type = Census_ISARDataHandler.getRELTOHRType1(aISARDataRecord);
            // type is only not 3 if there was a struggle to initialise with type = 3
            aAge_short = Data_AgeConverter.getAgeClass4(aAge_int);
            boolean got = false;
            value = toSwapOutCEP_Age_Vector_HashMap.get(aAge_short);
            if (value == null) {
                Vector aISARDataRecord_Vector = new Vector();
                aISARDataRecord_Vector.add(aISARDataRecord);
                toSwapOutCEP_Age_Vector_HashMap.put(
                        aAge_short,
                        aISARDataRecord_Vector);
            } else {
                Vector aISARDataRecord_Vector = (Vector) value;
                aISARDataRecord_Vector.add(aISARDataRecord);
            }
            int counter = 0;
            do {
                age7 = Data_AgeConverter.getAge7(aAge_int, _Random);
                bAgeSexType = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.new AgeSexType(
                        Data_AgeConverter.getAgeClassISARDataRecord(age7),
                        sex,
                        type);
                bISARDataRecord = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.getISARDataRecord(
                        _Random,
                        bAgeSexType);
                if (bISARDataRecord != null) {
                    got = true;
                } else {
                    counter++;
                    if (counter > 1000) {
                        _IndividualCensus_ISARHP_ISARCEP.log(
                                "Getting stuck in exchangeCEPISARDataRecords(HashMap,Vector,HashSet)");
                    }
                }
            } while (!got);
            toSwapInCEP_Vector.add(bISARDataRecord);
            value = toSwapInCEP_Age_Vector_HashMap.get(aAge_short);
            if (value == null) {
                Vector bISARDataRecord_Vector = new Vector();
                bISARDataRecord_Vector.add(bISARDataRecord);
                toSwapInCEP_Age_Vector_HashMap.put(
                        aAge_short,
                        bISARDataRecord_Vector);
            } else {
                Vector bISARDataRecord_Vector = (Vector) value;
                bISARDataRecord_Vector.add(bISARDataRecord);
            }
        }
        /*
         * Replace ISARDataRecords in tCEP_Age_Vector_HashMap
         * (toSwapOutCEP_Vector_HashMap) with those in
         * toSwapInCEP_Vector_HashMap
         * N.B. No need to do all swap outs (for each Age) keeping a
         * list of indexes before doing the swap ins. There is no issue if
         * swapping something in the interim that is already swapped.
         */
        aIterator = toSwapOutCEP_Age_Vector_HashMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            aAge_short = (Short) key;
            Vector aVector = (Vector) toSwapInCEP_Age_Vector_HashMap.get(key);
            Vector aCEP_Age_Vector = (Vector) tCEP_Age_Vector_HashMap.get(key);
            if (aCEP_Age_Vector != null) {
                for (int i = 0; i < aVector.size(); i++) {
                    for (int j = 0; j < aCEP_Age_Vector.size(); j++) {
                        aISARDataRecord = (Census_ISARDataRecord) aCEP_Age_Vector.elementAt(j);
                        bAge_short = Data_AgeConverter.getAgeClass4(aISARDataRecord.getAgeInt());
                        if (aAge_short == bAge_short) {
                            aCEP_Age_Vector.setElementAt(
                                    aISARDataRecord,
                                    j);
                        }
                    }
                }
            }
        }
        /*
         * Replace ISARDataRecords in tCEP_Vector
         * (toSwapOutCEP_Vector_HashMap, these have indexes
         * as in dataRecordsIndexesToSwapHashSet) with those in
         * toSwapInCEP_Vector_HashMap
         */
        aIterator = dataRecordsIndexesToSwapHashSet.iterator();
        int swapIndex = 0;
        while (aIterator.hasNext()) {
            tCEP_Vector.setElementAt(
                    toSwapInCEP_Vector.elementAt(swapIndex),
                    (Integer) aIterator.next());
            swapIndex++;
        }
    }

    /**
     * Exchanges some ISARDataRecords in tHPNonHRP_Vector.
     * Only constraint is to return the correct number of males and females.
     * @param tHPNonHRP_Vector
     * @param dataRecordsIndexesToSwapHashSet
     */
    public void exchangeHPNonHRPISARDataRecords(
            Vector tHPNonHRP_Vector,
            HashSet dataRecordsIndexesToSwapHashSet) {
        Iterator aIterator;
        Census_ISARDataRecord aISARDataRecord;
        Census_ISARDataRecord bISARDataRecord;
        int aIndex;
        int age;
        short aAge_short;
        boolean sex;
        AgeSexType bAgeSexType;
        aIterator = dataRecordsIndexesToSwapHashSet.iterator();
        while (aIterator.hasNext()) {
            aIndex = (Integer) aIterator.next();
            aISARDataRecord = (Census_ISARDataRecord) tHPNonHRP_Vector.elementAt(aIndex);
            sex = aISARDataRecord.get_SEX();
            boolean got = false;
            int counter = 0;
            do {
                age = _Random.nextInt(100);
//                if (age >= 35 && age < 40) {
//                    int debug = 1;
//                }
                bAgeSexType = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.new AgeSexType(
                        Data_AgeConverter.getAgeClassISARDataRecord(age),
                        sex,
                        (short) 2);
                bISARDataRecord = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.getISARDataRecord(
                        _Random,
                        bAgeSexType);
                if (bISARDataRecord != null) {
                    got = true;
                } else {
                    counter++;
                    if (counter > 1000) {
                        _IndividualCensus_ISARHP_ISARCEP.log(
                                "Getting stuck in exchangeHPNonHRPISARDataRecords(HashMap,Vector,HashSet)");
                    }
                }
            } while (!got);
            tHPNonHRP_Vector.setElementAt(
                    bISARDataRecord,
                    aIndex);
        }
    }

    /**
     * Exchanges some ISARDataRecords in tHPHRP_AgeSexType_Vector_HashMap
     * ensuring constraints are met.
     * @param tHPHRP_Age_Vector_HashMap
     * @param dataRecordsIndexesToSwapHashSet
     * @param tHPHRP_Vector
     */
    public void exchangeHPHRPISARDataRecords(
            HashMap tHPHRP_Age_Vector_HashMap,
            Vector tHPHRP_Vector,
            HashSet dataRecordsIndexesToSwapHashSet) {
        /*
         * Initialise toSwapOutHPHRP_Age_Vector_HashMap,
         * toSwapInHPHRP_AgeSexType_Vector_HashMap,
         * toSwapInHPHRP_Vector.
         */
        HashMap toSwapOutHPHRP_Age_Vector_HashMap = new HashMap();
        HashMap toSwapInHPHRP_Age_Vector_HashMap = new HashMap();
        Vector toSwapInHPHRP_Vector = new Vector();
        Iterator aIterator;
        Census_ISARDataRecord aISARDataRecord;
        Census_ISARDataRecord bISARDataRecord;
        int aAge_int;
        short aAge_short;
        short bAge_short;
        // 0,20,30,60
        int age5;
        short type = 1;
        boolean sex = true;
        AgeSexType aAgeSexType;
        Object key;
        Object value;
        aIterator = dataRecordsIndexesToSwapHashSet.iterator();
        while (aIterator.hasNext()) {
            aISARDataRecord = (Census_ISARDataRecord) tHPHRP_Vector.elementAt((Integer) aIterator.next());
            aAge_int = aISARDataRecord.getAgeInt();
            sex = aISARDataRecord.get_SEX();
            aAge_short = Data_AgeConverter.getAgeClass2(aAge_int);
            boolean got = false;
            value = toSwapOutHPHRP_Age_Vector_HashMap.get(aAge_short);
            if (value == null) {
                Vector aISARDataRecord_Vector = new Vector();
                aISARDataRecord_Vector.add(aISARDataRecord);
                toSwapOutHPHRP_Age_Vector_HashMap.put(
                        aAge_short,
                        aISARDataRecord_Vector);
            } else {
                Vector aISARDataRecord_Vector = (Vector) value;
                aISARDataRecord_Vector.add(aISARDataRecord);
            }
            int counter = 0;
            do {
                age5 = Data_AgeConverter.getAge5(aAge_short, _Random);
                aAgeSexType = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.new AgeSexType(
                        Data_AgeConverter.getAgeClassISARDataRecord(age5),
                        sex,
                        type);
                bISARDataRecord = _IndividualCensus_ISARHP_ISARCEP._ISARDataHandler.getISARDataRecord(
                        _Random,
                        aAgeSexType);
                if (bISARDataRecord != null) {
                    got = true;
                } else {
                    counter++;
                    if (counter > 1000) {
                        _IndividualCensus_ISARHP_ISARCEP.log(
                                "Getting stuck in exchangeHPHRPISARDataRecords(HashMap,Vector,HashSet)");
                    }
                }
            } while (!got);
            toSwapInHPHRP_Vector.add(bISARDataRecord);
            value = toSwapInHPHRP_Age_Vector_HashMap.get(aAge_short);
            if (value == null) {
                Vector bISARDataRecord_Vector = new Vector();
                bISARDataRecord_Vector.add(bISARDataRecord);
                toSwapInHPHRP_Age_Vector_HashMap.put(
                        aAge_short,
                        bISARDataRecord_Vector);
            } else {
                Vector bISARDataRecord_Vector = (Vector) value;
                bISARDataRecord_Vector.add(bISARDataRecord);
            }
        }
        /*
         * Replace ISARDataRecords in tHPHRP_Age_Vector_HashMap
         * (toSwapOutHPHRP_Age_Vector_HashMap) with those in
         * toSwapInHPHRP_Age_Vector_HashMap
         * N.B. No need to do all swap outs (for each Age) keeping a
         * list of indexes before doing the swap ins. There is no issue if
         * swapping something in the interim that is already swapped.
         */
        aIterator = toSwapOutHPHRP_Age_Vector_HashMap.keySet().iterator();
        while (aIterator.hasNext()) {
            key = aIterator.next();
            aAge_short = (Short) key;
            Vector aVector = (Vector) toSwapInHPHRP_Age_Vector_HashMap.get(key);
            Vector aHPHRP_Vector = (Vector) tHPHRP_Age_Vector_HashMap.get(key);
            if (aHPHRP_Vector != null) {
                for (int i = 0; i < aVector.size(); i++) {
                    for (int j = 0; j < aHPHRP_Vector.size(); j++) {
                        aISARDataRecord = (Census_ISARDataRecord) aHPHRP_Vector.elementAt(j);
                        bAge_short = Data_AgeConverter.getAgeClass2(aISARDataRecord.getAgeInt());
                        if (aAge_short == bAge_short) {
                            aHPHRP_Vector.setElementAt(
                                    aISARDataRecord,
                                    j);
                        }
                    }
                }
            }
        }
        /*
         * Replace ISARDataRecords in tHPHRP_Vector
         * (toSwapOutHPHRP_Age_Vector_HashMap, these have indexes
         * as in dataRecordsIndexesToSwapHashSet) with those in
         * toSwapInHPHRP_Age_Vector_HashMap
         */
        aIterator = dataRecordsIndexesToSwapHashSet.iterator();
        int swapIndex = 0;
        while (aIterator.hasNext()) {
            tHPHRP_Vector.setElementAt(
                    toSwapInHPHRP_Vector.elementAt(swapIndex),
                    (Integer) aIterator.next());
            swapIndex++;
        }
    }

    /**
     * @return A BigDecimal indicating goodness of fit. This is a sum of the
 sum of squared difference between Census_CASDataRecord values and estimated
 aggregate measures. The lower the number returned the better the fit.
     * @param tConstraintsAndPopulation_ISARHP_ISARCEP
     * An Object[]:
     * tConstraintsAndPopulation_ISARHP_ISARCEP[0] constraints:
     * constraints[0] = constraintCAS003HPHRPAgeFemaleCount_HashMap;
     * constraints[1] = constraintCAS003HPHRPAgeMaleCount_HashMap;
     * constraints[2] = constraintCAS001CEPAgeFemaleCount3_HashMap;
     * constraints[3] = constraintCAS001CEPAgeMaleCount3_HashMap;
     * tConstraintsAndPopulation_ISARHP_ISARCEP[2] population:
     * population[0] = tHPHRPFemale_AgeSexType_Vector_HashMap;
     * population[1] = tHPHRPFemale_Vector;
     * population[2] = tHPHRPMale_AgeSexType_Vector_HashMap;
     * population[3] = tHPHRPMale_Vector;
     * population[4] = tHPNonHRP_Vector;
     * population[5] = tCEPFemale_AgeSexType_Vector_HashMap;
     * population[6] = tCEPFemale_Vector;
     * population[7] = tCEPMale_AgeSexType_Vector_HashMap;
     * population[8] = tCEPMale_Vector;
     */
    public BigDecimal getFitness(
            Object[] tConstraintsAndPopulation_ISARHP_ISARCEP) {
        Object[] tFitnessCounts = getFitnessCounts();
        HashMap tCASCounts = (HashMap) tFitnessCounts[0];
        HashMap tSARCounts = new HashMap((HashMap) tFitnessCounts[1]);
        Object[] population = (Object[]) tConstraintsAndPopulation_ISARHP_ISARCEP[1];
        Census_ISARDataRecord aISARDataRecord;
        Iterator iterator;
        Object key;
        Object value;
        // addToCounts from tHPHRPFemale_Vector
        Vector tHPHRPFemale_Vector = (Vector) population[1];
        for (int i = 0; i < tHPHRPFemale_Vector.size(); i++) {
            aISARDataRecord = (Census_ISARDataRecord) tHPHRPFemale_Vector.elementAt(i);
            addToCountsHP(
                    aISARDataRecord,
                    tSARCounts,
                    this._Random);
        }
        // addToCounts from tHPHRPMale_Vector
        Vector tHPHRPMale_Vector = (Vector) population[3];
        for (int i = 0; i < tHPHRPMale_Vector.size(); i++) {
            aISARDataRecord = (Census_ISARDataRecord) tHPHRPMale_Vector.elementAt(i);
            addToCountsHP(
                    aISARDataRecord,
                    tSARCounts,
                    this._Random);
        }
        // addToCounts from tHPNonHRP_Vector
        Vector tHPNonHRP_Vector = (Vector) population[4];
        for (int i = 0; i < tHPNonHRP_Vector.size(); i++) {
            aISARDataRecord = (Census_ISARDataRecord) tHPNonHRP_Vector.elementAt(i);
            addToCountsHP(
                    aISARDataRecord,
                    tSARCounts,
                    this._Random);
        }
        // addToCounts from tCEPFemale_Vector
        Vector tCEPFemale_Vector = (Vector) population[6];
        for (int i = 0; i < tCEPFemale_Vector.size(); i++) {
            aISARDataRecord = (Census_ISARDataRecord) tCEPFemale_Vector.elementAt(i);
            addToCountsCEP(
                    aISARDataRecord,
                    tSARCounts,
                    this._Random);
        }
        // addToCounts from tCEPFemale_Vector
        Vector tCEPMale_Vector = (Vector) population[8];
        for (int i = 0; i < tCEPMale_Vector.size(); i++) {
            aISARDataRecord = (Census_ISARDataRecord) tCEPMale_Vector.elementAt(i);
            addToCountsCEP(
                    aISARDataRecord,
                    tSARCounts,
                    this._Random);
        }
        // Calculate _Fitness
        BigDecimal _Fitness = new BigDecimal("0");
        iterator = tCASCounts.keySet().iterator();
        BigDecimal aCASCount;
        BigDecimal aSARCount;
        int scale = 20;
        while (iterator.hasNext()) {
            key = iterator.next();
            aCASCount = new BigDecimal(((Integer) tCASCounts.get(key)).toString());
            aSARCount = new BigDecimal(((Integer) tSARCounts.get(key)).toString());
            _Fitness = _Fitness.add(
                    // ( _CASCount.subtract( _SARCount ).pow( 2 ) ).divide(
                    // _CASCount.pow(2).add( BigDecimal.ONE ), scale,
                    // BigDecimal.ROUND_HALF_EVEN )
                    (aCASCount.subtract(aSARCount).pow(2)).divide(aCASCount.add(BigDecimal.ONE), scale,
                    BigDecimal.ROUND_HALF_EVEN));
        }
        return _Fitness;
    }

    /**
     * aISARDataRecord does not comprise part of Household Population
     * @param aISARDataRecord
     * The Census_ISARDataRecord to be accounted for in tSARCounts
     * @param tSARCounts
     *  A HashMap of Aggregate Counts
     * @param a_Random
     */
    protected static void addToCountsCEP(
            Census_ISARDataRecord aISARDataRecord,
            HashMap tSARCounts,
            Random a_Random) {
        // Initialise
        // short AGE0;
        short MARSTAT;
        boolean married = false;
        short CETYPE = aISARDataRecord.get_CETYPE();
        short HEALTH = aISARDataRecord.get_HEALTH();
        short LLTI = aISARDataRecord.get_LLTI();
        int ECONACTInt = aISARDataRecord.get_ECONACT();
        int AGE0Int = aISARDataRecord.get_AGE0();
        boolean SEX = aISARDataRecord.get_SEX();
        int MARSTATInt = aISARDataRecord.get_MARSTAT();
        if (MARSTATInt == 2 || MARSTATInt == 3) {
            married = true;
        }
        // Calculate estimated Health variables
        if (HEALTH == -9) {
            HEALTH = (short) (a_Random.nextInt(2) + 1);
        }
        if (HEALTH == 1) {
            tSARCounts.put("peopleWhoseGeneralHealthWasGood",
                    ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasGood")).intValue() + 1);
        } else {
            if (HEALTH == 2) {
                tSARCounts.put("peopleWhoseGeneralHealthWasFairlyGood",
                        ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasFairlyGood")).intValue() + 1);
            } else {
                tSARCounts.put("peopleWhoseGeneralHealthWasNotGood",
                        ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasNotGood")).intValue() + 1);
            }
        }
        if (LLTI == -9) {
            LLTI = (short) (a_Random.nextInt(2) + 1);
        }
        if (LLTI == 1) {
            tSARCounts.put("peopleWithLimitingLongTermIllness",
                    ((Integer) tSARCounts.get("peopleWithLimitingLongTermIllness")).intValue() + 1);
        }
        // Add to estimated Employment variables
        if (SEX) {
            switch (AGE0Int) {
                case 0:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 1:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 2:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 3:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 4:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 5:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 6:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 7:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 8:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 9:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 10:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 11:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 12:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 13:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 14:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 15:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 16:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge16to19",
                                ((Integer) tSARCounts.get("malesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 17:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge16to19",
                                ((Integer) tSARCounts.get("malesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 18:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge16to19",
                                ((Integer) tSARCounts.get("malesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 19:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge16to19",
                                ((Integer) tSARCounts.get("malesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 20:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 21:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 22:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 23:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 24:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 25:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 26:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 27:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 28:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 29:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 30:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 31:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 32:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 33:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 34:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 35:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 36:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 37:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 38:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 39:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 40:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 41:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 42:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 43:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 44:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 45:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 46:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 47:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 48:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 49:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 50:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 51:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 52:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 53:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 54:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 55:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 56:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 57:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 58:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 59:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 60:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 61:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 62:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 63:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 64:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 65:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 66:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 67:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 68:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 69:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 70:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 71:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 72:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 73:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 74:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 75:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 76:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 77:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 78:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 79:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                default:
                    tSARCounts.put(
                            "malesAge80AndOver",
                            ((Integer) tSARCounts.get("malesAge80AndOver")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge80AndOver",
                                ((Integer) tSARCounts.get("malesMarriedAge80AndOver")).intValue() + 1);
                    }
                    break;
            }
            if (AGE0Int >= 16 && AGE0Int <= 24 && (ECONACTInt == 7 || ECONACTInt == 8)) {
                tSARCounts.put(
                        "malesAge16to24Unemployed",
                        ((Integer) tSARCounts.get("malesAge16to24Unemployed")).intValue() + 1);
            }
            if (AGE0Int >= 16 && AGE0Int <= 74) {
                tSARCounts.put(
                        "malesAge16to74",
                        ((Integer) tSARCounts.get("malesAge16to74")).intValue() + 1);
                switch (ECONACTInt) {
                    case 1:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveEmployedPartTime",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveEmployedPartTime")).intValue() + 1);
                        break;
                    case 2:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveEmployedFullTime",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveEmployedFullTime")).intValue() + 1);
                        break;
                    case 3:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 4:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 5:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 6:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 7:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 8:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    default:
                        break;
                }
            }
//            if (AGE0Int >= 50 && AGE0Int <= 74 && (ECONACTInt == 7 || ECONACTInt == 8)) {
//                tSARCounts.put(
//                        "malesAge50AndOverUnemployed",
//                        ((Integer) tSARCounts.get("malesAge50AndOverUnemployed")).intValue() + 1);
//            }
        } else {
            switch (AGE0Int) {
                case 0:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 1:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 2:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 3:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 4:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 5:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 6:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 7:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 8:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 9:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 10:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 11:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 12:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 13:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 14:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 15:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 16:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge16to19",
                                ((Integer) tSARCounts.get("femalesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 17:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge16to19",
                                ((Integer) tSARCounts.get("femalesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 18:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge16to19",
                                ((Integer) tSARCounts.get("femalesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 19:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge16to19",
                                ((Integer) tSARCounts.get("femalesMarriedAge16to19")).intValue() + 1);
                    }
                    break;
                case 20:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 21:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 22:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 23:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 24:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 25:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 26:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 27:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 28:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 29:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 30:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 31:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 32:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 33:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 34:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 35:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 36:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 37:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 38:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 39:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 40:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 41:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 42:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 43:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 44:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 45:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 46:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 47:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 48:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 49:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 50:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 51:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 52:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 53:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 54:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 55:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 56:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 57:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 58:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 59:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 60:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 61:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 62:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 63:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 64:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 65:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 66:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 67:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 68:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 69:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 70:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 71:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 72:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 73:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 74:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 75:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 76:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 77:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 78:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 79:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                default:
                    tSARCounts.put(
                            "femalesAge80AndOver",
                            ((Integer) tSARCounts.get("femalesAge80AndOver")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge80AndOver",
                                ((Integer) tSARCounts.get("femalesMarriedAge80AndOver")).intValue() + 1);
                    }
                    break;
            }
            if (AGE0Int >= 16 && AGE0Int <= 24 && (ECONACTInt == 7 || ECONACTInt == 8)) {
                tSARCounts.put(
                        "femalesAge16to24Unemployed",
                        ((Integer) tSARCounts.get("femalesAge16to24Unemployed")).intValue() + 1);
            }
            if (AGE0Int >= 16 && AGE0Int <= 74) {
                tSARCounts.put(
                        "femalesAge16to74",
                        ((Integer) tSARCounts.get("femalesAge16to74")).intValue() + 1);
                switch (ECONACTInt) {
                    case 1:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveEmployedPartTime",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveEmployedPartTime")).intValue() + 1);
                        break;
                    case 2:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveEmployedFullTime",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveEmployedFullTime")).intValue() + 1);
                        break;
                    case 3:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 4:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 5:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 6:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 7:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 8:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    default:
                        break;
                }
            }
//            if (AGE0Int >= 50 && AGE0Int <= 74 && (ECONACTInt == 7 || ECONACTInt == 8)) {
//                tSARCounts.put(
//                        "femalesAge50AndOverUnemployed",
//                        ((Integer) tSARCounts.get("femalesAge50AndOverUnemployed")).intValue() + 1);
//            }
        }
    }

    /**
     * Census_ISARDataRecord aISARDataRecord is assumed to comprise part of Household
 Population
     * @param aISARDataRecord
     * The Census_ISARDataRecord that will be accounted for in tSARCounts
     * @param tSARCounts
     * HashMap of aggregate counts.
     * @param a_Random
     */
    protected static void addToCountsHP(
            Census_ISARDataRecord aISARDataRecord,
            HashMap tSARCounts,
            Random a_Random) {
        // Initialise
        // short AGE0;
        short MARSTAT;
        boolean married = false;
        short CETYPE = aISARDataRecord.get_CETYPE();
        short HEALTH = aISARDataRecord.get_HEALTH();
        short LLTI = aISARDataRecord.get_LLTI();
        int ECONACTInt = aISARDataRecord.get_ECONACT();
        int AGE0Int = aISARDataRecord.get_AGE0();
        if (AGE0Int >= 35 && AGE0Int < 40) {
            int debug = 1;
        }
        boolean SEX = aISARDataRecord.get_SEX();
        int MARSTATInt = aISARDataRecord.get_MARSTAT();
        if (MARSTATInt == 2 || MARSTATInt == 3) {
            married = true;
        }
        // Calculate estimated Health variables
        if (HEALTH == -9) {
            HEALTH = (short) (a_Random.nextInt(2) + 1);
        }
        if (HEALTH == 1) {
            tSARCounts.put(
                    "peopleWhoseGeneralHealthWasGood",
                    ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasGood")).intValue() + 1);
        } else {
            if (HEALTH == 2) {
                tSARCounts.put(
                        "peopleWhoseGeneralHealthWasFairlyGood",
                        ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasFairlyGood")).intValue() + 1);
            } else {
                tSARCounts.put(
                        "peopleWhoseGeneralHealthWasNotGood",
                        ((Integer) tSARCounts.get("peopleWhoseGeneralHealthWasNotGood")).intValue() + 1);
            }
        }
        if (LLTI == -9) {
            LLTI = (short) (a_Random.nextInt(2) + 1);
        }
        if (LLTI == 1) {
            tSARCounts.put(
                    "peopleWithLimitingLongTermIllness",
                    ((Integer) tSARCounts.get("peopleWithLimitingLongTermIllness")).intValue() + 1);
        }
        // Add to estimated Household Composition variables
        if (aISARDataRecord.get_RELTOHR() == 1) {
            short FAMTYP = aISARDataRecord.get_FAMTYP();
            if (FAMTYP == 1 || FAMTYP == 2) {
                tSARCounts.put(
                        "loneParentHouseholdsWithChildren",
                        ((Integer) tSARCounts.get("loneParentHouseholdsWithChildren")).intValue() + 1);
            } else {
                if (FAMTYP == 3 || FAMTYP == 6) {
                    tSARCounts.put(
                            "oneFamilyAndNoChildren",
                            ((Integer) tSARCounts.get("oneFamilyAndNoChildren")).intValue() + 1);
                } else {
                    if (FAMTYP == 4 || FAMTYP == 5 || FAMTYP == 7 || FAMTYP == 8) {
                        tSARCounts.put(
                                "marriedOrCohabitingCoupleWithChildren",
                                ((Integer) tSARCounts.get("marriedOrCohabitingCoupleWithChildren")).intValue() + 1);
                    }
                }
            }
        }
        // Add to estimated age variables
        if (SEX) {
            switch (AGE0Int) {
                case 0:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 1:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 2:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 3:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 4:
                    tSARCounts.put(
                            "malesAge0to4",
                            ((Integer) tSARCounts.get("malesAge0to4")).intValue() + 1);
                    break;
                case 5:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 6:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 7:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 8:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 9:
                    tSARCounts.put(
                            "malesAge5to9",
                            ((Integer) tSARCounts.get("malesAge5to9")).intValue() + 1);
                    break;
                case 10:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 11:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 12:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 13:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 14:
                    tSARCounts.put(
                            "malesAge10to14",
                            ((Integer) tSARCounts.get("malesAge10to14")).intValue() + 1);
                    break;
                case 15:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 16:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 17:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 18:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 19:
                    tSARCounts.put(
                            "malesAge15to19",
                            ((Integer) tSARCounts.get("malesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge0to15",
                                ((Integer) tSARCounts.get("malesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 20:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 21:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 22:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 23:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 24:
                    tSARCounts.put(
                            "malesAge20to24",
                            ((Integer) tSARCounts.get("malesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge20to24",
                                ((Integer) tSARCounts.get("malesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 25:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 26:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 27:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 28:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 29:
                    tSARCounts.put(
                            "malesAge25to29",
                            ((Integer) tSARCounts.get("malesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge25to29",
                                ((Integer) tSARCounts.get("malesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 30:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 31:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 32:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 33:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 34:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 35:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 36:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 37:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 38:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 39:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 40:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 41:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 42:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 43:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 44:
                    tSARCounts.put(
                            "malesAge30to44",
                            ((Integer) tSARCounts.get("malesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge30to44",
                                ((Integer) tSARCounts.get("malesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 45:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 46:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 47:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 48:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 49:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 50:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 51:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 52:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 53:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 54:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 55:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 56:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 57:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 58:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 59:
                    tSARCounts.put(
                            "malesAge45to59",
                            ((Integer) tSARCounts.get("malesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge45to59",
                                ((Integer) tSARCounts.get("malesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 60:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 61:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 62:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 63:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 64:
                    tSARCounts.put(
                            "malesAge60to64",
                            ((Integer) tSARCounts.get("malesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge60to64",
                                ((Integer) tSARCounts.get("malesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 65:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 66:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 67:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 68:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 69:
                    tSARCounts.put(
                            "malesAge65to69",
                            ((Integer) tSARCounts.get("malesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 70:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 71:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 72:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 73:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 74:
                    tSARCounts.put(
                            "malesAge70to74",
                            ((Integer) tSARCounts.get("malesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge65to74",
                                ((Integer) tSARCounts.get("malesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 75:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 76:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 77:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 78:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 79:
                    tSARCounts.put(
                            "malesAge75to79",
                            ((Integer) tSARCounts.get("malesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge75to79",
                                ((Integer) tSARCounts.get("malesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                default:
                    tSARCounts.put(
                            "malesAge80AndOver",
                            ((Integer) tSARCounts.get("malesAge80AndOver")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "malesMarriedAge80AndOver",
                                ((Integer) tSARCounts.get("malesMarriedAge80AndOver")).intValue() + 1);
                    }
                    break;
            }
            // Add to estimated employment variables
            if (AGE0Int >= 16 && AGE0Int <= 24 && (ECONACTInt == 7 || ECONACTInt == 8)) {
                tSARCounts.put(
                        "malesAge16to24Unemployed",
                        ((Integer) tSARCounts.get("malesAge16to24Unemployed")).intValue() + 1);
            }
            if (AGE0Int >= 16 && AGE0Int <= 74) {
                tSARCounts.put(
                        "malesAge16to74",
                        ((Integer) tSARCounts.get("malesAge16to74")).intValue() + 1);
                switch (ECONACTInt) {
                    case 1:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveEmployedFullTime",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveEmployedFullTime")).intValue() + 1);
                        break;
                    case 2:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveEmployedPartTime",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveEmployedPartTime")).intValue() + 1);
                        break;
                    case 3:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 4:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 5:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 6:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 7:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 8:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 9:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyInactiveRetired",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyInactiveRetired")).intValue() + 1);
                        break;
                    case 11:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily")).intValue() + 1);
                        break;
                    case 12:
                        tSARCounts.put(
                                "malesAge16to74EconomicallyInactivePermanentlySickOrDisabled",
                                ((Integer) tSARCounts.get("malesAge16to74EconomicallyInactivePermanentlySickOrDisabled")).intValue() + 1);
                        break;
                    default:
                        break;
                }
            }
//            if (AGE0Int >= 50 && AGE0Int <= 74 && (ECONACTInt == 7 || ECONACTInt == 8)) {
//                tSARCounts.put(
//                        "malesAge50AndOverUnemployed",
//                        ((Integer) tSARCounts.get("malesAge50AndOverUnemployed")).intValue() + 1);
//            }
        } else {
            switch (AGE0Int) {
                case 0:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 1:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 2:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 3:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 4:
                    tSARCounts.put(
                            "femalesAge0to4",
                            ((Integer) tSARCounts.get("femalesAge0to4")).intValue() + 1);
                    break;
                case 5:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 6:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 7:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 8:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 9:
                    tSARCounts.put(
                            "femalesAge5to9",
                            ((Integer) tSARCounts.get("femalesAge5to9")).intValue() + 1);
                    break;
                case 10:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 11:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 12:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 13:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 14:
                    tSARCounts.put(
                            "femalesAge10to14",
                            ((Integer) tSARCounts.get("femalesAge10to14")).intValue() + 1);
                    break;
                case 15:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 16:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 17:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 18:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 19:
                    tSARCounts.put(
                            "femalesAge15to19",
                            ((Integer) tSARCounts.get("femalesAge15to19")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge0to15",
                                ((Integer) tSARCounts.get("femalesMarriedAge0to15")).intValue() + 1);
                    }
                    break;
                case 20:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 21:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 22:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 23:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 24:
                    tSARCounts.put(
                            "femalesAge20to24",
                            ((Integer) tSARCounts.get("femalesAge20to24")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge20to24",
                                ((Integer) tSARCounts.get("femalesMarriedAge20to24")).intValue() + 1);
                    }
                    break;
                case 25:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 26:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 27:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 28:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 29:
                    tSARCounts.put(
                            "femalesAge25to29",
                            ((Integer) tSARCounts.get("femalesAge25to29")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge25to29",
                                ((Integer) tSARCounts.get("femalesMarriedAge25to29")).intValue() + 1);
                    }
                    break;
                case 30:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 31:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 32:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 33:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 34:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 35:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 36:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 37:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 38:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 39:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 40:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 41:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 42:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 43:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 44:
                    tSARCounts.put(
                            "femalesAge30to44",
                            ((Integer) tSARCounts.get("femalesAge30to44")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge30to44",
                                ((Integer) tSARCounts.get("femalesMarriedAge30to44")).intValue() + 1);
                    }
                    break;
                case 45:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 46:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 47:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 48:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 49:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 50:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 51:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 52:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 53:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 54:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 55:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 56:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 57:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 58:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 59:
                    tSARCounts.put(
                            "femalesAge45to59",
                            ((Integer) tSARCounts.get("femalesAge45to59")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge45to59",
                                ((Integer) tSARCounts.get("femalesMarriedAge45to59")).intValue() + 1);
                    }
                    break;
                case 60:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 61:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 62:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 63:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 64:
                    tSARCounts.put(
                            "femalesAge60to64",
                            ((Integer) tSARCounts.get("femalesAge60to64")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge60to64",
                                ((Integer) tSARCounts.get("femalesMarriedAge60to64")).intValue() + 1);
                    }
                    break;
                case 65:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 66:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 67:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 68:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 69:
                    tSARCounts.put(
                            "femalesAge65to69",
                            ((Integer) tSARCounts.get("femalesAge65to69")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 70:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 71:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 72:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 73:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 74:
                    tSARCounts.put(
                            "femalesAge70to74",
                            ((Integer) tSARCounts.get("femalesAge70to74")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge65to74",
                                ((Integer) tSARCounts.get("femalesMarriedAge65to74")).intValue() + 1);
                    }
                    break;
                case 75:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 76:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 77:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 78:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                case 79:
                    tSARCounts.put(
                            "femalesAge75to79",
                            ((Integer) tSARCounts.get("femalesAge75to79")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge75to79",
                                ((Integer) tSARCounts.get("femalesMarriedAge75to79")).intValue() + 1);
                    }
                    break;
                default:
                    tSARCounts.put(
                            "femalesAge80AndOver",
                            ((Integer) tSARCounts.get("femalesAge80AndOver")).intValue() + 1);
                    if (married) {
                        tSARCounts.put(
                                "femalesMarriedAge80AndOver",
                                ((Integer) tSARCounts.get("femalesMarriedAge80AndOver")).intValue() + 1);
                    }
                    break;
            }
            if (AGE0Int >= 16 && AGE0Int <= 24 && (ECONACTInt == 7 || ECONACTInt == 8)) {
                tSARCounts.put(
                        "femalesAge16to24Unemployed",
                        ((Integer) tSARCounts.get("femalesAge16to24Unemployed")).intValue() + 1);
            }
            if (AGE0Int >= 16 && AGE0Int <= 74) {
                tSARCounts.put(
                        "femalesAge16to74",
                        ((Integer) tSARCounts.get("femalesAge16to74")).intValue() + 1);
                switch (ECONACTInt) {
                    case 1:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveEmployedFullTime",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveEmployedFullTime")).intValue() + 1);
                        break;
                    case 2:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveEmployedPartTime",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveEmployedPartTime")).intValue() + 1);
                        break;
                    case 3:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 4:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 5:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 6:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveSelfEmployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveSelfEmployed")).intValue() + 1);
                        break;
                    case 7:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 8:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyActiveUnemployed",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyActiveUnemployed")).intValue() + 1);
                        break;
                    case 9:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyInactiveRetired",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyInactiveRetired")).intValue() + 1);
                        break;
                    case 11:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily")).intValue() + 1);
                        break;
                    case 12:
                        tSARCounts.put(
                                "femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled",
                                ((Integer) tSARCounts.get("femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled")).intValue() + 1);
                        break;
                    default:
                        break;
                }
            }
//            if (AGE0Int >= 50 && AGE0Int <= 74 && (ECONACTInt == 7 || ECONACTInt == 8)) {
//                tSARCounts.put(
//                        "femalesAge50AndOverUnemployed",
//                        ((Integer) tSARCounts.get("femalesAge50AndOverUnemployed")).intValue() + 1);
//            }
        }
    }

    /**
     * @param a_CASDataRecord
     * @return Object[] tFitnessCounts:
 tFitnessCounts[0] is a HashMap where;
 keys are Strings
 Keys with values derived from CAS001:
 malesAge0to4
 malesAge5to9
 malesAge10to14
 malesAge15to19
 malesAge20to24
 malesAge25to29
 malesAge30to34
 malesAge35to39
 malesAge40to44
 malesAge45to49
 malesAge50to54
 malesAge55to59
 malesAge60to64
 malesAge65to69
 malesAge70to74
 malesAge75to79
 malesAge80AndOver
 femalesAge0to4
 femalesAge5to9
 femalesAge10to14
 femalesAge15to19
 femalesAge20to24
 femalesAge25to29
 femalesAge30to34
 femalesAge35to39
 femalesAge40to44
 femalesAge45to49
 femalesAge50to54
 femalesAge55to59
 femalesAge60to64
 femalesAge65to69
 femalesAge70to74
 femalesAge75to79
 femalesAge80AndOver

 Keys with values derived from CAS002:
 malesMarriedAge0to15
 malesMarriedAge16to19
 malesMarriedAge20to24
 malesMarriedAge25to29
 malesMarriedAge30to34
 malesMarriedAge35to39
 malesMarriedAge40to44
 malesMarriedAge45to49
 malesMarriedAge50to54
 malesMarriedAge55to59
 malesMarriedAge60to64
 malesMarriedAge65to74
 malesMarriedAge75to79
 malesMarriedAge80AndOver
 femalesMarriedAge0to15
 femalesMarriedAge16to19
 femalesMarriedAge20to24
 femalesMarriedAge25to29
 femalesMarriedAge30to34
 femalesMarriedAge35to39
 femalesMarriedAge40to44
 femalesMarriedAge45to49
 femalesMarriedAge50to54
 femalesMarriedAge55to59
 femalesMarriedAge60to64
 femalesMarriedAge65to74
 femalesMarriedAge75to79
 femalesMarriedAge80AndOver

 Keys with values derived from CASKS008
 peopleWhoseGeneralHealthWasGood
 peopleWhoseGeneralHealthWasFairlyGood
 peopleWhoseGeneralHealthWasNotGood
 peopleWithLimitingLongTermIllness

 Keys with values derived from Census_CASKS020DataRecord
 oneFamilyAndNoChildren
 marriedOrCohabitingCoupleWithChildren
 loneParentHouseholdsWithChildren

 Keys with values derived from Census_CASKS09bDataRecord
 malesAge16to24Unemployed
 malesAge16to74
 malesAge16to74EconomicallyActiveEmployedFullTime
 malesAge16to74EconomicallyActiveEmployedPartTime
 malesAge16to74EconomicallyActiveSelfEmployed
 malesAge16to74EconomicallyActiveUnemployed
 malesAge16to74EconomicallyInactiveRetired
 malesAge16to74EconomicallyInactivePermanentlySickOrDisabled
 malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily
 malesAge50AndOverUnemployed

 Keys with values derived from Census_CASKS09cDataRecord
 femalesAge16to24Unemployed
 femalesAge16to74
 femalesAge16to74EconomicallyActiveEmployedFullTime
 femalesAge16to74EconomicallyActiveEmployedPartTime
 femalesAge16to74EconomicallyActiveSelfEmployed
 femalesAge16to74EconomicallyActiveUnemployed
 femalesAge16to74EconomicallyInactiveRetired
 femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled
 femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily
 femalesAge50AndOverUnemployed

 tFitnessCounts[1] is a HashMap where;
 keys are Strings as in tFitnessCounts[0]
 values are all 0.
     */
    public static Object[] getFitnessCounts(
            Census_CASDataRecord a_CASDataRecord) {
        Object[] result = new Object[2];
        // Initialise Count HashMaps to compare
        HashMap<String, Integer> tCASCounts = new HashMap<String, Integer>();
        HashMap<String, Integer> tSARCounts = new HashMap<String, Integer>();
        // Initialise age gender variables from Census_CAS001DataRecord and
        // Census_CAS002DataRecord
        Census_CAS001DataRecord aCAS001DataRecord = a_CASDataRecord.getCAS001DataRecord();
        Census_CAS002DataRecord aCAS002DataRecord = a_CASDataRecord.getCAS002DataRecord();
        Census_CASKS008DataRecord aCASKS008DataRecord = a_CASDataRecord.getCASKS008DataRecord();
        Census_CASKS020DataRecord aCASKS020DataRecord = a_CASDataRecord.getCASKS020DataRecord();
        Census_CASKS09bDataRecord aCASKS09bDataRecord = a_CASDataRecord.getCASKS09bDataRecord();
        Census_CASKS09cDataRecord aCASKS09cDataRecord = a_CASDataRecord.getCASKS09cDataRecord();
        // Census_CAS001DataRecord
        // males
        String s_malesAge0to4 = "malesAge0to4";
        String s_malesAge5to9 = "malesAge5to9";
        String s_malesAge10to14 = "malesAge10to14";
        String s_malesAge15to19 = "malesAge15to19";
        String s_malesAge20to24 = "malesAge20to24";
        String s_malesAge25to29 = "malesAge25to29";
        String s_malesAge30to44 = "malesAge30to44";
        String s_malesAge45to59 = "malesAge45to59";
        String s_malesAge60to64 = "malesAge60to64";
        String s_malesAge65to69 = "malesAge65to69";
        String s_malesAge70to74 = "malesAge70to74";
        String s_malesAge75to79 = "malesAge75to79";
        String s_malesAge80AndOver = "malesAge80AndOver";
        tCASCounts.put(
                s_malesAge0to4,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge0to4()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge0to4());
        tCASCounts.put(
                s_malesAge5to9,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge5to9()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge5to9());
        tCASCounts.put(
                s_malesAge10to14,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge10to14()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge10to14());
        tCASCounts.put(
                s_malesAge15to19,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge15to19()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge15to19());
        tCASCounts.put(
                s_malesAge20to24,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge20to24()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge20to24());
        tCASCounts.put(
                s_malesAge25to29,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge25to29()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge25to29());
        tCASCounts.put(
                s_malesAge30to44,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge30to34()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge30to34()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge35to39()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge35to39()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge40to44()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge40to44());
        tCASCounts.put(
                s_malesAge45to59,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge45to49()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge45to49());
        tCASCounts.put(
                s_malesAge60to64,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge60to64()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge60to64());
        tCASCounts.put(
                s_malesAge65to69,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge65to69()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge65to69());
        tCASCounts.put(
                s_malesAge70to74,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge70to74()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge70to74());
        tCASCounts.put(
                s_malesAge75to79,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge75to79()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge75to79());
        tCASCounts.put(
                s_malesAge80AndOver,
                aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge90AndOver()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge90AndOver()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge80to84()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge80to84()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge85to89()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge85to89()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsMalesAge90AndOver()
                + aCAS001DataRecord.getHouseholdResidentsMalesAge90AndOver());
        tSARCounts.put(s_malesAge0to4, 0);
        tSARCounts.put(s_malesAge5to9, 0);
        tSARCounts.put(s_malesAge10to14, 0);
        tSARCounts.put(s_malesAge15to19, 0);
        tSARCounts.put(s_malesAge20to24, 0);
        tSARCounts.put(s_malesAge25to29, 0);
        tSARCounts.put(s_malesAge30to44, 0);
        tSARCounts.put(s_malesAge45to59, 0);
        tSARCounts.put(s_malesAge60to64, 0);
        tSARCounts.put(s_malesAge65to69, 0);
        tSARCounts.put(s_malesAge70to74, 0);
        tSARCounts.put(s_malesAge75to79, 0);
        tSARCounts.put(s_malesAge80AndOver, 0);
        // females
        String s_femalesAge0to4 = "femalesAge0to4";
        String s_femalesAge5to9 = "femalesAge5to9";
        String s_femalesAge10to14 = "femalesAge10to14";
        String s_femalesAge15to19 = "femalesAge15to19";
        String s_femalesAge20to24 = "femalesAge20to24";
        String s_femalesAge25to29 = "femalesAge25to29";
        String s_femalesAge30to44 = "femalesAge30to44";
        String s_femalesAge45to59 = "femalesAge45to59";
        String s_femalesAge60to64 = "femalesAge60to64";
        String s_femalesAge65to69 = "femalesAge65to69";
        String s_femalesAge70to74 = "femalesAge70to74";
        String s_femalesAge75to79 = "femalesAge75to79";
        String s_femalesAge80AndOver = "femalesAge80AndOver";
        tCASCounts.put(
                s_femalesAge0to4,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge0to4()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge0to4());
        tCASCounts.put(
                s_femalesAge5to9,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge5to9()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge5to9());
        tCASCounts.put(
                s_femalesAge10to14,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge10to14()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge10to14());
        tCASCounts.put(
                s_femalesAge15to19,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge15to19()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge15to19());
        tCASCounts.put(
                s_femalesAge20to24,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge20to24()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge20to24());
        tCASCounts.put(
                s_femalesAge25to29,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge25to29()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge25to29());
        tCASCounts.put(
                s_femalesAge30to44,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge30to34()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge30to34()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge35to39()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge35to39()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge40to44()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge40to44());
        tCASCounts.put(
                s_femalesAge45to59,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge45to49()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge45to49()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge50to54()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge50to54()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge55to59()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge55to59());
        tCASCounts.put(
                s_femalesAge60to64,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge60to64()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge60to64());
        tCASCounts.put(
                s_femalesAge65to69,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge65to69()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge65to69());
        tCASCounts.put(
                s_femalesAge70to74,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge70to74()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge70to74());
        tCASCounts.put(
                s_femalesAge75to79,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge75to79()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge75to79());
        tCASCounts.put(
                s_femalesAge80AndOver,
                aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge80to84()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge80to84()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge85to89()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge85to89()
                + aCAS001DataRecord.getCommunalEstablishmentResidentsFemalesAge90AndOver()
                + aCAS001DataRecord.getHouseholdResidentsFemalesAge90AndOver());
        tSARCounts.put(s_femalesAge0to4, 0);
        tSARCounts.put(s_femalesAge5to9, 0);
        tSARCounts.put(s_femalesAge10to14, 0);
        tSARCounts.put(s_femalesAge15to19, 0);
        tSARCounts.put(s_femalesAge20to24, 0);
        tSARCounts.put(s_femalesAge25to29, 0);
        tSARCounts.put(s_femalesAge30to44, 0);
        tSARCounts.put(s_femalesAge45to59, 0);
        tSARCounts.put(s_femalesAge60to64, 0);
        tSARCounts.put(s_femalesAge65to69, 0);
        tSARCounts.put(s_femalesAge70to74, 0);
        tSARCounts.put(s_femalesAge75to79, 0);
        tSARCounts.put(s_femalesAge80AndOver, 0);
        // Census_CAS002DataRecord
        // males
        String s_malesMarriedAge0to15 = "malesMarriedAge0to15";
        String s_malesMarriedAge16to19 = "malesMarriedAge16to19";
        String s_malesMarriedAge20to24 = "malesMarriedAge20to24";
        String s_malesMarriedAge25to29 = "malesMarriedAge25to29";
        String s_malesMarriedAge30to44 = "malesMarriedAge30to44";
        String s_malesMarriedAge45to59 = "malesMarriedAge45to59";
        String s_malesMarriedAge60to64 = "malesMarriedAge60to64";
        String s_malesMarriedAge65to74 = "malesMarriedAge65to74";
        String s_malesMarriedAge75to79 = "malesMarriedAge75to79";
        String s_malesMarriedAge80AndOver = "malesMarriedAge80AndOver";
        tCASCounts.put(
                s_malesMarriedAge0to15,
                aCAS002DataRecord.getMalesMarriedAge0to15());
        tCASCounts.put(
                s_malesMarriedAge16to19,
                aCAS002DataRecord.getMalesMarriedAge16to19());
        tCASCounts.put(
                s_malesMarriedAge20to24,
                aCAS002DataRecord.getMalesMarriedAge20to24());
        tCASCounts.put(
                s_malesMarriedAge25to29,
                aCAS002DataRecord.getMalesMarriedAge25to29());
        tCASCounts.put(
                s_malesMarriedAge30to44,
                aCAS002DataRecord.getMalesMarriedAge30to34()
                + aCAS002DataRecord.getMalesMarriedAge35to39()
                + aCAS002DataRecord.getMalesMarriedAge40to44());
        tCASCounts.put(
                s_malesMarriedAge45to59,
                aCAS002DataRecord.getMalesMarriedAge45to49()
                + aCAS002DataRecord.getMalesMarriedAge50to54()
                + aCAS002DataRecord.getMalesMarriedAge55to59());
        tCASCounts.put(
                s_malesMarriedAge60to64,
                aCAS002DataRecord.getMalesMarriedAge60to64());
        tCASCounts.put(
                s_malesMarriedAge65to74,
                aCAS002DataRecord.getMalesMarriedAge65to74());
        tCASCounts.put(
                s_malesMarriedAge75to79,
                aCAS002DataRecord.getMalesMarriedAge75to79());
        tCASCounts.put(
                s_malesMarriedAge80AndOver,
                aCAS002DataRecord.getMalesMarriedAge80to84()
                + aCAS002DataRecord.getMalesMarriedAge85to89()
                + aCAS002DataRecord.getMalesMarriedAge90AndOver());
        tSARCounts.put(s_malesMarriedAge0to15, 0);
        tSARCounts.put(s_malesMarriedAge16to19, 0);
        tSARCounts.put(s_malesMarriedAge20to24, 0);
        tSARCounts.put(s_malesMarriedAge25to29, 0);
        tSARCounts.put(s_malesMarriedAge30to44, 0);
        tSARCounts.put(s_malesMarriedAge45to59, 0);
        tSARCounts.put(s_malesMarriedAge60to64, 0);
        tSARCounts.put(s_malesMarriedAge65to74, 0);
        tSARCounts.put(s_malesMarriedAge75to79, 0);
        // _SARCounts.put( s_malesMarriedAge80to84, 0 );
        // _SARCounts.put( s_malesMarriedAge85to89, 0 );
        // _SARCounts.put( s_malesMarriedAge90AndOver, 0 );
        tSARCounts.put(s_malesMarriedAge80AndOver, 0);
        // females
        String s_femalesMarriedAge0to15 = "femalesMarriedAge0to15";
        String s_femalesMarriedAge16to19 = "femalesMarriedAge16to19";
        String s_femalesMarriedAge20to24 = "femalesMarriedAge20to24";
        String s_femalesMarriedAge25to29 = "femalesMarriedAge25to29";
        String s_femalesMarriedAge30to44 = "femalesMarriedAge30to44";
        String s_femalesMarriedAge45to59 = "femalesMarriedAge45to59";
        String s_femalesMarriedAge60to64 = "femalesMarriedAge60to64";
        String s_femalesMarriedAge65to74 = "femalesMarriedAge65to74";
        String s_femalesMarriedAge75to79 = "femalesMarriedAge75to79";
        String s_femalesMarriedAge80AndOver = "femalesMarriedAge80AndOver";
        tCASCounts.put(
                s_femalesMarriedAge0to15,
                aCAS002DataRecord.getFemalesMarriedAge0to15());
        tCASCounts.put(
                s_femalesMarriedAge16to19,
                aCAS002DataRecord.getFemalesMarriedAge16to19());
        tCASCounts.put(
                s_femalesMarriedAge20to24,
                aCAS002DataRecord.getFemalesMarriedAge20to24());
        tCASCounts.put(
                s_femalesMarriedAge25to29,
                aCAS002DataRecord.getFemalesMarriedAge25to29());
        tCASCounts.put(
                s_femalesMarriedAge30to44,
                aCAS002DataRecord.getFemalesMarriedAge30to34()
                + aCAS002DataRecord.getFemalesMarriedAge35to39()
                + aCAS002DataRecord.getFemalesMarriedAge40to44());
        tCASCounts.put(
                s_femalesMarriedAge45to59,
                aCAS002DataRecord.getFemalesMarriedAge45to49()
                + aCAS002DataRecord.getFemalesMarriedAge50to54()
                + aCAS002DataRecord.getFemalesMarriedAge55to59());
        tCASCounts.put(
                s_femalesMarriedAge60to64,
                aCAS002DataRecord.getFemalesMarriedAge60to64());
        tCASCounts.put(
                s_femalesMarriedAge65to74,
                aCAS002DataRecord.getFemalesMarriedAge65to74());
        tCASCounts.put(
                s_femalesMarriedAge75to79,
                aCAS002DataRecord.getFemalesMarriedAge75to79());
        tCASCounts.put(
                s_femalesMarriedAge80AndOver,
                aCAS002DataRecord.getFemalesMarriedAge80to84()
                + aCAS002DataRecord.getFemalesMarriedAge85to89()
                + aCAS002DataRecord.getFemalesMarriedAge90AndOver());
        tSARCounts.put(s_femalesMarriedAge0to15, 0);
        tSARCounts.put(s_femalesMarriedAge16to19, 0);
        tSARCounts.put(s_femalesMarriedAge20to24, 0);
        tSARCounts.put(s_femalesMarriedAge25to29, 0);
        tSARCounts.put(s_femalesMarriedAge30to44, 0);
        tSARCounts.put(s_femalesMarriedAge45to59, 0);
        tSARCounts.put(s_femalesMarriedAge60to64, 0);
        tSARCounts.put(s_femalesMarriedAge65to74, 0);
        tSARCounts.put(s_femalesMarriedAge75to79, 0);
        tSARCounts.put(s_femalesMarriedAge80AndOver, 0);
        // Initialise health variables from Census_CASKS008DataRecord
        String s_peopleWhoseGeneralHealthWasGood = "peopleWhoseGeneralHealthWasGood";
        String s_peopleWhoseGeneralHealthWasFairlyGood = "peopleWhoseGeneralHealthWasFairlyGood";
        String s_peopleWhoseGeneralHealthWasNotGood = "peopleWhoseGeneralHealthWasNotGood";
        String s_peopleWithLimitingLongTermIllness = "peopleWithLimitingLongTermIllness";
        tCASCounts.put(
                s_peopleWhoseGeneralHealthWasGood,
                aCASKS008DataRecord.getPeopleWhoseGeneralHealthWasGood());
        tCASCounts.put(
                s_peopleWhoseGeneralHealthWasFairlyGood,
                aCASKS008DataRecord.getPeopleWhoseGeneralHealthWasFairlyGood());
        tCASCounts.put(
                s_peopleWhoseGeneralHealthWasNotGood,
                aCASKS008DataRecord.getPeopleWhoseGeneralHealthWasNotGood());
        tCASCounts.put(
                s_peopleWithLimitingLongTermIllness,
                aCASKS008DataRecord.getPeopleWithLimitingLongTermIllness());
        tSARCounts.put(s_peopleWhoseGeneralHealthWasGood, 0);
        tSARCounts.put(s_peopleWhoseGeneralHealthWasFairlyGood, 0);
        tSARCounts.put(s_peopleWhoseGeneralHealthWasNotGood, 0);
        tSARCounts.put(s_peopleWithLimitingLongTermIllness, 0);
        // Initialise Household Composition variables from
        // Census_CASKS020DataRecord
        String s_oneFamilyAndNoChildren = "oneFamilyAndNoChildren";
        String s_marriedOrCohabitingCoupleWithChildren = "marriedOrCohabitingCoupleWithChildren";
        String s_loneParentHouseholdsWithChildren = "loneParentHouseholdsWithChildren";
        tCASCounts.put(
                s_oneFamilyAndNoChildren,
                aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersAllPensioners() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersCohabitingCoupleHouseholdsNoChildren() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersMarriedCoupleHouseholdsNoChildren());
        tCASCounts.put(
                s_marriedOrCohabitingCoupleWithChildren,
                aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersCohabitingCoupleHouseholdsAllChildrenNonDependent() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersCohabitingCoupleHouseholdsWithDependentChildren() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersMarriedCoupleHouseholdsAllChildrenNonDependent() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersMarriedCoupleHouseholdsWithDependentChildren());
        tCASCounts.put(
                s_loneParentHouseholdsWithChildren,
                aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersLoneParentHouseholdsAllChildrenNonDependent() + aCASKS020DataRecord.getHouseholdsComprisingOneFamilyAndNoOthersLoneParentHouseholdsWithDependentChildren());
        tSARCounts.put(s_oneFamilyAndNoChildren, 0);
        tSARCounts.put(s_marriedOrCohabitingCoupleWithChildren, 0);
        tSARCounts.put(s_loneParentHouseholdsWithChildren, 0);
        // Initialise Employment variables from Census_CASKS09bDataRecord
        String s_malesAge16to24Unemployed = "malesAge16to24Unemployed";
        String s_malesAge16to74 = "malesAge16to74";
        String s_malesAge16to74EconomicallyActiveEmployedFullTime = "malesAge16to74EconomicallyActiveEmployedFullTime";
        String s_malesAge16to74EconomicallyActiveEmployedPartTime = "malesAge16to74EconomicallyActiveEmployedPartTime";
        String s_malesAge16to74EconomicallyActiveSelfEmployed = "malesAge16to74EconomicallyActiveSelfEmployed";
        String s_malesAge16to74EconomicallyActiveUnemployed = "malesAge16to74EconomicallyActiveUnemployed";
        String s_malesAge16to74EconomicallyInactiveRetired = "malesAge16to74EconomicallyInactiveRetired";
        String s_malesAge16to74EconomicallyInactivePermanentlySickOrDisabled = "malesAge16to74EconomicallyInactivePermanentlySickOrDisabled";
        String s_malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily = "malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily";
        String s_malesAge50AndOverUnemployed = "malesAge50AndOverUnemployed";
        tCASCounts.put(
                s_malesAge16to24Unemployed,
                aCASKS09bDataRecord.getMalesAged16to24Unemployed());
        tCASCounts.put(
                s_malesAge16to74,
                aCASKS09bDataRecord.getMalesAged16to74());
        tCASCounts.put(
                s_malesAge16to74EconomicallyActiveEmployedFullTime,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyActiveEmployeesFullTime());
        tCASCounts.put(
                s_malesAge16to74EconomicallyActiveEmployedPartTime,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyActiveEmployeesPartTime());
        tCASCounts.put(
                s_malesAge16to74EconomicallyActiveSelfEmployed,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyActiveSelfEmployed());
        tCASCounts.put(
                s_malesAge16to74EconomicallyActiveUnemployed,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyActiveUnemployed());
        tCASCounts.put(
                s_malesAge16to74EconomicallyInactiveRetired,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyInactiveRetired());
        tCASCounts.put(
                s_malesAge16to74EconomicallyInactivePermanentlySickOrDisabled,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyInactivePermanentlySickOrDisabled());
        tCASCounts.put(
                s_malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily,
                aCASKS09bDataRecord.getMalesAged16to74EconomicallyInactiveLookingAfterHomeOrFamily());
        tCASCounts.put(
                s_malesAge50AndOverUnemployed,
                aCASKS09bDataRecord.getMalesAged50AndOverUnemployed());
        tSARCounts.put(s_malesAge16to24Unemployed, 0);
        tSARCounts.put(s_malesAge16to74, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyActiveEmployedFullTime, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyActiveEmployedPartTime, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyActiveSelfEmployed, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyActiveUnemployed, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyInactiveRetired, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyInactivePermanentlySickOrDisabled, 0);
        tSARCounts.put(s_malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily, 0);
        tSARCounts.put(s_malesAge50AndOverUnemployed, 0);
        // Initialise Employment variables from Census_CASKS09cDataRecord
        String s_femalesAge16to24Unemployed = "femalesAge16to24Unemployed";
        String s_femalesAge16to74 = "femalesAge16to74";
        String s_femalesAge16to74EconomicallyActiveEmployedFullTime = "femalesAge16to74EconomicallyActiveEmployedFullTime";
        String s_femalesAge16to74EconomicallyActiveEmployedPartTime = "femalesAge16to74EconomicallyActiveEmployedPartTime";
        String s_femalesAge16to74EconomicallyActiveSelfEmployed = "femalesAge16to74EconomicallyActiveSelfEmployed";
        String s_femalesAge16to74EconomicallyActiveUnemployed = "femalesAge16to74EconomicallyActiveUnemployed";
        String s_femalesAge16to74EconomicallyInactiveRetired = "femalesAge16to74EconomicallyInactiveRetired";
        String s_femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled = "femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled";
        String s_femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily = "femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily";
        String s_femalesAge50AndOverUnemployed = "femalesAge50AndOverUnemployed";
        tCASCounts.put(
                s_femalesAge16to24Unemployed,
                aCASKS09cDataRecord.getFemalesAged16to24Unemployed());
        tCASCounts.put(
                s_femalesAge16to74,
                aCASKS09cDataRecord.getFemalesAged16to74());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyActiveEmployedFullTime,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyActiveEmployeesFullTime());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyActiveEmployedPartTime,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyActiveEmployeesPartTime());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyActiveSelfEmployed,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyActiveSelfEmployed());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyActiveUnemployed,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyActiveUnemployed());
        tCASCounts.put(s_femalesAge16to74EconomicallyInactiveRetired,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyInactiveRetired());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyInactivePermanentlySickOrDisabled());
        tCASCounts.put(
                s_femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily,
                aCASKS09cDataRecord.getFemalesAged16to74EconomicallyInactiveLookingAfterHomeOrFamily());
        tCASCounts.put(
                s_femalesAge50AndOverUnemployed,
                aCASKS09cDataRecord.getFemalesAged50AndOverUnemployed());
        tSARCounts.put(s_femalesAge16to24Unemployed, 0);
        tSARCounts.put(s_femalesAge16to74, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyActiveEmployedFullTime, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyActiveEmployedPartTime, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyActiveSelfEmployed, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyActiveUnemployed, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyInactiveRetired, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled, 0);
        tSARCounts.put(s_femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily, 0);
        tSARCounts.put(s_femalesAge50AndOverUnemployed, 0);
        result[0] = tCASCounts;
        result[1] = tSARCounts;
        return result;
    }

    public Object[] getFitnessCounts() {
        if (this._FitnessCounts == null) {
            return getFitnessCounts(this._CASDataRecord);
        }
        return this._FitnessCounts;
    }

    public static String[] getVariables() {
        Vector<String> variables = getVariableList();
        String[] result = null;
        result = variables.toArray(result);
        return result;
    }

    public static Vector<String> getVariableList() {
        Vector<String> result = new Vector<String>();
        result.add("malesAge0to4");
        result.add("malesAge5to9");
        result.add("malesAge10to14");
        result.add("malesAge15to19");
        result.add("malesAge20to24");
        result.add("malesAge25to29");
        result.add("malesAge30to44");
        result.add("malesAge45to59");
        result.add("malesAge60to64");
        result.add("malesAge65to69");
        result.add("malesAge70to74");
        result.add("malesAge75to79");
        result.add("malesAge80AndOver");
        result.add("femalesAge0to4");
        result.add("femalesAge5to9");
        result.add("femalesAge10to14");
        result.add("femalesAge15to19");
        result.add("femalesAge20to24");
        result.add("femalesAge25to29");
        result.add("femalesAge30to44");
        result.add("femalesAge45to59");
        result.add("femalesAge60to64");
        result.add("femalesAge65to69");
        result.add("femalesAge70to74");
        result.add("femalesAge75to79");
        result.add("femalesAge80AndOver");
        result.add("malesMarriedAge0to15");
        result.add("malesMarriedAge16to19");
        result.add("malesMarriedAge20to24");
        result.add("malesMarriedAge25to29");
        result.add("malesMarriedAge30to44");
        result.add("malesMarriedAge45to59");
        result.add("malesMarriedAge60to64");
        result.add("malesMarriedAge65to74");
        result.add("malesMarriedAge75to79");
        result.add("malesMarriedAge80AndOver");
        result.add("femalesMarriedAge0to15");
        result.add("femalesMarriedAge16to19");
        result.add("femalesMarriedAge20to24");
        result.add("femalesMarriedAge25to29");
        result.add("femalesMarriedAge30to44");
        result.add("femalesMarriedAge45to59");
        result.add("femalesMarriedAge60to64");
        result.add("femalesMarriedAge65to74");
        result.add("femalesMarriedAge75to79");
        result.add("femalesMarriedAge80AndOver");
        result.add("peopleWhoseGeneralHealthWasGood");
        result.add("peopleWhoseGeneralHealthWasFairlyGood");
        result.add("peopleWhoseGeneralHealthWasNotGood");
        result.add("peopleWithLimitingLongTermIllness");
        result.add("oneFamilyAndNoChildren");
        result.add("marriedOrCohabitingCoupleWithChildren");
        result.add("loneParentHouseholdsWithChildren");
        result.add("malesAge16to24Unemployed");
        result.add("malesAge16to74");
        result.add("malesAge16to74EconomicallyActiveEmployedFullTime");
        result.add("malesAge16to74EconomicallyActiveEmployedPartTime");
        result.add("malesAge16to74EconomicallyActiveSelfEmployed");
        result.add("malesAge16to74EconomicallyActiveUnemployed");
        result.add("malesAge16to74EconomicallyInactiveRetired");
        result.add("malesAge16to74EconomicallyInactivePermanentlySickOrDisabled");
        result.add("malesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily");
        //result.add("malesAge50AndOverUnemployed");
        result.add("femalesAge16to24Unemployed");
        result.add("femalesAge16to74");
        result.add("femalesAge16to74EconomicallyActiveEmployedFullTime");
        result.add("femalesAge16to74EconomicallyActiveEmployedPartTime");
        result.add("femalesAge16to74EconomicallyActiveSelfEmployed");
        result.add("femalesAge16to74EconomicallyActiveUnemployed");
        result.add("femalesAge16to74EconomicallyInactiveRetired");
        result.add("femalesAge16to74EconomicallyInactivePermanentlySickOrDisabled");
        result.add("femalesAge16to74EconomicallyInactiveLookingAfterHomeOrFamily");
        //result.add("femalesAge50AndOverUnemployed");
        return result;
    }
}
