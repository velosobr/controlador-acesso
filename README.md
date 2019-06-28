

# Trabalho 01 – Tema Controle de Acesso

## Problema:
A UFSC necessita controlar os acessos de entrada e saída às diferentes salas e laboratórios. Essas salas só poderiam ser acessadas por pessoas da comunidade UFSC (Alunos e servidores) cadastradas no sistema e que tenham permissão para isso. 

Implementar um sistema orientado a objetos em Java para permitir o controle de acesso às salas da Universidade.


### Escopo do desenvolvimento:
Um sistema é responsável por controlar o acesso de pessoas às salas e laboratórios da UFSC.

#### [OK] O sistema deve permitir o cadastro das salas, contendo ao menos: Numero, bloco, centro e campus. 

#### [OK] Deve ser possível também cadastrar os funcionários Alunos e Professores, contendo no mínimo: número de matrícula, nome, email, telefone, cargo/curso, e nível de permissão ao sistema (usuário/administrador) . 

#### [OK] No cadastro da pessoa também deve ser possível indicar quais das salas cadastradas a pessoa poderá utilizar. E por sua vez o cadastro de pessoas também deve indicar quais salas uma pessoa é cadastrada.

#### O sistema deve portanto controlar o acesso às salas. Ao abrir o sistema, é perguntado o que a pessoa deseja fazer. Abrir a porta ou abrir o gerencialmente Adm. Caso opte por abrir o gerenciamento, verifica se é ADM. Caso seja administrador abre-se opções de verificação de acesso ou gerenciamento do sistema (cadastro de salas e alunos, permissão ou retirada de acesso e relatório de acessos em uma determinada sala ou acessos feitos por uma pessoa).

#### Para permitir a entrada em uma das salas, o sistema deve verificar primeiramente se a matrícula da pessoa é válida (se existe algum pessoa cadastrado com aquele número de matrícula) e, em caso positivo, verificar se a pessoa possui permissão para entrar na sala.

#### Cada vez que uma sala é acessada por uma pessoa, deve ser registrado um evento do acesso permitido. Deve ser registrado: data e hora do acesso, matrícula da pessoa, o número da sala e uma informação de “Acesso Permitido” ou "Acesso Negado", caso seja negado o motivo da negação de acesso.

#### Se uma pessoa tentar acessar a sala, digitando sua matrícula, e tiver seu acesso negado, 
##### deve ser emitida uma mensagem com o motivo ("matrícula não existe, não possui acesso à sala, ou acesso bloqueado") 

##### e deve ser armazenado um registro da data e hora da tentativa de acesso negada. Deve ser registrado: data e hora da tentativa de acesso, matrícula do funcionário, número da sala e o motivo de negação de acesso.

#### Também deve ser possível pesquisar pelos registros de acesso negados ou permitidos. 
##### O sistema deve permitir emitir um relatório de acessos às salas, onde seja possível pesquisar pela matrícula da pessoa ou pelo número da sala.

### Considere algumas regras:
1. Não podem existir duas pessoas cadastradas com o mesmo número de matrícula. Ao se
tentar cadastrar uma pessoa com número de matrícula já existente, o sistema deve impedir
e retornar mensagem de erro informando que já existe uma pessoa com o mesmo número
de matrícula.
2. A mesma regra se aplica às salas pelo número de identificação.
3. Se uma mesma pessoa (mesmo número de matrícula) tentar acesso mais de três vezes a
salas não permitidos, deve ser bloqueado o acesso daquela pessoa, emitindo uma
mensagem na tela e registrando um evento de negação de acesso do tipo “acesso bloqueado”.

### Restrições de escopo:
O sistema contempla somente algumas das funcionalidades de um sistema de controle de um claviculário eletrônico, não abordando funcionalidades avançadas, de forma a facilitar a sua implementação.

## Entrega:
Parte 1: Deve ser postado um arquivo ZIP por equipe no Moodle até o
dia 10/05/2019 às 23:55hs, contendo:
• Documento em formato PDF contendo a divisão das atividades entre os membros da
equipe;
• Todas figuras com os diagramas de classes da solução a ser implementada, seguindo a
notação UML 2. Deve ser elaborado um diagrama de classes por funcionalidade do
sistema, englobando: controladores, classes de apresentação e as entidades.

Parte 2: Deve ser postado um arquivo ZIP por equipe no Moodle até o
dia 20/05/2019 às 18hs, contendo:
• Código fonte completo do sistema orientado a objetos em Java.
• Figuras contendo os diagramas de classes atualizados seguindo a notação UML 2. Deve
ser elaborado um diagrama de classes por funcionalidade do sistema, englobando:
controladores, classes de apresentação e as entidades.

### Critérios de Avaliação:
1. Qualidade da solução do problema apresentado atendendo ao escopo do desenvolvimento (4,5 pontos), observando os seguintes cenários:
 1.1 Cadastrar funcionários (0,5 pontos).
 1.2 Cadastrar veículos (0,5 pontos).
 1.3 Realizar validação de acesso (1,5 pontos).
 1.4 Registrar eventos de acesso, negação de acesso e devolução (1,0 ponto).
 1.5 Gerar relatório/pesquisa de eventos de acesso (1,0 pontos).
2. Descrição detalhada da divisão das atividades de implementação entre os membros da equipe (0,5 ponto) (Entrega dia 10/05/2019).
3. Qualidade, uso da notação e consistência do diagrama de classes (1,0 ponto) (Entrega dia 10/05/2019).
4. Utilização correta de: associação, agregação e composição (1,5 pontos).
5. Utilização, de maneira adequada, de: herança, interface e enum (1,5 pontos).
6. Tratamento todas as exceções (1,0 pontos).

### Defesas dos Trabalhos:
A apresentação será realizada em laboratório uma equipe de cada vez, por sorteio, onde o
professor irá questionar os membros da equipe quanto à implementação realizada e aos
conceitos aplicados nos trabalhos. A nota de cada membro da equipe será individual,
dependendo da sua participação individual no desenvolvimento do trabalho e na sua capacidade.
de explicar o código desenvolvido. Qualquer membro da equipe que não estiver presente na
apresentação receberá 50% da média das notas da equipe no trabalho.

## Materiais de estudo para implementação do trabalho
##### Para implementação da autenticação usando interfaces - https://www.caelum.com.br/apostila-java-orientacao-objetos/interfaces/#interfaces
#### Dicas
##### É preciso separar o controlador por responsabilidades
##### modelo fala com controle, controle fala com view.
##### padrões de projeto
##### Gang of four - padrão de projeto Mediator
##### j2ee - busness delecate
##### Não mais que 3 entidades

Descrição do Trabalho 02:

Utilizando como base o sistema desenvolvido no Trabalho 01, complementar o trabalho anterior incluindo persistência em arquivo e interface gráfica.

Critérios de Avaliação:

1. Qualidade da solução do problema apresentado atendendo ao escopo do desenvolvimento (3,0 pontos), observando os seguintes cenários:

1.1 Ao menos um cadastro, contemplando: inclusão, exclusão, alteração e listagem (1,0 pontos).
1.2 Registro (inclusão, exclusão, alteração e listagem) de ao menos um tipo de emissão de documento, movimentação, log de uso, jogada do jogador, etc. (1,0 pontos).
1.3 Geração de ao menos um relatório/pesquisa de registros armazenados (1,0 pontos).

2. Qualidade da notação UML no diagrama de classes e sua consistência com o código (0,5 pontos).

3. Interface Gráfica Java Swing funcional. A interface gráfica deve ter sido completamente desenvolvida de forma manual, sem apoio de ferramenta para desenhar a tela. (1,5 pontos)

4. Camada de persistência (em arquivo) implementada corretamente, gravando todos os cadastros e registros em arquivos e permitindo a sua recuperação (1,5 pontos).

5. Utilização correta do MVC e da separação em camadas (2,0 pontos)

6. Implementação de ao menos um exemplo de Polimorfismo (0,5 pontos)

7. Tratamento correto de todas as exceções, utilizando ao menos duas classes de exceção estendidas (1,0 ponto).

