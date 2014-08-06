CREATE TABLE authorized_program (
  tenantId NUMERIC(19, 0) NOT NULL,
  id NUMERIC(19, 0) NOT NULL,
  programName NVARCHAR(255) NOT NULL,
  programSecurityToken NVARCHAR(512) NOT NULL,
  UNIQUE (tenantId, programName),
  PRIMARY KEY (tenantId, id)
)
GO
