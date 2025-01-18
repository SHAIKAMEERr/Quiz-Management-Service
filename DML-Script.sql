INSERT INTO categories (name, description) VALUES
('Science', 'Science-related quizzes'),
('Mathematics', 'Math-related quizzes'),
('History', 'History-related quizzes');

INSERT INTO quizzes (title, description, category_id, difficulty_level) VALUES
('Physics Basics', 'A quiz on basic physics concepts', 1, 'EASY'),
('Advanced Algebra', 'Challenging algebra problems', 2, 'HARD'),
('World War II', 'Facts and events from WWII', 3, 'MEDIUM');


INSERT INTO questions (quiz_id, question_text, question_type) VALUES
(1, 'What is the unit of force?', 'MCQ'),
(1, 'Is gravity a force?', 'TRUE_FALSE'),
(2, 'What is the solution to 2x + 5 = 15?', 'MCQ'),
(3, 'When did World War II begin?', 'MCQ');


INSERT INTO options (question_id, option_text, is_correct) VALUES
-- For Question 1
(1, 'Newton', TRUE),
(1, 'Joule', FALSE),
(1, 'Pascal', FALSE),
-- For Question 2
(2, 'True', TRUE),
(2, 'False', FALSE),
-- For Question 3
(3, 'x = 5', TRUE),
(3, 'x = 10', FALSE),
-- For Question 4
(4, '1939', TRUE),
(4, '1945', FALSE),
(4, '1914', FALSE);
