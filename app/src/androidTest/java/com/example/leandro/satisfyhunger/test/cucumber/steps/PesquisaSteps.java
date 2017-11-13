package com.example.leandro.satisfyhunger.test.cucumber.steps;

import android.test.ActivityInstrumentationTestCase2;

import com.example.leandro.satisfyhunger.Pesquisa;
import com.example.leandro.satisfyhunger.R;

import cucumber.api.CucumberOptions;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Familia on 01/11/2017.
 */

@CucumberOptions(features = "features")
public class PesquisaSteps extends ActivityInstrumentationTestCase2<Pesquisa> {

    public PesquisaSteps(Pesquisa pesquisa){
        super(Pesquisa.class);
    }

    @Quando("eu digitar a palavra (\\S+)$")
    public void digitar_palavra(final String palavra){
        onView(withId(R.id.editPalavra)).perform(typeText(palavra));
    }

    @Dado("a tela de pesquisa esta ativa")
    public void tela_pesquisa_ativa(){
        assertNotNull(getActivity());
        onView(withId(R.id.menu_buscar)).perform(click());
    }

    @Entao("serao exibidos resultados")
    public void exibicao_resultados(){

    }
}