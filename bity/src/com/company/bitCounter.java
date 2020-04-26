package com.company;

public class bitCounter {
   // implementacja naiwna:
    public int countSetBitsSum(int n) {
        int bitCount = 0;
        for (int i = 1; i <= n; i++)
            bitCount += countSetBitsNumber(i);
        return bitCount;
    }

    public int countSetBitsNumber(int x) {
        if (x <= 0)
            return 0;
        return (x % 2 == 0 ? 0 : 1) +
                countSetBitsNumber(x / 2);
    }

    // IMPLEMENTACJA SPRYTNA: Złożoność O(log(n))

    // zwraca pozycję ostatniego (najbardziej znaczącego) bitu 1
    int getLastSetBit(int n) {
        int k = 0;
        while (n > 1) {
            n = n >> 1;
            k ++;
        }
        return k; // m - pozycja bitu -- 2^0 na pozycji 0 itd...
    }

    // mając pozycję ostatniego ustawionego bitu, zwraca pozycję następnego
    int getNextLastSetBit(int n, int k) {
        int m = n >> 1;
        while (m > n) {
            m = m >> 1;
            k--;
        }
        return k;
    }

    // rekurencyjna funkcja (pomocnicza w getBits)
    int getSetBits(int n, int k) {
        if (n == (1 << (k+1)) - 1) // jeśli liczba jest postaci 2^x - 1 (to musi być postaci 2^(k+1) -1) (1,3,7,...)
            return (1 << k) * (k + 1); // ze względu na bijekcję f: {0,...,2^k -1} --> {2^k,...,2^(k+1)-1},
                                        // gdzie f - dopełnienie bitowe
        // else
        //k = getNextLastSetBit(n, k);
        int kthBitCount = n - (1 << k) + 1; // ile razy wystąpił bit na k-tej pozycji - n - 2^k + 1
        int bitsLessThanK = k * (1 << (k-1)); // liczba 1-bitów dla liczb od 1 do n, gdzie bit na k-tej pozycji = 0,
                                            // to dokładnie liczba postaci 2^(k-1), czyli ta sama formułka, co wcześniej
                                            // inaczej getSetBits(2^(k-1))
        int bitsRightOfK = getBits(n - (1 << k)); // getBits(
        return kthBitCount + bitsRightOfK + bitsLessThanK;
    }

    // Główna funkcja:
    int getBits(int n) {
        int k = getLastSetBit(n);
        return getSetBits(n, k);
    }

    public static void main(String[] args) {
        int n = 13;
        bitCounter bitsC = new bitCounter();
        System.out.print(bitsC.getBits(n));
    }
}

