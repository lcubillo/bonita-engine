CREATE TABLE authorization (
  tenantId INT8 NOT NULL,
  id INT8 NOT NULL,
  programName VARCHAR(255) NOT NULL,
  programSecurityToken VARCHAR(512) NOT NULL,
  UNIQUE (tenantId, programName, programSecurityToken),
  PRIMARY KEY (tenantId, id)
);
