CREATE TABLE authorization (
  tenantId NUMBER(19, 0) NOT NULL,
  id NUMBER(19, 0) NOT NULL,
  programName VARCHAR2(255) NOT NULL,
  programSecurityToken VARCHAR2(255) NOT NULL,
  UNIQUE (tenantId, programName, programSecurityToken),
  PRIMARY KEY (tenantId, id)
);
