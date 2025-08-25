
## **1️⃣ Estrutura de Milestones**

| Milestone             | Backend                                                       | Frontend                                         | Data final |
| --------------------- | ------------------------------------------------------------- | ------------------------------------------------ | ---------- |
| Infra & Setup         | Configuração do repositório, banco, CI/CD, integração inicial | Setup React, roteamento, integração com backend  | 28/08      |
| Autenticação & Perfis | Cadastro/login, JWT, roles (STUDENT, INSTRUCTOR, ADMIN)       | Telas de login e registro, fluxo JWT             | 28/08      |
| Gestão de Cursos      | CRUD de cursos, matrículas, controle de progresso             | Páginas de catálogo, detalhe do curso, inscrição | 04/09      |
| Frontend Student      | Endpoints catálogo/inscrição, integração com backend          | Dashboard do estudante                           | 04/09      |
| Frontend Instructor   | Endpoints criação/listagem/gerenciamento cursos               | Dashboard instrutor                              | 11/09      |
| Frontend Admin        | Endpoints CRUD usuários, dashboard métricas                   | Dashboard admin                                  | 11/09      |
| Extras                | Upload de materiais, tracking, quiz                           | Upload de material, exibição de progresso, quiz  | 15/09      |

----------

## **2️⃣ Labels sugeridas**

-   **Por área**: `backend`, `frontend`, `fullstack`    
-   **Por papel**: `student`, `instructor`, `admin`
-   **Por tipo de tarefa**: `bug`, `enhancement`, `documentation`
-   **Por prioridade**: `urgent`, `low-priority`
-   **Good first issue**: para iniciantes ou tarefas pequenas
    
----------

## **3️⃣ Fluxo de criação de Issues**

1.  Abrir uma **nova issue** usando os templates `.github/ISSUE_TEMPLATE/`
    
    -   `bug_report.yml` ou `feature_request.yml`
        
2.  Atribuir **milestone correspondente**
    
3.  Atribuir **labels corretas** (área, tipo de tarefa, papel)
    
4.  Designar **assignee** (quem vai implementar)
    

💡 **Exemplo:**

-   Bug de login JWT → `bug`, `backend`, `student`, milestone “Autenticação & Perfis”, assignee: Dev responsável pelo auth.
    

----------

## **4️⃣ Estrutura de branches sugerida**

### Branch principal

-   **`main`** → branch estável, só recebe merges aprovados via PR.
    

### Branches de desenvolvimento

-   **`develop`** → opcional, branch integradora de features antes do merge para `main` (útil se quiser testar várias features juntas).
    

### Branches de features

-   Nome padrão: `feature/<área>/<descrição>`
    
-   Exemplos:
    
    -   `feature/students/get-all` → listar todos os estudantes
        
    -   `feature/courses/create` → criar curso novo
        
    -   `feature/auth/login` → implementação de login JWT
        
-   Boas práticas:
    
    -   Lowercase, separadas por `-`
        
    -   Evitar nomes genéricos como `feature1`
        
    -   Refletir claramente **o que está sendo implementado e para qual área**
        

### Branches de bugs

-   Nome padrão: `bugfix/<área>/<descrição>`
    
-   Exemplos:
    
    -   `bugfix/students/login-error` → corrigir erro de login do estudante
        
    -   `bugfix/courses/empty-catalog` → corrigir catálogo vazio
        

### Branches de documentação

-   Nome padrão: `docs/<área>/<descrição>`
    
-   Exemplos:
    
    -   `docs/students/swagger` → documentação Swagger para endpoints de estudante
        
    -   `docs/setup/readme` → documentação de setup do projeto
        

### Branches experimentais/testes

-   Nome padrão: `experiment/<descrição>`
    
-   Exemplo: `experiment/chatbot-integration` → testar integração de IA
    

----------

## **5️⃣ Regras de nomenclatura**

1.  Todas em **lowercase**
    
2.  Usar **slash `/`** para separar categoria e descrição
    
3.  Usar **hífen `-`** dentro da descrição
    
4.  Evitar caracteres especiais
    
5.  Sempre referenciar a **issue** relacionada na PR
    

----------

## **6️⃣ Fluxo de criação de branches e PRs**

1.  Criar branch a partir da **`main`** ou **`develop`**:
    

```
git checkout main
git pull origin main
git checkout -b feature/students/get-all
```

2.  Implementar a feature ou correção
    
3.  Commitar pequenas alterações com mensagens claras, ex.:
    
```
feat(students): Adiciona endpoint GET /students

bugfix(admin): Corrige bug de catálogo vazio
```

4.  Subir para o repositório remoto:
    
`git push origin feature/students/get-all` 

5.  Abrir **Pull Request** contra `main` (ou `develop` se estiver usando branch de integração)
    
6.  Usar **PULL_REQUEST_TEMPLATE.md**: checklist, descrição, issue relacionada
    
7.  Revisão obrigatória por pelo menos 1 colega antes do merge
    
8.  Merge aprovado → branch pode ser deletada para manter repositório limpo

----------
## **7️⃣ Boas práticas**

-   Usar **naming conventions** para branches:
    
    -   `feature/<descrição>` → novas funcionalidades
        
    -   `bugfix/<descrição>` → correções de bug
        
-   Manter **milestones atualizadas** para controle do cronograma
    
-   Integrar **GitHub Actions** para CI/CD desde o início
    
-   Manter **PRs pequenos** e revisáveis
    
-   Revisões obrigatórias ajudam a manter **qualidade e aprendizado do time**
  ---------
## **8️⃣ Conventional Commits**

A estrutura básica é:

`<tipo>(escopo opcional): descrição breve`
Exemplo: 
`feat(students): add endpoint to get all students`


### 📌 Tipos principais (mais usados)

| Tipo         | Uso                                                                      | Exemplo                                             |
| ------------ | ------------------------------------------------------------------------ | --------------------------------------------------- |
| **feat**     | Nova funcionalidade                                                      | `feat(auth): add JWT token validation`              |
| **fix**      | Correção de bug                                                          | `fix(users): adjust email validation regex`         |
| **docs**     | Alteração de documentação (README, Swagger, etc.)                        | `docs(students): update swagger with new endpoint`  |
| **style**    | Mudanças de formatação, sem impacto no código (espaço, indentação, etc.) | `style(lint): fix indentation issues`               |
| **refactor** | Refatoração de código (sem mudar comportamento)                          | `refactor(rentals): simplify calculation logic`     |
| **test**     | Adição ou ajuste de testes                                               | `test(users): add unit test for login endpoint`     |
| **chore**    | Tarefas de manutenção (configs, CI/CD, dependências)                     | `chore(ci): update GitHub Actions workflow`         |
| **perf**     | Melhorias de performance                                                 | `perf(api): optimize query performance for rentals` |

----------

### 📌 Escopo (opcional, mas recomendado)

O **escopo** ajuda a indicar **onde** a mudança foi feita (pode ser um módulo, entidade, ou parte da aplicação).  
Exemplos:

-   `students`, `auth`, `rentals`, `db`, `ci`, `docs`    

Exemplo prático:

```
feat(rentals): implement vehicle return endpoint

feat(student): add endpoint to get all students

fix(auth): resolve bug when token expires before refresh

docs(api): update swagger for students endpoints

test(service): add unit tests for rental service
```

----------

### 📌 Descrição (curta e clara)

Sempre em **letra minúscula**, frase curta, no **imperativo** (como se fosse uma ordem).  
👉 Correto: `fix(users): update password validation`  
👉 Errado: `fixed password validation` ou `updates password validation`

----------

### 📌 Corpo do commit (opcional, mas útil)

Se precisar detalhar:

`feat(students): add endpoint to  get  all students

- Added new  GET /students endpoint
- Includes pagination and sorting
- Updated service and repository layers` 

----------

### 📌 Footer (para breaking changes ou referências)

Se for uma mudança que **quebra compatibilidade**:
```
feat(api): change response format  of student endpoint
BREAKING CHANGE: The GET /students endpoint now returns an array  instead  of an object.
```

Ou para linkar issues:

`fix(auth): correct login token expiration Closes #42` 

----------

## **9️⃣ Branch Protection & Status Checks**

-   Branch principal: `main`
    
-   Regras:
    
    -   **Require pull request reviews before merging**
        
    -   **Require status checks to pass** (ex.: CI backend, CI frontend)
        
    -   **Do not allow bypassing the above settings** → protege admins
        
    -   **Restrict who can push** → só PR via review, ninguém push direto
        
-   **Status checks sugeridos**:
    
    -   Backend: `gradlew build` + `gradlew test`
        
    -   Frontend: `npm install && npm run build` + `npm test`
        

----------

## **🔟 Organização de workflow visual**

`Milestones → Issues → Branch (feature/bugfix/docs) → Commit → Push → Pull Request → Revisão obrigatória → Status Checks → Merge em  main → Branch deletada` 

-   **Milestones**: agrupam issues por datas de entrega
    
-   **Issues**: descrevem a tarefa detalhada, com labels e checklist
    -   Labels ajudam a filtrar e priorizar tarefas
    
-   **Branch**: criada a partir de main/develop, nomeada seguindo padrão
    
-   **PR**: revisado, testado, status checks passam
    
-   **Merge**: só via PR aprovado
    
-   **Limpeza**: deletar branch após merge

---------
