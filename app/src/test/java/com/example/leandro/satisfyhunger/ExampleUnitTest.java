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
    public void testeLoginTrue(){
        LoginActivity loginActivity = new LoginActivity();
        boolean resultado = loginActivity.validarUsuario("ps.vale@gmail.com", "123456");
        assertEquals(true,resultado);
    }
    @Test
    public void testeLoginFalse(){
        LoginActivity loginActivity = new LoginActivity();
        boolean resultado = loginActivity.validarUsuario("", "");
        assertEquals(false,resultado);
    }

    @Test
    public void testeCadastroTrue(){
        CadastroUsuarioActivity cadastroUsuarioActivity = new CadastroUsuarioActivity();
        boolean resultado = cadastroUsuarioActivity.validarUsuario("ps.vale@gmail.com", "123456");
        assertEquals(true,resultado);
    }
    @Test
    public void testeCadastroFalse(){
        CadastroUsuarioActivity cadastroUsuarioActivity = new CadastroUsuarioActivity();
        boolean resultado = cadastroUsuarioActivity.validarUsuario("", "");
        assertEquals(false,resultado);
    }

    @Test
    public void testeCadastroVendedorTrue(){
        CadastroVendedorActivity cadastroVendedorActivity = new CadastroVendedorActivity();
        boolean resultado = cadastroVendedorActivity
                .validarInformacoes("Teste", "123456",
                        "Sao Paulo", "Brigadeiro","Com Granulado" );
        assertEquals(true,resultado);
    }
    @Test
    public void testeCadastroVendedorFalse(){
        CadastroVendedorActivity cadastroVendedorActivity = new CadastroVendedorActivity();
        boolean resultado = cadastroVendedorActivity
                .validarInformacoes("", "",
                        "", "","" );
        assertEquals(false,resultado);
    }

    @Test
    public void testeCadastroVendedorFalse2(){
        CadastroVendedorActivity cadastroVendedorActivity = new CadastroVendedorActivity();
        boolean resultado = cadastroVendedorActivity
                .validarInformacoes("Teste", "",
                        "", "","" );
        assertEquals(false,resultado);
    }
}