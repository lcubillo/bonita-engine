CREATE TABLE authorized_program (
  tenantId NUMBER(19, 0) NOT NULL,
  id NUMBER(19, 0) NOT NULL,
  programName VARCHAR2(255) NOT NULL,
  programSecurityToken VARCHAR2(512) NOT NULL,
  creationDate TIMESTAMP NOT NULL,
  UNIQUE (tenantId, programName),
  PRIMARY KEY (tenantId, id)
);
