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
 * This class is to fulfill the needs of getting combinations from a collection.
 * 
 * It basically provides the functionalities of : 1 - enquiry the number of
 * combination count : c(m, n) 2 - given an ordinal number i, fetch the i-th
 * combination of c(m, n) as a read-only list. 3 - convenient for-each iteration
 * of all the combinations
 * 
 * This class is NOT thread safe.
 * 
 * This class re-uses one array to fetch the combination, so if the user want to
 * keep the i-th combination result, make a copy.
 * 
 * @date 07/08/2012
 * @author raistlic
 */
public class Combination<E> implements Iterable<List<E>> {

	public static final CombinationAlgorithm DEFAULT_ALGORITHM = AlgorithmVER01.INSTANCE;

	public static <E> Combination<E> of(Collection<E> elements, int numberToPick) {

		return of(elements, numberToPick, DEFAULT_ALGORITHM);
	}

	public static <E> Combination<E> of(Collection<E> elements,int numberToPick, CombinationAlgorithm algorithm) {

		if (elements == null)
			throw new NullPointerException();

		if (numberToPick < 0 || numberToPick > elements.size())
			throw new IllegalArgumentException(
					"Invalid number of elements to pick : " + numberToPick + " out of " + elements.size());

		if (algorithm == null)
			algorithm = DEFAULT_ALGORITHM;

		return new Combination<E>(elements, numberToPick, algorithm);
	}

	@SuppressWarnings("unchecked")
	private E[] elements, picked;
	private CombinationAlgorithm algorithm;
	private BigInteger count;

	private Combination(Collection<E> elements, int numberToPick,CombinationAlgorithm algorithm) {

		assert elements != null;
		assert numberToPick >= 0;
		assert numberToPick <= elements.size();
		assert algorithm != null;

		this.elements = (E[]) elements.toArray();
		this.picked = (E[]) new Object[numberToPick];
		this.algorithm = algorithm;
		this.count = this.algorithm.getCombinationCount(this.elements.length,numberToPick);
	}

	public BigInteger getCombinationCount() {

		return count;
	}

	public List<E> getCombination(BigInteger ordinal) {

		algorithm.fetchCombination(elements, picked, ordinal);
		return Arrays.asList(picked);
	}

	@Override
	public Iterator<List<E>> iterator() {

		return new OrdinalIterator();
	}

	private class OrdinalIterator implements Iterator<List<E>> {

		private BigInteger ordinal;

		private OrdinalIterator() {

			ordinal = ZERO;
		}

		@Override
		public boolean hasNext() {

			return ordinal.compareTo(getCombinationCount()) < 0;
		}

		@Override
		public List<E> next() {

			List<E> result = getCombination(ordinal);
			ordinal = ordinal.add(ONE);
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private static enum AlgorithmVER01 implements CombinationAlgorithm {

		INSTANCE;

		@Override
		public int getMaxSupportedSize() {

			return MAX_SUPPORT;
		}

		@Override
		public BigInteger getCombinationCount(int numberOfElements,
				int numberToPick) {

			if (numberOfElements < 0)
				throw new IllegalArgumentException(
						"Invalid number of elements : " + numberOfElements);

			if (numberOfElements > getMaxSupportedSize())
				throw new IllegalArgumentException(
						"Number of elements out of range : " + numberOfElements);

			if (numberToPick < 0 || numberToPick > numberOfElements)
				throw new IllegalArgumentException("Invalid number to pick : "
						+ numberToPick);

			if (numberToPick == 0 || numberToPick == numberOfElements)
				return ONE;
			else
				return Calculator.factorial(numberOfElements).divide(
						Calculator.factorial(numberToPick).multiply(
								Calculator.factorial(numberOfElements
										- numberToPick)));
		}

		@Override
		public void fetchCombination(Object[] source, Object[] target,BigInteger ordinal) {

			for (int i = 0, si = 0; i < target.length; i++, si++) {

				if (ordinal.compareTo(ZERO) > 0) {

					BigInteger cLeft = getCombinationCount(source.length - si- 1, target.length - i - 1);
					while (ordinal.compareTo(cLeft) >= 0) {

						si++;
						ordinal = ordinal.subtract(cLeft);
						if (ordinal.compareTo(ZERO) == 0)
							break;
						cLeft = getCombinationCount(source.length - si - 1,target.length - i - 1);
					}
				}
				target[i] = source[si];
			}
		}

		private static final int MAX_SUPPORT = 1024;
	}
}
