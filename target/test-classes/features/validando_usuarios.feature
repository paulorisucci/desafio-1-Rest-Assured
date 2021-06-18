#language: pt


Funcionalidade: Validando usuarios
  Cenário: Validando a lista de usuários
    Dado o site "http://restapi.wcaquino.me/"
    Quando fizer uma requisição GET
    Então verifique a resposta
    E faça o teste de contrato
    E valide os Ids
    E valide os nomes
    E valide as idades
    E valide os endereços
    E valide os dependentes
