package projekt.delivery.rating;

import projekt.delivery.event.DeliverOrderEvent;
import projekt.delivery.event.Event;
import projekt.delivery.event.OrderReceivedEvent;
import projekt.delivery.routing.ConfirmedOrder;
import projekt.delivery.simulation.Simulation;

import java.util.List;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Rates the observed {@link Simulation} based on the punctuality of the orders.<p>
 *
 * To create a new {@link InTimeRater} use {@code InTimeRater.Factory.builder()...build();}.
 */
public class InTimeRater implements Rater {

    public static final RatingCriteria RATING_CRITERIA = RatingCriteria.IN_TIME;

    private final long ignoredTicksOff;
    private final long maxTicksOff;
    public final long ignoredTicksOffPublic,maxTicksOffPublic;
    private long maxTotalTicksOff=0,actualTotalTicksOff=0;
    /**
     * Creates a new {@link InTimeRater} instance.
     * @param ignoredTicksOff The amount of ticks this {@link InTimeRater} ignores when dealing with an {@link ConfirmedOrder} that didn't get delivered in time.
     * @param maxTicksOff The maximum amount of ticks too late/early this {@link InTimeRater} considers.
     */
    private InTimeRater(long ignoredTicksOff, long maxTicksOff) {
        if (ignoredTicksOff < 0) throw new IllegalArgumentException(String.valueOf(ignoredTicksOff));
        if (maxTicksOff <= 0) throw new IllegalArgumentException(String.valueOf(maxTicksOff));

        this.ignoredTicksOff = ignoredTicksOff;
        this.maxTicksOff = maxTicksOff;
        ignoredTicksOffPublic=ignoredTicksOff;
        maxTicksOffPublic=maxTicksOff;
    }

    @Override
    public double getScore() {
        if(maxTicksOff==0) return 0;
        return 1-(double)actualTotalTicksOff/maxTotalTicksOff;
    }

    @Override
    public void onTick(List<Event> events, long tick) {
        long start,end;
        // TODO: what about too early deliveries?
        for(Event event:events) if(event instanceof DeliverOrderEvent del) {
            actualTotalTicksOff-=maxTicksOff;
            start=del.getOrder().getDeliveryInterval().start();
            end=del.getOrder().getDeliveryInterval().end();
            if(tick>end+ignoredTicksOff+maxTicksOff) actualTotalTicksOff+=maxTicksOff;
            else if(tick>end+ignoredTicksOff) actualTotalTicksOff+=tick-end-ignoredTicksOff;
            else if(tick<start-ignoredTicksOff) actualTotalTicksOff+=start-ignoredTicksOff-tick;
            else if(tick<start-ignoredTicksOff-maxTicksOff) actualTotalTicksOff+=maxTicksOff;
        }
        else if(event instanceof OrderReceivedEvent) {
            maxTotalTicksOff+=maxTicksOff;
            actualTotalTicksOff+=maxTicksOff;
        }
    }

    /**
     * A {@link Rater.Factory} for creating a new {@link InTimeRater}.
     */
    @Override
    public RatingCriteria getRatingCriteria() {
        return RATING_CRITERIA;
    }

    public static class Factory implements Rater.Factory {

        public final long ignoredTicksOff;
        public final long maxTicksOff;

        private Factory(long ignoredTicksOff, long maxTicksOff) {
            this.ignoredTicksOff = ignoredTicksOff;
            this.maxTicksOff = maxTicksOff;
        }

        @Override
        public Rater create() {
            return new InTimeRater(ignoredTicksOff, maxTicksOff);
        }

        /**
         * Creates a new {@link InTimeRater.FactoryBuilder}.
         * @return The created {@link InTimeRater.FactoryBuilder}.
         */
        public static InTimeRater.FactoryBuilder builder() {
            return new InTimeRater.FactoryBuilder();
        }
    }

    /**
     * A {@link Rater.FactoryBuilder} form constructing a new {@link InTimeRater.Factory}.
     */
    public static class FactoryBuilder implements Rater.FactoryBuilder {

        public long ignoredTicksOff = 5;
        public long maxTicksOff = 25;

        private FactoryBuilder() {}

        public FactoryBuilder setIgnoredTicksOff(long ignoredTicksOff) {
            this.ignoredTicksOff = ignoredTicksOff;
            return this;
        }

        public FactoryBuilder setMaxTicksOff(long maxTicksOff) {
            this.maxTicksOff = maxTicksOff;
            return this;
        }

        @Override
        public Rater.Factory build() {
            return new Factory(ignoredTicksOff, maxTicksOff);
        }
    }
}
