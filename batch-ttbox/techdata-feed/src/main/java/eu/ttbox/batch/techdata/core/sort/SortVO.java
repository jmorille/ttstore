package eu.ttbox.batch.techdata.core.sort;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Arrays;

public class SortVO implements Serializable, Comparable<SortVO> {
 
	private static final long serialVersionUID = 1L;

	private final String[] keys;

	private final String line;

	/** The cached hashCode */
	private final int hashCode;

	public SortVO(String[] keys, String line) {
		super();
		this.line = line;
		// Key
		if (keys == null) {
			throw new IllegalArgumentException(
					"The array of keys must not be null");
		}
		this.keys = keys;
		// Precompute hashcode
		int total = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i] != null) {
				total ^= keys[i].hashCode();
			}
		}
		hashCode = total;
	}

	public String getLine() {
		return line;
	}

	// -----------------------------------------------------------------------

	public String[] getKeys() {
		return keys;
	}

	/**
	 * Gets the key at the specified index.
	 * <p>
	 * The key should be immutable. If it is not then it must not be changed.
	 * 
	 * @param index
	 *            the index to retrieve
	 * @return the key at the index
	 * @throws IndexOutOfBoundsException
	 *             if the index is invalid
	 * @since Commons Collections 3.1
	 */
	public String getKey(int index) {
		return keys[index];
	}

	// -----------------------------------------------------------------------

	/**
	 * Compares this object to another.
	 * <p>
	 * To be equal, the other object must be a <code>MultiKey</code> with the
	 * same number of keys which are also equal.
	 * 
	 * @param other
	 *            the other object to compare to
	 * @return true if equal
	 */
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other instanceof SortVO) {
			SortVO otherMulti = (SortVO) other;
			return Arrays.equals(keys, otherMulti.keys);
		}
		return false;
	}

	/**
	 * Gets the combined hash code that is computed from all the keys.
	 * <p>
	 * This value is computed once and then cached, so elements should not
	 * change their hash codes once created (note that this is the same
	 * constraint that would be used if the individual keys elements were
	 * themselves {@link java.util.Map Map} keys.
	 * 
	 * @return the hash code
	 */
	public int hashCode() {
		return hashCode;
	}

	/**
	 * Gets a debugging string version of the key.
	 * 
	 * @return a debugging string
	 */
	public String toString() {
		return Objects.toStringHelper(this)
				.add("key", Arrays.asList(keys).toString()).toString();
	}

	// -----------------------------------------------------------------------
	@Override
	public int compareTo(SortVO other) {
		String[] otherKeys = other.keys;
		int iterationSize = keys.length;
		boolean isManageDifferentSize = false;
		if (iterationSize != otherKeys.length) {
			isManageDifferentSize = true;
			iterationSize = Math.min(iterationSize, otherKeys.length);
		}
		int compare = 0;
		for (int i = 0; i < iterationSize; i++) {
			compare = keys[i].compareTo(otherKeys[i]);
			if (compare != 0) {
				return compare;
			}
		}
		if (isManageDifferentSize) {
			compare = (keys.length < otherKeys.length ? -1
					: (keys.length == otherKeys.length ? 0 : 1));
		}
		return compare;
	}

}
