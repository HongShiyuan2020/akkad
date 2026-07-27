CREATE TABLE role_permission (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT,
    permission_id BIGINT
);

CREATE INDEX idx_role_permission_role_permission ON role_permission(role_id, permission_id);