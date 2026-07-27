CREATE TABLE permission (
    id          BIGSERIAL PRIMARY KEY,
    parent_id   BIGINT
    code        VARCHAR(100) NOT NULL UNIQUE, -- 权限标识（如 user:create, order:export）
    name        VARCHAR(50)  NOT NULL,        -- 权限/菜单名称
    type        VARCHAR(20)  NOT NULL,        -- 类型：MENU(菜单), BUTTON(按钮), API(接口)
    path        VARCHAR(255),                 -- 前端路由路径或 API URL 匹配规则
    method      VARCHAR(10),                  -- 请求方式（GET, POST, PUT, DELETE）
    sort_order  INT          DEFAULT 0,        -- 排序字段
    created_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_permission_parent_id ON permission(parent_id);
CREATE INDEX idx_permission_code ON permission(code);
CREATE INDEX idx_permission_name ON permission(name);

COMMENT ON TABLE permission IS '权限管理';
COMMENT ON COLUMN permission.parent_id IS '父权限id，用于树状管理';
COMMENT ON COLUMN permission.code IS '权限标识代码';
COMMENT ON COLUMN permission.name IS '权限名称';
COMMENT ON COLUMN permission.type IS '权限类型：MENU（菜单） BUTTON（按钮） API（接口）';
COMMENT ON COLUMN permission.path IS '前端路由 或 API URL 匹配规则';
COMMENT ON COLUMN permission.method IS '请求方法';
COMMENT ON COLUMN permission.sort_order IS '排序字段';