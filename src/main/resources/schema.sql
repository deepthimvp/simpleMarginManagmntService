create sequence "INST_ID_SEQ" MINVALUE 1 MAXVALUE 9999 INCREMENT BY 1 START WITH 1;

DROP TABLE MARGIN_DATA;
CREATE TABLE MARGIN_DATA (
  margin_order INT,
  instruction VARCHAR(50),
  base_ccy VARCHAR(3),
  term_ccy VARCHAR(3),
  trader_tier VARCHAR(5),
  from_amt INT,
  to_amt INT,
  amt_ccy VARCHAR(3),
  bid_operator VARCHAR(5),
  bid_modifier VARCHAR(10),
  ask_operator VARCHAR(5),
  ask_modifier VARCHAR(10),
  remarks VARCHAR(100)
);

DROP TABLE STG_MARGIN_DATA;
CREATE TABLE STG_MARGIN_DATA (
  file_instnce_id INT,
  margin_order INT,
  instruction VARCHAR(50),
  base_ccy VARCHAR(3),
  term_ccy VARCHAR(3),
  trader_tier VARCHAR(5),
  from_amt INT,
  to_amt INT,
  amt_ccy VARCHAR(3),
  bid_operator VARCHAR(10),
  bid_modifier VARCHAR(10),
  ask_operator VARCHAR(10),
  ask_modifier VARCHAR(10),
  remarks VARCHAR(100)
);
