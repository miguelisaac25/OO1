🎬 Movie Review API (Javalin + JDBI)

Este é um sistema de back-end para gerenciamento de filmes, diretores, atores e avaliações, construído com Java e o micro-framework Javalin. O projeto utiliza uma arquitetura limpa em camadas para acesso a dados.

🛠️ Tecnologias Utilizadas

Linguagem: Java 17+

Web Framework: Javalin (API REST)

Persistência: PostgreSQL (via Supabase)

Driver: JDBI (Substitui o JDBC manual, seguindo o Padrão Repository).

Estrutura: Controller ➡️ Service ➡️ Repository.

🧱 Estrutura do Código

A aplicação é organizada em pacotes para garantir a Separação de Responsabilidades:

model: Classes de Entidade (POJOs), incluindo a Herança (Pessoa -> Ator/Diretor).

repository: Interfaces que definem as operações de CRUD, utilizando JDBI para mapear o SQL.

service: Contém a lógica de negócio, validações e coordenação de transações (Herança e N:M).

controller: Camada de apresentação que lida com as requisições HTTP (Status Codes, JSON).

🚀 Como Executar

Configuração do Banco: Certifique-se de que as credenciais do Supabase estão configuradas no arquivo .env. O método DatabaseConfig.setup() recriará todas as tabelas e dados iniciais (incluindo a estrutura de Herança e N:M) na inicialização.

Execução:

mvn compile exec:java -Dexec.mainClass="com.sistema.App"


Endpoint: A API estará disponível em http://localhost:7070.

🌐 Endpoints da API

A API suporta CRUD completo para as entidades, além de rotas especializadas.

Entidade

Rotas Base

Detalhe

Geral

GET /

Status da API.

Usuários

/usuarios, /usuarios/{id}

CRUD simples.

Herança

/atores, /diretores, /diretores/{id}

Criação e leitura de subclasses de Pessoa.

Filmes

/filmes, /filmes/{id}

CRUD. O POST e PUT gerenciam as associações N:M (Gêneros e Atores) em uma transação.

Avaliações

/avaliacoes

Criação de avaliações.

Chave Composta

/avaliacoes/{filmeId}/{usuarioId}

GET, PUT, DELETE usando a chave primária composta.
