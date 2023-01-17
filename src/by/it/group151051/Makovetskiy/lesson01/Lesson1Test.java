package by.it.group151051.Makovetskiy.lesson01;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class Lesson1Test {
    /*
    для прохождения тестов создайте JUnit-конфигурацию на свой пакет:
    Поля:
    Name:               Test Padabied (тут ваша фамилия)
    Test kind:          All in package
    Package:            by.it.151051.padabied (тут ваша фамилия)
    Search for test:    In whole project
    */

    @Test(timeout = 2000)
    public void slowA() throws Exception {
        FiboA fibo=new FiboA();
        BigInteger res=fibo.slowA(33);
        boolean ok=res.toString().equals("3524578");
        assertTrue("slowA failed", ok);
    }

    @Test(timeout = 2000)
    public void fastB() throws Exception {
        FiboB fibo=new FiboB();
        BigInteger res=fibo.fastB(50);
        boolean ok=res.toString().equals("12586269025");
        assertTrue("fastB failed", ok);
    }


    @Test(timeout = 2000)
    public void fasterC() throws Exception {
        FiboC fibo=new FiboC();
        assertTrue("fasterC failed 1", fibo.fasterC(10,2)==1L);
        assertTrue("fasterC failed 2", fibo.fasterC(1,2)==1L);
        assertTrue("fasterC failed 3", fibo.fasterC(55,5)==0L);
    }
}
