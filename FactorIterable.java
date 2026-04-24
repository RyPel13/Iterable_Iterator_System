import java.util.Iterator;

/**
 * An {@link Iterable} that enables for-each iteration over all positive factors
 * of a given integer in ascending order.
 *
 * This class acts as a factory for {@link FactorIterator} instances. Its sole
 * responsibility is to hold the target number and produce a fresh iterator on demand.
 * Because a new {@link FactorIterator} is created on each call to {@link #iterator()},
 * the same {@code FactorIterable} can be safely used in multiple for-each loops
 * without one loop's state affecting another.
 *
 * @see FactorIterator
 */
public class FactorIterable implements Iterable<Integer> {

    /** The integer whose factors will be iterated. */
    private int number;

    /**
     * Constructs a {@code FactorIterable} for the given integer.
     *
     * The number is stored as-is and passed directly to {@link FactorIterator},
     * which handles sign normalization via {@link Math#abs(int)}.
     *
     * @param number the integer whose positive factors will be iterated
     */
    public FactorIterable(int number) {
        this.number = number;
    }

    /**
     * Returns a new {@link FactorIterator} over the positive factors of this iterable's number.
     *
     * Each call produces an independent iterator starting from factor {@code 1},
     * so multiple for-each loops over the same {@code FactorIterable} work correctly.
     *
     * @return a fresh {@link FactorIterator} for the stored number
     */
    @Override
    public Iterator<Integer> iterator() {
        return new FactorIterator(number);
    }
}