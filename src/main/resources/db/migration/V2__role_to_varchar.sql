-- Converte users.role de role_enum -> varchar(20) e adiciona um CHECK
BEGIN;

-- Se existir DEFAULT tipado como role_enum, remova e recrie depois
ALTER TABLE users ALTER COLUMN role DROP DEFAULT;

-- Converte o tipo (o USING faz cast do enum para texto)
ALTER TABLE users
  ALTER COLUMN role TYPE VARCHAR(20)
  USING role::text;

-- (opcional) redefine default
ALTER TABLE users
  ALTER COLUMN role SET DEFAULT 'STUDENT';

-- Garante os valores válidos
ALTER TABLE users
  DROP CONSTRAINT IF EXISTS chk_users_role,
  ADD CONSTRAINT chk_users_role
    CHECK (role IN ('STUDENT','INSTRUCTOR','ADMIN'));

-- Se não houver mais nenhuma coluna usando o tipo, pode remover o enum do banco
DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'role_enum') THEN
    DROP TYPE role_enum;
  END IF;
END$$;

COMMIT;