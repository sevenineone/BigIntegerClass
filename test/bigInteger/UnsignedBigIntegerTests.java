package bigInteger;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnsignedBigIntegerTests {
    private UnsignedBigInteger
            a1 = new UnsignedBigInteger("4"), b1 = new UnsignedBigInteger("4"),
            a2 = new UnsignedBigInteger("123"), b2 = new UnsignedBigInteger("3"),
            a3 = new UnsignedBigInteger("12"), b3 = new UnsignedBigInteger("34"),
            a4 = new UnsignedBigInteger("153513513413413"), b4 = new UnsignedBigInteger("1351351"),
            a5 = new UnsignedBigInteger("1100"), b5 = new UnsignedBigInteger("10"),
            a6 = new UnsignedBigInteger("2"), b6 = new UnsignedBigInteger("8"),
            a7 = new UnsignedBigInteger("125"), b7 = new UnsignedBigInteger("3");


    @Test
    public void compareTest() {
        assertEquals(0, a1.compare(b1));
        assertEquals(-1, a2.compare(b2));
        assertEquals(1, a3.compare(b3));
        assertEquals(-1, a4.compare(b4));
        assertEquals(-1, a5.compare(b5));
        assertEquals(1, a6.compare(b6));
    }

    @Test
    public void sumUnsignedBigIntegerTest() {
        UnsignedBigInteger
                ans1 = new UnsignedBigInteger("8"),
                ans2 = new UnsignedBigInteger("126"),
                ans3 = new UnsignedBigInteger("46"),
                ans4 = new UnsignedBigInteger("153513514764764"),
                ans5 = new UnsignedBigInteger("1110"),
                ans6 = new UnsignedBigInteger("10");
        assertEquals(ans1, a1.sum(b1));
        assertEquals(ans2, a2.sum(b2));
        assertEquals(ans3, a3.sum(b3));
        assertEquals(ans4, a4.sum(b4));
        assertEquals(ans5, a5.sum(b5));
        assertEquals(ans6, a6.sum(b6));
    }

    @Test
    public void subUnsignedBigIntegerTest() {
        UnsignedBigInteger
                ans1 = new UnsignedBigInteger("0"),
                ans2 = new UnsignedBigInteger("120"),
                ans4 = new UnsignedBigInteger("153513512062062"),
                ans5 = new UnsignedBigInteger("1090");
        assertEquals(ans1, a1.sub(b1));
        assertEquals(ans2, a2.sub(b2));
        assertEquals(ans4, a4.sub(b4));
        assertEquals(ans5, a5.sub(b5));
    }


    @Test
    public void mulUnsignedBigIntegerTest() {
        UnsignedBigInteger
                ans1 = new UnsignedBigInteger("16"),
                ans2 = new UnsignedBigInteger("369"),
                ans3 = new UnsignedBigInteger("408"),
                ans4 = new UnsignedBigInteger("207450639864729070963"),
                ans5 = new UnsignedBigInteger("11000"),
                ans6 = new UnsignedBigInteger("16");
        assertEquals(ans1, a1.mul(b1));
        assertEquals(ans2, a2.mul(b2));
        assertEquals(ans3, a3.mul(b3));
        assertEquals(ans4, a4.mul(b4));
        assertEquals(ans5, a5.mul(b5));
        assertEquals(ans6, a6.mul(b6));
    }

    @Test
    public void divUnsignedBigIntegerTest() {
        UnsignedBigInteger
                ans1 = new UnsignedBigInteger("1"),
                ans2 = new UnsignedBigInteger("41"),
                ans3 = new UnsignedBigInteger("0"),
                ans4 = new UnsignedBigInteger("113600029"),
                ans5 = new UnsignedBigInteger("110"),
                ans6 = new UnsignedBigInteger("0");
        assertEquals(ans1, a1.div(b1));
        assertEquals(ans2, a2.div(b2));
        assertEquals(ans3, a3.div(b3));
        assertEquals(ans4, a4.div(b4));
        assertEquals(ans5, a5.div(b5));
        assertEquals(ans6, a6.div(b6));
    }

    @Test
    public void remainderUnsignedBigIntegerTest() {
        UnsignedBigInteger
                ans1 = new UnsignedBigInteger("0"),
                ans2 = new UnsignedBigInteger("0"),
                ans3 = new UnsignedBigInteger("0"),
                ans4 = new UnsignedBigInteger("624234"),
                ans5 = new UnsignedBigInteger("0"),
                ans6 = new UnsignedBigInteger("0"),
                ans7 = new UnsignedBigInteger("2");
        assertEquals(ans1, a1.remainder(b1));
        assertEquals(ans2, a2.remainder(b2));
        assertEquals(ans3, a3.remainder(b3));
        assertEquals(ans4, a4.remainder(b4));
        assertEquals(ans5, a5.remainder(b5));
        assertEquals(ans6, a6.remainder(b6));
        assertEquals(ans7, a7.remainder(b7));
    }

}