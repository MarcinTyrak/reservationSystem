INSERT INTO EMPLOYEE (ID, LOGIN , PASSWORD , NAME , SURNAME , EMAIL , PHONE ) VALUES (1, 'emp1', 'test1', 'Josh', 'Smyte', 'js@gmail.com', '501-502-503');
INSERT INTO EMPLOYEE (ID, LOGIN , PASSWORD , NAME , SURNAME , EMAIL , PHONE ) VALUES (2, 'emp2', 'test2', 'Ally', 'South', 'as@gmail.com', '601-602-603');
INSERT INTO ROOM (ID, NAME, PLACES ) VALUES (1, 'Room 1', 40);
INSERT INTO ROOM (ID, NAME, PLACES ) VALUES (2, 'Room 2', 25);
INSERT INTO ROOM (ID, NAME, PLACES ) VALUES (3, 'Room 3', 10);
INSERT INTO ROOM (ID, NAME, PLACES ) VALUES (4, 'Room 4', 150);
INSERT INTO ADDITION (ID, NAME ) VALUES (1, 'Kawa');
INSERT INTO ADDITION (ID, NAME ) VALUES (2, 'Herbata');
INSERT INTO ADDITION (ID, NAME ) VALUES (3, 'Rzutnik');
INSERT INTO ADDITION (ID, NAME ) VALUES (4, 'Długopisy');
INSERT INTO ADDITION (ID, NAME ) VALUES (5, 'Notatniki');
INSERT INTO ADDITION (ID, NAME ) VALUES (6, 'Cukierki');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (1, '2024-08-12T12:00:00', 'PT45M', '2024-08-12T10:15:08', 1, 1, 'Lorem ipsum', 'Consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (2, '2024-08-13T12:00:00', 'PT45M', '2024-08-13T10:16:18', 2, 1, 'Dolor sit amet', 'Voluptate velit esse cillum dolore eu fugiat nulla');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (3, '2024-08-14T12:00:00', 'PT45M', '2024-08-14T10:17:28', 3, 1, 'Sed do eiusmod', 'Non proident, sunt in culpa qui officia deserunt mollit');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (4, '2024-08-15T12:00:00', 'PT45M', '2024-08-15T10:18:48', 4, 1, 'Incididunt ut labore', 'Omnis iste natus error sit voluptatem accusantium');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (5, '2024-08-13T16:00:00', 'PT45M', '2024-08-12T10:18:48', 1, 2, 'Ut enim ad minim', 'Totam rem aperiam, eaque ipsa quae ab illo');
INSERT INTO RESERVATION (ID, DATE, DURATION, CREATE, ROOM_ID, EMPLOYEE_ID, SUBJECT, INFORMATION ) VALUES (6, '2024-08-14T16:00:00', 'PT45M', '2024-08-12T10:28:48', 2, 2, 'Excepteur sint', 'Xercitation ullamco laboris nisi');
INSERT INTO REPORT (ID, STATUS, CREATE, ROOM_ID, EMPLOYEE_ID, CONTENT ) VALUES (1, 'DONE', '2024-07-18T18:12:14', 1, 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.');
INSERT INTO REPORT (ID, STATUS, CREATE, ROOM_ID, EMPLOYEE_ID, CONTENT ) VALUES (2, 'DONE', '2024-08-10T21:23:14', 2, 1, 'Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.');
INSERT INTO REPORT (ID, STATUS, CREATE, ROOM_ID, EMPLOYEE_ID, CONTENT ) VALUES (3, 'REJECTED', '2024-08-12T11:53:14', 3, 1, 'Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.');
INSERT INTO REPORT (ID, STATUS, CREATE, ROOM_ID, EMPLOYEE_ID, CONTENT ) VALUES (4, 'IN_PROGRESS', '2024-08-13T09:23:14', 1, 2, 'Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.');
INSERT INTO REPORT (ID, STATUS, CREATE, ROOM_ID, EMPLOYEE_ID, CONTENT ) VALUES (5, 'IN_PROGRESS', '2024-08-13T10:23:14', 2, 1, 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.');
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (1, 1);
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (1, 2);
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (1, 3);
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (2, 1);
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (2, 4);
INSERT INTO ADDITION_RESERVATIONS (ADDITIONS_ID, RESERVATIONS_ID ) VALUES (3, 6);

ALTER SEQUENCE EMPLOYEE_SEQ RESTART WITH 100;
ALTER SEQUENCE ROOM_SEQ RESTART WITH 100;
ALTER SEQUENCE ADDITION_SEQ RESTART WITH 100;
ALTER SEQUENCE RESERVATION_SEQ RESTART WITH 100;
ALTER SEQUENCE REPORT_SEQ RESTART WITH 100;
