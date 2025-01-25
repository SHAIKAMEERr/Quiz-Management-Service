-- Insert sample data for categories
INSERT INTO categories (name, description)
VALUES
    ('Science', 'Science-related quizzes'),
    ('History', 'History-related quizzes'),
    ('Mathematics', 'Mathematics-related quizzes');

-- Insert sample data for quizzes
INSERT INTO quizzes (title, description, category_id, difficulty_level)
VALUES
    ('Science Quiz 1', 'A basic science quiz', 1, 'EASY'),
    ('History Quiz 1', 'A history of world wars quiz', 2, 'MEDIUM'),
    ('Math Quiz 1', 'An algebra-based math quiz', 3, 'HARD');
    
-- Insert sample data for questions
INSERT INTO questions (quiz_id, category_id, question_text, question_type, difficulty_level)
VALUES
    (1, 1, 'What is the chemical symbol for water?', 'MCQ', 'EASY'),
    (2, 2, 'Who was the first president of the United States?', 'MCQ', 'MEDIUM'),
    (3, 3, 'What is the quadratic formula?', 'FILL_IN_THE_BLANK', 'HARD');
    
-- Insert sample data for options
INSERT INTO options (question_id, option_text, is_correct)
VALUES
    (1, 'H2O', TRUE),
    (1, 'O2', FALSE),
    (2, 'George Washington', TRUE),
    (2, 'Abraham Lincoln', FALSE),
    (3, 'x = (-b ± √(b² - 4ac)) / 2a', TRUE),
    (3, 'x = (-b ± √(b² + 4ac)) / 2a', FALSE);