# Pet Bem-Estar - Sistema de gestão para petshop
Sistem web desenvolvido em java com spring boot, thymeleaf, JPAHibernate para gerenciamento de tutores e animais na pet shop

## Funcionalidades implementadas
- Cadastro de clientes(Tutores): cadastro, listagem, edição e remoção com validaçao de campos obrigatórios(nome, telefone, email único)
- Cadastro de animais: cadastro, listagem, edição e remoção vinculados a um tutor existente, com seleçaõ de tipo e porte do animal pré-definidos.
- Consulta de animais por cliente página de detalhes do tutor exibindo seus dados e todos os animais cadastrados de um tutor.
- Autenticação e controle de acesso: cadastro e login de funcionarios da pet com senha criptografada com BCrypt, controle de sessão (HttpSession) e proteção de rotas internas via LoginInterceptor
- Identidade visual: aplicação de logotipo oficial e da paleta de cores do Pet Bem-Estar(verde do petshop: #2E8B7A, laranja dos detalhes: #F28C45, fundo creme: #FFF8EE, letras: #3B3A36

## Pré-requisitos 
- Java 17 ou superior
- Maven
- MySQL

## Como configurar e executar
- Inicie o MySQL no seu ambiente local (ex: XAMPP ou serviço do MySQL na porta 3306)
- Confira o acesso ao banco
- Abra o arquivo `src/main/resources/application.properties` e `spring.datasource.password`conforme o seu MySQL local, está configurado com root e sem senha. Tanto o banco de dados como as tabelas são criados automaticamente ao iniciar a aplicação.

Execute a aplicação
Pelo Eclipse/IDE: execute a classe `PetbemestarApplication.java`com o botão direito run as > java application.

Acesse no navegador:
abra: `http://localhost:8080/login`

## Para fazer o teste
Caso o banco esteja vazio, clique em criar usuário na tela de login
Usuário: `Marlene`
Senha: `12345` 





## Remover um cliente com bichos

Apareceu status 500
Tutor está vinculado a um ou mais animais, então não posso clicar para excluir tutor antes de excluir animais.