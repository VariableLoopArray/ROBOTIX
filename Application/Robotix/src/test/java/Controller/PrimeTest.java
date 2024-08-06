package Controller;

import org.junit.jupiter.api.Test;

class PrimeTest {

    @Test
    void primeTest(){
        Prime primeMethod = new Prime();

        boolean prime = primeMethod.primeOrNot(5);
        assert(prime);

    }
}