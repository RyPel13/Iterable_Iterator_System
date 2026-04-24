import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An {@link Iterator} that produces all positive factors of a given integer in ascending order.
 *
 * The iterator steps through every integer from 1 up to the absolute value of the input,
 * yielding only those values that evenly divide the number. For example, for input {@code 12},
 * the iterator yields {@code 1, 2, 3, 4, 6, 12} in that order.
 *
 * @see FactorIterable
 */
public class FactorIterator implements Iterator<Integer> {

    /** The number being factorized (stored as its absolute value). */
    private int number;

    /**
     * Tracks the current candidate being tested for divisibility.
     * Advances from 1 up to {@code number} across successive calls to {@link #next()}.
     */
    private int currentFactor;

    /**
     * Constructs a {@code FactorIterator} for the given integer.
     *
     * The absolute value of {@code number} is used, so negative inputs produce
     * the same factors as their positive counterparts. If {@code number} is zero,
     * {@link #hasNext()} will immediately return {@code false}.
     *
     * @param number the integer whose positive factors will be iterated
     */
    public FactorIterator(int number) {
        this.number = Math.abs(number);
        this.currentFactor = (this.number == 0) ? 0 : 1;
    }

    /**
     * Returns {@code true} if there are more factors to be returned.
     *
     * Returns {@code false} immediately if the input was zero, or once
     * {@code currentFactor} has advanced past {@code number}, meaning the
     * final factor (the number itself) has already been yielded.
     *
     * @return {@code true} if at least one more factor remains; {@code false} otherwise
     */
    public boolean hasNext() {
        if (number == 0) return false;
        return currentFactor <= number;
    }

    /**
     * Returns the next positive factor of the number in ascending order.
     *
     * Scans forward from {@code currentFactor}, skipping non-divisors, until
     * it finds the next value that evenly divides {@code number}. That value is
     * saved, {@code currentFactor} is advanced past it, and the saved value is returned.
     *
     * @return the next positive integer factor of the number
     * @throws NoSuchElementException if there are no more factors to return
     */
    public Integer next() {
        if (!hasNext()) throw new NoSuchElementException();

        while (currentFactor <= number) {
            if (number % currentFactor == 0) {
                int result = currentFactor;
                currentFactor++;
                return result;
            }
            currentFactor++;
        }

        // Should never reach here if hasNext() was checked
        throw new NoSuchElementException();
    }
}