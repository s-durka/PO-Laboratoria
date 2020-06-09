package com.company;

import static org.junit.jupiter.api.Assertions.*;

class bitCounterTest {
    @Test
    void getBits() {
        assertEquals(4,bitsCounter.getBits(3));
        assertEquals(35,bitsCounter.getBits(17));
        assertEquals(13313844,bitsCounter.getBits(1333566));
        assertEquals(1797034504,bitsCounter.getBits(133356699));
        assertEquals(416198729,bitsCounter.getBits(33356694));
        assertEquals(416660324,bitsCounter.getBits(33386694));
        assertEquals(281066627,bitsCounter.getBits(23335669));
    }
    @Test
    void t2(){
        assertTimeout(Duration.ofMillis(30),() -> {   bitsCounter.getBits(133356699);
            bitsCounter.getBits(33356694); bitsCounter.getBits(1933546);  bitsCounter.getBits(1333516);
            bitsCounter.getBits(1933546);
            bitsCounter.getBits(81933546);
            bitsCounter.getBits(81993546);
            bitsCounter.getBits(89953546);
            for (int i=100; i<90000;i++){
                bitsCounter.getBits(i);
            }
            return 6;});
    }
}

}