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
import java.math.BigInteger;

/**
 * This class is intended to provide convenient facilities for 
 * frequently used maths calculations that {@code java.lang.Math} 
 * does not cover.
 * 
 * @date   08/08/2012
 */
public final class Calculator {
  
  private Calculator() {}
  
  private static final BigInteger[] FACT_RESULT_POOL = new BigInteger[1024];
  public static BigInteger factorial(int number) {
    
    if( number < 0 )
      throw new IllegalArgumentException();

    BigInteger result = null;

    if( number < FACT_RESULT_POOL.length )
      result = FACT_RESULT_POOL[number];

    if( result == null ) {

      result = BigInteger.ONE;
      for(int i = 2; i <= number; i++)
        result = result.multiply(BigInteger.valueOf(i));
      if( number < FACT_RESULT_POOL.length )
        FACT_RESULT_POOL[number] = result;
    }
    return result;
  }
}
