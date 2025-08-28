CREATE TYPE role_enum AS ENUM ('STUDENT','INSTRUCTOR','ADMIN');

CREATE TABLE users (
  id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  name          VARCHAR(120) NOT NULL,
  email         VARCHAR(160) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  role          role_enum NOT NULL DEFAULT 'STUDENT',
  active        BOOLEAN NOT NULL DEFAULT TRUE,
  created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW(),
  last_login    TIMESTAMPTZ
);


CREATE UNIQUE INDEX ux_users_email_ci ON users (LOWER(email));
