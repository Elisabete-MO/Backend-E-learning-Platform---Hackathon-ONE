# 📘 Projeto Plataforma de Cursos

----------

## 🔹 Milestones Backend

### **Infra & Setup (até 28/08)**

-   **Configuração do repositório backend** – `infra`
    
-   **Configuração do banco de dados** – `infra`
    
-   **Setup do CI/CD** – `infra`
    
-   **Integração inicial do backend** – `infra`, `fullstack`
    

----------

### **Autenticação & Perfis (até 28/08)**

-   **Cadastro de usuário** – `backend`, `api`, `student`, `instructor`, `admin`
    
    -   `POST /auth/register` – Cria novo usuário com role
        
-   **Login de usuário** – `backend`, `api`
    
    -   `POST /auth/login` – Retorna JWT
        
-   **Middleware de roles** – `backend`, `api`
    
    -   Controla acesso baseado no JWT e role
        

----------

### **Gestão de Cursos (até 04/09)**

-   **Criar curso** – `backend`, `api`, `instructor`
    
    -   `POST /courses` – Cria curso com título, descrição e módulos
        
-   **Listar cursos** – `backend`, `api`, `student`
    
    -   `GET /courses`
        
-   **Detalhe de curso** – `backend`, `api`, `student`
    
    -   `GET /courses/{id}`
        
-   **Atualizar curso** – `backend`, `api`, `instructor`
    
    -   `PUT /courses/{id}`
        
-   **Deletar curso** – `backend`, `api`, `instructor`
    
    -   `DELETE /courses/{id}`
        
-   **Inscrever aluno em curso** – `backend`, `api`, `student`
    
    -   `POST /courses/{id}/enroll`
        
-   **Progresso do aluno** – `backend`, `api`, `student`
    
    -   `GET /students/{id}/progress`
        

----------

### **Frontend Student (APIs Backend) (até 04/09)**

-   **Catálogo de cursos** – `frontend`, `student`
    
    -   `GET /courses`
        
-   **Detalhe do curso** – `frontend`, `student`
    
    -   `GET /courses/{id}`
        
-   **Inscrição em curso** – `frontend`, `student`
    
    -   `POST /courses/{id}/enroll`
        
-   **Progresso do aluno** – `frontend`, `student`
    
    -   `GET /students/{id}/progress`
        

----------

### **Frontend Instructor (APIs Backend) (até 11/09)**

-   **Criar curso** – `frontend`, `instructor`
    
    -   `POST /instructor/courses`
        
-   **Listar cursos do instrutor** – `frontend`, `instructor`
    
    -   `GET /instructor/courses`
        
-   **Atualizar curso** – `frontend`, `instructor`
    
    -   `PUT /instructor/courses/{id}`
        
-   **Deletar curso** – `frontend`, `instructor`
    
    -   `DELETE /instructor/courses/{id}`
        
-   **Gerenciar módulos/aulas** – `frontend`, `instructor`
    
    -   `POST/PUT/DELETE /instructor/modules`
        

----------

### **Frontend Admin (APIs Backend) (até 11/09)**

-   **Listagem de usuários** – `frontend`, `admin`
    
    -   `GET /admin/users`
        
-   **Criar/editar usuário** – `frontend`, `admin`
    
    -   `POST /admin/users`
        
    -   `PUT /admin/users/{id}`
        
-   **Deletar usuário** – `frontend`, `admin`
    
    -   `DELETE /admin/users/{id}`
        
-   **Listagem de cursos** – `frontend`, `admin`
    
    -   `GET /admin/courses`
        
-   **Ativar/desativar curso** – `frontend`, `admin`
    
    -   `PATCH /admin/courses/{id}/status`
        
-   **Relatórios básicos** – `frontend`, `admin`
    
    -   `GET /admin/reports/overview`
        

----------

### **Extras (Backend) (até 15/09)**

-   **Busca avançada de cursos** – `backend`, `api`, `extra`
    
    -   `GET /courses/search`
        
-   **Upload de materiais** – `backend`, `api`, `extra`
    
    -   `POST /uploads`
        
-   **Categorias e tags** – `backend`, `api`, `extra`
    
    -   `POST /admin/categories`
        
    -   `GET /categories`
        
-   **Favoritar cursos** – `backend`, `api`, `extra`, `student`
    
    -   `POST /students/{id}/favorites`
        
    -   `GET /students/{id}/favorites`
        
-   **Relatórios detalhados** – `backend`, `api`, `extra`, `admin`
    
    -   `GET /admin/reports/detailed`
        
-   **Feedback / Avaliações** – `backend`, `api`, `extra`, `student`
    
    -   `POST /courses/{id}/reviews`
        
    -   `GET /courses/{id}/reviews`
        

----------

## 🔹 Milestones Frontend

### **Infra & Setup (até 28/08)**

-   Configuração do projeto React – `frontend`, `infra`
    
-   Roteamento inicial (React Router) – `frontend`, `infra`
    
-   Integração com backend via API – `frontend`, `fullstack`
    

----------

### **Autenticação & Perfis (até 28/08)**

-   Tela de login – `frontend`, `student`, `instructor`, `admin`
    
-   Tela de registro – `frontend`, `student`, `instructor`
    
-   Fluxo de autenticação com JWT – `frontend`, `fullstack`
    
-   Redirecionamento baseado no perfil – `frontend`, `fullstack`
    

----------

### **Gestão de Cursos (até 04/09)**

-   Página catálogo de cursos – `frontend`, `student`
    
    -   `GET /courses`
        
-   Página detalhe do curso – `frontend`, `student`
    
    -   `GET /courses/{id}`
        
-   Botão/fluxo de inscrição – `frontend`, `student`
    
    -   `POST /courses/{id}/enroll`
        

----------

### **Frontend Student (até 04/09)**

-   Dashboard do estudante – `frontend`, `student`
    
-   Listagem de cursos inscritos – `frontend`, `student`
    
-   Visualização do progresso – `frontend`, `student`
    
    -   `GET /students/{id}/progress`
        

----------

### **Frontend Instructor (até 11/09)**

-   Dashboard do instrutor – `frontend`, `instructor`
    
-   Criação de cursos – `frontend`, `instructor`
    
    -   `POST /instructor/courses`
        
-   Listagem de cursos próprios – `frontend`, `instructor`
    
    -   `GET /instructor/courses`
        
-   Gestão de conteúdo dos cursos – `frontend`, `instructor`
    
    -   `POST/PUT/DELETE /instructor/modules`
        

----------

### **Frontend Admin (até 11/09)**

-   Dashboard do administrador – `frontend`, `admin`
    
-   CRUD de usuários – `frontend`, `admin`
    
    -   `GET/POST/PUT/DELETE /admin/users`
        
-   Visualização de métricas – `frontend`, `admin`
    
    -   `GET /admin/reports/overview`
        
-   Controle de permissões (somente admins) – `frontend`, `admin`
    

----------

### **Extras (Frontend) (até 15/09)**

-   Tela de busca e filtro de cursos – `frontend`, `student`
    
    -   `GET /courses/search`
        
-   Favoritar cursos – `frontend`, `student`
    
    -   `POST/GET /students/{id}/favorites`
        
-   Avaliações de curso – `frontend`, `student`
    
    -   `POST/GET /courses/{id}/reviews`
        
-   Dashboard do Admin com gráficos – `frontend`, `admin`
    
    -   `GET /admin/reports/detailed`
        
-   Upload e exibição de materiais – `frontend`, `instructor`, `admin`
    
    -   `POST /uploads`
        
-   Dark Mode – `frontend`, `student`, `instructor`, `admin`
    
-   Carrossel de cursos em destaque – `frontend`, `student`
