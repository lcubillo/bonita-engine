CREATE TABLE authorized_program (
  tenantId BIGINT NOT NULL,
  id BIGINT NOT NULL,
  programName VARCHAR(255) NOT NULL,
  programSecurityToken VARCHAR(512) NOT NULL,
  UNIQUE (tenantId, programName, programSecurityToken),
  PRIMARY KEY (tenantId, id)
) ENGINE = INNODB;
