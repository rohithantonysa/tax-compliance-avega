-- 1. Pre-fill Dummy Users for Security (Passwords are 'password123' encrypted with BCrypt)
INSERT INTO users (username, password, role) 
VALUES ('admin', '$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HCGzzDExr5R6sFzE/vO/6', 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE username=username;

-- 2. Pre-fill the Mandatory Tax Rules with JSON configuration
INSERT INTO tax_rules (rule_name, is_active, severity, rule_configuration)
VALUES ('HIGH_VALUE_TRANSACTION', true, 'HIGH', '{"threshold": 50000}')
ON DUPLICATE KEY UPDATE rule_name=rule_name;

INSERT INTO tax_rules (rule_name, is_active, severity, rule_configuration)
VALUES ('GST_SLAB_VIOLATION', true, 'MEDIUM', '{"slabThreshold": 10000, "requiredRate": 0.18}')
ON DUPLICATE KEY UPDATE rule_name=rule_name;

INSERT INTO tax_rules (rule_name, is_active, severity, rule_configuration)
VALUES ('REFUND_VALIDATION', true, 'HIGH', '{}')
ON DUPLICATE KEY UPDATE rule_name=rule_name;