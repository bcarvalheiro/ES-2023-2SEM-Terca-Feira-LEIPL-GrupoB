
[logo]: (https://github.com/bcarvalheiro/ES-2023-2SEM-Terca-Feira-LEIPL-GrupoB/blob/master/Windows_11_Clippy_paperclip_emoji.png) "Calendar Schedule"
# [logo] Horário - Conversor e Visualizador
Este é um projeto desenvolvido em equipa para a disciplina de Engenharia de Software, com o objetivo de criar uma aplicação Java capaz de carregar um horário em formato CSV ou JSON, converter para o outro formato e visualizar em HTML, permitindo ainda a criação de horários para estudantes e a sobreposição e sobrelotação de aulas.

## Funcionalidades
- Carregamento de horário em formato CSV ou JSON, conversão para o outro formato e gravação do horário resultante.
- Visualização do horário em HTML, permitindo navegação por dia, semana e mês.
- Criação de horário para estudantes a partir dos dados do horário na plataforma Fénix.
- Criação de horário para estudantes a partir de um subconjunto de unidades curriculares de um horário carregado para a aplicação.
- Visualização das aulas em sobreposição e sobrelotação.

## Tecnologias Utilizadas
- Ambiente de programação em Java em IntelliJ.
- Git para gestão de configurações e controlo de versões, com plugin do Git no IDE.
- GitHub para gestão de configurações e controlo de versões remoto, suporte ao trabalho colaborativo e entrega do projeto
- Maven para gestão das dependências de bibliotecas que forem reutilizadas, com plugin no IDE
- SonarLint para prevenção de defeitos, vulnerabilidades, cheiros no código e más práticas de programação no repositório local antes de cada commit, com plugin no IDE
- SonarCloud para avaliação da qualidade do software do projeto como um todo no repositório remoto
- JUnit 5 para construção da bateria de testes unitários, com plugin no IDE
- JaCoCo para avaliação da cobertura da bateria de testes unitários, com plugin no IDE
-Trello com PowerUp do GitHub para gestão do projeto segundo abordagem Scrum, com rastreio entre os cartões das user stories e os eventos respetivos no GitHub (commits, pull requests e issues)

## Participantes
- Ana Rita Pereira Francisco (94639) - RitaAPF
- Bruno Alexandre Guinapo Carvalheiro (94514) - bcarvalheiro
- Carolina Varanda Roque (98494) - CarolinaVRoque
- Catarina Alexandre do Carmo Loureiro (94575) - neoeve
- Nuno Filipe de Oliveira Monteiro (94587) - bitilitos
- Diogo Miguel Leal da Costa (94466) - DiogoM107

## Como Usar
- Primeiro deve fazer um import de um ficheiro CSV ou JSON através do botão "Upload Horario". O ficheiro deve ter o formato : 
    
    |Curso|UnidadeCurricular|Turno|Turma|Inscritos no turno|Dia da semana|Hora início da aula|Hora fim da aula|Data da aula|Sala atribuída à aula|Lotação da sala|
    |-----|:---------------:|:---:|:---:|:----------------:|:-----------:|:-----------------:|:--------------:|:----------:|:--------------------:|-------------:|
    |CT|Gestão|00668TP02|CI-CT-02|24|Qui|16:00:00|17:00:00|28/04/2023|AA3.30|24
    
- Após carregamento do horário em sistema, consegue iterar as visualizações entre dia, semana e mês através dos botões.
- Possui dois botões para avançar no dia/mes/semana, e um para se posicionar no dia/mes/semana actual.
- Se o calendário estiver importado, através do botão "Gravar Horário" conseguirá exportar o ficheiro em formato CSV ou JSon
- Para criação de um calendário pessoal:
  - Pode importar um ficheiro com aulas ou
  - Através do horário actual importado, ir clicando aula a aula, e adicionar ao seu horario pessoal   

## Metodologia de trabalho Git
- Criar branch a partir do master para issue especifico. Nome da branch deve informar qual o issue (ex.: feature/conversion-csv-to-json)
- Fazer checkout para branch criada
- Fazer alterações ao código
- Fazer commit/push
- Assim que terminado, fazer checkout para master, pull (para ir buscar a última versão), e fazer merge. Seleccionar a branch que estavamos a trabalhar e aceitar. Poderão aparecer conflitos, que terão que ser resolvidos manualmente. 


## Dificuldades encontradas
- Utilizamos o PowerUP do Trello BurndownDown by Corello e passado uma semana recebemos uma notificação para pagar a licença do software. Assim tivemos que a meio do Sprint 1 alterar o nosso Burndown Chart o que teve efeitos na apresentação do mesmo.
- Como tinhamos que alterar o BurnDown Chart, aproveitamos para alterar o quadro do sprint 1 porque o mesmo tinha sido criado pela conta pessoal de um elemento do grupo. 
- Os calendários importados através de webcal (exemplo calendário do fénix), não tem certos campos, como é o exemplo de lotação, curso, dia da semana ou turma.
- Tivemos dificuldade ao testar as funções de importar o horário do fénix devido à necessidade de cookies
- As aulas sobrepostas não ficaram com uma boa representação na GUI do programa
