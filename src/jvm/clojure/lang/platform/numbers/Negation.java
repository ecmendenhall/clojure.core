package clojure.lang.platform.numbers;

import java.math.BigInteger;
import java.math.BigDecimal;
import clojure.lang.BigInt;
import clojure.lang.platform.Ratio;

public final class Negation {

  public static final Number numberNegate(Number x) {
    if ((x instanceof Double) || (x instanceof Float)) {
      return Negation.doubleNegate(Coercion.toDouble(x));
    } else if (x instanceof BigDecimal) {
      return Negation.bigDecimalNegate((BigDecimal) x);
    } else if (x instanceof Ratio) {
      return Negation.ratioNegate((Ratio) x);
    } else if ((x instanceof BigInt) || (x instanceof BigInteger)) {
      return Negation.bigIntNegate(Coercion.toBigInt(x));
    } else {
      return Negation.longNegate(Coercion.toLong(x));
    }
  }

  public static final Number longNegate(long x) {
    if (x == Long.MIN_VALUE) {
      throw new ArithmeticException("integer overflow");
    } else {
      return (Number) Long.valueOf(-x);
    }
  }

  public static final Number bigIntNegate(BigInt x) {
    return (Number) BigInt.fromBigInteger(Coercion.toBigInteger(x).negate());
  }

  public static final Number ratioNegate(Ratio x) {
    Ratio r = new Ratio(((BigInteger) x.getNumerator()).negate(), ((BigInteger) x.getDenominator()));
    return (Number) r;
  }

  public static final Number bigDecimalNegate(BigDecimal x) {
    return (Number) x.negate();
  }

  public static final Number doubleNegate(double x) {
    return (Number) Double.valueOf(-x);
  }

}

