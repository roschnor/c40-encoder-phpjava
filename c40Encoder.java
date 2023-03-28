public class C40Encoder {

  /**
   * 
   * @param stringS
   * @return c40 String based on an input of 0-9, A-Z, , <
   * @throws Exception 
   */
  public static String encodeC40Visa(String stringS) throws Exception {

    stringS = stringS.trim();
    stringS = stringS.replace('<', ' ');

    String returnS = "";

    char[] chars = stringS.toCharArray();
    Integer[][] master_arr = new Integer[(chars.length / 3) + 1][3];
    int i = 0;
    int k = 0;

    for (char char2: chars) {
      master_arr[k][i] = ord(char2);
      if (i == 2) {
        i = 0;
        k++;
      } else {
        i++;
      }
    }

    int U = 0;
    int U1 = 0;
    int U2 = 0;
    int U3 = 0;
    float resa;
    float res;
    float res2;
    String div;
    String mod;

    for (Integer[] val: master_arr) {
      if (val[0] != null) {
        U1 = ord2c40(val[0]);
      } else {
        U1 = 0;
      }
      if (val[1] != null) {
        U2 = ord2c40(val[1]);
      } else {
        U2 = 0;
      }
      if (val[2] != null) {
        U3 = ord2c40(val[2]);
      } else {
        U3 = 0;
      }

      if (U2 == 0 && U3 == 0) {
        div = "fe";
        if (val[0] == null) {
          return returnS.trim();
        }
        mod = dechex(val[0] + 1);
      } else {
        U = (1600 * U1) + (40 * U2) + U3 + 1;

        resa = floatval(U / 256);
        res = floatval(floor(resa));
        res2 = (resa - res) * 256;
        div = dechex((int) res);
        if (div.length() == 1) {
          div = "0" + div;
        }
        mod = dechex((int) res2);
        if (mod.length() == 1) {
          mod = "0" + mod;
        }
      }

      returnS = returnS + "" + div + "" + mod;
    }
    return returnS.trim();
  }

  private static double floor(float resa) {

    return Math.floor(resa);
  }

  private static float floatval(int i) {
    return (float) i;
  }

  private static float floatval(double i) {
    return (float) i;
  }

  private static String dechex(int i) {
    return Integer.toHexString(i);
  }

  private static int ord(char char2) {
    return (int) char2;
  }

  public static int ord2c40(Integer ord) throws Exception {
    switch (ord.intValue()) {
    case 32:
      return 3;
    case 48:
      return 4;
    case 49:
      return 5;
    case 50:
      return 6;
    case 51:
      return 7;
    case 52:
      return 8;
    case 53:
      return 9;
    case 54:
      return 10;
    case 55:
      return 11;
    case 56:
      return 12;
    case 57:
      return 13;
    case 65:
      return 14;
    case 66:
      return 15;
    case 67:
      return 16;
    case 68:
      return 17;
    case 69:
      return 18;
    case 70:
      return 19;
    case 71:
      return 20;
    case 72:
      return 21;
    case 73:
      return 22;
    case 74:
      return 23;
    case 75:
      return 24;
    case 76:
      return 25;
    case 77:
      return 26;
    case 78:
      return 27;
    case 79:
      return 28;
    case 80:
      return 29;
    case 81:
      return 30;
    case 82:
      return 31;
    case 83:
      return 32;
    case 84:
      return 33;
    case 85:
      return 34;
    case 86:
      return 35;
    case 87:
      return 36;
    case 88:
      return 37;
    case 89:
      return 38;
    case 90:
      return 39;
    default:
      throw new Exception("only upper case letters, numbers, <SPACE>, and the symbol '<'");
    }

  }

}
