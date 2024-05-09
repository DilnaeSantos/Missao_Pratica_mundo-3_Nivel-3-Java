📝 Relatório discente de acompanhamento

## Informações do Curso

- **Curso:** Estácio Desenvolvimento Full Stack
- **Universidade:** Estácio de Sá - Campus São José dos Pinhais
- **Período:** 3º
- **Turma:** 9001
- **Tutor:** Simone Ingrid Monteiro Gama
- **Matéria:** BackEnd sem banco não tem (RPG0016)

## Informações do Aluno

- **Nome:** Dilnae Rennan Souza dos Santos
- **Matrícula:** 202302631631


## 1º Procedimento | Mapeamento Objeto-Relacional e DAO

a) A importância dos componentes de middleware, como o JDBC, reside na sua capacidade de fornecer uma camada de abstração entre o código da aplicação e o banco de dados. Com o JDBC, é possível estabelecer conexões com o banco de dados, enviar consultas e atualizações e recuperar resultados de forma eficiente e segura.

b) A principal diferença entre o uso de Statement e PreparedStatement está na segurança e performance. O PreparedStatement permite a pré-compilação de consultas SQL, o que reduz o risco de injeção de SQL e melhora o desempenho ao executar consultas múltiplas.

c) O padrão DAO (Data Access Object) melhora a manutenibilidade do software ao separar a lógica de acesso a dados da lógica de negócios da aplicação. Isso permite que as classes de negócios operem com objetos de domínio sem precisar saber como os dados são armazenados ou recuperados.

d) No modelo estritamente relacional, a herança não é diretamente refletida no banco de dados. Em vez disso, ela é mapeada para o banco de dados usando técnicas como tabelas separadas para cada classe filha (tabelas de junção) ou uma tabela única com uma coluna de tipo discriminadora.

## 2º Procedimento | Alimentando a Base

a) As principais diferenças entre a persistência em arquivo e a persistência em banco de dados estão na eficiência, escalabilidade e capacidade de consulta. Enquanto a persistência em arquivo pode ser simples e adequada para pequenos conjuntos de dados, a persistência em banco de dados oferece melhor desempenho, gerenciamento de transações e capacidade de consulta complexa.

b) O uso de operador lambda simplificou a impressão dos valores contidos nas entidades, nas versões mais recentes do Java, ao permitir uma sintaxe mais concisa para iterar sobre coleções de elementos.

c) Métodos acionados diretamente pelo método main, sem o uso de um objeto, precisam ser marcados como static porque o método main é estático e só pode chamar outros métodos estáticos diretamente.

## Conclusão

Nesta missão prática, foi possível entender a importância do mapeamento objeto-relacional e do padrão DAO na construção de aplicações robustas e de fácil manutenção. Através da implementação dos procedimentos propostos, pudemos explorar conceitos fundamentais de persistência de dados e suas implicações no desenvolvimento de software. O uso adequado de componentes de middleware, como o JDBC, juntamente com boas práticas de design de software, contribui para a criação de sistemas escaláveis e eficientes.
