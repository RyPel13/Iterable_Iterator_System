import java.util.Collection;
import java.util.Iterator;

/**
 * A generic {@link Iterable} that enables for-each iteration over a source list,
 * yielding only those elements that are present in a provided selection list.
 *
 * This class acts as a factory for {@link SelectiveIterator} instances. Its sole
 * responsibility is to hold references to the source list and selection list, then
 * produce a fresh {@link SelectiveIterator} on demand. Because a new iterator is
 * created on each call to {@link #iterator()}, the same {@code SelectiveIterable}
 * can safely be used in multiple for-each loops independently.
 *
 * @param <T> the type of elements produced by this iterable
 * @see SelectiveIterator
 */
public class SelectiveIterable<T> implements Iterable<T> {

    /** The source list whose elements will be selectively iterated. */
    private Iterable<? extends T> list;

    /**
     * The collection of permitted values. Only elements from {@link #list}
     * that are also found in this collection will be yielded.
     */
    private Collection<?> selectList;

    /**
     * Constructs a {@code SelectiveIterable} over the given source list,
     * filtering to only those elements present in {@code selectList}.
     *
     * @param list   the source iterable to iterate over; may contain any subtype of {@code T}
     * @param selectList the collection of permitted values used to filter elements
     */
    public SelectiveIterable(Iterable<? extends T> list, Collection<?> selectList) {
        this.list = list;
        this.selectList = selectList;
    } 

    /**
     * Returns a new {@link SelectiveIterator} that filters the source list
     * against the selection list.
     *
     * Each call produces an independent iterator starting from the beginning
     * of the source list, so multiple for-each loops over the same
     * {@code SelectiveIterable} work correctly and independently.
     *
     * @return a fresh {@link SelectiveIterator} for the stored list and select list
     */
    @Override
    public Iterator<T> iterator() {
        return new SelectiveIterator<T>(list, selectList);
    }
}