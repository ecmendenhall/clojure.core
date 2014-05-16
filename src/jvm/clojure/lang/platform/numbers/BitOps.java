package clojure.lang.platform.numbers;

public final class BitOps {

  public static long numberBitAnd(Object x, Object y) {
    long lx = Coercion.toBitOperand(x);
    long ly = Coercion.toBitOperand(y);
    return lx & ly;
  }

  public static int integerPreserveBitAnd(int x, int y) {
    return x & y;
  }

  public static long numberBitOr(Object x, Object y) {
    long lx = Coercion.toBitOperand(x);
    long ly = Coercion.toBitOperand(y);
    return lx | ly;
  }

  public static int integerPreserveBitOr(int x, int y) {
    return x | y;
  }

  public static long numberBitXor(Object x, Object y) {
    long lx = Coercion.toBitOperand(x);
    long ly = Coercion.toBitOperand(y);
    return lx ^ ly;
  }

  public static int integerPreserveBitXor(int x, int y) {
    return x ^ y;
  }

}
