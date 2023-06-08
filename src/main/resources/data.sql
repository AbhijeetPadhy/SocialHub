INSERT INTO USERS (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('Frank Moley', 'fpmoles@gmail.com', 'fpmoles', '$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi');
INSERT INTO USERS (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('John Doe', 'john.doe@gmail.com', 'jdoe', '$2a$11$3NO32OV1TGjap3xMpAEjmuiizitWuaSwUYz42aMtlxRliwJ8zm4Sm');
INSERT INTO USERS (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('Abhijeet Padhy', 'abhijeet@gmail.com', 'apadhy', '$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi');
INSERT INTO USERS (NAME, EMAIL, USERNAME, PASSWORD) VALUES ('Lipu Padhy', 'lipu@gmail.com', 'lpadhy', '$2a$11$dp4wMyuqYE3KSwIyQmWZFeUb7jCsHAdk7ZhFc0qGw6i5J124imQBi');

INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has liked your Post!', '2023-06-03 10:30:00', FALSE, 'like');
INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has commented on your Post!', '2023-06-04 10:30:00', FALSE, 'comment');
INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has shared your Post!', '2023-06-05 10:30:00', FALSE, 'share');
INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has liked your Post!', '2023-06-06 10:30:00', FALSE, 'like');
INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has commented on your Post!', '2023-06-07 10:30:00', FALSE, 'comment');
INSERT INTO NOTIFICATIONS (USERNAME, CONTENT, CREATED, SEEN, TYPE) VALUES ('fpmoles', 'John Doe has shared your Post!', '2023-06-08 10:30:00', FALSE, 'share');

INSERT INTO MESSAGES (SENDER, RECEIVER, MESSAGE, CREATED, SEEN) VALUES ('jdoe', 'fpmoles', 'Hey Buddy! How are you?', '2023-06-03 10:30:00', FALSE);
INSERT INTO MESSAGES (SENDER, RECEIVER, MESSAGE, CREATED, SEEN) VALUES ('jdoe', 'fpmoles', 'Hey Buddy! You should die!', '2023-06-02 10:30:00', FALSE);
INSERT INTO MESSAGES (SENDER, RECEIVER, MESSAGE, CREATED, SEEN) VALUES ('apadhy', 'fpmoles', 'Hey Buddy! How are you?', '2023-06-03 10:30:00', FALSE);
INSERT INTO MESSAGES (SENDER, RECEIVER, MESSAGE, CREATED, SEEN) VALUES ('lpadhy', 'fpmoles', 'Hey Buddy! You should die!', '2023-06-02 10:30:00', FALSE);

INSERT INTO FOLLOWS (FOLLOWER, FOLLOWED_USER) VALUES ('fpmoles', 'fpmoles');
INSERT INTO POSTS (POST_ID, USERNAME, CONTENT, CREATED) VALUES (0, 'fpmoles', 'Hey Buddy How are you!', '2023-06-02 10:30:00');
INSERT INTO FEEDS (USERNAME, POST_ID) VALUES ('fpmoles', 0);

