package projekt.runner;

import projekt.delivery.archetype.ProblemArchetype;
import projekt.delivery.archetype.ProblemGroup;
import projekt.delivery.rating.RatingCriteria;
import projekt.delivery.service.DeliveryService;
import projekt.delivery.simulation.BasicDeliverySimulation;
import projekt.delivery.simulation.Simulation;
import projekt.delivery.simulation.SimulationConfig;
import projekt.runner.handler.ResultHandler;
import projekt.runner.handler.SimulationFinishedHandler;
import projekt.runner.handler.SimulationSetupHandler;

import java.util.HashMap;
import java.util.Map;

import static org.tudalgo.algoutils.student.Student.crash;

public class RunnerImpl implements Runner {

    @Override
    public void run(ProblemGroup problemGroup,
                    SimulationConfig simulationConfig,
                    int simulationRuns,
                    DeliveryService.Factory deliveryServiceFactory,
                    SimulationSetupHandler simulationSetupHandler,
                    SimulationFinishedHandler simulationFinishedHandler,
                    ResultHandler resultHandler) {

        //if(simulationRuns==0) return null;      // not needed?
        Map<ProblemArchetype, Simulation> simulations=createSimulations(problemGroup,simulationConfig,deliveryServiceFactory);
        Map<RatingCriteria,Double> ratings=new HashMap<>();
        for(RatingCriteria rating:problemGroup.ratingCriteria()) ratings.put(rating,new Double(0));
        int count=0;
        double tmp;
        for(int i=0;i<simulationRuns;i++) for(ProblemArchetype problem:simulations.keySet()) {
            count++;
            simulationSetupHandler.accept(simulations.get(problem),problem,i);
            simulations.get(problem).runSimulation(problem.simulationLength());
            simulationFinishedHandler.accept(simulations.get(problem),problem);
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
       resultHandler.accept(ratings);
    }

    /**
     * Creates a {@link Map} that maps each {@link ProblemArchetype} of the given {@link ProblemGroup} to a
     * {@link BasicDeliverySimulation} that simulates the {@link ProblemArchetype}.
     *
     * @param problemGroup           The {@link ProblemGroup} to create {@link BasicDeliverySimulation}s for.
     * @param simulationConfig       The {@link SimulationConfig} used to create the {@link BasicDeliverySimulation}s.
     * @param deliveryServiceFactory The {@link DeliveryService.Factory} used to create the {@link DeliveryService}s for the {@link BasicDeliverySimulation}s.
     * @return The created {@link Map} from {@link ProblemArchetype} to {@link BasicDeliverySimulation}.
     */
    private Map<ProblemArchetype, Simulation> createSimulations(ProblemGroup problemGroup,
                                                                SimulationConfig simulationConfig,
                                                                DeliveryService.Factory deliveryServiceFactory) {

        Map<ProblemArchetype,Simulation> map=new HashMap<>();
        for(ProblemArchetype problem:problemGroup.problems()) map.put(problem,
            new BasicDeliverySimulation(simulationConfig,problem.raterFactoryMap(),
                deliveryServiceFactory.create(problem.vehicleManager()),problem.orderGeneratorFactory()));
        return map;
    }

}
