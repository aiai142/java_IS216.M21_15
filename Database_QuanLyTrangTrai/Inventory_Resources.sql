-- Tao bang Inventory_Resources
CREATE TABLE Inventory_Resources
(
    stockID varchar2(10) NOT NULL,
    reID varchar2(10) NOT NULL, 
    num_Inventory_Re number,
    
    CONSTRAINT PK_InvRe PRIMARY KEY(stockID, reID)
);
ALTER TABLE Inventory_Resources ADD CONSTRAINT FK01_InvRe FOREIGN KEY(stockID) REFERENCES Stock(stockID);
ALTER TABLE Inventory_Resources ADD CONSTRAINT FK02_InvRe FOREIGN KEY(reID) REFERENCES Resources(reID);


-- Insert du lieu
INSERT INTO INVENTORY_RESOURCES VALUES ('S9', 'R1', 1000);
INSERT INTO INVENTORY_RESOURCES VALUES ('S9', 'R6', 4000);
INSERT INTO INVENTORY_RESOURCES VALUES ('S9', 'R9', 680);
INSERT INTO INVENTORY_RESOURCES VALUES ('S9', 'R10', 3100);
INSERT INTO INVENTORY_RESOURCES VALUES ('S10', 'R2', 4000);
INSERT INTO INVENTORY_RESOURCES VALUES ('S10', 'R3', 3500);
INSERT INTO INVENTORY_RESOURCES VALUES ('S10', 'R4', 3800);
INSERT INTO INVENTORY_RESOURCES VALUES ('S10', 'R6', 385);
INSERT INTO INVENTORY_RESOURCES VALUES ('S11', 'R5', 3000);
INSERT INTO INVENTORY_RESOURCES VALUES ('S11', 'R8', 3500);
INSERT INTO INVENTORY_RESOURCES VALUES ('S12', 'R6', 300);
INSERT INTO INVENTORY_RESOURCES VALUES ('S12', 'R7', 5500);
INSERT INTO INVENTORY_RESOURCES VALUES ('S12', 'R9', 350);
INSERT INTO INVENTORY_RESOURCES VALUES ('S12', 'R10', 1100);