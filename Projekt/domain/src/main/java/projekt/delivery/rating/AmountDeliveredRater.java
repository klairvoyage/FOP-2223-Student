package projekt.delivery.rating;

import projekt.delivery.event.DeliverOrderEvent;
import projekt.delivery.event.Event;
import projekt.delivery.event.OrderReceivedEvent;
import projekt.delivery.simulation.Simulation;

import java.util.List;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Rates the observed {@link Simulation} based on the amount of delivered orders.<p>
 *
 * To create a new {@link AmountDeliveredRater} use {@code AmountDeliveredRater.Factory.builder()...build();}.
 */
public class AmountDeliveredRater implements Rater {

    public static final RatingCriteria RATING_CRITERIA = RatingCriteria.AMOUNT_DELIVERED;

    private final double factor;

    private int totalOrders=0, deliveredOrders=0;
    public final double factorPublic;

    private AmountDeliveredRater(double factor) {
        this.factor = factor;
        factorPublic=factor;
    }

    @Override
    public double getScore() {
        int undeliveredOrders=totalOrders-deliveredOrders;
        if(undeliveredOrders==0) return 1;                      //ACHTUNG: DAS WIDERSPRICHT DER AUFGABENSTELLUNG, IST ABER SINNIG
        if(0<undeliveredOrders&&undeliveredOrders<totalOrders*(1-factor))
            return 1-(double)undeliveredOrders/(totalOrders*(1-factor));
        return 0;
    }

    @Override
    public RatingCriteria getRatingCriteria() {
        return RATING_CRITERIA;
    }

    @Override
    public void onTick(List<Event> events, long tick) {
        for(Event event:events) if(event instanceof DeliverOrderEvent) deliveredOrders++;
                                else if(event instanceof OrderReceivedEvent) totalOrders++;
    }

    /**
     * A {@link Rater.Factory} for creating a new {@link AmountDeliveredRater}.
     */
    public static class Factory implements Rater.Factory {

        public final double factor;

        private Factory(double factor) {
            this.factor = factor;
        }

        @Override
        public Rater create() {
            return new AmountDeliveredRater(factor);
        }

        /**
         * Creates a new {@link AmountDeliveredRater.FactoryBuilder}.
         * @return The created {@link AmountDeliveredRater.FactoryBuilder}.
         */
        public static AmountDeliveredRater.FactoryBuilder builder() {
            return new AmountDeliveredRater.FactoryBuilder();
        }
    }

    /**
     * A {@link Rater.FactoryBuilder} form constructing a new {@link AmountDeliveredRater.Factory}.
     */
    public static class FactoryBuilder implements Rater.FactoryBuilder {

        public double factor = 0.99;

        private FactoryBuilder() {}

        @Override
        public Rater.Factory build() {
            return new Factory(factor);
        }

        public FactoryBuilder setFactor(double factor) {
            if (factor < 0 || factor > 1) {
                throw new IllegalArgumentException("factor must be between 0 and 1");
            }

            this.factor = factor;
            return this;
        }
    }
}
