package h10;

import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Objects;

import static org.tudalgo.algoutils.student.Student.crash;

/**
 * Represents a skip list. A skip list is a randomized data structure that allows fast access to elements in a sorted
 * list.
 *
 * <p>Its a two-dimensional linked list where the lowest level contains all elements and each higher level contains a
 * subset of the elements of the lower level.
 *
 * <p>Example:
 * <pre>{@code
 *  head ----------------- 47
 *   |                     |
 *   |------ 12 --------- 47
 *   |       |            |
 *   |------ 12 -------- 47 -- 72
 *   |       |           |    |
 *   | 5 -- 12 -- 17 -- 47 -- 72 -- 98
 * }</pre>
 *
 * @param <T> the type of the elements in this list
 *
 * @author Nhan Huynh
 * @see <a href="https://en.wikipedia.org/wiki/Skip_list">Skip list</a>
 */
public class SkipList<T> {

    /**
     * The comparator used to maintain order in this list.
     */
    protected final Comparator<? super T> cmp;

    /**
     * The maximum height of the skip list.
     */
    final int maxHeight;

    /**
     * The probability function used to determine if a node should be added on another level.
     */
    private Probability probability;

    /**
     * The head of the skip list.
     */
    @Nullable ListItem<ExpressNode<T>> head;

    /**
     * The current height of the skip list.
     */
    int height = 0;

    /**
     * The number of items in the skip list.
     */
    int size = 0;

    /**
     * Constructs and initializes an empty skip list without the probability to add elements on higher levels.
     *
     * @param cmp       the comparator used to maintain order in this list
     * @param maxHeight the maximum height of the skip list
     */
    public SkipList(Comparator<? super T> cmp, int maxHeight) {
        this(cmp, maxHeight, DEFAULT);
    }

    /**
     * Constructs and initializes an empty skip list.
     *
     * @param cmp         the comparator used to maintain order in this list
     * @param maxHeight   the maximum height of the skip list
     * @param probability the probability function used to determine if a node should be added on another level
     */
    public SkipList(Comparator<? super T> cmp, int maxHeight, Probability probability) {
        this.cmp = cmp;
        this.maxHeight = maxHeight;
        this.probability = probability;
    }

    /**
     * Returns the current height of this skip list.
     *
     * @return the current height of this skip list
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the probability function used to determine if a node should be added on another level.
     *
     * @return the probability function used to determine if a node should be added on another leve
     */
    public Probability getProbability() {
        return probability;
    }

    /**
     * Sets the probability function used to determine if a node should be added on another level.
     *
     * @param probability the probability function
     */
    public void setProbability(Probability probability) {
        this.probability = probability;
    }

    /**
     * Returns the number of items in this skip list.
     *
     * @return the number of items in this skip list
     */
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     *
     * @param key the element whose presence in this list is to be tested
     *
     * @return {@code true} if this list contains the specified element
     */
    public boolean contains(T key) {
        // TODO: H1 - remove if implemented
        if (head!=null) {
            ListItem<ExpressNode<T>> current = head;
            while (current.key.down!=null || current.next!=null && cmp.compare(current.next.key.value, key)<1) {
                if (current.next!=null) {
                    int comparator = cmp.compare(current.next.key.value, key);
                    if (comparator==0) return true;
                    else if (current.key.down!=null && comparator>0) current = current.key.down;
                    else current = current.next;
                } else current = current.key.down;
            }
        }
        return false;
    }

    /**
     * Adds the specified element to this list. The element will be added on the highest floor of the skip list and on
     * the next levels if the probability function returns {@code true}.
     *
     * @param key the element to be added
     */
    public void add(T key) { }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present. The element will be
     * removed from all levels.
     *
     * @param key the element to be removed from this list, if present
     */
    public void remove(T key) {
        // TODO: H3 - remove if implemented
        if (head!=null) {
            ListItem<ExpressNode<T>> current = head;
            while (current.key.down!=null || current.next!=null && cmp.compare(current.next.key.value, key)<1) {
                if (current.next!=null) {
                    int comparator = cmp.compare(current.next.key.value, key);
                    if (comparator==0) {
                        while (true) {
                            ListItem<ExpressNode<T>> nextCurrent;
                            if (current.key.value==null && current.next.next==null) {
                                if (current.key.down!=null) {
                                    nextCurrent = current.next.key.down.key.prev;
                                    current.next.key.prev = null;
                                    current.next = null;
                                    head = head.key.down;
                                    head.key.up = null;
                                    current = nextCurrent;
                                    height--;
                                } else {
                                    current.next.key.up = null;
                                    current.next = null;
                                    head = null;
                                    height = 0;
                                    break;
                                }
                            } else {
                                if (current.key.down!=null) {
                                    nextCurrent = current.next.key.down.key.prev;
                                    if (current.next.next!=null) {
                                        current.next.next.key.prev = current;
                                        current.next = current.next.next;
                                    } else {
                                        current.next.key.prev = null;
                                        current.next.key.up = null;
                                        current.next = null;

                                    }
                                    current = nextCurrent;
                                } else {
                                    if (current.next.next!=null) {
                                        current.next.next.key.prev = current;
                                        current.next = current.next.next;
                                    } else {
                                        current.next.key.prev = null;
                                        current.next.key.up = null;
                                        current.next = null;
                                    }
                                    break;
                                }
                            }
                        }
                        size --;
                        break;
                    }
                    else if (current.key.down!=null && comparator>0) current = current.key.down;
                    else current = current.next;
                }
                else current = current.key.down;
            }
        }
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        // Check instance of to avoid ClassCastException
        if (!(o instanceof SkipList<?> other)) {
            return false;
        }
        return height == other.height
            && size == other.size
            && Objects.equals(head, other.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxHeight, head, height, size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (ListItem<ExpressNode<T>> walker = head; walker != null; walker = walker.key.down) {
            sb.append("[");
            for (ListItem<ExpressNode<T>> walker2 = walker.next; walker2 != null; walker2 = walker2.next) {
                sb.append(walker2.key.value);
                if (walker2.next != null) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            if (walker.key.down != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    /**
     * Default probability of 0%
     */
    public static final Probability DEFAULT = new Probability() {
        @Override
        public boolean nextBoolean() {
            return false;
        }

        @Override
        public String toString(){
            return "0%";
        }
    };

}
