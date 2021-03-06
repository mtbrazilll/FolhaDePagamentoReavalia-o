# Folha de pagamento  Reavaliação 


## Padrões de projetos implementados 

1. Command
2. Strategy
3. Extract Class
4. Extract Method
5. GRASP (Alta coesão)


# Padrões de projetos implementados n

## Command
Foi implemenado executar os comandos enviado pelo o usuário.
1. [Command](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/blob/main/Folha%20de%20pagamento%20reav/comandos/comandos/Invoker.java)

## Strategy
Foi usado para implementar as agenda de pagamento e também na criação de agenda de pagamento.
1. [Agenda de pagamento](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/blob/main/Folha%20de%20pagamento%20reav/AgendaPagamento/Agenda/AgendaDePagamento.java) para ganhar flexibilidade.
2. [Criação de agenda](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/blob/main/Folha%20de%20pagamento%20reav/AgendaPagamento/Agenda/AjudaCriarAgenda.java) para ganhar flexibilidade e eleminar os if.
3. [Criação e alteração](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/blob/main/Folha%20de%20pagamento%20reav/comandos/comandos/CriaEmpregado.java) de empregado para ganhar flexibilidade e eleminar os if.

## Extract Class
Em vez de implementar o sindicato em cada tipo(subclase) de empregado, foi criado uma classe sindicato. Analogamente para o cartão de ponto e resultado de venda.\n
1. [cartão de ponto](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/tree/main/Folha%20de%20pagamento%20reav/cartaoDePonto/cartaoDePonto)
2. [Resultado de venda](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/tree/main/Folha%20de%20pagamento%20reav/ResultadoDeVenda/ResultadoDeVenda)
3. [Classe Sindicato](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/tree/main/Folha%20de%20pagamento%20reav/sindicato/sindicato)

## Extract Method
1. Tirando as múltiplas ocorrências de busca por empregado e pedir cpf ao usuário.
2. Elimando os métodos longo e código não inteligível  

## GRASP (Alta coesão)
Na implementação do padrão Command todos os comandos ficaram na classe "folha de pagamento" deixando o código travado, ingerenciável. Para resolver
foram cirados novas classes que realizam os comandos como o deparmento de funciónarios, o departemento de finanças, etc. 
1. [Como era](https://github.com/mtbrazilll/FolhaDePagamentoRefotorado/blob/main/comandos/comandos/Invoker.java)
2. [Como ficou.](https://github.com/mtbrazilll/FolhaDePagamentoReavalia-o/blob/main/Folha%20de%20pagamento%20reav/comandos/comandos/Invoker.java)



## Funções implementadas

Comandos   |  Funções  | descrição | status |  
:---------: | --------- | :------: | :------:
1 | Adição de um empregado     | Adicionar um empregado |:white_check_mark:
2 | Remoção de um empregado    | Remove um empregado pelo o cpf |:white_check_mark:
3 | Lançar um Resultado Venda    | Lança um resultado de venda de um empregado comissionado pelo o cpf| :white_check_mark:
4 | Lançar uma taxa de serviço  | Lança uma taxa de serviço de um empregado que seja vinculado a algum sindicato| :white_check_mark:
5 | Alterar detalhes de um empregado | Altera informações de um empregado|:white_check_mark:
6 | Rodar a folha de pagamento  | Roda a folha de pagamento para o dia especificado |:white_check_mark:
7 | Undo | Disponível para as funcionalidades 1 a 7| :white_check_mark:
8 | redo | Disponível para as funcionalidades 1 a 7 | :white_check_mark:
9 | Agenda de Pagamento | Altera agenda de pagamento de um empregado|:white_check_mark:
10 | Criação de Novas Agendas de Pagamento | Cria uma agenda de pagamento|:white_check_mark:
a | Lista de empregado | Mostra os empregados cadastrado | :white_check_mark:

