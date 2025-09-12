-- V3__seed_users.sql
-- Inserção de dados de teste na tabela users

INSERT INTO users (name, email, password_hash, role, active, created_at, last_login) VALUES
('Admin Master', 'admin@example.com', 'hashed_password_123', 'ADMIN', TRUE, NOW() - INTERVAL '30 days', NOW() - INTERVAL '1 day'),
('Marcos Silva', 'marcos.silva@example.com', 'hashed_password_456', 'INSTRUCTOR', TRUE, NOW() - INTERVAL '10 days', NOW() - INTERVAL '2 hours'),
('Ana Oliveira', 'ana.oliveira@example.com', 'hashed_password_789', 'INSTRUCTOR', FALSE, NOW() - INTERVAL '20 days', NULL),
('João Pereira', 'joao.pereira@example.com', 'hashed_password_101', 'STUDENT', TRUE, NOW() - INTERVAL '5 days', NOW() - INTERVAL '1 hour'),
('Maria Souza', 'maria.souza@example.com', 'hashed_password_202', 'STUDENT', TRUE, NOW() - INTERVAL '15 days', NOW() - INTERVAL '3 days'),
('Carlos Mendes', 'carlos.mendes@example.com', 'hashed_password_303', 'STUDENT', FALSE, NOW() - INTERVAL '40 days', NULL);
