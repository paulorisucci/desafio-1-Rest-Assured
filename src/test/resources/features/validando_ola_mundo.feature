#language: pt


Funcionalidade: Validando a mensagem olá mundo
  Cenario: A mensagem recebida deve ser "Ola mundo!"
    Dado o site "http://restapi.wcaquino.me/"
    Quando verifico o conteúdo da página "/ola"
    Entao o valor recebido é "Ola Mundo!"