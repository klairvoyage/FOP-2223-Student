package projekt.delivery.runner;

import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.archetype.ProblemGroup;
import projekt.delivery.rating.RatingCriteria;
import projekt.delivery.service.DeliveryService;
import projekt.delivery.simulation.Simulation;
import projekt.delivery.simulation.SimulationConfig;

import java.util.HashMap;
import java.util.Map;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * A basic {@link Runner} that only executes the simulation and returns the result.
 */
public class BasicRunner extends AbstractRunner {

    @Override
    public Map<RatingCriteria, Double> run(ProblemGroup problemGroup,
                                           SimulationConfig simulationConfig,
                                           int simulationRuns,
                                           DeliveryService.Factory deliveryServiceFactory) {
        /*//if(simulationRuns==0) return null;      // not needed?
        Map<ProblemArchetype, Simulation> simulations=createSimulations(problemGroup,simulationConfig,deliveryServiceFactory);
        Map<RatingCriteria,Double> ratings=new HashMap<>();
        for(RatingCriteria rating:problemGroup.ratingCriteria()) ratings.put(rating,new Double(0));
        int count=0;
        double tmp;
        for(int i=0;i<simulationRuns;i++) for(ProblemArchetype problem:simulations.keySet()) {
            count++;
            simulations.get(problem).runSimulation(problem.simulationLength());
            for(RatingCriteria rating:problemGroup.ratingCriteria()){
                tmp=ratings.get(rating);
                ratings.remove(rating);
                tmp+=simulations.get(problem).getRatingForCriterion(rating);
                ratings.put(rating,tmp);
            }
        }
        for(RatingCriteria rating:problemGroup.ratingCriteria()){
            tmp=ratings.get(rating);
            ratings.remove(rating);
            ratings.put(rating,tmp/count);
        }
        return ratings;*/
        return crash();
    }
}
