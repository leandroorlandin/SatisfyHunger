# language: pt
Funcionalidade: LoginActvity
Testar o loginActivity

  Esquema do Cenario: Pesquisa com retorno de dados válidos
    Dado a tela de login esta ativa
    Quando eu digitar o login <email>
    E eu digitar a senha <senha>
    E clicar no botao Login
    Entao a tela de Cadastro é exibida

  Exemplos:
    | email           | senha       | see    |
    | teste@gmail.com | teste       | true   |