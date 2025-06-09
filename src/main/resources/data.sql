-- Insert initial categories if they don't exist
INSERT INTO categories (name) 
VALUES 
    ('Novel'),
    ('Science Fiction'),
    ('Mystery'),
    ('Romance'),
    ('Fantasy'),
    ('Horror'),
    ('Biography'),
    ('Self-Help'),
    ('History'),
    ('Comedy')
ON DUPLICATE KEY UPDATE name = VALUES(name);
