CREATE TABLE authorization (
  tenantId NUMERIC(19, 0) NOT NULL,
  id NUMERIC(19, 0) NOT NULL,
  programName NVARCHAR(255) NOT NULL,
  programSecurityToken NVARCHAR(512) NOT NULL,
  UNIQUE (tenantId, programName, programSecurityToken),
  PRIMARY KEY (tenantId, id)
)
GO
