-- Create the TOOLS table
CREATE TABLE TOOL (
    code VARCHAR(10) PRIMARY KEY,
    tool_type VARCHAR(50),
    brand VARCHAR(50)
);

-- Create the TOOL_COST table
CREATE TABLE TOOL_COST (
    tool_type VARCHAR(50) PRIMARY KEY,
    daily_charge DOUBLE,
    weekday_charge BOOLEAN,
    weekend_charge BOOLEAN,
    holiday_charge BOOLEAN
);

-- Insert data into the TOOLS table
INSERT INTO TOOL (code, tool_type, brand) VALUES ('LADW', 'Ladder', 'Werner');
INSERT INTO TOOL (code, tool_type, brand) VALUES ('CHNS', 'Chainsaw', 'Stihl');
INSERT INTO TOOL (code, tool_type, brand) VALUES ('JAKD', 'Jackhammer', 'DeWalt');
INSERT INTO TOOL (code, tool_type, brand) VALUES ('JAKR', 'Jackhammer', 'Ridgid');

-- Insert data into the TOOL_COST table
INSERT INTO TOOL_COST (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge)
VALUES ('Ladder', 1.99, TRUE, TRUE, FALSE);
INSERT INTO TOOL_COST (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge)
VALUES ('Chainsaw', 1.49, TRUE, FALSE, TRUE);
INSERT INTO TOOL_COST (tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge)
VALUES ('Jackhammer', 2.99, TRUE, FALSE, FALSE);

commit;
