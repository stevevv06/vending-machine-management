-- SEED DATA FOR DATABASE

INSERT INTO role (name)
    SELECT 'ROLE_ADMIN' WHERE NOT EXISTS(SELECT * FROM role WHERE name='ROLE_ADMIN');
INSERT INTO role (name)
    SELECT 'ROLE_USER' WHERE NOT EXISTS(SELECT * FROM role WHERE name='ROLE_USER');

INSERT INTO bill_type (name, value, created_by)
    SELECT '$1', 1, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM bill_type WHERE name='$1');
INSERT INTO bill_type (name, value, created_by)
    SELECT '$2', 2, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM bill_type WHERE name='$2');

INSERT INTO coin_type (name, value, created_by)
    SELECT '$0.05', 0.05, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM coin_type WHERE name='$0.05');
INSERT INTO coin_type (name, value, created_by)
    SELECT '$0.10', 0.10, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM coin_type WHERE name='$0.10');
INSERT INTO coin_type (name, value, created_by)
    SELECT '$0.25', 0.25, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM coin_type WHERE name='$0.25');
INSERT INTO coin_type (name, value, created_by)
    SELECT '$0.50', 0.50, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM coin_type WHERE name='$0.50');

INSERT INTO vending_machine_model (name, accepts_coins, accepts_bills, accepts_credit_card, created_by)
    SELECT 'XYZ1', true, true, false, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM vending_machine_model WHERE name='XYZ1');
INSERT INTO vending_machine_model (name, accepts_coins, accepts_bills, accepts_credit_card, created_by)
    SELECT 'XYZ2', true, true, true, 'ADMIN'  WHERE NOT EXISTS(SELECT * FROM vending_machine_model WHERE name='XYZ2');