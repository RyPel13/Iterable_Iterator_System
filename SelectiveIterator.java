import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic {@link Iterator} that wraps another iterator and yields only those elements
 * that are present in a provided selection list.
 *
 * Elements from the source {@link Iterable} are returned in their original order,
 * but any element not contained in {@code selectList} is silently skipped. This iterator
 * uses an eager lookahead strategy: the next valid element is pre-loaded into
 * a cache immediately after each call to {@link #next()}, and also during construction.
 * This ensures that {@link #hasNext()} can answer in O(1) without triggering any
 * additional scanning.
 *
 * @param <T> the type of elements returned by this iterator
 * @see SelectiveIterable
 */
public class SelectiveIterator<T> implements Iterator<T> {

    /** The underlying iterator over the full source list. */
    private Iterator<? extends T> iterator;

    /**
     * The set of permitted values. Only elements found in this collection
     * will be yielded by {@link #next()}.
     */
    private Collection<?> selectList;

    /**
     * Holds the next element that has already passed the filter,
     * ready to be returned by the next call to {@link #next()}.
     * Only meaningful when {@link #hasNextCached} is {@code true}.
     */
    private T nextElement;

    /**
     * Indicates whether {@link #nextElement} currently holds a valid,
     * pre-loaded value. Set to {@code true} by {@link #advance()} when a
     * matching element is found, and reset to {@code false} at the start
     * of each {@link #advance()} call.
     */
    private boolean hasNextCached;

    /**
     * Constructs a {@code SelectiveIterator} over the given list, filtering
     * to only those elements contained in {@code selectList}.
     *
     * The first valid element is pre-loaded immediately so that
     * {@link #hasNext()} and {@link #next()} work correctly from the first call.
     *
     * @param list       the source iterable to iterate over; may contain any subtype of {@code T}
     * @param selectList the collection of permitted values; containment is checked via
     *                   {@link Collection#contains(Object)}
     */
    public SelectiveIterator(Iterable<? extends T> list, Collection<?> selectList) {
        this.iterator = list.iterator();
        this.selectList = selectList;
        this.hasNextCached = false;
        advance();
    }

    /**
     * Scans forward through the underlying iterator to find and cache the next element
     * that is present in {@code selectList}.
     *
     * Resets {@link #hasNextCached} to {@code false} at the start of each call.
     * If a matching element is found, it is stored in {@link #nextElement} and
     * {@link #hasNextCached} is set to {@code true}. If the underlying iterator is
     * exhausted without finding a match, {@link #hasNextCached} remains {@code false}.
     */
    private void advance() {
        hasNextCached = false;
        while (iterator.hasNext()) {
            T candidate = iterator.next();
            if (selectList.contains(candidate)) {
                nextElement = candidate;
                hasNextCached = true;
                break;
            }
        }
    }

    /**
     * Returns {@code true} if there is at least one more element remaining that
     * passes the selection filter.
     *
     * This method is O(1) because the next valid element is pre-loaded by
     * {@link #advance()} rather than searched for here.
     *
     * @return {@code true} if a next filtered element is available; {@code false} otherwise
     */
    public boolean hasNext() {
        return hasNextCached;
    }

    /**
     * Returns the next element from the source list that is also contained in {@code selectList}.
     *
     * After returning the cached element, {@link #advance()} is called immediately
     * to pre-load the following valid element for the next call.
     *
     * @return the next element present in both the source list and the select list
     * @throws NoSuchElementException if no more filtered elements remain
     */
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T result = nextElement;
        advance();
        return result;
    }
}