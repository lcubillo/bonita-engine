CREATE TABLE document_content (
  tenantid BIGINT NOT NULL,
  id BIGINT NOT NULL,
  documentId VARCHAR(50) NOT NULL,
  content LONGBLOB NOT NULL,
  PRIMARY KEY (tenantid, id)
);
CREATE TABLE document_mapping (
  tenantid BIGINT NOT NULL,
  id BIGINT NOT NULL,
  processinstanceid BIGINT,
  documentName VARCHAR(50) NOT NULL,
  documentAuthor BIGINT,
  documentCreationDate BIGINT NOT NULL,
  documentHasContent BOOLEAN NOT NULL,
  documentContentFileName VARCHAR(255),
  documentContentMimeType VARCHAR(255),
  contentStorageId VARCHAR(50),
  documentURL VARCHAR(255),
  PRIMARY KEY (tenantid, ID)
);