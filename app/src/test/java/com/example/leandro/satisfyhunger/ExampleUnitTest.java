package com.example.leandro.satisfyhunger;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        Pesquisa p = new Pesquisa();
        boolean result = p.teste();

        assertEquals(true, result);
    
    }
    public void testeLoginTrue(){
        LoginActivity loginActivity = new LoginActivity();
        boolean resultado = loginActivity.validarUsuario("ps.vale@gmail.com", "123456");
        assertEquals(true,resultado);
    }
    public void testeLoginFalse(){
        LoginActivity loginActivity = new LoginActivity();
        boolean resultado = loginActivity.validarUsuario("", "");
        assertEquals(false,resultado);
    }
}