CREATE TABLE IF NOT EXISTS ES_AGGREGATE (
  ID              UUID     PRIMARY KEY,
  VERSION         INTEGER  NOT NULL,
  AGGREGATE_TYPE  TEXT     NOT NULL
);

CREATE INDEX IF NOT EXISTS IDX_ES_AGGREGATE_AGGREGATE_TYPE ON ES_AGGREGATE (AGGREGATE_TYPE);

CREATE TABLE IF NOT EXISTS ES_EVENT (
  ID              BIGSERIAL  PRIMARY KEY,
  TRANSACTION_ID  XID8       NOT NULL,
  AGGREGATE_ID    UUID       NOT NULL REFERENCES ES_AGGREGATE (ID),
  VERSION         INTEGER    NOT NULL,
  EVENT_TYPE      TEXT       NOT NULL,
  JSON_DATA       JSON       NOT NULL,
  UNIQUE (AGGREGATE_ID, VERSION)
);

CREATE INDEX IF NOT EXISTS IDX_ES_EVENT_TRANSACTION_ID_ID ON ES_EVENT (TRANSACTION_ID, ID);
CREATE INDEX IF NOT EXISTS IDX_ES_EVENT_AGGREGATE_ID ON ES_EVENT (AGGREGATE_ID);
CREATE INDEX IF NOT EXISTS IDX_ES_EVENT_VERSION ON ES_EVENT (VERSION);

CREATE TABLE IF NOT EXISTS ES_AGGREGATE_SNAPSHOT (
  AGGREGATE_ID  UUID     NOT NULL REFERENCES ES_AGGREGATE (ID),
  VERSION       INTEGER  NOT NULL,
  JSON_DATA     JSON     NOT NULL,
  PRIMARY KEY (AGGREGATE_ID, VERSION)
);

CREATE INDEX IF NOT EXISTS IDX_ES_AGGREGATE_SNAPSHOT_AGGREGATE_ID ON ES_AGGREGATE_SNAPSHOT (AGGREGATE_ID);
CREATE INDEX IF NOT EXISTS IDX_ES_AGGREGATE_SNAPSHOT_VERSION ON ES_AGGREGATE_SNAPSHOT (VERSION);

CREATE TABLE IF NOT EXISTS ES_EVENT_SUBSCRIPTION (
  SUBSCRIPTION_NAME    TEXT    PRIMARY KEY,
  LAST_TRANSACTION_ID  XID8    NOT NULL,
  LAST_EVENT_ID        BIGINT  NOT NULL
);

CREATE TABLE IF NOT EXISTS RM_ORDER (
  ID              UUID                      PRIMARY KEY,
  VERSION         INTEGER                   NOT NULL,
  STATUS          TEXT                      NOT NULL,
  RIDER_ID        UUID                      NOT NULL,
  PRICE           DECIMAL(19, 2)            NOT NULL,
  DRIVER_ID       UUID,
  PLACED_DATE     TIMESTAMP WITH TIME ZONE  NOT NULL,
  ACCEPTED_DATE   TIMESTAMP WITH TIME ZONE,
  CANCELLED_DATE  TIMESTAMP WITH TIME ZONE,
  COMPLETED_DATE  TIMESTAMP WITH TIME ZONE
);

CREATE TABLE IF NOT EXISTS RM_ORDER_ROUTE (
  ORDER_ID   UUID              NOT NULL REFERENCES RM_ORDER (ID),
  ADDRESS    TEXT,
  LATITUDE   DOUBLE PRECISION  NOT NULL,
  LONGITUDE  DOUBLE PRECISION  NOT NULL,
  PRIMARY KEY (ORDER_ID, ADDRESS)
);

CREATE INDEX IF NOT EXISTS IDX_RM_ORDER_ROUTE_ORDER_ID ON RM_ORDER_ROUTE (ORDER_ID);