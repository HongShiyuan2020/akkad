CREATE TABLE user_role(
    id  BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    role_id BIGINT
);

CREATE INDEX idx_user_role_user_role ON user_role(user_id, role_id);