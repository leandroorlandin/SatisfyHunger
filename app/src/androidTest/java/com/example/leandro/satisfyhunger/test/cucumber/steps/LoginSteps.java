package com.example.leandro.satisfyhunger.test.cucumber.steps;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.example.leandro.satisfyhunger.LoginActivity;

import com.example.leandro.satisfyhunger.R;


import cucumber.api.CucumberOptions;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Leandro on 09/12/2017.
 */

@CucumberOptions(features = "features")
public class LoginSteps extends ActivityInstrumentationTestCase2<LoginActivity> {

    public LoginSteps(LoginActivity loginActivity){
        super(LoginActivity.class);
    }

    @Quando("eu digitar o login (\\S+)$")
    public void digitar_email(final String email) {
        onView(withId(R.id.email)).perform(typeText(email));
    }

    @Quando("eu digitar a senha (\\S+)$")
    public void digitar_senha(final String senha){
        onView(withId(R.id.password)).perform(typeText(senha));
    }

    @Quando("clicar no botao Login")
//    public void clicar_botao_login() throws InterruptedException {
      public void clicar_botao_login(){
        onView(withId(R.id.btnLogin)).perform(click());
//        Thread.sleep(5000);
//        HelperSteps.takeScreenshot("Confirmando_Login", getActivity());
//        throw new InterruptedException();
    }

    @Dado("a tela de login esta ativa")
    public void tela_login_ativa(){
        assertNotNull(getActivity());
    }

    @Entao("a tela de Cadastro é exibida")
    public void tela_de_cadastro_e_exibida() {
        onView(withId(R.id.btnVendedor)).check(matches(isDisplayed()));
    }

}
