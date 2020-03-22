package bigInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UnsignedBigInteger {
    private List<Integer> number = new ArrayList<>();

    public UnsignedBigInteger(String number) {
        if (!number.matches("[0-9]+")) throw new IllegalArgumentException("Wrong format, it must be number");
        if (number.length() > 1)
            number = number.replaceFirst("[0]*", "");
        for (int i = number.length() - 1; i >= 0; i--) {
            this.number.add(Character.getNumericValue(number.charAt(i)));
        }
    }


    private void setUBIDigit(int digit) {
        this.number.add(digit);
    }

    private void setUBIDigit(int i, int digit) {
        this.number.add(i, digit);
    }

    private void trimUBI(int i) {
        this.number = this.number.subList(0, i);
    }

    private void replaceUBIDigit(int i, int digit) {
        if (i >= this.number.size()) this.number.add(i, digit);
        this.number.set(i, digit);
    }

    private int getUBISize() {
        return this.number.size();
    }

    private int getUBIDigit(int i) {
        if (i < this.number.size())
            return this.number.get(i);
        else return 0;
    }

    public void printUnsignedBigInteger() {
        for (int i = this.number.size() - 1; i >= 0; i--) {
            System.out.print(this.number.get(i));
        }
    }

    public int compare(UnsignedBigInteger other) {
        if (number.size() < other.number.size()) return -1;
        else if (number.size() > other.number.size()) return 1;
        else {
            for (int i = number.size() - 1; i >= 0; i--)
                if (number.get(i) > other.number.get(i)) return 1;
                else if (number.get(i) < other.number.get(i)) return -1;
            return 0;
        }
    }

    public UnsignedBigInteger sum(UnsignedBigInteger other) {
        int c = 0;
        int i;
        UnsignedBigInteger ans = new UnsignedBigInteger("0");
        for (i = 0; i < Math.max(number.size(), other.number.size()); i++) {
            int a, b;
            if (i >= number.size()) { // getOrDefault ???
                a = 0;
            } else a = number.get(i);
            if (i >= other.number.size()) {
                b = 0;
            } else b = other.number.get(i);

            c += a + b;
            ans.replaceUBIDigit(i, c % 10);
            c /= 10;
        }
        if (c > 0)
            ans.setUBIDigit(c);
        return ans;
    }

    public UnsignedBigInteger sub(UnsignedBigInteger other) {
        if (this.compare(other) == -1) throw new IllegalArgumentException("A must be bigger then B!");
        UnsignedBigInteger C = new UnsignedBigInteger("0");
        if (this.compare(other) == 0) {
            C.replaceUBIDigit(0, 0);
            return C;
        }
        int c = 0;
        for (int i = 0; i < number.size(); i++) {
            try {
                c += number.get(i) - other.number.get(i);
            } catch (IndexOutOfBoundsException e) {
                c += number.get(i);
            }
            C.replaceUBIDigit(i, (c + 10) % 10);
            if (c < 0) c = -1;
            else c = 0;
        }
        int i = C.getUBISize() - 1;
        while (C.getUBIDigit(i) == 0 && i != 0) {
            C.trimUBI(i);
            i--;
        }

        return C;
    }

    public UnsignedBigInteger mul(UnsignedBigInteger other) {
        int remainder = 0;
        int k = 0;
        UnsignedBigInteger C = new UnsignedBigInteger("0");
        for (int i = 0; i < number.size(); i++) {
            remainder = 0;
            for (int j = 0; j < other.number.size(); j++) {
                remainder = number.get(i) * other.number.get(j) + C.getUBIDigit(k + j) + remainder;
                C.replaceUBIDigit(k + j, remainder % 10);
                remainder /= 10;
            }
            k++;
        }
        if (remainder > 0) C.setUBIDigit(remainder);
        return C;
    }

    private UnsignedBigInteger divUBI(UnsignedBigInteger other, boolean h) {
        if (other.number.size() == 1 && other.number.get(0) == 0)
            throw new IllegalArgumentException("Maths error: Divider can't be zero");
        UnsignedBigInteger ans = new UnsignedBigInteger("0");
        if (this.compare(other) == -1) {
            ans.replaceUBIDigit(0, 0);
            return ans;
        }
        UnsignedBigInteger divA = new UnsignedBigInteger("0");
        UnsignedBigInteger divB;

        int digit;
        if (number.size() == 1 && number.get(0) == 0) {
            ans.setUBIDigit(0);
            return ans;
        }
        for (int i = number.size() - 1; i >= 0; i--) {
            if (divA.getUBIDigit(0) == 0 && divA.getUBISize() == 1) divA.replaceUBIDigit(0, number.get(i));
            else
                divA.setUBIDigit(0, number.get(i));
            if (divA.compare(other) == -1) {
                ans.setUBIDigit(0, 0);
            } else if (divA.compare(other) == 0) {
                if (ans.getUBIDigit(0) == 0 && ans.getUBISize() == 1)
                    ans.replaceUBIDigit(0, 1);
                else
                    ans.setUBIDigit(0, 1);
                divA = divA.sub(other);
            } else {
                digit = 0;
                divB = other;
                while (divB.compare(divA) < 1) {
                    divB = divB.sum(other);
                    digit++;
                }
                if (ans.getUBIDigit(0) == 0 && ans.getUBISize() == 1)
                    ans.replaceUBIDigit(0, digit);
                else
                    ans.setUBIDigit(0, digit);
                divB = divB.sub(other);
                divA = divA.sub(divB);
            }
        }
        if (h) {
            int i = ans.getUBISize() - 1;
            while (ans.getUBIDigit(i) == 0 && i != 0) {
                ans.trimUBI(i);
                i--;
            }
            return ans;
        } else {
            return divA;
        }
    }

    UnsignedBigInteger div(UnsignedBigInteger other) {
        return this.divUBI(other, true);
    }

    public UnsignedBigInteger remainder
            (UnsignedBigInteger other) {
        return this.divUBI(other, false);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnsignedBigInteger that = (UnsignedBigInteger) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i = this.getUBISize() - 1; i >= 0; i--) {
            ans += String.valueOf(this.getUBIDigit(i));
        }
        return ans;
    }

    public static void main(String[] args) {
        UnsignedBigInteger b =  new UnsignedBigInteger("");
    }
}