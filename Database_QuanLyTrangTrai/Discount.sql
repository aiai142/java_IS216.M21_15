-- Tao bang Discount
CREATE TABLE Discount
(
    disID varchar2(10) PRIMARY KEY,
    disCode varchar2(20),
    value number(11,2),
    cusType varchar2(20),
    startDate date,
    endDate date
);

-- Insert du lieu
CREATE SEQUENCE dis_id
MINVALUE 1
MAXVALUE 9999999
INCREMENT BY 1
START WITH 1
NOCACHE
ORDER
NOCYCLE;
/
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'ELi8PwHzWu', 0.1, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'K3Up52gL24', 0.1, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'6Z3zCFSI22', 0.1, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'DGXu11dbBW', 0.1, 'Diamond', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'8p1HR05N8L', 0.1, 'Diamond', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'Ilivy0rFyx', 0.08, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'),	TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'VpOn4dqDLM', 0.08, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'),	TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'lxtoGxkDWc', 0.08, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'),	TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'Jfnzhe6tLS', 0.08, 'Diamond', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'br8YqmX77P', 0.08, 'Diamond', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'kqxE31ecEz', 0.08, 'Diamond', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'iR7HW4lDHl', 0.08, 'Diamond', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'MKz6a4fwaQ', 0.06, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'4svIvlu7tK', 0.06, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'fDRZi3vtzD', 0.06, 'Diamond', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'X3oPvR4K3e', 0.06, 'Diamond', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'Dll75VmylH', 0.06, 'Diamond', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'FqurOyUmE6', 0.06, 'Gold', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'rJK6rai5Df', 0.06, 'Gold', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'5uo7lwZRPM', 0.06, 'Gold', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'SgrtHVz0JO', 0.06, 'Gold', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'rymrY2cxnW', 0.05, 'Gold', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'XksmRxZ7gf', 0.05, 'Gold', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'EJWrp8ZMKn', 0.05, 'Gold', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'hk4qoUXr3L', 0.05, 'Gold', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'0fYfEweskp', 0.05, 'Gold', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'PFNpNWMAba', 0.05, 'Gold', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'HBuvbpEOCJ', 0.04, 'Gold', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'b153xXe6ZI', 0.04, 'Gold', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'UzZBvxHGv7', 0.04, 'Gold', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'qt8zaQ5ort', 0.04, 'Silver', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'pBqoBi9WGm', 0.04, 'Silver', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'6qLyIxO1ji', 0.04, 'Silver', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'AfWbn6OIRR', 0.04, 'Silver', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'QduGlbJGtU', 0.04, 'Silver', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'WURv8DX30T', 0.03, 'Silver', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'b1E7NQBUUt', 0.03, 'Silver', TO_DATE('31-MAR-2022', 'DD-MON-RR'), TO_DATE('10-APR-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'wDG2QLeEld', 0.03, 'Silver', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'BxFxFvJCsC', 0.03, 'Silver', TO_DATE('28-APR-2022', 'DD-MON-RR'), TO_DATE('07-MAY-2022', 'DD-MON-RR'));
INSERT INTO DISCOUNT VALUES ('D' || dis_id.nextval,	'JsWMIAegUh', 0.03, 'Silver', TO_DATE('26-MAY-2022', 'DD-MON-RR'), TO_DATE('08-JUN-2022', 'DD-MON-RR'));
