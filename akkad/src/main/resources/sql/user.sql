CREATE TABLE user (
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL UNIQUE,   
    email         VARCHAR(100) UNIQUE,            
    password_hash VARCHAR(255) NOT NULL,          
    status        SMALLINT    NOT NULL DEFAULT 1, 
    icon_url      VARCHAR(255) NOT NULL DEFAULT '',
    created_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_user_username ON user(username);
CREATE INDEX idx_user_email ON user(email);

COMMENT ON TABLE user IS '系统用户基础信息表';
COMMENT ON COLUMN user.id IS '自增主键';
COMMENT ON COLUMN user.username IS '用户登录名';
COMMENT ON COLUMN user.email IS '电子邮箱';
COMMENT ON COLUMN user.status IS '账号状态：0-禁用，1-正常，2-锁定';
COMMENT ON COLUMN user.icon_url IS '头像链接';
COMMENT ON COLUMN user.created_at IS '记录创建时间';
COMMENT ON COLUMN user.updated_at IS '记录更新时间';