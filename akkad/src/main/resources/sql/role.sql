CREATE TABLE role (
    id          BIGSERIAL PRIMARY KEY,
    code        VARCHAR(50) NOT NULL UNIQUE,  
    name        VARCHAR(50) NOT NULL,         
    description VARCHAR(255),                 
    created_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_role_code ON role(code);
CREATE INDEX idx_role_name ON role(name);

COMMENT ON TABLE role IS '用户角色';
COMMENT ON COLUMN role.code IS '角色识别码';
COMMENT ON COLUMN role.name IS '角色名称';
COMMENT ON COLUMN role.description IS '角色描述';