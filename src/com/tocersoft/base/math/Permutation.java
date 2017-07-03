package com.tocersoft.base.math;

/*
 * Copyright 2012 wuyou (raistlic@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class is to fulfill the needs of getting permutations from a collection.
 * 
 * It basically provides the functionalities of : 1 - enquiry the number of
 * permutation count : p(m, n) 2 - given an ordinal number i, fetch the i-th
 * permutation result as a read-only list. 3 - convenient for-each iteration of
 * all the permutations
 * 
 * This class is NOT thread safe.
 * 
 * This class re-uses one array to fetch each enquiry, so if the user want to
 * keep the i-th permutation result, make a copy.
 * 
 * @date 07/08/2012
 * @author raistlic
 */
public class Permutation<E> implements Iterable<List<E>> {

	public static final PermutationAlgorithm DEFAULT_PERMUTATION_ALGORITHM = AlgorithmVER01.INSTANCE;
	public static final CombinationAlgorithm DEFAULT_COMBINATION_ALGORITHM = Combination.DEFAULT_ALGORITHM;

	public static <E> Permutation<E> of(Collection<E> elements) {

		return of(elements, elements.size());
	}

	public static <E> Permutation<E> of(Collection<E> elements, int numberToPick) {

		return of(elements, numberToPick, DEFAULT_PERMUTATION_ALGORITHM);
	}

	public static <E> Permutation<E> of(Collection<E> elements,
			PermutationAlgorithm pAlgorithm) {

		return of(elements, elements.size(), pAlgorithm);
	}

	public static <E> Permutation<E> of(Collection<E> elements,
			int numberToPick, PermutationAlgorithm pAlgorithm) {

		return of(elements, numberToPick, pAlgorithm,
				DEFAULT_COMBINATION_ALGORITHM);
	}

	public static <E> Permutation<E> of(Collection<E> elements,
			int numberToPick, PermutationAlgorithm pAlgorithm,
			CombinationAlgorithm cAlgorithm) {

		if (elements == null)
			throw new NullPointerException();

		if (pAlgorithm == null)
			throw new NullPointerException();

		if (elements.size() > pAlgorithm.getMaxSupportedSize())
			throw new IllegalArgumentException(
					"Element collection size not supported by the permutation algorithm.");

		return new Permutation<E>(elements, numberToPick, pAlgorithm,
				cAlgorithm);
	}

	@SuppressWarnings("unchecked")
	private E[] elements, picked;
	private PermutationAlgorithm pAlgorithm;
	private CombinationAlgorithm cAlgorithm;
	private BigInteger cCount, pCount;
	private BigInteger count;
	private int numberToPick;

	private Permutation(Collection<E> elements, int numberToPick,
			PermutationAlgorithm pAlgorithm, CombinationAlgorithm cAlgorithm) {

		assert elements != null;
		assert pAlgorithm != null;
		assert cAlgorithm != null;
		assert numberToPick >= 0;
		assert numberToPick <= elements.size();

		this.elements = (E[]) elements.toArray();
		this.picked = (E[]) new Object[numberToPick];
		this.pAlgorithm = pAlgorithm;
		this.cAlgorithm = cAlgorithm;
		this.numberToPick = numberToPick;

		this.cCount = this.cAlgorithm.getCombinationCount(this.elements.length,
				this.numberToPick);
		this.pCount = this.pAlgorithm.getPermutationCount(this.numberToPick);
		this.count = this.cCount.multiply(this.pCount);
	}

	public BigInteger getPermutationCount() {

		return count;
	}

	public List<E> getPermutation(BigInteger ordinal) {

		if (ordinal == null)
			throw new NullPointerException();

		if (ordinal.compareTo(ZERO) < 0 || ordinal.compareTo(count) >= 0)
			throw new IllegalArgumentException("Ordinal value out of range : "
					+ ordinal);

		if (numberToPick == elements.length) {

			System.arraycopy(elements, 0, picked, 0, elements.length);
		} else {

			cAlgorithm.fetchCombination(elements, picked,
					ordinal.divide(pCount));
			ordinal = ordinal.mod(pCount);
		}
		pAlgorithm.fetchPermutation(picked, ordinal);
		return Arrays.asList(picked);
	}

	@Override
	public Iterator<List<E>> iterator() {

		return this.new OrdinalIterator();
	}

	private class OrdinalIterator implements Iterator<List<E>> {

		private BigInteger ordinal;

		private OrdinalIterator() {

			this.ordinal = ZERO;
		}

		@Override
		public boolean hasNext() {

			return ordinal.compareTo(count) < 0;
		}

		@Override
		public List<E> next() {

			List<E> result = getPermutation(ordinal);
			ordinal = ordinal.add(ONE);
			return result;
		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();
		}
	}

	private static enum AlgorithmVER01 implements PermutationAlgorithm {

		INSTANCE;

		@Override
		public int getMaxSupportedSize() {

			return MAX_SUPPORT;
		}

		@Override
		public BigInteger getPermutationCount(int numberOfElements) {

			if (numberOfElements < 0)
				throw new IllegalArgumentException(
						"Invalid number of elements : " + numberOfElements);

			if (numberOfElements > getMaxSupportedSize())
				throw new IllegalArgumentException(
						"Number of elements out of range : " + numberOfElements);

			return Calculator.factorial(numberOfElements);
		}

		@Override
		public void fetchPermutation(Object[] elements, BigInteger ordinal) {

			for (int i = 0; i < elements.length - 1; i++) {

				int left = elements.length - i - 1;
				BigInteger leftCount = Calculator.factorial(left);
				int curr = ordinal.divide(leftCount).intValue();
				ordinal = ordinal.mod(leftCount);
				if (curr > 0) {

					Object temp = elements[curr + i];
					for (int j = curr + i; j > i; j--)
						elements[j] = elements[j - 1];
					elements[i] = temp;
				}
			}
		}

		private static final int MAX_SUPPORT = 1024;
	}
	
	public static void main(String[] args) {
	    
	    System.out.println("===== demo combination :");
	    for(List<Integer> list : Combination.of(Arrays.asList(1, 2, 3, 4, 5), 3))
	      System.out.println(list);
	  }
}
