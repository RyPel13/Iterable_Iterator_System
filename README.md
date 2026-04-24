# Iterable_Iterator_System
•	Implemented the Java Iterator and Iterable interfaces from scratch to create two custom iteration utilities: FactorIterable (iterating all positive integer factors of a number) and SelectiveIterable (filtering any collection's elements against a select list).

•	Applied generics extensively: SelectiveIterator<T> and SelectiveIterable<T> used bounded wildcard types (Iterable<? extends T> and Collection<?>) to support flexible, type-safe iteration over any Collection subtype without coupling to a specific implementation.

•	Demonstrated the power of the Collections framework by enabling SelectiveIterator to operate over any Iterable — including ArrayList, Set, and custom collections — making the design open for extension without modification.

•	Implemented hasNext() and next() with proper NoSuchElementException handling per the Iterator contract, and leveraged for-each loop compatibility through the Iterable interface to produce clean, idiomatic Java patterns.

