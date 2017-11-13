# language: pt
Funcionalidade: Pesquisa
Testar a pesquisa

  Esquema do Cenario: Pesquisa com retorno de dados válidos
    Dado a tela de pesquisa esta ativa
    Quando eu digitar a palavra <palavra>
    Entao serao exibidos resultados

  Exemplos:
    | palavra  |
    | Sao   |