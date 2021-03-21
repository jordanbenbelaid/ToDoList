INSERT INTO urgency (name)
VALUES ('Urgent');
VALUES ('Non-Urgent');
VALUES ('Extremely Urgent');

INSERT INTO tasks (difficulty, length, name, description, fk_urgency_id)
VALUES('easy', 3, 'cooking', 'cook dinner for everyone', 2);
VALUES('hard', 3, 'work admin', 'sort out everybodys emails', 3);
VALUES('easy', 3, 'excercise', '30 minute workout', 2);